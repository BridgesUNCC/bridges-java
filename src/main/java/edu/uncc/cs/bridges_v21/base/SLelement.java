package bridges.base;

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
	 * @param label the label of SLelement that shows up on the Bridges visualization
	 * @param e the generic object that this SLelement will hold
	 */
	public SLelement (String label, E e){
		super(label, e);
		this.next = null;
	}
	
	/**
	 * Creates a new element with value "e" and sets the next pointer
	 * to the SLelement referenced by the "next" argument 
	 * @param e the generic object that this SLelement will hold
	 * @param next the SLelement that should be assigned to the next pointer
	 */
	public SLelement (E e, SLelement<E> next) {
		super(e);
		this.setNext(next);
	}

	/**
	 * Creates a new element and sets the next pointer
	 * to the SLelement "next"
	 * @param next the SLelement that should be assigned to the next pointer
	 */
	public SLelement (SLelement<E> next) {
		this.setNext(next);
	}
	
/*
	public SLelement (SLelement<E> original) {
		super(original.getValue());
		this.setLabel(original.getLabel());
		this.setVisualizer(original.getVisualizer());
		this.setNext(original.getNext());
	}
*/
	
	/**
	 * Retrieves the next SLelement
	 * @return SLelement<E> assigned to next
	 */
	public SLelement<E> getNext() {
		return next;
	}
	
	/**
	 * Sets the pointer to the next SLelement
	 * @param next SLelement<E> that should be assigned to the next pointer
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
}
