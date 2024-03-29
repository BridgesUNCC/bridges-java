package bridges.base;

import java.util.Map.Entry;

/**
 * @brief This class is used to represent the edges in a graph and will
 * 	appear as links in the BRIDGES graph visualization: GraphAdjList and GraphAdjMatrix.

 * This object is used in graphs and graph algorithms such as DFS, BFS and shortest
 * path algorithms that need to visit graph edges. The adjacency list
 * representation uses them as the generic paramter, as SLelement<Edge>
 * Bridges represents Edges as links between pairs of elements.
 *
 * @sa the tutorial on using graphs in BRIDGES: https://bridgesuncc.github.io/tutorials/Graph.html and https://bridgesuncc.github.io/tutorials/Graph_AM.html
 *
 * @author Kalpathi Subramanian
 *
 * @param K  holds the terminating vertex of the edge
 * @param E2 holds edge specific information
 */
public class Edge<K, E2> {

	private K from;						// source vertex
	private K to;						// destination vertex
	private E2 edge_data;				// edge specific data
	private LinkVisualizer lvis;		// link visualizer for this edge
	/**
	 * @brief Construct an edge using each extremities, user data, and styling information.
	 *
	 * @param from key of source vertex of the edge
	 * @param to key of terminating vertex of the edge
	 * @param data is the  edge information object
	 * @param lvis is the object storing styling information for the edge
	 *
	 */
	public Edge(K from, K to, E2 data, LinkVisualizer lvis) {
		this.to = to;
		this.from = from;
		this.edge_data = data;
		this.lvis = lvis;
	}

	/**
	 * @brief Construct an edge using each extremities, user data.
	 *
	 * @param from key of source vertex of the edge
	 * @param to key of terminating vertex of the edge
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
	 * @brief Set source Element (Vertex) of the edge
	 *
	 * @param from the identifier of the source Element (Vertex)
	 *
	 */
	public void setFrom(K from) {
		this.from = from;
	}

	/**
	 * @brief Set identifier of the terminating Element (Vertex) of edge
	 *
	 * @param to the identifier of the terminating Element (Vertex)
	 *
	 */
	public void setTo(K to) {
		this.to = to;
	}

	/**
	 * @brief Get identifer of the terminating Element of edge
	 *
	 * @return the identifier of the terminating Element (vertex)
	 */
	public K getTo() {
		return this.to;
	}

	/**
	 *
	 * Get identifer of the source Element of edge
	 *
	 * @return the identifier of the source Element
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

	/**
	 *	Get the edge's link visualizer
	 *  @return link visualizer for this edge
	 */
	public LinkVisualizer getLinkVisualizer() {
		return this.lvis;
	}

	/**
	 *  @brief Set the edge's link visualizer
	 *
	 *  @param lvis link visualizer to be set for this edge
	 */
	public void setLinkVisualizer(LinkVisualizer lvis) {
		this.lvis = lvis;
	}

	/**
	 *	@brief Get the edge's label
	 *	@return label of this edge
	 */
	public String getLabel() {
		return this.lvis.getLabel();
	}

	/**
	 *	@brief Set the edge's label
	 *	@param label the label to be assigned
	 */
	public void setLabel(String label) {
		this.lvis.setLabel(label);
	}

	/**
	 * @brief Get the thickness of the edge
	 *
	 * @return the size in pixels of the edge
	 */
	public double getThickness() {
		return this.lvis.getThickness();
	}

	/**
	 * @brief Set the thickness of the edge
	 *
	 * @param thickness edge thickness to set
	 */
	public void setThickness(double thickness) {
		this.lvis.setThickness(thickness);
	}

	/**
	 *	@brief Get the color of the link in the Bridges Visualization
	 *
	 *	@return the edge color
	 *
	 */
	public Color getColor() {
		return this.lvis.getColor();
	}

	/**
	 * @brief Set the color of the link from an existing Color object
	 *
	 * @param color the edge color to be assigned
	 */
	public void setColor(Color color) {
		this.lvis.setColor(color);
	}

	/**
	 *	@brief Set the color of the link in the Bridges Visualization
	 *
	 * 	@param color the string reprsenting the color. See the Color class
	 *		for the complete set of supported colors.
	 */
	public void setColor(String color) {
		this.lvis.setColor(color);
	}

	/**
	 * 	@brief Set the color of the link given RGBA components
	 *
	 * 	@param r  red component
	 * 	@param g  green component
	 * 	@param b  blue component
	 * 	@param a  alpha (transparency) component
	 */
	public void setColor(int r, int g, int b, float a) {
		this.lvis.setColor(r, g, b, a);
	}

}
