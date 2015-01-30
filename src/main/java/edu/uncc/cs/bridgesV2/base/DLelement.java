package edu.uncc.cs.bridgesV2.base;
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
	
	public DLelement (String label, E e){
		super(label, e);
		this.next = null;
		this.prev = null;
	}
	
	public DLelement(DLelement<E> next, DLelement<E> prev) {
		super();
		this.next = next;
		this.prev = prev;
	}
	
	public DLelement(E e, DLelement<E> next, DLelement<E> prev) {
		super(e);
		this.next = next;
		this.prev = prev;
	}
	
	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */

/*
	public void copyDLelement (DLelement<E> original){
		this.setIdentifier(new String(original.getIdentifier()));
		this.setVisualizer(new ElementVisualizer(original.getVisualizer()));
		this.setLabel(new String(original.getLabel()));
		this.next = original.next;
		this.prev = original.prev;
	}
*/
	
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
	}
}
