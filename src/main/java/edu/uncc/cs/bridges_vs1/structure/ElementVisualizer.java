package edu.uncc.cs.bridges_vs1.structure;

import java.util.HashMap;
import java.util.Map;

import edu.uncc.cs.bridges_vs1.validation.Validation;
import edu.uncc.cs.bridges_vs1.validation.*;

public class ElementVisualizer {
	private String aColor = "black";
	private String aShape = "circle";
	private double opacity = 0.5;
	private double size = 10.0;
	// Visualization properties for this Node.
	Map<String, String> properties =  new HashMap<String, String>(){{
		put("color","green");
		put("opacity","1.0");
		put("weight","1.0");
		put("width","1.0");
		}}; 
	
	public ElementVisualizer (){
		super();
	}
	
	public ElementVisualizer (String aColor){
		super();
		this.aColor = aColor;
		setColor(aColor);
	}
	
	public ElementVisualizer (String aColor, String aShape){
		this(aColor);
		this.aShape = aShape;
		setShape(aShape);
	}
	
	public ElementVisualizer (double size){
		super();
		this.size = size;
		setSize(size);
	}
	
	public ElementVisualizer(String aColor, String aShape, double opacity, double size){
		this(aColor, aShape);
		this.opacity = opacity;
		this.size = size;
	}
	
	public ElementVisualizer (ElementVisualizer v){
		this(v.getColor(), v.getShape(), v.getOpacity(), v.getSize());
	}
	
	/**
	 * The size is in pixels
	 * @param size
	 */
	public void setSize(double size){
		this.size = size;
		Validation.validateSize(size);
		properties.put("size", Double.toString(size));
	}
	
	public double getSize(){
		return Double.parseDouble(properties.get("size"));
	}

	public void setColor(String aColor){
		//this.aColor = aColor;
		aColor = aColor.toLowerCase();
		if (aColor == null || aColor.isEmpty()) {
			properties.put("color", this.aColor);
		} else {
			Validation.validateColor(aColor);
			properties.put("color", aColor);
		}
	}
	
	/**
	 * @return the element's color
	 */
	public String getColor(){
		return properties.get("color");
	} 
	
	/**
	 * 
	 * @return the element's shape
	 */
	public String getShape(){
		return properties.get("shape");
	}
	
	/**
	 * Sets the shape of the element
	 * @param aShape
	 */
	public void setShape(String aShape){
		//this.aShape = aShape;
		
		aShape = aShape.toLowerCase();
		if(aShape.equals("square")){
			aShape = "rect";
		}
		Validation.validateShape(aShape);
		properties.put("shape", aShape);
	}
	
	/**
	 * Sets the opacity
	 * @param opacity
	 */
	public void setOpacity(double opacity){
		Validation.validateOpacity(opacity);
		properties.put("opacity", Double.toString(opacity));
	}
	
	/**
	 * @return the opacity value
	 */
	public double getOpacity(){
		String prop = properties.get("opacity");
		if (prop == null)
			return 1.0;
		else
			return Double.parseDouble(properties.get("opacity"));
	}
	
}
