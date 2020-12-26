package bridges.connect;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

import bridges.base.*;
import bridges.data_src_dependent.*;
import bridges.validation.*;

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
		int maxElements) throws Exception {
		return DataFormatter.getEarthquakeUSGSData(maxElements);
	}

	/**
	 *  This helper function provides access to a small curated IMDB dataset. The data is
	 *  retrieved data is formatted into a list of ActorMovieIMDB objects (about 1800 pairs).
	 *
	 *  This curated data set has only actor and movie name pairs. refer to <p>
	 *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://bridgesuncc.github.io/datasets.html <p>
	 *  for more information and to look at the dataset.
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of ActorMovieIMDB objects, but only actor,  movie, movie genre
	 *			and movie rating are returned.
	 */
	public  List<ActorMovieIMDB> getActorMovieIMDBData() throws Exception {
		return DataFormatter.getActorMovieIMDBData(Integer.MAX_VALUE);
	}
	/**
	 *  This helper function provides access to a small curated IMDB dataset. The data is
	 *  retrieved data is formatted into a list of ActorMovieIMDB objects (about 1800 pairs).
	 *
	 *  This curated data set has only actor and movie name pairs. refer to <p>
	 *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://bridgesuncc.github.io/datasets.html <p>
	 *  for more information and to look at the dataset. This method lets you 
	 *  specify the number of actor/movie pairs to be retrieved.
	 *
	 *  @throws Exception if the request fails
	 *  @param numElements  number of earthquake records to be retrieved
	 *
	 *  @return a list of ActorMovieIMDB objects, but only actor,  movie, movie genre
	 *			and movie rating are returned.
	 */
	public  List<ActorMovieIMDB> getActorMovieIMDBData(int maxElements) throws Exception {
		return DataFormatter.getActorMovieIMDBData(maxElements);
	}
	/**
	 *  This helper function provides access to a second curated IMDB dataset; the data is
	 *  retrieved, formatted into a list of ActorMovieIMDB objects
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
	public  List<ActorMovieIMDB> getActorMovieIMDBData2() throws Exception {
		return DataFormatter.getActorMovieIMDBData2();
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
	public  List<GutenbergBook> getGutenbergBookMetaData() throws Exception {
		return DataFormatter.getGutenbergBookMetaData();
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
	public  List<Game> getGameData() throws Exception {
		return DataFormatter.getGameData();
	}

	/**
	 *  This helper function provides access to the meta-data of the lyrics
	 *	collection.
	 *
	 *  Each record in this collection has
	 *	information on song title, artist, album, year, lyrics, and genre. For more information
	 *	and to look at the data, refer to <p>
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://bridgesdata.herokuapp.com/api/datasets/songs <p>
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of Song objects.
	 */
	public  List<Song> getSongData() throws Exception {
		return DataFormatter.getSongData();
	}
	/**
	 *  These helper functions provides access to a particular song.
	 *
	 *  The record has information such as song title, artist, album, year, lyrics, and genre. For more information
	 *	and to look at the data, refer to <p>
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://bridgesdata.herokuapp.com/api/datasets/songs <p>
	 *
	 *  @throws Exception if the request fails
	 *  @param songTitle  title of song (string)
	 *
	 *  @return a Song object.
	 */
	public  Song getSong(String songTitle) throws Exception {
		return DataFormatter.getSong(songTitle, "");
	}
	/**
	 *  These helper functions provides access to a particular song.
	 *
	 *  The record has information such as song title, artist, album, year, lyrics, and genre. For more information
	 *	and to look at the data, refer to <p>
	 *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://bridgesdata.herokuapp.com/api/datasets/songs <p>
	 *
	 *  @throws Exception if the request fails
	 *  @param songTitle  title of song (string)
	 *  @param artistName  of song (string)
	 *
	 *  @return a Song object.
	 */
	public  Song getSong(String songTitle, String artistName) throws Exception {
		return DataFormatter.getSong(songTitle, artistName);
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
		return DataFormatter.getShakespeareData("", false);
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
		return DataFormatter.getShakespeareData(works, false);
	}
	/**
	 *  @brief This function provides access to a collection of Shakespeare plays,
	 * 	poems and plays.
	 *
	 *  Each record in this collection has
	 *	information on title, type (poem, Sonnet, play) and text.
	 *
	 *	For more information and to look at the data, refer to http://bridgesuncc.github.io/datasets.html
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @param works return only some of the works. "Plays" for only the plays, "Poems", or "" for all.
	 *
	 *  @param textOnly true to returns only words, no punctuation.
	 *
	 *  @return a list of Shakespeare objects.
	 */
	public  List<Shakespeare> getShakespeareData(String works, Boolean textOnly) throws Exception {
		return DataFormatter.getShakespeareData(works, textOnly);
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
	public ArrayList<CancerIncidence> getCancerIncidenceData() throws Exception {
		return DataFormatter.getCancerIncidenceData();
	}


	/**
	 * Generates Open Street Map URL request for a given location at general level of details and returns the map data
	 * @param location name of city or area that the server supports
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	public bridges.data_src_dependent.OsmData getOsmData(String location) throws IOException {
		return DataFormatter.getOsmData(location, "default");
	}
	/**
	* Generates Open Street Map URL request for a given location and returns the map data
	 * @param location name of city or area that the server supports
	 * @param level level of road detail on requested map
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	public bridges.data_src_dependent.OsmData getOsmData(String location, String level) throws IOException {
		return DataFormatter.getOsmData(location, level);
	}

	/**
	 * Generates Open Street Map URL request for a given location and returns the map data
	 *
	 * This is a legacy function that only supports few cities:
	 * 	  "uncc_campus", "charlotte", "washington_dc",
	 * 	 "saint_paul", "new_york", "los_angeles",
	 * 	 "san_francisco", "miami", "minneapolis", "dallas"
	 *
	 * @param location name of city or area that the server supports
	 * @param level level of road detail on requested map
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	private bridges.data_src_dependent.OsmData getOsmDataOld(String location, String level) throws IOException {
		return DataFormatter.getOsmData(location, level);
	}


	/**
	 * Generates Open Street Map URL request for a given set of coordinates and returns the map data
	 * @param minLat minimum latitude value for the area requested
	 * @param minLon minimum longitude value for the area requested
	 * @param maxLat maximum latitude value for the area requested
	 * @param maxLon maximum longitude value for the area requested
	 * @param level level of road detail on requested map
	 * @return OsmData vertices and edges of Open Street Map data
	 * @throws IOException If there is an error parsing response from server or is an invalid location name
	 */
	public static bridges.data_src_dependent.OsmData getOsmData(double minLat, double minLon, double maxLat, double maxLon, String level) throws IOException {
		return DataFormatter.getOsmData(minLat, minLon, maxLat, maxLon, level);
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
	public static OsmData getOsmData(double minLat, double minLon, double maxLat, double maxLon) throws IOException {
		return getOsmData(minLat, minLon, maxLat, maxLon, "default");
	}

	/**
	 * Returns elevation data for the provided coordinate box at the
	 * given resolution. Note that the ElevationData that is returned
	 * may have slightly different location and resolution.
	 *
	 * @param minLat minimum latitude requested
	 * @param minLon minimum longitude requested
	 * @param maxLat maximum latitude requested
	 * @param maxLat maximum longitude requested
	 * @param res spatial resolution, aka the distance between two samples (in degrees)
	 *
	 * @return a ElevationData object mapping a region close to the box requested
	 **/
	public static bridges.data_src_dependent.ElevationData getElevationData(double minLat, double minLon, double maxLat, double maxLon, double res) throws IOException {
		return DataFormatter.getElevationData(minLat, minLon, maxLat, maxLon, res);
	}


	/**
	 * Returns elevation data for the provided coordinate box. Note
	 * that the ElevationData that is returned may have slightly
	 * different location.
	 *
	 * @param minLat minimum latitude requested
	 * @param minLon minimum longitude requested
	 * @param maxLat maximum latitude requested
	 * @param maxLat maximum longitude requested
	 **/
	public static ElevationData getElevationData(double minLat, double minLon, double maxLat, double maxLon) throws IOException {
		return getElevationData(minLat, minLon, maxLat, maxLon, .01666);
	}


	/**
	 * Returns amenity data for the provided coordinate box, with a specified
	 * amenity.
	 *
	 * @param minLat minimum latitude requested
	 * @param minLon minimum longitude requested
	 * @param maxLat maximum latitude requested
	 * @param maxLat maximum longitude requested
	 * @param amenity amenity type (string)
	 **/
	public static bridges.data_src_dependent.AmenityData getAmenityData(double minLat, double minLon, double maxLat, double maxLon, String amenity) throws IOException {
		return DataFormatter.getAmenityData(minLat, minLon, maxLat, maxLon, amenity);
	}

	/**
	 * Returns amenity data for the provided coordinate box, with a specified
	 * amenity.
	 *
	 * @param location name of city/town for whose amenity data is being retrieved
	 * @param amenity amenity type (string)
	 **/
	public static bridges.data_src_dependent.AmenityData getAmenityData(String location, String amenity) throws IOException {
		return DataFormatter.getAmenityData(location, amenity);
	}

	/**
	 * This function obtains the JSON representation of a particular subassignment.
	 *
	 * @return a string that is the JSON representation of the subassignment as stored by the Bridges server.
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 * @param subassignment the ID of the subassignment to get
	 */
	public String getAssignmentJSON(String user, int assignment, int subassignment) throws IOException {
		return DataFormatter.getAssignment(bridges.getServerURL(), user, assignment, subassignment);
	}
	/**
	 * This function obtains the JSON representation of a particular subassignment.
	 *
	 * @return a string that is the JSON representation of the subassignment as stored by the Bridges server.
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 */
	public String getAssignmentJSON(String user, int assignment) throws IOException {
		return getAssignmentJSON(user, assignment, 0);
	}
	/** Reconstruct a ColorGrid from an existing ColorGrid on the Bridges server
	 *
	 * @return the ColorGrid stored in the bridges server
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 * @param subassignment the ID of the subassignment to get
	 */
	public bridges.base.ColorGrid getColorGridFromAssignment(String user, int assignment, int subassignment)
	throws IOException {
		return DataFormatter.getColorGridFromAssignment(bridges.getServerURL(), user, assignment, subassignment);
	}

	/**
	 * Reconstruct a ColorGrid from an existing ColorGrid on the Bridges server
	 *
	 * @return the ColorGrid stored in the bridges server
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 **/
	public bridges.base.ColorGrid getColorGridFromAssignment(String user, int assignment) throws IOException {
		return getColorGridFromAssignment(user, assignment, 0);
	}

	/**
	 * @brief This function returns the Movie and Actors playing in them between two years from WikiData
	 *
	 * @param yearBegin inclusive start year
	 * @param yearEnd inclusive end year
	 * @return ArrayList of all ActorMovie pairs in Wikidata between the years provided
	 * @throws IOException If the response from WikiData is malformed or an issue occurs making the request
	 */
	public static ArrayList<ActorMovieWikidata> getWikidataActorMovie(int yearBegin, int yearEnd) throws IOException {
		return DataFormatter.getWikidataActorMovie(yearBegin, yearEnd);
	}

}
