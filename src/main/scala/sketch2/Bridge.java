package sketch2;

import java.util.ArrayList;
import java.util.List;

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
	
	
}
