package bridges.base;

import bridges.validation.*;
import bridges.base.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * This class is used to keep the visual properties of links that art of 
 *  data structures such as linked lists, pointer based trees, link based
 *  graph representations, etc.  Relevant attributes include
 *  color, thickness, opacity, line end point attributes.
 * <p>
 * Objects of this class are stored as properties of all Element subclasses.
 * Generally, you will manipulating the LinkVisualizer returned from the
 * Element getLinkVisualizer() method, and then call the setLinkVisualizer() 
 * method on the Element after changes have been made.
 */

public class LinkVisualizer{
					// Link visualization properties for this element.
					// maintains mapping from the terminating vertex to its
					// visual properties
					// implemented as a hashmap mapping into properties, which
					// is als a hashmap, to keep the accesses constant time.
	HashMap<String,String> properties; 

	public LinkVisualizer() {
		super();
		properties = new HashMap<String, String>() {{
					put("color", "black");
        				put("opacity", "1.0");
        				put("width", "1.0");
        				put("weight", "1.0");
					}};
	}

	/**
	 * Set the thickness of the link in the Bridge Visualization in pixels
	 * 
	 * @param thickness
	 *            the pixel size of the Element in the Bridges Visualization
	 */
	public void setThickness(double thickness) {
		Validation.validateSize(thickness);
		properties.put("width", Double.toString(thickness));
	}

	/**
	 * Get the thickness of the link in the Bridges Visualiation
	 * 
	 * @return the size in pixels of the Element in the Bridges Visualization
	 */
	public double getThickness() {
		return Double.parseDouble(properties.get("width"));
	}

	/** Set the color of the link in the Bridges Visualization to "aColor".
	 * @param aColor the string reprsenting the color of the Element in the Bridges Visualization
	 */
	public void setColor(String aColor) {
		Validation.validateColor(aColor);
		properties.put("color", aColor);
	}

	/**   Get the color of the link in the Bridges Visualization
	 *    @return the string reprsenting the color of the Element in the 
     *    Bridges Visualization
	 */
	public String getColor() {
		return properties.get("color");
	}

	/**
	 * Sets the opacity of the link in the Bridges Visualization
	 * 
	 * @param opacity a double between 0 and 1 representing how transparent the node
	 *            should be on the Bridges Visualization. 0 for invisible, 1 for
	 *            fully visible, a decimal between 0 and 1 for varying
	 *            transparency.
	 */
	public void setOpacity(double opacity) {
		Validation.validateOpacity(opacity);
		properties.put("opacity", Double.toString(opacity));
	}

	/** Get the opacity of the link in the Bridges Visualization
	 * @return the opacity value
	 */
	public double getOpacity() {
		return Double.parseDouble( properties.get("opacity"));
	}

	public HashMap<String, String> getProperties() {
		return properties;
	}
}
