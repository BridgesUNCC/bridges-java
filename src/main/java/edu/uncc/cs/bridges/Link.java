package edu.uncc.cs.bridges;
/*
 * A Link class 
 * author: Mihai Mehedint
 *  
 */
public class Link<E>{
	private E 		nodeName;		   //can be set to string, integer, Vertex, Edge etc.; current node
	private Link<E> 	nextNodePointer;  //holds the next pointer
	private Link<E> prevNodePointer; //holds the previous pointer 
	/*
	 * Constructors
	 */
	Link(E ident){
		this.nodeName=ident;
	}
	
	Link(E ident, Link<E> nextNodePointer){
		this(ident);
		this.nextNodePointer=nextNodePointer;	
	}
	
	Link(E ident, Link<E> nextNodePointer, Link<E> prevNodePointer){
		this(ident, nextNodePointer);
		this.prevNodePointer=prevNodePointer;
	}
	
	/*
	 * Returns the next Node
	 */
	public Link<E> getNext(){
		return nextNodePointer;
	}
	
	/*
	 * Creates a link between 2 nodes
	 */
	public Link<E> setNext(Link<E> aNode){
		 return nextNodePointer=aNode;
	}
	
	/*
	 * Returns the previous node
	 */
	public Link<E> getPrev(){
		return prevNodePointer;
	}
	
	/*
	 * Returns the previous node
	 */
	public Link<E> setPrev(Link<E> aNode){
		return prevNodePointer=aNode;
	}
	
	/*
	 * Sets the name of the node element
	 */
	public E setNodeName(E aNode){
		return this.nodeName=aNode;
	}
	
	/*
	 * Returns the name of the node 
	 */
	public E getNodeName(){
		 return this.nodeName;
	}
	
}