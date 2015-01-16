/**
 * 
 */
package edu.uncc.cs.bridges_vs1.structure;

/**
 * @author mihai
 * @
 *
 */
public class GraphList <E>{
	private int size; //size of list
	private SLelement<E> head; //pointer to the head of the list
	private SLelement<E> tail; //pointer to the tail of the list
	private SLelement<E> current; //pointer to the current element visited
	
	/**
	 * Constructor
	 * @param size
	 * @param head
	 * @param tail
	 * @param current
	 */
	public GraphList() {
		super();
		this.size = 0;
		this.head = this.tail = this.current;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the head
	 */
	public Element<E> getStart() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setStart(SLelement<E> head) {
		this.head = head;
	}

	/**
	 * @return the tail
	 */
	public Element<E> getEnd() {
		return tail;
	}

	/**
	 * @param tail the tail to set
	 */
	public void setEnd(SLelement<E> tail) {
		this.tail = tail;
	}

	/**
	 * @return the current
	 */
	public Element<E> getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(SLelement<E> current) {
		this.current = current;
	}
	
	/**
	 * All elements of the list are removed
	 * the list is empty 
	 */
	public void clearList(){
		this.head = this.tail = this.current;
		this.size = 0;
		head.setNext(null);
	}
	
	/**
	 * Set the pointer to the next element of the list
	 */
	public void getNext(){
		if (current.getNext()!=null)
			current = current.getNext();
	}
	
	/**
	 * sets the current pointer to the next element 
	 * @param element
	 */
	public void setNext(SLelement<E> element){
		if(current!=tail)
			current = current.getNext();
	}
	
	/**
	 * Append one element to the list
	 * @param element
	 */
	public void append(SLelement<E> element){
		tail.setNext(element);
		size++;
	}
	
	/**
	 * Insert one element in the list
	 * @param element
	 */
	public void insert(SLelement<E> element){
		SLelement<E> temp = current.getNext();
		current.setNext(element);
		element.setNext(temp);
		current = element;
		if (tail == current) tail = current.getNext();
		size++;
		
	}
}
