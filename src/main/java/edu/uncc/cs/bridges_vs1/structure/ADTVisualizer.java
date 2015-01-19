package edu.uncc.cs.bridges_vs1.structure;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.uncc.cs.bridges_vs1.validation.InvalidValueException;
import edu.uncc.cs.bridges_vs1.network.*;

public class ADTVisualizer<E> {
	//public LinkedHashMap<Element<Value, T>, String> LList;
	public String visualizerType;
	public String visualizerIdentifier;
	public final Map<String, String> ADT_TYPE = new HashMap <String, String>(){{
		put("graphVis","graph");
		put("stackVis","stack");
		put("queueVis","queue");
		put("treeVis","tree");
		put("llist", "llist");
		put("Dllist", "Dllist");
		}};
	public Map<String, String> linkProperties =  new HashMap<String, String>(){{
															put("color","black");
															put("opacity","1.0");
															put("weight","1.0");
															put("width","1.0");
															}}; 
	public HashMap<Element<E>, HashMap<String, Element<E>>> mapOfLinks;
	public HashMap<String, SLelement<E>> adjacencyList;
	//public Map<Element<E>, Element<E> []> arrayOfLinks;
	public Element<E> [] adtArray;
	protected int arrayIndex;
	
	/**
	 * constructor using the superclass Object
	 * when using this constructor the default ADT structure is graph 
	 * @throws Exception 
	 */														
	public ADTVisualizer() throws Exception{
		super();
		this.visualizerType =ADT_TYPE.get("graphVis");
		//validateType(new Object());
	}
	
	public ADTVisualizer(HashMap<Element<E>, HashMap<String, Element<E>>> mapOfLinks){
		super();
		this.mapOfLinks = mapOfLinks;
	}
	
	/**
	 * @param adtArray
	 */
	public ADTVisualizer(Element<E>[] adtArray) {
		super();
		this.adtArray = adtArray;
	}
	
	/**
	 * Any other object type sent to the ADTVisualizer will throw an exception
	 * example sending a String will throw an error
	 * @param type
	 * @throws Exception
	 */
	public ADTVisualizer(Object type) throws Exception{
		validateType(type);
	}
	
	public void validateType(Object type) throws Exception{
		//Class<? extends Object> aClass = type.getClass();
		//System.out.println(aClass.getSimpleName());
		if (type.getClass().getSimpleName().equals("HashMap") ||
			type.getClass().getSimpleName().equals("Element<>[]"))
			;
		else if (type.getClass().getSimpleName().equals("Object")){
			throw new Exception("ADT structure undefined. Expected a "
					+ " HashMap or an Array object. Ex: ADTVisualizer<Tweet>[] vis = new ADTVisualizer<Tweet>(new Element<Tweet>[100]);");
		}
		else{
			throw new Exception("Invalid type " +type.getClass().getSimpleName() + "'. Expected a "
					+ " HashMap or an Array object.");
		}
	}
	
	public String getVisualizerType() {
		return visualizerType;
	}

	/**
	 * This method sets the visualizer type
	 * @param visualizerType
	 * @throws Exception
	 */
	public void setVisualizerType(String visualizerType) throws Exception {
		
		if (ADT_TYPE.keySet().contains(visualizerType))
			this.visualizerType = ADT_TYPE.get(visualizerType);
		else if (visualizerType==null){
			throw new Exception("Invalid value '" + visualizerType + "'. Expected "
					+ " a string value: graph, llist, stack, tree, or queue.");
		}
		this.visualizerType = visualizerType;
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
	
	/**
	 * Adding one element to the ADT
	 * @param e - holds the reference to the element
	 * @throws Exception 
	 */
	public void add(Element<E> e) throws Exception{
		if (e == null)
			throw new Exception("A null element cannot be added to the ADT.");
		if (mapOfLinks != null)
			mapOfLinks.put(e, new HashMap<String, Element<E>>());
		else if (adtArray != null)
			adtArray[currIndexArray()] = e;
		else{
			throw new Exception("The ADT must have an initialized HashMap or Array.");
		}
	}
	
	public void setLink(Element<E> source, Element<E> target) throws Exception{
		if (source.equals(target)){
			throw new Exception("When setting a link between elements, the source and the target must be different.");
		}
		else if (source != null && target != null){
			mapOfLinks.get(source).put(target.getIdentifier(), target);
			//System.out.println(mapOfLinks.get(source));
		}
		else{
			throw new NullPointerException("One or both elements are null." +
											"Cannot set a link between 2 elements if one or both are null.");
		} 
			
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public int currIndexArray() throws Exception{
		if (adtArray != null)
			return currIndexArray();
		else{
			throw new Exception("The array must be initialized.");
		}	
	}
	
	/**
	 * This method creates a JSON representation of the graph 
	 * @param e - is the starting element(node)
	 * @return - this method returns the JSON 
	 */
	public String getGraphRepresentation() {
		StringBuilder nodes = new StringBuilder();
		StringBuilder links = new StringBuilder();
		Map<String, Integer> element_to_index = new HashMap<>();
		
		int i=0;
		for (Entry<Element<E>, HashMap<String, Element<E>>> element: mapOfLinks.entrySet()) {
			nodes.append(element.getKey().getRepresentation() + ",");
			element_to_index.put(element.getKey().getIdentifier(), i);
			i++;
		}
		// Manage link properties
		for (Entry<Element<E>, HashMap<String, Element<E>>> element: mapOfLinks.entrySet()) {
			//System.out.println("Element entryset: "+element.getKey().getIdentifier()+"  "+element.getValue().entrySet().toString());
			if (!element.getValue().entrySet().isEmpty()){
				for(Entry<String, Element<E>> target: element.getValue().entrySet()) {
					links.append(getLinkRepresentation(element.getKey(), target.getValue(), element_to_index) + ",");
				}
			}
		}
		
		return "{"
				+ "\"name\": \"edu.uncc.cs.bridges\","
				+ "\"version\": \"0.4.0\","
				+ "\"visual\": \""+visualizerType+"\","
				+ "\"nodes\": [" + DataFormatter.trimComma(nodes) + "],"
				+ "\"links\": [" + DataFormatter.trimComma(links) + "]"
				+ "}";
	}
	
	/**
	 * This method creates a JSON representation of the graph 
	 * @param e - is the starting element(node)
	 * @return - this method returns the JSON 
	 */
	public String getGraphLRepresentation() {
		StringBuilder nodes = new StringBuilder();
		StringBuilder links = new StringBuilder();
		Map<String, Integer> element_to_index = new HashMap<>();
		
		int i=0;
		for (Entry<String, SLelement<E>> element: adjacencyList.entrySet()) {
			nodes.append(element.getValue().getRepresentation() + ",");
			element_to_index.put(element.getValue().getIdentifier(), i);
			i++;
		}
		// Manage link properties
		for (Entry<String, SLelement<E>> elementList: adjacencyList.entrySet()){
			SLelement<E> currElement = elementList.getValue();
			if (currElement.getNext()!=null)
			if (currElement.getNext()!=null){
				SLelement<E> target = currElement.getNext();
				while(target!=null){
					links.append(getLinkRepresentation(currElement, target, element_to_index) + ",");
					target=target.getNext();
				}
			}
		}
		return "{"
				+ "\"name\": \"edu.uncc.cs.bridges\","
				+ "\"version\": \"0.4.0\","
				+ "\"visual\": \""+visualizerType+"\","
				+ "\"nodes\": [" + DataFormatter.trimComma(nodes) + "],"
				+ "\"links\": [" + DataFormatter.trimComma(links) + "]"
				+ "}";
	}
	
	/**
	 * This method returns the JSON string of a singly linked list 
	 * @param e
	 * @return
	 */
	public
	<E> String getSLRepresentation(SLelement<E> e) {
		StringBuilder nodes = new StringBuilder();
		StringBuilder links = new StringBuilder();
		Map<String, Integer> element_to_index = new HashMap<>();
		
		int i=0;
		SLelement<E> anElement = e;
		do {
			// Manage vertex properties
			// Encapsulate in {}, and remove the trailing comma.
			if (anElement != null){
				nodes.append(anElement.getRepresentation() + ",");
				element_to_index.put(anElement.getIdentifier(), i);
				i++;
			}
			anElement = anElement.getNext();
		}while(anElement != null);
		
		anElement = e;
			// Manage link properties
			do {
				if (anElement.getNext() != null){
					links.append(getLinkRepresentation(anElement, anElement.getNext(), element_to_index) + ",");
				}	
					anElement = anElement.getNext();
				
			}while(anElement!=null);
			// Encapsulate in {}, and remove the trailing comma.	
		return "{"
				+ "\"name\": \"edu.uncc.cs.bridges\","
				+ "\"version\": \"0.4.0\","
				+ "\"visual\": \""+visualizerType+"\","
				+ "\"nodes\": [" + DataFormatter.trimComma(nodes) + "],"
				+ "\"links\": [" + DataFormatter.trimComma(links) + "]"
				+ "}";
	}
	
	/**
	 * This method returns the JSON string of a singly linked list 
	 * @param e
	 * @return
	 */
	public
	<E> String getDLRepresentation(DLelement<E> e) {
		StringBuilder nodes = new StringBuilder();
		StringBuilder links = new StringBuilder();
		Map<String, Integer> element_to_index = new HashMap<>();
		
		int i=0;
		DLelement<E> anElement = e;
		do {
			// Manage vertex properties
			// Encapsulate in {}, and remove the trailing comma.
			if (anElement != null){
				nodes.append(anElement.getRepresentation() + ",");
				element_to_index.put(anElement.getIdentifier(), i);
				i++;
			}
			anElement = anElement.getNext();
		}while(anElement != null);
		
		anElement = e;
			// Manage link properties
			do {
				if (anElement.getNext() != null){
					links.append(getLinkRepresentation(anElement, anElement.getNext(), element_to_index) + ",");
					
					links.append(getLinkRepresentation(anElement.getNext(), anElement.getNext().getPrev(), element_to_index) + ",");
				}	
					anElement = anElement.getNext();
				
			}while(anElement!=null);
			// Encapsulate in {}, and remove the trailing comma.	
		return "{"
				+ "\"name\": \"edu.uncc.cs.bridges\","
				+ "\"version\": \"0.4.0\","
				+ "\"visual\": \""+visualizerType+"\","
				+ "\"nodes\": [" + DataFormatter.trimComma(nodes) + "],"
				+ "\"links\": [" + DataFormatter.trimComma(links) + "]"
				+ "}";
	}
	
	
	/**
	 * 
	 * @param e1: source element
	 * @param e2: target element
	 * @return
	 */
	public <E> int compare(Element<E> e1, Element<E> e2){
		return e1.getIdentifier().compareTo(e2.getIdentifier());
	}
	
	
	/**
	 * This method is used to build a simple JSON for a specific link between 2 nodes 
	 * @param source element
	 * @param target element
	 * @param element_to_index holds the integer representation of the node
	 * @return
	 */
	//Changed the Map<Element<E>, Integer> element_to_index to Map<String, Integer> element_to_index
	//this allows for creating the Link representation using copy of SLelement objects
	//however the down side is there cannot be elements with the same String identifier
	<E> String getLinkRepresentation(Element<E>source, Element<E>target, Map<String, Integer> element_to_index) {
		String json = "{";
		if (source == null || target == null){
			return json = "";
		}
		else{
			for (Entry<String, String> entry : linkProperties.entrySet()) {
		
			json += String.format("\"%s\": \"%s\", ", entry.getKey(), entry.getValue());
			}
			json += String.format("\"source\":%s,", element_to_index.get(source.getIdentifier()));
			json += String.format("\"target\":%s", element_to_index.get(target.getIdentifier()));
			//System.out.println("json: " + json);
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
	public HashMap<String, SLelement<E>> getAdjacencyList() {
		return adjacencyList;
	}

	/**
	 * @param adjacencyList2 the adjacencyList to set
	 */
	public void setAdjacencyList(HashMap<String, SLelement<E>> adjacencyList2) {
		this.adjacencyList = adjacencyList2;
	}
	
}
