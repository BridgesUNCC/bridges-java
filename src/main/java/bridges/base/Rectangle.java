
package bridges.base;

import bridges.base.Symbol;
import org.json.simple.JSONObject;

/**
 * @brief This class defines a rectangle and is part of the symbol collection.
 *		A rectangle has height and width
 *
 * Basic styling such as stroke and fill are defined in the superclass Symbol.
 *
 * @author Kalpathi Subramanian
 * @date 12/23/18
 *
 */
public	class Rectangle extends  Symbol {

	// height, width of rectangle
	private float width = 1.0f, height = 1.0f;

	/**
	 *  Construct a default rectangle
	 */
	public Rectangle() {
		super();
		setRectangle (0.0f, 0.0f, 1.0f, 1.0f);
	}

	/**
	 *  Construct a rectangle with given height and width
	 *  @param w  width of rectangle
	 *  @param h  height of rectangle
	 */
	public Rectangle (float w, float h) {
		super();
		setRectangle (0.0f, 0.0f, w, h);
	}

	/**
	 *  Construct a rectangle with given its location (center),  height and width
	 *  @param locx x coordinate of center
	 *  @param locy y coordinate of center
	 *  @param w  width of rectangle
	 *  @param h  height of rectangle
	 */
	public Rectangle (float locx, float locy, float w, float h) {
		super();
		setRectangle (locx, locy, w, h);
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
	public void setWidth(float w) {
		if (w <= 0.0f) {
			throw new IllegalArgumentException ("Width needs to be positive");
		}
		width = w;
	}

	/**
	 * This method sets the shape height
	 *
	 * @param h  height
	 */
	void setHeight(float h) {
		if (h <= 0.0f) {
			throw new IllegalArgumentException ("Height needs to be positive");
		}
		height = h;
	}


	/**
	 * This method returns the dimensions of the shape: min and max
	 *	values in X and Y
	 *
	 * @return rectangle's bounding box (array of 4 values)
	 */
	public float[] getDimensions() {

		float[] dims = new float[4];
		float[] location = getLocation();

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
	 */
	public void setRectangle(float locx, float locy, float w, float h) 	{

		setLocation (locx, locy);
		setWidth(w);
		setHeight(h);
		
		setShapeType("rect");
	}

	/**
	*  Translate the rectangle
	*
	*  @param tx,ty translation vector
	*/
	public void translate(float tx, float ty) {
		float[] center = getLocation();
		translatePoint (center, tx, ty);
		setLocation(center[0], center[1]);
	}
	/**
	 *  Scale the rectangle about its center
	 *
	 *  @param sx,sy scale factor along each axis
	 */
	public void scale(float sx, float sy) {
		// scale the height, width
		// center remains the same
		float[] pt =  new float[2];
		pt[0]  = width;
		pt[1] = height;
		scalePoint (pt, sx, sy);
		width = pt[0];
		height = pt[1];
	}

	/**
	 * This method returns the JSON representation of the shape
	 *
	 * @return JSON representation of rectangle (string)
	 */
	public JSONObject getJSONRepresentation() {

		String shape = getShapeType();

		// get the JSON of the attributes of the shape
		JSONObject shape_json = super.getJSONRepresentation();

		shape_json.put ("name", getLabel());
		shape_json.put ("shape", shape);
		shape_json.put ("width", width);
		shape_json.put ("height", height);

		return shape_json;
	}
};
