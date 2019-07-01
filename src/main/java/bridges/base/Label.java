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
	static final Integer DEFAULT_FONTSIZE = 12;

	private Integer width = 100;
	private Integer height = 50;
	private Integer fontSize = DEFAULT_FONTSIZE;


	public Label() {
		super();
	}

	public Label(String label) {
		this();
		this.setLabel(label);
	}

	// size method for width and height arguments
	// public Label setSize(Integer width, Integer height) {
	//   if((width <= 0 || width > 100) || (height <= 0 || height > 100)) {
	//     throw new IllegalArgumentException("Please enter dimensions between 0 and 100");
	//   } else {
	//     this.width = width;
	//     this.height = height;
	//   }
	//   return this;
	// }

	public Label setFontSize(Integer size) {
		if (size <= 0 || size > 200) {
			throw new IllegalArgumentException("Please use font size between 0 and 200");
		}
		else {
			fontSize = size;
		}
		return this;
	}

	// return four points: min x value, max x value, min y value, max y value
	public Float[] getDimensions() {
		float length = (float) 0.09 * this.fontSize * this.getLabel().length();
		float x = this.getLocation()[0];
		float y = this.getLocation()[1];

		return new Float[] {(float) x - length / 2, (float) x + length / 2, (float) y, (float) y};

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
		json_builder.put("shape", "text");

		if (fontSize != DEFAULT_FONTSIZE) {
			json_builder.put("font-size", fontSize);
		}

		return json_builder;
	}
}
