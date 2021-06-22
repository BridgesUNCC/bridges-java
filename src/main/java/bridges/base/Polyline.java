
package bridges.base;

import bridges.base.Symbol;
import org.json.simple.JSONObject;
import java.util.ArrayList;

/**
 * @brief This class defines a polyline and is part of the symbol collection.
 *		A polyline has a sequence of points (x, y coordinate pairs)
 *
 * Basic styling such as stroke and fill are defined in the superclass Symbol.
 *
 * @sa An example tutorial can be found at
 * 		http://bridgesuncc.github.io/tutorials/Symbol_Collection.html
 *
 * @author David Burlinson, Kalpathi Subramanian, Erik Saule
 * @date 12/23/18, 7/15/19, 6/22/21
 *
 */
public class Polyline extends Symbol {

	// height, width of rectangle
	private ArrayList<Float> points = null;

	/**
	 *	 Construct a default polyline structure
	 */
	public Polyline () {
		super();
		points = new ArrayList<Float>();
	}

	/**
	 *	 Construct a polyline with the give set of points
	 *
	 *   @param pts  the given set of input 2D points
	 */
	public Polyline (ArrayList<Float>  pts) {
		this();
		setPolyline(pts);
	}

	/**
	 *	This method gets the name of the shape
	 *
	 *  @return shape name
	 */
	public String getShapeType() {
		return "polyline";
	}

	/**
	 * This method adds a point to the polyline
	 *
	 * @param x, y  Coordinates of the point
	 */
	public void addPoint(float x, float y) {

		if 	((x > Float.NEGATIVE_INFINITY) && (x < Float.POSITIVE_INFINITY) &&
			(y > Float.NEGATIVE_INFINITY) && (y < Float.POSITIVE_INFINITY) ) {
			this.points.add (x);
			this.points.add (y);
		}
	}
	/**
	 * This method returns the point list of the polyline
	 *
	 * @return points  point list of the polyline - sequence of x, y values
	 */
	public ArrayList<Float> getPoints() {
		return points;
	}

	public void setPolyline (ArrayList<Float> pts) {
		points = pts;
	}


	/**
	 * This method returns the JSON representation of the shape
	 *
	 * @return string  JSON string
	 */
	public JSONObject getJSONRepresentation() {

		String shape = getShapeType();

		JSONObject  shape_json = super.getJSONRepresentation();

		shape_json.put ("points", points);

		return shape_json;
	}
};

