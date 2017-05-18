package bridges.base;

/**
 * 	@brief This class can be used to instantiate Singly Linked 
 *	Circular List Elements. 

 *	Structurally they are the same as singly linked elements
 *  except that each node constructed with the next point pointing to itself;
 *  User's implementation of the circularly linked list needs to ensure that
 *	the last node points to first node of the list, as the visualization generation is 
 *	dependent on this.
 *  
 * 	Elements have labels (string) that are displayed on the visualization.
 *  Elements take an generic object as a user defined parameter, E, which 
 *	can be any native type or object.
 *
 * 	Elements contains a visualizer (ElementVisualizer) object for setting visual 
 *	attributes (color, shape, opacity, size), necessary for displaying them in a web 
 *	browser.
 *
 *  Elements also have a LinkVisualizer object that is used when they are 
 *	linked to another element, appropriate for setting link attributes, between
 *  an element and its next element.
 *	
 *	@author Kalpathi Subramanian
 *
 *	@date 6/22/16, 1/7/17, 5/17/17
 *
 *	@param <E>  the generic parameter that is defined by the application
 *
 *	\sa Example Tutorial at <br>
 *			http://bridgesuncc.github.io/Hello_World_Tutorials/CSLL.html
 */
public class CircSLelement<E> extends SLelement<E> {
	/**
	 *
	 * 	This constructor creates an CircSLelement object 
	 * 	and sets its next pointer to itself
	 *
	 */
	public CircSLelement() {
		super();
		this.next = this;
	}
	/**
	 * 	
	 *
	 * 	This constructor creates an CircSLelement object of value "e" and 
	 *	label "label"
	 * 	and sets the next pointer to null
	 *
	 * 	@param label the label of CircSLelement that shows up on the Bridges 
	 *		visualization
	 * 	@param e the generic object that this CircSLelement will hold
	 * 	
	 */
	public CircSLelement (String label, E e){
		super(label, e);
		this.next = this;
	}

	/**
 	 *
	 * Creates a new element with value "e" and sets the next pointer
	 * to the CircSLelement referenced by the "next" argument 
 	 *
	 * @param e the generic object that this CircSLelement will hold
	 * @param next the CircSLelement that should be assigned to the next pointer
 	 *
	 */
	public CircSLelement (E e, CircSLelement<E> next) {
		super(e, next);
	}

	/**
 	 *
	 * Creates a new element and sets the next pointer
	 * to the CircSLelement "next"
 	 *
	 * @param next the CircSLelement that should be assigned to the next pointer
 	 *
	 */
	public CircSLelement (CircSLelement<E> next) {
		super(next);
	}
	
	/**
 	 *
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
 	 *
	 **/
	public String getDataStructType() {
		return "CircularSinglyLinkedList";
	}
	
	/**
 	 *
	 * Retrieves the next CircSLelement
 	 *
	 * @return CircSLelement<E> assigned to next
 	 *
	 */
	public CircSLelement<E> getNext() {
		return (CircSLelement<E>) next;
	}
	
	/**
 	 *
	 * Sets the pointer to the next CircSLelement
	 *
	 * @param next CircSLelement<E> that should be assigned to the next pointer
 	 *
	 */
	public void setNext(CircSLelement<E> next) {
		this.next = next;
	}
	
	/** (non-Javadoc)
 	 *
	 * @see java.lang.Object#toString()
 	 *
	 */
	@Override
	public String toString() {
		return "CircSLelement [next=" + next + ", getNext()=" + getNext()
				+ ", getIdentifier()=" + getIdentifier() + ", getVisualizer()="
				+ getVisualizer()
				+ ", getClassName()=" + getClassName()
				+ ", getRepresentation()=" + getRepresentation()
				+ ", getLabel()=" + getLabel() + ", getValue()=" + getValue()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
