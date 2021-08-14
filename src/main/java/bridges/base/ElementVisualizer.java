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
 *	Element's getVisualizer() method (rather than creating this object directly),
 *	and then set attributes using its methods.
 *  Supported attributed values are as follows:<br>
 *
 *  <b>Color:</b> Supported colors by <b>name</b> using setColor():
 *		See the Color class for the complete list; colors are set by
 *		by RGBA Specification,  0-255 for each component, or using color names.
 *
 *	<b>	Supported Shapes:</b>
 *	"circle", "square", "diamond", "cross",
 *	"triangle", "star", "wye". set it with setShape() and getShape().
 *
 *	<b> Shape Size</b> : Range [0-50]. Set it with setSize() and getSize().
 *
 *	<b> Opacity: </b> Range [0.0 - 1.0]. Use it with setOpacity(), getOpacity().
 *
 *  <b> Location: </b> Use it with setLocation(), getLocationX(), and getLocationY().
 *
 *	@author Mihai Mehedint, Kalpathi Subramanian
 *
 *	@date 6/22/16, 1/7/17, 5/17/17, 7/14/19
 *
 *  \sa Example Tutorial (with singly linked list) at <br>
 *	https://bridgesuncc.github.io/tutorials/SinglyLinkedList.html
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

	// default properties
	private Map<String, String> properties = new HashMap<String, String>() {
		{
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
	 *	The default settings are color = [70, 130, 180, 1.0], opacity = 1.0,
	 *	size = 10.0, shape = circle.
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
	 * @brief Construct an ElementVisualizer with given color, shape, opacity, and size.
	 *
	 * @param aColor the string that represents one of the Bridges colors.
	 * @param aShape the string that represents one of the Bridges shapes
	 * @param opacity a double in [0;1] for varying transparency representing how transparent the node
	 *            should. 0 for invisible, 1 for fully visible/
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
	 * Get the size of the Element in the Bridges Visualization
	 *
	 * @return the size in pixels of the Element in the Bridges Visualization
	 *
	 */
	public double getSize() {
		return size;
	}

	/**
	 *  @brief Set the color of the Element in the Bridges Visualization.
	 *
	 *	@param col_name the string reprsenting the color of the Element in
	 *  	the Bridges Visualization; see the Color class for the list
	 * 		of supported colors
	 *
	 */
	public void setColor(String col_name) throws InvalidValueException {
		color.setColor(col_name);
	}

	/**
	 *	@brief Set the color of the Element in the Bridges Visualization given
	 *	RGBA components
	 *
	 *	@param r,g, b, a color components. r, g, b are in the [0-255] range, a in [0,1].
	 *
	 */
	public void setColor(Integer r, Integer g, Integer b, float a) {
		color.setRed(r);
		color.setGreen(g);
		color.setBlue(b);
		color.setAlpha(a);
	}

	/**
	 * @brief Set the color of the Element in the Bridges Visualization.
	 *
	 * @param c  Color to set.
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
	 * 	Sets the opacity of the Element in the Bridges Visualization (0.0-1.0)
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
	 * 	@brief Get the opacity of the Element in the Bridges Visualization
	 *	@return the opacity value
	 */
	public float getOpacity() {
		return (color.getAlpha());
	}

	/**
	 *  @brief Set the location (x, y)  of the element
	 *
	 *	@param x,y location to be set
	 */
	public void setLocation(double x, double y) {
		locationX = x;
		locationY = y;
	}

	/**
	 *  @brief Get the X coordinate position of the element
	 *
	 * @return location X coordinate
	 *
	 **/
	public double getLocationX() {
		return locationX;
	}

	/**
	 *  @brief Get the Y coordinate  position  of the element
	 *
	 * @return location Y coordinate
	 *
	 **/
	public double getLocationY() {
		return locationY;
	}
}
