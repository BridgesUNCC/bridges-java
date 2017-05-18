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
 * @param generic parameter <K>  usually an Element<E> type used to hold the 
 *		terminating vertex 
 */
public class Edge<K> {

	private int weight;
	private Key vertex; // refers to a terminating vertex
	private String edge_data;

	/**
	 *
	 * Constructors
	 *
	 */
	public Edge() {
		weight = 0;
		edge_data = "";
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
		edge_data = "";
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
	public Edge(int wt, Key v) {
		weight = wt;
		vertex = v;
		edge_data = "";
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
	public void setVertex(Key v) {
		vertex = v;
	}

	/**
	 * 
	 * Get identifer of the terminating Element of edge
	 * 
	 * @return the string identifier of the terminating Element
	 * 
	 */
	public Key getVertex() {
		return vertex;
	}

	/**
	 * 
	 * Set edge to weight  of "wt" and terminating Elememt of "v".
	 * 
	 * @param wt edge weight 
	 * @param v the identifier of the terminating Element
	 * 
	 */
	public void setEdge(int wt, Key v) {
		this.weight = wt;
		this.vertex = v;
	}

	/**
	 * Set Edge data (represented as a string for now)
	 * 
	 * @param string: application data
	 **/
	void setEdgeData(String data) {
		edge_data = data;
	}
    
	/**
	 * Get edge data
	 * 
	 * @return the edge data
	 **/
	String getEdgeData() {
		return edge_data;
	}

	/**
	 * Returns this edge
	 */
	public Edge<Key> getEdge() {
		return this;
	}
	
};
