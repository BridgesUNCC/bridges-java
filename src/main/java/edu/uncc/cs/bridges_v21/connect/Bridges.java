package bridges.connect;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import bridges.base.*;
import bridges.data_src_dependent.*;
import bridges.validation.*;


/**
 * 	@brief The Bridges class is the main class that provides interfaces to datasets,
 *	maintains user and assignment information, and connects to the Bridges server. 
 * 
 * 	The Bridges class is responsible  for initializing the Bridges system, specifying
 * 	parameters (user id, assignment id, title, description, data structure 
 *	type, etc) for the student assignment, generating the data structure representation 
 *	and transmission to the Bridges server. In addition, it provides interfaces to  
 *	a number of real-world datasets, that makes it easy to access the data for use
 * 	algorithms/data structure assignments. <br>
 *
 *  <b>Datasets.</b> The datasets that are currently supported through the BRIDGES API 
 *	include USGS Earthquake Data, IMDB Actor/Movie Data (2 versions), Gutenberg Book 
 *	Collection Meta Data, a Video Game Dataset and Shakespeare Dataset. More information
 *	is found in the respective methods (below) and at <p> 
 *	http://bridgesuncc.github.io/datasets.html <p>
 *
 *	A typical Bridges program includes creating the Bridges object, followed by creation
 *  of the data structure by the user, assigning visual attributes to elements of the 
 *	data structure, followed by specification of teh data structure type  and the 
 *	call to visualize the data structure (Bridges::setDataStructure() and visualize() 
 *	methods).
 * 
 * 	@author Sean Gallagher, Kalpathi Subramanaian, Mihai Mehedint.
 *
 * 	@date  1/16/17, 5/19/17
 *
 *	\sa Tutorial examples at <br> 
 *	http://bridgesuncc.github.io/Hello_World_Tutorials/Overview.html
 */

public class Bridges <K, E> {
	
	private static int assignmentDecimal = 0;
	protected ADTVisualizer<K, E> visualizer;
	private  Connector connector;
	private Element<E> root;
	private GraphAdjList<K, E>  graph_adj_list;
	private GraphAdjMatrix<K, E>  graph_adj_matrix;
	private Element<E>[]  element_array;
	private Array<E>  br_array;
	private int element_array_size;
	private static int assignment;
	private static int assignment_part;
	private static String key;
	private static DataFormatter df;
	private static String userName;

	/**
	 *
	 *	Constructors
	 *
	 */
	public Bridges() {
		super();
		visualizer = new ADTVisualizer<K,E>();
		connector = new Connector();
		df = new DataFormatter();
		assignment_part = 0;
	}
	
	/**
	 * Initialize Bridges (Constructor) 
	 *
	 * @param assignment this is the assignmen id (integer)
	 * @param appl_id    this is the appl authentication key(from the Bridges account)
	 * @param username   this is the username (from the Bridges account)
	 *
	 */
	public Bridges(int assignment, String appl_id, String username){
		this();
		init(assignment, appl_id, username);
	}

	/**
	 * 
	 * @param title title used in the visualization;
	 *
	 */
	public void setTitle(String title) {
		visualizer.setTitle(title);
	}

	/**
	 * 
	 * @param descr description to annotate the visualization;
	 *
	 */
	public void setDescription(String descr) {
		visualizer.setDescription(descr);
	}
	
	/**
	 *
	 * Initialize Bridges
	 *
	 * @param <E>
	 * @param assignment this is the assignmen id (integer)
	 * @param appl_id    this is the appl authentication key(from the Bridges account)
	 * @param username   this is the username (from the Bridges account)
	 *
	 */
	public <E> void init(int assignment, String appl_id, String username){
		Bridges.setAssignment(assignment);
		Bridges.key = appl_id;
		Bridges.userName = username;
	}
	
	public static List<Tweet> getAssociations(TwitterAccount name, 
									int maxElements){
		return DataFormatter.getAssociations(name, maxElements);
	}
	
	public static List<EarthquakeUSGS> getAssociations(USGSaccount name, 
									int maxElements){
		return DataFormatter.getAssociations(name, maxElements);
	}

	/**
	 *  This helper function provides a simple API to retrieve current USGS earthquake 
	 *	Tweet data from the USGS website (https://earthquake.usgs.gov/earthquakes/map/);
     *  The data is retrieved and formatted into a list of EarthquakeUSGS objects.
	 *  
	 *  More information on the dataset can be found at <p>
     *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://bridgesuncc.github.io/datasets.html <p>
	 *
	 *  @param name   USGS account name - must be "earthquake" to create account
	 *	@param maxElements  the number of earthquake records retrieved, limited to 5000
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of earthquake records
	 */
	public static List<EarthquakeUSGS> getEarthquakeUSGSData(USGSaccount name,
									int maxElements) throws Exception {
		return DataFormatter.getEarthquakeUSGSData(name, maxElements);
	}

	/**
	 *  This helper function provides access to a small curated IMDB dataset. The data is
	 *  retrieved, formatted into a list of ActorMovieIMDB objects (about 1800 pairs).
	 *
	 *  This curated data set has only actor and movie name pairs. refer to <p>
     *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://bridgesuncc.github.io/datasets.html <p>
     *  for more information and to look at the dataset.
	 *
	 *  @param name   should be "IMDB"
	 *  @param maxElements  the number of actor/movie pairs(but currently unused),
	 *	 							returns all records. 
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of ActorMovieIMDB objects, but only actor,  movie, movie genre
	 *			and movie rating are returned. 
	 */
	public static List<ActorMovieIMDB> getActorMovieIMDBData(String name, 
								int maxElements) throws Exception {
		return DataFormatter.getActorMovieIMDBData(name, maxElements);
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
	 *  @param name   should be "IMDB"
	 *  @param maxElements  the number of actor/movie pairs, but currently unused,
	 *	 	returns all records. 
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of ActorMovieIMDB objects
	 */
	public static List<ActorMovieIMDB> getActorMovieIMDBData2() throws 
											Exception {
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
	public static List<GutenbergBook> getGutenbergBookMetaData() 
										throws Exception{
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
	public static List<Game> getGameData() throws Exception {
		return DataFormatter.getGameData();
	}

	/**
	 *  This helper function provides access to a collection of Shakespeare plays,
	 * 	poems and plays.
	 *
	 *  Each record in this collection has  
	 *	information on title, type (poem, Sonnet, play) and text. <br>
	 *
	 *	For more information and to look at the data, refer to <p>
     *	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://bridgesuncc.github.io/datasets.html <p>
	 *
	 *  @throws Exception if the request fails
	 *
	 *  @return a list of Shakespeare objects.
	 */
	public static List<Shakespeare> getShakespeareData() throws Exception {
		return DataFormatter.getShakespeareData();
	}
	
	/**
	 *	Get the assignment id
	 *
	 *  @return assignment as a string
	 *
	 */
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
		if (assignment  <  0)
			throw new IllegalArgumentException(
				"\n Assignment value must be >=  0.\n");
		else if (Bridges.assignment >= 0)
			Bridges.assignment_part = 0;

		Bridges.assignment = assignment;
	}
	
	/**
	 *
	 *	This exists to prevent duplicate error traces.
	 *
	 *	@return user id (string)
	 */
	public static String getUserName() {
		return userName;
	}

	/**
	 *	set User id 
	 *
	 *	@param userName (string)
	 *
	 */
	public static void setUserName(String userName) {
		Bridges.userName = userName;
	}
	
	/**
	 *
	 *	Get application key
	 *
	 *	@return application key value (string)
	 *
	 */
	public static String getKey() {
		return key;
	}
	
	/**
	 *
	 *	Set application key
	 *
	 *	@param  key application key value (string)
	 *
	 */
	public static void setKey(String key) {
		Bridges.key = key;
	}

	/**
	 *
	 * This method returns the current visualizer (for internal use only)
	 *
	 * @return visualizer
	 *
	 */
	public ADTVisualizer<K, E> getVisualizer() {
		return visualizer;
	}
	
	/**
	 *
	 * This method sets visualizer (for internal use only)
	 *
	 * @param visualizer
	 *
	 */
	public void setVisualizer(ADTVisualizer<K, E> visualizer) {
		this.visualizer = visualizer;
	}
	
	/**
	 *
	 * 	This method sets the array data type as the current data structure.
	 *	Arrays of upto 3 dimensions are suppported.
	 *
	 * @param Array<E>   The array of elements, Array<E>
	 *
	 */
	public void setDataStructure(Array<E>  arr) {
		br_array = arr;	
		int num_dims = br_array.getNumDimensions();
		if (num_dims <= 3)
			visualizer.setVisualizerType ("Array");
		else throw  new InvalidValueException("Invalid number of dimensions. Only 1D, 2D  and 3D arrays supported at this time");
	}
	
	/**
	 * This method sets the first element of the singly linked list
	 *
	 * @param head  first element of the list
	 *
	 */
	public void setDataStructure(SLelement<E> head) {
		root = head;
		visualizer.setVisualizerType("SinglyLinkedList");
	}
	
	/**
	 * This method sets the first element of the multi list
	 *
	 * @param head  first element of the multi-list
	 *
	 */
	public void setDataStructure(MLelement<E> head) {
		root = head;
		visualizer.setVisualizerType("MultiList");
	}
	
	/**
	 * This method sets the first element of the doubly linked list
	 *
	 * @param head - first element of the  list  
	 *
	 */
	public void setDataStructure(DLelement<E> head){ 
		root = head;
		visualizer.setVisualizerType("DoublyLinkedList");
	}

	/**
	 * This method sets the first element of the singly linked circular list
	 *
	 * @param head - first element of  the circular singly linked list
	 *
	 */
	public void setDataStructure(CircSLelement<E> head) {
		root = head;
		visualizer.setVisualizerType("CircularSinglyLinkedList");
	}
	
	/**
	 * This method sets the first element of the doubly linked circular list
	 *
	 * @param head - first element of  the circular doubly linked list
	 *
	 */
	public void setDataStructure(CircDLelement<E> head) {
		root = head;
		visualizer.setVisualizerType("CircularDoublyLinkedList");
	}
	
	/**
	 * 	This method sets the root of a general  tree (can have 
	 *	any number of children at each node
	 *
	 * 	@param tree_root The root of the generalized tree
	 *
	 */
	public void setDataStructure(TreeElement<E> tree_root){
		root = tree_root;
		visualizer.setVisualizerType("Tree");
	}
	/**
	 * This method sets the root of the binary  tree
	 * data structure. 
	 *
	 * @param tree_root The root of the binary tree
	 */
	public void setDataStructure(BinTreeElement<E> tree_root){
		root = tree_root;
		visualizer.setVisualizerType("BinaryTree");
	}
	
	/**
	 * This method sets the root of the binary search tree
	 * data structure. 
	 *
	 * @param tree_root - The root of the binary search tree 
	 */
	public void setDataStructure(BSTElement<K, E> tree_root){
		root = tree_root;
		visualizer.setVisualizerType("BinarySearchTree");
	}
	
	/**
	 * This method sets the root of an AVL tree
	 * data structure. 
	 *
	 * @param tree_root The root of the AVL tree
	 */
	public void setDataStructure(AVLTreeElement<K, E> tree_root){
		root = tree_root;
		visualizer.setVisualizerType("AVLTree");
	}
	/**
	 * This method passes the handle to the input graph
	 * (represented using adjacency lists)
	 *
	 * @param graph adjacency list based graph
	 */
	public void setDataStructure(GraphAdjList<K, E> graph){
		graph_adj_list = graph;
		visualizer.setVisualizerType("GraphAdjacencyList");
	}

	/**
	 * This method passes the handle to the input graph (represented
	 * using adjacency matrix)
	 *
	 * @param graph adjacency matrix based graph
	 */
	public void setDataStructure(GraphAdjMatrix<K, E> graph){
		graph_adj_matrix = graph;
		visualizer.setVisualizerType("GraphAdjacencyMatrix");
	}
	
	/**
	 *
	 * This method generates the representation of the current data structure (JSON)
	 * and sends that to the Bridges server.
	 *
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	public void visualize() {
		switch (visualizer.getVisualizerType()) {
			case "Array":
				visualizeArrayObj();
				break;
			case "SinglyLinkedList":
			case "llist":
			case "CircularSinglyLinkedList":
				visualizeLinkedList();
				break;
			case "MultiList":
				visualizeMultiList();
				break;
			case "DoublyLinkedList":
			case "dllist":
			case "CircularDoublyLinkedList":
				visualizeDoublyLinkedList();
				break;
			case "Tree":
			case "BinaryTree":
			case "BinarySearchTree":
			case "AVLTree":
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
		System.out.println("\nCheck Your Visualization at \n\n" +
			"http://bridges-cs.herokuapp.com/assignments/" + assignment + "/" 
						+ userName + "\n\n");
        assignment_part++;
	}

	/**
	 *
	 * visualize a multi list. 
	 *
	 **/
	protected void visualizeMultiList() {
        try {
        	connector.post("/assignments/" + getAssignment(), 
				visualizer.getMLRepresentation((MLelement<E>)root));
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
		System.out.println("\nCheck Your Visualization at \n\n" +
			"http://bridges-cs.herokuapp.com/assignments/" + assignment + "/" 
						+ userName + "\n\n");
        assignment_part++;
	}
	
	
	/**
	 *  Visualization  a doubly linked list. 
	 *
	 **/
	protected void visualizeDoublyLinkedList() {
        try {
        	connector.post("/assignments/" + getAssignment(), 
				visualizer.getDLRepresentation((DLelement<E>)root));
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
		System.out.println("\nCheck Your Visualization at \n\n" +
			"http://bridges-cs.herokuapp.com/assignments/" + assignment + "/" 
						+ userName + "\n\n");
        assignment_part++;
	}
	
	/**
	 *  Visualize  an array
	 *
	 **/
	protected void visualizeArrayObj() {
        try {
        	connector.post("/assignments/" + getAssignment(), 
				visualizer.getArrayRepresentation(br_array));
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
		System.out.println("\nCheck Your Visualization at \n\n" +
			"http://bridges-cs.herokuapp.com/assignments/" + assignment + "/" 
						+ userName + "\n\n");
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
		System.out.println("\nCheck Your Visualization at \n\n" +
			"http://bridges-cs.herokuapp.com/assignments/" + assignment + "/" 
						+ userName + "\n\n");
        assignment_part++;
	}
	
	/**
	 * Visualize a binary tree
	 *
	 */
	protected void visualizeTree() {
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
		System.out.println("\nCheck Your Visualization at \n\n" +
			"http://bridges-cs.herokuapp.com/assignments/" + assignment + "/" 
						+ userName + "\n\n");
        assignment_part++;
	}
	/**
	 * Visualize a binary searchtree
	 *
	 */
	protected void visualizeBinarySearchTree() {
        try {
        	connector.post("/assignments/" + getAssignment(), 
					visualizer.getTreeRepresentation((TreeElement<E>)root));
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
		System.out.println("\nCheck Your Visualization at \n\n" +
			"http://bridges-cs.herokuapp.com/assignments/" + assignment + "/" 
						+ userName + "\n\n");
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
		System.out.println("\nCheck Your Visualization at \n\n" +
			"http://bridges-cs.herokuapp.com/assignments/" + assignment + "/" 
						+ userName + "\n\n");
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
		System.out.println("\nCheck Your Visualization at \n\n" +
			"http://bridges-cs.herokuapp.com/assignments/" + assignment + "/" 
						+ userName + "\n\n");
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
