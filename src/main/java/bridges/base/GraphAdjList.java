package bridges.base;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;

/**
 *
 *	@brief The GraphAdjList class can be used to represent adjacency list based
 *		graphs in BRIDGES.
 *
 *	The GraphAdjList class can be used to represent adjacency list
 *	based graphs in BRIDGES; it takes 3 generic parameters: (1) K,
 *	which is an orderable key value used in accessing vertices (in
 *	constant time) using a hashmap. This permits data sets that
 *	need to be accessed by keys that are strings, (2) E1, for
 *	maintaining vertex specific data, and (3) E2, for maintaining
 *	edge specific data.  The class is a wrapper around the Java
 *	Hashmap class and, thus, derives all its operations from it.
 *	BRIDGES provides methods to visualize the graph and its
 *	contents.
 *
 *      In most cases, you want all the three types to be the same, or
 *      in cases where you only care about the vertex key type, one
 *      can use the GraphAdjListSimple helper class.
 *
 *	The vertices of the graph are held in a Java hashmap, for near
 *	constant time access; this enables to use strings or integer ids
 *	for vertices. The adjacency lists, also a Java hashmap are
 *	built for each vertex and contain the edge (source,
 *	destination vertices) in the Edge structure,
 *	defined separately.
 *
 *
 * Convenience method addVertex() is provided to add vertices to
 * the graph, and addEdge() is provided to add edges.  Edges are
 * retrieved by using the dual hashmap, given the vertex ids of the
 * edge. Vertices can be styled directly from the vertex element
 * returned by getVertex(), and edges are styled from a LinkVisualizer
 * one can access through getLinkVisualizer(). Here is a simple example:
 *
 *\code{java}
 * GraphAdjList<string, Integer, Double> graph = new GraphAdjList<String, Integer, Double> ();
 *    graph.addVertex("a");
 *    graph.addVertex("b");
 *    graph.addEdge("a", "b");
 *    graph.getVertex("a").setShape("square");
 *    graph.getLinkVisualizer("a", "b").setColor("yellow");
 *\endcode
 *
 * Adjacency lists are singly linked lists using the BRIDGES
 * SLelement. Iterators are provided for easy traversal of the
 * adjacency lists. For instance,
 *
 *\code{java}
 * GraphAdjList<String, Integer, Double> graph = something();
 * for (Edge<String, Double> e : graph.outgoingEdgeSetOf("a"))
 *   System.out.println("a -> "+e.getTo());
 *\endcode
 *
 * Graphs can have their nodes and links affected by visual attributes. Nodes
 * can have color, size, opacity and shape and  detailed in the ElementVisualizer
 * class. Edges support attributes such as color, thickness and opacity and are
 * detailed in the LinkVisualizer class.  Element and link attributes are set
 * using the getVisualizer() and getLinkVisualizer() methods.  For instance,
 *
 *\code{java}
 * GraphAdjList<String, Integer, Double> graph = something();
 *   graph.addVertex("baskin");
 *   graph.addVertex("robins");
 *   graph.addEdge("baskin","robins");
 *   graph.getVisualizer().setColor("cyan");
 *   graph.getVisualizer().setShape("square");
 *   graph.getLinkVisualizer("baskin", "robins").setColor("green");
 *   graph.getLinkVisualizer("baskin", "robins").setOpacity("0.5f");
 *\endcode
 *
 * There are two visualization engines available for
 * graph. The small graph visualization supports all
 * attributes of vertices and edges but is
 * prohibitively slow on large graphs. The large graph
 * visualization only supports locations (actually
 * they are mandatory) and colors, all other
 * attributes are ignored.
 *
 * BRIDGES picks the rendering engine automatically. But it can be
 * forced to pick one used forceLargeVizualization() and
 * forceSmallVizualization
 *
 * @param K  orderable key (String, Integer, etc) that is used to index into vertex
 * @param E1 holds vertex specific information, defined by application
 * @param E2 holds edge specific information, defined by application
 *
 * \sa Example tutorial at
 *		https://bridgesuncc.github.io/tutorials/Graph.html
 *
 * @author Kalpathi Subramanian, Erik Saule
 *
 * @date 6/29/15, 5/18/17, 4/24/18, 7/14/19, 1/5/21
 *
 */
public class GraphAdjList<K, E1, E2> extends DataStruct  {

	// keep track of the graph nodes; useful
	// to maintain their properties

	private final HashMap<K, Element<E1> > vertices;

	// holds the adjacency list of edges

	private final HashMap < K, SLelement < Edge< K, E2 > > > adj_list;

	private final static int LARGE_GRAPH_VERT_SIZE = 1000;

	private boolean forceLargeViz = false;
	private boolean forceSmallViz = false;


	/**
	 *
	 *	Constructor
	 */
	public GraphAdjList() {
		vertices = new HashMap<K, Element<E1> >();
		adj_list = new HashMap<K, SLelement<Edge<K, E2> > >();
	}

	/**
	 *	@brief This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 *
	 */
	public String getDataStructType() {
		if (forceLargeViz ||
			(!forceSmallViz && this.vertices.size() > LARGE_GRAPH_VERT_SIZE &&
				areAllVerticesLocated())) {
			return "largegraph";
		}
		return "GraphAdjacencyList";
	}
	/**
	 * @brief adds a new vertex to the graph.
	 *
	 *	Adds a new vertex to the graph, initializes the  adjacency
	 *	list; user is responsible for checking if the vertex already
	 *	exists. This method will replace the value for this key
	 *
	 *	@param k - vertex id
	 *	@param e - vertex info, currently used as a label by default
	 *
	 *	@return none
	 */

	public void addVertex(K k, E1 e) {
		// note: it is the user's responsibility to  check
		// for duplicate vertices
		vertices.put(k, new Element<E1>(e));
		vertices.get(k).setLabel(String.valueOf(k));
		adj_list.put(k,  null);
	}
	/**
	 * @brief adds a new edge to the graph.
	 *
	 *	Adds a new edge to the graph, adds it to that vertex's
	 *	adjacency list; user is responsible for checking if the
	 *	vertex already exists. This version assumes the edge data is null.
	 *
	 *	@param src - source vertex of edge
	 *	@param dest - destination  vertex of edge
	 *
	 */
	public void addEdge(K src, K dest) {
		this.addEdge(src, dest, null);
	}

	/**
	 * @brief adds a new edge to the graph.
	 *
	 *	Adds a new edge to the graph, adds it to that vertex's
	 *	adjacency list; user is responsible for checking if the
	 *	vertex already exists.
	 *
	 *	@param src - source vertex of edge
	 *	@param dest - destination  vertex of edge
	 *	@param data - edge data
	 *
	 */
	public void addEdge(K src, K dest, E2 data) {
		// check to see if the two vertices exist, else
		// throw an exception

		try {
			if (vertices.get(src) == null || vertices.get(dest) == null) {
				throw new NullPointerException("Vertex " + src + " or " + dest +
					" does not exist! Add the vertex before creating the edge.");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// bails out if edge already exists
		if (getEdge(src, dest) != null)
			return;

		adj_list.put(src, new SLelement<Edge<K, E2 >> (new Edge<K, E2>(src, dest, data), adj_list.get(src)));
	}
	/**
	 *	@brief Sets data for a graph vertex
	 *
	 *	@param src  source vertex of edge
	 *	@param vertex_data  vertex data
	 *
	 */
	public void setVertexData(K src, E1 vertex_data) {
		// check to see if the vertex  exists, else throw an exception
		try {
			if (vertices.get(src) == null) {
				throw new NullPointerException("Vertex " + src + " does not exist!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		vertices.get(src).setValue(vertex_data);
	}
	/**
	 *	@brief Gets data for an edge
	 *
	 *	@param src - source vertex of edge
	 *
	 */
	public E1 getVertexData(K src) {
		// check to see if the vertex  exists, else throw an exception

		try {
			if (vertices.get(src) == null) {
				throw new NullPointerException("Vertex " + src +
					" does not exist!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return  vertices.get(src).getValue();
	}
	/**
	 *	@brief Sets data for an edge
	 *
	 *	@param src - source vertex of edge
	 *	@param dest - destination  vertex of edge
	 *	@param edge_data - edge data
	 *
	 */
	public void setEdgeData(K src, K dest, E2 edge_data) {
		// check to see if the two vertices exist, else
		// throw an exception

		if (vertices.get(src) == null || vertices.get(dest) == null) {
			throw new NullPointerException("Vertex " + src + " or " + dest +
				" does not exist! Add the vertex before creating the edge.");
		}
		// look for the edge

		SLelement<Edge<K, E2 >> sle = adj_list.get(src);
		while (sle != null) {
			K edge_dest = ((Edge<K, E2>) sle.getValue()).getTo();
			if (edge_dest.equals(dest)) {	// found
				sle.getValue().setEdgeData(edge_data);
				return;
			}
			sle = sle.getNext();
		}
		if (sle == null)
			throw new NullPointerException("Edge from " + src  + " to "
				+ dest + " does not exist!");
	}
	/**
	 *	@brief Gets data for an edge
	 *
	 *	@param src source vertex of edge
	 *	@param dest destination vertex of edge
	 *
	 * @return the edge data, or null if the edge does not exist
	 */
	private Edge<K, E2> getEdge(K src, K dest) {
		// check to see if the two vertices exist, else
		// return null
		if (vertices.get(src) == null || vertices.get(dest) == null) {
			return null;
		}

		// look for the edge
		SLelement<Edge<K, E2 >> sle = adj_list.get(src);
		while (sle != null) {
			K edge_dest = ((Edge<K, E2>) sle.getValue()).getTo();
			if (edge_dest.equals(dest)) 	// found
				return sle.getValue();
			sle = sle.getNext();
		}

		// should never reach here if the edge exist
		return null;
	}


	/**
	 *	@brief Gets data for an edge
	 *
	 *	@param src source vertex of edge
	 *	@param dest destination vertex of edge
	 *
	 * @return the edge data, or null if the edge does not exist
	 */
	public E2 getEdgeData(K src, K dest) {
		Edge<K, E2> e = getEdge(src, dest);
		if (e == null)
			return null;
		else
			return e.getEdgeData();
	}
	/**
	 *	@brief This method returns the graph vertices
	 *
	 *	@return vertices held in the hashmap
	 *
	 */
	public HashMap<K, Element<E1> > getVertices() {
		return vertices;
	}
	/**
	 * @brief returns a vertex from its key
	 *
	 *	This is a convenience method to retrieve a vertex given
	 *	its key
	 *
	 *	@return graph vertex corresponding to its key (or null if the vertex does not exist)
	 *
	 */
	public Element<E1> getVertex(K key) {
		return vertices.get(key);
	}

	/**
	 * @brief Gets the graph's adjacency list
	 *
	 *	@return the graph's adjacency lists
	 *
	 */
	public HashMap<K, SLelement<Edge<K, E2> > > getAdjacencyList() {
		return adj_list;
	}
	/**
	 *	@brief Gets the adjacency list of a vertex. Note that the list
	 *  can be traversed using iterators. See example at top of page.
	 *
	 *	@param vertex the key of the vertex
	 *
	 *	@return - the graph's adjacency list  corresponding to this vertex
	 */
	public SLelement<Edge<K, E2> >  getAdjacencyList(K vertex) {
		return adj_list.get(vertex);
	}

	/**
	 * @brief returns an iterable set of outgoing edge of a vertex
	 *
	 *  Gets a vector of the outgoing edges from a graph vertex
	 *	@param vertex  vertex identifier
	 *	@return  an iterable set of the outgoing edge of this vertex
	 */
	public Iterable<Edge<K, E2 >> outgoingEdgeSetOf(K vertex) {
		//TODO: This should probably not create an array list, but create an iterable type out of SLelement
		ArrayList<Edge<K, E2 >> edgeSet = new ArrayList<Edge<K, E2 >> ();
		SLelement<Edge<K, E2 >> list = getAdjacencyList(vertex);

		if (list != null) {
			for (Edge<K, E2> element : list) {
				edgeSet.add(element);
			}
		}

		return edgeSet;
	}
	/**
	 * @brief Access a LinkVisualizer associated with an edge.
	 *
	 *	 This is a convenience method to simplify access to the link visualizer;
	 *	 the method assumes the vertex names point to existing vertices, else an exception
	 *	 is thrown
	 *
	 *	 @param src   source vertex
	 *	 @param dest   destination  vertex
	 *
	 *	 @return the LinkVisualizer of an edge from src to dest, or null if the edge does not exist
	 *
	 */
	public LinkVisualizer getLinkVisualizer (K src, K dest) {
		Edge<K, E2> e = getEdge(src, dest);
		if (e == null)
			return null;
		else
			return e.getLinkVisualizer();
	}
	/**
	 * @brief Access the ElementVisualizer associated with a vertex
	 *
	 *	This is a convenience method to simplify access to the element visualizer;
	 *	the method assumes the vertex name points to an existing vertice, else an
	 *	exception is thrown
	 *
	 *	 @param vertex   graph vertex
	 *	 @return the element visualizer of this vertex
	 */
	public ElementVisualizer getVisualizer (K vertex) {
		// get the source and destination vertex elements
		// and check to see if they exist
		Element<E1> v = vertices.get(vertex);
		try {
			if (v == null) {
				throw new NullPointerException("Vertex " + vertex  +
					" does not exist! First add the vertices to the graph.");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return v.getVisualizer();
	}


	/**
	 * @brief Checks whether all the vertices have a specified location
	 * @return true if all vertices have both an x and y location
	 */
	private boolean areAllVerticesLocated() {
		for (Entry<K, Element<E1 >> element : vertices.entrySet()) {
			Element<E1> el = element.getValue();
			ElementVisualizer elvis = el.getVisualizer();
			if (elvis.getLocationX() == Double.POSITIVE_INFINITY
				|| elvis.getLocationY() == Double.POSITIVE_INFINITY) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @brief  Forces the graph use the large graph visualization.
	 *
	 * This forces the rendering to a more bandwidth
	 * efficient at the cost of having less features. The large
	 * graph visualization only renders vertices that have
	 * specified locations. The only usable attribute for vertices and edges are colors.
	 *
	 * @param f  true to force large visualization. Setting to false does not prevent large visualization to be used, just does not force it.
	 */
	public void forceLargeVisualization(boolean f) {
		if (f) {
			forceLargeViz = true;
			forceSmallViz = false;
		}
		else {
			forceLargeViz = false;
		}
	}

	/**
	 * @brief Forces  the graph to use the small graph visualization
	 *
	 * The small visualization uses more bandwidth, have more
	 * features, and support a force directed layout for vertices
	 * which do not have a specified location.
	 *
	 * @param f  true to force using the small visualization. Setting to false does not prevent small visualization to be used, just does not force it.
	 */
	public void forceSmallVisualization(boolean f) {
		if (f) {
			forceSmallViz = true;
			forceLargeViz = false;
		}
		else {
			forceSmallViz = false;
		}
	}

	/*
	 *  @brief Constructs the JSON representation of the the data structure
	 *
	 *  @return the JSON (string) of the graph
	 */
	public String getDataStructureRepresentation() {
		if (forceLargeViz ||
			(!forceSmallViz && this.vertices.size() > LARGE_GRAPH_VERT_SIZE && areAllVerticesLocated())) {
			return getDataStructureLargeGraph();
		}
		// map to reorder the nodes for building JSON
		HashMap<Element<E1>, Integer> node_map = new HashMap<Element<E1>, Integer>();
		// get the list nodes
		ArrayList<Element<E1> > nodes = new ArrayList<Element<E1 >> ();

		for (Entry<K, Element<E1 >> element : vertices.entrySet())
			nodes.add(element.getValue());

		// remap  map these nodes to  0...MaxNodes-1
		// and build the nodes JSON
		StringBuilder nodes_JSON = new StringBuilder();
		for (int k = 0; k < nodes.size(); k++) {
			node_map.put(nodes.get(k), k);
			nodes_JSON.append(nodes.get(k).getElementRepresentation());
			nodes_JSON.append(COMMA);
		}
		// remove the last comma
		if (nodes.size() != 0) 		// only if there is at least one node
			nodes_JSON.setLength(nodes_JSON.length() - 1);

		// build the links JSON - traverse the adj. lists
		StringBuilder links_JSON = new StringBuilder();
		for (Entry<K, SLelement<Edge<K, E2 >>> a_list : adj_list.entrySet()) {
			SLelement<Edge<K, E2 >> list = a_list.getValue();
			// get the source vertex index for the JSON (int)
			Element<E1> src_vert = vertices.get(a_list.getKey());
			while (list != null) {
				Integer src_indx = node_map.get(src_vert);
				// get the destination vertex index for the JSON (int)
				Edge<K, E2> edge = list.getValue();
				Element<E1> dest_vert = vertices.get(edge.getTo());
				Integer dest_indx = node_map.get(dest_vert);
				// get link representation
				LinkVisualizer lv = edge.getLinkVisualizer();
				links_JSON.append(lv.getLinkRepresentation(
						Integer.toString(src_indx), Integer.toString(dest_indx))).append(COMMA);
				list = list.getNext();
			}
		}
		// remove the last comma
		if (links_JSON.length() > 0) // if there is at least one link
			links_JSON.setLength(links_JSON.length() - 1);


		String json_str =
			QUOTE + "nodes"  + QUOTE + COLON +
			OPEN_BOX + nodes_JSON.toString()  + CLOSE_BOX + COMMA +
			QUOTE + "links" + QUOTE + COLON +
			OPEN_BOX + links_JSON.toString() + CLOSE_BOX +
			CLOSE_CURLY;

		return json_str;

	}

	private String getDataStructureLargeGraph() {
		HashMap<Element<E1>, Integer> node_map = new HashMap<Element<E1>, Integer>();
		// get the list nodes
		ArrayList<Element<E1 >> nodes = new ArrayList<Element<E1 >> ();

		for (Entry<K, Element<E1 >> element : vertices.entrySet())
			nodes.add(element.getValue());

		String nodes_JSON = "";
		// remap  map these nodes to  0...MaxNodes-1
		// and build the nodes JSON
		for (int k = 0; k < nodes.size(); k++) {
			node_map.put(nodes.get(k), k);
			ElementVisualizer elvis = nodes.get(k).getVisualizer();
			String loc_str = "";
			if (elvis.getLocationX() != Double.POSITIVE_INFINITY
				&& elvis.getLocationY() != Double.POSITIVE_INFINITY) {
				loc_str = OPEN_BOX + elvis.getLocationX() + COMMA
					+ elvis.getLocationY()
					+ CLOSE_BOX + COMMA;
			}
			Color color = elvis.getColor();
			nodes_JSON += OPEN_BOX + loc_str + OPEN_BOX +
				color.getRed() + COMMA +
				color.getGreen() + COMMA +
				color.getBlue() + COMMA +
				color.getAlpha() + CLOSE_BOX + CLOSE_BOX + COMMA;

		}

		if (nodes_JSON.length() > 0) {
			nodes_JSON = nodes_JSON.substring(0, nodes_JSON.length() - 1);
		}

		String link_JSON = "";

		for (Entry<K, SLelement<Edge<K, E2 >>> a_list : adj_list.entrySet()) {
			SLelement<Edge<K, E2 >> list = a_list.getValue();
			Element<E1> src_vert = vertices.get(a_list.getKey());
			while (list != null) {
				Integer src_indx = node_map.get(src_vert);
				Edge<K, E2> edge = list.getValue();
				Element<E1> dest_vert = vertices.get(edge.getTo());
				Integer dest_indx = node_map.get(dest_vert);
				Color color = src_vert.getLinkVisualizer(dest_vert).getColor();

				link_JSON += OPEN_BOX +
					src_indx + COMMA +
					dest_indx + COMMA +
					OPEN_BOX +
					color.getRed() + COMMA +
					color.getGreen() + COMMA +
					color.getBlue() + COMMA +
					color.getAlpha() + CLOSE_BOX + CLOSE_BOX + COMMA;
				list = list.getNext();
			}
		}

		if (link_JSON.length() > 0) {
			link_JSON = link_JSON.substring(0, link_JSON.length() - 1);
		}

		String graph_alist_json =
			QUOTE + "nodes"  + QUOTE + COLON +
			OPEN_BOX + nodes_JSON + CLOSE_BOX + COMMA +
			QUOTE + "links" + QUOTE + COLON +
			OPEN_BOX + link_JSON + CLOSE_BOX +
			CLOSE_CURLY;

		return graph_alist_json;
	}
}
