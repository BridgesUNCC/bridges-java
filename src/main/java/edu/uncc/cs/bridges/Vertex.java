package edu.uncc.cs.bridges;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * An implementation of AbstractVertex with HashMap for adjacency.
 *  
 * @author Sean Gallagher
 */
public class Vertex<T> extends AbstractVertex<T> {

	public int currVertexIndex=-1; //this holds the number of visited children belonging to the current vertex 
					    //in the the list of the children vertices
						// by default initialized to -1 meaning no children
	public AbstractVertex<T> curr=null; //holds the pointer to the next() child of this vertex
	public int currEdgeIndex = -1;
	//private GraphVisualizer graph;
	/**
	 * Creates and vertex and adds it to the graph.
	 * @param identifier Name of the vertex.
	 * @param graph The graph the vertex is added to.
	 */
	
	
	public Vertex(T identifier, GraphVisualizer graph) {
		super(identifier);

		outgoing = new ArrayList<AbstractEdge<T>>();//creates empty list of connected edges

		
		//adds a vertex to the map	
		graph.vertices.put(identifier, this);		
	}
	
	/**
	 * Creates an edge between the calling vertex and a passed vertex.
	 * 
	 * @param v2 The second vertex that edge is between.
	 */
	public AbstractEdge<T> createEdge(Vertex<T> v2){
		//identifier is to be used internally to find the Edges later
		AbstractEdge<T> temp=getEdge(v2);
		if (temp==null && this.equals(v2)){
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
	public AbstractEdge<T> createEdge(Vertex<T> v2, double weight){
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
	public AbstractEdge<T> createEdge(Vertex<T> v2, String randWeight){
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
	public AbstractEdge<T> getEdge(AbstractVertex<T> v2){
		for(int i = 0; i < this.outgoing.size(); i++){ 
			AbstractEdge<T> anEdge=this.outgoing.get(i);
				if(anEdge.destination.compareTo(v2)==0){				
					return this.outgoing.get(i);
			}
		}		
		return null;
	}
	
	/**
	 * This method returns the next child of the current vertex as specified by the index integer value
	 * if the index value is above or below the number of children vertices it returns null
	 * @param anIndex represents the index of the child vertex
	 * @return AbstractVertex 
	 */
	public AbstractVertex<T> next(int anIndex){
		if (anIndex<0 || anIndex>=outgoing.size())
			return null;
		AbstractEdge<T> anEdge =this.outgoing.get(anIndex); 
		if(anEdge.eOutgoing.get(0).equals(this))
			return curr=anEdge.eOutgoing.get(1); //returns the vertex corresponding to edge destination
											// where .get(0) is the vertex source for the edge (equivalent to the parent)
		return curr=anEdge.eOutgoing.get(0); 		//vice versa, if the destination is actually the current vector it returns the source
											//after all, GraphVisualizer is an undirected graph
	}
	/**
	 * This method returns the next child of the current vertex
	 * If no children nodes are present it returns null
	 * it also keeps track of the last next() method call and returns the following child
	 * @return AbstractVertex representing the next child of the currrent vertex
	 */
	public AbstractVertex<T> next(){
		if (currVertexIndex==-1 && !this.outgoing.isEmpty()){
			currVertexIndex=0;
		}
		if(currVertexIndex!=-1 && currVertexIndex!=this.outgoing.size()){
			return this.next(currVertexIndex++);}
		else 
			return null;
	}

	@Override
	public Vertex<T> setColor(String color) {
		// TODO Auto-generated method stub
		super.setColor(color);
		return this;
	}

	@Override
	public Vertex<T> setShape(String shape) {
		// TODO Auto-generated method stub
		super.setShape(shape);
		return this;
	}

	@Override
	public Vertex<T> setSize(double pixels) {
		// TODO Auto-generated method stub
		super.setSize(pixels);
		return this;
	}

	@Override
	public Vertex<T> setOpacity(double opacity) {
		// TODO Auto-generated method stub
		super.setOpacity(opacity);
		return this;
	}
	/**
	 * This method returns a collection of exiting edges emerging from the current vertex
	 * @return
	 */
	public Collection<? extends Vertex<T>> getNeighbors() {
		Collection edges = new HashSet();
		//Iterator i = new this.outgoing.iterator();
		
		edges.addAll(this.outgoing);
		if (!edges.isEmpty())
			return edges;
		return null;
	}
	
	/**
	 * This method returns the next unvisited edge from the current vertex's list of emerging edges
	 * if the list is empty (no edges) it returns null
	 * if the method is called repeatedly one can iterate, one edge at a time, through the entire list of existing edges
	 * @return
	 */
	public AbstractEdge<T> nextEdge(){
		if (this.outgoing.isEmpty() || ++currEdgeIndex == this.outgoing.size()){
			currEdgeIndex = -1; //the counter is reset 
			return null;
		}
		
		return this.outgoing.get(currEdgeIndex);
			
	}
}
