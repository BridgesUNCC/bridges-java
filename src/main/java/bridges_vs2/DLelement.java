package bridges_vs2;

public class DLelement<value, T> extends Element<value, T>{
	private Link<T> next;
	private Link<T> prev;
	
	public DLelement(){
		super();
	}
	
	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	public DLelement (DLelement<value, T> original){
		this.identifier = new String(original.getIdentifier());
		this.visualizer = new Visualizer(original.getVisualizer());
		this.next = original.next;
		this.prev = original.prev;
	}
}
