package sketch;

import java.util.HashMap;
import java.util.Map;

/**
 * Object Oriented Graph Visualizer
 * 
 * intended to be used like:
 * <tt>
 * OOVisual v = new OOVisual();<br>
 * v.add("actor/Tom Hanks");<br>
 * v.get("actor/Tom Hanks).setColor("green");<br>
 * Node n = v.get("actor/Tom Hanks");<br>
 * n.setColor("blue");<br>
 * n.setSize(8.0);<br>
 * n.connect("movie/Big").setDashArray("5,5,5");<br>
 * Link n = n.get("movie/Big").setWidth("5,5,5");<br>
 * v.has("actor/Thorin Oakinshield");<br>
 */
public class GraphVisualizer extends Visualizer {
	/**
	 * This visualizer is actually an abstract graph; students could use it as
	 * part of their homework. So this can be public.
	 */
	public Map<String, AbstractVertex> nodes = new HashMap<String, AbstractVertex>();
	
	/**
	 * Test membership of a node in the visualization by identifier.
	 * @param identifier
	 * @return whether the visualization has this node
	 * @
	 */
	public boolean has(String identifier) {
		return nodes.containsKey(identifier);
	}
	
	/**
	 * Test membership of a node in the visualization by object.
	 * @param identifier
	 * @return whether the visualization has this node
	 */
	public boolean has(AbstractVertex av) {
		return nodes.containsValue(av);
	}
	
	/**
	 * Get a Node in this visualization by its identifier.
	 * @param identifier
	 * @return the associated node, or null.
	 */
	public AbstractVertex get(String identifier) {return null;}
	
	/**
	 * Add a new Node to the visualizer by identifier
	 * @param identifier
	 * @return the associated Node object
	 */
	public AbstractVertex add(String identifier) {return null;}
	
	/**
	 * Add a new AbstractVertex to the visualizer
	 * @param identifier
	 * @return the associated Node object
	 */
	public AbstractVertex add(AbstractVertex av) {return null;}
	
	/**
	 * Remove a Node from the visualizer, along with its Links
	 * @param identifier 
	 * @return whether the Node existed before the removal
	 */
	public boolean remove(String identifier) {return false;}
	
	/**
	 * Internal API for exporting visualizer state
	 */
	@Override
	String getRepresentation() {return "";}
}
