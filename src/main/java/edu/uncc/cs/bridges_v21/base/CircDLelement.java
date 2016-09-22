package bridges.base;
/**
 * @author Kalpathi Subramanian
 * @date  7/17/16
 *
 * This class can be used to create circular doubly linked element objects
 * with next and previous (prev) pointers
 *
 */

public class CircDLelement<E> extends DLelement<E>{
	/** 
	 *	Constructs an empty CircDLelement with next and prev pointers set 
	 *	to itself
	 */
	public CircDLelement(){
		super();
		this.next = this;
		this.prev = this;
	}
	
	/** Constructs a CircDLelement labeled "label", holding an object "e", 
	 *	with next and prev pointers set to itself
	 * 	@param label the label for this CircDLelement that shows up on the 
	 *		Bridges visualization
	 * 	@param e the genereic object that this CircDLelement is holding
	 */
	public CircDLelement (String label, E e){
		super(label, e);
		this.next = this;
		this.prev = this;
	}

	/** 
	 *	Constructs an empty DLelement with the next pointer set to the 
	 *	CircDLelement "next" and the prev pointer set to CircDLelement "prev". 
	 *
	 * 	@param next the DLelement that should be assigned to the next pointer
	 *	@param prev the DLelement that should be assigned to the prev pointer
	 *
	 */
	public CircDLelement(CircDLelement<E> next, CircDLelement<E> prev) {
		super();
		this.next = next;
		this.prev = prev;
	}

	/** 
	 *	Constructs a DLelement holding an object "e", with the next pointer 
	 *	set to the DLelement "next" and the prev pointer set to DLelement 
	 *	"prev". 
	 *
	 *	@param e the genereic object that this CircDLelement is holding
	 *	@param next the CircDLelement that should be assigned to the next 
	 *		pointer
	 *	@param prev the CircDLelement that should be assigned to the prev 
	 *	pointer
	 *
	 */
	public CircDLelement(E e, CircDLelement<E> next, CircDLelement<E> prev) {
		super(e, next, prev);
	}
	
	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 **/
	public String getDataStructType() {
		return "CircularDoublyLinkedList";
	}

	/**
	 * This method returns the pointer to the next DLelement
	 * @return the DLelement assigned to the next pointer
	 */
	public CircDLelement<E> getNext() {
		return (CircDLelement<E>) next;
	}
	
	/**
	 * This method sets the pointer to the next DLelement
	 *
	 * @param next the DLelement that should be assigned to the next pointer
	 *
	 */
	public void setNext(CircDLelement<E> next) {
		this.next = next;
	}
	

	/**
	 * This method returns the pointer to the previous DLelement
	 * @return the DLelement assigned to the prev pointer
	 */
	public CircDLelement<E> getPrev() {
		return (CircDLelement<E>) prev;
	}

	/**
	 * This method sets the pointer to the previous DLelement
	 * @param prev the DLelement that should be assigned to the prev pointer
	 */
	public void setPrev(CircDLelement<E> prev) {
		this.prev = prev;
	}
}
