package bridges_vs2;

public class ElementVisualizer {
	private String aColor = "black";
	private String aShape = "circle";
	private String opacity = "0.5";
	private double size = 10.0;
	
	public ElementVisualizer (){
		super();
	}
	
	public ElementVisualizer (String aColor){
		super();
		this.aColor = aColor;
	}
	
	public ElementVisualizer (String aColor, String aShape){
		this(aColor);
		this.aShape = aShape;
	}
	
	public ElementVisualizer (double size){
		super();
		this.size = size;
	}
	
	public ElementVisualizer(String aColor, String aShape, String opacity, double size){
		this(aColor, aShape);
		this.opacity = opacity;
		this.size = size;
	}
	
	public ElementVisualizer (ElementVisualizer v){
		this(v.getColor(), v.getShape(), v.getOpacity(), v.getSize());
	}
	
	public void setSize(double size){
		this.size = size;
	}
	
	public double getSize(){
		return size;
	}

	public void setColor(String aColor){
		this.aColor = aColor;
	}
	
	public String getColor(){
		return aColor;
	} 
	
	public String getShape(){
		return aShape;
	}
	
	public void setShape(String aShape){
		this.aShape = aShape;
		
	}
	
	public void setOpacity(String opacity){
		this.opacity = opacity;
	}
	
	public String getOpacity(){
		return opacity;
	}
	
}
