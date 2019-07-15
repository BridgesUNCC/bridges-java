package bridges.base;

import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Vector;
import org.json.simple.JSONValue;

/**
 * @brief This is the main superclass in BRIDGES for  deriving a number of
 * 	objects used  in building arrays, lists, trees and graph data structures.

 *  SLelement, DLelement, CircSLelement, CircDLelement, TreeElement, BinTreeElement,
 *	BSTElement, CircSLelement, CircDLelement, AVLTreeElement are all subclasses
 *  (see class hierarchy above).  Element contains  two
 *	visualizer objects (ElementVisualizer, LinkVisualizer) for specifying
 *	visual attributes for nodes and links respectively. It also contains a label that
 *	that can be displayed in BRIDGES visualizations.
 *
 *  All the tutorials under
 *
 *	http://bridgesuncc.github.io/Hello_World_Tutorials/Overview.html
 *
 *  illustrate examples of using different types of Element objects and how to
 *	manipulate their visual attributes.
 *
 * @author Mihai Mehedint, Kalpathi Subramanian
 *
 * @param generic <E>  Elements are defined with an application specific  generic
 *	parameter, that is defined by the user. These can be any legal Java type
 *	and manipulated using setValue()/getValue() methods.
 *	For more information on Java generics, see
 *	for example, https://docs.oracle.com/javase/tutorial/java/generics/types.html
 *
 */

public class Element<E> extends DataStruct {

	static 	Integer ids = 0;
	private String label;
	private String identifier;
	private ElementVisualizer visualizer;
	private HashMap<Element<E>, LinkVisualizer>  lvisualizer;
	private E value;

	/**
	 *  Get the Element type name
	 *	@return the element type (string)
	 */
	public String getDataStructType() {
		return "Element";
	}

	/**
	 * Element constructor
	 * creates an ElementVisualizer object
	 * sets a unique identifier for the current Element
	 * normally used from subclasses
	 */
	public Element() {
		super();
		this.identifier = ids.toString();
		this.label = "";
		ids++;
		this.setVisualizer(new ElementVisualizer());
		this.lvisualizer  = new HashMap<Element<E>, LinkVisualizer>();
	}

	/**
	 * the constructor of Element
	 *
	 * @param val generic parameter value used to construct Element
	 */
	public Element (E val) {
		this();
		// here we need to check for null values until the
		// server will accept JSON containing both
		// identifiers(always not NUll)
		validateVal(val);
		this.setValue(val);
	}

	/**
	 * the constructor of Element
	 *
	 * @param label the string that is visible on the Bridges Visualization
	 * @param val generic parameter value used to construct Element
	 */
	public Element (String label, E val) {
		this(val);
		this.setLabel(label);
	}

	/**
	 * performing deep copy of an element when needed
	 *
	 * @param original the Element that is to be copied
	 */
	public Element (Element<E> original) {
		this.identifier = ids.toString();
		ids++;
		this.label = new String(original.getLabel());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
		this.lvisualizer  = new HashMap<Element<E>, LinkVisualizer> ();
		this.setValue(original.getValue());
	}

	/**
	 * this method returns the element's unique identifier
	 *
	 * @return the string identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Set the size of the Element in the Bridge Visualization (in pixel units)
	 *
	 * @param sz the pixel size of the Element in the Bridges Visualization.
	 *	Must be in [1:50].
	 */
	public void setSize(double sz) {
		this.visualizer.setSize(sz);
	}


	/**
	 *  Set the color to "col"
	 *  @param col The color of the element. See the Color class for details
	 */
	public void setColor(Color col) {
		this.visualizer.setColor(col);
	}

	/**
		Get the current color of the element
	 *	@return The color of the element
	 */
	public Color getColor() {
		return this.visualizer.getColor();
	}

	/**
	 *	set opacity of element - use the 4th color component
	 *
	 *  @param opacity
	 */
	public void setOpacity(float opacity) {
		this.visualizer.setOpacity(opacity);
	}

	/**
	 *	get opacity of element
	 *
	 *	@return current opacity of element (0-1 range)
	 */
	public double getOpacity() {
		return this.visualizer.getOpacity();
	}
	/**
	 * Sets the shape of the Element in the Bridges Visualization. Supported
	 * 		shapes include "star", "circle", "square", "diamond", "cross",
	 *		"triangle", "wye".
	 *
	 * @param aShape the string representing the shape of the Element in
	 *			the Bridges Visualization
	 */
	public void setShape(String aShape) {
		this.visualizer.setShape(aShape);
	}

	/**
	 * Returns the Element's visualizer object
	 *
	 * @return the visualizer object
	 */
	public ElementVisualizer getVisualizer() {
		return visualizer;
	}

	/**
	 * This method sets the visualizer object for the current
	 * element object
	 *
	 * @param visualizer the visualizer to set
	 */
	public void setVisualizer(ElementVisualizer visualizer) {
		this.visualizer = visualizer;
	}

	/**
	 * Returns the Element's link visualizer object
	 *
	 * The link visualizer object links this element to another element, which
	 * is specified by the argument to this method. This method is typically used
	 * to set the visual attributes of the links, such as in graphs or binary tree
	 * structures.
	 *
	 * @parm Element el -- the element terminating the link
	 *
	 * @return the link visualizer
	 */
	public LinkVisualizer getLinkVisualizer(Element<E> el) {
		// if this is the first time, must create the
		// link visualizer
		if (lvisualizer.get(el) == null)
			lvisualizer.put(el, new LinkVisualizer() );

		return lvisualizer.get(el);
	}

	/**
	 *	Sets the link from this element to a new incoming element
	 *
	 *	@param el the element to be linked to.
	 *
	 */
	protected void setLinkVisualizer(Element<E> el) {
		lvisualizer.put(el, new LinkVisualizer() );
	}

	/**
	 *	Removes the link from this element to a new incoming element
	 *
	 *	@param el the terminating element of the link
	 *
	 */
	protected void removeLinkVisualizer(Element<E> el) {
		lvisualizer.remove(el);
	}

	/**
	 * Validates the Element's value when the Element is created
	 * A non null value is expected
	 * this will be unnecessary after we modify the server
	 * @param <E> value
	 */
	protected void validateVal(E value) {
		try {
			if (value == null) {
				throw new NullPointerException(
					"\nInvalid value set to Element<E> '" + value + "'. Expected"
					+ " non null E value.\n");
			}
			else if (value.getClass().getCanonicalName().isEmpty()) {
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
	public String getClassName() {
		return this.value.getClass().getName();
	}

	/**
	 * Comparison between 2 elements
	 * @param e1 the Element to compare this with
	 * @return 0 if the 2 elements have the same label and the same identifier
	 * and a nonZero integer otherwise
	 */
	public int compareTo(Element<E> e1) {
		if (e1 == null)
			throw new NullPointerException("The object you are comparing to is null.");
		return this.getLabel().compareTo(e1.getLabel());
	}

	/**
	 * This method compares 2 Elements
	 * @param e1
	 * @return boolean
	 */
	public boolean equals(Element<E> e1) {
		if (e1 == null)
			throw new NullPointerException("The object you are comparing to is null.");
		return this.getLabel().equals(e1.getLabel()) &&
			this.getIdentifier().equals(e1.getIdentifier()) &&
			this.getValue().equals(e1.getValue()) &&
			this.getVisualizer().equals(e1.getVisualizer());
	}


	/**
	 * Get the JSON representation of the element's propertis
	 *
	 * @returns the encoded JSON string
	 */
	public String getElementRepresentation() {

		// first get all the attributes common to all
		// elements; assumes location is a fundamental
		// attribute that may or may not be used
		String json_str = OPEN_CURLY +
			QUOTE + "name" + QUOTE + COLON + QUOTE + JSONValue.escape(label) + QUOTE + COMMA +
			QUOTE + "shape" + QUOTE + COLON +
			QUOTE + visualizer.getShape() + QUOTE + COMMA +
			QUOTE + "size" + QUOTE + COLON +
			Double.toString(visualizer.getSize()) + COMMA +
			QUOTE + "color" + QUOTE + COLON +
			OPEN_BOX +
			Integer.toString(visualizer.getColor().getRed()) + COMMA +
			Integer.toString(visualizer.getColor().getGreen()) + COMMA +
			Integer.toString(visualizer.getColor().getBlue()) + COMMA +
			Float.toString(visualizer.getColor().getAlpha()) +
			CLOSE_BOX;
		// only include location if it was set by user
		// check against default values
		Boolean loc_flag =
			!((visualizer.getLocationX() == Double.POSITIVE_INFINITY) ||
				(visualizer.getLocationY() == Double.POSITIVE_INFINITY));
		if (loc_flag)
			json_str += COMMA + QUOTE + "location" + QUOTE + COLON +
				OPEN_BOX +
				Double.toString(visualizer.getLocationX()) + COMMA +
				Double.toString(visualizer.getLocationY()) +
				CLOSE_BOX;

		json_str += CLOSE_CURLY;

		return json_str;
	}

	public String getDataStructureRepresentation() {
		return null;
	}

	/**
	 *
	 *	Get  the link visualizer representation, iterating through
	 *	the link properties
	 *
	 *	@param lv  link visualizer
	 *	@param src source node name
	 *	@param dest destination node name
	 *
	 */
	public String getLinkRepresentation(LinkVisualizer lv, String src,
		String dest) {

		return	OPEN_CURLY +
			lv.getLinkProperties() + COMMA +
			QUOTE + "source"    + QUOTE + COLON + src  + COMMA +
			QUOTE + "target"    + QUOTE + COLON + dest +
			CLOSE_CURLY;
	}

	/**
	 * This method returns the current label of the element
	 *
	 * @return the label of the Element; the label is displayed
	 *		on BRIDGES visualizations.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * This method sets the label
	 *
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * This method returns the generic object held in the element.
	 *
	 * @return the value
	 */
	public E getValue() {
		return value;
	}

	/**
	 * This method sets the generic object for  this element.
	 *
	 * @param value the value to set
	 */
	public void setValue(E value) {
		validateVal(value);
		this.value = value;
	}


	/**
	 *  Get element size
	 *	@return the size (in pixels) of the element
	 *
	 */
	public double getSize() {
		return visualizer.getSize();
	}
	/**
	 *  Set the color to "col"
	 *  See the Color class for a complete list of supported color names
	 *  @param color The color of the element
	 */
	public void setColor(String col) {
		visualizer.setColor(col);
	}

	/**
	 *	@return The shape of the element(one of CIRCLE,SQUARE,
	 *		DIAMOND,CROSS,TRI_DOWN,TRI_UP
	 */
	public String getShape() {
		return visualizer.getShape();
	}
	/**
	 * 	Set the location attributes of an element.
	 *
	 * 	@param locX X coordinate of the element location
	 * 	@param locY Y coordinate of the element location
	 */
	public void setLocation(double locX, double locY) {
		visualizer.setLocation(locX, locY);
	}

	/**
	 *	Get the X coordinate of the element's location
	 *	@return the X coordinate of the  element's location attribute
	 */
	public double getLocationX() {
		return visualizer.getLocationX();
	}
	/**
	 *	Get the Y coordinate of the element's location
	 *	@return the Y coordinate of the  element's location attribute
	 */
	public double getLocationY() {
		return visualizer.getLocationY();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Element [name=" + JSONValue.escape(label) + ", identifier=" + identifier
			+ ", visualizer=" + visualizer + ", value=" + value
			+ ", getIdentifier()=" + getIdentifier() + ", getVisualizer()="
			+ getVisualizer()
			+ ", getClassName()=" + getClassName()
			+ ", getElementRepresentation()=" + getElementRepresentation()
			+ ", getLabel()=" + JSONValue.escape(getLabel()) + ", getValue()=" + getValue()
			+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
			+ ", toString()=" + super.toString() + "]";
	}

}
