package bridges.base;
import bridges.base.DataStruct;

import java.util.ArrayList;
import java.lang.Character;

import bridges.base.Color;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;


/**
 * @brief This class used to label symbols.
 *		Labels have  a text string, font size, width, height and location
 *
 * Basic styling such as stroke, color are defined in the superclass Symbol.
 *
 *
 * On a label the "stroke" refer to the outside of the of the letter, and the "fill" refers to the inside of the letters. In most case, you want no stroke but a fill. 
 *
 *
 * @sa An example tutorial can be found at 
 * 		http://bridgesuncc.github.io/tutorials/Symbol_Collection.html
 *
 * @author David Burlinson
 * @date 2018, 7/15/19
 */
public class Label extends Symbol {
	static final Integer DEFAULT_FONTSIZE = 12;

	private Integer width = 100;
	private Integer height = 50;
	private Integer fontSize = DEFAULT_FONTSIZE;
	private float rotation_angle = 0.0f;


	/**
	 *	Construct a default label
	 */
	public Label() {
		super();
		setStrokeWidth(0.0f);
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
	 * @brief Set the rotation angle for the label
	 *
	 * Permits rotated text labels (only horiz and vertical
	 *  supported now.
	 *
	 * @param angle  rotation angle in dedgrees
	 *
	 */
	public void setRotationAngle (float angle) {
		rotation_angle = angle;
	}

	/**
	 * @brief Get the rotation angle for the label
	 *
	 *
	 * @return angle  rotation angle in degrees
	 *
	 */
	 float getRotationAngle () {
	 	return rotation_angle;
	 }

	/**
	 * @brief This method returns the bounding box dimensions of
	 *  the shape
	 *
	 *  A more accurate computation, takes into account
	 *  the label string content
	 *
	 *  @return bounding box of the label (min x, min y, max x, max y)
	 *
	 * @return vector of floats
	 */

	public float[] getBoundingBox() {

		// first get the width of the string by parsing it
		String str = getLabel();
		float length = 0.0f;
		Boolean upper_case_exists = false;
		for (char ch: str.toCharArray()) {
			if (Character.isLowerCase(ch)) {
				if (ch == 'm' || ch == 'w')
					length +=  0.6f;
				else if (ch == 'i' || ch == 'l' || ch == 'j')
					length +=  0.4f;
				else length += 0.5f;
			}
			else if (Character.isUpperCase(ch)) {
				upper_case_exists = true;
				if (ch == 'M' || ch == 'W') 
					length +=  0.72f;
				else if (ch == 'I')
					length += 0.52f;	
				else length += 0.62f;
			}
			else // support only spaces
				length += 0.55;
		}
		length *= fontSize;

		float loc_x = this.getLocation()[0];
		float loc_y = this.getLocation()[1];

		float width = length;
		float height = 0.0f;
		if (upper_case_exists) {
			height = fontSize + 0.3f*fontSize;
			}
		else 
			height = fontSize + 0.1f*fontSize;

		// account for text orientation to compute an 
		// axis aligned bounding box

		float bbox_width = length;
		float bbox_height = height;
		if (rotation_angle == 90.0f || rotation_angle == -90.0f) {
			bbox_width = height;
			bbox_height = length;
		}
		else if (rotation_angle == -45.0f || rotation_angle == 45.0f) {
			bbox_width  = length/(float) Math.sqrt(2.0);
			bbox_height = length/(float) Math.sqrt(2.0);
		}
			

		// order is xmin, ymin, xmax, ymax
		float[] bbox = new float[4];
		bbox[0] = loc_x - bbox_width/2.0f;
		bbox[1] = loc_y - bbox_height/2.0f;
		bbox[2] = loc_x + bbox_width/2.0f;
		bbox[3] = loc_y + bbox_height/2.0f;

		return bbox;
	}

	/**
	 *  Get the dimensions of the label object
	 *  @return bounding box of the label (min x, min y, max x, max y)
	 */
	public float[] getDimensions() {
		return getBoundingBox();
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

		json_builder.put("font-size", fontSize);
		json_builder.put("angle", rotation_angle);

		return json_builder;
	}
}
