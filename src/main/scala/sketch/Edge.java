package sketch;

import java.util.Map;

/**
 * Generic graph link, with visual components. 
 */
public class Edge {
	/**
	 * Visualization properties for this Link
	 * This could be made private.
	 */
	private Map<String, String> properties;
	
	/**
	 * Get the color
	 * @return Color as a String
	 */
	public String getColor() { return ""; }
	
	/**
	 * Set the color
	 * @param color Color as a String
	 */
	public String setColor(String color) {return "";}
	
	/**
	 * Get the dash array
	 * @return  CSS dash array, for example "5,10,5"
	 */
	public String getDash() {return "";}
	
	/**
	 * Set the dash array
	 * @param dash CSS dash array, for example "5,10,5"
	 */
	public void setDash(String dash) {}
	
	
	/**
	 * Get the thickness in pixels
	 * @returns Thickness in pixels
	 */
	public double getThickness() {return 1.0;}
	
	/**
	 * Set the thickness in pixels
	 * @param pixels  Thickness in pixels
	 */
	public void setThickness(double pixels) {}
	
	/**
	 * Get the edge's current opacity
	 * 0.0 is invisible
	 * 1.0 is opaque
	 * @returns  Alpha, in range [0.0, 1.0]
	 */
	public double getOpacity() {
		String prop = properties.get("opacity");
		if (prop == null)
			return 1.0;
		else
			return Double.parseDouble(prop);
	}
	
	/**
	 * Set the edge opacity
	 * 0.0 is invisible
	 * 1.0 is opaque
	 * @param opacity  Alpha, in range [0.0, 1.0]
	 */
	public void setOpacity(double opacity) {
		properties.put("opacity", Double.toString(opacity));
	}
}
