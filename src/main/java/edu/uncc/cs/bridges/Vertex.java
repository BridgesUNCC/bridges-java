package edu.uncc.cs.bridges;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An implementation of AbstractVertex with HashMap for adjacency.
 *  
 * @author Sean Gallagher
 */
public class Vertex extends AbstractVertex {

	public int curr=-1; //this holds the pointer to the current vertex 
					    //in the the list of the children vertices
						// by default initialized to -1 meaning no children
	
	//private GraphVisualizer graph;
	/**
	 * Creates and vertex and adds it to the graph.
	 * @param identifier Name of the vertex.
	 * @param graph The graph the vertex is added to.
	 */
	
	
	public Vertex(String identifier, GraphVisualizer graph) {
		super(identifier);

		outgoing = new ArrayList<AbstractEdge>();//creates empty list of connected edges

		
		//adds a vertex to the map	
		graph.vertices.put(identifier, this);		
	}
	
	/**
	 * Creates an edge between the calling vertex and a passed vertex.
	 * 
	 * @param v2 The second vertex that edge is between.
	 */
	public AbstractEdge createEdge(Vertex v2){
		//identifier is to be used internally to find the Edges later
		AbstractEdge temp=getEdge(v2);
		if (temp==null){
			String ident = this.getIdentifier() +"To"+ v2.getIdentifier();
			return new Edge(this, v2, ident);
		}
		return null;
	}
	/**
	 * Creates an edge between the calling vertex and a passed vertex.
	 * It attaches the weight attribute to an Edge
	 * @param v2 The second vertex that edge is between.
	 * @param weight Contains the weight value as a double
	 */
	public AbstractEdge createEdge(Vertex v2, double weight){
		//identifier is to be used internally to find the Edges later
		createEdge(v2);
		this.getEdge(v2).setWeight(weight);
		return this.getEdge(v2);
	}
	
	/**
	 * Creates an edge between the calling vertex and a passed vertex.
	 * It attaches the weight attribute to an Edge and assigns it a random value
	 * @param v2 The second vertex that edge is between.
	 * @param weight Contains the weight value as a string "randWeight", later 
	 * transformed in a random double between 0.0-9.0
	 */
	public AbstractEdge createEdge(Vertex v2, String randWeight){
		//identifier is to be used internally to find the Edges later
			createEdge(v2);
			this.getEdge(v2).setWeight(this, v2, randWeight);
			return this.getEdge(v2);
	}
	
	/**
	 * Returns the Edge between two Vertices.
	 * 
	 * @param v2 The second vertex.
	 * @return The associated Edge.
	 */
	public AbstractEdge getEdge(AbstractVertex v2){
		for(int i = 0; i < this.outgoing.size(); i++){ 
			AbstractEdge anEdge=this.outgoing.get(i);
				if(anEdge.destination.compareTo(v2)==0){				
					return this.outgoing.get(i);
			}
		}		
		return null;
	}
	
	public AbstractVertex next(int anIndex){
		if (anIndex<0 || anIndex>=outgoing.size())
			return null;
		return outgoing.get(anIndex).eOutgoing.get(0);
	}
	
	public AbstractVertex next(){
		return outgoing.get(curr).eOutgoing.get(0);
	}

	@Override
	public Vertex setColor(String color) {
		// TODO Auto-generated method stub
		super.setColor(color);
		return this;
	}

	@Override
	public Vertex setShape(String shape) {
		// TODO Auto-generated method stub
		super.setShape(shape);
		return this;
	}

	@Override
	public Vertex setSize(double pixels) {
		// TODO Auto-generated method stub
		super.setSize(pixels);
		return this;
	}

	@Override
	public Vertex setOpacity(double opacity) {
		// TODO Auto-generated method stub
		super.setOpacity(opacity);
		return this;
	}
}
