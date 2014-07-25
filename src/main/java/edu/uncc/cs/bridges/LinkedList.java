package edu.uncc.cs.bridges;

/*
 * author Mihai Mehedint
 * 
 */
public class LinkedList<E>{  
	public GraphVisualizer list; //creates a graph
	private Link<E> currPointer; //holds the current pointer
	private Link<E> listHead; //the head pointer
	private Link<E> listTail; //the tail pointer
	private int size;  //the size of the list
	private String ident;
	
	/*
	 * constructor
	 */
	public LinkedList(){
		currPointer=listHead=listTail=new Link(null);
		list= new GraphVisualizer();
		
		size=0;
	}
	
	/*
	 * Returns the current position as an integer value
	 */
	public int currPos(){
		int aPosition;
		Link<E> aNode=listHead;
		for (aPosition=0;currPointer!=aNode;aPosition++){
			aNode=aNode.getNext();
		}
		return aPosition;
	}
	
	/*
	 * Returns the head pointer
	 */
	public Link<E> moveToBegining(){
		return currPointer=listHead;
	}
	
	/* 
	 * Returns the tail pointer
	 * 
	 */
	public Link<E> moveToEnd(){
		return currPointer=listTail;
	}
	
	/*
	 * Returns the size of the list
	 */
	public int length(){
		return size;
	}
	
	/*
	 * Clears the list
	 */
	public void clearList(){
		listHead=currPointer=listTail=null;
		size=0;
	}
	
	/*
	 * Inserts a node into the list and increments the length of the list
	 */
	public void insert(E aNode, int position){
		moveToPos(position-1);  //moves current pointer to the position
		Link<E> node=new Link(new Vertex(aNode.toString(),list));
		
		
		if(currPointer==null)
			listHead=listTail=currPointer=node;
		else if (currPointer==listHead && currPointer==listTail)
				currPointer.setNext(node);
		else{
			node.setPrev(currPointer);
			node.setNext(currPointer.getNext());
			
			currPointer.getNext().setPrev(node);
			currPointer.setNext(node);
			setEdge(currPointer.getNodeName(), currPointer.getNext().getNodeName());
			//setEdge(currPointer.getNext().getNodeName(), currPointer.getNext().getNext().getNodeName());
			
		}
		size++;	
		//setEdge(currPointer.getNodeName(), currPointer.getNext().getNodeName());
		//setEdge(currPointer.getNext().getNodeName(), currPointer.getNext().getNext().getNodeName());
	}
	
	/*
	 * Appends a node to the list and increments the length variable
	 */
	public void append(E aNode){
		Link<E> node=new Link(new Vertex(aNode.toString(),list));
		listTail.setNext(new Link(aNode));
		currPointer=listTail;
		listTail=next();
		size++;
	}
	
	/*
	 * Removes the a node and decrements the length variable
	 */
	public Link<E> remove(){
		if (currPointer.getNext()==null)
			return currPointer=null;
		if (listTail==currPointer.getNext())
			listTail=currPointer;
		else
			
		
		size--;
		return currPointer.setNext(currPointer.getNext().getNext());
	}
	
	/*
	 * This method provides the moves the current pointer to the previous node
	 * in a singly linked list
	 * @see edu.uncc.cs.bridges.Link#prev()
	 */
	public Link prev(){
		if (currPointer==listHead) return null;
		Link aNode=listHead;
		while(aNode.getNext()!=currPointer){
			aNode=aNode.getNext();
		}
		return currPointer=aNode;
	}
	/*
	 * This method returns the next node in the list
	 * @see edu.uncc.cs.bridges.Link#next()
	 */
	public Link<E> next(){
		if (currPointer==listTail)
			return null;
		else
			return currPointer=currPointer.getNext();
	}
	
	/*
	 * The moveToPos method moves the pointer to the a designated position
	 */
	public void moveToPos(int aPosition){
		if(aPosition<=0 && aPosition>size){
			System.out.println("The position requested is out of the range of the list.");
		}
		currPointer=listHead;
		for (int i=0;i<aPosition;i++)
			currPointer=currPointer.getNext();
	}
	
	/*
	 * This method returns the node value
	 */
	public E getNodeValue(){
		if (currPointer.getNext()==null){
			return null;
		}
		else 
			return currPointer.getNext().getNodeName();
	}
	
	/*
	 * This method is setting default edges between nodes
	 * without the possibility of changing any parameters
	 */
	private void setEdge(E source, E destination){
		((Vertex) source).createEdge((Vertex) destination);
	}
	
}
