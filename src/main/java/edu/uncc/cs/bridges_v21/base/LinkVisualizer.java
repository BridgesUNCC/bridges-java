package bridges.base;

import bridges.validation.*;
import bridges.base.Element;
import bridges.base.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * @brief  This class maintains visual properties of links in all relevant
	data structures using BRIDGES.

 * This class is used to keep the visual properties of links 
 * in data structures such as linked lists, tree structures, and graphs.
 * Relevant attributes include color, thickness, opacity, line end point 
 * attributes.

 * Objects of this class are stored as properties of all Element subclasses.
 * Generally, a user will manipulate the LinkVisualizer returned from the
 * Element's getLinkVisualizer() method. 
 *
 * @author Mihai Mehedint, Kalpathi Subramanian
 * @date   2015, 1/16/17
 */

public class LinkVisualizer{
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

					// link color
	Color color;
					// link thickness
	double thickness;
					// link weight
	double weight;


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
	 * Set the thickness of the link in the Bridge Visualization in pixels
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
	 * Set the weight of the link, useful in graph algorithms, for example
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
	 * @return the size in pixels of the Element in the Bridges Visualization
	 */
	public double getWeight() {
		return weight;
	}

	/** Set the color of the link in the Bridges Visualization to "aColor".
	 * @param aColor the string reprsenting the color of the Element in 
	 *		the Bridges Visualization
	 */
	public void setColor(String aColor) {
		String col = aColor.toLowerCase();
                        // validates and returns a 4 component RGBA val
		if (col.equals("red"))
			color = new Color(255, 0, 0, 1.0f);
		else if(col.equals("green"))
			color = new Color(0, 255, 0, 1.0f);
		else if(col.equals("blue"))
			color = new Color(0, 0, 255, 1.0f);
		else if(col.equals("yellow"))
			color = new Color(255, 255, 0, 1.0f);
		else if(col.equals("magenta"))
			color = new Color(255, 0, 255, 1.0f);
		else if(col.equals("cyan"))
			color = new Color(0, 255, 255, 1.0f);
		else if(col.equals("white"))
			color = new Color(255, 255, 255, 1.0f);
		else if(col.equals("black"))
			color = new Color(0, 0, 0, 1.0f);
		else
			throw new InvalidValueException("Invalid color " + "'" + 
				aColor + "'" +"."
				+ " Only named primaries supported now \n");
    }

	/**
	 * Set the color of the link given RGBA components
	 *
	 * @param r, g, b, a components 
	 *
	 *	check to ensure they are in 0-255 range, else throw exception
	 * 
	 * @return the size in pixels of the Element in the Bridges Visualization
	 */
    public void setColor(Integer r, Integer g, Integer b, Float a)  throws
									InvalidValueException {
		color.setRed(r);
		color.setGreen(g);
		color.setBlue(b);
		color.setAlpha(a);
    }


	/**   Get the color of the link in the Bridges Visualization
	 *    @return the string reprsenting the color of the Element in the 
     *    Bridges Visualization
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
	 * 	Get the opacity of the link in the Bridges Visualization
	 * 	@return the opacity value (in the range 0.0-1.0
	 */
	public float getOpacity() {
		return (color.getAlpha());
	}

	public String getLinkProperties() {
		String link_props = 
			QUOTE + "color" + QUOTE + COLON + 
// TEMP
//			QUOTE + "cyan" + QUOTE + COMMA +
				OPEN_BOX + 
					Integer.toString(this.getColor().getRed()) + COMMA +
					Integer.toString(this.getColor().getGreen()) + COMMA +
					Integer.toString(this.getColor().getBlue()) + COMMA +
					Float.toString(this.getColor().getAlpha()) +
				CLOSE_BOX + COMMA + 
			QUOTE + "thickness" + QUOTE + COLON + 
				Double.toString(this.getThickness()) + COMMA +
			QUOTE + "name" + QUOTE + COLON + 
				Double.toString(this.getWeight());
			

		return link_props;
	}
}
