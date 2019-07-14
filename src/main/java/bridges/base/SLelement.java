package bridges.base;

import java.util.Iterator;
import java.util.Vector;
import java.util.HashMap;
import java.util.NoSuchElementException;
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
 * @date 6/22/16, 1/7/17, 5/17/17, 7/14/19
 *
 * @param <E> The generic parameter object that is part of this element, representing
 *			application specific data.
 *
 *	\sa Example Tutorial at http://bridgesuncc.github.io/tutorials/SLL.html
 */

public class SLelement<E> extends Element<E> implements Iterable<SLelement<E>> {

	protected SLelement<E> next = null; //the link to the next element

	/**
	 *
	 * This constructor creates an SLelement object
	 * and sets the next pointer to null
	 *
	 */

	public SLelement() {
		super();
		next = null;
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
	public SLelement (String label, E e) {
		super(label, e);
		next = null;
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
		next = null;
	}


	/**
	 * Creates a new element and sets the next pointer
	 * to the SLelement "next"
	 * @param next the SLelement that should be assigned to the next pointer
	 */
	public SLelement (SLelement<E> next) {
		this.setNext(next);
	}

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
	 * Sets the element to point to the next SLelement, updates
	 *  the link visualizer
	 *
	 * @param next SLelement<E> that should be assigned to the next pointer
	 */
	public void setNext(SLelement<E> next) {
		this.next = next;
		if (next != null) {
			this.setLinkVisualizer(next);
		}
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

	/**
	 *	Get the JSON representation of the the data structure
	 *
	 *  @return the JSON string of the element's representation
	 */
	public String getDataStructureRepresentation() {
		// map to reorder the nodes for building JSON
		HashMap<Element<E>, Integer> node_map = new HashMap<Element<E>, Integer>();
		// get teh list nodes
		Vector<Element<E> > nodes = new Vector<Element<E>> ();
		getListElements(nodes);

		// generate the JSON of the list nodes
		StringBuilder nodes_JSON = new StringBuilder();
		for (int k = 0; k < nodes.size(); k++) {
			node_map.put(nodes.get(k), k);
			nodes_JSON.append(nodes.get(k).getElementRepresentation());
			nodes_JSON.append(COMMA);
		}
		// remove the last comma
		if (nodes.size() != 0)	// only if there is at least one node
			nodes_JSON.setLength(nodes_JSON.length() - 1);

		StringBuilder links_JSON = new StringBuilder();
		for (int k = 0; k < nodes.size(); k++) {
			SLelement<E> par = (SLelement<E>) nodes.get(k);
			SLelement<E> chld = par.next;
			if (chld != null) { 		// add the link
				links_JSON.append(getLinkRepresentation(
						par.getLinkVisualizer(chld),
						Integer.toString(node_map.get(par)),
						Integer.toString(node_map.get(chld))) );
				links_JSON.append(COMMA);
			}
		}
		if (links_JSON.length() > 0) // if there is at least one link
			links_JSON.setLength(links_JSON.length() - 1);

		String json_str =
			QUOTE + "nodes"  + QUOTE + COLON +
			OPEN_BOX + nodes_JSON.toString()  + CLOSE_BOX + COMMA +
			QUOTE + "links" + QUOTE + COLON +
			OPEN_BOX + links_JSON.toString() + CLOSE_BOX +
			CLOSE_CURLY;

		return json_str;
	}

	/**
	 *
	 *  Implements an iterator on the singly linked element for ease
	 *  iterating over lists
	 */
	class SLelementIterator implements Iterator<SLelement<E>> {
		SLelement<E> current;

		SLelementIterator(SLelement<E> current) {
			this.current = current;
		}

		public boolean hasNext() {
			return this.current != null;
		}

		public SLelement<E> next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			SLelement<E> ret = this.current;
			this.current = this.current.next;
			return ret;
		}

	}

	/**
	 *	Return an iterator over the elements in the array. This is generally not
	 *  called directly, but is called by Java when used in a "simple" for loops
	 */
	public Iterator<SLelement<E>> iterator() {
		return new SLelementIterator(this);
	}

	/*
	 *	Get the elements of the list - used for  JSON construction
	 *
	 *	@param nodes  a vector of the ndoes in the list
	 *
	 */
	protected void getListElements(Vector<Element<E>> nodes) {
		SLelement<E> el = this;
		// try to handle all lists in subclasses, except multilists
		nodes.clear();
		while (el != null) {
			nodes.add(el);
			el = ((SLelement<E>) el).getNext();
			// handle circular lists
			if (el == this)
				break;
		}
	}
}
