package edu.uncc.cs.bridgesV2.base;

import java.util.Map.Entry;

/**
 * This class is used to assign a visual connection between two Elements in the
 * Bridges Visualization. 
 * <p>
 * Bridges will represent these as arrows between two
 * Elements. The starting Element of the arrow will be referred to as the source
 * Element and the ending Element of the arrow will be referred to as the
 * terminating Element.
 * 
 * @author krs
 *
 * @param <E>
 */
public class Edge implements Comparable<Edge> {

	private int weight;
	private String vertex; // refers to a terminating vertex

	/**
	 * Construct an edge with no terminating Element - used only for graphs
	 */
	public Edge() {
		super();
		this.weight = 0;
		vertex = null;
	}

	/**
	 * Construct an edge with thickness equal to "wt" and no terminating Element
	 * - used only for graphs.
	 * @param wt integer representing the thickness of the arrow in the Bridges Visualization
	 */
	public Edge(int wt) {
		super();
		this.weight = wt;
		vertex = null;
	}

	/**
	 * Construct an edge with thickness equal to "wt" and a terminating Element with an identifer equal to "v" 
	 * - used only for graphs 
	 * @param wt integer representing the thickness of the arrow in the Bridges Visualization
	 * @param v the string identifier of the terminating Element
	 */
	public Edge(int wt, String v) {
		super();
		this.weight = wt;
		vertex = v;
	}

	/**
	 * Set edge weight to "wt"
	 * 
	 * @param wt  integer representing the thickness of the arrow in the Bridges Visualization
	 */
	public void setWeight(int wt) {
		this.weight = wt;
	}

	/**
	 * Get edge weight
	 * 
	 * @return the thickness of the arrow in the Bridges Visualization
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Set terminating Element of the edge
	 * 
	 * @param v the string identifier of the terminating Element
	 */
	public void setVertex(String v) {
		this.vertex = v;
	}

	/**
	 * Get identifer of the terminating Element of edge
	 * 
	 * @return the string identifier of the terminating Element
	 */
	public String getVertex() {
		return this.vertex;
	}

	/**
	 * Set edge to thickness of "wt" and terminating Elememt of "v".
	 * 
	 * @param wt integer representing the thickness of the arrow in the Bridges Visualization
	 * @param v the string identifier of the terminating Element
	 */
	public void setEdge(int wt, String v) {
		this.weight = wt;
		this.vertex = v;
	}

	/**
	 * Returns this edge
	 */
	public Edge getEdge() {
		return this;
	}
	
	public int compareTo(Edge e1){
		return ((Integer)this.getWeight()).compareTo((Integer)e1.getWeight());
	}
};
