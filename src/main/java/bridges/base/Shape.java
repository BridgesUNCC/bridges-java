package bridges.base;
import bridges.base.DataStruct;

import java.util.ArrayList;
import bridges.base.Color;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;

/*
 * @brief This is a class BRIDGES for deriving a
 *  number of Shape objects for use in a ShapeCollection.
 *  Shapes correspond to a simplified subset of SVG paths
 *  and shapes for custom visual representations in BRIDGES.
 *
 * @author David Burlinson
 *
*/
public class Shape extends Symbol {

  static final String DEFAULT_SHAPE = "circle";

  private String shape = "circle";

  // css attributes and defaults
  private Integer size = 5;

  private ArrayList<Float> points = null;
  private Integer radius = size;
  private Integer width = 0;
  private Integer height = 0;

  public String getDataStructType() {
		return "Shape";
	}

  public Shape() {
    super();
  }

  public Shape(String shape) {
    this();
    this.setShape(shape);
  }
  //
  // /**
	//  * This method sets the label
	//  *
	//  * @param label the label to set
	//  */
	// public void setLabel(String label) {
	// 	this.label = label;
	// }

  /**
   * This method sets the Shape
   *
   * @param Shape the Shape to draw
   */
  public void setShape(String shape) {
    switch(shape.toLowerCase()) {
      case "circle":
        this.shape = "circle";
        this.radius = this.size;
        break;
      case "rectangle":
        this.shape = "rect";
        this.width = this.size;
        this.height = this.size;
        break;
      case "polygon":
        this.shape = "polygon";
        this.points = new ArrayList<Float>();
        break;
      default:
        throw new IllegalArgumentException(shape + " is not a valid Bridges shape - try circle, rectangle, or polygon.");
    }
  }

  // size method for radius or square rectangle argument
  public Shape setSize(Integer size) {
    if(size <= 0 || size > 50) {
      throw new IllegalArgumentException("Please enter a size value between 0 and 50");
    } else {
      switch(this.shape) {
        case "circle":
          this.radius = size;
          break;
        case "rect":
          this.width = size;
          this.height = size;
          break;
      }
    }
    return this;
  }

  // size method for width and height arguments
  public Shape setSize(Integer width, Integer height) {
    if(size <= 0 || size > 50) {
      throw new IllegalArgumentException("Please enter a size value between 0 and 50");
    } else {
      switch(this.shape) {
        case "rect":
          this.width = width;
          this.height = height;
          break;
      }
    }
    return this;
  }

  public Shape addPoint(Integer x, Integer y) {
    return addPoint((float) x, (float) y);
  }
  public Shape addPoint(Float x, Float y) {
    if(this.shape.compareTo("polygon") == 0) {
      if((x > Float.NEGATIVE_INFINITY && x < Float.POSITIVE_INFINITY) &&
        (y > Float.NEGATIVE_INFINITY && y < Float.POSITIVE_INFINITY))
        {
          this.points.add(x);
          this.points.add(y);
        }
    } else {
      throw new IllegalArgumentException("You may only add points to a polygon shape");
    }
    return this;
  }
  public ArrayList<Float> getPoints() {
    if(this.shape.compareTo("polygon") != 0) {
      throw new IllegalArgumentException("You may only get points from a polygon shape");
    }
    return this.points;
  }

  /**
	 * Internal code for getting the properties of the Shape object.
	 * It produces (without the spaces or newlines):
	 * {
	 *  "name": "Some label",
	 *  "other CSS properties like color": any_JSON_value
	 * }
	 * @returns the encoded JSON string
	 */
  public JSONObject getJSONRepresentation() {
  JSONObject json_builder = super.getJSONRepresentation();

    json_builder.put("name", JSONValue.escape(super.label));
    json_builder.put("shape", shape);

    if(shape.compareTo("circle") == 0) {
        json_builder.put("r", radius);
      }

    // set up width and height of rectangles
    if(shape.compareTo("rect") == 0) {
        json_builder.put("width", width);
        json_builder.put("height", height);
    }

    // add point list to polygons
    if(shape.compareTo("polygon") == 0) {
      json_builder.put("points", points);
    }

    return json_builder;
  }
}
