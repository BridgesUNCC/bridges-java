package bridges.data_src_dependent;

import java.lang.String;
import java.util.HashMap;
import java.util.Map.Entry;
import bridges.base.Color;

import bridges.data_src_dependent.USCounty;

/**
* @brief Class for USState and its attributes
*
*
* A tutorial on how to use the these objects is available at:
*  ???
*
*  Each State object contains its name, stroke color, stroke width, fill color, 
*  and a list of counties 
*
* @author Kalpathi Subramanian, Erik Saule
*
* @date 12/22/24
*/

public class USState {

	private String name;
	private	Color stroke_color, fill_color;
	private float stroke_width;
	private Boolean view_counties;	// boolean if counties are stored
	private HashMap<String,USCounty> counties;

	public USState() {
		this.name = new String();
		this.stroke_width = 1.0f;
		this.fill_color = new Color();
		this.stroke_color = new Color(0, 255, 0);
		this.view_counties = true;
		this.counties = new HashMap<String,USCounty> ();
	}

	public USState(String state_name) {
		this.name = state_name;
		this.stroke_width = 1.0f;
		this.fill_color = new Color("blue");
		this.stroke_color = new Color(0, 255, 0);
		this.view_counties = true;
		this.counties = new HashMap<String,USCounty> ();
	}

	// getters, setters
	public HashMap<String, USCounty> getCounties() {
		return this.counties;
	}

	public void setCounties(HashMap<String, USCounty> c) {
		this.counties = c;
	}

	public String getStateName() {
		return this.name;
	}
	public void setStateName(String n) {
		this.name = n;	
	}

	public Color getStrokeColor()  {
		return this.stroke_color;
	}
	public void setStrokeColor(Color c) {
		this.stroke_color = c; 
	}

	public Color getFillColor()  {
		return this.fill_color;
	}
	public void setFillColor(Color c) {
		this.fill_color = c;
	}

	public float getStrokeWidth()  {
		return stroke_width;
	}
	public void setStrokeWidth(float width) {
		stroke_width = width;
	}

	public Boolean getViewCountiesFlag()  {
		return view_counties;
	}
	public void setViewCountiesFlag(Boolean flag) {
		view_counties = flag;
	}
};

