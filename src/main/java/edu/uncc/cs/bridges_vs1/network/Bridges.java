package edu.uncc.cs.bridges_vs1.network;

/**
 * Connection to the Bridges server.
 * 
 * Initialize this class before using it, and call complete() afterward.
 * 
 * @author Sean Gallagher
 * @param <E>
 * @secondAuthor Mihai Mehedint
 */

import java.io.IOException;
import java.util.HashMap;

import edu.uncc.cs.bridges_vs1.sources.Tweet;
import edu.uncc.cs.bridges_vs1.structure.ADTVisualizer;
import edu.uncc.cs.bridges_vs1.structure.Element;
import edu.uncc.cs.bridges_vs1.structure.GraphList;
import edu.uncc.cs.bridges_vs1.structure.SLelement;
import edu.uncc.cs.bridges_vs1.validation.RateLimitException;

public class Bridges <E> {
	
	private static double assignmentDecimal = 0.0;
	public ADTVisualizer<E> visualizer ;
	private SLelement<E> root;
	private static int assignment;
	private static String key;

	private static DataFormatter formatter;
	private static String userName;

	
	
	/**
	 * Constructors
	 * @throws Exception 
	 */
	public Bridges() throws Exception {
		super();
		visualizer = new ADTVisualizer<>();
		Bridges.formatter = new DataFormatter();
	}

	public Bridges(String key, String username) throws Exception{
		this();
		init(key, username);
	}
	
	public Bridges(String key, SLelement<E> e, String username) throws Exception{
		this();
		init(key, e, username);
	}

	/**
	 * Initialize DataFormatters with Visualizer
	 * @param <E>
	 * @param assignment  The assignment number, for grading
	 * @param visualizer  The visualizer, for assignment
	 * @param username 
	 * @throws Exception 
	 */
	public <E> void init(String key, String username){
		Bridges.key = key;
		Bridges.userName = username;
		
	}
	
	/**
	 * Initialize DataFormatters with Visualizer
	 * @param <E>
	 * @param assignment  The assignment number, for grading
	 * @param visualizer  The visualizer, for assignment
	 * @param username TODO
	 */
	public void init(String key, SLelement<E> e, String username) throws Exception{
		init(key, username);
		root = e;
	}
	

	
	/* Accessors and Mutators */
	public static String getAssignment() {
		return Double.toString(assignment+assignmentDecimal);
	}
	public static void setAssignment(int assignment) {
		Bridges.assignment = assignment;
	}
	
	// This exists to prevent duplicate error traces.
	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		Bridges.userName = userName;
	}
	
	public static String getKey() {
		return key;
	}
	
	public static void setKey(String key) {
		Bridges.key = key;
	}

	
	/**
	 * This method returns the current visualizer
	 * @return visualizer
	 */
	public ADTVisualizer<E> getVisualizer() {
		return visualizer;
	}
	/**
	 * This method sets the new DataFormatter visualizer
	 * @param visualizer
	 */
	public void setVisualizer(ADTVisualizer<E> visualizer) {
		this.visualizer = visualizer;
	}
	
	/**
	 * This method sets the HashMap and the type of ADT for the ADTVisualizer object
	 * @param mapOfLinks
	 * @param visualizerType
	 * @throws Exception
	 */
	public void setDataStructure(HashMap<Element<E>, HashMap<String, Element<E>>> mapOfLinks,
			String visualizerType) throws Exception{
		visualizer.setMapOfLinks(mapOfLinks);
		visualizer.setVisualizerType(visualizerType);
	}
	
	/**
	 * This method sets the HashMap and the type of ADT for the ADTVisualizer object
	 * @param mapOfLinks
	 * @param visualizerType
	 * @throws Exception
	 */
	public void setDataStructure(SLelement<E> e, 
			String visualizerType) throws Exception{
		root = e;
		visualizer.setVisualizerType(visualizerType);
	}
	
	/**
	 * This method is sets the adjacency list for the Graph ADT
	 * @param adjacencyList
	 * @param visualizerType
	 * @throws Exception
	 */
	public void setDataStructure(
			String visualizerType,
			HashMap<Element<E>, GraphList<E>> adjacencyList) throws Exception{
		visualizer.setAdjacencyList(adjacencyList);
		visualizer.setVisualizerType(visualizerType);	
	}
	
	/**
	 * This method adds one Element to the ADTVisualizer object
	 * @param e
	 * @throws Exception
	 */
	public void add(Element<E> e) throws Exception{
		visualizer.add(e);
	}
	
	/**
	 * This methods sets a link between 2 given elements of the ADTVisualizer
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public void setLink(Element<E> source, Element<E> target) throws Exception{
		visualizer.setLink(source, target);
	}
	
	/**
	 * This method returns the current JSON
	 * @return JSON string
	 */
	public String getJSON(){
		if (visualizer.getVisualizerType().compareToIgnoreCase("graph")==0)
			return visualizer.getGraphRepresentation();
		else if (visualizer.getVisualizerType().equalsIgnoreCase("llist"))
			return visualizer.getSLRepresentation(root);
		else
			return visualizer.getGraphRepresentation();
	}
	
	/**
	 * This method calls the updateGraph() or updateSL() methods
	 * depending upon the type of ADT being created.
	 * These methods send the JSON to post() which ultimately executes the http request
	 * from the server
	 */
	public void visualize(int assignment){
		this.assignment = assignment;
		if (visualizer.visualizerType.equalsIgnoreCase("graph") && visualizer.getAdjacencyList().size()==0)
			this.updateGraph(assignment);
		else if (visualizer.visualizerType.equalsIgnoreCase("llist"))
			this.updateSL(assignment);
		else if (visualizer.visualizerType.equalsIgnoreCase("graph") && visualizer.getAdjacencyList().size()!=0)
			this.updateGraphL(assignment);
		else
			this.updateGraph(assignment);
		
	}
	
	
	/**
	 * Update visualization metadata. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	public void updateGraph(int assignment) {
        try {
			formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getGraphRepresentation());
		} catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central DataFormatters server is down. Try again later.\n"
					+ e.getMessage());
		} catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " DataFormatters developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + formatter.getBackend().prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignmentDecimal+=0.01;
	}
	
	/**
	 * Update visualization metadata. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	public void updateSL(int assignment) {
        try {
        	formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getSLRepresentation(root));
		} catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central DataFormatters server is down. Try again later.\n"
					+ e.getMessage());
		} catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " DataFormatters developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + formatter.getBackend().prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignmentDecimal+=0.01;
	}

	/**
	 * Update visualization metadata. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	public void updateGraphL(int assignment) {
        try {
        	formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getGraphLRepresentation());
		} catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central Bridges server is down. Try again later.\n"
					+ e.getMessage());
		} catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " DataFormatters developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + formatter.getBackend().prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignmentDecimal+=0.01;
	}

	/**
	 * Close down DataFormatters.
	 * 
	 * This only calls update() but it could conceivably do more.
	 * @param i 
	 */
	public void complete(int assignment) {
		visualize(assignment);
	}

	
	
	
}
