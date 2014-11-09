package bridges_vs2;

public class Element<value, T> {
	protected String identifier;
	protected Visualizer visualizer;
	private T aType;
	private value val;
	
	
	public Element(){
		super();
	}
	
	/**
	 * the constructor
	 * @param identifier
	 * @param aType
	 * @param val
	 */
	public Element (String identifier,
					value val){
		this.identifier = identifier;
		this.val = val;
		this.visualizer = new Visualizer();
	}
	
	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	public Element (Element<value, T> original){
		this.identifier = new String(original.getIdentifier());
		this.visualizer = new Visualizer(original.getVisualizer());
	}
	
	/**
	 * @return the string identifier
	 */
	public String getIdentifier(){
		return identifier;
	}
	
	/**
	 * 
	 * @return the visualizer
	 */
	public Visualizer getVisualizer(){
		return visualizer;
	}
}
