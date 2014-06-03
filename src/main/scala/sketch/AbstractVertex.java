package sketch;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Abstract graph vertex, with an unspecified adjacency construct.
 * Whatever is used must be compatible with Map. The default implementation
 * Vertex uses a HashMap. You are free to use any you prefer.
 * 
 * This class provides delegates for its {@link links} member for convenience.
 */
abstract public class AbstractVertex implements Map<String, Edge> {
	/**
	 * This is the string by which this AbstractVertex should be found.
	 * This is not a label; it includes provider information as well.
	 */
	final String identifier;
	
	/**
	 * Links, with properties other than just target Node.
	 */
	public Map<String, Edge> links;
	
	/**
	 * Visualization properties for this Node.
	 */
	Map<String, String> properties;
	
	/**
	 * Create an AbstractVertex
	 * @param identifier  The unique, final id for this vertex. 
	 */
	public AbstractVertex(String identifier) {
		this.identifier = identifier;
	}
	
	/// Accessors and mutators for visualization properties follow
	
	/**
	 * Get the String associated with this Vertex.
	 * This is not a label; it includes provider information as well.
	 * @return
	 */
	public String getIdentifier() {
		return identifier;
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
	 * Take a node by it's identifier string and get it's shape
	 * @return "square" or "circle"
	 */
	public String getShape() {
		String prop = properties.get("shape");
		if (prop == null) {
			return "circle";
		} else {
			return prop;
		}
	}
	
	/**
	 * Take a node by it's identifier string and get it's shape
	 * @param shape "Circle" or "Square"
	 */
	public void setShape(String shape) {
		Validation.validateShape(shape);
		properties.put("shape", shape);
	};
	
	/**
	 * Take a node by it's identifier string and get it's node color
	 * @param node  Node identifier
	 * @returns  Diameter of the node
	 */
	public double getSize() {
		String prop = properties.get("size");
		if (prop == null)
			return 1.0;
		else
			return Double.parseDouble(prop);
	}
	
	/**
	 * Take a node by it's identifier string and get it's node color
	 * @param pixels  Diameter of the node
	 */
	public void setSize(double pixels) {
		properties.put("opacity", Double.toString(pixels));
	}
	
	/**
	 * Get the node's current opacity
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
	 * Set the node opacity
	 * 0.0 is invisible
	 * 1.0 is opaque
	 * @param opacity  Alpha, in range [0.0, 1.0]
	 */
	public void setOpacity(double opacity) {
		properties.put("opacity", Double.toString(opacity));
	}
	
	/// Hash code and equals: implements map, but it only uses this.identifier

	/**
	 * Get the hash code for this AbstractVertex.
	 * The code is only based on the identifier.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	/**
	 * Find whether two AbstractVertex's are the same.
	 * Based only on the identifier.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractVertex other = (AbstractVertex) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}
	
	
}
