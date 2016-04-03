package bridges.connect;

 
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import bridges.base.*;
import bridges.validation.*;
import bridges.data_src_dependent.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;





/**
 * Connection to the DataFormatters server.
 * 
 * Initialize this class before using it, and call complete() afterward.
 * 
 * @author Sean Gallagher
 * @param <E>
 * @secondAuthor Mihai Mehedint
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
		if (in.length() > 0 && in.charAt(in.length()-1) == ',')
			in.deleteCharAt(in.length()-1);
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
				Math.min(in.length()-1, 1),
				Math.max(in.length()-1, 0));
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
	 * @param values
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
    	public static List<Follower> getAssociations(Follower identifier, int max){
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
    	 * @throws MyExceptionClass 
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
        	 * @throws MyExceptionClass 
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
        	public static List<EarthquakeTweet> convertTweet(List<Tweet> aList){
        		List<EarthquakeTweet> earthquakes = new ArrayList<>();
        		for (int i =0; i<aList.size();i++){
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
        	public static List<Actor> getAssociations(Actor identifier, int max){
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
        	public static List<Movie> getAssociations(Movie identifier, int max){
            	try {
            		return movies(identifier, max);
        	    }
            		catch (RateLimitException e) {
            		return new ArrayList<>();
            	}
        }
	
    /** List the user's followers as more FollowGraphNodes.
        Limit the result to `max` followers. Note that results are batched, so
        a large `max` (as high as 200) _may_ only count as one request.
        See DataFormatters.followgraph() for more about rate limiting. 
     * @throws IOException */
    static List<Follower> followers(Follower id, int max)
    		throws RateLimitException {
    	if (failsafe) {
    		// Don't contact Twitter, use sample data
    		return SampleDataGenerator.getFriends(id.getName(), max);
    	} else {
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
	    	} catch (IOException e) {
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
     * @throws MyExceptionClass 
     * @throws IOException */
	private static List<Tweet> getTwitterTimeline(TwitterAccount id, int max)
			throws RateLimitException{
		if (failsafe) {
			// Don't contact Twitter, use sample data
			return SampleDataGenerator.getTwitterTimeline(id.getName(), max);
		} else {
	    	try {
			 if (allTweets.isEmpty()){
				 	System.out.println("/streams/twitter.com/timeline/"
			    			+ id.getName() + "/" + maxRequests);
				 	String partial = "/streams/twitter.com/timeline/"
			    			+ id.getName() + "/" + maxRequests;
	    			String resp ="";
	    			resp= backend.get(partial);
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
							} catch (ParseException e) {
								date = new Date();
							}
				    		allTweets.add(new Tweet(content, date));
				    	}
			 }
			
			max = validNumberOfTweets(max);
		    	List<Tweet> results = new ArrayList<>();
		    //	results.addAll(allTweets);
		    	return next(results, max);
		    	
	    	} catch (IOException e) {
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
			throws RateLimitException{
	    	try {
			 if (allUSGS.isEmpty()){   				 	
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
				    										Float.parseFloat(deserializedEq.geometry.coordinates.latitude), 
				    										Float.parseFloat(deserializedEq.geometry.coordinates.longitude),
				    										deserializedEq.properties.place, 
				    										deserializedEq.properties.title,
				    										deserializedEq.properties.url,
				    										deserializedEq.properties.toString()));
				    	}
			 
			
			max = validNumberOfTweets(max);
		    	List<EarthquakeUSGS> results = new ArrayList<>();
		    //	results.addAll(allTweets);
		    	return next(results, max, id);
		    	
	    	}
			 } catch (IOException e) {
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
	 * @throws MyExceptionClass 
	 */
	public static List<Tweet> next(List<Tweet> aList, int max){
		max = validNumberOfTweets(max);
		for (int i = 0; i < max; i++){
			tweetIterator ++;
			try{
				//aList.add(allTweets.get(tweetIterator));
				aList.add(new Tweet(allTweets.get(tweetIterator)));
				} catch(Exception e){
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
	 * @throws MyExceptionClass 
	 */
	public static List<EarthquakeUSGS> next(List<EarthquakeUSGS> aList, int max, USGSaccount acu){
		max = validNumberOfTweets(max);//same validator as for the tweets
		for (int i = 0; i < max; i++){
			tweetIterator ++; //same iterator as for the tweets
			try{
				//aList.add(allTweets.get(tweetIterator));
				aList.add(new EarthquakeUSGS(allUSGS.get(tweetIterator)));
				} catch(Exception e){
					System.out.println(e.getMessage());
				}	
		}
		return aList;
	}
	/**
	 * Check the validity of number of Tweets requested
	 * @param max the number of tweets
	 * @return returns true if the number is in the range 0 - 500
	 * @throws MyExceptionClass otherwise
	 */
	public static int validNumberOfTweets(int max){
		 //check if max is valid
		 try{
			 if (max<0 || (max+tweetIterator)>500){
		 
				 max = 500 - tweetIterator;
			 	throw new DataFormatterException("The number of tweets requested must be in the range 0 - 500");
			 }
		 } catch (DataFormatterException ex){
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
    	} else {
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
	    	} catch (IOException e) {
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
    	} else {
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
	    	} catch (IOException e) {
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
    	if (h < 0) h = -h;
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
}
