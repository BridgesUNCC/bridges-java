package bridges;

public class DLListEdge extends AbstractEdge {
	
	/**
	 * Pointers to reference the 'Next' and 'Previous' Nodes connected to this Edge.
	 */
	private DLListNode prevNode, nextNode;
	
	/**
	 * The constructor for an Edge in a Doubly Linked List. Connects the Edge to the two passed Nodes.
	 * 
	 * @param prev The previous Node in the list.
	 * @param next The next Node in the list.
	 */
	public DLListEdge(DLListNode prev, DLListNode next){
		super(prev, next, "");
		
		//setting the connections for this edge to the next and previous Nodes
		//o<-[]  o
		this.prevNode = prev;

		//o<-[]->o
		this.nextNode = next;
		
	}

	
	/**
	 * Returns the next Node in the list.
	 * 
	 * @return The next Node in the list.
	 */
	public DLListNode getNextNode(){
		return this.nextNode;
	}
	
	/**
	 * Sets the next Node in the list.
	 * 
	 * @param nextNode The next Node in the list.
	 */
	public void setNextNode(DLListNode nextNode){
		this.nextNode = nextNode;
	}
	
	/**
	 * Returns the previous Node in the list.
	 * 
	 * @return The previous Node in the list.
	 */
	public DLListNode getPrevNode(){
		return this.prevNode;
	}
	
	/**
	 * Sets the previous Node in the list.
	 * 
	 * @param prevNode The previous Node in the list.
	 */
	public void setPrevNode(DLListNode prevNode){
		this.prevNode = prevNode;
	}
}
