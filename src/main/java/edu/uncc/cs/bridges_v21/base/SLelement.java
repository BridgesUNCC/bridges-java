package bridges.base;

import java.util.Vector;
/**
 * 	@brief This class can be used to instantiate Singly Linked Elements.

 * 	This class extends Element and takes a generic parameter <E> 
 *	representing application specific data. This element forms the basic
 *	building block for singly linked lists. Singly linked elements have a field
 *	pointing to the next element along the list.
 *
 *
 * 	Elements contain a visualizer (ElementVisualizer) object for setting visual 
 *	attributes (color, shape, opacity, size), necessary for displaying them in a 
 *	web browser.
 *
 *	Elements also have a LinkVisualizer object, that is used when they are linked to 
 *	another element, appropriate for setting link attributes, for instance, between 
 *	the current element and its next element.
 *
 * @author Mihai Mehedint, Kalpathi Subramanian
 *
 * @date 6/22/16, 1/7/17, 5/17/17
 *
 * @param <E> The generic parameter object that is part of this element, representing
 *			application specific data.
 *
 *	\sa Example Tutorial at <br> http://bridgesuncc.github.io/Hello_World_Tutorials/SLL.html
 */

public class SLelement<E> extends Element<E> {

	protected SLelement<E> next=null; //the link to the next element 

	/**
	 * 
	 * This constructor creates an SLelement object
	 * and sets the next pointer to null
	 * 
	 */

	public SLelement() {
		super();
		this.next = null;
	}
	
	/**
	 *
	 * This constructor creates an SLelement object of generic parameter object E, 
	 *	and label "label" and sets the next pointer to null
 	 *
	 * @param label the label of SLelement that shows up on the Bridges visualization
	 * @param e the generic object that this SLelement will hold
 	 *
	 */
	public SLelement (String label, E e){
		super(label, e);
		this.next = null;
	}
	
	/**
 	 *
	 * Creates a new element with value "e" and sets the next pointer
	 * to the SLelement referenced by the "next" argument 
	 *
	 * @param e the generic object that this element will hold
	 * @param next the element that should be assigned to the next pointer
 	 *
	 */
	public SLelement (E e, SLelement<E> next) {
		super(e);
		this.setNext(next);
		this.setLinkVisualizer(next);
	}

	/**
 	 *
	 * Creates a new element with value "e" 
	 *
	 * @param e the generic object that this element will hold
 	 *
	 */
	public SLelement (E e) {
		super(e);
		this.next = null;
	}


	/**
	 * Creates a new element and sets the next pointer
	 * to the SLelement "next"
	 * @param next the SLelement that should be assigned to the next pointer
	 */
	public SLelement (SLelement<E> next) {
		this.setNext(next);
		this.setLinkVisualizer(next);
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
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 *
	 */
	public String getDataStructType() {
		return "SinglyLinkedList";
	}
	
	/**
	 * Retrieves the element following this element 
	 *
	 * @return SLelement<E> assigned to next
	 *
	 */
	public SLelement<E> getNext() {
		return next;
	}
	
	/**
	 * Sets the element to point to the next SLelement
	 *
	 * @param next SLelement<E> that should be assigned to the next pointer
	 */
	public void setNext(SLelement<E> next) {
		this.next = next;
		this.setLinkVisualizer(next);
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
				+ ", getElementRepresentation()=" + getElementRepresentation()
				+ ", getLabel()=" + getLabel() + ", getValue()=" + getValue()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	/*
	 *	Get the JSON representation of the the data structure
	 */
	public String[] getDataStructureRepresentation() {
		Vector<Element<E> > nodes = new Vector<Element<E>> ();
		getListElements(nodes);
				// generate the JSON of the list
		return generateListJSON (nodes);
	}

	/*
	 *	Get the elements of the list
	 *
	 *	@param nodes  a vector of the ndoes in the list
	 *
	 */
	protected void getListElements(Vector<Element<E>> nodes) {
		SLelement<E> el = this;
					// try to handld all lists in subclasses, except multilists
		nodes.clear();
		while (el != null && el.getNext() != el) {
			nodes.add(el);
			el = el.getNext();
		}
	}
}
