package bridges.data_src_dependent;

import java.lang.String;
import bridges.base.Color;

/**
* @brief Class that holds US County information
*
*
* A tutorial on how to use the these objects is available at:
* ???
*
* Each County object contains its county name, state name, geoid, fips code,
* stroke color, stroke width, fill color, stroke color
*
* @author Kalpathi Subramanian, Erik Saule
*
* @date 12/22/24
*/

public class USCounty {

	private String county_name, state_name, geoid, fips_code;
	private	Color stroke_color, fill_color;
	private float stroke_width;
	Boolean hide_flag;			// county visibility
	
	public USCounty() {
		this.county_name = new String();
		this.state_name = new String();
		this.stroke_width = 1.0f;
		this.geoid  = new String();
		this.fips_code  = new String();
		this.fill_color = new Color(255, 0, 0);
		this.stroke_color = new Color(0, 255, 0);
		this.hide_flag = false;
	}
	public USCounty(String geoid, String fips_code, String county_name,
			String state_name) {
		this.county_name = county_name;
		this.state_name = state_name;
		this.stroke_width = 1.0f;
		this.geoid  = geoid;
		this.fips_code  = fips_code;
		this.fill_color = new Color(255, 0, 0);
		this.stroke_color = new Color(0, 255, 0);
		this.hide_flag = false;
	}


	// getters, setters
	public String getCountyName() {
		return this.county_name;
	}
	public void setCountyName(String n) {
		this.county_name = n;	
	}
	public String getStateName() {
		return this.state_name;
	}
	public void setStateName(String n) {
		this.state_name = n;	
	}

	public String getGeoId() {
		return this.geoid;
	}
	public void setGeoId(String id) {
		this.geoid = id;	
	}
	public String getFipsCode() {
		return this.fips_code;
	}
	public void setFipsCode(String code) {
		this.fips_code = code;	
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
	void setStrokeWidth(float width) {
		stroke_width = width;
	}

	public Boolean getHideFlag()  {
		return this.hide_flag ;
	}
	public void setHideFlag(Boolean flag) {
		this.hide_flag = flag;
	}
};

