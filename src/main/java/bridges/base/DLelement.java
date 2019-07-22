package bridges.base;

import java.util.Vector;
import java.util.HashMap;

/**
 *
 * 	@author Mihai Mehedint, Kalpathi Subramanian
 *
 *	@date 6/22/16, 1/7/17, 5/17/17, 7/14/19
 *
 * 	@brief This class is used to create doubly linked element objects.
 *
 *	This class extends Element and takes a generic parameter <E> representing
 *	application specific data. This element forms the basic building block for
 *	doubly linked lists. Doubly linked elements have two links,
 * 	"next" and "previous", that point to the previous and succeeding nodes along the list.
 *
 *	Elements contain a visualizer (ElementVisualizer) object for setting visual
 *	attributes (color, shape, opacity, size), necessary for displaying them in a web
 *	browser.
 *
 *	Elements also have a LinkVisualizer object that is used when they are linked to
 *	another element, appropriate for setting link attributes, such as in linked lists,
 *	between the current element and its next or previous nodes.
 *
 *	@param E The generic parameter object that is part of this element, representing
 *          application specific data.
 *
 *	\sa Example Tutorial at <br>
 *		http://bridgesuncc.github.io/tutorials/DLL.html
 */

public class DLelement<E> extends SLelement<E> {
	protected DLelement<E> prev;

	/**
	 *	Constructs an empty DLelement with next and prev pointers set to null.
	 */
	public DLelement() {
		super();
		this.prev = null;
	}

	/**
	 *	Constructs a DLelement labeled "label", holding an object "e",
	 *	with next and prev pointers set to null.
	 *
	 *	@param label the label for this DLelement that shows up on the
	 *		Bridges visualization
	 * 	@param e the genereic object that is held in this element.
	 *
	 */
	public DLelement (String label, E e) {
		super(label, e);
		this.prev = null;
	}

	/**
	 *
	 *	Constructs an empty DLelement with the next pointer set to the
	 *	DLelement "next" and the prev pointer set to DLelement "prev".
	 *
	 * 	@param next the DLelement that should be assigned to the next pointer
	 *	@param prev the DLelement that should be assigned to the prev pointer
	 *
	 */
	public DLelement(DLelement<E> next, DLelement<E> prev) {
		super();
		this.setNext(next);
		this.setPrev(prev);
	}

	/**
	 *
	 *	Constructs a DLelement holding an object "e", with the next pointer
	 *	set to the DLelement "next" and the prev pointer set to DLelement
	 *	"prev".
	 *
	 *	@param e the genereic object that this DLelement is holding
	 *	@param next the DLelement that should be assigned to the next pointer
	 *	@param prev the DLelement that should be assigned to the prev pointer
	 *
	 */
	public DLelement(E e, DLelement<E> next, DLelement<E> prev) {
		super(e);
		this.setNext(next);
		this.setPrev(prev);
	}

	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 */
	public String getDataStructType() {
		return "DoublyLinkedList";
	}

	/**
	 * This method returns the pointer to the next DLelement
	 *
	 * @return the DLelement assigned to the next pointer
	 */
	public DLelement<E> getNext() {
		return (DLelement<E>) next;
	}

	/**
	 * This method sets the pointer to the next DLelement
	 *
	 * @param nxt the DLelement that should be assigned to the next pointer
	 *
	 */
	public void setNext(DLelement<E> nxt) {
		this.next = nxt;
		if (nxt != null)
			this.setLinkVisualizer(nxt);
	}

	/**
	 * This method returns the pointer to the previous DLelement
	 *
	 * @return the DLelement assigned to the prev pointer
	 */
	public DLelement<E> getPrev() {
		return prev;
	}

	/**
	 * This method sets the pointer to the previous DLelement
	 *
	 * @param prv the DLelement that should be assigned to the prev pointer
	 */
	public void setPrev(DLelement<E> prv) {
		// first remove any existing link visualizer from this node
		//	to its next node
		if (this.prev != null)
			this.removeLinkVisualizer(this.prev);

		this.prev = prv;
		// add a new link visualizer
		if (prv != null)
			this.setLinkVisualizer(prv);
	}
	/*
	 *	Get the JSON representation of the the data structure
	 *	@return JSON string of the doubly linked list representation
	 */
	public String getDataStructureRepresentation() {
		// map to reorder the nodes for building JSON
		HashMap<Element<E>, Integer> node_map = new HashMap<Element<E>, Integer>();
		// get teh list nodes
		Vector<Element<E> > nodes = new Vector<Element<E>> ();
		this.getListElements(nodes);

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
			DLelement<E> par = (DLelement<E>) nodes.get(k);
			DLelement<E> nxt = (DLelement<E>) par.next;
			DLelement<E> prv = par.prev;
			if (nxt != null) { 		// add the link
				links_JSON
				.append(getLinkRepresentation( par.getLinkVisualizer(nxt),
						Integer.toString(node_map.get(par)),
						Integer.toString(node_map.get(nxt))))
				.append(COMMA);
			}
			if (prv != null) { 		// add the link
				links_JSON
				.append(getLinkRepresentation(
						par.getLinkVisualizer(prv),
						Integer.toString(node_map.get(par)),
						Integer.toString(node_map.get(prv))))
				.append(COMMA);
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
}
