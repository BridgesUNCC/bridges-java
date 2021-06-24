package bridges.base;
import bridges.base.DataStruct;

import java.util.ArrayList;
import bridges.base.Color;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 * @brief This is a class in BRIDGES for deriving a
 * number of Symbol objects for use in a SymbolCollection.
 *
 * It is not intended that objects from this class will be directly
 * created. Rather, we expect that classes that derive from this class
 * be instantiated such as Circle, Polyline, Polygon, Rectangle, Text.
 *
 * Symbols correspond to a simplified subset of SVG paths
 * and shapes for custom visual representations in BRIDGES.
 *
 * The stroke is the actual lines that get drawn (such as the
 * perimeter of a rectangle) and its style is controlled by
 * setStrokeColor(), setStrokeWidth(), and setStrokeDash().
 *
 * The inside of the Symbol can be colored independently from the
 * stroke using setFillColor().
 *
 * Affine transformations are supported with each symbol. If specified,
 * these will transform the shape (pre-multiply); translation, rotation and
 * scale transforms are supported. These can be chained to form a composite
 * transform
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

	// represents the affine transform of the Symbol
	private float[3][3] xform;
    
	/**
	 *	Create a default symbol object
	 */
	public Symbol() {
		super();
		identity(xform);
	}

	/**
	 * This method sets the label
	 *
	 * @param label the label to set
	 */
	public Symbol setLabel(String label) {
		this.label = label;
		return this;
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
	 * @return the symbol
	 */
	public Symbol setFillColor(String c) {
		this.fillColor = new Color(c);
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
	 * @return the symbol
	 */
	public Symbol setStrokeColor(Color c) {
		this.strokeColor = c;
		return this;
	}
	/**
	 * This method sets the symbol stroke color
	 *
	 * @param c the named color to set
	 * @return the symbol
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
	 * This is the weight of the individual lines that are drawn, such as the 
	 *	perimeter of a rectangle.
	 *
	 * @param strokewidth the stroke width to set
	 * @return the symbol
	 */
	public Symbol setStrokeWidth(float strokewidth) {
	    this.strokeWidth = strokewidth;
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
	 * @brief This method sets the symbol opacity
	 *
	 * @param op the opacity to set
	 * @return the symbol
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
	 * @return the symbol
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
	 * @return the symbol
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

	/** 
	 *	Matrix multiplication - premultiplication; multiplies m1 and m2 and stores
	 *  result in m1
	 *  
	 */
	private void matMult (float[3][3] m1, float[3][3] m2, float[3][3]) {
		// multiply m1 and m2
		float[3][3] result;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				result[i][j] = 0.;
				for (int k = 0; k < 3; ++k) 
					result[i][j] += m1[i][k] * m2[k][j];
			}
		}
    	// copy into m1
		for (int i = 0; i < 3; ++i) 
		for (int j = 0; j < 3; ++j) 
			m1[i][j] = result[i][j];
	}


	/** 
     *  create the identity matrix
	 */ 
	void identity(float[3][3] m) {
		for (int i = 0; i < 3; ++i) 
		for (int j = 0; j < 3; ++j) 
			if (i == j)
				m[i][j] = 1.;
			else
				m[i][j] = 0.;
	}
	
	/** 
     *  translate the symbol by tx, ty along the X and Y axes
	 * 
     * 	@param tx  translation in X
     * 	@param ty  translation in Y
	 */
	void translate (float tx, float ty) {
		float transl[3][3];

		identity(transl);

		// apply translation factors
		transl[2][0] = tx;
		transl[2][1] = ty;

		// post multiply
		matMult (xform, transl);
	}
	/** 
     *  scale the symbol by sx, sy along the X and Y axes
	 * 
     * 	@param sx  scale factor in X
     * 	@param sy  scale factor in Y
	 */
	void scale (float sx, float sy) {
		float scale[3][3];
		identity(scale);
		// apply scale factors
		scale[0][0] = sx; 
		scale[1][1] = sy;

		// post multiply
		matMult (xform, scale);
	}
	/** 
     *  rotate the symbol by angle theta  about Z axis (2D rotation)
	 * 
     * 	@param angle  angle (in degrees)
	 */
	void rotate (float angle) {
		float rotation[3][3];
		identity(rotation);

		// convert to radians
		float angle_r = angle * (float) (Math.PI/180.);
		float cos_a = Math.cos(angle);
		float sin_a = Math.sin(angle);
		// apply rotation factors
		rotation[0][0] = rotation[1][1] = cos_a; 
		rotation[0][1] = -sin_a; rotation[1][0] = sin_a;

		// post multiply
		matMult (xform, rotation);
	}

	/**
	 * This method sets the transform matrix for this symbol; terms passed in
	 * row major order (2 rows by 3 columns)
	 *
	 * @param a transformation term 
	 * @param b transformation term 
	 * @param c transformation term 
	 * @param d transformation term 
	 * @param e transformation term 
	 * @param f transformation term 
	 *
	 * @return the symbol
	 */
    public Symbol setTransform (float a, float b, float c,
				float d, float e, float f) {
		float[] mat = new float[6];
		mat[0] = a; 	mat[1] = b; 	mat[2] = c;
		mat[3] = d; 	mat[4] = e; 	mat[5] = f;
		this.transform = mat;
		return this;
    }

	/**
	 * This method returns the affine transformation associated with this
	 * symbol
	 *
	 * @return  transformation matrix
	 */
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
		    ArrayList<Float> al = new ArrayList<Float>();
		    al.add(transform[0]);
		    al.add(transform[1]);
		    al.add(transform[2]);
		    al.add(transform[3]);
		    al.add(transform[4]);
		    al.add(transform[5]);
			json_builder.put("transform", al);
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

    public void addAllJSON (JSONArray symbol_json, Integer parent) {
	int id = symbol_json.size();

	JSONObject obj = this.getJSONRepresentation();

	obj.put("ID", id);
	
	if (parent != null) {
	    obj.put("parentID", parent);	    
	}
	
	symbol_json.add(obj);
    }
}
