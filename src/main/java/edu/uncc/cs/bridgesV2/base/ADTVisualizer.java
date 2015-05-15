package edu.uncc.cs.bridgesV2.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import edu.uncc.cs.bridgesV2.validation.Validation;
import edu.uncc.cs.bridgesV2.connect.*;
import edu.uncc.cs.bridgesV2.base.GraphList;

/** The Bridges object uses this class to keep track of the Visualization representation prior to passing the information to the Bridges Server.
 * <p>
 * An end user will generally not need to interact directly with this class.
 */

public class ADTVisualizer<E extends Comparable<? super E>> {
	//public LinkedHashMap<Element<Value, T>, String> LList;
	public String visualizerType;
	public String visualizerIdentifier;
	public final Map<String, String> ADT_TYPE = 	   //To update the nomenclature here
			new HashMap <String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("graph","graph");
				put("graphl","graphl");
				put("stack","stack");
				put("queue","queue");
				put("tree","tree");
				put("llist", "llist");
				put("Dllist", "Dllist");
				put("AList", "AList");
			}};
	public Map<String, String> linkProperties =  
			new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("color","black");
				put("opacity","1.0");
				put("weight","1.0");
				put("width","1.0");
			}}; 
	public HashMap<Element<E>, HashMap<String, Element<E>>> mapOfLinks;

	public HashMap<String, GraphList<E>> adjacencyList;

	public Element<E> [] adtArray;
	protected int arrayIndex;
	protected boolean visualizeJSON = false;
	
	/**
	 * constructor using the superclass Object
	 * when using this constructor the default ADT structure is graph 
	 * @throws Exception 
	 */														
	public ADTVisualizer(){
		super();
		this.visualizerType =ADT_TYPE.get("graphVis");
		//validateType(new Object());
	}
	
	/** Constructor allow visulizations of a graph.
	 * @param mapOfLinks the HashMap representing a set of Elements mapped to a mapping of strings and Elements
	 */
	public ADTVisualizer(HashMap<Element<E>, HashMap<String, Element<E>>> mapOfLinks){
		super();
		this.mapOfLinks = mapOfLinks;
	}
	
	/** Constructor allow visulizations of an array.
	 * @param adtArray the array of elements to visualize
	 */
	public ADTVisualizer(ArrayElement<E>[] adtArray) {
		super();
		this.adtArray = adtArray;
	}
	
	/**
	 * Any other object type sent to the ADTVisualizer will throw an exception
	 * example sending a String will throw an error
	 * @param type an object
	 * @throws Exception to indicate that an invalid object was sent to the ADTVisualizer
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
	 * @return one the of the strings represented in ADTVisualizer.ADT_TYPE that represents the type of Bridges Visualization (e.g. "graph", "llist", etc.)
	 */
	public String getVisualizerType() {
		return visualizerType;
	}

	/**
	 * This method sets the visualizer type
	 * @param visualizerType a string in ADTVisualizer.ADT_TYPE representing the type of Bridges Visualization (e.g. "graph", "llist", etc.)
	 * @throws Exception
	 */
	public void setVisualizerType(String visualizerType) {
		if (ADT_TYPE.keySet().contains(visualizerType))
			this.visualizerType = ADT_TYPE.get(visualizerType);
		else if (visualizerType==null){
			try {
				throw new NullPointerException("Invalid value '" + visualizerType + "'. Expected "
						+ " a string value: graph, llist, AList, stack, tree, or queue.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else{
			try {
				throw new IllegalArgumentException("Invalid value '" + visualizerType + "'. Expected "
						+ " a string value: graph, llist, AList, stack, tree, or queue.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setGraph() {
		this.visualizerType = ADT_TYPE.get("graphVis");
	}
	
	
	public void setTree() {
		this.visualizerType = ADT_TYPE.get("treeVis");
	}
	
	public void setStack() {
		this.visualizerType = ADT_TYPE.get("stackVis");
	}
	
	public void setQueue() {
		this.visualizerType = ADT_TYPE.get("queueVis");
	}
	
	public void setArray(){
		this.visualizerType = ADT_TYPE.get("arrayVis");
	}
	
/**
 * This method creates a JSON representation of the graph - 
 * Adjacency List Representation. 
 * @param Hashmap<String, SLelement<E> represents the list of vertices
 * each of which is a singly linked list.
 * @return - this method returns the JSON string
 */
public 
<E extends Comparable <? super E>>	String getGraphAdjList_Representation(HashMap<String, GraphList<E>> 
									graph_adj_list){
							// first gather the nodes and links by
							// traversing the graph
	LinkedList<SLelement<E>> nodes = new LinkedList<>(); 
	LinkedList<SLelement<E>> links_src = new LinkedList<>();
	LinkedList<SLelement<E>> links_dest = new LinkedList<>();

	for (Entry<String, GraphList<E>> element: graph_adj_list.entrySet()){
		if (element != null)  {
							//  get the graph nodes
			SLelement<E> src_vertex = (element.getValue()).getSourceVertex();
			nodes.push (src_vertex);
							//  get the graph links
			SLelement<Edge> list =  (element.getValue()).getAdjacencyList();
			while (list != null) {
				links_src.push(src_vertex);
							// look up the vertex corresponding to this edge
							// since only the nodes are used in the graph
							// for visualization. Edges in adjacency list
							// are for the graph reprentation only
				Edge e = list.getValue();
				SLelement<E> dest_vertex = graph_adj_list.get(e.getVertex()).getSourceVertex();
				links_dest.push(dest_vertex);
				list = list.getNext();
			}
		}
	}
						// use the singly linked list case to get the JSON!
	return generateJSON_Graph(nodes, links_src, links_dest);
}
	
/**
 * This method creates a JSON representation of the graph - Adj. Matrix Rep. 
 * @param e - is the starting element(node)
 * @return - this method returns the JSON 
 */
/*
public 
<E>	String getGraphAdjMatrix_Representation(HashMap<String,Element<E>> graph_adj_matrix){
							// first gather the nodes and links by
							// traversing the graph
	LinkedList<SLelement<E>> nodes = new LinkedList<>(); 
	LinkedList<SLelement<E>> links = new LinkedList<>();

							//  get the graph vertices
	for (Entry<String, Element<E>> element: graph_adj_matrix.entrySet()){
		SLelement sle = new SLelement(element.getLabel(), element.getValue());
		nodes.add(sle);
	}
}
*/

public
<E extends Comparable<? super E>> String getSLRepresentation(SLelement<E> first_element) {

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
public
<E extends Comparable <? super E>> String getDLRepresentation(DLelement<E> first_element) {

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

public String getALRepresentation() {
		
		// generate the JSON string
		String str =  generateJSON_AL();
		return str;
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
public void preOrder(TreeElement<E> root, LinkedList<TreeElement<E>> nodes, 
			 LinkedList<TreeElement<E>> links){

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

public 
<E> String generateJSON_AL(){
	
	StringBuilder nodes_JSON = new StringBuilder();
	StringBuilder links_JSON = new StringBuilder();
	for (int i = 0; i < adtArray.length; i++){
		if (adtArray[i]!=null){
			Validation.validate_ADT_size(i);
			nodes_JSON.append(adtArray[i].getRepresentation() + ",");
		}
		
	}
	
	// get the JSON string for links
	String str = links_JSON.toString();
	for (int j = 0; j < adtArray.length-1; j++){
		
		if(adtArray[j] != null && adtArray[j+1] != null){
			// get the link properties
			str+="{";
			for (Entry<String,String> entry : linkProperties.entrySet()) {
				str += String.format("\"%s\": \"%s\", ", entry.getKey(), 
									entry.getValue());
			}
			// get the link
			
			str += String.format("\"source\":%s,", 
					Integer.parseInt(adtArray[j].getIdentifier()));
			str += String.format("\"target\":%s", 
					Integer.parseInt(adtArray[j+1].getIdentifier()));
			str += "},";
		}
	}
	links_JSON.append(str);	
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
public 
<E extends Comparable <? super E>> String generateJSON_SLL (LinkedList<SLelement<E>> nodes, 
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
		nodes_JSON.append(element.getRepresentation() + ",");
	}
						// get the JSON string for links
	String str = links_JSON.toString();
	while (!links.isEmpty()){
						// get the link properties
		str+="{";
		for (Entry<String,String> entry : linkProperties.entrySet()) {
			str += String.format("\"%s\": \"%s\", ", entry.getKey(), 
								entry.getValue());
		}
						// get the link
		SLelement<E> child = links.pop();
		SLelement<E> parent = links.pop();
		str += String.format("\"source\":%s,", 
				map.get(Integer.parseInt(parent.getIdentifier())));
		str += String.format("\"target\":%s", 
				map.get(Integer.parseInt(child.getIdentifier())));

		str += "},";
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
public 
<E extends Comparable <? super E>> String generateJSON_DLL (LinkedList<DLelement<E>> nodes, 
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
		nodes_JSON.append(element.getRepresentation() + ",");
	}
						// get the JSON string for links
	String str = links_JSON.toString();
	while (!links.isEmpty()){
						// get the link properties
		str+="{";
		for (Entry<String,String> entry : linkProperties.entrySet()) {
			str += String.format("\"%s\": \"%s\", ", entry.getKey(), 
								entry.getValue());
		}
						// get the link
		DLelement<E> child = links.pop();
		DLelement<E> parent = links.pop();
		str += String.format("\"source\":%s,", 
				map.get(Integer.parseInt(parent.getIdentifier())));
		str += String.format("\"target\":%s", 
				map.get(Integer.parseInt(child.getIdentifier())));

		str += "},";
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
		nodes_JSON.append(element.getRepresentation() + ",");
	}
		 
	String str = links_JSON.toString();
	while (!links.isEmpty()){
		str+="{";
		for (Entry<String, String> entry : linkProperties.entrySet()) {
			str += String.format("\"%s\": \"%s\", ", entry.getKey(), 
								entry.getValue());
		}
		TreeElement<E> child = links.pop();
		TreeElement<E> parent = links.pop();
					
		str += String.format("\"source\":%s,", 
				map.get(Integer.parseInt(parent.getIdentifier())));
		str += String.format("\"target\":%s", 
				map.get(Integer.parseInt(child.getIdentifier())));
		str += "},";
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
public 
<E extends Comparable <? super E>> String generateJSON_Graph (LinkedList<SLelement<E>> nodes, LinkedList<SLelement<E>> links_src, LinkedList<SLelement<E>> links_dest){

	HashMap<Integer,Integer> map = new HashMap<>();
	StringBuilder nodes_JSON = new StringBuilder();
	StringBuilder links_JSON = new StringBuilder();

						// map the nodes to a sequence of ids, 0...N-1
						// then get the JSON string for nodes
	int i = 0;
	while (!nodes.isEmpty()){
		SLelement<E>  element = nodes.pop();
		map.put(Integer.parseInt(element.getIdentifier()), i++);
		Validation.validate_ADT_size(i);
		nodes_JSON.append(element.getRepresentation() + ",");
	}
						// get the JSON string for links
	String str = links_JSON.toString();
	while (!links_src.isEmpty()){
						// get the link properties
		str+="{";
		for (Entry<String,String> entry : linkProperties.entrySet()) {
			str += String.format("\"%s\": \"%s\", ", entry.getKey(), 
								entry.getValue());
		}
						// get the link
		SLelement<E> child = links_dest.pop();
		SLelement<E> parent = links_src.pop();
		str += String.format("\"source\":%s,", 
				map.get(Integer.parseInt(parent.getIdentifier())));
						// get the edge terminating vertex
		str += String.format("\"target\":%s", map.get(Integer.parseInt(child.getIdentifier())) );

		str += "},";
	}
	links_JSON.append(str);
	return build_JSON(nodes_JSON, links_JSON);	 
}
	 
	/**
	 * 
	 * @param e1: source element
	 * @param e2: target element
	 * @return
	 */
	public int compare(Element<E> e1, Element<E> e2){
		 if (e1.getIdentifier().compareTo(e2.getIdentifier())==0)
			 return e1.getLabel().compareTo(e2.getLabel());
		 else return e1.getIdentifier().compareTo(e2.getIdentifier());
		 
	}
		
	/**
	 * This method is used to build a simple JSON for a specific link between 2 nodes 
	 * @param source element
	 * @param target element
	 * @param element_to_index holds the integer representation of the node
	 * @return
	 */
	
	/**
	 * returns the JSON representation of a link between 2 elements
	 * @param source
	 * @param target
	 * @return
	 */
	<E extends Comparable<? super E>> String getLinkRepresentation(Element<E>source, Element<E>target) {
		String json = "{";
		if (source == null || target == null){
			return json + "}";
		}
		else{
			for (Entry<String, String> entry : linkProperties.entrySet()) {
		
			json += String.format("\"%s\": \"%s\", ", entry.getKey(), entry.getValue());
			}
			json += String.format("\"source\":%s,", source.getIdentifier());
			json += String.format("\"target\":%s", target.getIdentifier());
			return json + "}";
		}
	}

	/**
	 * @return the visualizerIdentifier
	 */
	protected String getVisualizerIdentifier() {
		return visualizerIdentifier;
	}

	/**
	 * @param visualizerIdentifier the visualizerIdentifier to set
	 */
	protected void setVisualizerIdentifier(String visualizerIdentifier) {
		this.visualizerIdentifier = visualizerIdentifier;
	}

	/**
	 * @return the mapOfLinks
	 */
	public HashMap<Element<E>, HashMap<String, Element<E>>> getMapOfLinks() {
		return mapOfLinks;
	}

	/**
	 * @param mapOfLinks the mapOfLinks to set
	 */
	public void setMapOfLinks(
			HashMap<Element<E>, HashMap<String, Element<E>>> mapOfLinks) {
		this.mapOfLinks = mapOfLinks;
	}

	/**
	 * @return the adjacencyList
	 */
	public HashMap<String, GraphList<E>> getAdjacencyList() {
		return adjacencyList;
	}

	/**
	 * @param adjacencyList2 the adjacencyList to set
	 */
	public void setAdjacencyList(HashMap<String, GraphList<E>> adjacencyList2) {
		this.adjacencyList = adjacencyList2;
	}
	
	public void setArray(Element<E> []e) {
		this.adtArray = e;
	}

	/**
	 * @return the visualizeJSON
	 */
	public boolean isVisualizeJSON() {
		return visualizeJSON;
	}

	/**
	 * @param visualizeJSON the visualizeJSON to set
	 */
	public void setVisualizeJSON(boolean visualizeJSON) {
		this.visualizeJSON = visualizeJSON;
	}
	
	/**
	 * This method builds the JSON string
	 * @param nodes_JSON the string builder object of existing nodes 
	 * @param links_JSON the string builder object of existing links between the nodes
	 * @return ADT's JSON
	 */
	public String build_JSON(StringBuilder nodes_JSON, StringBuilder links_JSON){
		StringBuilder s = new StringBuilder();
		
		s.append("{").append("\"name\": \"edu.uncc.cs.bridges\",")
			.append("\"version\": \"0.4.0\",")
			.append("\"visual\": \""+visualizerType+"\",")
			.append("\"nodes\": [").append(DataFormatter.trimComma(nodes_JSON))
			.append( "],")
			.append("\"links\": [").append(DataFormatter.trimComma(links_JSON))
			.append("]")
			.append("}");
		if (this.isVisualizeJSON())
			System.out.println(s.toString());

		return s.toString();	
	}	
}
