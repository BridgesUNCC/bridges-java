package bridges.base;

import bridges.validation.Validation;
import bridges.connect.*;
import bridges.base.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;


/** The Bridges object uses this class to keep track of the Visualization representation prior to passing the information to the Bridges Server.
 * <p>
 * An end user will generally not need to interact directly with this class.
 */

public class ADTVisualizer<K, E> {
						// some constants used to generate JSON strings
	private String
			QUOTE = "\"",
			COMMA = ",",
			COLON = ":",
			OPEN_CURLY = "{", 
			CLOSE_CURLY = "}", 
			OPEN_PAREN = "[",
			CLOSE_PAREN = "]";
	public String visualizerType;
	public final Map<String, String> adt_names =
			new HashMap <String, String>(){
				private static final long serialVersionUID = 1L;

				{
					put("Array", "AList");
					put("SinglyLinkedList", "llist");
					put("DoublyLinkedList", "dllist");
					put("GraphAdjacencyList","graphl");
					put("GraphAdjacencyMatrix","graphl");
					put("ArrayStack","llist");
					put("ArrayQueue","llist");
					put("LinkedListStack","llist");
					put("LinkedListQueue","llist");
					put("BinaryTree","tree");
					put("BinarySearchTree","tree");
				}
			};

	public Element<E> [] elementArray;
	protected int arrayIndex;
	protected boolean json_flag = false;
	
	/**
	 * constructor using the superclass Object
	 * when using this constructor the default ADT structure is graph 
	 * @throws Exception 
	 */														
	public ADTVisualizer(){
		super();
	}
	
	/** Constructor allow visulizations of an array.
	 * @param adtArray the array of elements to visualize
	 */
	public ADTVisualizer(ArrayElement<E>[] elementArray) {
		super();
		this.elementArray = elementArray;
	}
	
	/**
	 * Any other object type sent to the ADTVisualizer will throw an exception
	 * example sending a String will throw an error
	 * @param type an object
	 * @throws Exception to indicate that an invalid object was sent to 
	 *		the ADTVisualizer
	 */
	public ADTVisualizer(Object type){
		validateType(type);
	}
	
	/**
	 * Validates whether the "type" is recognized by ADTVisualizer.
	 * @param type an object to check whether ADTVisualizer will accept as a valid type
	 */
	public void validateType(Object type){
		if (type.getClass().getSimpleName().equals("HashMap") ||
			type.getClass().getSimpleName().equals("Element<>[]"))
			;
		else if (type.getClass().getSimpleName().equals("Object")){
			try {
				throw new Exception("ADT structure undefined. Expected a "
						+ " HashMap or an Array object. Ex: ADTVisualizer<Tweet>[] vis = new ADTVisualizer<Tweet>(new Element<Tweet>[100]);");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				throw new Exception("Invalid type " +type.getClass().getSimpleName() + "'. Expected a "
						+ " HashMap or an Array object.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method returns the visualizer type as a string
	 * @return one the of the strings represented in ADTVisualizer.ADT_TYPE 
	 * that represents the type of Bridges Visualization (e.g. "graph", 
	 * "llist", etc.)
	 */
	public String getVisualizerType() {
		return visualizerType;
	}

	/**
	 * This method sets the visualizer type
	 * @param visualizerType a string in ADTVisualizer.ADT_TYPE representing 
	 * 	the type of Bridges Visualization (e.g. "graph", "llist", etc.)
	 * @throws Exception
	 */
	public void setVisualizerType(String visualizerType) {
		if (adt_names.keySet().contains(visualizerType))
			this.visualizerType = visualizerType;
		else if (visualizerType==null){
			try {
				throw new NullPointerException("Invalid value '" + 
					visualizerType + "'. Expected "
					+ " a string value: graph, llist, AList, stack, tree, or queue.");
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		} else{
			try {
				throw new IllegalArgumentException(
					"Invalid value '" + visualizerType + "'. Expected "
					+ " a string value: graph, llist, AList, stack, tree, or queue.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
 	 * This method creates a JSON representation of the graph - 
 	 * Adjacency List Representation. 
 	 * @param Hashmap<String, SLelement<E> represents the list of vertices
 	 * each of which is a singly linked list.
 	 * @return - this method returns the JSON string
 	 */
	public String getGraphAdjList_Representation(GraphAdjList<K,E> graph) {
							// first gather the nodes and links by
							// traversing the graph
		LinkedList<Element<E>> nodes = new LinkedList<>(); 
		LinkedList<Element<E>> links_src = new LinkedList<>();
		LinkedList<Element<E>> links_dest = new LinkedList<>();

		HashMap<K, Element<E> > vertices = graph.getVertices();
		HashMap<K, SLelement<Edge<K>> > adj_list = graph.getAdjacencyList();
										// get the graph nodes
		for (Entry<K, Element<E>> element : vertices.entrySet()) 
			nodes.push(element.getValue());
										// get the links
		for (Entry<K, SLelement<Edge<K>>> element : adj_list.entrySet()){ 
							// traverse this vertex's adj list
			SLelement<Edge<K>> list = element.getValue();
			while (list != null) {
							// get the source vertex
				links_src.push(vertices.get(element.getKey()) );
							// look up the vertex corresponding to this edge
				Edge<K> edge = list.getValue();
				Element<E> dest_vertex = vertices.get(edge.getVertex());
				links_dest.push(dest_vertex);
				list = list.getNext();
			}
		}
						// use the singly linked list case to get the JSON!
		return generateJSON_Graph(nodes, links_src, links_dest);
	}
	
	/**
 	 * This method creates a JSON representation of the graph - Adjacency 
	 * Matrix
	 *
 	 * @param e - is the starting element(node)
	 * @return - this method returns the JSON 
	 *
	 **/
	public String getGraphAdjMatrix_Representation(GraphAdjMatrix<K,E> graph) {
							// first gather the nodes and links by
							// traversing the graph
		LinkedList<Element<E>> nodes = new LinkedList<>(); 
		LinkedList<Element<E>> links_src = new LinkedList<>();
		LinkedList<Element<E>> links_dest = new LinkedList<>();

		HashMap<K, Element<E> > vertices = graph.getVertices();
		HashMap<K, HashMap<K, Integer> > matrix = graph.getAdjacencyMatrix();
										// get the graph nodes
		for (Entry<K, Element<E>> element : vertices.entrySet()) 
			nodes.push(element.getValue());
										// get the links
		for (Entry<K, HashMap<K, Integer> > el_src : matrix.entrySet()){ 
							// check if vertex exists
			for (Entry<K,Integer> el_dest : el_src.getValue().entrySet()) {
				links_src.push(vertices.get(el_src.getKey()) );
										// get destination vertex
				Element<E> dest_vert = vertices.get(el_dest.getKey());
				links_dest.push(dest_vert);
			}
		}
						// use the singly linked list case to get the JSON!
		return generateJSON_Graph(nodes, links_src, links_dest);
	}


	public String getSLRepresentation(SLelement<E> first_element) {
		LinkedList<SLelement<E>> nodes = new LinkedList<>(); 
		LinkedList<SLelement<E>> links = new LinkedList<>();
										// first get the nodes and links
		SLelement<E> sle = first_element;
		for (sle = first_element; sle!= null; sle = sle.getNext()) {
			nodes.push(sle);
			if (sle.getNext() != null) {	// link exists
				links.push(sle);
				links.push(sle.getNext());
			}
		}
									// generate the JSON string
		String str =  generateJSON_SLL(nodes, links);

		return str;
	}
	
	/**
	 * This method returns the JSON string of a doubly linked list 
	 * @param e
	 * @return
	 */
	public String getDLRepresentation(DLelement<E> first_element) {

		LinkedList<DLelement<E>> nodes = new LinkedList<>(); 
		LinkedList<DLelement<E>> links = new LinkedList<>();
										// first get the nodes and links
		DLelement<E> dle = first_element;
		for (dle = first_element; dle!= null; dle = dle.getNext()) {
			nodes.push(dle);
			if (dle.getNext() != null) {	// link exists
				links.push(dle);
				links.push(dle.getNext());
			}
			if (dle.getPrev() != null) {	// link exists
				links.push(dle);
				links.push(dle.getPrev());
			}
		}
										// generate the JSON string
		String str =  generateJSON_DLL(nodes, links);

		return str;
	}

	public String getArrayRepresentation(Element<E>[] el_array, int size) {
		
								// generate the JSON string
		return generateJSON_Array(el_array, size);
	}
	/**
	 * This method returns the JSON string representation of the tree 
	 * made by using preorder traversal
	 * @param e
	 * @return
	 */
	public String getTreeRepresentation(TreeElement<E> root) {

		LinkedList<TreeElement<E>> nodes = new LinkedList<>(); 
		LinkedList<TreeElement<E>> links =new LinkedList<>();

		preOrder(root, nodes, links);

		return getJSON_BinaryTree(nodes, links);
	}
	
	/**
	 * Use a preorder traversal to collect the nodes and links in the tree
	 * @param root
	 * @param element_to_index
	 * @param i
	 * @return
	 */
	private void preOrder(TreeElement<E> root, LinkedList<TreeElement<E>> 
						nodes, LinkedList<TreeElement<E>> links){
		if (root != null){
			nodes.push(root);
			if (root.getLeft() != null){
				links.push(root);
				links.push(root.getLeft());
			}
			if (root.getRight() != null){
				links.push(root);
				links.push(root.getRight());
			}
			preOrder(root.getLeft(), nodes, links);
			preOrder(root.getRight(), nodes, links);
		}
	}
	/**
	 * Generating the JSON string for an array of elements
	 *
	 * @param element array (Element<E>[])
	 * @param size (size of array)
	 *
	 * @return JSON string
	*/

	public String generateJSON_Array(Element<E>[] el_array, int size){
		StringBuilder nodes_JSON = new StringBuilder();
		StringBuilder links_JSON = new StringBuilder();
		Validation.validate_ADT_size(size);
		for (int i = 0; i < size; i++){
			if (el_array[i] != null){
				nodes_JSON.append(el_array[i].getRepresentation() + ",");
			}
			
		}
					// note: there are no links for an array, order is 
					// by index
		return build_JSON(nodes_JSON, links_JSON);
	}
	
	
	/**
		 * Generating the JSON string given a set of nodes 
		 * and links for the singly linked list
		 * @param nodes
		 * @param links
		 * @param data structure type
		 * @return string
	*/
	public String generateJSON_SLL (LinkedList<SLelement<E>> nodes, 
					LinkedList<SLelement<E>> links){
	
		HashMap<Integer,Integer> map = new HashMap<>();
		StringBuilder nodes_JSON = new StringBuilder();
		StringBuilder links_JSON = new StringBuilder();
	
							// map the nodes to a sequence of ids, 0...N-1
							// then get the JSON string for nodes
		int i = 0;
		while (!nodes.isEmpty()){
			SLelement<E>  element = nodes.pop();
			Validation.validate_ADT_size(i);
			map.put(Integer.parseInt(element.getIdentifier()), i++);
			nodes_JSON.append(element.getRepresentation() + COMMA);
		}
							// get the JSON string for links
		String str = links_JSON.toString();
		while (!links.isEmpty()){
							// get the link
			SLelement<E> child = links.pop();
			SLelement<E> parent = links.pop();
	
							// get the link properties
			LinkVisualizer lvis = parent.getLinkVisualizer(child);
			str+= OPEN_CURLY;
			if (lvis != null) {
				for (Entry<String,String> entry:lvis.getProperties().entrySet())
																{
					str += 	QUOTE + entry.getKey() + QUOTE 
								  +	COLON +
							QUOTE + entry.getValue() + QUOTE + COMMA;
				}
			}
			str += 	QUOTE + "source" + QUOTE + COLON + 
					map.get(Integer.parseInt(parent.getIdentifier()))
						+ COMMA + 
                    QUOTE + "target" + QUOTE + COLON + 
					map.get(Integer.parseInt(child.getIdentifier())) +
                    CLOSE_CURLY + COMMA;
		}
		links_JSON.append(str);
		return build_JSON(nodes_JSON, links_JSON);	 
	}
	
	/**
	 * This method creates the JSON for a doubly linked list
	 * @param nodes - this is the Linked list of DLelements
	 * @param links - this is the list containing the links
	 * @return returns the JSON string for the current ADT
	 */
	public String generateJSON_DLL (LinkedList<DLelement<E>> nodes, 
					LinkedList<DLelement<E>> links){
	
		HashMap<Integer,Integer> map = new HashMap<>();
		StringBuilder nodes_JSON = new StringBuilder();
		StringBuilder links_JSON = new StringBuilder();
	
							// map the nodes to a sequence of ids, 0...N-1
							// then get the JSON string for nodes
		int i = 0;
		while (!nodes.isEmpty()){
			DLelement<E>  element = nodes.pop();
			map.put(Integer.parseInt(element.getIdentifier()), i++);
			Validation.validate_ADT_size(i);
			nodes_JSON.append(element.getRepresentation() + COMMA);
		}
							// get the JSON string for links
		String str = links_JSON.toString();
		while (!links.isEmpty()){
							// get the link
			DLelement<E> child = links.pop();
			DLelement<E> parent = links.pop();
							// get the link properties
			LinkVisualizer lvis = parent.getLinkVisualizer(child);
			str+= OPEN_CURLY;
			if (lvis != null) {
				for (Entry<String,String> entry : lvis.getProperties().entrySet()){
					str += 	QUOTE + entry.getKey() + QUOTE 
								  +	COLON +
							QUOTE + entry.getValue() + QUOTE + COMMA;
				}
			}

			str += 	QUOTE + "source" + QUOTE + COLON + 
					map.get(Integer.parseInt(parent.getIdentifier()))
						+ COMMA + 
                    QUOTE + "target" + QUOTE + COLON + 
					map.get(Integer.parseInt(child.getIdentifier())) +
                    CLOSE_CURLY + COMMA;
		}
		links_JSON.append(str);
		return build_JSON(nodes_JSON, links_JSON);	 
	}
	
	/**
	 * This method builds the JSON for the Binary Tree
	 * @param nodes - list of the tree nodes
	 * @param links - list of the tree links
	 * @return complete JSON string for the current ADT
	 */
	public String getJSON_BinaryTree(LinkedList<TreeElement<E>> nodes, 
							LinkedList<TreeElement<E>> links){
	
		HashMap<Integer,Integer> map = new HashMap<>();
		StringBuilder nodes_JSON = new StringBuilder();
		StringBuilder links_JSON = new StringBuilder();
		int i = 0;
		while (!nodes.isEmpty()){
			TreeElement<E> element = nodes.pop();
			map.put(Integer.parseInt(element.getIdentifier()), i++);
			Validation.validate_ADT_size(i);
			nodes_JSON.append(element.getRepresentation() + COMMA);
		}
			 
		String str = links_JSON.toString();
		while (!links.isEmpty()){
							// get links
			TreeElement<E> child = links.pop();
			TreeElement<E> parent = links.pop();
	
							// get the link properties
			LinkVisualizer lvis = parent.getLinkVisualizer(child);
			str+= OPEN_CURLY;
			if (lvis != null) {
				for (Entry<String,String> entry : lvis.getProperties().entrySet()){
					str += 	QUOTE + entry.getKey() + QUOTE 
								  +	COLON +
							QUOTE + entry.getValue() + QUOTE + COMMA;
				}
			}

			str += 	QUOTE + "source" + QUOTE + COLON + 
					map.get(Integer.parseInt(parent.getIdentifier()))
						+ COMMA + 
                    QUOTE + "target" + QUOTE + COLON + 
					map.get(Integer.parseInt(child.getIdentifier())) +
                    CLOSE_CURLY + COMMA;
		}
		links_JSON.append(str);
		return build_JSON(nodes_JSON, links_JSON);		 
	}
	
	/**
	 * This method builds the JSON for the Graph ADT
	 * @param nodes - list of the tree nodes
	 * @param links - list of the tree links
	 * @return complete JSON string for the current ADT
	 */
	public String generateJSON_Graph (LinkedList<Element<E>> nodes, 
				LinkedList<Element<E>> links_src, 
				LinkedList<Element<E>> links_dest){
	
		HashMap<Integer,Integer> map = new HashMap<>();
		StringBuilder nodes_JSON = new StringBuilder();
		StringBuilder links_JSON = new StringBuilder();
	
							// map the nodes to a sequence of ids, 0...N-1
							// then get the JSON string for nodes
		int i = 0;
		while (!nodes.isEmpty()){
			Element<E>  element = nodes.pop();
			map.put(Integer.parseInt(element.getIdentifier()), i++);
			Validation.validate_ADT_size(i);
			nodes_JSON.append(element.getRepresentation() + ",");
		}
							// get the JSON string for links
		String str = links_JSON.toString();
		while (!links_src.isEmpty()){
							// get the link
			Element<E> child = links_dest.pop();
			Element<E> parent = links_src.pop();
	
							// get the link properties
			LinkVisualizer lvis = parent.getLinkVisualizer(child);
			str+="{";
			if (lvis != null) {
				for (Entry<String,String> entry : lvis.getProperties().entrySet()){
					str += String.format("\"%s\": \"%s\", ", entry.getKey(), 
									entry.getValue());
				}
			}
	
			str += String.format("\"source\":%s,", 
					map.get(Integer.parseInt(parent.getIdentifier())));
							// get the edge terminating vertex
			str += String.format("\"target\":%s", 
					map.get(Integer.parseInt(child.getIdentifier())) );
	
			str += "},";
		}
		links_JSON.append(str);
		return build_JSON(nodes_JSON, links_JSON);	 
	}
		 
	/**
	 * @param check if the flag to output the JSON is set
	 **/
	public boolean visualizeJSON() {
		return json_flag;
	}

	/**
	 * @param set the flag to output the JSON
	 **/
	public void	setVisualizeJSON(boolean flag) {
		json_flag = flag;
	}
		
	/**
	 * This method builds the JSON string
	 * @param nodes_JSON the string builder object of existing nodes 
	 * @param links_JSON the string builder object of existing links 
	 * 	between the nodes
	 * @return ADT's JSON
	 **/
	public String build_JSON(StringBuilder nodes_JSON, 
							StringBuilder links_JSON){
		StringBuilder s = new StringBuilder();
			
		s.append("{").append("\"name\": \"edu.uncc.cs.bridges\",")
			.append("\"version\": \"0.4.0\",")
			.append("\"visual\": \""+adt_names.get(visualizerType)+"\",")
			.append("\"nodes\": [").append(trimComma(nodes_JSON))
			.append( "],")
			.append("\"links\": [").append(trimComma(links_JSON))
			.append("]")
			.append("}");

		if (visualizeJSON())
			System.out.println(s.toString());

		return s.toString();	
	}	
	public static StringBuilder trimComma(StringBuilder in) {
		if (in.length() > 0 && in.charAt(in.length()-1) == ',')
			in.deleteCharAt(in.length()-1);

		return in;
	}
}
