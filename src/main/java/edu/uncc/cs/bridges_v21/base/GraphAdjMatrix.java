package bridges.base;

import bridges.base.Element;

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
 *
 *  @author Kalpathi Subramanian, Mihai Mehedint
 *
 *  @date 7/12/15, 5/18/17
 *
 *  @param <E> application/user defined type used as part of vertices and edges
 *  @param <K> orderable key (string, int, etc) that is used to index into vertex
 *      structure, for fast access
 *
 *  \sa Example tutorial at <p>
 *      ?? TO DO
 *
 */

public class GraphAdjMatrix<K, E> extends DataStruct { 

	private int maxSize;

						// graph vertices list
	private final HashMap<K, Element<E> > vertices;

						// holds the adjacency list of edges
	private final HashMap<K, HashMap<K, Integer> > matrix;

	/**
	 *	Constructor 
	 */ 
	public GraphAdjMatrix(int size) {
		maxSize = size;
		vertices = new HashMap<K, Element<E> >(size);
		matrix = new HashMap<K, HashMap<K, Integer>>(size);
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
	public void addVertex(K k, E e) {
					// note: it is the user's responsibility to  check
					// for duplicate vertices
		try {
			if (vertices.size() == maxSize) {
				throw new IndexOutOfBoundsException();
			}
		}
		catch (IndexOutOfBoundsException ex) {
    		System.err.println(
			"IndexOutOfBoundsException: Max Vertices exceeded!"+ex.getMessage());
		}
		vertices.put(k, new Element<E>(e));
		vertices.get(k).setLabel((String) k);
								// create a hashmap for this vertex
		matrix.put(k, new HashMap<K, Integer>(maxSize));
								// fill up this vertex's row and column elements
		for (Entry<K, Element<E>> el: vertices.entrySet()) {
			(matrix.get(k)).put(el.getKey(), 0); // row
			(matrix.get(el.getKey())).put(k, 0); // col
		}
	}

	/**
	 *	Adds a new edge to the graph, adds it to the index corresponding to 
     *	the source, destination vertex ids;  this version of the method assumes
	 *	an edge weight of 1 (unweighted graph); user is responsible for checking if the 
	 *	vertices already exist, else an exception is thrown.
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
            	throw new NullPointerException("Vertex "+ src + " or " + dest +
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
            	throw new NullPointerException("Vertex "+ src + " or " + dest +
               	" does not exist! Add the vertex before creating the edge.");
			}
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		(matrix.get(src)).put(dest, weight);
	}
	/** 
	 *
	 *	This method returns the graph nodes
	 *
	 *	return -- vertices held in an unordered  map
	 *
	 */
	public HashMap<K, Element<E> > getVertices() {
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
				throw new NullPointerException("Vertex "+ src + " or " + dest +
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
};
