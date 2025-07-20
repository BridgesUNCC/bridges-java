package bridges.data_src_dependent;

import java.lang.String;
import bridges.base.Color;

/**
 * @brief Class that holds US County information
 *
 * This object is used alongside with the USMap object. Each county
 * has a stroke color and fill color attributes. Each county has a geoid
 * and FIPS code for identification. The geoid is used as the key in the
 * USState object to effic
 *
 * Each County object contains its county name, state name, geoid, fips code,
 * stroke color, stroke width, fill color, stroke color
 *
 * A tutorial on how to use the these objects is available at:
 * https://bridgesuncc.github.io/tutorials/Map.html
 *
 * @author Kalpathi Subramanian, Erik Saule
 * @date 12/22/24
 */

public class USCounty {

	private String county_name, state_name, geoid, fips_code;
	private	Color stroke_color, fill_color;
	private float stroke_width;
	Boolean hide_flag;			// county visibility
	
	/**
	 * @brief Default constructor
	 */
	public USCounty() {
		this.county_name = new String();
		this.state_name = new String();
		this.stroke_width = 1.0f;
		this.geoid  = new String();
		this.fips_code  = new String();
		this.fill_color = new Color(255, 0, 0);
		this.stroke_color = new Color(0, 0, 255);
		this.hide_flag = false;
	}
	/**
	 * @brief Constructor with county related parameters
	 *
	 * @param geoid  GeoId of the county
	 * @param fips_code  FIPSa Code of the county
	 * @param county_name  county
	 * @param state_name   state containing county
	 */
	public USCounty(String geoid, String fips_code, String county_name,
			String state_name) {
		this.county_name = county_name;
		this.state_name = state_name;
		this.stroke_width = 1.0f;
		this.geoid  = geoid;
		this.fips_code  = fips_code;
		this.fill_color = new Color("lightblue");
		this.stroke_color = new Color("green");
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

