package bridges.base;

import bridges.base.Symbol;
import org.json.simple.JSONObject;


/**
 * @brief This class defines a circle and is part of the symbol collection.
 *		A circle has a center and radius
 *
 * @author Kalpathi Subramanian
 * @date 12/24/18
 *
 */
public class Circle extends Symbol {

	// radius of circle
	private float radius = 1.0f;


	/**
	 *  Construct a default circle  (center at origin, radius of 10 units)
	 */
	public Circle () {
		super();
		setCircle(0.0f, 0.0f, 1.0f);
	}

	/**
	 *  Construct a circle of radius r
	 *	@param r  radius of circle
	 */
	public Circle (float r) {
		super();
		setCircle(0.0f, 0.0f, r);
	}

	/**
	 *	Construct a circle with given location and radius
	 *  @param locx  x coordinat of circle center
	 *  @param locy  y coordinat of circle center
	 *	@param r  radius of circle
	 */
	public Circle (float locx, float locy, float r) {
		super();
		setCircle (locx, locy, r);
	}

	/**
	 *	This method gets the name of the shape
	 *
	 *  @return name   shape name
	 */
	public String getName() {
		return "circle";
	}

	/**
	 * This method sets the radius of the circle
	 *
	 * @param r  radius
	 */
	public void setRadius(float r) {
		if (r < 0)
			throw new IllegalArgumentException ("Illegal value for radius. Must be positive");
		radius = r;
	}

	/**
	 * This method sets the circle dimensions
	 *
	 * @param locx  x coordinate of circle center
	 * @param locy  y coordinate of circle center
	 * @param r  radius of circle
	 * @return none
	 */
	public void setCircle (float locx, float locy, float r) {
		setLocation (locx, locy);
		if (r < 0)
			throw new IllegalArgumentException ("Illegal value for radius. Must be positive");
		radius = r;
		setShapeType("circle");
	}

	/**
	*  Translate the circle
	*
	*  @param (tx, ty) translation vector
	*/
	public void translate(float tx, float ty) {
		float[] center = getLocation();
		this.translatePoint (center, tx, ty);
		setLocation(center[0], center[1]);
	}
	/**
	*  Scale the circle
	*  Only the radius needs to be scaled, using a single scale value
	*
	*  @param scale factor s
	*/
	public void scale(float scale) {
		// scale only the radius,
		// center stays the same
		radius  *= scale;
	}
	/**
	* This method returns the dimensions of the shape: min and max
	*  values in X and Y
	*
	* @return array of 4 values
	*/

	/**
	 * This method returns the dimensions of the shape: min and max
	 *	values in X and Y
	 *
	 * @return bounding box of circle (array of 4 values)
	 */
	public float[] getDimensions() {

		float[] dims = new float[4];
		float[]  location = getLocation();

		dims[0] = location[0] - radius;
		dims[1] = location[0] + radius;
		dims[2] = location[1] - radius;
		dims[3] = location[1] + radius;

		return dims;
	}

	/**
	 * This method returns the JSON representation of the shape
	 *
	 * @return JSON representation of circle (string)
	 */
	public JSONObject getJSONRepresentation() {

		// get the JSON of the attributes of the shape
		JSONObject shape_json = super.getJSONRepresentation();

		String shape = getShapeType();

		shape_json.put ("name", getLabel());
		shape_json.put ("shape", shape);
		shape_json.put ("r", radius);

		return shape_json;
	}
};
