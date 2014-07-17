package edu.uncc.cs.bridges;


import java.util.Collections;
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
	public Map<String, AbstractVertex> vertices = new HashMap<>();
	
	/**
	 * Internal API for exporting visualizer state
	 * 
	 * The end result should look like this:
	 * (the source code may have better formatting)
	 * <tt>
	 * {"name": "edu.uncc.cs.bridges",
	 * 	"version": "0.4.0",
	 * 	"visual": "graph",
	 * 	"nodes": [
	 *		NODE, NODE, .. NODE
	 *	],
	 *	"links": [
	 *		LINK, LINK, .. LINK
	 *	]
	 * }
	 * 
	 * Where NODE and LINK have basically the same syntax:
	 * 
	 * NODE = LINK = {
	 * 	"\w+": ANY_JSON_TYPE,
	 *  ..
	 * }
	 * Note how there is no trailing comma in lists.
	 * 
	 */
	@Override
	protected
	String getRepresentation() {
		StringBuilder nodes = new StringBuilder();
		StringBuilder links = new StringBuilder();
		Map<AbstractVertex, Integer> vertex_to_index = new HashMap<>();
		
		int i=0;
		for (AbstractVertex v : Bridge.sorted_values(vertices)) {
			// Manage vertex properties
			// Encapsulate in {}, and remove the trailing comma.
			nodes.append(v.getRepresentation() + ",");
			vertex_to_index.put(v, i);
			i++;
		}
		
		// You have to finish all the vertices before you can start any of the edges
		//  because otherwise you might meet a vertex without an index
		for (AbstractVertex v : Bridge.sorted_values(vertices)) {
			// Manage link properties
			Collections.sort(v.outgoing);
			for (AbstractEdge e : v.outgoing) {
				// Encapsulate in {}, and remove the trailing comma.
				links.append(e.getRepresentation(vertex_to_index) + ",");
			}
		}
		return "{"
				+ "\"name\": \"edu.uncc.cs.bridges\","
				+ "\"version\": \"0.4.0\","
				+ "\"visual\": \"graph\","
				+ "\"nodes\": [" + Bridge.trimComma(nodes) + "],"
				+ "\"links\": [" + Bridge.trimComma(links) + "]"
				+ "}";
	}
}
