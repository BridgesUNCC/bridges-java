package bridges;

public class BSTEdge extends AbstractEdge{
/**
 * Edge class specifically for trees due to their hierarchical nature.
 * @author mvitulli
 */
	private BSTNode out;
	/**
	 * Creates an Edge between two nodes.
	 * 
	 * @param source The source node.
	 * @param destination The destination node.
	 */
	public BSTEdge(BSTNode source, BSTNode destination) {		
		super(source, destination, "");
		out = destination;//edge outgoing pointer to the destination vertex	
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
