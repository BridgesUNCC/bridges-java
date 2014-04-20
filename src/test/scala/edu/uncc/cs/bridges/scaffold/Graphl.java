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

	public Graphl(){}
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
	
	/** Either add a vertex, returning true, or skip it and return false */
	public boolean add(String name) {
		if (vertex.containsKey(name)) {
			return false;
		} else {
			vertex.put(name, new GraphList());
			setMark(name, UNVISITED);
			return true;
		}	
	}
	
	/** @return edge weight */
	public int getEdge(String i, String j) {
		// GraphList can't do a search, so look for the element.
		// Can't iterate either, and next() doesn't tell if it reached the end.
		// So check to see if the position changed.
		GraphList l = vertex.get(i);
		int position = -1;
		l.moveToStart();
		while (position != l.currPos() && !l.getValue().equals(j)) {
			l.next();
		}
		// At this point we might have found it, or we might have reached the end.
		if (l.getValue().equals(j)) {
			// Yay! Tell the user the good news.
			return l.getValue().weight();
		} else {
			// No edge.
			return 0;
		}
	}
	
	/** Store edge weight */
	public void setEdge(String i, String j, int weight) {
		// Why not? Is it so bad to delete an edge?
		assert weight != 0 : "May not set weight to 0";
		Edge currEdge = new Edge(j, weight);
		vertex.get(i).insert(currEdge);
	}

	// Set and get marks
	public void setMark(String string, int val) {
		mark.remove(string);
		mark.put(string, val);
	}

	public int getMark(String string) {
		return mark.get(string);
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
	public String first(String root) {
		vertex.get(root).moveToStart();
		String first = null;
		if (vertex.get(root).getValue() != null)
			first = vertex.get(root).getValue().vertex();
		return first;
	}

	@Override
	public String next(String root) {
		int pos = vertex.get(root).currPos();
		vertex.get(root).moveToPos(pos + 1);
		String next = null;
		if (vertex.get(root).getValue() != null)
			next = vertex.get(root).getValue().vertex();
		return next;
	}

	@Override
	public String getColor(String v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColor(String v, String color) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean setEdgeColor(String i, String j, String color) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void getEdgeColor(String i, String j) {
		// TODO Auto-generated method stub
		
	}
}
