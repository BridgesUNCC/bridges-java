package bridges.base;
import bridges.base.DataStruct;

import java.util.ArrayList;
import java.lang.Character;

import bridges.base.Color;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;


/**
 * @brief This class used to render text as part of a SymbolCollection.
 *
 *		Text have a text string, font size, anchor location, and anchoring mode.
 *
 * Basic styling such as stroke, color are defined in the superclass Symbol.
 *
 * On a label the "stroke" refer to the outside of the of the letter, and the "fill" refers to the inside of the letters. In most case, you want no stroke but a fill. 
 *
 * Text is placed by defining an anchor position and what that
 * position is for the text (is it the middle? is it the top
 * left?). Check the tutorial for details.
 *
 * @sa An example tutorial can be found at 
 * 		http://bridgesuncc.github.io/tutorials/Symbol_Collection.html
 *
 * @author David Burlinson, Erik Saule
 * @date 2018, 7/15/19, 06/22/21
 */
public class Text extends Symbol {
    private Float fontSize = null;
    private String anchorAlignmentLR = null;
    private String anchorAlignmentTB = null;
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

    /**
     * @param typeLR valid parameters are "left", "middle", and "right"
     * @param typeTB valid parameters are "top", "bottom", "embottom", "emtop", "middle"
     **/
    public Symbol setAnchorAlignment(String typeLR, String typeTB) {
	anchorAlignmentLR = typeLR;
	anchorAlignmentTB = typeTB;
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

		if (anchorAlignmentLR != null)
		    json_builder.put("anchor-alignmentLR", anchorAlignmentLR);

		if (anchorAlignmentTB != null)
		    json_builder.put("anchor-alignmentTB", anchorAlignmentTB);

		
		return json_builder;
	}
}
