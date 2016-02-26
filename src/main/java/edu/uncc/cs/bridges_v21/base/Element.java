package bridges.base;

import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

/**
 * This is the Superclass Element with SLelement, DLelement,
 * ArrayElement, TreeElement, BSTElement subclasses
 * it contains the Element Visualizer object
 * label field derived from the E (application data) value
 * an object of E data type: integer, string, Tweet, Actor, Movie, 
 * EarthquakeTweet
 * identifier field automatically generated
 * @author mihai
 *
 * @param generic <E>
 */


public class Element<E>{
	
	static Integer ids = 0;
	private String label;
	private String identifier;
	private ElementVisualizer visualizer;
	private HashMap<String, LinkVisualizer>  lvisualizer;
	private E value;
	private final int wordNumber = 0; //this is the number of pattern matches where the new string can be inserted; useful in case we insert line breaks at a desired number of characters
									// is the pattern is change to white space this index can be changed to 2 words to insert a 
									//line break every 2 words
	private final String INSERT_STRING = "\\n"; //this is the string value that replaces the pattern found in the label
	private final String DIVIDE_KEY ="(\r?\n)|(\n)|(\f)|(\r)|(%n)";    //for more complex patterns the key must be changed like so "((John) (.+?))" returns "John firstWordAfterJohn": John writes, John doe, John eats etc.
	//(\\w) matches any word (\\d) any digit (\\D) any non digit
												//(\\s) a white space (\\s*) zero or more whitespaces, (\\s+) one or more
	
	
	//public String INSERT_STRING = "\\n";
	//public String DIVIDE_KEY ="(\n)";  
	
	
	/**
	 * Element constructor
	 * creates an ElementVisualizer object
	 * sets a unique identifier for the current Element
	 * normally used from subclasses
	 */
	public Element(){
		super();
		this.identifier = ids.toString();
		this.label = "";
		ids++;
		this.setVisualizer(new ElementVisualizer());
		this.lvisualizer  = new HashMap<String, LinkVisualizer>();
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
	 * @param label the string that is visible on the Bridges Visualization
	 * @param val will be used to construct Element
	 */
	public Element (String label, E val){
		this(val);
		this.setLabel(label);
	}

	/**
	 * performing deep copy of an element when needed
	 * @param original the Element that is to be copied
	 */
	public Element (Element<E> original){
		this.identifier = ids.toString();
		ids++;
		this.label = new String(original.getLabel());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
		this.lvisualizer  = new HashMap<String, LinkVisualizer> ();
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
	public void setVisualizer(ElementVisualizer visualizer) {
		this.visualizer = visualizer;
	}

	/**
	 * Returns the Element's link visualizer object 
     * that is linked to element el
	 * @parm Element el -- the element terminating the link 
	 * @return the link visualizer
	 */
	public LinkVisualizer getLinkVisualizer(Element<E> el){
						// if this is the first time, must create the
						// link visualizer
		if (lvisualizer.get(el.getIdentifier()) == null)
			lvisualizer.put(el.getIdentifier(), new LinkVisualizer() ); 
		return lvisualizer.get(el.getIdentifier());
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
	 * @return the class name "Element"
	 */
	public String getClassName(){
		return this.value.getClass().getName();
	}
	
	/**
	 * Comparison between 2 elements
	 * @param e1 the Element to compare this with
	 * @return 0 if the 2 elements have the same label and the same identifier 
	 * and a nonZero integer otherwise
	 */
	public int compareTo(Element<E> e1){
		if (e1==null) 
			throw new NullPointerException("The object you are comparing to is null.");
		return this.getLabel().compareTo(e1.getLabel());
	}
	
	/**
	 * This method compares 2 Elements
	 * @param e1
	 * @return boolean
	 */
	public boolean equals(Element<E> e1) {
		if (e1==null) 
			throw new NullPointerException("The object you are comparing to is null.");	
		return this.getLabel().equals(e1.getLabel()) &&
				this.getIdentifier().equals(e1.getIdentifier()) &&
				this.getValue().equals(e1.getValue()) &&
				this.getVisualizer().equals(e1.getVisualizer());
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
		String locx = "0.0", locy = "0.0";
		String json = "{";
		for (Entry<String, String> entry : visualizer.properties.entrySet()) {
			if (entry.getKey() == "locationX")
				locx = entry.getValue();
			else if (entry.getKey() == "locationY")
				locy = entry.getValue();
			else
				json += String.format("\"%s\": \"%s\", ", entry.getKey(), 
									entry.getValue());
		}
							// add in the location attribute as an array
		json += String.format("\"location\": [ %s , %s ], ", locx, locy);
		json += String.format("\"name\": \"%s\"", label);
		return json + "}";
	}

	/**
	 * This method returns the existing value of the label fields
	 * @return the label of the Element that shows up on the Bridges Visualization
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * This method sets the label
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = arrangeLabel(label, wordNumber);
	}
	
	/**
	 * This method formats the label string using a predefine pattern (DIVIDE_KEY) and 
	 * replaces the pattern with the string characters hold by the INSERT_STRING global variable
	 * @param label
	 * @param wordNumber in very long strings in the case where the whitespace \\s is chosen as a key the wordNumber can be set 
	 * to replace the whitespace with a newline character \\n at a given number of words (every second or third word)
	 * The default value is 0. In most situations we want to replace all patterns found.
	 * for more complex patterns the key must be changed like so "((John) (.+?))" returns "John firstWordAfterJohn": John writes, John doe, John eats etc.
	 * (\\w) matches any word
	 * (\\s) one white space (\\s*) zero or more white spaces, (\\s+) one or more 
	 * 
	 * @return
	 */
	public String arrangeLabel(String label, int wordNumber){
		final Pattern myPattern = Pattern.compile(DIVIDE_KEY);
		Matcher match= myPattern.matcher(label);
		if (!match.find())
			return label;
		else{
			match.reset();
			int counter = -1;
			StringBuffer str = new StringBuffer();
			while(match.find()){
				counter++;
				if (counter == wordNumber){
					counter = -1;
					match.appendReplacement(str, Matcher.quoteReplacement(INSERT_STRING));
				}
			}
			match.appendTail(str);
			if (str.length()==0)
				return label;
			else
				return label = str.toString();
		}
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
		return "Element [name=" + label + ", identifier=" + identifier
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
