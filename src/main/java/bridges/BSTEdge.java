package bridges;

import java.util.ArrayList;

public class BSTEdge extends AbstractEdge{

	private BSTNode out;
	/**
	 * Creates an Edge between two nodes.
	 * 
	 * @param source The source node.
	 * @param destination The destination node.
	 */
	public BSTEdge(BSTNode source, BSTNode destination) {		
		super(source, destination, "");
		
		eOutgoing = new ArrayList<AbstractVertex>();
		
		out = destination;//edge outgoing pointer to the destination vertex	
		
		//source Node -> Edge    destination Node
		source.outgoing.add(this);
		//test
		destination.outgoing.add(this);
		
		//this.eOutgoing.add(source);
		
		//source Node -> Edge -> destination Node
		this.eOutgoing.add(destination);
		//test
		this.eOutgoing.add(source);

	}
	/**
	 * Sets the outgoing pointer of the calling edge.
	 * 
	 * @param n The new node being referenced.
	 */
	public void setOutgoing(BSTNode n){
		this.out = n;
	}
	/**
	 * 
	 * @return The pointer of the outgoing connection.
	 */
	public BSTNode getOutgoing(){
		return out;
	}
}
