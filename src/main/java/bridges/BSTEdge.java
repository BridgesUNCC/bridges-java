package bridges;

public class BSTEdge extends AbstractEdge{

	private BSTNode outgoing;
	/**
	 * Creates an Edge between two nodes.
	 * 
	 * @param source The source node.
	 * @param destination The destination node.
	 */
	public BSTEdge(BSTNode source, BSTNode destination) {		
		super(source, destination);
		
		outgoing = destination;//edge outgoing pointer to the destination vertex	
	}
	/**
	 * Sets the outgoing pointer of the calling edge.
	 * 
	 * @param n The new node being referenced.
	 */
	public void setOutgoing(BSTNode n){
		this.outgoing = n;
	}
	/**
	 * 
	 * @return The pointer of the outgoing connection.
	 */
	public BSTNode getOutgoing(){
		return outgoing;
	}
}
