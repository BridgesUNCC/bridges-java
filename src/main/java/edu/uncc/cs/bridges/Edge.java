package edu.uncc.cs.bridges;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Generic graph link, with visual components. 
 */
public class Edge extends AbstractEdge{

	/**
	 * Takes two vertices and creates and edge between them.
	 * 
	 * @param source The source vertex.
	 * @param destination The destination vertex.
	 * @param identifier The name of the Edge 
	 */
	public Edge(AbstractVertex source, AbstractVertex destination, String identifier) {		
		super(source, destination, identifier);
		
		if(source == null || destination == null){
			throw new IllegalArgumentException("Source and Destination nodes cannot be NULL.");
		}
		//automates the connection process
		//creates both connections   O  ->  O
		//							    <-
		
		//Maybe put a check making sure the source and destination exist?		
		eOutgoing = new ArrayList<AbstractVertex>();//each edge gets a list (of 2) connected vertices
		//creates outgoing links from the associated vertices to the 'Edge'
		//Adds the this edge to the lists associated with the two connecting vertices
		source.outgoing.add(this);
		destination.outgoing.add(this);
		identifier = source.getIdentifier() + destination.getIdentifier();
		//creates outgoing links from the 'Edge' to the associated Vertices
		//Adds the destination and the source to the list of vertices connected to the edge
		this.eOutgoing.add(source);
		this.eOutgoing.add(destination);		
	}
	
	/**
	 * Takes two vertices and creates and edge between them giving the Edge a weight.
	 * 
	 * @param source The source vertex.
	 * @param destination The destination vertex. 
	 * @param identifier The name of the Edge
	 * @param weight The weight(double) of the Edge 
	 */
	public Edge(AbstractVertex source, AbstractVertex destination, String identifier, double weight) {		
		
		//calls base Edge constructor, for clarity sake, instead of doing the same instructions again
		this(source, destination, identifier);
		this.setWeight(weight);
	}
	
	/**
	 * Takes two vertices and creates and edge between them giving the Edge a weight.
	 * 
	 * @param source The source vertex.
	 * @param destination The destination vertex. 
	 * @param identifier The name of the Edge
	 * @param weight The weight(String) of the Edge 
	 */
	public Edge(AbstractVertex source, AbstractVertex destination, String identifier, String randWeight) {		
		//calls base Edge constructor, for clarity sake, instead of doing the same instructions again
		this(source, destination, identifier);
		this.setWeight(source, destination, randWeight);

	}
	@Override
	public Edge setColor(String color) {
		// TODO Auto-generated method stub
		super.setColor(color);
		return this;
	}


	@Override
	public Edge setWidth(double pixels) {
		// TODO Auto-generated method stub
		super.setWidth(pixels);
		return this;
	}

	@Override
	public Edge setOpacity(double opacity) {
		// TODO Auto-generated method stub
		super.setOpacity(opacity);
		return this;
	}
	
}
