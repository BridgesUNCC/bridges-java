package bridges.base;
import bridges.base.DataStruct;

import java.util.ArrayList;
import bridges.base.Color;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/*
 *  @brief This is a class in BRIDGES for deriving a
 *  number of Symbol objects for use in a SymbolCollection.
 *  Symbols correspond to a simplified subset of SVG paths
 *  and shapes for custom visual representations in BRIDGES.
 *
 * 	@author David Burlinson, Kalpathi Subramanian
 *
 *	@date  2018, 7/15/19
 *
*/
public class Symbol extends DataStruct {

	static	Integer ids = 0;
	private String identifier;
	protected String label = "";

	// Static default attribute values for all Symbols
	static final Color DEFAULT_FILLCOLOR = new Color("blue");
	static final Float DEFAULT_OPACITY = 1.0f;
	static final Color DEFAULT_STROKECOLOR = new Color("white");
	static final Float DEFAULT_STROKEWIDTH = 1.0f;
	static final Integer DEFAULT_STROKEDASH = 1;
	static final Float DEFAULT_LOCATIONX = 0.0f;
	static final Float DEFAULT_LOCATIONY = 0.0f;

	// default css attributes for Symbols
	protected Color fillColor = new Color("blue");
	protected Float opacity = DEFAULT_OPACITY;
	protected Color strokeColor = new Color("white");
	protected Float strokeWidth = DEFAULT_STROKEWIDTH;
	protected Integer strokeDash = DEFAULT_STROKEDASH;

	// default location attributes for Symbols
	protected Float locationX = DEFAULT_LOCATIONX;
	protected Float locationY = DEFAULT_LOCATIONY;

	/**
	 *  Get data structure name
	 *
	 *	@return data type name (string)
	 */
	public String getDataStructType() {
		return "Symbol";
	}

	/**
	 *	Create a default symbol object
	 */
	public Symbol() {
		super();
		this.identifier = ids.toString();
		this.label = "";
		ids++;
	}

	/**
	 * This method sets the label
	 *
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * This method gets the symbol label
	 *
	 * @return symbol label
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * This method returns the Symbol's unique identifier
	 * @return the string identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * This method sets the symbol fill color
	 *
	 * @param c the color to set
	 */
	public Symbol setFillColor(Color c) {
		this.fillColor = c;
		return this;
	}
	/**
	* This method gets fill color
	*
	* @return  fill color
	*/
	public Color getFillColor() {
		return this.fillColor;
	}

	/**
	 * This method sets the symbol stroke color
	 *
	 * @param c the color to set
	 */
	public Symbol setStrokeColor(Color c) {
		this.strokeColor = c;
		return this;
	}
	/**
	 * This method gets stroke color
	 *
	 * @return  stroke color
	 */
	public Color getStrokeColor() {
		return this.strokeColor;
	}

	/**
	 * This method sets the symbol stroke width
	 *
	 * @param strk_width the stroke width to set
	 */
	public Symbol setStrokeWidth(Float strokewidth) {
		if (strokewidth <= 0.0f || strokewidth > 10.0f) {
			throw new IllegalArgumentException("Stroke width must be between 0 and 10");
		}
		else {
			this.strokeWidth = strokewidth;
		}
		return this;
	}
	/**
	 * This method gets stroke width
	 *
	 * @return  stroke width
	 */

	public Float getStrokeWidth() {
		return this.strokeWidth;
	}

	/**
	 * This method sets the symbol opacity
	 *
	 * @param op the opacity to set
	 */
	public Symbol setOpacity(Float o) {
		if (o <= 0.0f || o > 1.0f) {
			throw new IllegalArgumentException("Opacity must be between 0 and 1");
		}
		else {
			this.opacity = o;
		}
		return this;
	}

	/**
	 * This method gets symbol opacity
	 *
	 * @return  symbol opacity
	 */
	public Float getOpacity() {
		return this.opacity;
	}

	/**
	 * This method sets the stroke dash level
	 *
	 * @param dash dash level
	 */
	public Symbol setStrokeDash(int dash) {
		if (dash < 0 || dash > 10) {
			throw new IllegalArgumentException("Dash must be between 0 and 10 (inclusive)");
		}
		else {
			this.strokeDash = dash;
		}
		return this;
	}

	/**
	 * This method gets stroke dash level
	 *
	 * @return  stroke dash level
	 */

	public Integer getStrokeDash() {
		return this.strokeDash;
	}


	/**
	 * This method sets the symbol location
	 *
	 * @param x  x coordinate
	 * @param y  y coordinate
	 */
	public Symbol setLocation(int x, int y) {
		setLocation((float) x, (float) y);
		return this;
	}

	/**
	 * This method sets the symbol location
	 *
	 * @param x  x coordinate
	 * @param y  y coordinate
	 */

	public Symbol setLocation(Float x, Float y) {
		if ((x > Float.NEGATIVE_INFINITY && x < Float.POSITIVE_INFINITY) &&
			(y > Float.NEGATIVE_INFINITY && y < Float.POSITIVE_INFINITY)) {
			this.locationX = x;
			this.locationY = y;
		}
		else {
			throw new IllegalArgumentException("Coordinates must be real numbers");
		}
		return this;
	}
	/**
	 * This method gets the symbol location
	 *
	 * @return location (x, y) of the symbol
	 */

	public Float[] getLocation() {
		return new Float[] {this.locationX, this.locationY};
	}
	/**
	 * Get Dimensions of symbol
	 * @return  symbol dimensions
	 */
	public Float[] getDimensions() {
		return new Float[] {0.0f, 0.0f, 0.0f, 0.0f};
	}

	/**
	 * Get JSON string of Data type
	 * @return JSON of data type (string)
	 */

	public String getDataStructureRepresentation() {
		return null;
	}

	/**
	 * Internal code for getting the properties of the Symbol object.
	 * @returns the encoded JSON string
	 */
	public JSONObject getJSONRepresentation() {
		JSONObject json_builder = new JSONObject();
		JSONObject location = new JSONObject();
		JSONArray myColor;

		if (fillColor.getRepresentation().compareTo(DEFAULT_FILLCOLOR.getRepresentation()) != 0) {
			myColor = new JSONArray();
			myColor.add(fillColor.getRed());
			myColor.add(fillColor.getGreen());
			myColor.add(fillColor.getBlue());
			myColor.add(fillColor.getAlpha());
			json_builder.put("fill", myColor);
		}

		if (opacity != DEFAULT_OPACITY) {
			json_builder.put("opacity", opacity);
		}

		if (strokeColor.getRepresentation().compareTo(DEFAULT_STROKECOLOR.getRepresentation()) != 0) {
			myColor = new JSONArray();
			myColor.add(strokeColor.getRed());
			myColor.add(strokeColor.getGreen());
			myColor.add(strokeColor.getBlue());
			myColor.add(strokeColor.getAlpha());
			json_builder.put("stroke", myColor);
		}

		if (strokeWidth != DEFAULT_STROKEWIDTH) {
			json_builder.put("stroke-width", strokeWidth);
		}

		if (strokeDash != DEFAULT_STROKEDASH) {
			json_builder.put("stroke-dasharray", strokeDash);
		}

		if (locationX != DEFAULT_LOCATIONX && locationY != DEFAULT_LOCATIONY) {
			location.put("x", locationX);
			location.put("y", locationY);
			json_builder.put("location", location);
		}

		return json_builder;
	}
}
