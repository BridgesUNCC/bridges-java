
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
 * @author David Burlinson, Kalpathi Subramanian
 * @date 12/23/18, 7/15/19
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
		setShapeType("polyline");
	}

	/**
	 *	 Construct a polyline with the give set of points
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
	public String getName() {
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
		setShapeType("polyline");
	}

	/**
	 * This method returns the dimensions of the shape: min and max
	 *	values in X and Y
	 *
	 * @return Bounding box of the point list (array of 4 values)
	 */
	public float[] getDimensions() {
		float minx = Float.POSITIVE_INFINITY;
		float miny = Float.POSITIVE_INFINITY;
		float maxx = Float.NEGATIVE_INFINITY;
		float maxy = Float.NEGATIVE_INFINITY;
		float x = 0.0f;
		float y = 0.0f;
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
		return new float[] {minx, maxx, miny, maxy};
	}

	/**
	 *  Translate the polyline
	 *
	 *  @param tx, ty translation vector
	 */
	void translate(float tx, float ty) {
		// translate the points
		for (int k = 0; k < points.size(); k += 2) {
			points.set(k, points.get(k) + tx);
			points.set(k + 1, points.get(k + 1) + ty);
		}
	}

	/**
	 *  @brief Rotate the polyline about its center
	 *
	 *  @param angle rotation angle in degree
	 */
	void rotate(float angle) {
		// get center of polyline
		float[]  center = new float[2];
		getCenter(center);
		// translate the center to the origin
		float[] transl = new float[2];
		transl[0] = -center[0];
		transl[1] = -center[1];
		translate (transl[0], transl[1]);
		// rotate the points
		for (int k = 0; k < points.size(); k += 2) {
			float[] tmp = new float[2];
			tmp[0] = points.get(k);
			tmp[1] = points.get(k + 1);
			rotatePoint (tmp,  angle);
			points.set(k, tmp[0]);
			points.set(k + 1, tmp[1]);
		}
		// translate back
		transl[0] = center[0];
		transl[1] = center[1];
		translate (transl[0], transl[1]);
	}

	/**
	 *  @brief Scale the polyline about its center.
	 *
	 * That is to say, the center of the polyline will not change
	 * location, but the object itself will grow bigger.
	 *
	 *  @param sx, sy scale factor 
	 */
	void scale(float sx, float sy) {
		// get center of polyline
		float[] center = new float[2];
		getCenter(center);
		// translate the center to the origin
		float[] transl = new float[2];
		transl[0] = -center[0];
		transl[1] = -center[1];
		translate (transl[0], transl[1]);
		// scale the points
		for (int k = 0; k < points.size(); k += 2) {
			points.set(k,  points.get(k) * sx);
			points.set(k + 1, points.get(k + 1) * sy);
		}
		// translate back
		transl[0] = center[0];
		transl[1] = center[1];
		translate(transl[0], transl[1]);
	}
    
	/**
	 * @brief Get center of polyline - use its bounding box
	 *
	 * @param[out] center center of the polyline
	 */
	void getCenter(float[] center) {
		float[]  bbox = new float[4];
		bbox[0] = bbox[1] = 100000.0f;
		bbox[2] = bbox[3] = -10000.0f;
		for (int k = 0; k < points.size(); k += 2) {
			if (points.get(k) < bbox[0])
				bbox[0] = points.get(k);
			if (points.get(k) > bbox[2])
				bbox[2] = points.get(k);
			if (points.get(k + 1) < bbox[1])
				bbox[1] = points.get(k + 1);
			if (points.get(k + 1) > bbox[3])
				bbox[3] = points.get(k + 1);
		}
		center[0] = bbox[0] + (bbox[2] - bbox[0]) / 2.0f;
		center[1] = bbox[1] + (bbox[3] - bbox[1]) / 2.0f;
	}



	/**
	 * This method returns the JSON representation of the shape
	 *
	 * @return string  JSON string
	 */
	public JSONObject getJSONRepresentation() {

		String shape = getShapeType();

		JSONObject  shape_json = super.getJSONRepresentation();

		shape_json.put ("name", getLabel());
		shape_json.put ("shape", shape);
		shape_json.put ("points", points);

		return shape_json;
	}
};

