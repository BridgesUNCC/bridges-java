
package bridges.base;

import bridges.base.Symbol;
import org.json.simple.JSONObject;
import java.util.ArrayList;

/**
 * @brief This class defines a polygon and can be used as a part of a SymbolCollection.
 *
 *		A polygon has a sequence of 2D points (x, y coordinate pairs)
 *
 * Basic styling such as stroke, fill, color are defined in the superclass Symbol.
 *
 * @sa An example tutorial can be found at
 * 		https://bridgesuncc.github.io/tutorials/Symbol_Collection.html
 *
 * @author David Burlinson, Kalpathi Subramanian, Erik Saule
 * @date 12/23/18, 7/15/19, 6/22/21
 *
 */
public class Polygon extends Polyline {

	/**
	 *	 Construct a default polygon structure
	 */
	public Polygon () {
		super();
	}

	/**
	 *	 Construct a polygon with the give set of points
	 *   @param pts the array of 2D points
	 */
	public Polygon (ArrayList<Float>  pts) {
		super(pts);
	}


	/**
	 *	This method gets the name of the shape
	 *
	 *  @return name   shape name
	 */
	public String getShapeType() {
		return "polygon";
	}

};

