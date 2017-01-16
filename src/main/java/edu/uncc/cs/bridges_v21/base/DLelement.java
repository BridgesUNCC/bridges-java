package bridges.base;
/**
 *	
 * 	@author Mihai Mehedint
 *
 * 	This class can be used to create doubly linked element objects
 * 	with next and previous prevous pointers
 *
 */

public class DLelement<E> extends Element<E>{
	protected DLelement<E> next;
	protected DLelement<E> prev;

	/** 
	 *	Constructs an empty DLelement with next and prev pointers set to null. 
	 */
	public DLelement(){
		super();
		this.next = null;
		this.prev = null;
	}
	
	/** Constructs a DLelement labeled "label", holding an object "e", 
	 *	with next and prev pointers set to null. 
	 * @param label the label for this DLelement that shows up on the 
	 *		Bridges visualization
	 * @param e the genereic object that this DLelement is holding
	 */
	public DLelement (String label, E e){
		super(label, e);
		this.next = null;
		this.prev = null;
	}

	/** Constructs an empty DLelement with the next pointer set to the 
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
	}

	/** 
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
	}
	
	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 **/
	public String getDataStructType() {
		return "DoublyLinked List";
	}

	/**
	 * This method returns the pointer to the next DLelement
	 * @return the DLelement assigned to the next pointer
	 */
	public DLelement<E> getNext() {
		return next;
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
	 * @return the DLelement assigned to the prev pointer
	 */
	public DLelement<E> getPrev() {
		return prev;
	}

	/**
	 * This method sets the pointer to the previous DLelement
	 * @param prev the DLelement that should be assigned to the prev pointer
	 */
	public void setPrev(DLelement<E> prev) {
		this.prev = prev;
	}
}
