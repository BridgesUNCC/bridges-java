package sketch;

import java.util.HashMap;

/**
 * An implementation of AbstractVertex with HashMap for adjacency.
 *  
 * @author Sean Gallagher
 */
public class Vertex extends AbstractVertex {

	private GraphVisualizer graph;
	
	public Vertex(String identifier, GraphVisualizer graph) {
		super(identifier);
		incoming = outgoing = new HashMap<>();		 
		
		this.graph = graph;
		
		//adds a vertex to the map
		graph.vertices.put(identifier, this);		
	}
	
	//removes the vertex that called the method
	public void remove(){
		graph.vertices.remove(this);
	}
}
