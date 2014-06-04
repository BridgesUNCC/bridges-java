package sketch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Connection to the Bridges server.
 * 
 * Initialize this class before using it, and call complete() afterward.
 * 
 * @author Sean Gallagher
 */
public class Bridge {
	private static int assignment;
	private static String server_url;
	private static Visualizer visualizer;
	public enum visual {GRAPH, TREE, ARRAY}; 

	/**
	 * Initialize Bridges with Visualizer
	 * @param assignment  The assignment number, for grading
	 * @param visualizer  The visualizer, for assignment
	 */
	public static void init(int assignment, Visualizer visualizer) {
		Bridge.assignment = assignment;
		Bridge.visualizer = visualizer;
	}
	
	/**
	 * Initialize Bridges with a new Visualizer
	 * @param assignment  The assignment number, for grading
	 * @param visual  Type of Visualizer (an enum)
	 * @return the new Visualizer
	 */
	public static Visualizer init(int assignment, visual kind) {
		Bridge.assignment = assignment;
		// TODO: actually pick a Visualizer
		Bridge.visualizer = new GraphVisualizer();
		return Bridge.visualizer;
	}

	public int getAssignment() {
		return assignment;
	}

	public void setAssignment(int assignment) {
		this.assignment = assignment;
	}

	public String getServerURL() {
		return server_url;
	}

	public void setServerURL(String server_url) {
		this.server_url = server_url;
	}

	public Visualizer getVisualizer() {
		return visualizer;
	}

	public void setVisualizer(Visualizer visualizer) {
		this.visualizer = visualizer;
	};
	
	/**
	 * Get the identifiers of nodes associated with another node
	 * 
	 * What exactly this means depends on the identifier.
	 * For an actor, it would be the movies he played in, for example.
	 * 
	 * @param identifier  The node in question
	 * @return a list of related nodes' identifiers
	 */
	public static List<String> getAssociations(String identifier) {
		return new ArrayList<>();
	}
	
	/**
	 * Update visualization metadata. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	public static void update() {
		
	}

	/**
	 * Close down Bridges.
	 * 
	 * This only calls update() but it could conceivably do more.
	 */
	public static void complete() {
		
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
		return in.substring(Math.min(in.length()-1, 1), Math.max(in.length()-1, 0));
	}
	
	/**
	 * Idiom for enabling ordered iteration on any map.
	 * The reason for this is to make the strings compare equal for testing
	 * @param values
	 * @return
	 */
	static <T extends Comparable> List<T> sorted_values(Map<String, T> values) {
		List<T> sorted_values = new ArrayList<T>(values.values());
		Collections.sort(sorted_values);
		return sorted_values;
	}
	
	/**
	 * Idiom for enabling ordered iteration on any map.
	 * The reason for this is to make the strings compare equal for testing
	 * @param values
	 * @return
	 */
	static <K extends Comparable, V> List<Entry<K, V>> sorted_entries(Map<K, V> map) {
		List<Entry<K, V>> sorted_entries = new ArrayList<Entry<K, V>>(map.entrySet());
		Collections.sort(sorted_entries, new Comparator<Entry<K, V>>() {
			public int compare(Entry<K, V> t, Entry<K, V> o) {
				return t.getKey().compareTo(o.getKey());
			}
		});
		return sorted_entries;
	}
	
}
