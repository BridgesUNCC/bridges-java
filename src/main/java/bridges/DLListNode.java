package bridges;

/*
 * The Node class for a Doubly Linked List. Automates the process for creating the 'Edge objects' between 
 * the Nodes since that is not typically done in data structures. The Edges are still able to be accessed 
 * to change their properties.
 */
public class DLListNode extends AbstractVertex{
	
	/**
	 * Pointers to reference the 'Next' and 'Previous' Edges connected to this Node.
	 */
	private DLListEdge nextEdge, prevEdge;
	
	/**
	 * Constructor for a Doubly Linked List Node.
	 * 
	 * @param ident The identifier of the Node.
	 */
	public DLListNode(String ident, GraphVisualizer graph){
		super(ident);
		graph.vertices.put(ident, this);
	}
	/**
	 * Creates an Edge between the two Nodes if one doesn't exist, otherwise it utilizes a pre-existing Edge.
	 * 
	 * @param next The next Node in the Doubly Linked List.
	 * @throws Exception Verifies the Nodes aren't NULL.
	 */
	public void setNext(DLListNode next) throws Exception{
		if(this == null || next == null){
			throw new Exception("Next not set, one or more Nodes were NULL.");
		}
		//o  []  o
		if(this.nextEdge == null && next.prevEdge == null){
			//o<- [] ->o
			// ->
			this.nextEdge = new DLListEdge(this, next);
			//o   [] <-o
			//       ->
		}else if(this.nextEdge == null){
			//o-> [] <-o
			//       ->
			this.nextEdge = next.prevEdge;
		}else{//TODO Handle if both have an Edge but aren't connected to each other.
			if(this.nextEdge != null && next.prevEdge != null){
				next.prevEdge = null;
			}
			//o<-[]->o
			// ->
			this.nextEdge.setNextNode(next);
		}		
	}
	
	/**
	 * Returns the next Node in the List.
	 * 
	 * @return The next Node in the Doubly Linked List.
	 */
	public DLListNode getNext(){
		if(this.nextEdge == null){
			return null;
		}else
			return this.nextEdge.getNextNode();
	}
	/**
	 * Creates an edge between the two Nodes if one doesn't exist, otherwise it utilizes a pre-existing edge.
	 * 
	 * @param prev The previous Node in the Double Linked List.
	 * @throws Exception Verifies the Nodes aren't NULL.
	 */
	public void setPrev(DLListNode prev) throws Exception{
		if(this == null || prev == null){
			throw new Exception("Previous not set, one or more Nodes were NULL.");
		}
		//o  []  o
		if(this.prevEdge == null && prev.nextEdge == null){
			//o<-[]->o
			//     <-
			this.prevEdge = new DLListEdge(prev, this);
		}
		//o->[]  o
		// <-
		else if(this.prevEdge == null){
			//o->[]<-o
			// <-  
			this.prevEdge = prev.nextEdge;
			//o  []<-o
			//     ->
		}else{//TODO Handle if both have an Edge but aren't connected to each other.
			if(this.prevEdge != null && prev.nextEdge != null){
				prev.nextEdge = null;
			}
			//o  []<-o
			// <-  ->
			this.prevEdge.setPrevNode(prev);
		}
	}
	/**
	 * Returns the previous Node in the List.
	 * 
	 * @return The previous Node in the Doubly Linked List.
	 */
	public DLListNode getPrev(){
		if(this.prevEdge == null){
			return null;
		}else
			return this.prevEdge.getPrevNode();
	}

}
