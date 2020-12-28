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
import java.net.URLEncoder;


// JSON related
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
 *	actor/movies, Wikidata, Guttenberg book collections, OpenStreet maps, 
 *  Shakespeare, play, sonnets, poems,  Cancer incidence data and IGN games
 *  Each dataset is accessed through a function call, which typically returns
 * 	a list of objects with all associated attributes. These can then be used
 *	as part of a data structure, algorithm. Some initial support to import
 *  previously built data structures is also provided.
 */
public class DataSource {
	private Bridges bridges;

	DataSource() {
	}

	DataSource(Bridges b) {
		bridges = b;
	}

	/**
	 *  This helper function provides a simple API to retrieve current USGS earthquake
	 *	Tweet data from the USGS website (https://earthquake.usgs.gov/earthquakes/map/);
	 *  The data is retrieved and formatted into a list of EarthquakeUSGS objects.
	 *
	 *  More information on the dataset can be found at http://bridgesuncc.github.io/datasets.html
	 *
	 *	@param maxElements  the number of earthquake records retrieved, limited to 5000
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
	 *	For more information and to look at the data, refer to http://bridgesuncc.github.io/datasets.html
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
	 *	For more information and to look at the data, refer to http://bridgesuncc.github.io/datasets.html
	 *
	 * This function returns only some of the works, either the plays, or the poems, depending on the parameters.
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @param works return only some of the works. "Plays" for only the plays, "Poems", or "" for all.
	 *
	 *  @return a list of Shakespeare objects.
	 */
	public  List<Shakespeare> getShakespeareData(String works) throws Exception {
		String url = "https://bridgesdata.herokuapp.com/api/shakespeare";

		Boolean textOnly = false;

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
	 *  This helper function provides access to a cancer dataset from CDC
	 *  https://www.cdc.gov/cancer/npcr/uscs/download_data.htm and curated
	 *  by Austin (Cory) Bart as part of the Corgis data collection
	 *	https://think.cs.vt.edu/corgis/
	 *
	 *  Each record in this collection has a number of fields detailed in the
	 *	CancerIncidence class.
	 *
	 *	For more information and to look at the data, refer to <p>
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://bridgesuncc.github.io/datasets.html <p>
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
	 * @param location name of city or area that the server supports
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	public static OsmData getOsmData(String location) throws IOException {
		return getOsmDataDS(location, "default");
	}
	/**
	* Generates Open Street Map URL request for a given location and returns the map data
	 * @param location name of city or area that the server supports
	 * @param level level of road detail on requested map
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	public static OsmData getOsmData(String location, String level) throws 
												IOException {
		return getOsmDataDS(location, level);
	}

	/**
	 * Generates Open Street Map URL request for a given set of coordinates with default level and returns the map data
	 * @param minLat minimum latitude value for the area requested
	 * @param minLon minimum longitude value for the area requested
	 * @param maxLat maximum latitude value for the area requested
	 * @param maxLon maximum longitude value for the area requested
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	public static OsmData getOsmData(double minLat, double minLon, double maxLat,
					 double maxLon) throws IOException {
		return getOsmDataDS(minLat, minLon, maxLat, maxLon, "default");
	}
	/**
	 * Generates Open Street Map URL request for a given set of coordinates with default level and returns the map data
	 * @param minLat minimum latitude value for the area requested
	 * @param minLon minimum longitude value for the area requested
	 * @param maxLat maximum latitude value for the area requested
	 * @param maxLon maximum longitude value for the area requested
	 * @paral level resolution at which the data is to be retrieved
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	public static OsmData getOsmData(double minLat, double minLon, double maxLat, 
					double maxLon, String level) throws IOException {
		return getOsmDataDS(minLat, minLon, maxLat, maxLon, level);
	}


	private static HttpResponse makeRequest(String url) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(UrlEscapers.urlFragmentEscaper().escape(url));
		return client.execute(request);
	}

	private static JSONArray unwrapJSONArray(HttpResponse response, String get) throws IOException {
		String result = EntityUtils.toString(response.getEntity());
		JSONObject full = (JSONObject)JSONValue.parse(result);
		return (JSONArray)full.get(get);
	}

	private static JSONArray unwrapJSONArray(HttpResponse response) throws IOException {
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
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://bridgesuncc.github.io/datasets.html <p>
	 *  for more information and to look at the dataset.
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
	 *  This helper function provides access to the meta-data of the Gutenberg book
	 *	collection (about 1000 books); the data is retrieved, formatted into a
	 *	list of GutenbergBook objects.
	 *
	 *  Each book in this collection has  for each record,
	 *	information on author (name, birth, death), title, languages, genres,
	 *  subjects, metrics(number of chars, words, sentences, difficult words), url
	 *	downloads. More information and commands to access the data can be found at <p>
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://bridgesuncc.github.io/datasets.html <br>
	 *  for more information and to look at the dataset.
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of GutenbergBook objects
	 */
	public List<GutenbergBook> getGutenbergBookMetaData ()
								throws IOException {

		String url = "https://bridgesdata.herokuapp.com/api/books";
		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();

		if (status == 200) 	{
			JSONArray json = unwrapJSONArray(response);

			List<GutenbergBook> gb_list =
				new ArrayList<GutenbergBook>(json.size());
			for (int i = 0; i < json.size(); i++) {
				JSONObject item = (JSONObject)json.get(i);
				JSONObject author = (JSONObject)item.get("author");
				JSONObject metrics = (JSONObject)item.get("metrics");
				JSONArray lang = (JSONArray) item.get("languages");
				JSONArray genres = (JSONArray)item.get("genres");
				JSONArray subjects = (JSONArray)item.get("subjects");
				Vector<String> gb_tmp = new Vector<String>(100);;

				GutenbergBook gb = new GutenbergBook();

				gb.setAuthorName ((String) author.get("name"));
				gb.setAuthorBirth(((Number) (author.get("birth"))).intValue());
				gb.setAuthorDeath(((Number) (author.get("death"))).intValue());
				gb.setTitle((String) item.get("title"));
				gb.setURL((String) item.get("url"));
				gb.setNumDownloads(((Number) item.get("downloads")).intValue());
				for (int k = 0; k < lang.size(); k++) {
					gb_tmp.add((String)lang.get(k));
				}
				gb.setLanguages(gb_tmp);
				gb_tmp.clear();

				gb.setNumChars(((Number) (metrics.get("characters"))).intValue());
				gb.setNumWords(((Number) (metrics.get("words"))).intValue());
				gb.setNumSentences(((Number) (metrics.get("sentences"))).intValue());
				gb.setNumDifficultWords(((Number) (metrics.get("difficultWords"))).intValue());
				for (int k = 0; k < genres.size(); k++)
					gb_tmp.add((String)genres.get(k));
				gb.setGenres(gb_tmp);
				gb_tmp.clear();
				for (int k = 0; k < subjects.size(); k++)
					gb_tmp.add((String)subjects.get(k));
				gb.setSubjects(gb_tmp);
				gb_tmp.clear();

				gb_list.add(gb);
			}
			return gb_list;
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
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://bridgesuncc.github.io/datasets.html <p>
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
	private static Song parseSong(JSONObject songJSON) {
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
	 *  @throws Exception if the request fails
	 *  @param songTitle  title of song (string)
	 *
	 *  @return a Song object.
	 */
	public Song getSong(String songTitle, String artistName)
								throws IOException {
		String url = "https://bridgesdata.herokuapp.com/api/songs/find/";

		// add the song title to the query url
		if (songTitle.length() > 0) {
			url += songTitle;
		}
		else {
			throw new IllegalArgumentException("Must provide a valid song title.");
		}
		// add the artist name as a query variable where appropriate
		if (artistName.length() > 0) {
			url += "?artistName=" + artistName;
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
	 * @brief Generates Open Street Map URL request for a given location and returns the map data
	 *
	 * @param location  name of city or area that the server supports
	 * @param level  level of road detail on requested map
	 * @return OsmData  vertices and edges of Open Street Map data
	 * @throws IOException  If there is an error parsing response from server or is an invalid location name
	 */
	private static OsmData getOsmDataDS(String location, String level) 
								throws IOException {
		String url = "http://cci-bridges-osm.uncc.edu/loc?location=" + location + "&level=" + level;
		String hashUrl = "http://cci-bridges-osm.uncc.edu/hash?location=" + location + "&level=" + level;
		return (downloadMapFile(url, hashUrl));
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
	private static OsmData getOsmDataDS(double minLat, double minLon, 
		double maxLat, double maxLon, String level) throws IOException {

		String url = "http://cci-bridges-osm.uncc.edu/coords?minLon=" + Double.toString(minLon) + "&minLat=" + Double.toString(minLat) + "&maxLon=" + Double.toString(maxLon) + "&maxLat=" + Double.toString(maxLat) + "&level=" + level;
		String hashUrl = "http://cci-bridges-osm.uncc.edu/hash?minLon=" + Double.toString(minLon) + "&minLat=" + Double.toString(minLat) + "&maxLon=" + Double.toString(maxLon) + "&maxLat=" + Double.toString(maxLat) + "&level=" + level;
		return (downloadMapFile(url, hashUrl));
	}

	/**
	 * @brief Downloads and caches maps requested
	 *
	 * @param url, string of the url that will be used when requesting 
	 *		map data from server
	 * @param hashurl string of the url that will be used when requesting 
	 *		hash data from serverp
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from 
	 *		server or is an invalid location name
	 */
	private static OsmData downloadMapFile(String url, String hashUrl) 
									throws IOException {
		File cache_dir = new File("./cache");
		LRUCache lru = new LRUCache(30);

		// look for file in cache
		String content = null;
		String hash = null;
		HttpResponse hashResp = makeRequest(hashUrl);
		int hashStatus = hashResp.getStatusLine().getStatusCode();
		hash = EntityUtils.toString(hashResp.getEntity());
		if (!hash.equals("false") && hashStatus == 200 && lru.inCache(hash) == true) {
			content = lru.getDoc(hash);
		}

		// if not in cache, hit server for data
		if (content == null) {
			HttpResponse resp = makeRequest(url);
			int status = resp.getStatusLine().getStatusCode();
			content = EntityUtils.toString(resp.getEntity());
			if (status != 200) {
				if (status == 404) {
					// attempt to get valid names
					url = "http://cci-bridges-osm.dyn.uncc.edu/cities";
					resp = makeRequest(url);
					status = resp.getStatusLine().getStatusCode();
					if (status == 200) {
						content = EntityUtils.toString(resp.getEntity());
						throw new RuntimeException("Invalid name" + "\nValid names:" + content);
					}
				}
				throw new HttpResponseException(status, "Http Request Failed. Error Code:" + status + ". Message:" + content);
			}
			hashResp = makeRequest(hashUrl);
			hash = EntityUtils.toString(hashResp.getEntity());

			//Checks to see if valid hash is generated
			if (!hash.equals("false")) {
				lru.putDoc(hash, content);
			}
		}

		// parse that data
		Gson gson = new Gson();
		OsmServerResponse respObject;
		try {
			respObject = gson.fromJson(content, OsmServerResponse.class);
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
	 *	@param location city/town from where amenity data is sought
	 *	@param amenity  amenity type
	 *	@throws exception
	 */
	public AmenityData getAmenityData(String location, String amenity) 
									throws IOException {
		
		String url = "http://cci-bridges-osm.uncc.edu/amenity?location=" + location + "&amenity=" + amenity;
		String hashUrl = "http://cci-bridges-osm.uncc.edu/hash?location=" + location + "&amenity=" + amenity;
		return (downloadAmenityData(url, hashUrl));
	}
	
	/** 
	 * This method retrieves the specified amenity related data given a 
	 * bounding box of a region, from a Open Street map 
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

		String url = "http://cci-bridges-osm.uncc.edu/amenity?minLon=" + Double.toString(minLon) + "&minLat=" + Double.toString(minLat) + "&maxLon=" + Double.toString(maxLon) + "&maxLat=" + Double.toString(maxLat) + "&amenity=" + amenity;
		String hashUrl = "http://cci-bridges-osm.uncc.edu/hash?minLon=" + Double.toString(minLon) + "&minLat=" + Double.toString(minLat) + "&maxLon=" + Double.toString(maxLon) + "&maxLat=" + Double.toString(maxLat) + "&amenity=" + amenity;
		return (downloadAmenityData(url, hashUrl));
	}

	private AmenityData downloadAmenityData(String url, String hashUrl) 
											throws IOException{
		LRUCache lru = new LRUCache(30);

		// look for file in cache
		String content = null;
		String hash = null;
		HttpResponse hashResp = makeRequest(hashUrl);
		int hashStatus = hashResp.getStatusLine().getStatusCode();
		hash = EntityUtils.toString(hashResp.getEntity());
		if (!hash.equals("false") && hashStatus == 200 && lru.inCache(hash) == true) {
			content = lru.getDoc(hash);
		}

		// if not in cache, hit server for data
		if (content == null) {
			HttpResponse resp = makeRequest(url);
			int status = resp.getStatusLine().getStatusCode();
			content = EntityUtils.toString(resp.getEntity());
			if (status != 200) {
				if (status == 404) {
					// attempt to get valid names
					url = "http://cci-bridges-osm.dyn.uncc.edu/cities";
					resp = makeRequest(url);
					status = resp.getStatusLine().getStatusCode();
					if (status == 200) {
						content = EntityUtils.toString(resp.getEntity());
						throw new RuntimeException("Invalid name" + "\nValid names:" + content);
					}
				}
				throw new HttpResponseException(status, "Http Request Failed. Error Code:" + status + ". Message:" + content);
			}
			hashResp = makeRequest(hashUrl);
			hash = EntityUtils.toString(hashResp.getEntity());

			//Checks to see if valid hash is generated
			if (!hash.equals("false")) {
				lru.putDoc(hash, content);
			}
		}

		// Parse Data into object
		AmenityData temp = new AmenityData();
		JSONParser parser = new JSONParser();

		try {
			JSONObject json = (JSONObject) parser.parse(content);
			JSONArray nodes = (JSONArray) json.get("nodes");
			JSONObject meta = (JSONObject) json.get("meta");


			temp.setCount(Integer.parseInt(meta.get("count").toString()));
			temp.setMinLat(Double.parseDouble(meta.get("minlat").toString()));
			temp.setMinLon(Double.parseDouble(meta.get("minlon").toString()));
			temp.setMaxLat(Double.parseDouble(meta.get("maxlat").toString()));
			temp.setMaxLon(Double.parseDouble(meta.get("maxlon").toString()));
			

			Iterator<JSONArray> iter = nodes.iterator();
			while(iter.hasNext()){
				JSONArray sub_data = iter.next();
				Amenities amen = new Amenities();
				amen.setId(Double.parseDouble(sub_data.get(0).toString()));
				amen.setLat(Double.parseDouble(sub_data.get(1).toString()));
				amen.setLon(Double.parseDouble(sub_data.get(2).toString()));
				amen.setName(sub_data.get(3).toString());

				temp.addAmenities(amen);
			}
		}catch(Exception e) {
			System.out.println("Error Parsing Amenity Json: " + e);
		}
		
		return temp;
	}
	/**
	 *
	 *	@param minLat  minimum latitude
	 *	@param minLon  minimumm longitude
	 *	@param maxLat  maximum latitude
	 *	@param maxLon  maximum longitude
	 *	@param res     resolution
	 *
	 */

	/**
	 * This method retrieves the elevation map of a region given the lat/long
	 * range (bounding box) and resolution level
	 * Note that the ElevationData that is returned
	 * may have slightly different location and resolution.
	 *
	 * @param minLat minimum latitude requested
	 * @param minLon minimum longitude requested
	 * @param maxLat maximum latitude requested
	 * @param maxLat maximum longitude requested
	 * @param res spatial resolution, aka the distance between two samples (in degrees)
	 * @return a ElevationData object mapping a region close to the box requested
	 */
	public ElevationData getElevationData(double minLat, double minLon, 
			double maxLat, double maxLon, double res) throws IOException {

		String data_url = "http://cci-bridges-elevation-t.dyn.uncc.edu/elevation?minLon=" + Double.toString(minLon) + "&minLat=" + Double.toString(minLat) + "&maxLon=" + Double.toString(maxLon) + "&maxLat=" + Double.toString(maxLat) + "&resX=" + res + "&resY=" + res;
		String hash_url = "http://cci-bridges-elevation-t.dyn.uncc.edu/hash?minLon=" + Double.toString(minLon) + "&minLat=" + Double.toString(minLat) + "&maxLon=" + Double.toString(maxLon) + "&maxLat=" + Double.toString(maxLat);

		// store in cache or retrieve from cache if available
		File cache_dir = new File("./cache/elevationData");
		LRUCache lru = new LRUCache(30);

		// look for file in cache
		String content = null;
		String hash = null;
		HttpResponse hashResp = makeRequest(hash_url);
		int hashStatus = hashResp.getStatusLine().getStatusCode();
		hash = EntityUtils.toString(hashResp.getEntity());
		if (!hash.equals("false") && hashStatus == 200 && lru.inCache(hash) == true) {
			content = lru.getDoc(hash);
		}

		// if not in cache, hit server for data
		if (content == null) {
			HttpResponse resp = makeRequest(data_url);
			int status = resp.getStatusLine().getStatusCode();
			content = EntityUtils.toString(resp.getEntity());
			if (status != 200) {
				throw new HttpResponseException(status, "Http Request Failed. Error Code:" + status + ". Message:" + content);
			}
			hashResp = makeRequest(hash_url);
			hash = EntityUtils.toString(hashResp.getEntity());

			//Checks to see if valid hash is generated
			if (!hash.equals("false")) {
				lru.putDoc(hash, content);
			}
		}

		//Parse Data into object
		int cols = 0;
		int rows = 0;
		double xll = 0;
		double yll = 0;
		double cellsize = 0;
		int maxVal = -9999999;

		String [] lines = content.split("\n");
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
			}
		}

		ElevationData ret_data = new ElevationData(data, cols, rows, xll, yll, cellsize, maxVal);
		return ret_data;
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
	public static ArrayList<ActorMovieWikidata> getWikidataActorMovie (int 
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
	private static void populateWikidataActorMovie(int yearBegin, int yearEnd, 
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
	private static Color getColorFromSignedBytes(byte r, byte g, byte b, byte a) {
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
	public static ColorGrid getColorGridFromAssignment(String server, String 
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
	public static bridges.base.ColorGrid getColorGridFromAssignment(
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
	public static String getAssignment(String server, String user, 
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
	public static String getAssignment(String server, String user, int assignment)
							throws IOException, Exception {
		return getAssignment(server, user, assignment, 0);
	}

}; // end class
