package bridges.base;
import bridges.base.DataStruct;

import java.util.ArrayList;
import bridges.base.Color;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 *  @brief This is a class in BRIDGES for deriving a
 *  number of Symbol objects for use in a SymbolCollection.
 *
 * It is not intended that objects from this class will be directly
 * created. Rather, we expect that classes that derive from this class
 * be instantiated such as Circle, Label, Polyline, Polygon,
 * Rectangle.
 *
 *  Symbols correspond to a simplified subset of SVG paths
 *  and shapes for custom visual representations in BRIDGES.
 *
 * The stroke is the actual lines that get drawn (such as the
 * perimeter of a rectangle) and its style is controlled by
 * setStrokeColor(), setStrokeWidth(), and setStrokeDash().
 *
 * The inside of the Symbol can be colored independently from the
 * stroke using setFillColor().
 *
 * The overall Symbol can be made more of less visible by adjusting
 * its opacity using setOpacity().
 *
 * 	@author David Burlinson, Kalpathi Subramanian
 *
 *	@date  2018, 7/15/19
 *
*/
public class Symbol {

	static	Integer ids = 0;
	private String identifier;
	protected String label = "";
	private String shape_type = "circle";

	// Static default attribute values for all Symbols
	static final Color DEFAULT_FILLCOLOR = new Color("blue");
	static final float DEFAULT_OPACITY = 1.0f;
	static final Color DEFAULT_STROKECOLOR = new Color("white");
	static final float DEFAULT_STROKEWIDTH = 1.0f;
	static final Integer DEFAULT_STROKEDASH = 1;
	static final float DEFAULT_LOCATIONX = 0.0f;
	static final float DEFAULT_LOCATIONY = 0.0f;

	// default css attributes for Symbols
	protected Color fillColor = new Color("blue");
	protected float opacity = DEFAULT_OPACITY;
	protected Color strokeColor = new Color("white");
	protected float strokeWidth = DEFAULT_STROKEWIDTH;
	protected Integer strokeDash = DEFAULT_STROKEDASH;

	// default location attributes for Symbols
	protected float locationX = DEFAULT_LOCATIONX;
	protected float locationY = DEFAULT_LOCATIONY;


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
	 *  Get the shape type
	 *
	 *	@return shape type
	 */
	protected String getShapeType() {
		return shape_type;
	}

	/**
	 *	Set the shape type
	 *	@param sh shape type
	 */
	protected void setShapeType(String sh) {
		shape_type = sh;
	}

	/**
	 * This method sets the symbol fill color
	 *
	 * @param c the color to set
	 */
	public void setFillColor(Color c) {
		this.fillColor = c;
	}
	/**
	 * This method sets the symbol fill color
	 *
	 * @param c the color to set
	 */
	public void setFillColor(String c) {
		this.fillColor = new Color(c);
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
	public void setStrokeColor(Color c) {
		this.strokeColor = c;
	}
	/**
	 * This method sets the symbol stroke color
	 *
	 * @param c the named color to set
	 */
	public Symbol setStrokeColor(String c) {
		this.strokeColor = new Color(c);
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
	 * @brief This method sets the symbol stroke width.
	 *
	 * This is the weight of the individual lines that are drawn, such as the perimeter of a rectangle.
	 *
	 * @param strokewidth the stroke width to set
	 */
	public void setStrokeWidth(float strokewidth) {
		if (strokewidth <= 0.0f || strokewidth > 10.0f) {
			throw new IllegalArgumentException("Stroke width must be between 0 and 10");
		}
		else {
			this.strokeWidth = strokewidth;
		}
	}
	/**
	 * This method gets stroke width
	 *
	 * @return  stroke width
	 */

	public float getStrokeWidth() {
		return this.strokeWidth;
	}

	/**
	 * @brief This method sets the symbol opacity
	 *
	 * @param op the opacity to set
	 */
	public Symbol setOpacity(float op) {
		if (op <= 0.0f || op > 1.0f) {
			throw new IllegalArgumentException("Opacity must be between 0 and 1");
		}
		else {
			this.opacity = op;
		}
		return this;
	}

	/**
	 * This method gets symbol opacity
	 *
	 * @return  symbol opacity
	 */
	public float getOpacity() {
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

	public void setLocation(float x, float y) {
		if ((x > Float.NEGATIVE_INFINITY && x < Float.POSITIVE_INFINITY) &&
			(y > Float.NEGATIVE_INFINITY && y < Float.POSITIVE_INFINITY)) {
			this.locationX = x;
			this.locationY = y;
		}
		else {
			throw new IllegalArgumentException("Coordinates must be real numbers");
		}

	}
    	/**
	 * This method sets the symbol location
	 *
	 * @param x  x coordinate
	 * @param y  y coordinate
	 */

	public void setLocation(double x, double y) {
	    setLocation((float)x, (float)y);
	}

	/**
	 * This method gets the symbol location
	 *
	 * @return location (x, y) of the symbol
	 */

	public float[] getLocation() {
		return new float[] {this.locationX, this.locationY};
	}

	/**
	 * Get Dimensions of symbol
	 * @return  symbol dimensions
	 */
	public float[] getDimensions() {
		return new float[] {0.0f, 0.0f, 0.0f, 0.0f};
	}

	/**
	 *  Translate (move)  a 2D point along X and Y
	 *  @param pt  2D point (x, y)
	 *  @param tx, ty translation vector
	 */
	protected void translatePoint (float[] pt, float tx, float ty) {
		pt[0] += tx;
		pt[1] += ty;
	}

	/**
	 * @brief Scale a 2D point; used to increase or decrease the size of objects
	 *
	 *  @param pt 2D point (x, y) to be scaled
	 *  @param sx,sy  scale factors (along each axis)
	 */
	protected void scalePoint (float[] pt, float sx, float sy) {
		pt[0] *= sx;
		pt[1] *= sy;
	}

	/**
	 *  @brief Rotate a 2D point (about Z)
	 *
	 *  @param pt  2D point (x, y)
	 *  @param angle rotation angle in degrees (positive is counter clockwise, 
	 *		negative is clockwise)
	 */
	protected void rotatePoint (float[] pt, float angle) {
		// compute sin, cos
		double angle_r =  Math.toRadians(angle);
		double c = Math.cos(angle_r);
		double s = Math.sin(angle_r);

		// rotate the point
		double[] tmp = new double[2];
		tmp[0] = pt[0] * c - pt[1] * s;
		tmp[1] = pt[0] * s + pt[1] * c;

		// assign to point
		pt[0] = (float) tmp[0];
		pt[1] = (float) tmp[1];
	}

	/**
	 * Internal code for getting the representation  of the Symbol object.
	 *
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

		if (!((locationX == DEFAULT_LOCATIONX) &&
				(locationY == DEFAULT_LOCATIONY))) {
			location.put("x", locationX);
			location.put("y", locationY);
			json_builder.put("location", location);
		}

		return json_builder;
	}
}
