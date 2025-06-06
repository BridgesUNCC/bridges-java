package bridges.data_src_dependent;

import java.lang.String;
import java.util.HashMap;
import java.util.Map.Entry;
import bridges.base.Color;

import bridges.data_src_dependent.USCounty;

/**
 * @brief This object stores a Country and links to information
 *
 * This object is used alongside with the WorldMap object. Each country
 * has a stroke color and fill color and a set of ids
 *
 * See tutorial at  https://bridgesuncc.github.io/tutorials/Map.html
 *
 * @author Kalpathi Subramanian
 * @date  Last modified June 6, 2025
 */

public class Country {

	private String name;
	private String alpha2_id;		// two character id
	private String alpha3_id;		// three character id
	private long numeric3_id;     // 3 digit numeric id
	private	Color stroke_color, fill_color;
	private float stroke_width;

	/**
	 *  @brief Default constructor
	 */
	public Country() {
		this.name = new String();
		this.alpha2_id = new String();
		this.alpha3_id = new String();
		this.numeric3_id= 0;
		this.stroke_width = 1.0f;
		this.fill_color = new Color("lightblue");
		this.stroke_color = new Color("green");
	}

	/** 
	 * @brief Constructor for a specific state
	 *
	 * @param state_name  state info
	 */
	public Country(String country, String alpha2_id, String alpha3_id, long numeric3_id) {
		this.name = country;
		this.alpha2_id = alpha2_id;
		this.alpha3_id = alpha3_id;
		this.numeric3_id= numeric3_id;
		this.stroke_width = 1.0f;
		this.fill_color = new Color("lightblue");
		this.stroke_color = new Color("green");
	}

	// getters, setters

	public String getCountryName() {
		return this.name;
	}
	public void setCountryName(String n) {
		this.name = n;	
	}

	public String getAlpha2Id() {
		return this.alpha2_id;
	}
	public void setAlpha2Id(String id) {
		this.alpha2_id = id;
	}

	public String getAlpha3Id(){
		return this.alpha3_id;
	}
	public void setAlpha3Id(String id) {
		this.alpha3_id = id;
	}

	public long getNumeric3Id() {
		return this.numeric3_id;
	}
	public void setNumeric3Id(int id) {
		this.numeric3_id = id;
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
};

