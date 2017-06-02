package bridges.base;

/**
 * 	@brief This class can be used to instantiate Multi-list Elements.

 * 	This class extends SLelement (singly linked list element) to build multi-lists;
 *	Multilist elements contain a tag that indicates if the element is a sublist or not;
 *	If the element points to a sublist, then the sublist field is the beginning of 
 *	this sublist. If not, the data field contains the user specified data item and 
 *	list continues (getNext()/setNext()). As in singly linked elements, the next pointer 
 *	points to the following list element of the list or sublist. 
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
 * @author , Kalpathi Subramanian
 *
 * @date 5/24/17
 *
 * @param <E> The generic parameter object that is part of this element, representing
 *			either application specific data, or a pointer to a sublist.
 *
 *	\sa Example Tutorial at <br> ??  
 */

public class MLelement<E> extends SLelement<E> {

	protected MLelement<E> sub_list = null; // link to a sublist
	boolean tag = false; 

	/**
	 * 
	 * This constructor creates an SLelement object
	 * and sets the next pointer to null
	 * 
	 */

	public MLelement() {
		super();
		this.sub_list = null;
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
	public MLelement (String label, E e){
		super(label, e);
		this.sub_list = null;
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
	public MLelement (E e, MLelement<E> next) {
		super("", e);
		this.setNext(next);
		this.sub_list = null;
	}

	/**
	 * Creates a new element and sets the next pointer
	 * to the SLelement "next"
	 * @param next the SLelement that should be assigned to the next pointer
	 */
	public MLelement (MLelement<E> sublist, MLelement<E> next) {
		this.setNext(next);
		this.sub_list = sublist;
		if (sublist != null)
			tag = true;
	}

	/**
	 * Sets the start of a new sublist. 
	 * to the SLelement "next"
	 *
	 * @param sl the MLelement that is the beginning of a sublist 
	 */
	public void setSubList(MLelement<E> sl) {
		this.sub_list = sl;
		this.tag = true;
					// by default, color and shape sublist nodes to distinguish them 
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
	 * Sets the element to point to the next MLelement
	 *
	 * @param next SLelement<E> that should be assigned to the next pointer
	 */
	public void setNext(MLelement<E> next) {
		this.next = next;
	}

	/** 
	 *
	 *	Sets the tag of the element.
	 *
	 *	@param boolean t
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
}
