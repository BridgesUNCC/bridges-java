package bridges_vs2;

public class SLelement<value, T> extends Element<value, T>{
	protected Link<T> next;
	
	
	public SLelement (){
		super();
	}
	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	public SLelement (SLelement<value, T> original){
		this.identifier = new String(original.getIdentifier());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
		this.next = original.next;
	}

}
