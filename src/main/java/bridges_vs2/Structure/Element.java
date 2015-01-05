package bridges_vs2.Structure;

import java.util.Map.Entry;

public class Element<E> {
	
	protected String identifier;
	protected ElementVisualizer visualizer;
	private E value;
	
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
	public Element (String identifier,
					E val){
		this.identifier = identifier;
		this.value = val;
		this.visualizer = new ElementVisualizer();
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
	
	public E getElement(){
		return this.value;
	}
	
	public String getClassName(){
		return this.value.getClass().getName();
	}
	
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
