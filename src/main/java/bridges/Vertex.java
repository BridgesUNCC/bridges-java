package bridges;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An implementation of AbstractVertex with HashMap for adjacency.
 *  
 * @author Sean Gallagher
 */
public class Vertex extends AbstractVertex {

	//private GraphVisualizer graph;
	/**
	 * Creates and vertex and adds it to the graph.
	 * @param identifier Name of the vertex.
	 * @param graph THe graph the vertex is added to.
	 */
	public Vertex(String identifier, GraphVisualizer graph) {
		super(identifier);
		//outgoing =  new HashMap<>();
		outgoing = new ArrayList<AbstractEdge>();//creates empty list of connected edges
		//this.graph = graph;
		
		//adds a vertex to the map	
		graph.vertices.put(identifier, this);		
	}
	
	/**
	 * Creates an edge between the calling vertex and a passed vertex.
	 * 
	 * @param v2 The second vertex that edge is between.
	 */
	public void createEdge(Vertex v2){
		String ident = this.getIdentifier() +"To"+ v2.getIdentifier();
		new Edge(this, v2, ident);
	}
	
	public AbstractEdge getEdge(AbstractVertex v2){
		
		for(int i = 0; i < this.outgoing.size(); i++){
			//if(this.outgoing(i) == v2.outgoing(i))
		}

		
		return null;
	}
}
