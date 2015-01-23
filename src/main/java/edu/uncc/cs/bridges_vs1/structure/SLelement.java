package edu.uncc.cs.bridges_vs1.structure;


public class SLelement<E> extends Element<E>{
	protected SLelement<E> next=null; //the link to the next element 
	
	
	public SLelement (String identifier, E e){
		super(e, identifier);
		this.next = null;
	}
	
	public SLelement (SLelement<E> original) {
		super(original.getElement(), original.getCaller());
		validateVal(original);
		copySLelement(original);	
		//original = null;
		validateVal(this);
	}
	/**
	 * Creates a new element with a copy of current Element
	 * @param e
	 * @param original
	 */
	public SLelement (E e, SLelement<E> next) {
		super(e, e.toString());
		this.setNext(next);
	}

	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	protected void copySLelement (SLelement<E> original){
		this.caller = new String(original.getCaller());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
		this.setNext(original.getNext());	
	}
	
	/**
	 * Validates the element
	 * @param value
	 */
	private void validateVal(SLelement<E> value) {
		if (value.getElement() == null){
			throw new NullPointerException("Invalid value' " + value.getElement() + "'. Expected"
					+ " non null value.");
		}
		else if (value.getClass().isInstance(getElement()))
			System.out.println(value.getClass().isInstance(getElement()));
		else
			;
	}
	
	/**
	 * Retrieves the next element
	 * @return
	 */
	public SLelement<E> getNext() {
		return next;
	}
	
	/**
	 * Sets the pointer to the next element
	 * @param next
	 */
	public void setNext(SLelement<E> next) {
		this.next = next;
	}
	
	//method removed
	//students will create this method
	/*
	public void removeNext(){
		this.next = null;
	}
	*/
}
