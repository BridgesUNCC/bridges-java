package bridges.base;
import bridges.base.DataStruct;

import java.util.ArrayList;
import bridges.base.Color;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;

/**
 * @brief This class used to label symbols.
 *		Labels have  a text string, font size, width, height and location
 *
 * Basic styling such as stroke and fill are defined in the superclass Symbol.
 *
 * @author David Burlinson
 * @date 2018, 7/15/19
 */
public class Label extends Symbol {
	static final Integer DEFAULT_FONTSIZE = 12;

	private Integer width = 100;
	private Integer height = 50;
	private Integer fontSize = DEFAULT_FONTSIZE;


	/**
	 *	Construct a default label
	 */
	public Label() {
		super();
	}

	/**
	 *	Construct a label with the give text string
	 *  @param label the text of the label
	 */
	public Label(String label) {
		this();
		this.setLabel(label);
	}

	public Label setFontSize(Integer size) {
		if (size <= 0 || size > 200) {
			throw new IllegalArgumentException("Please use font size between 0 and 200");
		}
		else {
			fontSize = size;
		}
		return this;
	}

	/**
	 *  Get the dimensions of the label object
	 *  @return bounding box of the label (min x, max x, min y, max y)
	 */
	public float[] getDimensions() {
		float length = (float) 0.09 * this.fontSize * this.getLabel().length();
		float x = this.getLocation()[0];
		float y = this.getLocation()[1];

		return new float[] {(float) x - length / 2, (float) x + length / 2, (float) y, (float) y};

	}

	/**
	 * Get the JSON representation of the label object
	 *
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
