package edu.uncc.cs.bridges_vs1.structure;


public class SLelement<E> extends Element<E>{
	protected SLelement<E> next; //the link to the next element 
	
	
	public SLelement (String identifier, E e){
		super(e, identifier);
		this.next = null;
	}


	public SLelement (SLelement<E> next) {
		super();
		validateVal(next);
		this.next = next;	
	}
	
	public SLelement (E e, SLelement<E> next) {
		super(e, next.getIdentifier());	
	}

	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	public void copySLelement (SLelement<E> original){
		this.identifier = new String(original.getIdentifier());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
		this.next = original.next;
		
	}
	
	/**
	 * Validates the element
	 * @param value
	 */
	private void validateVal(SLelement<E> value) {
		if (value == null){
			throw new NullPointerException("Invalid value' " + value + "'. Expected"
					+ " non null value.");
		}	
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
