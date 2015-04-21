package edu.uncc.cs.bridgesV2.connect;

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
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import edu.uncc.cs.bridgesV2.base.*;
import edu.uncc.cs.bridgesV2.validation.RateLimitException;
import edu.uncc.cs.bridgesV2.validation.Validation;

public class Bridges <E> {
	
	private static double assignmentDecimal = 0.0;
	protected ADTVisualizer<E> visualizer ;
	private Element<E> root;
	private static int assignment;
	private static String key;

	private static DataFormatter formatter;
	private static String userName;
	private final Map<String, String> ADT_UPDATE = new HashMap <String, String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 2055228591103348943L;

	{
		put("graph","updateGraphL");
		put("graphl","updateGraphL");
		put("stack","updateSL");
		put("queue","updateSL");
		put("tree","updateTree");
		put("llist", "updateSL");
		put("Dllist", "updateDL");
		put("AList", "updateAL");
		}};
	
	
	/**
	 * Constructors
	 * @throws Exception 
	 */
	public Bridges() {
		super();
		visualizer = new ADTVisualizer<>();
		Bridges.formatter = new DataFormatter();
		
	}
	
	/**
	 * 
	 * @param assignment this is an integer value;
	 * Ex: 13; whenever new Bridges.
	 * @param key
	 * @param username
	 * @throws Exception
	 */
	public Bridges(int assignment, String key, String username){
		this();
		init(assignment, key, username);
	}
	
	public Bridges(int assignment, String key, SLelement<E> e, String username){
		this();
		init(assignment, key, e, username);
	}

	/**
	 * Initialize DataFormatters with Visualizer
	 * @param <E>
	 * @param assignment  The assignment number, for grading
	 * @param visualizer  The visualizer, for assignment
	 * @param username 
	 * @throws Exception 
	 */
	public <E> void init(int assignment, String key, String username){
		Bridges.setAssignment(assignment);
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
	public void init(int assignment, String key, Element<E> e, String username){
		init(assignment, key, username);
		root = e;
	}
	
	/* Accessors and Mutators */
	public static String getAssignment() {
		return Double.toString(round(assignment+assignmentDecimal,2));
	}
	public static void setAssignment(int assignment) {
		if (assignment<0)
			throw new IllegalArgumentException("\nThe assignment value must be greater than or equal to 0.\n");
		else if (Bridges.assignment >= 0)
			Bridges.assignmentDecimal=0.0;
		else
			throw new IllegalArgumentException("\nPlease set an assignment value as an integer value greater than 0\n");
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
/*
	public void setDataStructure(HashMap<Element<E>, HashMap<String, Element<E>>> mapOfLinks,
			String visualizerType) throws Exception{
		visualizer.setMapOfLinks(mapOfLinks);
		visualizer.setVisualizerType(visualizerType);
		visualize();
	}
*/
	
	/**
	 * This method sets the first element and the type of ADT for the ADTVisualizer object
	 * @param e - is a SLelement<E>
	 * @param visualizerType
	 * this parameter can be set to: "graph", "graphl","stack","queue","tree", "llist", "AList" or "Dllist" 
	 * @throws Exception
	 */
	public void setDataStructure(Element<E> []arrayVisualizer, 
			String visualizerType){
		visualizer.setArray(arrayVisualizer);
		visualizer.setVisualizerType(visualizerType);
	}
	
	/**
	 * This method sets the first element and the type of ADT for the ADTVisualizer object
	 * @param e - is a SLelement<E>
	 * @param visualizerType
	 * this parameter can be set to: "graph", "graphl","stack","queue","tree", "llist" or "Dllist" 
	 */
	public void setDataStructure(SLelement<E> e, 
			String visualizerType){
		root = e;
		visualizer.setVisualizerType(visualizerType);
	}
	
	/**
	 * This method sets the first element and the type of ADT for the ADTVisualizer object
	 * @param e - is a DLelement<E>
	 * @param visualizerType
	 * this parameter can be set to: "graph", "graphl","stack","queue","tree", "llist" or "Dllist"
	 */
	public void setDataStructure(DLelement<E> e, 
			String visualizerType){
		root = e;
		visualizer.setVisualizerType(visualizerType);
	}
	
	/**
	 * This method sets the first element and the type of ADT for the ADTVisualizer object
	 * @param e - is a DLelement<E>
	 * @param visualizerType
	 * this parameter can be set to: "graph", "graphl","stack","queue","tree", "llist", AList or "Dllist"
	 * @throws Exception
	 */
	public void setDataStructure(TreeElement<E> e, 
			String visualizerType){
		root = e;
		visualizer.setVisualizerType(visualizerType);
	}
	
	/**
	 * This method is sets the adjacency list for the Graph ADT
	 * @param adjacencyList
	 * @param visualizerType
	 * this parameter can be set to: "graph", "graphl","stack","queue","tree", "llist" or "Dllist"
	 * @throws Exception
	 */
	public void setDataStructure(HashMap<String,SLelement<E>> adjacencyList,
						String visualizerType){
		visualizer.setAdjacencyList(adjacencyList);
		visualizer.setVisualizerType(visualizerType);
	}
	
	/**
	 * This method adds one Element to the ADTVisualizer object
	 * @param e
	 * @throws Exception
	 */
	public void add(Element<E> e) throws Exception{
//		visualizer.add(e);
	}
	

	/**
	 * This method returns the current JSON
	 * @return JSON string
	 */
	public void toggleJSONdisplay(){
		if (this.getVisualizer().isVisualizeJSON())
			this.getVisualizer().setVisualizeJSON(false);
		else
			this.getVisualizer().setVisualizeJSON(true);
	}
	
	/**
	 * This method calls the updateGraph() or updateSL() methods
	 * depending upon the type of ADT being created.
	 * These methods send the JSON to post() which ultimately executes the http request
	 * from the server
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	public void visualize() {
		//Validation.validate_ADTVisualizer(visualizer, this);
		try {
			java.lang.reflect.Method method = this.getClass().getDeclaredMethod((ADT_UPDATE.get(visualizer.getVisualizerType())));
			method.invoke(this);
		} catch (SecurityException e) {
			System.err.println("Security Exception. \nPlease check your ADT type. Expected values are: \"graph\", \"graphl\",\"stack\",\"queue\",\"tree\", \"llist\", \"AList\" or \"Dllist\"");
			System.err.println("Please check the JSON string for errors. It cannot be null nor can have line breaks");
			System.err.println("Please check the error stack below.");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println("Illegal Argument Exception. \nPlease check your ADT type. Expected values are: \"graph\", \"graphl\",\"stack\",\"queue\",\"tree\", \"llist\", \"AList\" or \"Dllist\"");
			System.err.println("Please check the JSON string for errors. It cannot be null nor can have line breaks");
			System.err.println("Please check the error stack below.");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.err.println("NoSuchMethodException \nPlease check your ADT type. Expected values are: \"graph\", \"graphl\",\"stack\",\"queue\",\"tree\", \"llist\", \"AList\" or \"Dllist\"");
			System.err.println("Please check the error stack below.");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.err.println("Illegal AccessException. \nPlease check your ADT type. Expected values are: \"graph\", \"graphl\",\"stack\",\"queue\",\"tree\", \"llist\", \"AList\" or \"Dllist\"");
			System.err.println("Please check the JSON string for errors. It cannot be null nor can have line breaks");
			System.err.println("Please check the error stack below.");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.err.println("Invocation Target Exception \nPlease check your ADT type. Expected values are: \"graph\", \"graphl\",\"stack\",\"queue\",\"tree\", \"llist\", \"AList\" or \"Dllist\"");
			System.err.println("Please check the JSON string for errors. It cannot be null nor can have line breaks");
			System.err.println("Please check the error stack below.");
			e.printStackTrace();
		}
	}

	/**
	 * Update visualization metadata of graph using adjacency matrix. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
/*
	public void updateGraph() {
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
        assignmentDecimal = round(assignmentDecimal+=0.01, 2);
	}
*/
	
	/**
	 * Update visualization metadata of singly linked list. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	protected void updateSL() {
        try {
        	formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getSLRepresentation((SLelement<E>)root));
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
	 * Update visualization metadata of doubly linked list. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	protected void updateDL() {
        try {
        	formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getDLRepresentation((DLelement<E>)root));
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
	
	protected void updateAL() {
        try {
        	formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getALRepresentation());
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
	 * Update visualization metadata of Tree. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	protected void updateTree() {
        try {
        	formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getTreeRepresentation((TreeElement<E>)root));
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
					+ " Bridgess developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + formatter.getBackend().prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignmentDecimal+=0.01;
	}

	/**
	 * Update visualization metadata of Graph with Adjacency List. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	protected void updateGraphL() {
        try {
        	formatter.getBackend().post("/assignments/" + getAssignment(), visualizer.getGraphAdjList_Representation(visualizer.getAdjacencyList()));
		} catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server."
					+ " First please check the graph's adjaceny list. This may cause errors while trying to interpret the data."
					+ " Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central Bridges server is down. Try again later.\n"
					+ e.getMessage());
		} catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " the developers and file a bug report; this error"
					+ " should not be possible. Also please check the data type for graph's adjacency list.\n"
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
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	public void complete() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		visualize();
	}

	/**
	 * @return the root
	 */
	public Element<E> getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Element<E> root) {
		this.root = root;
	}
	
	
    /**
	* This methods takes care of the rounding errors in setting the decimal
	* @param value
	* @param places
	* @return
	*/
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
