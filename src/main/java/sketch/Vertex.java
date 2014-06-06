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
		outgoing =  new HashMap<>();//incoming = 	 
		
		this.graph = graph;
		
		//adds a vertex to the map
		graph.vertices.put(identifier, this);		
	}
	//testing theory, I think this is better because it doesn't store the edge in memory. Just via pointers that can be erased.
	/**
	 * Creates an edge between the calling vertex and a passed vertex.
	 * 
	 * @param v2 The second vertex that edge is between
	 * @param identifier Name of the edge
	 */
	public void createEdge(Vertex v2, String identifier){
		Edge tempEdge = new Edge(this, v2, identifier);
	}
	/**
	 * Removes the vertex that is used to call this method from the GraphVisualizer.
	 */
	public void remove(){

		if(this.identifier != null){//removes vertex from map, not from memory though
			graph.vertices.remove(this.identifier);	
						
		}
		//TODO: message saying the vertex doesn't exist	
		
	}
	/**Removing an edge connected to the calling vertex.
	 * 
	 * @param edgeName The 'Edge' To be removed
	 */
	//Still needs to get the other vertex removed
	public void removeEdge(String edgeName){
		for(String key : edgeMap.keySet() ){//goes through the map of edges connected to the vertex
			if(edgeMap.keySet().equals(edgeName)){
				edgeMap.remove(edgeName);//once found removes it
				
			}
		}
		
	}
}
