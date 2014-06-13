package sketch;

import java.util.HashMap;

/**
 * An implementation of AbstractVertex with HashMap for adjacency.
 *  
 * @author Sean Gallagher
 */
public class Vertex extends AbstractVertex {

	private GraphVisualizer graph;
	/**
	 * Creates and vertex and adds it to the graph.
	 * @param identifier
	 * @param graph
	 */
	public Vertex(String identifier, GraphVisualizer graph) {
		super(identifier);
		outgoing =  new HashMap<>();
		
		this.graph = graph;
		
		//adds a vertex to the map
		graph.vertices.put(identifier, this);		
	}
	
	/**
	 * Creates an edge between the calling vertex and a passed vertex.
	 * 
	 * @param v2 The second vertex that edge is between.
	 * @param identifier Name of the edge being created.
	 */
	public void createEdge(String identifier, Vertex v2){
		new Edge(this, v2, identifier);
	}
	
	/**
	 * Removes the vertex that is used to call this method from the GraphVisualizer.
	 */
	public void remove(){

		if(graph.vertices.containsKey(this.identifier)){		
			graph.vertices.remove(this.identifier);	
						
		}else{
			System.out.println("The vertex " + this.identifier + " doesn't exist.");
		}
		
		//TODO: throw exception
		
	}
	
	/**
	 * Removes the edge contained between the two vertices used.
	 * 
	 * @param v2 The second vertex that makes up the edge
	 * @param edgeName The name of the edge to be removed
	 */
	public void removeEdge(String edgeName, Vertex v2){
		//goes through the map of edges connected to the vertex
		if(this.outgoing.containsKey(edgeName) && v2.outgoing.containsKey(edgeName)){
			this.outgoing.remove(edgeName);
			v2.outgoing.remove(edgeName);
		}else{
			System.out.println("The edge " + edgeName + " doesn't exist.");
		}
		
	}
}