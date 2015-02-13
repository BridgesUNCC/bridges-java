package edu.uncc.cs.bridgesV2.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.uncc.cs.bridgesV2.validation.*;

public class ElementVisualizer {
	// Visualization properties for this Node.
	Map<String, String> properties =  new HashMap<String, String>(){{
		put("color","green");
		put("opacity","1.0");
		put("size","10.0");
		put("shape","circle");
		}}; 
	
	public ElementVisualizer (){
		super();
	}
	
	public ElementVisualizer (String aColor){
		super();
		setColor(aColor);
	}
	
	public ElementVisualizer (String aColor, String aShape){
		this(aColor);
		setShape(aShape);
	}
	
	public ElementVisualizer (double size){
		super();
		setSize(size);
	}
	
	public ElementVisualizer(String aColor, String aShape, double opacity, double size){
		this(aColor, aShape);
		
		setOpacity(opacity);
		setSize(size);
	}
	
	public ElementVisualizer (ElementVisualizer v){
		this(v.getColor(), v.getShape(), v.getOpacity(), v.getSize());
		
	}
	
	/**
	 * The size is in pixels
	 * @param size
	 */
	public void setSize(double size){
		Validation.validateSize(size);
		properties.put("size", Double.toString(size));
	}
	
	public double getSize(){
		return Double.parseDouble(properties.get("size"));
	}

	public String setColor(String aColor){
		//this.aColor = aColor;
		aColor = aColor.toLowerCase();
		if (aColor == null || aColor.isEmpty()) {
			properties.put("color", aColor);
		} else {
			Validation.validateColor(aColor);
			properties.put("color", aColor);
		}
		return aColor;
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
	
	/**
	 * The randomColor method selects a random color from the available
	 * list of colors found in Validation.java and sets the color
	 * of the current element
	 * @return a color name as a string value
	 */
	public String randomColor(){
		Object [] a=Validation.COLOR_NAMES.toArray();
		return setColor(a[new Random().nextInt(a.length)].toString());
	}
	
}
