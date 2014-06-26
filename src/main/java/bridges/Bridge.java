package bridges;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * Connection to the Bridges server.
 * 
 * Initialize this class before using it, and call complete() afterward.
 * 
 * @author Sean Gallagher
 */
public class Bridge {
	private static int assignment;
	private static String key;
	private static String server_url;
	private static Visualizer visualizer;
	private static BridgeNetwork backend;
	public enum visual {GRAPH, TREE, ARRAY}; 

	/**
	 * Initialize Bridges with Visualizer
	 * @param assignment  The assignment number, for grading
	 * @param visualizer  The visualizer, for assignment
	 */
	public static void init(int assignment, String key, Visualizer visualizer) {
		Bridge.assignment = assignment;
		Bridge.key = key;
		Bridge.visualizer = visualizer;
		Bridge.backend = new BridgeNetwork();
	}
	
	/**
	 * Initialize Bridges with a Visualizer and a backend.
	 * This is only intended to make debugging easier by allowing you to stub
	 * the Internet.
	 * @param assignment  Your assignment ID
	 * @param visualizer  
	 * @param backend
	 */
	public static void init(
			int assignment, String key, Visualizer visualizer, BridgeNetwork backend) {
		init(assignment, key, visualizer);
		Bridge.backend = backend;
	}
	
	/**
	 * Initialize Bridges with a new Visualizer
	 * @param assignment  The assignment number, for grading
	 * @param visual  Type of Visualizer (an enum)
	 * @return the new Visualizer
	 */
	public static Visualizer init(int assignment, String key, visual kind) {
		// TODO: actually pick a Visualizer
		Visualizer visualizer = new GraphVisualizer();
		init(assignment, key, visualizer);
		return Bridge.visualizer;
	}
	
	/* Accessors and Mutators */

	public static int getAssignment() {
		return assignment;
	}
	public static void setAssignment(int assignment) {
		Bridge.assignment = assignment;
	}
	
	public static String getKey() {
		return key;
	}
	public static void setKey(String key) {
		Bridge.key = key;
	}

	public static String getServerURL() {
		return server_url;
	}
	public static void setServerURL(String server_url) {
		Bridge.server_url = server_url;
	}

	public static Visualizer getVisualizer() {
		return visualizer;
	}
	public static void setVisualizer(Visualizer visualizer) {
		Bridge.visualizer = visualizer;
	}
	
	/**
	 * Update visualization metadata. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	public static void update() {
        try {
			backend.post("/assignments/" + assignment, visualizer.getRepresentation());
		} catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central Bridges server is down. Try again later.");
			e.printStackTrace();
		} catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " Bridges developers and file a bug report; this error"
					+ " should not be possible.");
			e.printStackTrace();
		}
        // Return a URL to the user
        System.out.println("Check out your visuals at " + backend.prepare("/assignments/" + assignment + "/YOUR_USERNAME") );
	}

	/**
	 * Close down Bridges.
	 * 
	 * This only calls update() but it could conceivably do more.
	 */
	public static void complete() {
		update();
	}
	
	
	// Internal utility methods
	
	/**
	 * Internal method for JSON preparation
	 * @param in 	The original string
	 * @return a string with all but the last character
	 */
	static String trimComma(String in) {
		return in.substring(0, Math.max(in.length()-1, 0));
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
     * @throws IOException 
     * @returns a list of identifiers
     * @throws QueryLimitException
     */
    public static List<String> getAssociations(String identifier, int max)
    		throws RateLimitException, IOException {
    	Ident id = Ident.fromAnyString(identifier);
    	switch (id.provider) {
    	case "twitter.com":
    		return followers(id, max);
    	case "actor":
    		return movies(id, max);
    	case "movie":
    		return actors(id, max);
    	default:
    		throw new RuntimeException(
    				"Unrecognized service " + id.provider
    				+ ". Choose from twitter.com, actor, or movie");
    	}
    }
    
    /** List the user's followers as more FollowGraphNodes.
        Limit the result to `max` followers. Note that results are batched, so
        a large `max` (as high as 200) _may_ only count as one request.
        See Bridges.followgraph() for more about rate limiting. 
     * @throws IOException */
    static List<String> followers(Ident id, int max)
    		throws RateLimitException, IOException {
    	String resp = backend.get("/streams/twitter.com/followers/"
    			+ id.name + "/" + max);
    	
        JSONObject response = backend.asJSONObject(resp);
        JSONArray followers = (JSONArray) backend.safeJSONTraverse(
        		"['followers']", response, JSONArray.class);
        
        List<String> results = new ArrayList<>();
    	for (Object follower : followers) {
    		String name = (String) backend.safeJSONTraverse(
    				"", follower, String.class);
    		results.add("twitter.com/" + name);
    	}
        return results;
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
    static List<String> movies(Ident id, int max)
    		throws RateLimitException, IOException {
    	String resp = backend.get("/streams/actors/" + id.name);
    	JSONArray movies = backend.asJSONArray(resp);
    	
        // Get (in JS) movies_json.map(function(m) { return m.title; })
        List<String> results = new ArrayList<>();
        for (Object movie : movies) {
        	String title = (String) backend.safeJSONTraverse("['title']",
        			movie, String.class);
        	results.add("movie/" + title);
        }
        return results;
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
    static List<String> actors(Ident id, int max)
    		throws IOException, RateLimitException {
    	String resp = backend.get("/streams/rottentomatoes.com/" + id.name);
    	JSONArray movies = backend.asJSONArray(resp);
    	
        // We will assume that the first movie is the right one
    	JSONArray abridged_cast = (JSONArray) backend.safeJSONTraverse(
    			"[0]['abridged_cast']", movies, JSONArray.class);
    	List<String> results = new ArrayList<>();
    	for (Object cast_member : abridged_cast) {
    		if (results.size() == max)
    			break;
    		String name = (String) backend.safeJSONTraverse("['name']",
    				cast_member, String.class);
			results.add("actor/" + name);
    	}
    	return results;
    }
}

class Ident {
	public String provider;
	public String name;
	
	/**
	 * Create a new Ident from an identifier string with a provider
	 * @param identifier
	 */
	public Ident(String identifier) {
    	if (identifier.contains("/")) {
    		String[] halves = identifier.split("/", 2);
    		provider = halves[0];
    		name = halves[1];
    	} else {
    		throw new RuntimeException("Provider or screenname missing in "
    				+ identifier + ". Should look like: example.com/username");
    	}
	}
	
	/**
	 * Create a plain, straightforward Ident with the provider and name as given.
	 * @param provider
	 * @param name
	 */
	public Ident(String provider, String name) {
		this.provider = provider;
		this.name = name;
	}
	
	public static Ident fromAnyString(String identifier) {
    	if (identifier.contains("/")) {
    		return new Ident(identifier);
    	} else {
    		return new Ident("", identifier);
    	}
	}
}
