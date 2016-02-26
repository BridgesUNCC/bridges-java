package bridges.base;
import bridges.validation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * This class is used to store the visualization elements on the for the Bridges
 * Visualiztion, including the color, shape, opacity, and size of the node.
 * <p>
 * Objects of this class are stored as properties of all Element subclasses.
 * Generally, you will manipulating the ElementVisualizer returned from the
 * Element getVisualizer() method, and then call the setVisualizer() method on
 * the Element after changes have been made.
 */

public class ElementVisualizer {
	// Visualization properties for this Node.
	Map<String, String> properties = new HashMap<String, String>() {
		{
			put("color", "green");
			put("opacity", "1.0");
			put("size", "10.0");
			put("shape", "circle");
			put ("key", "");
			put ("locationX", "0.0");
			put ("locationY", "0.0");
		}
	};

	/**
	 * Construct an ElementVisualizer with the default visualization settings.
	 * The default settings are color = green, opacity = 1.0, size = 10.0, shape
	 * = circle.
	 */
	public ElementVisualizer() {
		super();
	}

	/**
	 * Construct an ElementVisualizer with its color set to "aColor".
	 * 
	 * @param aColor
	 *            the string that represents one of the Bridges colors.
	 */
	public ElementVisualizer(String aColor) {
		super();
		setColor(aColor);
	}

	/**
	 * Construct an ElementVisualizer with its color set to "aColor" and shape
	 * set to "aShape".
	 * 
	 * @param aColor
	 *            the string that represents one of the Bridges colors.
	 * @param aShape
	 *            the string that represents one of the Bridges shapes
	 */
	public ElementVisualizer(String aColor, String aShape) {
		this(aColor);
		setShape(aShape);
	}

	/**
	 * Construct an ElementVisualizer with its size set to "size".
	 * 
	 * @param size
	 *            the double that represents the size in pixels of the Element
	 *            on the Bridges Visualization
	 */
	public ElementVisualizer(double size) {
		super();
		setSize(size);
	}

	/**
	 * Construct an ElementVisualizer with its color set to "aColor", its shape
	 * set to "aShape", its opacity set to "opacity" and size set to "size".
	 * 
	 * @param aColor
	 *            the string that represents one of the Bridges colors.
	 * @param aShape
	 *            the string that represents one of the Bridges shapes
	 * @param opacity
	 *            a double between 0 and 1 representing how transparent the node
	 *            should be on the Bridges Visualization. 0 for invisible, 1 for
	 *            fully visible, a decimal between 0 and 1 for varying
	 *            transparency.
	 * @param size
	 *            the double that represents the size of the Element on the
	 *            Bridges Visualization
	 */
	public ElementVisualizer(String aColor, String aShape, double opacity,
			double size) {
		this(aColor, aShape);

		setOpacity(opacity);
		setSize(size);
	}

	/**
	 * Construct a new ElementVisualizer with the same color, shape, opacity,
	 * and size as "v"
	 * 
	 * @param v
	 *            the ElementVisualizer whose settings you want to copy.
	 */
	public ElementVisualizer(ElementVisualizer v) {
		this(v.getColor(), v.getShape(), v.getOpacity(), v.getSize());

	}

	/**
	 * Set the size of the Element in the Bridge Visualization in pixels
	 * 
	 * @param size
	 *            the pixel size of the Element in the Bridges Visualization
	 */
	public void setSize(double size) {
		Validation.validateSize(size);
		properties.put("size", Double.toString(size));
	}

	/**
	 * Get the size of the Element in the Bridges Visualiation
	 * 
	 * @return the size in pixels of the Element in the Bridges Visualization
	 */
	public double getSize() {
		return Double.parseDouble(properties.get("size"));
	}

	/** Set the color of the Element in the Bridges Visualization to "aColor".
	 * @param aColor the string reprsenting the color of the Element in 
	 *  		the Bridges Visualization
	 */
	public String setColor(String aColor) {
		// this.aColor = aColor;
		aColor = aColor.toLowerCase();
		if (aColor == null || aColor.isEmpty()) {
			properties.put("color", aColor);
		} else {
			Validation.validateColor(aColor);
			properties.put("color", aColor);
		}
		return aColor;
	}

	/**Get the color of the Element in the Bridges Visualization
	 * @return the string reprsenting the color of the Element in the 
	 *			Bridges Visualization
	 */
	public String getColor() {
		return properties.get("color");
	}

	/**
	 * Get the shape of the Element in the Bridges Visualization.
	 * @return the string that represents the Element's shape in the 
	 * 			Bridges Visualization.
	 */
	public String getShape() {
		return properties.get("shape");
	}

	/**
	 * Sets the shape of the Element in the Bridges Visualization
	 * 
	 * @param aShape the string representing the shape of the Element in 
	 *			the Bridges Visualization
	 */
	public void setShape(String aShape) {
		// this.aShape = aShape;

		aShape = aShape.toLowerCase();
		Validation.validateShape(aShape);
		properties.put("shape", aShape);
	}

	/**
	 * Sets the opacity of the Element in the Bridges Visualization
	 * 
	 * @param opacity a double between 0 and 1 representing how 
			transparent the node
	 *      should be on the Bridges Visualization. 0 for invisible, 1 for
	 *      fully visible, a decimal between 0 and 1 for varying
	 *      transparency.
	 */
	public void setOpacity(double opacity) {
		Validation.validateOpacity(opacity);
		properties.put("opacity", Double.toString(opacity));
	}

	/** Get the opacity of the Element in the Bridges Visualization
	 * @return the opacity value
	 */
	public double getOpacity() {
		String prop = properties.get("opacity");
		if (prop == null)
			return 1.0;
		else
			return Double.parseDouble(properties.get("opacity"));
	}

	/**	Return the key of the BSTElement
	 *
	 * 	@return the key of this BSTElement
	 */
	String getKey() {
		return properties.get("key");
	}

	/**
	 *  Set the key of the BSTElement to key
	 *  @param key the key to set
	 **/
	void setKey(String key) {
		properties.put("key", key);
	}
	/**
	 *
	 *  Set the location(X,Y)  of the element - used for displaying elements 
	 *	in maps
	 *
	 * @param location the X,Y location(array of 2 doubles) to be set
	 *
     **/
	public
	void setLocation(double x, double y) {
		properties.put("locationX", Double.toString(x));
		properties.put("locationY", Double.toString(y));
	}

	/**
	 *
	 *  Get the location(X,Y)  of the element - used for displaying elements 
	 *	in maps
	 *
	 * @return location the X,Y location(array of 2 doubles) to be set
	 *
     **/
	String getLocation() {
		return properties.get("location");
	}

	/**
	 * The randomColor method selects a random color from the available list of
	 * colors found in Validation.java and sets the color of the current element
	 * 
	 * @return a color name as a string value
	 */
	public String randomColor() {
		Object[] a = Validation.COLOR_NAMES.toArray();
		return setColor(a[new Random().nextInt(a.length)].toString());
	}

}
