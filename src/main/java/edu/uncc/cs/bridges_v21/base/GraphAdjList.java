package bridges.base;
import bridges.base.SLelement;
import bridges.base.Edge;


import java.util.HashMap;
import java.util.Map;

///
/// The GraphAdjList class can be used to represent adjacency list based 
/// graphs in BRIDGEs, with E represnting a data specific generic parameter.
///	The class is simply a wrapper  around the C++ STL  unordered_map class
///	and, thus, derives all its operations from it.
///	BRIDGES provides methods to visualize the graph  and its contents. 
///	
///	Author: Kalpathi Subramanian, 6/29/15
///	
///	@param Element<E>
///	

public class GraphAdjList<K, E> {
						// keep track of the graph nodes; useful
						// to maintain their properties
	private final HashMap<K, Element<E> > vertices;
						// holds the adjacency list of edges
	private final HashMap<K, SLelement<Edge<K> > > adj_list;

	/// 
	/// Constructor 
	/// 
	public GraphAdjList() {
		vertices = new HashMap<K, Element<E> >();
		adj_list = new HashMap<K, SLelement<Edge<K> > >();
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
		vertices.put(k, new Element<E>(e));
		vertices.get(k).setLabel((String)k);
		adj_list.put(k,  null);
	}

	/// 
	/// Adds a new edge to the graph, adds it to that vertex's 
	/// adjacency list; user is responsible for checking if the 
	/// vertex already exists.
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
		adj_list.put(src, new SLelement<Edge<K>>(new Edge<K>(weight, dest), 
							adj_list.get(src) ) ); 
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
	/// Gets the adjacency list (of type SLelement<Edge> )
	/// 
	///
	/// @return - the graph's adjacency lists 
	///
	public HashMap<K, SLelement<Edge<K> > > getAdjacencyList() {
		return adj_list; 
	}
	/// 
	/// Gets the adjacency list (of type SLelement<Edge>  of a vertex)
	/// 
	/// @param - vertex key
	///
	/// @return - the graph's adjacency list  corresponding to this vertex
	///
	public SLelement<Edge<K> >  getAdjacencyList(K vertex) {
		return adj_list.get(vertex); 
	}
};

