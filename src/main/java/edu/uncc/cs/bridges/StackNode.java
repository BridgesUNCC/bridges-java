package edu.uncc.cs.bridges;

public class StackNode extends AbstractVertex {
	
	private StackEdge nodeOutgoing;
	
	/**
	 * Creates a new StackNode.
	 * 
	 * @param nodeIdentifier The name of the new StackNode.
	 */
	public StackNode(String nodeIdentifier){
		super(nodeIdentifier);
				
	}
	/**
	 * Creates a StackEdge and connects it to the calling StackNode and the passed StackNode.
	 * 
	 * @param n The StackNode that is being connected to.
	 */
	private void setEdge (StackNode n){
		//TODO make identifier if needed
		this.nodeOutgoing = new StackEdge(this, n);//returns an edge pointing to the next node
	}
	/**
	 * Returns the connected StackEdge to the calling StackNode.
	 * 
	 * @return The connected StackEdge.
	 */
	public StackEdge getOutgoing(){
		return this.nodeOutgoing;
	}
	/**
	 * Creates the connecting StackNode and the resulting StackEdge.
	 * 
	 * @param n The next StackNode in the stack.
	 * @throws Exception Verifies the calling and passed StackNodes are not null.
	 */
	public void setNextNode(StackNode n) throws Exception{
		if(this == null || n == null){
			throw new Exception("New node not created, one or more nodes are NULL.");
		}else{
			this.setEdge(n);
		}
	}
	/**
	 * Returns the next node via the connected StackEdge.
	 * 
	 * @return The next StackNode in the stack.
	 */
	public StackNode getNextNode(){
		if(this.nodeOutgoing == null){
			return null;
		}else return this.nodeOutgoing.getEdgeOutgoing();
	}
}
