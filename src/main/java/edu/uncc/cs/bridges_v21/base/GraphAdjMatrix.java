package bridges.base;

import bridges.base.Element;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @brief The GraphAdjMatrix class can be used to represent adjacency matrix 
 * based * graphs in BRIDGES. 

 * E represents a application data specific generic parameter, and K a key value
 *  (not used now)
 * The class uses Java Hashmaps  to implement a 2D array representation, 
 * near constant time access to the graph vertices and edges.
 * BRIDGES provides methods to visualize the graph  and its contents
 *
 * @author Kalpathi Subramanian, Mihai Mehedint 
 * @date 7/12/15
 *
 */


public class GraphAdjMatrix<K, E> extends DataStruct { 

	private int maxSize;

						// graph vertices list
	private final HashMap<K, Element<E> > vertices;

						// holds the adjacency list of edges
	private final HashMap<K, HashMap<K, Integer> > matrix;

	/// 
	/// Constructor 
	/// 
	public GraphAdjMatrix(int size) {
		maxSize = size;
		vertices = new HashMap<K, Element<E> >(size);
		matrix = new HashMap<K, HashMap<K, Integer>>(size);
	}	

	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 **/
	public String getDataStructType() {
		return "GraphAdjacencyMatrix";
	}
	/// 
	/// Adds a new vertex to the graph, initializes the  adjacency
	/// list; user is responsible for checking if the vertex already
	/// exists. This method will replace the value for this key
	/// 
	/// @param K - vertex key value 
	/// @return none
	/// 

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

	/// 
	/// Adds a new edge to the graph, adds it to that vertex's 
	/// adjacency list; user is responsible for checking if the 
	/// vertices already exist.
	/// 
	/// @param src - source vertex of edge
	/// @param dest - destination  vertex of edge
	/// @param weight - edge weight
	/// @return none
	/// 
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
	/// 
	/// This method returns the graph nodes
	/// 
	///@return -- vertices held in an unordered  map
	///
	///
	public HashMap<K, Element<E> > getVertices() {
		return vertices;
	}

	/// 
	/// Gets the adjacency matrix 
	/// 
	///
	/// @return - the graph's adjacency lists 
	///
	public HashMap<K, HashMap<K, Integer>> getAdjacencyMatrix() {
		return matrix; 
	}
};

