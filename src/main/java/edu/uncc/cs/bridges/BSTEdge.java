package edu.uncc.cs.bridges;

public class BSTEdge<T> extends AbstractEdge<T>{
/**
 * Edge class specifically for trees due to their hierarchical nature.
 * @author mvitulli
 */
	private BSTNode<T> out;
	/**
	 * Creates an Edge between two nodes.
	 * 
	 * @param source The source node.
	 * @param destination The destination node.
	 */
	public BSTEdge(BSTNode<T> source, BSTNode<T> destination) {		
		super(source, destination, null);
		out = destination;//edge outgoing pointer to the destination vertex	
	}
	/**
	 * Sets the outgoing pointer of the calling edge.
	 * 
	 * @param n The new node being referenced.
	 */
	public void setOutgoing(BSTNode<T> n){
		this.out = n;
	}
	/**
	 * 
	 * @return The pointer of the outgoing connection.
	 */
	public BSTNode<T> getOutgoing(){
		return out;
	}
}
