package edu.uncc.cs.bridgesV2.base;

import java.util.Map.Entry;

/**
 * This is the Superclass Element with SLelement, DLelement,
 * ArrayElement, TreeElement, subclasses
 * it contains the Element Visualizer object
 * label field derived from the E (application data) value
 * an object of E data type: integer, string, Tweet, Actor, Movie, 
 * EarthquakeTweet
 * identifier field automatically generataed
 * @author mihai
 *
 * @param generic <E>
 */


public class Element<E>{
	
	private int MAX_ELEMENTS_SIZE = 1000;
	static Integer ids = 0;
	private String label;
	private String identifier;
	private ElementVisualizer visualizer;
	private E value;
	
	/**
	 * Element constructor
	 * creates an ElementVisualizer object
	 * sets a unique identifier for the current Element
	 * normally used from subclasses
	 */
	protected Element(){
		super();
		this.identifier = ids.toString();
		this.label = "";
		ids++;
		if (ids > MAX_ELEMENTS_SIZE)
		try {
			throw new Exception ("No more than 1000 elements can be created!");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		this.setVisualizer(new ElementVisualizer());
	}
	
	/**
	 * the constructor of Element
	 * @param val will be used to construct Element
	 */
	public Element (E val){
		this();	
					// here we need to check for null values until the 
					// server will accept JSON containing both 
					// identifiers(always not NUll)
		validateVal(val); 
		this.setValue(val);
	}
	
	/**
	 * the constructor of Element
	 * @param val will be used to construct Element
	 */
	public Element (String label, E val){
		this(val);
		this.setLabel(label);
	}

	/**
	 * performing deep copy of an element when needed
	 * @param original
	 */
	public Element (Element<E> original){
		this.identifier = ids.toString();
		ids++;
		if (ids > MAX_ELEMENTS_SIZE)
		try {
			throw new Exception ("No more than 1000 elements can be created!");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		this.label = new String(original.getLabel());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
		this.setValue(original.getValue());
	}

	/**
	 * this method returns the element's unique identifier
	 * @return the string identifier
	 */
	public String getIdentifier(){
		return identifier;
	}
	
	/**
	 * Returns the Element's visualizer object
	 * @return the visualizer
	 */
	public ElementVisualizer getVisualizer(){
		return visualizer;
	}
	
	/**
	 * This method sets the visualizer object for the current 
	 * element object
	 * @param visualizer the visualizer to set
	 */
	protected void setVisualizer(ElementVisualizer visualizer) {
		this.visualizer = visualizer;
	}

	/**
	 * Validates the Element's value when the Element is created
	 * A non null value is expected 
	 * this will be unnecessary after we modify the server
	 * @param ELement value 
	 */
	protected void validateVal(E value) {
		try{
			if (value == null){
				throw new NullPointerException(
				"\nInvalid value set to SLelement<E> '" + value + "'. Expected"
						+ " non null E value.\n");
			} else if (value.getClass().getCanonicalName().isEmpty()){
				throw new IllegalArgumentException(
					"\nThe argument is not a legal Element object!\n" +
					value.getClass().getCanonicalName());  
			}
			else;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns the name of the class
	 * @return "Element"
	 */
	public String getClassName(){
		return this.value.getClass().getName();
	}
	
	/**
	 * Comparison between 2 elements
	 * @param e1
	 * @return 0 if the 2 elements have the same label and the same identifier 
	 * and a nonZero integer otherwise
	 */
	public int compare(Element<E> e1){
		return e1.getLabel().compareTo(this.getLabel());
	}
	
	/**
	 * Internal code for getting the properties of the Element object.
	 * It produces (without the spaces or newlines):
	 * {
	 *  "name": "Some label",
	 *  "other CSS properties like color": any_JSON_value
	 * }
	 * @returns the encoded JSON string
	 */
	public String getRepresentation(){
		String json = "{";
		for (Entry<String, String> entry : visualizer.properties.entrySet()) {
			json += String.format("\"%s\": \"%s\", ", entry.getKey(), 
									entry.getValue());
		}
		json += String.format("\"label\": \"%s\"", label);
		return json + "}";
	}

	/**
	 * This method returns the existing value of the label fields
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * This method sets the label
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * this method returns the value E for the current Element
	 * @return the value
	 */
	public E getValue() {
		return value;
	}

	/**
	 * This method sets the value field to the E argument value
	 * @param value the value to set
	 */
	public void setValue(E value) {
		validateVal(value);
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Element [label=" + label + ", identifier=" + identifier
				+ ", visualizer=" + visualizer + ", value=" + value
				+ ", getIdentifier()=" + getIdentifier() + ", getVisualizer()="
				+ getVisualizer()
				+ ", getClassName()=" + getClassName()
				+ ", getRepresentation()=" + getRepresentation()
				+ ", getLabel()=" + getLabel() + ", getValue()=" + getValue()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
