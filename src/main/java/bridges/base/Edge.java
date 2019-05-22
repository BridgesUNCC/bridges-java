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

	private K to;
	private K from;
	private E2 edge_data;
	private LinkVisualizer lvis;
	/**
	 *
	 * Construct an edge with weight "wt" and a terminating
	 * Element with an identifer equal to "v" - used only for graphs
	 *
	 * @param from key of source vertex
	 * @param to ket of terminating vertex of the edge
	 * @param data is the  edge information object
	 *
	 */
	public Edge(K from, K to, E2 data) {
		this.to = to;
		this.from = from;
		this.edge_data = data;
		this.lvis = new LinkVisualizer();
	}

	/**
	 *
	 * Set source Element of the edge
	 *
	 * @param from the identifier of the source Element
	 *
	 */
	public void setFrom(K from) {
		this.from = from;
	}

	/**
	 *
	 * Set identifer of the terminating Element of edge
	 *
	 * @param to the identifier of the terminating Element
	 *
	 */
	public void setTo(K to) {
		this.to = to;
	}

	/**
	 *
	 * Get identifer of the terminating Element of edge
	 *
	 * @return the string identifier of the terminating Element
	 *
	 */
	public K getTo() {
		return this.to;
	}

	/**
	 *
	 * Get identifer of the source Element of edge
	 *
	 * @return the string identifier of the source Element
	 *
     */
	public K getFrom() {
		return this.from;
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

}
