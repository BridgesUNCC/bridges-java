package bridges.base;

import java.util.HashMap;
import java.util.Vector;
/**
 * 	@brief This class can be used to instantiate Multi-list Elements.

 * This class extends SLelement (singly linked list element) to build multi-lists;
 * Multilist elements contain a tag (boolean) that indicates if the element 
 * contains a sublist or not; if the tag is true, then there is a sublist beginning
 * at this node and the starting point is the `sublist' field in the element. 
 * If the tag is false, then the list continues as a normal singly linked list. 
 * The sublists are re recursive: any sublist can have its own sublists and so on.
 *
 * As in singly linked elements, the next pointer points to the following list element and 
 * each element contains a generic application specific object.
 * This class extends SLelement (singly linked list element) to build multi-lists;
 * Multilist elements contain a tag that indicates if the element is a sublist or not;
 * If the element points to a sublist, then the sublist field is the beginning of
 * this sublist. If not, the data field contains the user specified data item and
 * list continues (getNext()/setNext()). As in singly linked elements, the next pointer
 * points to the following list element of the list or sublist.
 *
 * 	Multi-list elements contain a visualizer (ElementVisualizer) object for setting
 *	visual attributes (color, shape, opacity, size), necessary for displaying
 *	them in a web browser.
 *
 *	Elements also have a LinkVisualizer object, that is used when they are linked to
 *	another element, appropriate for setting link attributes, for instance, between
 *	the current element and its next element. In this case, the link in question is that
 *  which connects the element to the following elements; a similar logic follows for
 *	sublists.
 *
 *	@sa Example Tutorial at http://bridgesuncc.github.io/tutorials/MultiList.html
 *
 * @author Kalpathi Subramanian
 *
 * @date 5/24/17, 7/14/19
 *
 * @param E The generic parameter object that is part of this element, representing
 *			either application specific data, or a pointer to a sublist.
 */

public class MLelement<E> extends SLelement<E> {

	protected MLelement<E> sub_list = null; // link to a sublist
	boolean tag = false;

	/**
	 *
	 * This constructor creates an MLelement object
	 * and sets the next pointer to null
	 *
	 */

	public MLelement() {
		super();
		this.sub_list = null;
	}

	/**
	 *
	 * This constructor creates an MLelement object of generic parameter object E,
	 *	and label "label" and sets the next pointer to null
	 *
	 * @param label the label of MLelement that shows up on the Bridges visualization
	 * @param e the generic object that this MLelement will hold
	 *
	 */
	public MLelement (String label, E e) {
		super(label, e);
		this.sub_list = null;
	}

	/**
	 *
	 * Creates a new element with value "e" and sets the next pointer
	 * to the MLelement referenced by the "next" argument
	 *
	 * @param e the generic object that this element will hold
	 * @param next the element that should be assigned to the next pointer
	 *
	 */
	public MLelement (E e, MLelement<E> next) {
		super("", e);
		this.setNext(next);
		this.sub_list = null;
	}

	/**
	 * Creates a new element and sets the next pointer
	 * to the MLelement "next"
	 * @param next the MLelement that should be assigned to the next pointer
	 */
	public MLelement (MLelement<E> sublist, MLelement<E> next) {
		this.setNext(next);
		this.sub_list = sublist;
		if (sublist != null) {
			tag = true;
			this.setLinkVisualizer(sublist);
		}
	}

	/**
	 * Sets the start of a new sublist.
	 * to the MLelement "next"
	 *
	 * @param sl the MLelement that is the beginning of a sublist
	 */
	public void setSubList(MLelement<E> sl) {
		this.sub_list = sl;
		if (sl != null) {
			tag = true;
			this.setLinkVisualizer(sl);
		}
		// by default, color and shape sublist nodes to distinguish them  from
		//	remaining nodes
		this.getVisualizer().setColor("red");
		this.getVisualizer().setShape("square");
	}

	/**
	 * Gets the sublist at this node, if it exists
	 *
	 * @return  the sublist head element, if it exists
	 */
	public MLelement getSubList() {
		return this.sub_list;
	}

	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 *
	 */
	public String getDataStructType() {
		return "MultiList";
	}

	/**
	 * Retrieves the element following this element
	 *
	 * @return MLelement<E> assigned to next
	 *
	 */
	public MLelement<E> getNext() {
		return (MLelement<E>) next;
	}


	/**
	 *
	 *	Sets the tag of the element.
	 *
	 *	@param[in]  t tag
	 *
	 */
	public void setTag(boolean t) {
		tag = t;
	}

	/**
	 *
	 *	Gets the tag of the element.
	 *
	 *  @return tag of the element
	 */
	public boolean getTag() {
		return tag;
	}

	/*
	 *	Get the JSON representation of the the data structure
	 */
	public String getDataStructureRepresentation() {

		Vector<Element<E> > nodes = new Vector<Element<E>> ();
		nodes.clear();
		getListElements(nodes);

		// generate the JSON of the list nodes
		StringBuilder nodes_JSON = new StringBuilder();
		HashMap<Element<E>, Integer> node_map = new HashMap<Element<E>, Integer>();

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
			MLelement<E> par = (MLelement<E>) nodes.get(k);
			if (par.tag) { 	// sub list
				MLelement<E> chld = par.sub_list;
				if (chld != null) { 		// add the link
					links_JSON
					.append(getLinkRepresentation(
							par.getLinkVisualizer(chld),
							Integer.toString(node_map.get(par)),
							Integer.toString(node_map.get(chld))))
					.append(COMMA);
				}
			}
			SLelement<E> chld = par.next;
			if (chld != null) { 		// add the link
				links_JSON
				.append(getLinkRepresentation(
						par.getLinkVisualizer(chld),
						Integer.toString(node_map.get(par)),
						Integer.toString(node_map.get(chld))))
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

	/*
	 *	Get the elements of the list
	 *
	 *	@param nodes  a vector of the nodes in the list
	 *
	 */
	protected void getListElements(Vector<Element<E>> nodes) {
		getListElements_R (this, nodes);
	}

	void getListElements_R (MLelement<E> list, Vector<Element<E>> nodes) {
		MLelement<E> el = list;
		// try to handld all lists in subclasses, except multilists
		while (el != null) {
			nodes.add(el);
			if (el.tag) {
				getListElements_R (el.sub_list, nodes);
			}
			el = el.getNext();
		}
	}
}
