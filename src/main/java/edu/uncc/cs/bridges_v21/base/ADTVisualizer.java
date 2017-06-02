package bridges.base;

import bridges.validation.Validation;
import bridges.connect.*;
import bridges.base.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;


/** 
 *	@brief The Bridges object uses this class to keep track of the Visualization 
 *	representation prior to passing the information to the Bridges Server.
 * 	<p>
 * 	An end user will generally not need to interact directly with this class.
 */

public class ADTVisualizer<K, E> {
						// some constants used to generate JSON strings
	private String
			QUOTE = "\"",
			COMMA = ",",
			COLON = ":",
			OPEN_CURLY = "{", 
			CLOSE_CURLY = "}", 
			OPEN_PAREN = "(",
			CLOSE_PAREN = ")",
			OPEN_BOX = "[",
			CLOSE_BOX = "]";
	private Integer MaxTitleSize = 50,
			MaxDescrSize = 1000;
	private int arrayDims[] = {1, 1, 1};
	private String title = "", description = "";
	public String visualizerType;
	public final Map<String, String> adt_names =
			new HashMap <String, String>(){
				private static final long serialVersionUID = 1L;
				{
					put("Array", "Array");
					put("SinglyLinkedList", "SinglyLinkedList");
					put("MultiList", "MultiList");
					put("DoublyLinkedList", "DoublyLinkedList");
					put("CircularSinglyLinkedList", "CircularSinglyLinkedList");
					put("CircularDoublyLinkedList", "CircularDoublyLinkedList");
					put("GraphAdjacencyList","GraphAdjacencyList");
					put("GraphAdjacencyMatrix","GraphAdjacencyMatrix");
					put("ArrayStack","Array");
					put("ArrayQueue","Array");
					put("LinkedListStack","SinglyLinkedList");
					put("LinkedListQueue","SinglyLinkedList");
					put("Tree","Tree");
					put("BinaryTree","BinaryTree");
					put("BinarySearchTree","BinarySearchTree");
					put("AVLTree","AVLTree");
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

	/**
	 * 
	 * @param title title used in the visualization;
	 *
	 */
	public void setTitle(String titl) {
		if (titl.length() > MaxTitleSize) {
			System.out.println ("Visualization Title restricted to 50 characters." 
				+ " Truncating title..");
			title = titl.substring(0, 49);
		}
		else title = titl;
	}

	/**
	 * 
	 * @param descr description to annotate the visualization;
	 *
	 */
	public void setDescription(String descr) {
		if (descr.length() > MaxDescrSize) {
            System.out.println ("Visualization Description restricted to " + MaxDescrSize + " characters."
                + " Truncating description..");
            description = descr.substring(0, MaxDescrSize);
        }
		else description = descr;
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

									// distinguish between singly linked
									// circular singly linked lists
		if (getVisualizerType().equals("SinglyLinkedList")) {
										// first get the nodes and links
			SLelement<E> sle = first_element;
			for (sle = first_element; sle!= null; sle = sle.getNext()) {
				nodes.addLast(sle);
				if (sle.getNext() != null) {	// link exists
					links.addLast(sle);
					links.addLast(sle.getNext());
				}
			}
		}
		else { // circular list
			SLelement<E> sle = first_element;
			if (sle != null) {
				do {
					nodes.addLast(sle);
					links.addLast(sle);
					links.addLast(sle.getNext());
					sle = sle.getNext();
				} while (sle != first_element);
			}
		}
									// generate the JSON string
		String str = generateJSON_SLL(nodes, links);

		return str;
	}
	/**
	 * This method returns the JSON string of a multi-list
	 *
	 * @return JSON string
	 */
	public String getMLRepresentation(MLelement<E> first_element) {
		LinkedList<MLelement<E>> nodes = new LinkedList<>(); 
		LinkedList<MLelement<E>> links = new LinkedList<>();

		getMLRepresentation_R(first_element, nodes, links);

									// generate the JSON string
		String str = generateJSON_ML(nodes, links);

		return str;
	}

	public void getMLRepresentation_R (MLelement<E> first_el, 
						LinkedList<MLelement<E>> nodes, 
						LinkedList<MLelement<E>> links) {

		for (MLelement<E> mle = first_el; mle!= null; mle = mle.getNext()) {
			nodes.addLast(mle);
			if (mle.getTag()) {	// follow the sublist
								// first add the link from this node to the sublist
				links.addLast(mle);
				links.addLast(mle.getSubList());
								// now generate nodes and links of the sublist	
				getMLRepresentation_R(mle.getSubList(), nodes, links);
			}
			if (mle.getNext() != null) {	// link exists
				links.addLast(mle);
				links.addLast(mle.getNext());
			}
		}
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
									// distinguish between singly linked
									// circular singly linked lists
		if (getVisualizerType().equals("DoublyLinkedList")) {
			for (dle = first_element; dle!= null; dle = dle.getNext()) {
				nodes.addLast(dle);
				if (dle.getNext() != null) {	// link exists
					links.addLast(dle);
					links.addLast(dle.getNext());
				}
				if (dle.getPrev() != null) {	// link exists
					links.addLast(dle);
					links.addLast(dle.getPrev());
				}
			}
		}
		else {	// circular doubly linked list
			if (dle != null) {
				do {
					nodes.addLast(dle);
					links.addLast(dle);
					links.addLast(dle.getNext());
					links.addLast(dle);
					links.addLast(dle.getPrev());
					dle = dle.getNext();
				} while( dle != first_element); 
			}
		}
										// generate the JSON string
		String str =  generateJSON_DLL(nodes, links);

		return str;
	}

	/**
	 * This method returns the JSON representation of an array
	 *
	 * @param br_array  a Bridges array object, which is an array of Element<E>
	 */

	public String getArrayRepresentation(Array<E> br_array) {
		
								// generate the JSON string
		return generateJSON_Array(br_array);
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

	//	preOrder(root, nodes, links);

	//	return getJSON_BinaryTree(nodes, links);
		String json_str = build_JSON(preOrder2(root));


		return json_str;
	}
	
	/**
	 * Use a preorder traversal to collect the nodes and links in the tree
	 *
	 * @param root  root of the tree
	 * @param nodes  tree nodes
	 * @param links  parent-child links
	 *
	 * @return
	 */
/*
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
*/
	/**
	 *
	 *	Use a preorder traversal to directly extract a hierarchical JSON 
	 *	representation of the tree.
	 *
	 */
	 private String preOrder2(TreeElement<E> root) {
		String json_str = "", children = "", link_props = "", elem_rep = "";
		String t_str;
		int num = root.getNumberOfChildren();
		if (root != null) {
									// first get the node representation
			elem_rep = root.getRepresentation();
									// remove surrounding curly braces
			t_str = elem_rep.substring(1, elem_rep.length()-1);
			json_str += t_str;
									// now get the children
			if (root.getNumberOfChildren() > 0)
				json_str += COMMA + QUOTE + "children" + QUOTE + COLON + OPEN_BOX ;
//			else json_str += CLOSE_CURLY;
			for (int k = 0; k < root.getNumberOfChildren(); k++) {
				if (root.getChild(k) == null) {
					json_str += OPEN_CURLY + QUOTE + "name" + QUOTE + COLON+
						QUOTE + "NULL" + QUOTE + CLOSE_CURLY + COMMA;
				}
				else {
					LinkVisualizer lv = 
						root.getLinkVisualizer(root.getChild(k));
					json_str += OPEN_CURLY;
					if (lv != null) {
						json_str += 
							QUOTE +"linkProperties"+QUOTE+COLON+OPEN_CURLY +
							QUOTE +"color" + QUOTE+ COLON + 
							OPEN_BOX + 
								Integer.toString(lv.getColor().getRed()) + COMMA +
								Integer.toString(lv.getColor().getGreen()) + COMMA+
								Integer.toString(lv.getColor().getBlue()) + COMMA +
								Float.toString(lv.getColor().getAlpha()) +
							CLOSE_BOX + COMMA +
							QUOTE + "thickness" + QUOTE + COLON + 
							String.valueOf(lv.getThickness()) + 
							CLOSE_CURLY + COMMA;
					}
					else json_str += "linkProperties" + COLON + "{}" +COMMA;
									// process its children
					json_str +=	preOrder2(root.getChild(k));
					json_str += CLOSE_CURLY + COMMA;
				}
			}
							// remove last comma
			json_str = json_str.substring(0, json_str.length()-1);
							// end of children
			json_str += CLOSE_BOX;
		}
		return json_str;
	 }

	/**
	 * Generating the JSON string for a Bridge array object (Array<E>[])
	 *
	 * @param Bridges Array object
	 *
	 * @return JSON string
	*/

	public String generateJSON_Array(Array<E> br_array){

		StringBuilder nodes_JSON = new StringBuilder();
		StringBuilder links_JSON = new StringBuilder();
		Validation.validate_ADT_size(br_array.getSize());
		br_array.getDimensions(arrayDims);
		for (int i = 0; i < br_array.getSize(); i++){
			if (br_array.getValue(i) != null){
				nodes_JSON.append(br_array.getValue(i).getRepresentation() + ",");
			}
		}
					// note: there are no links for an array, order is 
					// by index
		return build_JSON(nodes_JSON, links_JSON);
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
	 * Generating the JSON string of a singly linked list, given a set of nodes 
	 * and links 
	 *
	 * @param nodes
	 * @param links
	 *
	 * @return JSON string
	 *
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
			SLelement<E>  element = nodes.removeFirst();
			Validation.validate_ADT_size(i);
			map.put(Integer.parseInt(element.getIdentifier()), i++);
			nodes_JSON.append(element.getRepresentation() + COMMA);
		}
							// get the JSON string for links
		String str = links_JSON.toString();
		while (!links.isEmpty()){
							// get the link
			SLelement<E> parent = links.removeFirst();
			SLelement<E> child = links.removeFirst();
	
							// get the link properties
			LinkVisualizer lvis = parent.getLinkVisualizer(child);
			if (lvis != null) {
				str += OPEN_CURLY + lvis.getLinkProperties() + COMMA;

//				for (Entry<String,String> entry:lvis.getProperties().entrySet())
//																{
//					str += 	QUOTE + entry.getKey() + QUOTE 
//								  +	COLON +
//							QUOTE + entry.getValue() + QUOTE + COMMA;
//				}
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
	 * Generating the JSON string of a multi list, given a set of nodes 
	 * and links. 
	 *
	 * @param nodes
	 * @param links
	 *
	 * @return JSON string
	 *
	 */
	public String generateJSON_ML (LinkedList<MLelement<E>> nodes, 
					LinkedList<MLelement<E>> links){
	
		HashMap<Integer,Integer> map = new HashMap<>();
		StringBuilder nodes_JSON = new StringBuilder();
		StringBuilder links_JSON = new StringBuilder();
	
							// map the nodes to a sequence of ids, 0...N-1
							// then get the JSON string for nodes
		int i = 0;
		while (!nodes.isEmpty()){
			MLelement<E>  element = nodes.removeFirst();
			Validation.validate_ADT_size(i);
			map.put(Integer.parseInt(element.getIdentifier()), i++);
			nodes_JSON.append(element.getRepresentation() + COMMA);
		}
							// get the JSON string for links
		String str = links_JSON.toString();
		while (!links.isEmpty()){
							// get the link
			SLelement<E> parent = links.removeFirst();
			SLelement<E> child = links.removeFirst();
	
							// get the link properties
			LinkVisualizer lvis = parent.getLinkVisualizer(child);
			if (lvis != null) {
				str += OPEN_CURLY + lvis.getLinkProperties() + COMMA;

//				for (Entry<String,String> entry:lvis.getProperties().entrySet())
//																{
//					str += 	QUOTE + entry.getKey() + QUOTE 
//								  +	COLON +
//							QUOTE + entry.getValue() + QUOTE + COMMA;
//				}
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
			DLelement<E>  element = nodes.removeFirst();
			map.put(Integer.parseInt(element.getIdentifier()), i++);
			Validation.validate_ADT_size(i);
			nodes_JSON.append(element.getRepresentation() + COMMA);
		}
							// get the JSON string for links
		String str = links_JSON.toString();
		while (!links.isEmpty()){
							// get the link
			DLelement<E> parent = links.removeFirst();
			DLelement<E> child = links.removeFirst();
							// get the link properties
			LinkVisualizer lvis = parent.getLinkVisualizer(child);
			if (lvis != null) {
				str+= OPEN_CURLY + lvis.getLinkProperties() + COMMA;
/*
				for (Entry<String,String> entry : lvis.getProperties().entrySet()){
					str += 	QUOTE + entry.getKey() + QUOTE 
								  +	COLON +
							QUOTE + entry.getValue() + QUOTE + COMMA;
				}
*/
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
/*
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
*/
	
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
			if (lvis != null) {
				str+= OPEN_CURLY + lvis.getLinkProperties() + COMMA;
/*
				for (Entry<String,String> entry : lvis.getProperties().entrySet()){
					str += String.format("\"%s\": \"%s\", ", entry.getKey(), 
									entry.getValue());
				}
*/
			}
	
			str += String.format("\"source\":%s,", 
					map.get(Integer.parseInt(parent.getIdentifier())));
							// get the edge terminating vertex
			str += String.format("\"target\":%s", 
					map.get(Integer.parseInt(child.getIdentifier())) );
	
			str += CLOSE_CURLY + COMMA;
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
			.append("\"title\": \""+ title +"\",")
			.append("\"description\": \""+ description +"\",")
			.append((visualizerType == "Array") 
					?  "\"dims\":[" + arrayDims[0] + "," + arrayDims[1] + "," 
						+ arrayDims[2] + "],"
					: "")
			.append("\"nodes\": [").append(trimComma(nodes_JSON))
			.append( "],")
			.append("\"links\": [").append(trimComma(links_JSON))
			.append("]")
			.append("}");

		if (visualizeJSON())
			System.out.println(s.toString());

		return s.toString();	
	}

	public String build_JSON(String tree_json) {


		String final_json = 
			OPEN_CURLY + 
				QUOTE + "name" + QUOTE + COLON + 
					QUOTE + "edu.uncc.cs.bridges" + QUOTE + COMMA +
				QUOTE + "version" + QUOTE + COLON + 
					QUOTE + "0.4.0" + QUOTE + COMMA +
				QUOTE + "visual" + QUOTE + COLON + 
					QUOTE + adt_names.get(visualizerType)+ QUOTE + COMMA +
				QUOTE + "title" + QUOTE + COLON + 
					QUOTE + title + QUOTE + COMMA +
				QUOTE + "description" + QUOTE + COLON + 
					QUOTE + description + QUOTE + COMMA +
				QUOTE + "nodes" + QUOTE + COLON + OPEN_CURLY + tree_json + CLOSE_CURLY +
			CLOSE_CURLY;
			
		if (visualizeJSON())
			System.out.println(final_json);

		return final_json;
	}
	public static StringBuilder trimComma(StringBuilder in) {
		if (in.length() > 0 && in.charAt(in.length()-1) == ',')
			in.deleteCharAt(in.length()-1);

		return in;
	}
}
