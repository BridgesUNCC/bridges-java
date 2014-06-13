package sketch;

public class StackEdge extends AbstractEdge {
	
	private StackNode edgeOutgoing;
	private String edgeIdentifier;//do we need this?
	
	/**
	 * Creates a new StackEdge and connects it to the destination StackNode.
	 * 
	 * @param source The source StackNode.
	 * @param destination The destination StackNode.
	 * @param edgeIdentifier The name of the StackEdge.
	 */
	public StackEdge(StackNode source, StackNode destination, String edgeIdentifier){
		super(source, destination, edgeIdentifier);
		
		edgeOutgoing = destination;		
	}
	
	public void setEdgeOutgoing(StackNode n){
		this.edgeOutgoing = n;
	}
	
	public StackNode getEdgeOutgoing(){
		return edgeOutgoing;
	}
	
	public String getEdgeIdentifier(){
		return edgeIdentifier;
	}

}
