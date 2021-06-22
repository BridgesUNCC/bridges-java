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
 * 	@author David Burlinson, Kalpathi Subramanian, Erik Saule
 *
 *	@date  2018, 7/15/19
 *	@date  2020, 6/22/21
 *
*/
public abstract class Symbol {

	protected String label = null;

    protected Color fillColor = null;
    protected Float opacity = null;
    protected Color strokeColor = null;
    protected Float strokeWidth = null;
    protected Integer strokeDash = null;
    protected Integer layer = null;
    protected float[] transform = null;
    
	/**
	 *	Create a default symbol object
	 */
	public Symbol() {
		super();
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
	 *  Get the shape type
	 *
	 *	@return shape type
	 */
	protected String getShapeType() {
	    
	    return "";
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
	    this.strokeWidth = strokewidth;
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
	public Float getOpacity() {
		return this.opacity;
	}

	/**
	 * This method sets the stroke dash level
	 *
	 * @param dash dash level
	 */
	public Symbol setStrokeDash(int dash) {
	    this.strokeDash = dash;
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
	 * This method sets the layer the symbol sits on
	 *
	 * @param layer layer (lower value closer to camera)
	 */
	public Symbol setLayer(int layer) {
	    this.layer = layer;
	    return this;
	}

	/**
	 * This method gets layer
	 *
	 * @return  layer layer (lower value closer to camera)
	 */

	public Integer getLayer() {
	    return this.layer;
	}


    public Symbol setTransform (float a, float b, float c,
				float d, float e, float f) {
	float[] mat = new float[6];
	mat[0] = a; 	mat[1] = b; 	mat[2] = c;
	mat[3] = d; 	mat[4] = e; 	mat[5] = f;
	this.transform = mat;
	return this;
    }

    public float[] getTransform () {
	
	return this.transform;
    }
    
	/**
	 * Internal code for getting the representation  of the Symbol object.
	 *
	 * @returns the encoded JSON string
	 */
	public JSONObject getJSONRepresentation() {
		JSONObject json_builder = new JSONObject();
		JSONArray myColor;

		json_builder.put ("type", getShapeType());
		
		if (fillColor != null) {
			myColor = new JSONArray();
			myColor.add(fillColor.getRed());
			myColor.add(fillColor.getGreen());
			myColor.add(fillColor.getBlue());
			myColor.add(fillColor.getAlpha());
			json_builder.put("fill-color", myColor);
		}

		if (opacity != null) {
			json_builder.put("opacity", opacity);
		}

		if (transform != null) {
			json_builder.put("transform", transform);
		}

		if (label != null) {
			json_builder.put("label", label);
		}

		
		if (strokeColor != null) {
			myColor = new JSONArray();
			myColor.add(strokeColor.getRed());
			myColor.add(strokeColor.getGreen());
			myColor.add(strokeColor.getBlue());
			myColor.add(strokeColor.getAlpha());
			json_builder.put("stroke-color", myColor);
		}

		if (strokeWidth != null) {
			json_builder.put("stroke-width", strokeWidth);
		}

		if (strokeDash != null) {
			json_builder.put("stroke-dasharray", strokeDash);
		}

		return json_builder;
	}
}
