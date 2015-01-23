package edu.uncc.cs.bridges_vs1.structure;

import java.util.Map.Entry;

import edu.uncc.cs.bridges_vs1.validation.InvalidValueException;

public class Element<E> {
	
	static Integer ids = 0;
	protected String caller;
	protected String identifier;
	protected ElementVisualizer visualizer;
	private E value;
	
	//check for identifier value if it is null 
	//check for the generic type to be one that exists
	//
	protected Element(){
		super();
		this.caller = ids.toString();
		ids++;
		this.visualizer = new ElementVisualizer();
	}
	
	/**
	 * the constructor
	 * @param identifier
	 * @param aTy
	 * @param val
	 */
	public Element (E val, String identifier){
		validateIdentifier(identifier);
		validateVal(val);
		this.caller = ids.toString();
		ids++;
		this.identifier = identifier;
		this.value = val;
		this.visualizer = new ElementVisualizer();
	}
	/**
	 * 
	 * @param identifier2
	 */
	private void validateIdentifier(String identifier2) {
		if (identifier2 == null){
			throw new NullPointerException("Invalid value' " + identifier2 + "'. Expected"
					+ " non null value.");
		}
		else if (identifier2.equals("")){
			throw new InvalidValueException("Invalid value' " + identifier2 + "'. Expected"
					+ " non empty string.");
		}
		else
			;
	}

	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	public Element (Element<E> original){
		this.identifier = new String(original.getIdentifier());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
	}
	
	/**
	 * this method returns the identifier
	 * @return the string identifier
	 */
	public String getIdentifier(){
		return identifier;
	}
	
	
	/**
	 * this method sets the string identifier
	 * @return void
	 */
	public void setIdentifier(String identifier){
		this.identifier = identifier;
	}
	
	/**
	 * Returns the Element's visualizer object
	 * @return the visualizer
	 */
	public ElementVisualizer getVisualizer(){
		return visualizer;
	}
	
	/**
	 * Returns the Element value
	 * @return element
	 */
	public E getElement(){
		return this.value;
	}
	
	/**
	 * Sets the element's value
	 * @param the ELement value
	 */
	public void setElement(E value){
		validateVal(value);
		this.value = value;
	}
	/**
	 * Validates the Element
	 * @param ELement value 
	 */
	private void validateVal(E value) {
		if (value == null){
			throw new NullPointerException("Invalid value' " + value + "'. Expected"
					+ " non null value.");
		}	
	}
	
	/**
	 * This method returns the name of the class
	 * @return
	 */
	public String getClassName(){
		return this.value.getClass().getName();
	}
	
	/**
	 * Comparison between 2 elements
	 * @param e1
	 * @return
	 */
	public int compare(Element<E> e1){
		if (e1.getCaller().compareTo(this.getCaller())==0)
			return e1.getIdentifier().compareTo(this.getIdentifier());
		else 
			return e1.getCaller().compareTo(this.getCaller());
	}
	
	/**
	 * Internal code for getting the properties of an AbstractVertex.
	 * 
	 * It produces (without the spaces or newlines):
	 * <tt>
	 * {
	 *  "name": "Some identifier",
	 *  "other CSS properties like color": any_JSON_value
	 * }
	 * @returns the encoded JSON string
	 */
	public String getRepresentation(){
		String json = "{";
		for (Entry<String, String> entry : visualizer.properties.entrySet()) {
			json += String.format("\"%s\": \"%s\", ", entry.getKey(), entry.getValue());
		}
		json += String.format("\"name\": \"%s\"", identifier);
		return json + "}";
	}

	/**
	 * @return the caller
	 */
	public String getCaller() {
		return caller;
	}

	/**
	 * @param caller the caller to set
	 */
	protected void setCaller(String caller) {
		this.caller = caller;
	}
	
}
