package bridges.base;

import java.util.Map.Entry;

/**
 * @brief This class is used to represent the edges in a graph and will
 * 	appear as links in the BRIDGES graph visualization.

 * This object is used in graphs and graph algorithms such as DFS, BFS and shortest
 * path algorithms that need to visit graph edges. The adjacency list
 * representation uses them as the generic paramter, as SLelement<Edge>
 * Bridges represents Edges as links between pairs of elements
 *
 * @author K.R. Subramanian
 *
 * @param generic parameter <K>  holds the terminating vertex of the edge
 * @param generic parameter <E2> holds edge specific information
 */
public class Edge<K, E2> {

	private int weight;
	private K vertex; // refers to a terminating vertex
	private E2 edge_data;

	/**
	 *
	 * Constructors
	 *
	 */
	public Edge() {
		weight = 0;
	}

	/**
	 *
	 * Construct an edge with weight equal to "wt" and no terminating Element
	 * - used only for graphs.
	 * @param wt integer representing the weight of the edge
	 *
	 */
	public Edge(int wt) {
		weight = wt;
	}

	/**
	 *
	 * Construct an edge with weight "wt" and a terminating
	 * Element with an identifer equal to "v" - used only for graphs
	 *
	 * @param wt integer, representing  edge weight
	 * @param v the terminating vertex of the edge
	 *
	 */
	public Edge(int wt, K v) {
		weight = wt;
		vertex = v;
	}
	/**
	 *
	 * Construct an edge with weight "wt" and a terminating
	 * Element with an identifer equal to "v" - used only for graphs
	 *
	 * @param wt integer, representing  edge weight
	 * @param v the terminating vertex of the edge
	 * @param d is the  edge information object
	 *
	 */
	public Edge(int wt, K v, E2 e) {
		weight = wt;
		vertex = v;
		edge_data = e;
	}

	/**
	 *
	 * Set edge weight to "wt"
	 *
	 * @param wt  -  graph edge weight
	 *
	 */
	public void setWeight(int wt) {
		weight = wt;
	}

	/**
	 *
	 * Get edge weight
	 *
	 * @return the weight of edge
	 *
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 *
	 * Set terminating Element of the edge
	 *
	 * @param v the identifier of the terminating Element
	 *
	 */
	public void setVertex(K v) {
		vertex = v;
	}

	/**
	 *
	 * Get identifer of the terminating Element of edge
	 *
	 * @return the string identifier of the terminating Element
	 *
	 */
	public K getVertex() {
		return vertex;
	}

	/**
	 *
	 * Set edge specific data.
	 *
	 * @param data edge data
	 *
	 */
	public void setEdgeData(E2 data) {
		this.edge_data = data;
	}
	/**
	 *
	 * Get edge specific data.
	 *
	 * @return  edge data
	 *
	 */
	public E2 getEdgeData() {
		return this.edge_data;
	}
	/**
	 *
	 * Set edge to weight  of "wt" and terminating Elememt of "v".
	 *
	 * @param wt edge weight
	 * @param v the identifier of the terminating Element
	 *
	 */
	public void setEdge(int wt, K v) {
		this.weight = wt;
		this.vertex = v;
	}

	/**
	 * Returns this edge
	 */
	public Edge<K, E2> getEdge() {
		return this;
	}

};
