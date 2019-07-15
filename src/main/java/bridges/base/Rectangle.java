
package bridges.base;

import bridges.base.Symbol;
import org.json.simple.JSONObject;

/**
 * @brief This class defines a rectangle and is part of the symbol collection.
 *		A rectangle has height and width
 *
 * @author Kalpathi Subramanian
 * @date 12/23/18
 *
 */
public	class Rectangle extends  Symbol {

	private String shape = "rect";

	// height, width of rectangle
	private Integer width = 10, height = 10;


	/**
	 *  Construct a default rectangle
	 */
	public Rectangle() {
		super();
	}

	/**
	 *  Construct a rectangle with given height and width
	 *  @param w  width of rectangle
	 *  @param h  height of rectangle
	 */
	public Rectangle (int w, int h) {
		this();
		if (w < 0 || h < 0)
			throw new IllegalArgumentException ("Illegal height or width! Height and Width values need to be positive");

		width = w;
		height = h;
	}

	/**
	 *  Construct a rectangle with given its location (center),  height and width
	 *  @param locx x coordinate of center
	 *  @param locy y coordinate of center
	 *  @param w  width of rectangle
	 *  @param h  height of rectangle
	 */
	public Rectangle (Float locx, Float locy, int w, int h) {
		this(w, h);
		setLocation (locx, locy);
	}

	/**
	 *	This method gets the name of the shape
	 *
	 *  @return name   shape name
	 */
	public String getName()  {
		return "rect";
	}

	/**
	 * This method sets the shape width
	 *
	 * @param w  width
	 */
	public void setWidth(int w) {
		if (w <= 0 || w > 300) {
			throw new IllegalArgumentException ("Width need to be in the range(0-300)");
		}
		width = w;
	}

	/**
	 * This method sets the shape height
	 *
	 * @param h  height
	 */
	void setHeight(int h) {
		if (h <= 0 || h > 300) {
			throw new IllegalArgumentException ("Height need to be in the range(0-300)");
		}
		height = h;
	}


	/**
	 * This method returns the dimensions of the shape: min and max
	 *	values in X and Y
	 *
	 * @return rectangle's bounding box (array of 4 values)
	 */
	public Float[] getDimensions() {

		Float[] dims = new Float[4];
		Float[] location = getLocation();

		dims[0] = location[0] - width / 2;
		dims[1] = location[0] + width / 2;
		dims[2] = location[1] - height / 2;
		dims[3] = location[1] + height / 2;

		return dims;
	}

	/*
	 * This method sets the location and size of the rectangle
	 *
	 * @pram locx  x coordinate of location
	 * @pram locy  y coordinate of location
	 * @param w  width of rectangle
	 * @param h  height of rectangle
	 *
	 * @return none
	 */
	public void setRectangle(Float locx, Float locy, int w, int h) 	{

		setLocation (locx, locy);
		if (w <= 0 || w > 300 || h <= 0 ||  w > 300) {
			throw new IllegalArgumentException ("Height, Width need to be in the range(0-300)");
		}

		width = w;
		height = h;
	}

	/**
	 * This method returns the JSON representation of the shape
	 *
	 * @return JSON representation of rectangle (string)
	 */
	public JSONObject getJSONRepresentation() {

		// get the JSON of the attributes of the shape
		JSONObject shape_json = super.getJSONRepresentation();

		shape_json.put ("name", getLabel());
		shape_json.put ("shape", shape);
		shape_json.put ("width", width);
		shape_json.put ("height", height);

		return shape_json;
	}
};
