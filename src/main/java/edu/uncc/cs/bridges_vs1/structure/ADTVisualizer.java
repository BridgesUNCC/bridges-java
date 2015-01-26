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
	private int MAX_LINKS_ALLOWED = 1000; //this holds the maximum number of edges allowed 
	private int MAX_ELEMENTS_ALLOWED = 1000; //this variable holds the maximum number of nodes allowed
	public final Map<String, String> ADT_TYPE = new HashMap <String, String>(){{
		put("graphVis","graph");
		put("graphVis","graphl");
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
	protected boolean visualizeJSON = false;
	
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
	
	/**
	 * This method returns the visualizer type as a string
	 * @return string visualizer
	 */
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
		try {
			if (mapOfLinks != null){
				if (source.equals(target)){
					throw new Exception("When setting a link between elements, the source and the target must be different.");
				}
				else if (source != null && target != null){
					mapOfLinks.get(source).put(target.getLabel(), target);
					System.out.println(mapOfLinks.get(source));
				}
				else{
					throw new NullPointerException("One or both elements are null." +
													"Cannot set a link between 2 elements if one or both are null.");
				}
			} else if (adjacencyList != null){
				adjacencyList.get(source.getLabel()).setNext((SLelement<E>)target);
			} else
				throw new IllegalArgumentException();
		} catch (Exception e) {
			e.printStackTrace();
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
		int i =0;
		for (Entry<Element<E>, HashMap<String, Element<E>>> element: mapOfLinks.entrySet()) {
			nodes.append(element.getKey().getRepresentation() + ",");
			element.getKey().setIdentifier(Integer.toString(i));
			i++;
			if (i == MAX_ELEMENTS_ALLOWED)
				try {
					throw new Exception ("No more than 1000 elements can be created!");
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		// Manage link properties
		for (Entry<Element<E>, HashMap<String, Element<E>>> element: mapOfLinks.entrySet()) {
			//System.out.println("Element entryset: "+element.getKey().getLabel()+"  "+element.getValue().entrySet().toString());
			System.out.println("Element entryset: "+element.getKey().getLabel()+" "+element.getKey().getIdentifier() +"  "+element.getValue().keySet());
			if (!element.getValue().entrySet().isEmpty()){
				for(Entry<String, Element<E>> target: element.getValue().entrySet()) {
					links.append(getLinkRepresentation(element.getKey(), target.getValue()) + ",");
					if (element.getValue().entrySet().size() > MAX_LINKS_ALLOWED)
						try {
							throw new Exception ("No more than 1000 links per element can be created!");
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			}
		}
		
		StringBuilder s = new StringBuilder();
		
		s.append("{").
		  append("\"name\": \"edu.uncc.cs.bridges\",").
		  append("\"version\": \"0.4.0\",").
		  append("\"visual\": \""+visualizerType+"\",").
		  append("\"nodes\": [").append(DataFormatter.trimComma(nodes)).append( "],").
		  append("\"links\": [").append(DataFormatter.trimComma(links)).append("]").
		  append("}");
		if (this.isVisualizeJSON())
			System.out.println(s.toString());
		return s.toString();
	}
	
	/**
	 * This method creates a JSON representation of the graph 
	 * @param e - is the starting element(node)
	 * @return - this method returns the JSON 
	 */
	public String getGraphLRepresentation() {
		StringBuilder nodes = new StringBuilder();
		StringBuilder links = new StringBuilder();
		
		int i=0;
		for (Entry<String, SLelement<E>> element: adjacencyList.entrySet()) {
			nodes.append(element.getValue().getRepresentation() + ",");
			element.getValue().setIdentifier(Integer.toString(i));
			i++;
			if (i == MAX_ELEMENTS_ALLOWED)
				try {
					throw new Exception ("No more than 1000 elements can be created!");
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		// Manage link properties
		for (Entry<String, SLelement<E>> elementList: adjacencyList.entrySet()){
			SLelement<E> currElement = elementList.getValue();
			if (currElement.getNext()!=null){
				SLelement<E> target = currElement.getNext();
				int j=0;
				while(target!=null){
					links.append(getLinkRepresentation(currElement, target) + ",");
					j++;
					if (j == MAX_LINKS_ALLOWED)
						try {
							throw new Exception ("No more than 1000 links per element can be created!");
						} catch (Exception e) {
							e.printStackTrace();
						}
					target=target.getNext();
				}
			}
		}
		StringBuilder s = new StringBuilder();
		
		s.append("{").
		  append("\"name\": \"edu.uncc.cs.bridges\",").
		  append("\"version\": \"0.4.0\",").
		  append("\"visual\": \""+visualizerType+"\",").
		  append("\"nodes\": [").append(DataFormatter.trimComma(nodes)).append( "],").
		  append("\"links\": [").append(DataFormatter.trimComma(links)).append("]").
		  append("}");
		if (this.isVisualizeJSON())
			System.out.println(s.toString());
		return s.toString();
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
		
		int i=0;
		SLelement<E> anElement = e;
		do {
			// Manage vertex properties
			// Encapsulate in {}, and remove the trailing comma.
			if (anElement != null){
				nodes.append(anElement.getRepresentation() + ",");
				anElement.setIdentifier(Integer.toString(i));
				i++;
				if (i == MAX_ELEMENTS_ALLOWED)
					try {
						throw new Exception ("No more than 1000 elements can be created!");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
			anElement = anElement.getNext();
		}while(anElement != null);
		
		int j=0;
		anElement = e;
			// Manage link properties
			do {
				if (anElement.getNext() != null){
					links.append(getLinkRepresentation(anElement, anElement.getNext()) + ",");
					j++;
					if (j == MAX_LINKS_ALLOWED)
						try {
							throw new Exception ("No more than 1000 links per element can be created!");
						} catch (Exception ex) {
							ex.printStackTrace();
						}
				}	
					anElement = anElement.getNext();
				
			}while(anElement!=null);
			// Encapsulate in {}, and remove the trailing comma.	
			StringBuilder s = new StringBuilder();
			
			s.append("{").
			  append("\"name\": \"edu.uncc.cs.bridges\",").
			  append("\"version\": \"0.4.0\",").
			  append("\"visual\": \""+visualizerType+"\",").
			  append("\"nodes\": [").append(DataFormatter.trimComma(nodes)).append( "],").
			  append("\"links\": [").append(DataFormatter.trimComma(links)).append("]").
			  append("}");
			if (this.isVisualizeJSON())
				System.out.println(s.toString());
			return s.toString();
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
		
		int i=0;
		DLelement<E> anElement = e;
		do {
			// Manage vertex properties
			// Encapsulate in {}, and remove the trailing comma.
			if (anElement != null){
				nodes.append(anElement.getRepresentation() + ",");
				anElement.setIdentifier(Integer.toString(i));
				i++;
				if (i == MAX_ELEMENTS_ALLOWED)
					try {
						throw new Exception ("No more than 1000 elements can be created!");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
			anElement = anElement.getNext();
		}while(anElement != null);
		
		int j=0;
		anElement = e;
			// Manage link properties
			do {
				if (anElement.getNext() != null){
					links.append(getLinkRepresentation(anElement, anElement.getNext()) + ",");
					j++;
				}
				if (anElement.getPrev() != null){
					links.append(getLinkRepresentation(anElement, anElement.getPrev()) + ",");
					j++;
				}
				if (j == MAX_LINKS_ALLOWED)
					try {
						throw new Exception ("No more than 1000 links per element can be created!");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					anElement = anElement.getNext();
				
			}while(anElement!=null);
			// Encapsulate in {}, and remove the trailing comma.	
			StringBuilder s = new StringBuilder();
			
			s.append("{").
			  append("\"name\": \"edu.uncc.cs.bridges\",").
			  append("\"version\": \"0.4.0\",").
			  append("\"visual\": \""+visualizerType+"\",").
			  append("\"nodes\": [").append(DataFormatter.trimComma(nodes)).append( "],").
			  append("\"links\": [").append(DataFormatter.trimComma(links)).append("]").
			  append("}");
			if (this.isVisualizeJSON())
				System.out.println(s.toString());
			return s.toString();
	}
	
	/**
	 * This method returns the JSON string representation of the tree 
	 * made by using preorder traversal
	 * @param e
	 * @return
	 */
	public
	<E> String getTreeRepresentation(TreeElement<E> e) {
		StringBuilder nodes = new StringBuilder();
		StringBuilder links = new StringBuilder();
		preOrder(e);

		getTreeRepresentation(e, 0, nodes, links);
		//getTreeLinkRepresentation(e, 0, links);
		// Encapsulate in {}, and remove the trailing comma.	
					StringBuilder s = new StringBuilder();
					
					s.append("{").
					  append("\"name\": \"edu.uncc.cs.bridges\",").
					  append("\"version\": \"0.4.0\",").
					  append("\"visual\": \""+visualizerType+"\",").
					  append("\"nodes\": [").append(DataFormatter.trimComma(nodes)).append( "],").
					  append("\"links\": [").append(DataFormatter.trimComma(links)).
					  append("]").
					  append("}");
					if (this.isVisualizeJSON())
						System.out.println(s.toString());
					return s.toString();
	}
	
	/**
	 * This method returns the JSON containing the tree nodes without the links between the nodes 
	 * @param e
	 * @return
	 */
	private
	<E> StringBuilder getTreeRepresentation(TreeElement<E> e, int i, StringBuilder nodes, StringBuilder links) {

		TreeElement<E> anElement = e;
			// Manage vertex properties
			// Encapsulate in {}, and remove the trailing comma.
			if (anElement != null){
				nodes.append(anElement.getRepresentation() + ",");
				//anElement.setIdentifier(Integer.toString(i));
				//element_to_index.put(anElement.getLabel(), i);
				if (i == MAX_ELEMENTS_ALLOWED)
					try {
						throw new Exception ("No more than 1000 elements can be created!");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				if (e.getLeft() != null){
					links.append(getLinkRepresentation(e, e.getLeft()) + ",");
				}
				if (e.getRight() != null){
					links.append(getLinkRepresentation(e, e.getRight()) + ",");
				}
				if (i == MAX_LINKS_ALLOWED)
					try {
						throw new Exception ("No more than 1000 links between the tree elements can be created!");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
			else if (e == null)
				return null;
			getTreeRepresentation(e.getLeft(), i++, nodes, links);
			getTreeRepresentation(e.getRight(), i++, nodes, links);
			return nodes;
	}
	
	
	/**
	 * Creating a Element to Integer map using preorder tree traversal
	 * @param root
	 * @param element_to_index
	 * @param i
	 * @return
	 */
	 public <E> void preOrder(TreeElement<E> root){
		if (root == null)
			return;

		preOrder(root.getLeft());
		preOrder(root.getRight());
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
	<E> String getLinkRepresentation(Element<E>source, Element<E>target) {
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
			//System.out.println(target.toString());
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
	
}
