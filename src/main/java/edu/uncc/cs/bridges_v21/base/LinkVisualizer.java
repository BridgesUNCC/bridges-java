package bridges.base;

import bridges.validation.*;
import bridges.base.Element;
import bridges.base.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

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
 *  <b>Supported Colors (by name)</b>: <p>
 *  "red", "green", "blue","yellow","cyan","magenta",
 *  "white",, "black", "orange",  "turquoise",  "maroon",  <br>
 *  "aquamarine",  "azure",  "beige", "brown",  "tan",  "olive",
 *  "chartreuse", "khaki", "bisque",  "coral", <br>
 *  "pink",  "lavender",  "purple",  "gold" <p>
 *
 *  <b> Color by RGBA Specification :</b>  Range: 0-255 for each component <p>
 *
 *  <b> Thickness: </b> Range : 0.0-50.0
 *
 *  <b> Opacity: </b> Range (0.0-1.0) </p>
 *
 *  @author Mihai Mehedint, Kalpathi Subramanian
 *
 *  @date 6/22/16, 1/16/17, 5/17/17
 *
 *  \sa Example Tutorial at <br>
 *  http://bridgesuncc.github.io/Hello_World_Tutorials/SLL.html
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

	private final String INSERT_STRING = "\\n";
	private final String DIVIDE_KEY = "(\r?\n)|(\n)|(\f)|(\r)|(%n)";


	public LinkVisualizer() {
		super();
		color = new Color(70, 130, 180, 1.0f);
		setThickness(1.0);
		setWeight(1.0);
		/*
			HashMap<String,String> properties;
				properties = new HashMap<String, String>() {{
							put("color", "black");
		        				put("opacity", "1.0");
		        				put("width", "1.0");
		        				put("weight", "1.0");
							}};
		*/
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
		this.label = arrangeLabel(label);
	}

	/**
	 * This method formats the label string using a predefine pattern (DIVIDE_KEY) and
	 * replaces the pattern with the string characters hold by the INSERT_STRING global
	 *	variable
	 *
	 * @param label  the input label string
	 *
	 * @return  the formatted label
	 */
	public String arrangeLabel(String label) {
		final Pattern myPattern = Pattern.compile(DIVIDE_KEY);
		Matcher match = myPattern.matcher(label);
		if (!match.find())
			return label;
		else {
			match.reset();
			StringBuffer str = new StringBuffer();
			while (match.find()) {
				match.appendReplacement(str, Matcher.quoteReplacement(INSERT_STRING));
			}
			match.appendTail(str);
			if (str.length() == 0)
				return label;
			else
				return label = str.toString();
		}
	}

	/**
	 * Set the thickness of the link in the Bridge Visualization in pixels; thickness
	 * shoudl be in the range 0-50.0
	 *
	 * @param thickness
	 *
	 */
	public void setThickness(double th) {

		Validation.validateThickness(th);
		thickness  = th;
	}

	/**
	 * Get the thickness of the link in the Bridges Visualiation
	 *
	 * @return the size in pixels of the Element in the Bridges Visualization
	 */
	public double getThickness() {
		return thickness;
	}

	/**
	 * Set the weight of the link, useful in graph algorithms, for example.
	 * weight value is user defined, and determined by the input graph specification.
	 *
	 * @param weight
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
	 *
	 * 	@param col_name the string reprsenting the color of the Element in
	 *		the Bridges Visualization; supported named colors are
	 *  	"red", "green", "blue", "yellow", "cyan", "magenta", "white", "black",
	 *  	"orange", "turquoise", "maroon", "aquamarine", "azure", "beige",
	 *  	"brown", "tan", "olive", "chartreuse", "khaki", "bisque", "coral",
	 *  	"pink", "lavender", "purple", "gold"
	 *
	 */
	public void setColor(String col_name) {

		String col = col_name.toLowerCase();
		// validates and returns a 4 component RGBA val
		int red, green, blue;
		float  alpha = 1.0f;

		switch (col_name) {
			case "red":
				red = 255;
				green = blue = 0;
				break;
			case "green":
				red = 0;
				green = 255;
				blue = 0;
				break;
			case "blue":
				red = 0;
				green = 0;
				blue = 255;
				break;
			case "yellow":
				red = 255;
				green = 255;
				blue = 0;
				break;
			case "cyan":
				red = 0;
				green = 255;
				blue = 255;
				break;
			case "magenta":
				red = 255;
				green = 0;
				blue = 255;
				break;
			case "white":
				red = 255;
				green = 255;
				blue = 255;
				break;
			case "black":
				red = 0;
				green = 0;
				blue = 0;
				break;
			case "orange":
				red = 255;
				green = 155;
				blue = 0;
				break;
			case "turquoise":
				red = 173;
				green = 234;
				blue = 234;
				break;
			case "maroon":
				red = 176;
				green = 48;
				blue = 96;
				break;
			case "aquamarine":
				red = 127;
				green = 255;
				blue = 212;
				break;
			case "azure":
				red = 240;
				green = 255;
				blue = 255;
				break;
			case "beige":
				red = 245;
				green = 245;
				blue = 220;
				break;
			case "brown":
				red = 166;
				green = 42;
				blue = 42;
				break;
			case "tan":
				red = 210;
				green = 180;
				blue = 140;
				break;
			case "olive":
				red = 128;
				green = 128;
				blue = 0;
				break;
			case "chartreuse":
				red = 127;
				green = 255;
				blue = 0;
				break;
			case "khaki":
				red = 240;
				green = 230;
				blue = 140;
				break;
			case "bisque":
				red = 255;
				green = 228;
				blue = 196;
				break;
			case "coral":
				red = 255;
				green = 127;
				blue = 0;
				break;
			case "pink":
				red = 255;
				green = 192;
				blue = 203;
				break;
			case "lavender":
				red = 230;
				green = 230;
				blue = 250;
				break;
			case "purple":
				red = 160;
				green = 32;
				blue = 240;
				break;
			case "gold":
				red = 255;
				green = 215;
				blue = 0;
				break;
			default:
				throw new InvalidValueException("Invalid color " + "'" +
					col_name + "'" + "."
					+ " Only named primaries supported now. \n");
		}
		color = new Color (red, green, blue, alpha);
	}

	/**
	 *
	 * 	Set the color of the link given RGBA components
	 *
	 * 	@param r, g, b, a components
	 *
	 *	check to ensure they are in 0-255 range, else throw exception
	 *
	 */
	public void setColor(Integer r, Integer g, Integer b, Float a)  throws
		InvalidValueException {
		color.setRed(r);
		color.setGreen(g);
		color.setBlue(b);
		color.setAlpha(a);
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
	 * Sets the opacity of the link in the Bridges Visualization
	 *
	 * @param opacity a float between 0 and 1 representing how transparent
	 *	the node
	 *            should be on the Bridges Visualization. 0 for invisible, 1 for
	 *            fully visible, a decimal between 0 and 1 for varying
	 *            transparency.
	 */
	public void setOpacity(float opacity) {
		color.setAlpha(opacity);
	}

	/**
	 *
	 * 	Get the opacity of the link in the Bridges Visualization
	 *
	 * 	@return the opacity value (in the range 0.0-1.0
	 *
	 */
	public float getOpacity() {
		return (color.getAlpha());
	}

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
		if(label != null && !label.isEmpty()) {
			link_props += COMMA +
			QUOTE + "label" + QUOTE + COLON +
			QUOTE + this.getLabel() + QUOTE;
		}

		return link_props;
	}
}
