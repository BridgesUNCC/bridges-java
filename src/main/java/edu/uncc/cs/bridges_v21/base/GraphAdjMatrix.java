package bridges.base;

import bridges.base.Element;

import java.util.Vector;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 *  @brief The GraphAdjMatrix class can be used to represent adjacency matrix based
 *      graphs in BRIDGES
 *
 *  The GraphAdjMatrix class can be used to represent adjacency matrix based  graphs
 *  in BRIDGES; it takes 2 generic parameters: (1) K, which is an orderable
 *  key value used in accessing vertices (in constant time) using a hashmap. This
 *  permits data sets that need to be accessed by keys that are strings, and
 *  (2) E, an application defined type, and used in the Edge representation.
 *  The class is simply a wrapper  around the Java Hashmap class
 *  and, thus, derives all its operations from it.
 *  BRIDGES provides methods to visualize the graph  and its contents.
 *
 *  The vertices of the graph are held in a Java hashmap, for near constant time access;
 *  this lets us use strings or integral ids for vertices. The edges are accessed
 *	by a second hashmap from each vertex, again assuring near constant access time.
 *  Each edge contains the terminating vertex id and weight, as defined by  the Edge
 *	class structure.
 *
 *  Convenience methods are provided to add vertices and edges to the graph. Edges
 *  are retrieved by using the dual hashmap, given the vertex ids of the edge. 
 *	Methods to access the element and link visualizer are now provided, indexed
 *  vertex ids, making it easier to set visual attributes to graph nodes and 
 *	links.
 *
 *  @author Kalpathi Subramanian, Mihai Mehedint
 *
 *  @date 7/12/15, 5/18/17, 4/23/18
 *
 *  @param <K> orderable key (string, int, etc) that is used to index into vertex
 *  @param <E1> vertex specific information, for graph vertices
 *  @param <E2> edge specific information, for graph vertices
 *
 *  \sa Example tutorial at <p>
 *      ?? TO DO
 *
 */

public class GraphAdjMatrix<K, E1, E2> extends DataStruct {

	// graph vertices list
	private final HashMap<K, Element<E1> > vertices;

	// holds the adjacency list of edges
	private final HashMap<K, HashMap<K, Integer> > matrix;

	// holds edge information of type E2 for graph edges
	private final HashMap<K, HashMap<K, E2>> edge_data;

	/**
	 *	Constructor
	 */
	public GraphAdjMatrix() {
		vertices = new HashMap<K, Element<E1> >();
		matrix = new HashMap<K, HashMap<K, Integer>>();
		edge_data = new HashMap<K, HashMap<K, E2>>();
	}

	/**
	 *
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 *
	 */
	public String getDataStructType() {
		return "GraphAdjacencyMatrix";
	}

	/**
	 *	Adds a new vertex to the graph, initializes the  adjacency
	 *	list; user is responsible for checking if the vertex already
	 *	exists. This method will replace the value for this key
	 *
	 *	@param k - vertex key value
	 *	@param 3 - user specified data, part of the vertex data
	 *
	 */
	public void addVertex(K k, E1 e) {
		vertices.put(k, new Element<E1>(e));
		vertices.get(k).setLabel((String) k);

							// create a hashmap for this vertex
		matrix.put(k, new HashMap<K, Integer>());
							// create a hashmap for edge data for this vertex
		edge_data.put(k, new HashMap<K, E2>());


							// fill up this vertex's row and column elements
		for (Entry<K, Element<E1>> el : vertices.entrySet()) {
			(matrix.get(k)).put(el.getKey(), 0); // row
			(matrix.get(el.getKey())).put(k, 0); // col
		}
	}

	/**
	 *
	 *	Adds a new edge to the graph, adds it to the index corresponding to
	 *	the source, destination vertex ids;  this version of the method assumes
	 *	an edge weight of 1 (unweighted graph); user is responsible for 
	 *	checking if the vertices already exist, else an exception is thrown.
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
		(matrix.get(src)).put(dest, 1);
	}
	/**
	 *	Adds a new edge of weight 'weight' to the graph, adds it to the index
	 *	corresponding to the source, destination vertex ids;  user is responsible
	 *	for checking if the vertices already exist, else an exception is thrown.
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
		(matrix.get(src)).put(dest, weight);
	}
	/**
	 *	Sets data for a graph vertex
	 *	
	 *	@param src - source vertex of edge
	 *	@param dest - destination  vertex of edge
	 *	@param data - vertex data
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
	 *	@param dest - destination  vertex of edge
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
	 *	@param data - edge data
	 *
	 */
	public void setEdgeData(K src, K dest, E2 data) {
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
							// add edge data
		(edge_data.get(src)).put(dest, data);
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
		return (edge_data.get(src)).get(dest);
	}
	/**
	 *
	 *	This method returns the graph nodes
	 *
	 *	return -- vertices held in an unordered  map
	 *
	 */
	public HashMap<K, Element<E1> > getVertices() {
		return vertices;
	}

	/**
	 *
	 *	Gets the adjacency matrix
	 *
	 *	@return - the graph's adjacency matrix
	 */
	public HashMap<K, HashMap<K, Integer>> getAdjacencyMatrix() {
		return matrix;
	}
	/**
	 *
	 *	 This is a convenience method to simplify access to the link visualizer;
	 *	 the method assumes the vertex names point to existing vertices, 
	 *	 else an exception is thrown
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
		if (nodes.size() != 0) 	// only if there is at least one node
			nodes_JSON.setLength(nodes_JSON.length() - 1);

		// build the links JSON - traverse the adj. lists
		StringBuilder links_JSON = new StringBuilder();
		for (Entry<K, HashMap<K, Integer> > el_src : matrix.entrySet()) {

			Element<E1> src_vert = vertices.get(el_src.getKey());
			Integer src_indx = node_map.get(src_vert);

			for (Entry<K, Integer> el_dest : el_src.getValue().entrySet()) {
				Element<E1> dest_vert = vertices.get(el_dest.getKey());
				if (el_dest.getValue() > 0) {
					Integer dest_indx = node_map.get(dest_vert);

					links_JSON.append(src_vert.getLinkRepresentation(src_vert.getLinkVisualizer(dest_vert),
							Integer.toString(src_indx), Integer.toString(dest_indx)))
					.append(COMMA);
				}
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
};
