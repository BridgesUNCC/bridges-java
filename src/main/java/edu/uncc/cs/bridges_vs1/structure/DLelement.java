package edu.uncc.cs.bridges_vs1.structure;


public class DLelement<E> extends Element<E>{
	private DLelement<E> next;
	private DLelement<E> prev;
	
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
		this.prev = prev;
	}
	
	public DLelement(E e, DLelement<E> next, DLelement<E> prev) {
		super();
		this.next = next;
		this.prev = prev;
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

	public DLelement<E> getNext() {
		return next;
	}

	public void setNext(DLelement<E> next) {
		this.next = next;
	}

	public DLelement<E> getPrev() {
		return prev;
	}

	public void setPrev(DLelement<E> prev) {
		this.prev = prev;
	}
}
