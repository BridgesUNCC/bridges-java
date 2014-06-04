package sketch;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic graph link, with visual components. 
 */
public class Edge {
	/**
	 * Visualization properties for this Link
	 * This could be made private.
	 */
	Map<String, String> properties = new HashMap<>();
	AbstractVertex source, destination;
	
	public Edge(AbstractVertex source, AbstractVertex destination) {
		this.source = source;
		this.destination = destination;
	}

	/**
	 * Get the color, according to CSS formats.
	 * By default, the color will be chosen at random.
	 * Setting the color to {@code null} or {@code ""} resets to defaults.
	 * 
	 * @param color Color as a String
	 * @see Validation#validateColor(String)
	 */
	public String getColor() {
		String prop = properties.get("color");
		if (prop == null) {
			return "";
		} else {
			return prop;
		}
	}
	
	/**
	 * Set the color, according to CSS formats.
	 * By default, the color will be chosen at random.
	 * Setting the color to {@code null} or {@code ""} resets to defaults.
	 * 
	 * @param color Color as a String
	 * @see Validation#validateColor(String)
	 */
	public void setColor(String color) {
		if (color == null || color.isEmpty()) {
			properties.remove("color");
		} else {
			Validation.validateColor(color);
			properties.put("color", color);
		}
	}
	
	/**
	 * Get the dash array
	 * @return  CSS dash array, for example "5,10,5"
	 */
	public String getDash() {
		String prop = properties.get("dash");
		if (prop == null) {
			return "";
		} else {
			return prop;
		}
	}
	
	/**
	 * Set the dash array, effectively choosing patterns of dots and dashes for
	 * edges.
	 * @param dash CSS dash array, for example "5,10,5"
	 */
	public void setDash(double[] dashes) {
		StringBuilder sb = new StringBuilder("");
		for (double dash : dashes) {
			sb.append(dash);
			sb.append(',');
		}
		sb.deleteCharAt(sb.length()-1);
		properties.put("dasharray", sb.toString());
	}
	
	
	/**
	 * Get the thickness in pixels
	 * @returns Thickness in pixels
	 */
	public double getThickness() {
		String prop = properties.get("thickness");
		if (prop == null) {
			return 1.0;
		} else {
			return Double.parseDouble(prop);
		}
	}
	
	/**
	 * Set the thickness in pixels
	 * @param pixels  Thickness in pixels
	 */
	public void setThickness(double pixels) {
		//TODO: Should we protect against NaN and Inf?
		properties.put("thickness", Double.toString(pixels));
	}
	
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
