package sketch;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
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
	 * Links, with properties other than just target Node.
	 */
	public Map<String, Edge> links;
	
	/**
	 * Visualization properties for this Node.
	 */
	private Map<String, String> properties;
	
	/// Accessors and mutators for visualization properties follow

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
	
	/// Delegates for links follow.

	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		links.clear();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		return links.containsKey(key);
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value) {
		return links.containsValue(value);
	}

	/**
	 * @return
	 * @see java.util.Map#entrySet()
	 */
	public Set<Entry<String, Edge>> entrySet() {
		return links.entrySet();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Map#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return links.equals(o);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public Edge get(Object key) {
		return links.get(key);
	}

	/**
	 * @return
	 * @see java.util.Map#hashCode()
	 */
	public int hashCode() {
		return links.hashCode();
	}

	/**
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		return links.isEmpty();
	}

	/**
	 * @return
	 * @see java.util.Map#keySet()
	 */
	public Set<String> keySet() {
		return links.keySet();
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public Edge put(String key, Edge value) {
		return links.put(key, value);
	}

	/**
	 * @param m
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll(Map<? extends String, ? extends Edge> m) {
		links.putAll(m);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public Edge remove(Object key) {
		return links.remove(key);
	}

	/**
	 * @return
	 * @see java.util.Map#size()
	 */
	public int size() {
		return links.size();
	}

	/**
	 * @return
	 * @see java.util.Map#values()
	 */
	public Collection<Edge> values() {
		return links.values();
	}
	
	
//	/**
//	 * Return whether this Node has a Link to the specified Node
//	 * @param identifier
//	 * @return
//	 */
//	abstract public boolean isConnectedTo(String identifier);
//	
//	/**
//	 * Return whether this Node has a Link to the specified Node
//	 * @param identifier
//	 * @return
//	 */
//	abstract public boolean isConnectedTo(AbstractVertex identifier);
//	
//	/**
//	 * Connect this node to another
//	 * @param identifier  Target node
//	 */
//	abstract public void connect(String identifier);
//	
//	/**
//	 * Connect this node to another
//	 * @param target  Target node
//	 */
//	abstract public void connect(AbstractVertex target);
//	
//	
//	/**
//	 * Disconnect this node from another
//	 * @param identifier  Target node
//	 * @return whether a link was present before the removal
//	 */
//	abstract public boolean disconnect(String identifier);
//	
//	/**
//	 * Disconnect this node from another
//	 * @param terget  Target node
//	 * @return whether a link was present before the removal
//	 */
//	abstract public boolean disconnect(AbstractVertex target);
//	
//	/**
//	 * Get the Link to another node
//	 * @param identifier  Target node
//	 * @return the Link, or null
//	 */
//	abstract public Edge get(String identifier);
//	
//	
//	/**
//	 * Get the Link to another node
//	 * @param target  Target node
//	 * @return the Link, or null
//	 */
//	abstract public Edge get(AbstractVertex target);
//	
}
