package edu.uncc.cs.bridges_vs1.structure;
/**
 * @author mihai Mehedint
 * This class can be used to create doubly linked element objects
 * with next and previous (prev) pointers
 *
 */

public class DLelement<E> extends Element<E>{
	private DLelement<E> next;
	private DLelement<E> prev;
	
	/**
	 * Constructors
	 */
	public DLelement(){
		super();
		this.next = null;
		this.prev = null;
	}
	
	public DLelement (String identifier, E e){
		super(e, identifier);
		this.next = null;
		this.prev = null;
	}
	
	public DLelement(DLelement<E> next, DLelement<E> prev) {
		super();
		this.next = next;
		if(next.getPrev()!=this)
			next.setPrev(this);
		this.prev = prev;
		if(prev.getNext()!=this)
			prev.setNext(this);
	}
	
	public DLelement(E e, DLelement<E> next, DLelement<E> prev) {
		super();
		this.next = next;
		if(next.getPrev()!=this)
			next.setPrev(this);
		this.prev = prev;
		if(prev.getNext()!=this)
			prev.setNext(this);
	}
	
	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	public void copyDLelement (DLelement<E> original){
		this.identifier = new String(original.getIdentifier());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
		this.next = original.next;
		this.prev = original.prev;
	}
	
	/**
	 * This method returns the pointer to the next DLelement
	 * @return
	 */
	public DLelement<E> getNext() {
		return next;
	}
	

	/**
	 * This method sets the pointer to the next DLelement
	 * @return
	 */
	public void setNext(DLelement<E> next) {
		this.next = next;
		if(next.getPrev()!=this)
			next.setPrev(this);
	}
	

	/**
	 * This method returns the pointer to the previous DLelement
	 * @return 
	 */
	public DLelement<E> getPrev() {
		return prev;
	}

	/**
	 * This method sets the pointer to the previous DLelement
	 * @return
	 */
	public void setPrev(DLelement<E> prev) {
		this.prev = prev;
		if(prev.getNext()!=this)
			prev.setNext(this);
	}
}
