package edu.uncc.cs.bridges.scaffold;
import java.util.HashMap;
import java.util.Iterator;

import edu.uncc.cs.bridges.Graph;

/**
 * Source code example for "A Practical Introduction to Data Structures and
 * Algorithm Analysis, 3rd Edition (Java)" by Clifford A. Shaffer Copyright
 * 2008-2011 by Clifford A. Shaffer
 */

// Adjacency list graph implementation
public class Graphl implements Graph{
	private static final int UNVISITED = 0;
	private static final int VISITED = 1;

	private HashMap<String, GraphList> vertex = new HashMap<>(); // The vertex list
	private HashMap<String, Integer> mark = new HashMap<>(); // The mark array
	private HashMap<String, String> color = new HashMap<>(); // The mark array

	/** Create an empty Adjacency List graph */
	public Graphl(){}
	
	/** Create a filled Adjacency List graph */
	public Graphl(String... strings) { // Constructor
		// init adj list for each
		for (String string : strings) {
			vertex.put(string, new GraphList());
		}

		// init as unvisited for each
		for (String string : strings) {
			setMark(string, UNVISITED);
		}
	}
	
	/** Either add a vertex, returning true, or skip it and return false
	 * 
	 * This method is absent in the book. This must be provided for or
	 * implemented by students.
	 * */
	public boolean add(String name) {
		if (name == null || vertex.containsKey(name)) {
			return false;
		} else {
			vertex.put(name, new GraphList());
			setMark(name, UNVISITED);
			return true;
		}	
	}
	
	/** @return whether this node is in the graph
	 * 
	 * This method is not part of the Graph interface.
	 * 
	 * This method is absent in the book. This _may_ be provided for or
	 * implemented by students.*/
	public boolean has(String name) {
		 return vertex.containsKey(name);
	}
	
	/** @return the GraphList, shifted to the link going from node i to node j
	 * If the link doesn't exist, return null. 
	 * 
	 * This method is absent in the book. Since it is not public, students do
	 * not need to implement it but may find it very useful to implement it
	 * as it will aid with other methods.
	 * */
	private GraphList getEdgeLink(String i, String j) {
		GraphList l = vertex.get(i);
		if (l == null || !has(j)) {
			// No edge
			return null;
		} else {
			/* 1: GraphList can't do a search.
			 * 2: Can't iterate either, because that's n^2.
			 *   - LList always moves back to 0 before moving to an index
			 * 3: next() doesn't tell if it reached the end.
			 *   - So increment and check to see if the position changed.
			 */
			int last_position = -1;
			l.moveToStart();
			while (last_position != l.currPos() // While still going forward ...
					&& !(
							l.getValue() != null // (Guard against calling vertex() on null)
							&& j.equals(l.getValue().vertex()) // .. and the destination of this edge is not correct
						)
					) {
				// Move forward in the list
				last_position = l.currPos();
				l.next();
			}
			// At this point we might have found it...
			// ... or we might have reached the end.
			if (l.getValue() != null && j.equals(l.getValue().vertex())) {
				// Yay! Tell the user the good news.
				return l;
			} else {
				// No edge.
				return null;
			}
		}
	}
	/** @return edge weight */
	public int getEdge(String i, String j) {
		GraphList l = getEdgeLink(i, j);
		if (l == null) {
			// No edge, no weight.
			return 0; 
		} else {
			return l.getValue().weight();
		}
	}
	
	/** Store edge weight */
	public void setEdge(String i, String j, int weight) {
		if (!(has(i) && has(j))) {
			// TODO: Could return false here
		} else {
			GraphList l = getEdgeLink(i, j);
			if (l == null) {
				// There is no link. But we know has(i) so we know we can
				//  create a link.
				Edge newEdge = new Edge(j, weight, "");
				vertex.get(i).insert(newEdge);
			} else {
				// The weight already exists. Overwrite it.
				Edge newEdge = new Edge(j, weight, l.getValue().color());
				l.remove();
				l.insert(newEdge);
			}
		}
	}

	// Set and get marks
	public void setMark(String key, int val) {
		if (has(key))
			mark.put(key, val);
	}

	public int getMark(String key) {
		Integer m = mark.get(key);
		return m == null ? 0 : m;
	}
	
	public void clearMarks() {
		mark.clear();
	}

	@Override
	public void DFS(String root) {

		// get an iterator over the vertices
		Iterator<String> keys = vertex.keySet().iterator();

		// mark every vertex as unvisited
		while (keys.hasNext()) {
			String string = keys.next();
			if (getMark(string) != UNVISITED)
				setMark(string, UNVISITED);
		}

		// reset iterator
		keys = vertex.keySet().iterator();

		// call helperDFS for unvisited vertices starting at root
		helperDFS(root);
		while (keys.hasNext()) {
			String string = keys.next();
			if (getMark(string) == UNVISITED)
				helperDFS(string);

		}

	}

	private void helperDFS(String root) {

		// visit and print out current node
		setMark(root, VISITED);
		System.out.println("Visited node: " + root);

		// visit connected nodes
		for (String connectedString = first(root); connectedString != null; connectedString = next(root)) {
			if (getMark(connectedString) == UNVISITED)
				helperDFS(connectedString);
		}
	}

	@Override
	/**
	 * Start iterating the neighbors of root.
	 * @param root  The center node.
	 * @returns the name of the first neighbor node, or null if there are none
	 * 
	 * This method is present in the book. Students may easily find solutions
	 * for this method.
	 */
	public String first(String root) {
		GraphList gl = vertex.get(root);
		if (gl == null) {
			// That vertex doesn't exist.
			return null;
		} else {
			gl.moveToStart();
			String first = null;
			if (gl.getValue() != null)
				first = gl.getValue().vertex();
			return first;
		}
	}

	@Override
	/**
	 * Continue iterating the neighbors of root.
	 * @param root  The center node.
	 * @returns the name of the next neighbor node, or null if there are no more
	 * 
	 * This method is present in the book. Students may easily find solutions
	 * for this method.
	 */
	public String next(String root) {
		GraphList gl = vertex.get(root);
		if (gl == null) {
			return null;
		} else {
			// Can't use moveToPos(). It throws runtime errors.
			// Also, it's n^2 efficiency to iterate followers then.
			int pos = gl.currPos();
			gl.next();
			if (gl.currPos() > pos && gl.getValue() != null) {
				return gl.getValue().vertex();
			} else {
				// We are at the end.
				return null;
			}
		}
	}

	@Override
	/** Get the color associated with a node for visualization.
	 * @returns the color as a string
	 * @see setNodeColor(String, String)
	 * 
	 * This method is absent in the book. This must be provided for or
	 * implemented by students.
	 */
	public String getNodeColor(String v) {
		String c = color.get(v);
		if (v == null) {
			return "";
		} else {
			return c;
		}
	}

	@Override
	/**
	 * Set the color associated with a node for visualization.
	 * Anything recognized by CSS works. e.g.
	 * "#a4d", "red", "SandyBrown", "#71726a", "rgb(100, 172, 191)",
	 * "rgba(100, 172, 191, 0.2)"
	 * An empty string is retained as empty but indicates to pick a color at
	 * random when viewed on the web.
	 * 
	 * This method is absent in the book. This must be provided for or
	 * implemented by students.
	 */
	public void setNodeColor(String v, String c) {
		if (has(v))
			color.put(v, c);
		
	}
	
	@Override
	/** Set the color of the edge from i to j.
	 * @param i  Source node
	 * @param j  Target node
	 * @param color  CSS-formatted color.
	 * @return  Whether the color was successfully set.
	 * @see setNodeColor(String, String) for more information on valid colors
	 * @see getNodeColor(String, String)
	 * 
	 * This method is absent in the book. This must be provided for or
	 * implemented by students.
	 * */
	public boolean setEdgeColor(String i, String j, String color) {
		GraphList l = getEdgeLink(i, j);
		if (l == null) {
			// There is no link, or i or j is missing.
			// Don't make a new link for the color.
			return false;
		} else {
			// The edge already exists. Change its color.
			Edge newEdge = new Edge(j, l.getValue().weight(), color);
			l.remove();
			l.insert(newEdge);
			return true;
		}
	}
	
	@Override
	/**
	 * @return the color of the edge from i to j, or "" for a random color, or null for missing edges.
	 * 
	 * This method is absent in the book. This must be provided for or
	 * implemented by students.
	 */
	public String getEdgeColor(String i, String j) {
		GraphList l = getEdgeLink(i, j);
		if (l == null) {
			// There is no link, or i or j is missing.
			return null;
		} else {
			// The edge already exists. Change its color.
			return l.getValue().color();
		}	
	}
}
