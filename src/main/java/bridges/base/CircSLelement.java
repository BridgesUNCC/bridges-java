package bridges.base;

import java.util.Iterator;

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
 *  @sa Example Tutorial at
 *			http://bridgesuncc.github.io/tutorials/CircularSinglyLinkedList.html
 *
 *	@author Kalpathi Subramanian
 *
 *	@date 6/22/16, 1/7/17, 5/17/17, 7/14/19
 *
 *	@param <E>  the generic parameter that is defined by the application
 *
 */

public class CircSLelement<E> extends SLelement<E> implements Iterable<E> {
	/**
	 *
	 * 	This constructor creates an CircSLelement object
	 * 	and sets its next pointer to itself
	 *
	 */
	public CircSLelement() {
		super();
		this.setNext(this);
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
	public CircSLelement (String label, E e) {
		super(label, e);
		this.setNext(this);
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
	 *  Implements an iterator on the Circular singly linked element for ease
	 *  iterating over lists
	 */
	class CircSLelementIterator implements Iterator<E> {
		CircSLelement<E> current, first;
		private boolean at_start;

		CircSLelementIterator(CircSLelement<E> current) {
			this.current = current;
			this.first = current;
			this.at_start = true;
		}

		public boolean hasNext() {
			if ((this.current == this.first) && !at_start)
				return false;

			return true;
		}


		public E next() {
			E ret = this.current.getValue();
			this.current = (CircSLelement) this.current.next;
			at_start = false;
			return ret;
		}
	}

	/**
	 *	Return an iterator over the elements in the array. This is generally not
	 *  called directly, but is called by Java when used in a "simple" for loops
	 */
	public Iterator<E> iterator() {
		return new CircSLelementIterator(this);
	}


	@Override
	public String toString() {
		return "CircSLelement [next=" + next + ", getNext()=" + getNext()
			+ ", getIdentifier()=" + getIdentifier() + ", getVisualizer()="
			+ getVisualizer()
			+ ", getClassName()=" + getClassName()
			+ ", getElementRepresentation()=" + getElementRepresentation()
			+ ", getLabel()=" + getLabel() + ", getValue()=" + getValue()
			+ ", toString()=" + super.toString() + ", getClass()="
			+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
