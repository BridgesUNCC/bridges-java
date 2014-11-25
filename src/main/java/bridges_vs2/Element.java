package bridges_vs2;

public class Element<Value, Type> {
	protected String identifier;
	protected ElementVisualizer visualizer;
	private Type type;
	private Value val;
	
	
	public Element(){
		super();
		this.visualizer = new ElementVisualizer();
	}
	
	/**
	 * the constructor
	 * @param identifier
	 * @param aType
	 * @param val
	 */
	public Element (String identifier,
					Value val){
		this.identifier = identifier;
		this.val = val;
		this.visualizer = new ElementVisualizer();
	}
	
	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	public Element (Element<Value, Type> original){
		this.identifier = new String(original.getIdentifier());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
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
	public ElementVisualizer getVisualizer(){
		return visualizer;
	}
}
