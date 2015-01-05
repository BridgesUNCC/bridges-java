package bridges_vs2.Structure;


public class SLelement<E> extends Element<E>{
	protected SLelement<E> next; //the link to the next element 
	
	
	public SLelement (String identifier, E e){
		super(identifier, e);
	}
	
	public SLelement (SLelement<E> next) {
		super();
		this.next = next;
	}

	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	public void copySLelement (SLelement<E> original){
		this.identifier = new String(original.getIdentifier());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
		this.next = original.next;
		
	}
	
	public SLelement<E> getNext() {
		return next;
	}
	
	public void setNext(SLelement<E> next) {
		this.next = next;
	}
	
	public void removeNext(){
		this.next = null;
	}
}
