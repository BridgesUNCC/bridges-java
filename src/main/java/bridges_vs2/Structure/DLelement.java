package bridges_vs2.Structure;


public class DLelement<E> extends Element<E>{
	private DLelement<E> next;
	private DLelement<E> prev;
	
	public DLelement(){
		super();
	}
	
	public DLelement(DLelement<E> next, DLelement<E> prev) {
		super();
		this.next = next;
		this.prev = prev;
	}

	
	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	public void copyDLelement (DLelement<E> original){
		this.identifier = new String(original.getIdentifier());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
		this.next = original.next;
		this.prev = original.prev;
	}

	public DLelement<E> getNext() {
		return next;
	}

	public void setNext(DLelement<E> next) {
		this.next = next;
	}

	public DLelement<E> getPrev() {
		return prev;
	}

	public void setPrev(DLelement<E> prev) {
		this.prev = prev;
	}
}
