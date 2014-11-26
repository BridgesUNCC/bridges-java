package bridges_vs2;

public class Element<Val, Ty> {
	protected String identifier;
	protected ElementVisualizer visualizer;
	private Ty Ty;
	private Val val;
	
	
	public Element(){
		super();
		this.visualizer = new ElementVisualizer();
	}
	
	/**
	 * the constructor
	 * @param identifier
	 * @param aTy
	 * @param val
	 */
	public Element (String identifier,
					Val val){
		this.identifier = identifier;
		this.val = val;
		this.visualizer = new ElementVisualizer();
	}
	
	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	public Element (Element<Val, Ty> original){
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
