package bridges.connect;


import java.io.File;
import java.io.IOException;
import java.lang.Exception;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import com.google.gson.JsonParseException;
import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import bridges.base.*;
import bridges.validation.*;
import bridges.data_src_dependent.*;

import com.google.gson.Gson;
import com.google.common.net.UrlEscapers;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.HttpResponseException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.commons.codec.binary.Base64;
//import com.google.code.gson.Gson;





/**
 * Connection to the DataFormatters server.
 *
 * Initialize this class before using it, and call complete() afterward.
 *
 * @author Sean Gallagher, Mihai Mehedint
 */
public class DataFormatter {


	private static List<Tweet> allTweets = new ArrayList<>();// this is the list off all the tweets retrieved
	private static List<EarthquakeUSGS> allUSGS = new ArrayList<>();// this is the list off all the earthquakes retrieved

	private static int maxRequests = 500; //This is the max number of tweets one can retrieve from the server

	private static int tweetIterator = -1; //this iterator is used when requesting a new batch of tweets
	private static boolean failsafe = false;
	private static Connector backend;

	// Internal utility methods

	/**
	 * Constructor
	 */
	protected DataFormatter() {
		super();
		this.backend = new Connector();
	}

	public static String getServerURL() {
		return backend.server_url;
	}

	public static void setServerURL(String server_url) {
		DataFormatter.backend.server_url = server_url;
	}

	/**
	 * Internal method for JSON preparation
	 * @param in 	The original string
	 * @return a string with all but the last character
	 */
	public static StringBuilder trimComma(StringBuilder in) {
		if (in.length() > 0 && in.charAt(in.length() - 1) == ',')
			in.deleteCharAt(in.length() - 1);
		return in;
	}

	/**
	 * Internal method for JSON preparation
	 * @param in	The original String
	 * @return the string, encapsulated in quotes
	 */
	static String quote(String in) {
		return String.format("\"%s\"", in);
	}

	/**
	 * Internal method for JSON preparation
	 * @return a string with all but the first and last characters
	 */
	static String unquote(String in) {
		return in.substring(
				Math.min(in.length() - 1, 1),
				Math.max(in.length() - 1, 0));
	}

	/**
	 * Idiom for enabling ordered iteration on any map.
	 * The reason for this is to make the strings compare equal for testing
	 * @param values
	 * @return
	 */
	static <T extends Comparable<T>> List<T> sorted_values(
		Map<String, T> values) {
		List<T> sorted_values = new ArrayList<>(values.values());
		Collections.sort(sorted_values);
		return sorted_values;
	}

	/**
	 * Idiom for enabling ordered iteration on any map.
	 * The reason for this is to make the strings compare equal for testing
	 * @param map
	 * @return
	 */
	static <K extends Comparable<K>, V> List<Entry<K, V>> sorted_entries(
		Map<K, V> map) {
		List<Entry<K, V>> sorted_entries = new ArrayList<>(map.entrySet());
		Collections.sort(sorted_entries, new Comparator<Entry<K, V>>() {
			public int compare(Entry<K, V> t, Entry<K, V> o) {
				return t.getKey().compareTo(o.getKey());
			}
		});
		return sorted_entries;
	}

	/**
	 * 	Automatically choose whether to open a node identifier with:
	 *  Twitter via followers(),
	 *  TMDb with movies(), or
	 *  RottenTomatoes with actors()
	 *
	 * @param identifier
	 * @param max
	 * @returns a list of identifiers
	 * @throws QueryLimitException
	 */

	/**
	 * This Method returns the list of followers
	 * @param identifier holds the name of the
	 * @param max holds the max number of followers
	 * @return
	 */
	public static List<Follower> getAssociations(Follower identifier, int max) {
		try {
			return followers(identifier, max);
		}
		catch (RateLimitException e) {
			return new ArrayList<>();
		}
	}



	/**
	 * This Method returns the list of tweets
	 * @param identifier holds the name of the
	 * @param max holds the max number of tweets
	 * @return
	 */
	public static List<Tweet> getAssociations(TwitterAccount identifier, int max) {
		try {
			System.out.println("hello form getAssociations Data formatter");
			return getTwitterTimeline(identifier, max);
		}
		catch (RateLimitException e) {
			return new ArrayList<>();
		}
	}

	/**
	 * This Method returns the list of earthquakes
	 * @param identifier holds the name of the
	 * @param max holds the max number of earthquakes
	 * @return
	 */
	public static List<EarthquakeUSGS> getAssociations(USGSaccount identifier, int max) {
		try {
			return getUSGSTimeline(identifier, max);
		}
		catch (RateLimitException e) {
			return new ArrayList<>();
		}
	}

	/**
	 * This method change the tweet object into an earthquake tweet object with
	 * more data extracted from the tweet content, like magnitude
	 * @param aList
	 * @return
	 */
	public static List<EarthquakeTweet> convertTweet(List<Tweet> aList) {
		List<EarthquakeTweet> earthquakes = new ArrayList<>();
		for (int i = 0; i < aList.size(); i++) {
			Tweet aTweet = aList.get(i);
			EarthquakeTweet anEarthquake = new EarthquakeTweet(aTweet);
			earthquakes.add(anEarthquake);
		}
		return earthquakes;
	}

	/**
	 * This Method returns the list of actors
	 * @param identifier holds the name of the movie
	 * @param max holds the max number of actors
	 * @return
	 */
	public static List<Actor> getAssociations(Actor identifier, int max) {
		try {
			return actors(identifier, max);
		}
		catch (RateLimitException e) {
			return new ArrayList<>();
		}
	}


	/**
	 * This Method returns the list of movies
	 * @param identifier holds the name of the movie
	 * @param max holds the max number of movies
	 * @return
	 */
	public static List<Movie> getAssociations(Movie identifier, int max) {
		try {
			return movies(identifier, max);
		}
		catch (RateLimitException e) {
			return new ArrayList<>();
		}
	}

	/** List the user's followers as more FollowGraphNodes.
	 *  Limit the result to `max` followers. Note that results are batched, so
	 *  a large `max` (as high as 200) _may_ only count as one request.
	 *  See DataFormatters.followgraph() for more about rate limiting.
	 * @throws IOException
	 */
	static List<Follower> followers(Follower id, int max)
	throws RateLimitException {
		if (failsafe) {
			// Don't contact Twitter, use sample data
			return SampleDataGenerator.getFriends(id.getName(), max);
		}
		else {
			try {
				//either timeline or followers
				String resp = backend.get("/streams/twitter.com/followers/"
						+ id.getName() + "/" + max);
				//System.out.println("the resp: "+resp);
				JSONObject response = backend.asJSONObject(resp);
				JSONArray followers = (JSONArray) backend.safeJSONTraverse(
						"['followers']", response, JSONArray.class);
				List<Follower> results = new ArrayList<>();
				for (Object follower : followers) {
					String name = (String) backend.safeJSONTraverse(
							"", follower, String.class);
					results.add(new Follower(name));
				}
				return results;
			}
			catch (IOException e) {
				// Trigger failsafe.
				System.err.println("Warning: Trouble contacting DataFormatters. Using "
					+ "sample data instead.\n"
					+ e.getMessage());
				failsafe = true;
				return followers(id, max);
			}
		}
	}

	/**
	 * List the user's tweets in the current twitter account.
	 * Limit the result to `max` followers. Note that results are batched, so
	 * a large `max` (as high as 500) _may_ only count as one request.
	 * See DataFormatters.followgraph() for more about rate limiting.
	 * @throws IOException
	 */
	private static List<Tweet> getTwitterTimeline(TwitterAccount id, int max)
	throws RateLimitException {
		if (failsafe) {
			// Don't contact Twitter, use sample data
			return SampleDataGenerator.getTwitterTimeline(id.getName(), max);
		}
		else {
			try {
				if (allTweets.isEmpty()) {
					System.out.println("/streams/twitter.com/timeline/"
						+ id.getName() + "/" + maxRequests);
					String partial = "/streams/twitter.com/timeline/"
						+ id.getName() + "/" + maxRequests;
					String resp = "";
					resp = backend.get(partial);
					JSONObject response = backend.asJSONObject(resp);
					JSONArray tweets_json = (JSONArray) backend.safeJSONTraverse(
							"['tweets']", response, JSONArray.class);
					for (Object tweet_json : tweets_json) {
						String content = (String) backend.safeJSONTraverse(
								"['tweet']", tweet_json, String.class);
						String date_str = (String) backend.safeJSONTraverse(
								"['date']", tweet_json, String.class);

						// TODO: When Java 8 is common enough, switch this to ZonedDateTime.parse()
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
						Date date;
						try {
							date = df.parse(date_str);
						}
						catch (ParseException e) {
							date = new Date();
						}
						allTweets.add(new Tweet(content, date));
					}
				}

				max = validNumberOfTweets(max);
				List<Tweet> results = new ArrayList<>();
				//	results.addAll(allTweets);
				return next(results, max);

			}
			catch (IOException e) {
				// Trigger failsafe.
				System.err.println("Warning: Trouble contacting DataFormatters. Using "
					+ "sample data instead.\n"
					+ e.getMessage());
				failsafe = true;
				return getTwitterTimeline(id, max);
			}
		}
	}

	private static List<EarthquakeUSGS> getUSGSTimeline(USGSaccount id, int max)
	throws RateLimitException {
		try {
			if (allUSGS.isEmpty()) {
				String resp = backend.getUSGS("/latest/" + maxRequests);
				JSONObject response = backend.asJSONObject(resp);

				JSONArray usgs_json = (JSONArray) backend.safeJSONTraverse(    //these are earthquakes
						"['Earthquakes']", response, JSONArray.class);

				for (Object eq_json : usgs_json) {

					UsgsFoo deserializedEq = new Gson().fromJson(eq_json.toString(), UsgsFoo.class);//deserializing Eq
					//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					//DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");


					Date date = new Date(Long.parseLong((deserializedEq.properties.time)));
					//formated_date = df.format(date);

					allUSGS.add(new EarthquakeUSGS(deserializedEq.properties.mag,
							date,
							Double.parseDouble(deserializedEq.properties.mag),
							Float.parseFloat(deserializedEq.geometry.coordinates.longitude),
							Float.parseFloat(deserializedEq.geometry.coordinates.latitude),
							deserializedEq.properties.place,
							deserializedEq.properties.title,
							deserializedEq.properties.url,
							deserializedEq.properties.toString()));
				}

				//			System.out.println("allUSGS: " + allUSGS.get(0));
				max = validNumberOfTweets(max);
				List<EarthquakeUSGS> results = new ArrayList<>();
				//	results.addAll(allTweets);
				return next(results, max, id);

			}
		}
		catch (IOException e) {
			// Trigger failsafe.
			System.err.println("Warning: Trouble contacting DataFormatters. Using "
				+ "sample data instead.\n"
				+ e.getMessage());
			failsafe = true;
			return getUSGSTimeline(id, max);
		}
		return allUSGS;
	}


	/**
	 * The next(List<Tweet>, int) method retrieves the next batch of tweets
	 * and adds deep copy of those tweets to the current list
	 * @param aList holds the reference to the current list of tweets
	 * @param max the number of tweets in the new batch of tweets
	 * @return the list of tweets containing the old and the new batch of tweets
	 */
	public static List<Tweet> next(List<Tweet> aList, int max) {
		max = validNumberOfTweets(max);
		for (int i = 0; i < max; i++) {
			tweetIterator ++;
			try {
				//aList.add(allTweets.get(tweetIterator));
				aList.add(new Tweet(allTweets.get(tweetIterator)));
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return aList;
	}

	/**
	 * The next(List<EarthquakeUSGS>, int) method retrieves the next batch of eq
	 * and adds deep copy of those eq to the current list
	 * @param aList holds the reference to the current list of eq
	 * @param max the number of eq in the new batch of eq
	 * @return the list of eq containing the old and the new batch of tweets
	 */
	public static List<EarthquakeUSGS> next(List<EarthquakeUSGS> aList, int max, USGSaccount acu) {
		max = validNumberOfTweets(max);//same validator as for the tweets
		for (int i = 0; i < max; i++) {
			tweetIterator ++; //same iterator as for the tweets
			try {
				//aList.add(allTweets.get(tweetIterator));
				aList.add(new EarthquakeUSGS(allUSGS.get(tweetIterator)));
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return aList;
	}
	/**
	 * Check the validity of number of Tweets requested
	 * @param max the number of tweets
	 * @return returns true if the number is in the range 0 - 500
	 */
	public static int validNumberOfTweets(int max) {
		//check if max is valid
		try {
			if (max < 0 || (max + tweetIterator) > 500) {

				max = 500 - tweetIterator;
				throw new DataFormatterException("The number of tweets requested must be in the range 0 - 500");
			}
		}
		catch (DataFormatterException ex) {
			System.out.println (ex.getError());
		}
		return max;
	}

	/**
	 * Return a list of movies an actor played in.
	 *
	 * The data comes courtesy of RottenTomatoes.
	 *
	 * The quota for this resource is about 10k actors/day but is shared by all
	 * students. So if you consume all 10k, it will be a bad day. Please make
	 * sure you limit your queries appropriately.
	 *
	 */
	static List<Movie> movies(Movie id, int max)
	throws RateLimitException {

		if (failsafe) {
			// Don't contact DataFormatters, use sample data
			return SampleDataGenerator.getMovies(id.getName(), max);
		}
		else {
			try {
				String resp = backend.get("/streams/actors/" + id.getName());
				JSONArray movies = backend.asJSONArray(resp);

				// Get (in JS) movies_json.map(function(m) { return m.title; })
				List<Movie> results = new ArrayList<>();
				for (Object movie : movies) {
					String title = (String) backend.safeJSONTraverse("['title']",
							movie, String.class);
					results.add(new Movie(title));
				}
				return results;
			}
			catch (IOException e) {
				// Trigger failsafe.
				System.err.println("Warning: Trouble contacting DataFormatters. Using "
					+ "sample data instead.\n"
					+ e.getMessage());
				failsafe = true;
				return movies(id, max);
			}
		}
	}

	/**
	 * Return the actors that played in a movie.
	 *
	 * The data comes courtesy of TMDb.
	 *
	 * This resource has unlimited queries but has caveats. Not every extra
	 * that played in every movie ever is listed in the database and some
	 * movies are documented rather sparsely. Expect some to be missing.
	 * @throws IOException
	 * @throws RateLimitException
	 */
	static List<Actor> actors(Actor id, int max)
	throws RateLimitException {

		if (failsafe) {
			// Don't contact DataFormatters, use sample data
			return SampleDataGenerator.getCast(id.getName(), max);
		}
		else {
			try {
				String resp = backend.get("/streams/rottentomatoes.com/" + id.getName());
				JSONArray movies = backend.asJSONArray(resp);

				// We will assume that the first movie is the right one
				JSONArray abridged_cast = (JSONArray) backend.safeJSONTraverse(
						"[0]['abridged_cast']", movies, JSONArray.class);
				List<Actor> results = new ArrayList<>();
				for (Object cast_member : abridged_cast) {
					if (results.size() == max)
						break;
					String name = (String) backend.safeJSONTraverse("['name']",
							cast_member, String.class);
					results.add(new Actor(name));
				}
				return results;
			}
			catch (IOException e) {
				// Trigger failsafe.
				System.err.println("Warning: Trouble contacting Bridges. Using "
					+ "sample data instead.\n"
					+ e.getMessage());
				failsafe = true;
				return actors(id, max);
			}
		}
	}

	/**
	 * Generate a sample Edge weight for two nodes
	 * @param source
	 * @param target
	 * @return
	 */
	public static double getEdgeWeight(String source, String target) {
		int h = source.hashCode() + target.hashCode();
		if (h < 0)
			h = -h;
		return h % 10;
	}

	/**
	 * @return the backend
	 */
	protected Connector getBackend() {
		return backend;
	}

	/**
	 * @param backend the backend to set
	 */
	protected void setBackend(Connector backend) {
		DataFormatter.backend = backend;
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
	/**
	 *  Get USGS earthquake data
	 *  USGS Tweet data (https://earthquake.usgs.gov/earthquakes/map/)
	 *  retrieved, formatted into a list of EarthquakeUSGS objects
	 *
	 *  @param maxElem  the number of earthquake records retrieved, limited to 5000
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of earthquake records
	 */

	public static ArrayList<EarthquakeUSGS> getEarthquakeUSGSData(
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

	static ActorMovieIMDB parseActorMovieIMDB(JSONObject item) {
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
	public static ArrayList<ActorMovieIMDB> getActorMovieIMDBData(int maxElem) throws IOException, IllegalArgumentException {

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

			ArrayList<ActorMovieIMDB> am_list =
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
	 *  Get ActorMovie IMDB Data
	 *  retrieved, formatted into a list of ActorMovieIMDB objects
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of ActorMovieIMDB objects, consisting of  actor name,
	 *		movie name, movie genre and movie rating is returned.
	 *
	 */
	public static ArrayList<ActorMovieIMDB> getActorMovieIMDBData2 ()
	throws IOException {

		String url = "https://bridgesdata.herokuapp.com/api/imdb2";
		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();

		if (status == 200) 	{
			JSONArray json = unwrapJSONArray(response);

			ArrayList<ActorMovieIMDB> am_list =
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
	 *
	 *  Get meta data of the Gutenberg book collection (1000 books)
	 *  This function retrieves,  and formats the data into a list of
	 *	GutenbergBook objects
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of GutenbergBook objects,
	 *
	 */
	public static ArrayList<GutenbergBook> getGutenbergBookMetaData ()
	throws IOException {

		String url = "https://bridgesdata.herokuapp.com/api/books";
		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();

		if (status == 200) 	{
			JSONArray json = unwrapJSONArray(response);

			ArrayList<GutenbergBook> gb_list =
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
	 *
	 *  Get meta data of the IGN games collection.
	 *
	 *  This function retrieves  and formats the data into a list of
	 *	Game objects
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of Game objects,
	 *
	 */
	public static ArrayList<Game> getGameData() throws IOException {

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
	 *
	 *  Get data of the songs (including lyrics) using the Genius API
	 *	https://docs.genius.com/
	 *
	 *  This function retrieves  and formats the data into a list of
	 *	Song objects. This version of the API retrieves all the cached
	 *	songs in the local DB.
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of Song objects,
	 *
	 */
	public static ArrayList<Song> getSongData() throws IOException {

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
	 *
	 *  Get data of a particular songs (including lyrics) using the Genius API
	 *	(https://docs.genius.com/), given the song title and artist name.
	 *
	 *  This function retrieves  and formats the data into a
	 *	Song object. The song if not cached in the local DB is queried
	 *	and added to the DB
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a Song object,
	 *
	 */
	public static Song getSong(String songTitle, String artistName)
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

	/* Due to the nature of how Java handles numbers, Bytes range from -128 to 127, which is problematic for
	 * Bridges Color objects which store RGB as 0-255. This function translates 4 bytes into a Bridges Color object,
	 * taking these factors into account.
	 * This function may be useful to add directly to the Color class, but it may only be needed in this case, where
	 * we need to convert an array of bytes into a ColorGrid.
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
	/**Reconstruct a ColorGrid from an existing ColorGrid on the Bridges server
	 *
	 * @return the ColorGrid stored in the bridges server
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 * @param subassignment the ID of the subassignment to get
	 **/
	static bridges.base.ColorGrid getColorGridFromAssignment(String server,
		String user,
		int assignment,
		int subassignment
	) throws IOException {
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

	/**Reconstruct a ColorGrid from an existing ColorGrid on the Bridges server
	 *
	 * @return the ColorGrid stored in the bridges server
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 **/
	static bridges.base.ColorGrid getColorGridFromAssignment(String server, String user, int assignment)
	throws IOException {
		return getColorGridFromAssignment(server, user, assignment, 0);
	}

	/***
	 * This function obtains the JSON representation of a particular subassignment.
	 *
	 * @return a string that is the JSON representation of the subassignment as stored by the Bridges server.
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 * @param subassignment the ID of the subassignment to get
	 ***/
	static String getAssignment(String server, String user, int assignment, int subassignment)
	throws IOException {
		String leadingZero = subassignment > 10 ? "" : "0";
		String url = String.format("%s/assignmentJSON/%d.%s%d/%s", server, assignment, leadingZero, subassignment, user);
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
	 * This function obtains the JSON representation of the first subassignment of an assignment.
	 *
	 * @return a string that is the JSON representation of the subassignment as stored by the Bridges server.
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 ***/
	static String getAssignment(String server, String user, int assignment) throws IOException, Exception {
		return getAssignment(server, user, assignment, 0);
	}

	/***
	 * Fetches Open Street Map data for a given location
	 * @param location, name of city or area that the server supports
	 * @return OsmData, vertices and edges of Open Street Map data
	 * @throws IOException, If there is an error parsing response from server or is an invalid location name
	 */
	static OsmData getOsmData(String location) throws IOException {
		File cache_dir = new File("./bridges_data_cache");

		boolean has_cache = true;
		// make cache if does not exist
		if (!cache_dir.exists()) {
			if(!cache_dir.mkdir()) {
				System.err.println("Error creating cache directory");
				has_cache = false;
			}
		}

		// look for file in cache
		String content = null;
		if (has_cache) {
			for (File file : cache_dir.listFiles()) {
				if (file.getName().equals(location + ".json")) {
					content = new String(Files.readAllBytes(file.toPath()));
				}
			}
		}

		// if not in cache, hit server for data
		if (content == null) {
			String url = "https://osm-api.herokuapp.com/name/" + location;
			HttpResponse resp = makeRequest(url);
			int status = resp.getStatusLine().getStatusCode();
			content = EntityUtils.toString(resp.getEntity());
			if (status != 200) {
				if (status == 404) {
					// attempt to get valid names
					url = "https://osm-api.herokuapp.com/name_list";
					resp = makeRequest(url);
					status = resp.getStatusLine().getStatusCode();
					if (status == 200) {
						content = EntityUtils.toString(resp.getEntity());
						throw new RuntimeException("Invalid name: " + location + "\nValid names:" + content);
					}
				}
				throw new HttpResponseException(status, "Http Request Failed. Error Code:" + status + ". Message:" + content);
			}
			Files.write(Paths.get("./bridges_data_cache/" + location + ".json"), content.getBytes());
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
	 *
	 *  Get data of Shakespeare works (plays, poems)
	 *
	 *  This function retrieves  and formats the data into a
	 *	a list of Shakespeare objects.
	 *
	 *  @throws Exception if the request fails
	 *
	 *	@param works  can be either "plays" or "poems". If this is specified,
	 *		then only these types of works are retrieved.
	 *	@param textOnly  if this is set, then only the text is retrieved.
	 *  @return an array of Shakespeare objects
	 *
	 */
	public static ArrayList<Shakespeare> getShakespeareData(String works, Boolean textOnly) throws IOException {
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

			ArrayList<Shakespeare> shksp_list =
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

	public static ArrayList<CancerIncidence> getCancerIncidenceData() throws IOException {

		String url = "https://bridgesdata.herokuapp.com/api/cancer/withlocations?limit=10";
		HttpResponse response = makeRequest(url);

		int status = response.getStatusLine().getStatusCode();

		if (status == 200) 	{
			JSONArray json = unwrapJSONArray(response);

			ArrayList<CancerIncidence> canc_objs =
				new ArrayList<CancerIncidence>(json.size());
			for (int i = 0; i < 10; i++) {
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

} // end of DataFormatter

