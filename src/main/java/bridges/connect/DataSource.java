package bridges.connect;

import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.util.HashMap;
import java.util.Iterator;

import java.io.File;

// exception related
import java.io.IOException;
import java.lang.Exception;
import com.google.gson.JsonParseException;
import org.apache.http.client.ClientProtocolException;
import com.google.gson.JsonParseException;


// parser related
import com.google.common.net.UrlEscapers;
import com.google.gson.Gson;
import org.json.simple.parser.JSONParser;


// encoder related
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;



// JSON related
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.*;

// HTTP related
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.HttpResponseException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.commons.codec.binary.Base64;

import bridges.base.*;
import bridges.data_src_dependent.*;
import bridges.validation.*;
import bridges.cache.LRUCache;
import bridges.connect.*;

/**
 *  @brief This class is the main BRIDGES class that facilitates access to
 *	external datasets
 *
 *  The DataSource class provides the BRIDGES API to all external datasets that
 *  are implemented in BRIDGES. These include earthquake data, song data, IMDB
 *	actor/movies, Wikidata, Gutenberg book collections, OpenStreet maps,
 *  Shakespeare, play, sonnets, poems,  Cancer incidence data and IGN games
 *  Each dataset is accessed through a function call, which typically returns
 * 	a list of objects with all associated attributes. These can then be used
 *	as part of a data structure, algorithm. Some initial support to import
 *  previously built data structures is also provided.
 */
public class DataSource {
	private Bridges bridges;
	private static LRUCache lru;
	private boolean debug = false;

    private String sourceType = "live";

    /**
     * @brief specify which server to use for accessing datasources.
     *
     * This is mostly useful for the BRIDGES team to debug issues with accessing data.
     *
     *  @param st should be \"live\", \"testing\", or \"local\"
     */
    public void setSourceType(String st) {
	if (!(st.equals("live") || st.equals("testing") || st.equals("local")))
	    throw new IllegalArgumentException ("parameter to DataSource::setSourceType should be \"live\", \"testing\", or \"local\"");

	//activating debug information is a side effect we probably
	//want in all cases where 
	if (st.equals("testing") || st.equals("local"))
	    debug = true;
	
	sourceType = new String(st);
    }

    private String getOSMBaseURL() {
	    if (sourceType.equals("local"))
		return "http://localhost:3000/";
		
		return "http://bridges-data-server-osm.bridgesuncc.org/";
	}

	private String getElevationBaseURL() {
	    if (sourceType.equals("local"))
		return "http://localhost:3000/";

		    return "http://bridges-data-server-elevation.bridgesuncc.org/";
	}

	private String getAmenityBaseURL() {
	    if (sourceType.equals("local"))
		return "http://localhost:3000/";

	    return "http://bridges-data-server-osm.bridgesuncc.org/";
	}

	private String getGutenbergBaseURL(){
	    if (sourceType.equals("local"))
		return "http://localhost:3000/";
	    if (sourceType.equals("testing"))
		return "http://bridges-data-server-gutenberg-t.bridgesuncc.org/";

		return "http://bridges-data-server-gutenberg.bridgesuncc.org/";
	}

	String  getRedditURL() {
		if (sourceType.equals("testing"))
			return "http://bridges-data-server-reddit-t.bridgesuncc.org";
		else if (sourceType.equals("local"))
			return "http://localhost:9999";
		else
			return "http://bridges-data-server-reddit.bridgesuncc.org";
	}




	DataSource() {
		if (lru == null)
			lru = new LRUCache(30);
	}

	DataSource(Bridges b) {
		bridges = b;
		if (lru == null)
			lru = new LRUCache(30);
	}

	/**
	 *  This helper function provides a simple API to retrieve current USGS earthquake
	 *	Tweet data from the USGS website (https://earthquake.usgs.gov/earthquakes/map/);
	 *  The data is retrieved and formatted into a list of EarthquakeUSGS objects.
	 *
	 *  More information on the dataset can be found at
	 *		https://bridgesuncc.github.io/datasets.html
	 *
	 * Refer to the tutorial  on how to use this dataset: https://bridgesuncc.github.io/tutorials/Data_EQ_USGS.html
	 *
	 *	@param maxElem  the number of earthquake records retrieved, limited to 5000
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of earthquake records
	 */
	public  List<EarthquakeUSGS> getEarthquakeUSGSData(
		int maxElem) throws IOException {
		String url = "http://earthquakes-uncc.herokuapp.com/eq/latest/" + maxElem;
		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();

		if (status == 200) 	{
			JSONArray json = unwrapJSONArray(response, "Earthquakes");

			ArrayList<EarthquakeUSGS> eq_list =
				new ArrayList<EarthquakeUSGS>(json.size());
			for (int i = 0; i < json.size(); i++) {
				JSONObject item = (JSONObject)json.get(i);
				JSONObject props = (JSONObject)item.get("properties");
				JSONArray coords = (JSONArray)
					((JSONObject)item.get("geometry")).get("coordinates");

				EarthquakeUSGS eq = new EarthquakeUSGS();

				eq.setMagnitude(((Number)props.get("mag")).doubleValue());
				eq.setLatit(((Number)coords.get(1)).doubleValue());
				eq.setLongit(((Number)coords.get(0)).doubleValue());
				eq.setLocation((String)props.get("place"));
				eq.setTitle((String)props.get("title"));
				eq.setUrl((String)props.get("url"));
				eq.setTime ((String)props.get("time"));
				eq_list.add(eq);
			}
			return eq_list;
		}
		else {
			throw new HttpResponseException(status, "HTTP Request Failed. Error Code: " + status);
		}
	}

	/**
	 *  @brief This function provides access to a collection of Shakespeare plays,
	 * 	poems and plays.
	 *
	 * This function return all the works of Shakespeare in the collection.
	 *
	 *  Each record in this collection has
	 *	information on title, type (poem, Sonnet, play) and text.
	 *
	 *	For more information and to look at the data, refer to https://bridgesuncc.github.io/datasets.html
	 *
 * Refer to the tutorial  on how to use this dataset: https://bridgesuncc.github.io/tutorials/Data_Shakespeare.html
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of Shakespeare objects.
	 */
	public  List<Shakespeare> getShakespeareData() throws Exception {
		return getShakespeareData("");
	}
	/**
	 *  @brief This function provides access to a collection of Shakespeare plays,
	 * 	poems and plays.
	 *
	 *
	 *  Each record in this collection has
	 *	information on title, type (poem, Sonnet, play) and text.
	 *
	 *	For more information and to look at the data, refer to https://bridgesuncc.github.io/datasets.html
	 *
	 * This function returns only some of the works, either the plays, or the poems, depending on the parameters.
	 *
	 *
	 * Refer to the tutorial  on how to use this dataset: https://bridgesuncc.github.io/tutorials/Data_Shakespeare.html
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @param works return only some of the works. "Plays" for only the plays, "Poems", or "" for all.
	 *
	 *  @return a list of Shakespeare objects.
	 */
    public  List<Shakespeare> getShakespeareData(String works, Boolean textOnly) throws Exception {
		String url = "https://bridgesdata.herokuapp.com/api/shakespeare";

		if (works == "plays" || works == "poems") {
			url += "/" + works;
		}

		if (textOnly) {
			url += "?format=simple";
		}

		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();

		if (status == 200) 	{
			JSONArray json = unwrapJSONArray(response);

			List<Shakespeare> shksp_list =
				new ArrayList<Shakespeare>(json.size());
			for (int i = 0; i < json.size(); i++) {
				JSONObject item = (JSONObject)json.get(i);

				Shakespeare shksp = new Shakespeare();

				shksp.setTitle((String) item.get("title"));
				shksp.setType((String) item.get("type"));
				shksp.setText((String) item.get("text"));
				shksp_list.add(shksp);
			}
			return shksp_list;
		}
		else {
			throw new HttpResponseException(status, "HTTP Request Failed. Error Code: " + status);
		}
	}

    /**
     *
     * Refer to the tutorial  on how to use this dataset: https://bridgesuncc.github.io/tutorials/Data_Shakespeare.html
     *
     */
    public  List<Shakespeare> getShakespeareData(String works) throws Exception {
	return getShakespeareData(works, false);
    }
    
	/**
	 *  This helper function provides access to a cancer dataset from CDC
	 *  https://www.cdc.gov/cancer/npcr/uscs/download_data.htm and curated
	 *  by Austin (Cory) Bart as part of the Corgis data collection
	 *	https://think.cs.vt.edu/corgis/
	 *
	 *  Each record in this collection has a number of fields detailed in the
	 *	CancerIncidence class.
	 *
	 *	For more information and to look at the data, refer to <p>
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://bridgesuncc.github.io/datasets.html <p>
	 *
	 * Refer to tutorial examples on how to use this dataset: https://bridgesuncc.github.io/tutorials/Data_CancerIncidence.html

	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of Cancer incidence objects.
	 */
	public List<CancerIncidence> getCancerIncidenceData() throws Exception {
		String url = "https://bridgesdata.herokuapp.com/api/cancer/withlocations";
		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();

		if (status == 200) 	{
			JSONArray json = unwrapJSONArray(response);

			List<CancerIncidence> canc_objs =
				new ArrayList<CancerIncidence>(json.size());
			for (int i = 0; i < json.size(); i++) {
				JSONObject item = (JSONObject) json.get(i);

				CancerIncidence c = new CancerIncidence();

				JSONObject age = (JSONObject) item.get("Age");
				c.setAgeAdjustedRate(((Number)
						age.get("Age Adjusted Rate")).doubleValue());
				c.setAgeAdjustedCI_Lower(((Number)
						age.get("Age Adjusted CI Lower")).doubleValue());
				c.setAgeAdjustedCI_Upper(((Number)
						age.get("Age Adjusted CI Upper")).doubleValue());

				JSONObject data = (JSONObject) item.get("Data");
				c.setCrudeRate(((Number) data.get("Crude Rate")).doubleValue());
				c.setCrudeRate_CI_Lower(
					((Number) data.get("Crude CI Lower")).doubleValue());
				c.setCrudeRate_CI_Upper(
					((Number) data.get("Crude CI Upper")).doubleValue());
				c.setRace((String) data.get("Race"));
				c.setGender((String) data.get("Sex"));
				c.setYear(((Number) item.get("Year")).intValue());
				c.setEventType((String) data.get("Event Type"));
				c.setPopulation(((Number) data.get("Population")).intValue());
				c.setAffectedArea((String) item.get("Area"));
				JSONArray loc = (JSONArray) item.get("loc");
				c.setLocationX (((Number) loc.get(0)).doubleValue());
				c.setLocationY (((Number) loc.get(1)).doubleValue());

				canc_objs.add(c);
			}
			return canc_objs;
		}
		else {
			throw new HttpResponseException(status, "HTTP Request Failed. Error Code: " + status);
		}
	}


	/**
	 * Generates Open Street Map URL request for a given location at general level of details and returns the map data
	 *
	 * Check out how to use OSM data at: https://bridgesuncc.github.io/tutorials/Data_OSM.html
	 *
	 * @param location name of city or area that the server supports
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	public OsmData getOsmData(String location) throws IOException {
		return getOsmDataDS(location, "default");
	}
	/**
	* Generates Open Street Map URL request for a given location and returns the map data
	*
	* Check out how to use OSM data at: https://bridgesuncc.github.io/tutorials/Data_OSM.html
	*
	 * @param location name of city or area that the server supports
	 * @param level level of road detail on requested map
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	public OsmData getOsmData(String location, String level) throws
		IOException {
		return getOsmDataDS(location, level);
	}

	/**
	 * Generates Open Street Map URL request for a given set of coordinates with default level and returns the map data
	 *
	 * Check out how to use OSM data at: https://bridgesuncc.github.io/tutorials/Data_OSM.html

	 *
	 * @param minLat minimum latitude value for the area requested
	 * @param minLon minimum longitude value for the area requested
	 * @param maxLat maximum latitude value for the area requested
	 * @param maxLon maximum longitude value for the area requested
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	public OsmData getOsmData(double minLat, double minLon, double maxLat,
		double maxLon) throws IOException {
		return getOsmDataDS(minLat, minLon, maxLat, maxLon, "default");
	}
	/**
	 * Generates Open Street Map URL request for a given set of coordinates with default level and returns the map data
	 *
	 * Check out how to use OSM data at: https://bridgesuncc.github.io/tutorials/Data_OSM.html
	 *
	 * @param minLat minimum latitude value for the area requested
	 * @param minLon minimum longitude value for the area requested
	 * @param maxLat maximum latitude value for the area requested
	 * @param maxLon maximum longitude value for the area requested
	 * @param level resolution at which the data is to be retrieved
	 *
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	public OsmData getOsmData(double minLat, double minLon, double maxLat,
		double maxLon, String level) throws IOException {
		return getOsmDataDS(minLat, minLon, maxLat, maxLon, level);
	}


	private HttpResponse makeRequest(String url) throws ClientProtocolException,
		IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		return client.execute(request);
	}

	private JSONArray unwrapJSONArray(HttpResponse response, String get) throws IOException {
		String result = EntityUtils.toString(response.getEntity());
		JSONObject full = (JSONObject)JSONValue.parse(result);
		return (JSONArray)full.get(get);
	}

	private JSONArray unwrapJSONArray(HttpResponse response) throws IOException {
		return	unwrapJSONArray(response, "data");
	}
	private ActorMovieIMDB parseActorMovieIMDB(JSONObject item) {
		ActorMovieIMDB am_pair = new ActorMovieIMDB();
		am_pair.setActor((String) item.get("actor"));
		am_pair.setMovie((String) item.get("movie"));
		return am_pair;
	}

	/**
	 *  Get ActorMovie IMDB Data
	 *  retrieved, formatted into a list of ActorMovieIMDB objects
	 *
	 *  Check out the tutorial on the IMDB dataset at https://bridgesuncc.github.io/tutorials/Data_IMDB.html
	 *
	 *  @param maxElem the number of actor/movie pairs
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of ActorMovieIMDB objects, but only actor and movie fields
	 * 				in this version
	 */
	public List<ActorMovieIMDB> getActorMovieIMDBData(int maxElem)
	throws IOException, IllegalArgumentException {

		String url = "https://bridgesdata.herokuapp.com/api/imdb";

		if (maxElem > 0) {
			url += "?limit=" + maxElem;
		}
		else {
			throw new IllegalArgumentException("Must provide a valid number of Actor/Movie pairs to return.");
		}

		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();

		if (status == 200) 	{
			JSONArray json = unwrapJSONArray(response, "data");

			List<ActorMovieIMDB> am_list =
				new ArrayList<ActorMovieIMDB>(json.size());
			for (int i = 0; i < json.size(); i++) {
				JSONObject item = (JSONObject)json.get(i);

				ActorMovieIMDB am_pair = parseActorMovieIMDB(item);
				am_list.add(am_pair);
			}
			return am_list;
		}
		else {
			throw new HttpResponseException(status, "HTTP Request Failed. Error Code: " + status);
		}
	}
	/**
	 *  This helper function provides access to a second curated IMDB
	 *	dataset; the data is retrieved, formatted into a list of
	 *	ActorMovieIMDB objects
	 *
	 *  This version of the IMDB Actor/Movie data contains for each record,
	 *	actor name, movie name, movie genres, movie rating; refer to <p>
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://bridgesuncc.github.io/datasets.html <p>
	 *  for more information and to look at the dataset.
	 *
	 *
	 * Check out the tutorial on the IMDB dataset at https://bridgesuncc.github.io/tutorials/Data_IMDB.html
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of ActorMovieIMDB objects
	 */
	public List<ActorMovieIMDB> getActorMovieIMDBData2 ()
	throws IOException {

		String url = "https://bridgesdata.herokuapp.com/api/imdb2";
		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();

		if (status == 200) 	{
			JSONArray json = unwrapJSONArray(response);

			List<ActorMovieIMDB> am_list =
				new ArrayList<ActorMovieIMDB>(json.size());
			for (int i = 0; i < json.size(); i++) {
				JSONObject item = (JSONObject)json.get(i);

				ActorMovieIMDB am_pair = parseActorMovieIMDB(item);
				am_pair.setMovieRating(((Number) item.get("rating")).doubleValue());
				JSONArray genre = (JSONArray) item.get("genres");

				Vector<String> v = new Vector<String>();
				for (int k = 0; k < genre.size(); k++)
					v.add((String)genre.get(k));
				am_pair.setGenres(v);
				am_list.add(am_pair);
			}
			return am_list;
		}
		else {
			throw new HttpResponseException(status, "HTTP Request Failed. Error Code: " + status);
		}
	}

	/**
	 *  This helper function provides access to the meta-data of the video game
	 *	collection.
	 *
	 *  Each record in this collection has
	 *	information on game title, platform, rating, and genre. For more information
	 *	and to look at the data, refer to <p>
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://bridgesuncc.github.io/datasets.html <p>
	 *
	 * Refer to tutorial examples on how to use this dataset: https://bridgesuncc.github.io/tutorials/Data_IGN_Games.html
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of Game objects.
	 */
	public List<Game> getGameData() throws IOException {

		String url = "https://bridgesdata.herokuapp.com/api/games";
		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();

		if (status == 200) 	{
			JSONArray json = unwrapJSONArray(response);

			ArrayList<Game> game_list =
				new ArrayList<Game>(json.size());
			for (int i = 0; i < json.size(); i++) {
				JSONObject item = (JSONObject)json.get(i);

				Game game = new Game();

				game.setTitle((String) item.get("game"));
				game.setPlatformType((String) item.get("platform"));
				game.setRating(((Number) item.get("rating")).doubleValue());
				JSONArray genre = (JSONArray) item.get("genre");

				Vector<String> v = new Vector<String>();
				for (int k = 0; k < genre.size(); k++)
					v.add((String)genre.get(k));
				game.setGenre(v);

				game_list.add(game);

			}
			return game_list;
		}
		else {
			throw new HttpResponseException(status, "HTTP Request Failed. Error Code: " + status);
		}
	}

	// parse a Song -- for internal use only
	private Song parseSong(JSONObject songJSON) {
		Song song = new Song();
		song.setArtist((String) songJSON.get("artist"));
		song.setSongTitle((String) songJSON.get("song"));
		song.setAlbumTitle((String) songJSON.get("album"));
		song.setLyrics((String) songJSON.get("lyrics"));
		song.setReleaseDate(((String) songJSON.get("release_date")));

		return song;
	}

	/**
	 *  This helper function provides access to the meta-data of the lyrics
	 *	collection.
	 *
	 *  Each record in this collection has
	 *	information on song title, artist, album, year, lyrics, and genre.
	 *	For more information and to look at the data, refer to <p>
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://bridgesdata.herokuapp.com/api/datasets/songs <p>
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of Song objects.
	 */
	public ArrayList<Song> getSongData() throws IOException {

		String url = "https://bridgesdata.herokuapp.com/api/songs";
		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();

		if (status == 200) 	{
			JSONArray json = unwrapJSONArray(response);

			ArrayList<Song> song_list =
				new ArrayList<Song>(json.size());
			for (int i = 0; i < json.size(); i++) {
				JSONObject item = (JSONObject)json.get(i);
				Song song = parseSong(item);

				song_list.add(song);

			}
			return song_list;
		}
		else {
			throw new HttpResponseException(status, "HTTP Request Failed. Error Code: " + status);
		}
	}
	/**
	 *  These helper functions provides access to a particular song.
	 *
	 *  The record has information such as song title, artist, album, year,
	 *	lyrics, and genre. For more information
	 *	and to look at the data, refer to <p>
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://bridgesdata.herokuapp.com/api/datasets/songs <p>
	 *
	 *  @param songTitle  title of song (string)
	 *  @param artistName  name of artist (string), empty string for unspecified
	 *  @throws Exception if the request fails
	 *
	 *  @return a Song object.
	 */
	public Song getSong(String songTitle, String artistName)
	throws IOException {
		String url = "https://bridgesdata.herokuapp.com/api/songs/find/";

		// add the song title to the query url
		if (songTitle.length() > 0) {
			url += URLEncoder.encode(songTitle, StandardCharsets.UTF_8.name());
		}
		else {
			throw new IllegalArgumentException("Must provide a valid song title.");
		}
		// add the artist name as a query variable where appropriate
		if (artistName.length() > 0) {
			url += "?artistName=" +
				URLEncoder.encode(artistName, StandardCharsets.UTF_8.name());
		}

		// Create and execute the HTTP request
		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();
		String result = EntityUtils.toString(response.getEntity());

		if (status == 200) 	{
			JSONObject songJSON = (JSONObject)JSONValue.parse(result);
			Song song = parseSong(songJSON);

			return song;
		}
		else {
			throw new HttpResponseException(status, "HTTP Request Failed. Error Code: " + status + ". Message: " + result);
		}
	}
	/**
	 *  These helper functions provides access to a particular song.
	 *
	 *  The record has information such as song title, artist, album, year,
	 *	lyrics, and genre. For more information
	 *	and to look at the data, refer to <p>
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://bridgesdata.herokuapp.com/api/datasets/songs <p>
	 *
	 * Refer to tutorial for example of using this feature: https://bridgesuncc.github.io/tutorials/Data_Song_Lyrics.html
	 *
	 *  @param songTitle  title of song (string)
	 *  @throws Exception if the request fails
	 *
	 *  @return a Song object.
	 */
	public Song getSong(String songTitle)
	throws IOException {
	    return getSong(songTitle, "");
	}

    
	/**
	 * @brief Generates Open Street Map URL request for a given location and returns the map data
	 *
	 * @param location  name of city or area that the server supports
	 * @param level  level of road detail on requested map
	 * @return OsmData  vertices and edges of Open Street Map data
	 * @throws IOException  If there is an error parsing response from server or is an invalid location name
	 */
	private OsmData getOsmDataDS(String location, String level)
	throws IOException {
		// URL to request map
		String osm_url = getOSMBaseURL() +
			"loc?location=" + URLEncoder.encode(location, StandardCharsets.UTF_8.name()) +
			"&level="  		+ URLEncoder.encode(level, StandardCharsets.UTF_8.name());

		// URL to get hash code
		String hash_url = getOSMBaseURL() +
			"hash?location=" + URLEncoder.encode(location, StandardCharsets.UTF_8.name()) +
			"&level="  		 + URLEncoder.encode(level, StandardCharsets.UTF_8.name());

		return (parseOSMData(osm_url, hash_url));
	}
	/**
	 * @brief Generates Open Street Map URL request for a given set of coordinates and returns the map data
	 *
	 * @param minLat  minimum latitude value for the area requested
	 * @param minLon  minimum longitude value for the area requested
	 * @param maxLat  maximum latitude value for the area requested
	 * @param maxLon  maximum longitude value for the area requested
	 * @param level  level of road detail on requested map
	 * @return OsmData  vertices and edges of Open Street Map data
	 * @throws IOException  If there is an error parsing response from server or is an invalid location name
	 */
	private OsmData getOsmDataDS(double minLat, double minLon,
		double maxLat, double maxLon, String level) throws IOException {

		// URL to request map
		String osm_url = getOSMBaseURL() +
			"coords?minLon=" + URLEncoder.encode(Double.toString(minLon), StandardCharsets.UTF_8.name()) +
			"&minLat=" + URLEncoder.encode(Double.toString(minLat), StandardCharsets.UTF_8.name()) +
			"&maxLon=" + URLEncoder.encode(Double.toString(maxLon), StandardCharsets.UTF_8.name()) +
			"&maxLat=" + URLEncoder.encode(Double.toString(maxLat), StandardCharsets.UTF_8.name()) +
			"&level="  + URLEncoder.encode(level, StandardCharsets.UTF_8.name());

		// URL to get hash code
		String hash_url = getOSMBaseURL() +
			"hash?minLon=" + URLEncoder.encode(Double.toString(minLon), StandardCharsets.UTF_8.name()) +
			"&minLat=" + URLEncoder.encode(Double.toString(minLat), StandardCharsets.UTF_8.name()) +
			"&maxLon=" + URLEncoder.encode(Double.toString(maxLon), StandardCharsets.UTF_8.name()) +
			"&maxLat=" + URLEncoder.encode(Double.toString(maxLat), StandardCharsets.UTF_8.name()) +
			"&level="  + URLEncoder.encode(level, StandardCharsets.UTF_8.name());


		return (parseOSMData(osm_url, hash_url));
	}

	/**
	 * @brief parses the OSM data and caches maps requested
	 *
	 * @param url  string of the url that will be used when requesting
	 *		map data from server
	 * @param hashUrl string of the url that will be used when requesting
	 *		hash data from serverp
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from
	 *		server or is an invalid location name
	 */
	private OsmData parseOSMData(String osm_url, String hash_url)
	throws IOException {

		String osm_json = getDataSetJSON(osm_url, hash_url);

		// parse that data
		Gson gson = new Gson();
		OsmServerResponse respObject;
		try {
			respObject = gson.fromJson(osm_json, OsmServerResponse.class);
		}
		catch (Exception e) {
			throw new JsonParseException("Malformed JSON: Unable to Parse");
		}

		// vertex list
		OsmVertex[] vertices = new OsmVertex[respObject.nodes.length];
		HashMap<Double, Integer> vert_map = new HashMap<>();
		for (int i = 0; i < vertices.length; ++i) {
			Double[] node = respObject.nodes[i];
			vert_map.put(node[0], i);
			vertices[i] = new OsmVertex(node[1], node[2]);
		}

		// edge list
		OsmEdge[] edges = new OsmEdge[respObject.edges.length];
		for (int i = 0; i < edges.length; ++i) {
			Double[] edge = respObject.edges[i];
			Double id_from = edge[0];
			Double id_to = edge[1];
			Double dist = edge[2];
			edges[i] = new OsmEdge(vert_map.get(id_from), vert_map.get(id_to), dist);
		}

		OsmData ret_data = new OsmData(vertices, edges, respObject.meta.name);
		return ret_data;
	}

	/**
	 * This method retrieves the specified amenity related data given a location
	 * from a specified openstreet mmap location
	 *
	 * Check out the tutorial on getting amenity data at https://bridgesuncc.github.io/tutorials/Data_Amenity.html
	 *
	 *	@param location city/town from where amenity data is sought
	 *	@param amenity  amenity type
	 *	@throws exception
	 */
	public AmenityData getAmenityData(String location, String amenity)
	throws IOException {

		String amenity_url = getAmenityBaseURL() +
			"amenity?location=" + URLEncoder.encode(location, StandardCharsets.UTF_8.name()) +
			"&amenity=" + URLEncoder.encode(amenity, StandardCharsets.UTF_8.name());

		String hash_url = getAmenityBaseURL() +
			"hash?location=" + URLEncoder.encode(location, StandardCharsets.UTF_8.name()) +
			"&amenity=" + URLEncoder.encode(amenity, StandardCharsets.UTF_8.name());

		return (parseAmenityData(amenity_url, hash_url));
	}

	/**
	 * This method retrieves the specified amenity related data given a
	 * bounding box of a region, from a Open Street map
	 *
	 * Check out the tutorial on getting amenity data at https://bridgesuncc.github.io/tutorials/Data_Amenity.html
	 *
	 *	@param minLat  minimum latitude
	 *	@param minLon  minimumm longitude
	 *	@param maxLat  maximum latitude
	 *	@param maxLon  maximum longitude
	 *	@param amenity  amenity type
	 *	@throws exception
	 */
	public AmenityData getAmenityData(double minLat, double minLon, double
		maxLat, double maxLon, String amenity) throws IOException {

		String data_url = getAmenityBaseURL() +
			"amenity?minLon=" + Double.toString(minLon) +
			"&minLat=" + Double.toString(minLat) +
			"&maxLon=" + Double.toString(maxLon) +
			"&maxLat=" + Double.toString(maxLat) +
			"&amenity=" + URLEncoder.encode(amenity, StandardCharsets.UTF_8.name());

		String hash_url = getAmenityBaseURL() +
			"hash?minLon=" + Double.toString(minLon) +
			"&minLat=" + Double.toString(minLat) +
			"&maxLon=" + Double.toString(maxLon) +
			"&maxLat=" + Double.toString(maxLat) +
			"&amenity=" + URLEncoder.encode(amenity, StandardCharsets.UTF_8.name());

		return (parseAmenityData(data_url, hash_url));
	}

	/**
	 * @brief Downloads and caches amenity requested
	 *
	 * @param url  string of the url that will be used when requesting
	 *		amenity data from server
	 * @param hashUrl string of the url that will be used when requesting
	 *		hash data from server
	 * @return AmenityData object containing coordinates and meta data
	 * @throws IOException If there is an error parsing response from
	 *		server or is an invalid location name
	 */
	private AmenityData parseAmenityData(String amenity_url, String hash_url)
	throws IOException {

		// get the JSON of the amenity data
		String amenity_json = getDataSetJSON(amenity_url, hash_url);

		// Parse Data into object
		AmenityData amenity_data = new AmenityData();
		JSONParser parser = new JSONParser();

		try {
			JSONObject json = (JSONObject) parser.parse(amenity_json);
			JSONArray nodes = (JSONArray) json.get("nodes");
			JSONObject meta = (JSONObject) json.get("meta");


			amenity_data.setCount(Integer.parseInt(meta.get("count").toString()));
			amenity_data.setMinLat(Double.parseDouble(meta.get("minlat").toString()));
			amenity_data.setMinLon(Double.parseDouble(meta.get("minlon").toString()));
			amenity_data.setMaxLat(Double.parseDouble(meta.get("maxlat").toString()));
			amenity_data.setMaxLon(Double.parseDouble(meta.get("maxlon").toString()));


			Iterator<JSONArray> iter = nodes.iterator();
			while (iter.hasNext()) {
				JSONArray sub_data = iter.next();
				Amenities amen = new Amenities();
				amen.setId(Double.parseDouble(sub_data.get(0).toString()));
				amen.setLat(Double.parseDouble(sub_data.get(1).toString()));
				amen.setLon(Double.parseDouble(sub_data.get(2).toString()));
				amen.setName(sub_data.get(3).toString());

				amenity_data.addAmenities(amen);
			}
		}
		catch (Exception e) {
			System.out.println("Error Parsing Amenity Json: " + e);
		}

		return amenity_data;
	}
	/**
	 * @brief This method takes in both a dataset url and a hash url
	 *  and returns the JSON string of the external dataset based on the query
	 *
	 *  This method is a utility function that supports retrieving
	 *  external dataset given a url to the dataset's server as well
	 *  as a url to extract a hashcode for the dataset; the latter is
	 *  is to suppor local caching. The dataset is only retrieved
	 *  the server if a local copy is not available
	 *
	 *  Currently this function works with elevation, OpenStreet maps and
	 *  Amenity datasets
	 *
	 */
	private String getDataSetJSON(String data_url, String hash_url)
	throws IOException {

		// look for dataset in cache
		String data_content = null;
		String hash = null;

		if (debug)
			System.err.println("Hitting hash URL: " + hash_url);

		// get the hash code of the dataset
		HttpResponse hashResp = makeRequest(hash_url);
		int hashStatus = hashResp.getStatusLine().getStatusCode();
		hash = EntityUtils.toString(hashResp.getEntity());

		if (debug)
			System.err.println("Hash is: " + hash);

		if (!hash.equals("false") && hashStatus == 200 && lru.inCache(hash) == true) {
			if (debug)
				System.err.println("hash is in cache");

			data_content = lru.getDoc(hash);
		}

		// if not in cache, hit server for data
		if (data_content == null) {
			if (debug)
				System.err.println("hash is not in cache");

			if (debug)
				System.err.println("Hitting data URL: " + data_url);

			HttpResponse resp = makeRequest(data_url);

			int status = resp.getStatusLine().getStatusCode();
			if (debug)
			    System.err.println("[Data request:] Status code:" + status);

			if (status != 200) {
				throw new HttpResponseException(status, "Http Request Failed. Error Code:"
					+ status + ". Message:" + data_content);
			}

			data_content = EntityUtils.toString(resp.getEntity());

			if (debug)
				System.err.println("Hitting hash URL: " + hash_url);

			// get hash code of dataset
			hashResp = makeRequest(hash_url);

			hash = EntityUtils.toString(hashResp.getEntity());

			if (debug) {
				System.err.println("[Hash Request:] Status code:" + resp.getStatusLine().getStatusCode());
				System.err.println("Hash is: " + hash);
			}

			//Checks to see if valid hash is generated
			if (!hash.equals("false")) {
			    try{
				lru.putDoc(hash, data_content);
			    } catch (Exception e) {
				System.err.println("Could not store data in cache. Ignoring exception:\n"+e.getMessage());
			    }
			}
		}
		return data_content;
	}

	/**
	 * @brief This method retrieves the elevation map of a region given the lat/long
	 * range (bounding box) and resolution level.
	 *
	 * Note that the ElevationData that is returned
	 * may have slightly different location and resolution.
	 *
	 * A tutorial on how to use the Elevation dataset is available at: https://bridgesuncc.github.io/tutorials/Data_Elevation.html
	 *
	 * @param minLat minimum latitude requested
	 * @param minLon minimum longitude requested
	 * @param maxLat maximum latitude requested
	 * @param maxLon maximum longitude requested
	 * @param res spatial resolution, aka the distance between two samples (in degrees)
	 * @return a ElevationData object mapping a region close to the box requested
	 */

	public ElevationData getElevationData(double minLat, double minLon,
		double maxLat, double maxLon, double res) throws IOException {

		boolean debug = false;

		String data_url = getElevationBaseURL() +
			"elevation?minLon=" + URLEncoder.encode(Double.toString(minLon), StandardCharsets.UTF_8.name()) +
			"&minLat=" + URLEncoder.encode(Double.toString(minLat), StandardCharsets.UTF_8.name()) +
			"&maxLon=" + URLEncoder.encode(Double.toString(maxLon), StandardCharsets.UTF_8.name()) +
			"&maxLat=" + URLEncoder.encode(Double.toString(maxLat), StandardCharsets.UTF_8.name()) +
			"&resX="   + URLEncoder.encode(Double.toString(res), StandardCharsets.UTF_8.name()) +
			"&resY="   + URLEncoder.encode(Double.toString(res), StandardCharsets.UTF_8.name());

		String hash_url = getElevationBaseURL() +
			"hash?minLon=" + URLEncoder.encode(Double.toString(minLon), StandardCharsets.UTF_8.name()) +
			"&minLat=" + URLEncoder.encode(Double.toString(minLat), StandardCharsets.UTF_8.name()) +
			"&maxLon=" + URLEncoder.encode(Double.toString(maxLon), StandardCharsets.UTF_8.name()) +
			"&maxLat=" + URLEncoder.encode(Double.toString(maxLat), StandardCharsets.UTF_8.name()) +
			"&resX="   + URLEncoder.encode(Double.toString(res), StandardCharsets.UTF_8.name()) +
			"&resY="   + URLEncoder.encode(Double.toString(res), StandardCharsets.UTF_8.name());


		String elev_data_json = getDataSetJSON(data_url, hash_url);


		//Parse Data into object
		int cols = 0;
		int rows = 0;
		double xll = 0;
		double yll = 0;
		double cellsize = 0;
		int maxVal = Integer.MIN_VALUE;
		int minVal = Integer.MAX_VALUE;

		String [] lines = elev_data_json.split("\n");
		cols = Integer.parseInt(lines[0].replaceAll("[^0-9]", ""));
		rows = Integer.parseInt(lines[1].replaceAll("[^0-9]", ""));
		xll = Double.parseDouble(lines[2].replaceAll("[^0-9.]", ""));
		yll = Double.parseDouble(lines[3].replaceAll("[^0-9.]", ""));
		cellsize = Double.parseDouble(lines[4].replaceAll("[^0-9.]", ""));

		int[][] data = new int[rows][cols];
		int crows = 0;

		for (int i = 5; i < lines.length; i++) {
			String [] vals = lines[i].split(" ");
			int [] intVals = new int[vals.length - 1];
			for (int x = 0; x < vals.length - 1; x++) {
				intVals[x] = Integer.parseInt(vals[x + 1]);
			}
			data[crows] = intVals;
			crows++;
		}

		//Finds and sets the max elevation value in the set
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] > maxVal) {
					maxVal = data[i][j];
				}
				if (data[i][j] < minVal) {
					minVal = data[i][j];
				}

			}
		}

		ElevationData ret_data = new ElevationData(data, cols, rows, xll, yll,
							   cellsize, maxVal, minVal);
		return ret_data;
	}

	private String requestJSON(String url) throws IOException {
		String data = "";
		if (debug)
			System.err.println("Hitting URL: " + url);

		// get the hash code of the dataset
		HttpResponse dataResp = makeRequest(url);
		int requestStatus = dataResp.getStatusLine().getStatusCode();
		data = EntityUtils.toString(dataResp.getEntity());

//		if (debug)
//			System.err.println("Data is: " + data);

		if (requestStatus == 200){
			return data;
		} else {
			throw new HttpResponseException(requestStatus, "Http Request Failed. Error Code:"
					+ requestStatus + ". Message:" + data);
		}
		
		
	}
    /**
     * @brief Search the gutenberg data for retrieving meta
     *   data of books matching a string and a category
     *
     *  Data is retrieved  into a vector of book records.
     *
     *  A tutorial of how to use the Gutenberg data in bridges is presented here: https://bridgesuncc.github.io/tutorials/Data_Gutenberg.html
     *  
     *  @param term  a string that matches the category 
     *  @param category  category can be any book attribute (title, genre, 
     *                  date, Library of Congress class, language)
     */

	public List<GutenbergMeta> getGutenbergBookMetaData(String term, String category) 
														throws IOException{
		String url = getGutenbergBaseURL() + "/search?search=" + 
			URLEncoder.encode(term, StandardCharsets.UTF_8.name()) + "&type=" + category;
		String data = requestJSON(url);

		JSONParser parser = new JSONParser();
		List<GutenbergMeta> bookList = new ArrayList<GutenbergMeta>();

		try	 {
			JSONObject json = (JSONObject) parser.parse(data);
			JSONArray books = (JSONArray) json.get("book_list");
			Iterator<JSONObject> iter = books.iterator();
		
			while(iter.hasNext()){
				JSONObject book = iter.next();

				Gson gson = new Gson();
				GutenbergMeta bookData = gson.fromJson(book.toString(), GutenbergMeta.class);
				bookList.add(bookData);
			}
			return bookList;

		} 
		catch (Exception e) {
			System.out.println("Error parsing the returned JSON"); 
		}

		return bookList;
	}


	/**
	* This function is to retrieve a gutenberg book metadata from a book ID
	*
	* A tutorial of how to use the Gutenberg data in BRIDGES is available: https://bridgesuncc.github.io/tutorials/Data_Gutenberg.html
	*
	* @param id is the id of the book you want to retrieve
	*
	* @return a GutenbergMeta object of the book with the ID passed in
	*/
	public GutenbergMeta getAGutenbergBookMetaData(int id) throws IOException {
		String url = getGutenbergBaseURL() + "/meta?id=" + id;
		String data = requestJSON(url);


		JSONParser parser = new JSONParser();
		GutenbergMeta bookData = null;

		try{
			JSONObject json = (JSONObject) parser.parse(data);
			JSONArray books = (JSONArray) json.get("book_list");
			Iterator<JSONObject> iter = books.iterator();
		
			while(iter.hasNext()){
				JSONObject book = iter.next();

				Gson gson = new Gson();
				bookData = gson.fromJson(book.toString(), GutenbergMeta.class);
				return bookData;
			}
			

		} catch (Exception e) {
			System.out.println("Error parsing the returned JSON"); 
		}
		return bookData;
	}

	/**
	* This function gets the text of a gutenberg book based on the ID
	*
	* A tutorial of how to use the Gutenberg data in BRIDGES is available: https://bridgesuncc.github.io/tutorials/Data_Gutenberg.html
	*
	* @param id is the id of the book you want the text of
	*
	* @returns a string of the book's text
	*/
	public String getGutenbergBookText(int id) throws IOException{
		String url = getGutenbergBaseURL() + "/book?id=" + id;
		String data;
		String cacheID = "gutenberg"+id;
		if (lru.inCache(cacheID)){
			data = lru.getDoc(cacheID);
		} else {
			data = requestJSON(url);
			lru.putDoc(cacheID, data);
		}

		// need to parse the data
		JSONParser parser = new JSONParser();
		String book_text = new String();
		try{
			JSONObject json = (JSONObject) parser.parse(data);
			book_text = json.get(String.valueOf(id)).toString();
		} 
		catch (Exception e) {
			System.out.println("Error parsing the returned JSON"); 
		}

		return book_text;
	}


	/**
	 * @brief This function returns the Movie and Actors playing in them
	 *		between two years from WikiData
	 *
	 * @param yearBegin inclusive start year
	 * @param yearEnd inclusive end year
	 * @return ArrayList of all ActorMovie pairs in Wikidata between
	 *		the years provided
	 * @throws IOException If the response from WikiData is malformed or
	 *		an issue occurs making the request
	 */
	public ArrayList<ActorMovieWikidata> getWikidataActorMovie (int
		yearBegin, int yearEnd) throws IOException {
		ArrayList<ActorMovieWikidata> ret = new ArrayList<>();
		int limit = 30;
		if (yearEnd - yearBegin > 30)
			limit = (yearEnd - yearBegin) + 1;

		LRUCache cache = new LRUCache(limit);

		for (int i = yearBegin; i <= yearEnd; ++i) {
			populateWikidataActorMovie(i, i, ret, cache);
		}

		return ret;
	}

	/**
	 *	Helper function that will actually create and execute the
	 * 	request to WikiData, this allows us to
	 * 	partition the request to prevent WikiData from kicking out the client.
	 */
	private void populateWikidataActorMovie(int yearBegin, int yearEnd,
		ArrayList<ActorMovieWikidata> out, LRUCache cache) throws  IOException {

		String cacheName = String.format("wikidata-actormovie-%d-%d",
				yearBegin, yearEnd);
		String json = null;

		if (cache.inCache(cacheName)) {
			json = cache.getDoc(cacheName);
		}
		else {
			String url = "https://query.wikidata.org/sparql?";
			String query = "SELECT ?movie ?movieLabel ?actor ?actorLabel WHERE " +
				"{" +
				"?movie wdt:P31 wd:Q11424." +
				"?movie wdt:P161 ?actor." +
				"?movie wdt:P364 wd:Q1860." +
				"?movie wdt:P577 ?date."  +
				"FILTER(YEAR(?date) >= " + yearBegin + " && YEAR(?date) <= " + yearEnd + ")." +
				"SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". }" +
				"}";
			url += "query=" + URLEncoder.encode(query) + "&format=json";

			HttpGet req = new HttpGet(url);
			req.addHeader("User-Agent", "bridges-java");
			req.addHeader("Accept", "application/json");

			HttpClient client = HttpClientBuilder.create().build();
			HttpResponse resp;
			try {
				resp = client.execute(req);
				int status = resp.getStatusLine().getStatusCode();
				json = EntityUtils.toString(resp.getEntity());
				cache.putDoc(cacheName, json);
			}
			catch (Exception e) {}
		}

		if (json != null) {
			try {
				JSONObject data = (JSONObject) JSONValue.parse(json);
				JSONArray arr = (JSONArray) ((JSONObject) data.get("results")).get("bindings");

				for (Object item : arr) {
					JSONObject obj = (JSONObject) item;
					String actorUri, movieUri, actor, movie;

					actorUri = (String) ((JSONObject) obj.get("actor")).get("value");
					movieUri = (String) ((JSONObject) obj.get("movie")).get("value");
					actorUri = actorUri.replaceFirst("http://www.wikidata.org/entity/", "");
					movieUri = movieUri.replaceFirst("http://www.wikidata.org/entity/", "");

					actor = (String) ((JSONObject) obj.get("actorLabel")).get("value");
					movie = (String) ((JSONObject) obj.get("movieLabel")).get("value");

					ActorMovieWikidata add = new ActorMovieWikidata(movieUri, actorUri, movie, actor);

					out.add(add);
				}
			}
			catch (Exception e) {
				throw new IOException("Malformed JSON: Not from wikidata?");
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////
	//  Gettin Reddit data
	/////////////////////////////////////////////////////////////////////////
	public Vector<Reddit> getRedditData (String subreddit, int time_request) {
		String base_url = getRedditURL();
		System.out.println("reddit base url:" +  base_url);
		String url = base_url + "/cache?subreddit=" + subreddit +
			"&time_request=" + String.valueOf(time_request);

		System.out.println("reddit url:" +  url);
//      content = server_request(url)
//      data = json.loads(content.decode("utf-8"))

		Vector<Reddit> reddit_posts = new Vector<Reddit>();
		return reddit_posts;
	}
	/////////////////////////////////////////////////////////////////////////
	// The following functions are provided to import store assignments on
	// the BRIDGES server. Currently supported: ColorGrid import
	/////////////////////////////////////////////////////////////////////////
	/**
	 * This function obtains the JSON representation of a particular
	 *	 subassignment.
	 *
	 * @return a string that is the JSON representation of the subassignment
							as stored by the Bridges server.
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 * @param subassignment the ID of the subassignment to get
	 */
	public String getAssignmentJSON(String user, int assignment,
		int subassignment) throws IOException {
		return getAssignment(bridges.getServerURL(), user, assignment,
				subassignment);
	}
	/**
	 * This function obtains the JSON representation of a particular subassignment.
	 *
	 * @return a string that is the JSON representation of the subassignment as stored by the Bridges server.
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 */
	public String getAssignmentJSON(String user, int assignment)
	throws IOException {
		return getAssignmentJSON(user, assignment, 0);
	}
	/** Reconstruct a ColorGrid from an existing ColorGrid on the Bridges server
	 *
	 * @return the ColorGrid stored in the bridges server
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 * @param subassignment the ID of the subassignment to get
	 */
	public ColorGrid getColorGridFromAssignment(String user, int assignment,
		int subassignment) throws IOException {
		return getColorGridFromAssignment(bridges.getServerURL(), user,
				assignment, subassignment);
	}

	/**
	 * Reconstruct a ColorGrid from an existing ColorGrid on the Bridges server
	 *
	 * @return the ColorGrid stored in the bridges server
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 **/
	public ColorGrid getColorGridFromAssignment(String user, int assignment)
	throws IOException {
		return getColorGridFromAssignment(bridges.getServerURL(), user,
				assignment, 0);
	}


	/**
	 *	Due to the nature of how Java handles numbers, Bytes range from -128 to
	 *	127, which is problematic for Bridges Color objects which store
	 *	RGB as 0-255. This function translates 4 bytes into a Bridges
	 *	Color object, taking these factors into account.
	 *
	 * 	This function may be useful to add directly to the Color class, but
	 *	it may only be needed in this case, where we need to convert an array
	 *	of bytes into a ColorGrid.
	 */
	private Color getColorFromSignedBytes(byte r, byte g, byte b, byte a) {
		int R, G, B, A;
		R = r < 0 ? 256 + r : r;
		G = g < 0 ? 256 + g : g;
		B = b < 0 ? 256 + b : b;
		A = a < 0 ? 256 + a : a;

		float alpha = A / 255.0f;
		return new Color(R, G, B, alpha);
	}

	/**
	 * @brief Reconstruct a ColorGrid from an existing ColorGrid on the
	 *	Bridges server
	 *
	 * @param server base URL of the Bridges service
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 * @param subassignment the ID of the subassignment to get
	 * @return the ColorGrid stored in the bridges server
	 */
	public ColorGrid getColorGridFromAssignment(String server, String
		user, int assignment, int subassignment) throws IOException {

		String response = getAssignment(server, user, assignment, subassignment);

		Gson gson = new Gson();
		Assignment assignmentObject;
		try {
			// parse JSON into a BridgesAssignment object
			assignmentObject = gson.fromJson(response, Assignment.class);
		}
		catch (Exception e) {
			throw new JsonParseException("Malformed JSON: Unable to Parse");
		}
		if (!assignmentObject.assignment_type.equals("ColorGrid"))
			throw new RuntimeException("Malformed ColorGrid JSON: not a ColorGrid");

		if (assignmentObject.data.length != 1)
			throw new RuntimeException("Malformed JSON: data is malformed");

		AssignmentData data = assignmentObject.data[0];

		String encoding = data.encoding;
		// handle ColorGrids posted before encoding field was added
		if (encoding == null)
			encoding = "RAW";

		if (!encoding.equals("RLE") && !encoding.equals("RAW"))
			throw new RuntimeException("Malformed ColorGrid JSON: encoding not supported :" + encoding);

		int[] dims = data.dimensions;
		if (dims.length != 2)
			throw new RuntimeException("Malformed ColorGrid JSON: dimensions are malformed");
		int dimx = dims[0];
		int dimy = dims[1];

		if (data.nodes.length != 1)
			throw new RuntimeException("Malformed ColorGrid JSON: nodes are malformed");
		Object node = data.nodes[0];
		String nodeString;
		if (node instanceof String)
			nodeString = (String) node;
		else
			throw new RuntimeException("Malformed ColorGrid JSON: node is not a String");

		byte[] decoded = Base64.decodeBase64(nodeString);
		ColorGrid cg = new ColorGrid(dimx, dimy);

		if (encoding.equals("RAW")) {
			if (decoded.length < dimx * dimy * 4)
				throw new RuntimeException("Malformed ColorGrid JSON: nodes is smaller than expected");

			int base = 0;

			for (int x = 0; x < dimx ; ++x) {
				for (int y = 0; y < dimy; ++y) {
					Color c = getColorFromSignedBytes(decoded[base],
							decoded[base + 1],
							decoded[base + 2],
							decoded[base + 3]
						);

					cg.set(x, y, c);
					base += 4;
				}
			}

		}
		else if (encoding.equals("RLE")) {
			int currentInDecoded = 0;
			int currentInCG = 0;
			while (currentInDecoded != decoded.length) {
				if (decoded.length % 5 != 0)
					throw new RuntimeException("Malformed ColorGrid JSON: nodes is not a multiple of 5");

				int repeat = decoded[currentInDecoded++];
				if (repeat < 0)
					repeat = 256 + repeat;

				Color c = getColorFromSignedBytes(decoded[currentInDecoded++],
						decoded[currentInDecoded++],
						decoded[currentInDecoded++],
						decoded[currentInDecoded++]);

				while (repeat >= 0) {
					int posX = currentInCG / dimy;
					int posY = currentInCG % dimy;
					if (posX >= dimx || posY >= dimy)
						throw new RuntimeException("Malformed ColorGrid JSON: Too much data in nodes");

					cg.set(posX, posY, c);
					currentInCG++;
					repeat--;
				}
			}

			if (currentInCG != dimx * dimy)
				throw new RuntimeException("Malformed ColorGrid JSON: Not enough data in nodes");
		}

		return cg;
	}

	/**
	 * @brief Reconstruct a ColorGrid from an existing ColorGrid on the
	 *	Bridges server
	 *
	 * @param server base URL of the Bridges service
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 * @return the ColorGrid stored in the bridges server
	 */
	public bridges.base.ColorGrid getColorGridFromAssignment(
		String server, String user, int assignment) throws IOException {

		return getColorGridFromAssignment(server, user, assignment, 0);
	}

	/***
	 * This function obtains the JSON representation of a particular
	 *	subassignment.
	 *
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 * @param subassignment the ID of the subassignment to get
	 *
	 * @return a string that is the JSON representation of the subassignment
	 *		as stored by the Bridges server.
	 */
	public String getAssignment(String server, String user,
		int assignment, int subassignment) throws IOException {

		String leadingZero = subassignment > 10 ? "" : "0";
		String url = String.format("%s/assignmentJSON/%d.%s%d/%s", server,
				assignment, leadingZero, subassignment, user);
		HttpResponse resp = makeRequest(url);
		int status = resp.getStatusLine().getStatusCode();
		String result = EntityUtils.toString(resp.getEntity());
		if (status == 200) 	{
			return result;
		}
		else {
			throw new HttpResponseException(status, "HTTP Request Failed. Error Code: " + status + ". Message: " + result);
		}
	}
	/***
	 * @brief This function obtains the JSON representation of the first
	 *		subassignment of an assignment.
	 *
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 *
	 * @return a string that is the JSON representation of the subassignment
	 *		as stored by the Bridges server.
	 */
	public String getAssignment(String server, String user, int assignment)
	throws IOException, Exception {
		return getAssignment(server, user, assignment, 0);
	}

}; // end class
