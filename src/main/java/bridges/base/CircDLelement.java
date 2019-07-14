package bridges.base;
/**
 *  @brief This class can be used to instantiate Circular Doubly Linked
 *  List Elements.
 *
 *	Structurally they are the same as doubly linked elements
 *  except that each node constructed with the next and the previous pointers
 *	points to itself.
 *
 *  User's implementation of the circularly linked list needs to ensure that
 *  the last node's next pointer points to the first node and the first node's
 *	previous pointer points to the last node, as the visualization generation
 *	is dependent on this.
 *
 *  Elements have labels (string) that are displayed on the visualization.
 *  Elements take an generic object E as a user defined parameter, which can be
 *	any native type or object.
 *
 *  Elements contain a visualizer (ElementVisualizer) object for setting visual
 *	attributes (color, shape, opacity, size), necessary for displaying them in a web
 *	browser.
 *
 *	Elements also have a LinkVisualizer object that is used when they are
 *	linked to another element, appropriate for setting link attributes, between
 *  the element and its previous or next nodes.
 *
 *  @author Kalpathi Subramanian
 *
 *	@date   7/17/16, 1/16/17, 7/14/19
 *
 *  @param <E>  the generic parameter object that contains application specific data,
 *		defined by the user when instantiating this object.
 *
 *	\sa Example Tutorial at <br>
 *		http://bridgesuncc.github.io/tutorials/CDLL.html
 *
 */

public class CircDLelement<E> extends DLelement<E> {
	/**
	 *
	 *	Constructs an empty CircDLelement with next and prev pointers set
	 *	to itself
	 *
	 */
	public CircDLelement() {
		super();
		this.next = this;
		this.prev = this;
	}

	/**
	 *	Constructs a CircDLelement labeled "label", holding an object "e",
	 *	with next and prev pointers set to itself
	 *
	 * 	@param label the label for this CircDLelement that shows up on the
	 *		Bridges visualization
	 * 	@param e the genereic object that this CircDLelement is holding
	 *
	 */
	public CircDLelement (String label, E e) {
		super(label, e);
		this.setNext(this);
		this.setPrev(this);
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
		this.setNext(this);
		this.setPrev(this);
	}

	/**
	 *	Constructs a DLelement holding an object "e", with the next pointer
	 *	set to the DLelement "next" and the prev pointer set to DLelement
	 *	"prev".
	 *
	 *	@param e the generic object that this CircDLelement is holding
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
	 *
	 *	This method gets the name of the data structure type
	 *
	 *	@return  The date structure type as a string
	 *
	 */
	public String getDataStructType() {
		return "CircularDoublyLinkedList";
	}

	/**
	 *
	 * This method returns the pointer to the next DLelement
	 *
	 * @return the DLelement assigned to the next pointer
	 *
	 */
	public CircDLelement<E> getNext() {
		return (CircDLelement<E>) next;
	}

	/**
	 *
	 * This method returns the pointer to the previous DLelement
	 *
	 * @return the DLelement assigned to the prev pointer
	 *
	 */
	public CircDLelement<E> getPrev() {
		return (CircDLelement<E>) prev;
	}
}
