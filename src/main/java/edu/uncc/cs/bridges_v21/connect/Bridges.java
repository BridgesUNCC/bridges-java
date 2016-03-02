package bridges.connect;

/**
 * Connection to the Bridges server.
 * 
 * Initialize this class before using it, and call complete() afterward.
 * 
 * @author Sean Gallagher
 * @param <E>
 * @secondAuthor Mihai Mehedint
 * @ third author K.R. Subramanian -- revisions to Bridges to sync with
 *   the C++ version
 */

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bridges.base.*;
import bridges.data_src_dependent.EarthquakeUSGS;
import bridges.data_src_dependent.Tweet;
import bridges.data_src_dependent.TwitterAccount;
import bridges.data_src_dependent.USGSaccount;
import bridges.validation.RateLimitException;
import bridges.validation.Validation;

public class Bridges <K, E> {
	
	private static int assignmentDecimal = 0;
	protected ADTVisualizer<K, E> visualizer;
	private  Connector connector;
	private Element<E> root;
	private GraphAdjList<K, E>  graph_adj_list;
	private GraphAdjMatrix<K, E>  graph_adj_matrix;
	private Element<E>[]  element_array;
	private int element_array_size;
	private static int assignment;
	private static int assignment_part;
	private static String key;
	private DataFormatter df;

	private static String userName;
	/**
	 * Constructors
	 * @throws Exception 
	 */
	public Bridges() {
		super();
		visualizer = new ADTVisualizer<K,E>();
		connector = new Connector();
		df = new DataFormatter();
		assignment_part = 0;
	}
	
	/**
	 * 
	 * @param assignment this is an integer value;
	 * @param key
	 * @param username
	 * @throws Exception
	 */
	public Bridges(int assignment, String appl_id, String username){
		this();
		init(assignment, appl_id, username);
	}
	
	/**
	 * Initialize DataFormatters with Visualizer
	 * @param <E>
	 * @param assignment  The assignment number, for grading
	 * @param visualizer  The visualizer, for assignment
	 * @param username 
	 * @throws Exception 
	 */
	public <E> void init(int assignment, String appl_id, String username){
		Bridges.setAssignment(assignment);
		Bridges.key = appl_id;
		Bridges.userName = username;
		
	}
	
	public static List<Tweet> getAssociations(TwitterAccount name, int maxElements){
		return DataFormatter.getAssociations(name, maxElements);
	}
	
	public static List<EarthquakeUSGS> getAssociations(USGSaccount name, int maxElements){
		return DataFormatter.getAssociations(name, maxElements);
	}
	/* Accessors and Mutators */
	public static String getAssignment() {
		return (assignment_part < 10) 
                ? String.valueOf(assignment) + ".0" + 
					String.valueOf(assignment_part)
                : String.valueOf(assignment) + "." + 
					String.valueOf(assignment_part);
	}

	/**
	 *	set the assignment id
	 *
	 * @param assignment number (int)
	 *
	 **/
	public static void setAssignment(int assignment) {
		if (assignment<0)
			throw new IllegalArgumentException(
				"\n Assignment value must be >=  0.\n");
		else if (Bridges.assignment >= 0)
			Bridges.assignment_part = 0;

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
	public ADTVisualizer<K, E> getVisualizer() {
		return visualizer;
	}
	
	/**
	 * This method sets the new DataFormatter visualizer
	 * @param visualizer
	 */
	public void setVisualizer(ADTVisualizer<K, E> visualizer) {
		this.visualizer = visualizer;
	}
	
	/**
	 * This method sets the array data type 
	 *
	 * @param e - is a Element<E>[]
	 * @param visualizerType
	 *
	 * @throws Exception
	 */
	public void setDataStructure(Element<E>[] el_array, int size){
//		visualizer.setArray(arrayVisualizer);
		element_array = el_array;
		element_array_size = size;
		visualizer.setVisualizerType("Array");
	}
	
	/**
	 * This method sets the first element of the singly linked list
	 * @param head - is a SLelement<E>
	 * @param visualizerType
	 *
	 */
	public void setDataStructure(SLelement<E> head) {
		root = head;
		visualizer.setVisualizerType("SinglyLinkedList");
	}
	
	/**
	 * This method sets the first element of the doubly linked list
	 *
	 * @param head - is a DLelement<E>
	 * @param visualizerType
	 *
	 */
	public void setDataStructure(DLelement<E> head){ 
		root = head;
		visualizer.setVisualizerType("DoublyLinkedList");
	}
	
	/**
	 * This method sets the root of the binary tree
	 *
	 * @param tree_root - is a TreeElement<E>
	 *
	 * @throws Exception
	 */
	public void setDataStructure(TreeElement<E> tree_root){
		root = tree_root;
		visualizer.setVisualizerType("BinaryTree");
	}
	
	/**
	 * This method sets the first element and the type of ADT for the 
	 * ADTVisualizer object
	 *
	 * @param tree_root - is a BSTElement<E>
	 * @throws Exception
	 */
	public void setDataStructure(BSTElement<K, E> tree_root){
		root = tree_root;
		visualizer.setVisualizerType("BinarySearchTree");
	}
	
	/**
	 * This method is sets the adjacency list for the Graph ADT
	 * @param adjacencyList
	 * @throws Exception
	 */
	public void setDataStructure(GraphAdjList<K, E> graph){
		graph_adj_list = graph;
		visualizer.setVisualizerType("GraphAdjacencyList");
	}
	/**
	 * This method is sets the adjacency list for the Graph ADT
	 * @param adjacencyList
	 * @throws Exception
	 */
	public void setDataStructure(GraphAdjMatrix<K, E> graph){
		graph_adj_matrix = graph;
		visualizer.setVisualizerType("GraphAdjacencyMatrix");
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
		switch (visualizer.getVisualizerType()) {
			case "Array":
				visualizeArray();
				break;
			case "SinglyLinkedList":
			case "llist":
				visualizeLinkedList();
				break;
			case "DoublyLinkedList":
			case "dllist":
				visualizeDoublyLinkedList();
				break;
			case "BinaryTree":
				visualizeBinaryTree();
				break;
			case "BinarySearchTree":
			case "tree":
				visualizeBinarySearchTree();
				break;
			case "GraphAdjacencyList":
				visualizeGraphAdjacencyList();
				break;
			case "GraphAdjacencyMatrix":
				visualizeGraphAdjacencyMatrix();
				break;
		}
/*
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
*/
	}

	/**
	 *
	 * visualize a singly linked list. 
	 *
	 **/
	protected void visualizeLinkedList() {
        try {
        	connector.post("/assignments/" + getAssignment(), 
				visualizer.getSLRepresentation((SLelement<E>)root));
		} 
		catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central DataFormatters server is down. Try again later.\n"
					+ e.getMessage());
		}
		catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " DataFormatters developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + connector.prepare("/assignments/" + getAssignment() + "/" + userName) ); 
        assignment_part++;
	}
	
	
	/**
	 *  Visualization  a doubly linked list. 
	 *
	 **/
	protected void visualizeDoublyLinkedList() {
System.out.println("here..");
        try {
        	connector.post("/assignments/" + getAssignment(), visualizer.getDLRepresentation((DLelement<E>)root));
		} 
		catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central DataFormatters server is down. Try again later.\n"
					+ e.getMessage());
		} 
		catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " DataFormatters developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + connector.prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignment_part++;
	}
	
	/**
	 *  Visualize  an array
	 *
	 **/
	protected void visualizeArray() {
        try {
        	connector.post("/assignments/" + getAssignment(), 
				visualizer.getArrayRepresentation(element_array, element_array_size));
		} 
		catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central DataFormatters server is down. Try again later.\n"
					+ e.getMessage());
		} 
		catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " DataFormatters developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + connector.prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignment_part++;
	}
	
	/**
	 * Visualize a binary tree
	 *
	 */
	protected void visualizeBinaryTree() {
        try {
        	connector.post("/assignments/" + getAssignment(), visualizer.getTreeRepresentation((TreeElement<E>)root));
		}
		catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central Bridges server is down. Try again later.\n"
					+ e.getMessage());
		}
		catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " Bridgess developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + connector.prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignment_part++;
	}
	/**
	 * Visualize a binary searchtree
	 *
	 */
	protected void visualizeBinarySearchTree() {
        try {
        	connector.post("/assignments/" + getAssignment(), visualizer.getTreeRepresentation((TreeElement<E>)root));
		}
		catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. Are you connected to the"
					+ " Internet? Check your network settings. Otherwise, maybe"
					+ " the central Bridges server is down. Try again later.\n"
					+ e.getMessage());
		}
		catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
					+ " representation to the server. However, it responded with"
					+ " an impossible 'RateLimitException'. Please contact"
					+ " Bridgess developers and file a bug report; this error"
					+ " should not be possible.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + connector.prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignment_part++;
	}

	/**
	 * Update visualization metadata of Graph with Adjacency List. This may be called many times.
	 * This is usually an expensive operation and involves connecting to the network.
	 * Calling this method is optional provided you call complete()
	 */
	protected void visualizeGraphAdjacencyList() {
        try {
        	connector.post("/assignments/" + getAssignment(), 
				visualizer.getGraphAdjList_Representation(graph_adj_list) );
		}
		catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
				+ " representation to the server."
				+ " First please check the graph's adjaceny list. This may cause errors while trying to interpret the data."
				+ " Are you connected to the"
				+ " Internet? Check your network settings. Otherwise, maybe"
				+ " the central Bridges server is down. Try again later.\n"
				+ e.getMessage());
		} 
		catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
				+ " representation to the server. However, it responded with"
				+ " an impossible 'RateLimitException'. Please contact"
				+ " the developers and file a bug report; this error"
				+ " should not be possible. Also please check the data type for graph's adjacency list.\n"
					+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + connector.prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignment_part++;
	}
	protected void visualizeGraphAdjacencyMatrix() {
        try {
        	connector.post("/assignments/" + getAssignment(),
				visualizer.getGraphAdjMatrix_Representation(graph_adj_matrix) );
		} 
		catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
				+ " representation to the server."
				+ " First please check the graph's adjaceny list. This may cause errors while trying to interpret the data."
				+ " Are you connected to the"
				+ " Internet? Check your network settings. Otherwise, maybe"
				+ " the central Bridges server is down. Try again later.\n"
				+ e.getMessage());
		} 
		catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
				+ " representation to the server. However, it responded with"
				+ " an impossible 'RateLimitException'. Please contact"
				+ " the developers and file a bug report; this error"
				+ " should not be possible. Also please check the data type for graph's adjacency list.\n"
				+ e.getMessage());
		} 
        // Return a URL to the user
        System.out.println("Check out your visuals at " + connector.prepare("/assignments/" + getAssignment() + "/" + userName) );
        assignment_part++;
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
}
