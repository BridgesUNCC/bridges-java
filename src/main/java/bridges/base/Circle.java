package bridges.base;

import bridges.base.Symbol;
import org.json.simple.JSONObject;


/**
 * @brief This class defines a rectangle and is part of the symbol collection.
 *		A rectangle has height and width
 *
 * @author Kalpathi Subramanian
 * @date 12/24/18
 *
 */
public class Circle extends Symbol {

	private String shape = "circle";

	// radius of circle
	private int radius = 10;


	/**
	 *  constructors
	 */
	public Circle () {
		super();
	}

	public Circle (int r) {
		this();
		if (r < 0)
			throw new IllegalArgumentException ("Radius value needs to be positive");
		radius = r;
	}

	// provides both location and radius

	public Circle (Float locx, Float locy, int r) {
		this(r);
		setLocation (locx, locy);
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
	public void setRadius(int r) {
		if (r < 0)
			throw new IllegalArgumentException ("Illegal value for radius. Must be positive");
		radius = r;
	}

	/**
	 * This method sets the circle dimensions
	 *
	 * @param locx  x coordinat of location
	 * @param locy  y coordinat of location
	 * @param r  radius
	 * @return none
	 */
	public void setCircle (Float locx, Float locy, int r) {
		setLocation (locx, locy);
		if (r < 0)
			throw new IllegalArgumentException ("Illegal value for radius. Must be positive");
		radius = r;
	}

	/**
	 * This method returns the dimensions of the shape: min and max
	 *	values in X and Y
	 *
	 * @param none
	 * @return array of 4 values
	 */
	public Float[] getDimensions() {

		Float[] dims = new Float[4];
		Float[]  location = getLocation();

		dims[0] = location[0] - radius;
		dims[1] = location[0] + radius;
		dims[2] = location[1] - radius;
		dims[3] = location[1] + radius;

		return dims;
	}

	/**
	 * This method returns the JSON representation of the shape
	 *
	 * @return string  JSON string
	 */
	public JSONObject getJSONRepresentation() {

		// get the JSON of the attributes of the shape
		JSONObject shape_json = super.getJSONRepresentation();

		shape_json.put ("name", getLabel());
		shape_json.put ("shape", shape);
		shape_json.put ("r", radius);

		return shape_json;
	}
};
