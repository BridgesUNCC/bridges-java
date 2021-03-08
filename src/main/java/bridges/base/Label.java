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
	static final Float DEFAULT_FONTSIZE = 12.0f;

	private Integer width = 100;
	private Integer height = 50;
	private Float fontSize = DEFAULT_FONTSIZE;
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

	public Label setFontSize(Float size) {
		if (size <= 0. || size > 200.) {
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
					length +=  0.70f;
				else if (ch == 'I')
					length += 0.50f;	
				else length += 0.60f;
			}
			else // support only spaces
				length += 0.50f;
		}
		length *= fontSize;


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

		// deal first with vertical text - this is simple
		float loc_x = (float) this.getLocation()[0];
		float loc_y = (float) this.getLocation()[1];
		float[] bbox = new float[4];
		if (rotation_angle == 90.0f || rotation_angle == -90.0f) {
			bbox_width = height;
			bbox_height = length;
			bbox[0] =  loc_x - bbox_width/2.0f;
			bbox[1] =  loc_y - bbox_height/2.0f;
			bbox[2] =  loc_x + bbox_width/2.0f;
			bbox[3] =  loc_y + bbox_height/2.0f;
		}
		else  { 		
			// deal with any arbitrary angle, rotat the bounding box by this
			// angle, then recomput the bounding box
			float[] pt = new float[2];

			bbox[0] = bbox[1] = Float.MAX_VALUE;
			bbox[2] = bbox[3] = -Float.MAX_VALUE;

			// rotate  the four corners of the bounding box
			for (int k = 0; k < 4; k++) {
				switch (k) {
					case 0:     // lower left at (0,0)
						pt[0] = pt[1] = 0.0f;
						break;
					case 1:     // upper left
						pt[0] = 0.0f; pt[1] = bbox_height;
						break;
					case 2:     // lower right
						pt[0] = bbox_width; pt[1] = 0.0f;
						break;
					case 3:     // upper right
						pt[0] = bbox_width; pt[1] = bbox_height;
						break;
					}
				// rotate the point by the angle
				rotatePoint (pt, rotation_angle);

				// update bounding box
				if (pt[0] < bbox[0])  bbox[0] = pt[0];
				if (pt[1] < bbox[1])  bbox[1] = pt[1];
				if (pt[0] > bbox[2])  bbox[2] = pt[0];
				if (pt[1] > bbox[3])  bbox[3] = pt[1];
			}

			// translate center of box to center of label
			float tx = loc_x - (bbox[0] + (bbox[2]-bbox[0])/2.0f);
			float ty = loc_y - (bbox[1] + (bbox[3]-bbox[1])/2.0f);
			bbox[0] += tx; bbox[2] += tx;
			bbox[1] += ty; bbox[3] += ty;
		}
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
