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
 * @author David Burlinson, Erik Saule
 * @date 2018, 7/15/19, 06/22/21
 */
public class Text extends Symbol {
    private Float fontSize = null;
    private String anchorType = null;
    private String text = "";
    private float locx = 0.f;
    private float locy = 0.f;
    
	/**
	 *	Construct a default label
	 */
	public Text() {
		super();
		setStrokeWidth(0.0f);
	}

	/**
	 *	Construct a label with the give text string
	 *  @param label the text of the label
	 */
	public Text(String label) {
		this();
		this.setText(label);
	}

    	public String getShapeType() {
		return "text";
	}

    
    public Symbol setText(String t) {
	this.text = t;
	return this;
    }
    
	public Text setFontSize(float size) {
		if (size < 0.) {
			throw new IllegalArgumentException("Please use font size greater tan 0 ");
		}
		else {
			fontSize = size;
		}
		return this;
	}


    public Symbol setAnchorLocation(float x, float y) {
	this.locx = x;
	this.locy = y;
	return this;
    }

    public Symbol setAnchorType(String type) {
	this.anchorType = type;
	return this;
    }
    
	/**
	 * Get the JSON representation of the label object
	 *
	 * @returns the encoded JSON string
	 */
	public JSONObject getJSONRepresentation() {
		JSONObject json_builder = super.getJSONRepresentation();

		json_builder.put("text", JSONValue.escape(super.label));

		ArrayList<Float> loc = new ArrayList<Float>();
		loc.add(locx);
		loc.add(locy);
		
		json_builder.put("anchor-location", loc);
		json_builder.put("text", text);
		
		if (fontSize != null)
		    json_builder.put("font-size", fontSize);

		if (anchorType != null)
		    json_builder.put("anchor-type", anchorType);

		return json_builder;
	}
}
