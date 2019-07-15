package bridges.base;

import bridges.validation.*;
import bridges.base.Element;
import bridges.base.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;
import org.json.simple.JSONValue;

/**
 *  @brief This class maintains the visual attributes of links that join
 *  Bridges elements.
 *
 *  Visual properties include color, thickness, and opacity.
 *  Objects of this class are stored as part of the Element class.
 *  Generally, a user will manipulate the LinkVisualizer returned from the
 *  Element's getLinkVisualizer(Element it) method (which it is the Bridges element
 *	this element is linked to), and then set attributes using its methods. Links are
 *  utilized in all types of linked lists, tree and graph structures.
 *
 *  Supported attribute values are as follows:<p>
 *
 *  <b>Supported Colors (by name)</b>:
 *		See the Color class for the complete list.
 *
 *  <b> Color by RGBA Specification :</b>  Range: 0-255 for each component <p>
 *
 *  <b> Thickness: </b> Range : 0.0-50.0
 *
 *  <b> Opacity: </b> Range (0.0-1.0) </p>
 *
 *  @author Mihai Mehedint, Kalpathi Subramanian
 *
 *  @date 6/22/16, 1/16/17, 5/17/17, 7/12/19
 *
 *  \sa Example Tutorial at <br>
 *  http://bridgesuncc.github.io/tutorials/SLL.html
 *
 */

public class LinkVisualizer {
	// Link visualization properties for this element.
	// maintains mapping from the terminating vertex to its
	// visual properties
	// implemented as a hashmap mapping into properties, which
	// is als a hashmap, to keep the accesses constant time.
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

	// link label
	private String label;

	// link color
	private Color color;

	// link thickness
	private double thickness;

	// link weight
	private double weight;


	public LinkVisualizer() {
		super();
		color = new Color(70, 130, 180, 1.0f);
		setThickness(1.0);
		setWeight(1.0);
	}

	/**
	 * This method returns the existing value of the label fields
	 *
	 * @return the label of the Element; the label is typically displayed on BRIDGES
	 *          visualizations.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * This method sets the label
	 *
	 * @param label the link label to set
	 */
	public void setLabel(String label) {
		//		this.label = arrangeLabel(label);
		this.label = label;
	}

	/**
	 * Set the thickness of the link in the Bridge Visualization in pixels; thickness
	 * shoudl be in the range 0-50.0
	 *
	 * @param thickness of the link
	 *
	 */
	public void setThickness(double th) {

		Validation.validateThickness(th);
		thickness  = th;
	}

	/**
	 * Get the thickness of the link in the Bridges Visualiation
	 *
	 * @return the size in pixels of the  link
	 */
	public double getThickness() {
		return thickness;
	}

	/**
	 * Set the weight of the link, useful in graph algorithms, for example.
	 * weight value is user defined, and determined by the input graph specification.
	 *
	 * @param wt
	 *
	 */
	public void setWeight(double wt) {
		// is user defined so no checking
		weight = wt;
	}

	/**
	 * Get the weight of the link
	 *
	 * @return the stored edge weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 *
	 *	Set the color of the link in the Bridges Visualization to "aColor".
	 *	See the Color clas for a complete list of supported color names.
	 *
	 * 	@param col_name the string reprsenting the color of the link.
	 *
	 */
	public void setColor(String col_name) {
		color.setColor(col_name);
	}

	/**
	 * Set the color of the link from an existing Color object
	 * @param color Bridges Color object
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 *
	 * 	Set the color of the link given RGBA components;
	 *	0-255 range for R, G, B and 0-1.0 for opacity
	 *
	 * 	@param r red component
	 *	@param g green component
	 *	@param b blue component
	 *	@param a alpha (opacity)  component
	 *
	 *
	 */
	public void setColor(Integer r, Integer g, Integer b, Float a)  throws
		InvalidValueException {
		this.color = new Color(r, g, b, a);
	}

	/**
	 *
	 *	Get the color of the link in the Bridges Visualization
	 *
	 *	@return the Color object representing the color of the link
	 *
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the opacity of the link in the Bridges Visualization,
	 *  0 is fully transparent, 1 for fully opaque,
	 *
	 * @param opacity, a float in the range  0-1.0 representing
	 *		link transparency
	 */
	public void setOpacity(float opacity) {
		color.setAlpha(opacity);
	}

	/**
	 *
	 * 	Get the opacity of the link in the Bridges Visualization
	 *
	 * 	@return the opacity value (in the range 0.0-1.0)
	 *
	 */
	public float getOpacity() {
		return (color.getAlpha());
	}

	/**
	 *  Get link properties - used for building JSON representation
	 */
	public String getLinkProperties() {
		String link_props =
			QUOTE + "color" + QUOTE + COLON +
			OPEN_BOX +
			Integer.toString(this.getColor().getRed()) + COMMA +
			Integer.toString(this.getColor().getGreen()) + COMMA +
			Integer.toString(this.getColor().getBlue()) + COMMA +
			Float.toString(this.getColor().getAlpha()) +
			CLOSE_BOX + COMMA +
			QUOTE + "thickness" + QUOTE + COLON +
			Double.toString(this.getThickness()) + COMMA +
			QUOTE + "weight" + QUOTE + COLON +
			Double.toString(this.getWeight());

		String label = this.getLabel();
		if (label != null && !label.isEmpty()) {
			link_props += COMMA +
				QUOTE + "label" + QUOTE + COLON +
				QUOTE + JSONValue.escape(this.getLabel()) + QUOTE;
		}

		return link_props;
	}
}
