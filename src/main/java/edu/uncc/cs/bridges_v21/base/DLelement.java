package bridges.base;

/**
 *	
 * 	@author Mihai Mehedint, Kalpathi Subramanian
 *
 *	@date 6/22/16, 1/7/17, 5/17/17
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
 *	@param <E> The generic parameter object that is part of this element, representing
 *          application specific data.
 *
 *	\sa Example Tutorial at <br> 
 *		http://bridgesuncc.github.io/Hello_World_Tutorials/DLL.html
 */

public class DLelement<E> extends SLelement<E>{
	protected DLelement<E> prev;

	/** 
	 *	Constructs an empty DLelement with next and prev pointers set to null. 
	 */
	public DLelement(){
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
	public DLelement (String label, E e){
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
		this.next = next;
		this.prev = prev;
		this.setLinkVisualizer(next);
		this.setLinkVisualizer(prev);
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
		this.next = next;
		this.prev = prev;
		this.setLinkVisualizer(next);
		this.setLinkVisualizer(prev);
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
	 * @param next the DLelement that should be assigned to the next pointer
	 *
	 */
	public void setNext(DLelement<E> next) {
		this.next = next;
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
	 * @param prev the DLelement that should be assigned to the prev pointer
	 */
	public void setPrev(DLelement<E> prev) {
		this.prev = prev;
	}
}
