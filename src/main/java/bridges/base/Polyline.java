
package bridges.base;

import bridges.base.Symbol;
import org.json.simple.JSONObject;
import java.util.ArrayList;

/**
 * @brief This class defines a polyline and is part of the symbol collection.
 *		A polyline has a sequence of points (x, y coordinate pairs)
 *
 * @author David Burlinson, Kalpathi Subramanian
 * @date 12/23/18, 7/15/19
 *
 */
public class Polyline extends Symbol {

	private String shape = "polyline";
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
	 */
	public Polyline (ArrayList<Float>  pts) {
		this();
		points = pts;
	}

	/**
	 *	This method gets the name of the shape
	 *
	 *  @return name   shape name
	 */
	public String getName() {
		return "polyline";
	}

	/**
	 * This method adds a point to the polyline
	 *
	 * @param x, y  Coordinates of the point
	 */
	public void addPoint(Integer x, Integer y) {

		Float fx = (float) x;
		Float fy = (float) y;

		if 	((fx > Float.NEGATIVE_INFINITY) && (fx < Float.POSITIVE_INFINITY) &&
			(fy > Float.NEGATIVE_INFINITY) && (fy < Float.POSITIVE_INFINITY) ) {
			this.points.add (fx);
			this.points.add (fy);
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
	 * This method returns the dimensions of the shape: min and max
	 *	values in X and Y
	 *
	 * @return Bounding box of the point list (array of 4 values)
	 */
	public Float[] getDimensions() {
		Float minx = Float.POSITIVE_INFINITY;
		Float miny = Float.POSITIVE_INFINITY;
		Float maxx = Float.NEGATIVE_INFINITY;
		Float maxy = Float.NEGATIVE_INFINITY;
		Float x = 0.0f;
		Float y = 0.0f;
		for (int i = 0; i < this.points.size(); i += 2) {
			x = this.points.get(i);
			y = this.points.get(i + 1);
			if (x < minx)
				minx = x;
			if (x > maxx)
				maxx = x;
			if (y < miny)
				miny = y;
			if (y > maxy)
				maxy = y;
		}
		return new Float[] {minx, maxx, miny, maxy};
	}

	/**
	 * This method returns the JSON representation of the shape
	 *
	 * @return string  JSON string
	 */
	public JSONObject getJSONRepresentation() {

		JSONObject  shape_json = super.getJSONRepresentation();

		shape_json.put ("name", getLabel());
		shape_json.put ("shape", shape);

		shape_json.put ("points", points);

		return shape_json;
	}
};

