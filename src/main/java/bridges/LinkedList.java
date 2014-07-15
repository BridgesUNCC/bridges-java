package bridges;

/*
 * author Mihai Mehedint
 * 
 */
public class LinkedList extends Link{  
	public Link currPointer; //holds the current pointer
	public Link listHead; //the head pointer
	public Link listTail; //the tail pointer
	public int size;  //the size of the list
	public String ident;
	public GraphVisualizer graph;
	/*
	 * constructor
	 */
	LinkedList(String ident, GraphVisualizer graph){
		super(ident, graph);
		this.ident=ident;
		this.graph=graph;
		currPointer=new Link(null, ident, graph);
		nextNodePointer=currPointer;
		prevNodePointer = currPointer;
		size=0;
	}
	/*
	 * Returns the current position as an integer value
	 */
	public int currPos(){
		int aPosition;
		Link aNode=listHead;
		for (aPosition=0;currPointer!=aNode;aPosition++){
			aNode=aNode.next();
		}
		return aPosition;
	}
	
	/*
	 * Returns the head pointer
	 */
	public void moveToBegining(){
		currPointer=listHead;
	}
	
	/* 
	 * Returns the tail pointer
	 * 
	 */
	public void moveToEnd(){
		currPointer=listTail;
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
	public void clearList() throws Exception{
		listHead.setNext(null);
		size=0;
		currPointer=listHead=listTail=new Link(null, ident, graph);
	}
	
	/*
	 * Inserts a node into the list and increments the length of the list
	 */
	public void insert(String aNode) throws Exception{
		Link aNodeLink = new Link(currPointer.next(), aNode, graph);
		size++;
		currPointer.setNext(aNodeLink);
	}
	
	/*
	 * Appends a node to the list and increments the length variable
	 */
	public void append(String aNode) throws Exception{
		Link aNodeLink=new Link(null, aNode, graph);
		listTail=listTail.setNext(aNodeLink);
		size++;
	}
	
	/*
	 * Removes the a node and decrements the length variable
	 */
	public String remove() throws Exception{
		if (currPointer.next()==null)
			return null;
		String aNode = currPointer.next().getNodeName();
		if (listTail==currPointer.next())
			listTail=currPointer;
		currPointer.setNext(currPointer.next().next());
		size--;
		return aNode;
	}
	
	/*
	 * This method provides the moves the current pointer to the previous node
	 * in a singly linked list
	 * @see bridges.Link#prev()
	 */
	public Link prev(){
		if (currPointer==listHead) return null;
		Link aNode=listHead;
		while(aNode.next()!=currPointer){
			aNode=aNode.next();
		}
		return currPointer=aNode;
	}
	/*
	 * This method returns the next node in the list
	 * @see bridges.Link#next()
	 */
	public Link next(){
		if (currPointer==listTail)
			return null;
		else
			return currPointer=currPointer.next();
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
			currPointer=currPointer.next();
	}
	
	/*
	 * This method returns the node value
	 */
	public String getNodeValue(){
		if (currPointer.next()==null){
			return null;
		}
		else 
			return currPointer.next().getNodeName();
	}
	
}
