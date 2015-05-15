package edu.uncc.cs.bridgesV2.base;

import edu.uncc.cs.bridgesV2.validation.*;
import edu.uncc.cs.bridgesV2.base.SLelement;
import edu.uncc.cs.bridgesV2.base.Edge;

/**
 * This class is a helper class for representing adjacency lists in graph
 * based data structures.
 *
 * @param generic <E>
 */

public class GraphList<E extends Comparable<? super E>>{
					// vertex of the graph
	private SLelement<E> src_vertex;
					// corresponding adjacency list
	private SLelement<Edge> adj_list;

	/**
 	*  Constructor
	*  creates a GraphList object
	*/
	public GraphList(){
		super();
		src_vertex = null;
		adj_list = null;
	}

	/**
	*	return the source vertex
	*/

	public SLelement<E> getSourceVertex() {
		return src_vertex;
	}

	/**
	*	set the source vertex
	*   @param v  incoming vertex
	*/
	
	public void  setSourceVertex(SLelement<E> v) {
		src_vertex = v;
	}

	/**
	*	return the adjacency list 
	*/
	public SLelement<Edge> getAdjacencyList() {
		return adj_list;
	}

	/**
	*	set the source vertex
	*   @param list  incoming adjacency list
	*/
	
	public void  setAdjacencyList(SLelement<Edge> list) {
		adj_list = list;
	}

	/**
	*	add an edge to the adjacency list
	*   @param e incoming new edge 
	*/

	public void  addEdge(Edge e) {
								// insert at front
		adj_list = new SLelement<Edge> (e, adj_list);
	}

	/**
	*	add an edge to the adjacency list
	*   @param vertex vertex name
	*   @param weight edge weight 
	*/
	public void addEdge (String vertex, int weight) {
		adj_list = new SLelement<Edge> (new Edge(weight, vertex), adj_list);
	}
}
