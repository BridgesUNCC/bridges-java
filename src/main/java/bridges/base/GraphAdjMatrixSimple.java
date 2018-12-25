package bridges.base;

import bridges.base.GraphAdjMatrix;

/**
 *
 *	@brief The GraphAdjMatrixSimple class is a simplification of the
 *	GraphAdjList class; this class is useful in applications where vertex
 *	and edge specific information is not used; this class is thus a
 *	specialization of GraphAdjList with only a single generic parameter that
 *	specifies the key type.
 *
 *	The GraphAdjMatrixSimple class can be used to represent adjacency list based
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
 *	The vertices of the graph are held in a Java hashmap of hashmaps(2D hashmap),
 *	for near constant time access; this lets us use strings or integral ids for
 *	vertices. Edge information is also held in a hashmap with accessor methods.
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
 *	@param <K>  orderable key (string, int, etc) that is used to index into vertex
 *
 *	\sa Example tutorial at <p>
 *		http://bridgesuncc.github.io/Hello_World_Tutorials/Graph.html
 *
 */
public class GraphAdjMatrixSimple<K> extends GraphAdjMatrix<K, K, K> {};

