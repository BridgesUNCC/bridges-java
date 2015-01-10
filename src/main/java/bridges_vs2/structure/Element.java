package bridges_vs2.structure;

import java.util.Map.Entry;

import bridges_vs2.validation.InvalidValueException;

public class Element<E> {
	
	protected String identifier;
	protected ElementVisualizer visualizer;
	private E value;
	
	//check for identifier value if it is null 
	//check for the generic type to be one that exists
	//
	public Element(){
		super();
		this.visualizer = new ElementVisualizer();
	}
	
	/**
	 * the constructor
	 * @param identifier
	 * @param aTy
	 * @param val
	 */
	public Element (E val, String identifier
					){
		validateIdentifier(identifier);
		validateVal(val);
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
	 * @return the string identifier
	 */
	public String getIdentifier(){
		return identifier;
	}
	
	/**
	 * 
	 * @return the visualizer
	 */
	public ElementVisualizer getVisualizer(){
		return visualizer;
	}
	
	/**
	 * 
	 * @return
	 */
	public E getElement(){
		return this.value;
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setElement(E value){
		validateVal(value);
		this.value = value;
	}
	/**
	 * 
	 * @param value2
	 */
	private void validateVal(E value2) {
		if (value2 == null){
			throw new NullPointerException("Invalid value' " + value2 + "'. Expected"
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
		return e1.getIdentifier().compareTo(this.getIdentifier());
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
	
}
