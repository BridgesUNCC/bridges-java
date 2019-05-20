package bridges.base;

import bridges.validation.*;
import bridges.base.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * 	@brief This class maintains the visual attributes of each BRIDGES
 * 	element.
 *
 *	Visual properties include color, shape, opacity, size and location.
 *	Objects of this class are stored as part of the Element class.
 *	Generally, a user will manipulate the ElementVisualizer returned from the
 *	Element's getVisualizer() method, and then set attributes using its methods.
 *  Supported attributed values are as follows:<br>
 *
 *  <b>Supported Colors (by name)</b>: <p>
 * 	"red", "green", "blue","yellow","cyan","magenta",
 *	"white",, "black", "orange",  "turquoise",  "maroon",  <br>
 *	"aquamarine",  "azure",  "beige", "brown",  "tan",  "olive",
 *	"chartreuse", "khaki", "bisque",  "coral", <br>
 *	"pink",  "lavender",  "purple",  "gold" <p>
 *
 *	<b> Color by RGBA Specification :</b>  Range: 0-255 for each component <p>
 *
 *	<b>Supported Shapes: </b>
 *
 *	"circle", "square", "diamond", "cross",
 *	"triangle", "star", "wye" <p>
 *
 *	<b> Shape Size</b> : Range (0-50) </p>
 *
 *	<b> Opacity: </b> Range (0.0-1.0) </p>
 *
 *	@author Mihai Mehedint, Kalpathi Subramanian
 *
 *	@date 6/22/16, 1/7/17, 5/17/17
 *
 *  \sa Example Tutorial at <br>
 *	http://bridgesuncc.github.io/Hello_World_Tutorials/SLL.html
 *
 */

public class ElementVisualizer {
	// Visualization properties for this Node.

	private Color color;
	private String shape = "circle",
				   key = "";
	private	double	locationX = Double.POSITIVE_INFINITY,
					locationY = Double.POSITIVE_INFINITY,
					size = 10.0;
	private float   opacity = 1.0f;

	private Map<String, String> properties = new HashMap<String, String>() {
		{
			//			put("color", "green");
			put("color", "[70, 130, 180, 1.0]");
			put("opacity", "1.0");
			put("size", "10.0");
			put("shape", "circle");
			put ("key", "");
			put ("locationX", String.valueOf(locationX));
			put ("locationY", String.valueOf(locationY));
		}
	};

	/**
	 *
	 *	Construct an ElementVisualizer with the default visualization settings.
	 *
	 *	The default settings are color = green, opacity = 1.0, size = 10.0,
	 *	shape = circle.
	 *
	 */
	public ElementVisualizer() {
		super();
		color = new Color(70, 130, 180, 1.0f);
	}

	/**
	 *
	 * Construct an ElementVisualizer with its color set to "aColor".
	 *
	 * @param aColor the string that represents one of the Bridges colors.
	 *
	 */
	public ElementVisualizer(String aColor) {
		super();
		setColor(aColor);
	}

	/**
	 *
	 * Construct an ElementVisualizer with its color set to "aColor" and shape
	 * set to "aShape".
	 *
	 * @param aColor the string that represents one of the Bridges colors.
	 * @param aShape the string that represents one of the Bridges shapes
	 *
	 */
	public ElementVisualizer(String aColor, String aShape) {
		setColor(aColor);
		setShape(aShape);
	}

	/**
	 *
	 * Construct an ElementVisualizer with its size set to "size".
	 *
	 * @param size the value that represents the size in pixels of the Bridges Element
	 *
	 */
	public ElementVisualizer(double size) {
		super();
		setSize(size);
	}

	/**
	 *
	 * Construct an ElementVisualizer with its color set to "aColor", its shape
	 * set to "aShape", its opacity set to "opacity" and size set to "size".
	 *
	 * @param aColor the string that represents one of the Bridges colors.
	 * @param aShape the string that represents one of the Bridges shapes
	 * @param opacity a double between 0 and 1 representing how transparent the node
	 *            should be on the Bridges Visualization. 0 for invisible, 1 for
	 *            fully visible, 0-1 for varying transparency.
	 * @param size
	 *            the double that represents the size of the Element on the
	 *            Bridges Visualization
	 */
	public ElementVisualizer(String aColor, String aShape, float opacity,
		double size) {
		setColor(aColor);
		setShape(aShape);
		setOpacity(opacity);
		setSize(size);
	}

	/**
	 *
	 * Construct a new ElementVisualizer with the same color, shape, opacity,
	 * and size as "v"
	 *
	 * @param v the ElementVisualizer whose settings you want to copy.
	 *
	 */
	public ElementVisualizer(ElementVisualizer v) {
		setColor(v.getColor());
		setShape(v.getShape());
		setOpacity(v.getOpacity());
		setSize(v.getSize());
	}

	/**
	 * Set the size of the Element in the Bridge Visualization (in pixel units)
	 *
	 * @param sz the pixel size of the Element in the Bridges Visualization
	 */
	public void setSize(double sz) {
		Validation.validateSize(sz);
		size = sz;
		properties.put("size", Double.toString(size));
	}

	/**
	 *
	 * Get the size of the Element in the Bridges Visualiation
	 *
	 * @return the size in pixels of the Element in the Bridges Visualization
	 *
	 */
	public double getSize() {
		return size;
	}

	/**
	 *
	 *	Set the color of the Element in the Bridges Visualization to "aColor".
	 *
	 * @param col_name the string reprsenting the color of the Element in
	 *  	the Bridges Visualization; supported named colors are
	 * 	"red", "green", "blue", "yellow", "cyan", "magenta", "white", "black",
	 *	"orange", "turquoise", "maroon", "aquamarine", "azure", "beige",
	 *	"brown", "tan", "olive", "chartreuse", "khaki", "bisque", "coral",
	 *	"pink", "lavender", "purple", "gold"
	 *
	 */
	public void setColor(String col_name) throws InvalidValueException {

		String col = col_name.toLowerCase();
		// validates and returns a 4 component RGBA val
		int red, green, blue;
		float  alpha = this.getOpacity();

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
					+ " Only named primaries supported now \n");
		}
		color = new Color (red, green, blue, alpha);
	}

	/**
	 *
	 *	Set the color of the Element in the Bridges Visualization given
	 *	RGBA components (0-255 range)
	 *
	 *	@param r,g, b, a color components
	 *
	 */
	public void setColor(Integer r, Integer g, Integer b, float a) {
		color.setRed(r);
		color.setGreen(g);
		color.setBlue(b);
		color.setAlpha(a);
	}

	/**
	 *
	 *	Set the color of the Element in the Bridges Visualization to "aColor".
	 *  	given a Color object
	 *
	 * @param col  Color object
	 *
	 */
	public void setColor(Color c)  {
		setColor(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}

	/**
	 *
	 *	Get the color of the Element in the Bridges Visualization
	 *
	 * 	@return Color object representing the color of the Element as
	 *	R,G,B,A components, each in the range 0-255
	 *
	 */
	public Color getColor() {
		return color;
	}

	/**
	 *
	 * Get the shape of the Element in the Bridges Visualization.
	 *
	 * @return the string that represents the Element's shape in the
	 * 			Bridges Visualization.
	 *
	 */
	public String getShape() {
		return shape;
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
		// this.aShape = aShape;

		aShape = aShape.toLowerCase();
		Validation.validateShape(aShape);
		shape = aShape;
	}

	/**
	 *
	 * 	Sets the opacity of the Element in the Bridges Visualization
	 *
	 * 	@param opacity a double between 0 and 1 representing how
	 *		transparent the node
	 *      should be on the Bridges Visualization. 0 for invisible, 1 for
	 *      fully visible, a decimal between 0 and 1 for varying
	 *      transparency.
	 */
	public void setOpacity(float opacity) {
		color.setAlpha(opacity);
	}

	/**
	 *
	 * 	Get the opacity of the Element in the Bridges Visualization
	 *	@return the opacity value
	 *
	 */
	public float getOpacity() {
		return (color.getAlpha());
	}

	/**
	 *  Set the location (x, y)  of the element - used for displaying elements
	 *	in maps
	 *
	 *	@param location the X,Y location(array of 2 doubles) to be set
	 */
	public void setLocation(double x, double y) {
		locationX = x;
		locationY = y;
	}

	/**
	 *
	 *  Get the X coordinate position of the element
	 *
	 * @return location X coordinate
	 *
	 **/
	double getLocationX() {
		return locationX;
	}

	/**
	 *
	 *  Get the Y coordinate  position  of the element
	 *
	 * @return location Y coordinate
	 *
	 **/
	double getLocationY() {
		return locationY;
	}


}
