package sketch2;

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
interface GraphVisualizer {
	/**
	 * This visualizer is actually an abstract graph; students could use it as
	 * part of their homework. So this can be public.
	 */
	public Map<String, AbstractVertex> nodes;
}
