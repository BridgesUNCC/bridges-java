package bridges.base;
import bridges.base.DataStruct;

import java.util.ArrayList;
import bridges.base.Color;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;

/*
 * @brief This is a superclass in BRIDGES for deriving a
 *  number of Shape objects for use in a ShapeCollection.
 *  Shapes correspond to a simplified subset of SVG paths
 *  and shapes for custom visual representations in BRIDGES.
 *
 * @author David Burlinson
 *
*/
public class Label extends Symbol {

  private Integer width = 0;
  private Integer height = 0;

  public String getDataStructType() {
		return "Label";
	}

  public Label() {
    super();
  }

  public Label(String label) {
    this();
    this.setLabel(label);
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

  // size method for width and height arguments
  public Label setSize(Integer width, Integer height) {
    if((width <= 0 || width > 100) || (height <= 0 || height > 100)) {
      throw new IllegalArgumentException("Please enter dimensions between 0 and 100");
    } else {
      this.width = width;
      this.height = height;
    }
    return this;
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
  public String getElementRepresentation() {
    JSONObject json_builder = super.getJSONRepresentation();

    json_builder.put("name", JSONValue.escape(super.label));
    json_builder.put("shape", "text");
  
    return json_builder.toString();
  }
}
