package bridges.base;

import bridges.base.GraphAdjList;

/**
 *
 *	@brief The GraphAdjListSimple class is a simplification of the
 *	GraphAdjList class; this class is useful in applications where vertex
 *	and edge specific information is not used; this class is thus a
 *	specialization of GraphAdjList with only a single generic parameter that
 *	specifies the key type.
 *
 *	The GraphAdjListSimple class can be used to represent adjacency list based
 *	graphs in BRIDGES; it takes 1 generic parameter: K, which is an orderable
 *	key value used in accessing vertices and edges (in constant time) using
 *	hashmaps. This permits data sets that need to be accessed by keys that are
 *	strings. Vertex and edge specific information can still be represented, but
 *	they will be restricted to be of type K.
 *
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
 *	Convenience methods are provided to add vertices and edges to the graph as
 *	well as retrieve the adjacency list of a vertex, given its id. Methods
 *	to access and set visual attributes are also provided, indexed by the vertex
 *	ids.
 *
 *	@author Kalpathi Subramanian
 *
 *	@date 4/24/18
 *
 *	@param K orderable key (string, int, etc) that is used to index into vertex
 *
 *	@sa Example tutorial at
 *		https://bridgesuncc.github.io/tutorials/Graph.html
 *
 *
 *
 */

public class GraphAdjListSimple<K> extends GraphAdjList<K, K, K> {};

