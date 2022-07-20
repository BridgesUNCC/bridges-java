
package bridges.base;

import java.util.ArrayList;
import bridges.base.Symbol;
import org.json.simple.JSONObject;

/**
 * @brief This class defines a rectangle and is part of the bridges::base::SymbolCollection.
 *
 *		A rectangle has height and width, and a location (rectangle center)
 *
 * Basic styling such as stroke, fill, color are defined in the superclass Symbol.
 *
 * @sa An example tutorial can be found at
 * 		https://bridgesuncc.github.io/tutorials/Symbol_Collection.html
 * @author Kalpathi Subramanian, Erik Saule
 * @date 12/23/18, 6/22/21
 *
 */
public	class Rectangle extends  Symbol {

	// height, width of rectangle
	private float width = 1.0f, height = 1.0f;
	private float locx = 0.0f, locy = 0.0f;

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
	public String getShapeType()  {
		return "rect";
	}

	/**
	 * This method sets the shape width
	 *
	 * @param w  width
	 */
	public void setWidth(float w) {
		if (w < 0.0f) {
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
		if (h < 0.0f) {
			throw new IllegalArgumentException ("Height needs to be positive");
		}
		height = h;
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
		this.locx = locx;
		this.locy = locy;

		setWidth(w);
		setHeight(h);
	}

	/**
	 * This method returns the JSON representation of the shape
	 *
	 * @return JSON representation of rectangle (string)
	 */
	public JSONObject getJSONRepresentation() {
		// get the JSON of the attributes of the shape
		JSONObject shape_json = super.getJSONRepresentation();

		ArrayList<Float> loc = new ArrayList<Float>();
		loc.add(locx);
		loc.add(locy);

		shape_json.put ("lowerleftcorner", loc);
		shape_json.put ("width", width);
		shape_json.put ("height", height);

		return shape_json;
	}
};
