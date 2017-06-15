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
 *	Convenience methods are provided to add vertices and edges to the graph as well as
 *	retrieve the adjacency list of a vertex, given its id.
 *
 *	@author Kalpathi Subramanian
 *
 *	@date 6/29/15, 5/18/17
 *
 *	@param <E> application/user defined type used as part of vertices and edges
 *	@param <K> orderable key (string, int, etc) that is used to index into vertex
 *		structure, for fast access
 *
 *	\sa Example tutorial at <p>
 *		http://bridgesuncc.github.io/Hello_World_Tutorials/Graph.html
 *
 */
public class GraphAdjList<K, E> extends DataStruct {

	// keep track of the graph nodes; useful
	// to maintain their properties

	private final HashMap<K, Element<E> > vertices;

	// holds the adjacency list of edges

	private final HashMap<K, SLelement<Edge<K> > > adj_list;

	/**
	 *
	 *	Constructor
	 */
	public GraphAdjList() {
		vertices = new HashMap<K, Element<E> >();
		adj_list = new HashMap<K, SLelement<Edge<K> > >();
	}

	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 *
	 */
	public String getDataStructType() {
		return "GraphAdjacencyList";
	}
	/**
	 *	Adds a new vertex to the graph, initializes the  adjacency
	 *	list; user is responsible for checking if the vertex already
	 *	exists. This method will replace the value for this key
	 *
	 *	@param k - vertex id
	 *	@param E - vertex info, currently used as a label by default
	 *
	 *	@return none
	 */

	public void addVertex(K k, E e) {
		// note: it is the user's responsibility to  check
		// for duplicate vertices
		vertices.put(k, new Element<E>(e));
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
		adj_list.put(src, new SLelement<Edge<K>>(new Edge<K>(1, dest),
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
		adj_list.put(src, new SLelement<Edge<K>>(new Edge<K>(weight, dest),
				adj_list.get(src) ) );
	}
	/**
	 *	This method returns the graph nodes
	 *
	 *	return -- vertices held in  in the hashmap
	 *
	 */
	public HashMap<K, Element<E> > getVertices() {
		return vertices;
	}
	/**
	 *	This is a convenience method to retrieve a vertex given
	 *	its key
	 *
	 *	return -- graph vertex corresponding to its key
	 *
	 */
	public Element<E> getVertex(K key) {
		return vertices.get(key);
	}

	/**
	 *	Gets the adjacency list (of type SLelement<Edge> )
	 *
	 *	@return - the graph's adjacency lists
	 *
	 */
	public HashMap<K, SLelement<Edge<K> > > getAdjacencyList() {
		return adj_list;
	}
	/**
	 *	Gets the adjacency list (of type SLelement<Edge <K> >  of a vertex)
	 *
	 *	@param - vertex key
	 *
	 *	@return - the graph's adjacency list  corresponding to this vertex
	 */
	public SLelement<Edge<K> >  getAdjacencyList(K vertex) {
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
		Element<E> v1 = vertices.get(src);
		Element<E> v2 = vertices.get(dest);
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
		Element<E> v = vertices.get(vertex);
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
		HashMap<Element<E>, Integer> node_map = new HashMap<Element<E>, Integer>();
		// get teh list nodes
		Vector<Element<E> > nodes = new Vector<Element<E>> ();

		for (Entry<K, Element<E>> element : vertices.entrySet())
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
		for (Entry<K, SLelement<Edge<K>>> a_list : adj_list.entrySet()) {
			SLelement<Edge<K>> list = a_list.getValue();
			// get the source vertex index for the JSON (int)
			Element<E> src_vert = vertices.get(a_list.getKey());
			while (list != null) {
				Integer src_indx = node_map.get(src_vert);
				// get the destination vertex index for the JSON (int)
				Edge<K> edge = list.getValue();
				Element<E> dest_vert = vertices.get(edge.getVertex());
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
}
