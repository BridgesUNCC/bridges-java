package edu.uncc.cs.bridges;
/*
 * A Link class 
 * author: Mihai Mehedint
 *  
 */
public class Link extends DLListNode{
	private String aNode;					//current node
	public Link nextNodePointer; //holds the next pointer
	public Link prevNodePointer; //holds the previous pointer
	
	private DLListEdge nextEdgePointer, prevEdgePointer;//hold the pointers to edge objects 
	
	/*
	 * Constructors
	 */
	Link(String ident, GraphVisualizer graph){
		super(ident, graph);
		this.aNode=ident;
	}
	
	Link(Link nextNodePointer, String ident, GraphVisualizer graph){
		super(ident, graph);
		this.aNode=ident;
		this.nextNodePointer=nextNodePointer;
	}
	
	Link(Link nextNodePointer, Link prevNodePointer, String ident, GraphVisualizer graph){
		super(ident, graph);
		this.aNode=ident;
		this.nextNodePointer=nextNodePointer;
		this.prevNodePointer=prevNodePointer;
	}
	
	/*
	 * Returns the next Node
	 */
	public Link next(){
		return nextNodePointer;
	}
	
	/*
	 * Creates a link between 2 nodes
	 */
	public Link setNext(Link aLink) throws Exception{
		 super.setNext(aLink);
		 //this.getNext().setNext(aLink);
		 return nextNodePointer=aLink;
	}
	
	/*
	 * Returns the previous node
	 */
	public Link prev(){
		return prevNodePointer;
	}
	
	/*
	 * Returns the previous node
	 */
	public Link setPrev(Link aLink) throws Exception{
		super.setPrev(aLink);
		//this.getPrev().setPrev(aLink);
		return prevNodePointer=aLink;
	}
	
	/*
	 * Sets the name of the node element
	 */
	public String setNodeName(String aNode){
		return this.aNode=aNode;
	}
	
	/*
	 * Returns the name of the node 
	 */
	public String getNodeName(){
		 return this.aNode;
	}
	
}