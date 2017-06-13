package bridges.base;

import java.util.Vector;
import java.util.HashMap;
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
		// remove any existing link visualizer from this node
		//		if (this.next != null) {
		//			this.removeLinkVisualizer(this.next);
		//		}

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

	/*
	 *	Get the JSON representation of the the data structure
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
		links_JSON.setLength(links_JSON.length() - 1);

		String json_str =
			QUOTE + "nodes"  + QUOTE + COLON +
			OPEN_BOX + nodes_JSON.toString()  + CLOSE_BOX + COMMA +
			QUOTE + "links" + QUOTE + COLON +
			OPEN_BOX + links_JSON.toString() + CLOSE_BOX +
			CLOSE_CURLY;

		return json_str;
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
		while (el != null) {
			nodes.add(el);
			el = ((SLelement<E>) el).getNext();
			// handle circular lists
			if (el == this)
				break;
		}
	}
}
