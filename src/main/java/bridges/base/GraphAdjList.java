package bridges.base;
import bridges.base.SLelement;
import bridges.base.Edge;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

/**
 *
 *	@brief The GraphAdjList class can be used to represent adjacency list based
 *		graphs in BRIDGES
 *
 *	The GraphAdjList class can be used to represent adjacency list based  graphs
 *	in BRIDGES; it takes 2 generic parameters: (1) K, which is an orderable
 *	key value used in accessing vertices (in constant time) using a hashmap. This
 *	permits data sets that need to be accessed by keys that are strings, and
 *	(2) E, an application defined type, and used in the Edge representation.
 *	The class is simply a wrapper  around the Java Hashmap class
 *	and, thus, derives all its operations from it.
 *	BRIDGES provides methods to visualize the graph  and its contents.
 *
 *	The vertices of the graph are held in a Java hashmap, for near constant time access;
 *	this lets us use strings or integral ids for vertices. The adjacency lists,
 *	also a Java hashmap  are built for each vertex and contain the edge (terminating
 *	vertex id, weight) in the Edge structure, defined separately. Adjacency lists
 *	are singly linked lists using the BRIDGES SLelement.
 *
 *	Convenience methods are provided to add vertices and edges to the graph. Edges
 *  are retrieved by using the dual hashmap, given the vertex ids of the edge.
 *  Methods to access the element and link visualizer are now provided, indexed
 *  vertex ids, making it easier to set visual attributes to graph nodes and
 *  links.
 *
 *	@author Kalpathi Subramanian
 *
 *	@date 6/29/15, 5/18/17, 4/24/18
 *
 *	@param <K>  orderable key (string, int, etc) that is used to index into vertex
 *	@param <E1> holds vertex specific information, defined by application
 *	@param <E2> holds edge specific information, defined by application
 *
 *	\sa Example tutorial at <p>
 *		http://bridgesuncc.github.io/Hello_World_Tutorials/Graph.html
 *
 */
public class GraphAdjList<K, E1, E2> extends DataStruct  {

	// keep track of the graph nodes; useful
	// to maintain their properties

	private final HashMap<K, Element<E1> > vertices;

	// holds the adjacency list of edges

	private final HashMap < K, SLelement < Edge< K, E2 > > > adj_list;

	private final int LARGE_GRAPH_VERT_SIZE = 1000;

	/**
	 *
	 *	Constructor
	 */
	public GraphAdjList() {
		vertices = new HashMap<K, Element<E1> >();
		adj_list = new HashMap<K, SLelement<Edge<K, E2> > >();
	}

	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 *
	 */
	public String getDataStructType() {
		if (this.vertices.size() > LARGE_GRAPH_VERT_SIZE)
			return "largegraph";
		return "GraphAdjacencyList";
	}
	/**
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
	 *	Adds a new edge to the graph, adds it to that vertex's
	 *	adjacency list; user is responsible for checking if the
	 *	vertex already exists. This version assumes a default edge
	 * 	weight of 1.
	 *
	 *	@param src - source vertex of edge
	 *	@param dest - destination  vertex of edge
	 *
	 */
	public void addEdge(K src, K dest) {
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
		adj_list.put(src, new SLelement<Edge<K, E2>>(new Edge<K, E2>(1, dest),
				adj_list.get(src) ) );
	}

	/**
	 *	Adds a new edge to the graph, adds it to that vertex's
	 *	adjacency list; user is responsible for checking if the
	 *	vertex already exists.
	 *
	 *	@param src - source vertex of edge
	 *	@param dest - destination  vertex of edge
	 *	@param weight - edge weight
	 *
	 */
	public void addEdge(K src, K dest, int weight) {
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
		adj_list.put(src, new SLelement<Edge<K, E2>>(
				new Edge<K, E2>(weight, dest), adj_list.get(src) ) );
	}
	/**
	 *	Sets data for a graph vertex
	 *
	 *	@param src - source vertex of edge
	 *	@param vertex_data - vertex data
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
	 *	Gets data for an edge
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
	 *	Sets data for an edge
	 *
	 *	@param src - source vertex of edge
	 *	@param dest - destination  vertex of edge
	 *	@param edge_data - edge data
	 *
	 */
	public void setEdgeData(K src, K dest, E2 edge_data) {
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
		// look for the edge

		SLelement<Edge<K, E2>> sle = adj_list.get(src);
		while (sle != null) {
			K edge_dest = ((Edge<K, E2>) sle.getValue()).getVertex();
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
	 *	Gets data for an edge
	 *
	 *	@param src - source vertex of edge
	 *	@param dest - destination  vertex of edge
	 *
	 */
	public E2 getEdgeData(K src, K dest) {
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
		// look for the edge
		SLelement<Edge<K, E2>> sle = adj_list.get(src);
		while (sle != null) {
			K edge_dest = ((Edge<K, E2>) sle.getValue()).getVertex();
			if (edge_dest.equals(dest)) 	// found
				return sle.getValue().getEdgeData();
			sle = sle.getNext();
		}
		if (sle == null)
			throw new NullPointerException("Edge from " + src + " to " + dest +
				" does not exist!");

		// should never reach here
		return null;
	}
	/**
	 *	This method returns the graph nodes
	 *
	 *	return -- vertices held in  in the hashmap
	 *
	 */
	public HashMap<K, Element<E1> > getVertices() {
		return vertices;
	}
	/**
	 *	This is a convenience method to retrieve a vertex given
	 *	its key
	 *
	 *	return -- graph vertex corresponding to its key
	 *
	 */
	public Element<E1> getVertex(K key) {
		return vertices.get(key);
	}

	/**
	 *	Gets the adjacency list (of type SLelement<Edge> )
	 *
	 *	@return - the graph's adjacency lists
	 *
	 */
	public HashMap<K, SLelement<Edge<K, E2> > > getAdjacencyList() {
		return adj_list;
	}
	/**
	 *	Gets the adjacency list (of type SLelement<Edge <K> >  of a vertex)
	 *
	 *	@param - vertex key
	 *
	 *	@return - the graph's adjacency list  corresponding to this vertex
	 */
	public SLelement<Edge<K, E2> >  getAdjacencyList(K vertex) {
		return adj_list.get(vertex);
	}
	/**
	 *
	 *	 This is a convenience method to simplify access to the link visualizer;
	 *	 the method assumes the vertex names point to existing vertices, else an exception
	 *	 is thrown
	 *
	 */
	public LinkVisualizer getLinkVisualizer (K src, K dest) throws Exception {
		// get the source and destination vertex elements
		// and check to see if they exist
		Element<E1> v1 = vertices.get(src);
		Element<E1> v2 = vertices.get(dest);
		try {
			if (v1 == null || v2 == null) {
				throw new NullPointerException("Vertex " + src + " or " + dest +
					" does not exist! First add the vertices to the graph.");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return v1.getLinkVisualizer(v2);
	}
	/**
	 *
	 *	This is a convenience method to simplify access to the element visualizer;
	 *	the method assumes the vertex name points to an existing vertice, else an
	 *	exception is thrown
	 *
	 */
	public ElementVisualizer getVisualizer (K vertex) throws Exception {
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


	/*
	 *	Get the JSON representation of the the data structure
	 */
	public String getDataStructureRepresentation() {
		if (this.vertices.size() > LARGE_GRAPH_VERT_SIZE) {
			return getDataStructureLargeGraph();
		}
		// map to reorder the nodes for building JSON
		HashMap<Element<E1>, Integer> node_map = new HashMap<Element<E1>, Integer>();
		// get teh list nodes
		Vector<Element<E1> > nodes = new Vector<Element<E1>> ();

		for (Entry<K, Element<E1>> element : vertices.entrySet())
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
		for (Entry<K, SLelement<Edge<K, E2>>> a_list : adj_list.entrySet()) {
			SLelement<Edge<K, E2>> list = a_list.getValue();
			// get the source vertex index for the JSON (int)
			Element<E1> src_vert = vertices.get(a_list.getKey());
			while (list != null) {
				Integer src_indx = node_map.get(src_vert);
				// get the destination vertex index for the JSON (int)
				Edge<K, E2> edge = list.getValue();
				Element<E1> dest_vert = vertices.get(edge.getVertex());
				Integer dest_indx = node_map.get(dest_vert);
				// get link representation
				links_JSON.append(list.getLinkRepresentation(src_vert.getLinkVisualizer(dest_vert),
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
		Vector<Element<E1>> nodes = new Vector<Element<E1>>();

		for (Entry<K, Element<E1>> element : vertices.entrySet())
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

		for (Entry<K, SLelement<Edge<K, E2>>> a_list : adj_list.entrySet()) {
			SLelement<Edge<K, E2>> list = a_list.getValue();
			Element<E1> src_vert = vertices.get(a_list.getKey());
			while (list != null) {
				Integer src_indx = node_map.get(src_vert);
				Edge<K, E2> edge = list.getValue();
				Element<E1> dest_vert = vertices.get(edge.getVertex());
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
