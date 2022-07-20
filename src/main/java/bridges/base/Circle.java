package bridges.base;

import bridges.base.Symbol;
import org.json.simple.JSONObject;
import java.util.ArrayList;

/**
 * @brief This class defines a circle and is part of the bridges::base::SymbolCollection.
 *
 *		A circle has a center and radius
 *
 * Basic styling such as stroke, fill, color are defined in the superclass Symbol.
 *
 * @sa An example tutorial can be found at
 * 		https://bridgesuncc.github.io/tutorials/Symbol_Collection.html
 *
 * @author Kalpathi Subramanian, Erik Saule
 * @date 12/24/18, 08/11/21
 *
 */
public class Circle extends Symbol {

	// radius of circle
	private float radius = 1.0f;
	private float x;
	private float y;

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
	public String getShapeType() {
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
		this.x = locx;
		this.y =  locy;
		if (r < 0)
			throw new IllegalArgumentException ("Illegal value for radius. Must be positive");
		radius = r;
	}


	/**
	 * This method returns the JSON representation of the shape
	 *
	 * @return JSON representation of circle (string)
	 */
	public JSONObject getJSONRepresentation() {

		// get the JSON of the attributes of the shape
		JSONObject shape_json = super.getJSONRepresentation();

		shape_json.put ("r", radius);

		ArrayList<Float> loc = new ArrayList<Float>();
		loc.add(x);
		loc.add(y);

		shape_json.put ("center", loc);

		return shape_json;
	}
};
