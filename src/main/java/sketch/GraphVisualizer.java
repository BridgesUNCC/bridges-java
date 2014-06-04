package sketch;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	 * @return a string with all but the last character
	 */
	private String trimLast(String in) {
		return in.substring(0, Math.max(in.length()-1, 0));
	}
	
	/**
	 * Internal API for exporting visualizer state
	 * 
	 * The end result should look like this:
	 * (the source code may have better formatting)
	 * <tt>
	 * {"name": "bridges",
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
	String getRepresentation() {
		String nodes = "";
		String links = "";
		for (AbstractVertex v : vertices.values()) {
			// Manage vertex properties
			String node_json = "";
			for (Entry<String, String> entry : v.properties.entrySet()) {
				node_json += String.format("%s:%s,", entry.getKey(), entry.getValue());
			}
			// Encapsulate in {}, and remove the trailing comma.
			nodes += String.format("{%s},", trimLast(node_json));
			
			// Manage link properties
			String link_json = "";
			for (Edge e : v.links.values()) {
				for (Entry<String, String> entry : e.properties.entrySet()) {
					link_json += String.format("{%s:%s}", entry.getKey(), entry.getValue());
				}
				// Encapsulate in {}, and remove the trailing comma.
				links += String.format("{%s},", trimLast(link_json));
			}
		}
		return "{"
				+ "\"name\": \"bridges\","
				+ "\"version\": \"0.4.0\","
				+ "\"visual\": \"graph\","
				+ "\"nodes\": [" + trimLast(nodes) + "]"
				+ "\"links\": [" + trimLast(links) + "]"
				+ "}";
	}
}
