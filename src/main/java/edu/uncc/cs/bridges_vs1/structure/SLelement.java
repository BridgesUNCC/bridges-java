package edu.uncc.cs.bridges_vs1.structure;

/**
 * This class can be used to instantiate Singly List Elements
 * with identifiers (automatically generated), 
 * labels (derived from the values of E or manually set)
 * next pointers
 * an object of E data type: integer, string, Tweet, Actor, Movie, EarthquakeTweet
 * element visualizer objects containing all the CSS atributes 
 * (color, shape, opacity, size) necessary for displaying them in a web browser
 * @author mihai
 *
 * @param <E>
 */
public class SLelement<E> extends Element<E>{
	protected SLelement<E> next=null; //the link to the next element 

	public SLelement (String label, E e){
		super(label, e);
		this.next = null;
	}
	
	/**
	 * Creates a new element with a copy of current Element
	 * @param e
	 * @param original
	 */
	public SLelement (E e, SLelement<E> next) {
		super(e);
		this.setNext(next);
	}
	
	/**
	 * Deep copy SLelement
	 * @param original
	 */
	public SLelement (SLelement<E> original) {
		super(original.getValue());
		this.setIdentifier(original.getIdentifier());
		this.setLabel(original.getLabel());
		this.setVisualizer(original.getVisualizer());
		//original = null;
		this.setNext(original.getNext());
	}
	
	
	/**
	 * Retrieves the next element
	 * @return SLelement<E>
	 */
	public SLelement<E> getNext() {
		return next;
	}
	
	/**
	 * Sets the pointer to the next element
	 * @param next SLelement<E>
	 */
	public void setNext(SLelement<E> next) {
		this.next = next;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SLelement [next=" + next + ", getNext()=" + getNext()
				+ ", getIdentifier()=" + getIdentifier() + ", getVisualizer()="
				+ getVisualizer()
				+ ", getClassName()=" + getClassName()
				+ ", getRepresentation()=" + getRepresentation()
				+ ", getLabel()=" + getLabel() + ", getValue()=" + getValue()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	//method removed
	//students will create this method
	/*
	public void removeNext(){
		this.next = null;
	}
	*/
}
