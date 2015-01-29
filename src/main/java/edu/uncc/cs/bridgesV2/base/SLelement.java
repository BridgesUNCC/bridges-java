package edu.uncc.cs.bridgesV2.base;

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
public class SLelement<E> extends Element<E> implements Cloneable{
	protected SLelement<E> next=null; //the link to the next element 
	
	/**
	 * This constructor creates an SLelement object of value "e" and label "label"
	 * and sets the next pointer to null
	 * @param label
	 * @param e
	 */
	public SLelement (String label, E e){
		super(label, e);
		this.next = null;
	}
	
	/**
	 * Creates a new element with value "e" and sets the next pointer
	 * to the SLelement referenced by the "next" argument 
	 * @param e
	 * @param next
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
	 * Retrieves the next SLelement
	 * @return SLelement<E>
	 */
	public SLelement<E> getNext() {
		return next;
	}
	
	/**
	 * Sets the pointer to the next SLelement
	 * @param next SLelement<E>
	 */
	public void setNext(SLelement<E> next) {
		this.next = next;
	}
	
	@Override
    public SLelement<E> clone() throws CloneNotSupportedException {
				try {
					return (SLelement<E>)super.clone();
				} catch (Exception e) {
					e.printStackTrace();
				}
        return null;
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
