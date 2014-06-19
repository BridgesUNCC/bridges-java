package sketch;

public class StackEdge extends AbstractEdge {
	
	private StackNode edgeOutgoing;
	
	/**
	 * Creates a new StackEdge and connects it to the destination StackNode.
	 * 
	 * @param source The source StackNode.
	 * @param destination The destination StackNode.
	 */
	public StackEdge(StackNode source, StackNode destination){
		super(source, destination);
		
		edgeOutgoing = destination;		
	}
	
	public void setEdgeOutgoing(StackNode n){
		this.edgeOutgoing = n;
	}
	
	public StackNode getEdgeOutgoing(){
		return edgeOutgoing;
	}
}
