package bridges.base;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.nio.ByteBuffer;
import bridges.validation.InvalidValueException;


/**
 *	@brief This class is used to represent colors in BRIDGES.
 *
 *	We use an RGBA model
 *	to represent colors, with each component in the range 0-255. BRIDGES
 *	also supports named colors for user convenience, but these are converted
 *	into [RGBA] prior to transmission to the server for visualization.
 *
 * 	<p>

 * Supported named colors are listed here: https://drafts.csswg.org/css-color-3/#svg-color
 *
 * All named colors have are fully opaque by default.
 *
 * Default Color is opaque white
 *
 * @date 7/8/19, Kalpathi Subramanian 
 *
 *
 *	@author K.R. Subramanian,
 *	@date 7/8/19
 *
 */
public class Color {

	private int red, green, blue;
	private float alpha; // alpha represents opacity from 0.0-1.0
	private static final Map<String, String> colorNames;
	static {
		Map<String, String> tempMap = new HashMap<>();
		tempMap.put("aliceblue", "#f0f8ff");
		tempMap.put("antiquewhite", "#faebd7");
		tempMap.put("cyan", "#00ffff");
		tempMap.put("aquamarine", "#7fffd4");
		tempMap.put("azure", "#f0ffff");
		tempMap.put("beige", "#f5f5dc");
		tempMap.put("bisque", "#ffe4c4");
		tempMap.put("black", "#000000");
		tempMap.put("blanchedalmond", "#ffebcd");
		tempMap.put("blue", "#0000ff");
		tempMap.put("blueviolet", "#8a2be2");
		tempMap.put("brown", "#a52a2a");
		tempMap.put("burlywood", "#deb887");
		tempMap.put("cadetblue", "#5f9ea0");
		tempMap.put("chartreuse", "#7fff00");
		tempMap.put("chocolate", "#d2691e");
		tempMap.put("coral", "#ff7f50");
		tempMap.put("cornflowerblue", "#6495ed");
		tempMap.put("cornsilk", "#fff8dc");
		tempMap.put("crimson", "#dc143c");
		tempMap.put("darkblue", "#00008b");
		tempMap.put("darkcyan", "#008b8b");
		tempMap.put("darkgoldenrod", "#b8860b");
		tempMap.put("darkgrey", "#a9a9a9");
		tempMap.put("darkgray", "#a9a9a9");
		tempMap.put("darkgreen", "#006400");
		tempMap.put("darkkhaki", "#bdb76b");
		tempMap.put("darkmagenta", "#8b008b");
		tempMap.put("darkolivegreen", "#556b2f");
		tempMap.put("darkorange", "#ff8c00");
		tempMap.put("darkorchid", "#9932cc");
		tempMap.put("darkred", "#8b0000");
		tempMap.put("darksalmon", "#e9967a");
		tempMap.put("darkseagreen", "#8fbc8f");
		tempMap.put("darkslateblue", "#483d8b");
		tempMap.put("darkslategrey", "#2f4f4f");
		tempMap.put("darkslategray", "#2f4f4f");
		tempMap.put("darkturquoise", "#00ced1");
		tempMap.put("darkviolet", "#9400d3");
		tempMap.put("deeppink", "#ff1493");
		tempMap.put("deepskyblue", "#00bfff");
		tempMap.put("dimgrey", "#696969");
		tempMap.put("dimgray", "#696969");
		tempMap.put("dodgerblue", "#1e90ff");
		tempMap.put("firebrick", "#b22222");
		tempMap.put("floralwhite", "#fffaf0");
		tempMap.put("forestgreen", "#228b22");
		tempMap.put("magenta", "#ff00ff");
		tempMap.put("gainsboro", "#dcdcdc");
		tempMap.put("ghostwhite", "#f8f8ff");
		tempMap.put("gold", "#ffd700");
		tempMap.put("goldenrod", "#daa520");
		tempMap.put("grey", "#808080");
		tempMap.put("gray", "#808080");
		tempMap.put("green", "#008000");
		tempMap.put("greenyellow", "#adff2f");
		tempMap.put("honeydew", "#f0fff0");
		tempMap.put("hotpink", "#ff69b4");
		tempMap.put("indianred", "#cd5c5c");
		tempMap.put("indigo", "#4b0082");
		tempMap.put("ivory", "#fffff0");
		tempMap.put("khaki", "#f0e68c");
		tempMap.put("lavender", "#e6e6fa");
		tempMap.put("lavenderblush", "#fff0f5");
		tempMap.put("lawngreen", "#7cfc00");
		tempMap.put("lemonchiffon", "#fffacd");
		tempMap.put("lightblue", "#add8e6");
		tempMap.put("lightcoral", "#f08080");
		tempMap.put("lightcyan", "#e0ffff");
		tempMap.put("lightgoldenrodyellow", "#fafad2");
		tempMap.put("lightgrey", "#d3d3d3");
		tempMap.put("lightgray", "#d3d3d3");
		tempMap.put("lightgreen", "#90ee90");
		tempMap.put("lightpink", "#ffb6c1");
		tempMap.put("lightsalmon", "#ffa07a");
		tempMap.put("lightseagreen", "#20b2aa");
		tempMap.put("lightskyblue", "#87cefa");
		tempMap.put("lightslategrey", "#778899");
		tempMap.put("lightsteelblue", "#b0c4de");
		tempMap.put("lightyellow", "#ffffe0");
		tempMap.put("lime", "#00ff00");
		tempMap.put("limegreen", "#32cd32");
		tempMap.put("linen", "#faf0e6");
		tempMap.put("maroon", "#800000");
		tempMap.put("mediumaquamarine", "#66cdaa");
		tempMap.put("mediumblue", "#0000cd");
		tempMap.put("mediumorchid", "#ba55d3");
		tempMap.put("mediumpurple", "#9370db");
		tempMap.put("mediumseagreen", "#3cb371");
		tempMap.put("mediumslateblue", "#7b68ee");
		tempMap.put("mediumspringgreen", "#00fa9a");
		tempMap.put("mediumturquoise", "#48d1cc");
		tempMap.put("mediumvioletred", "#c71585");
		tempMap.put("midnightblue", "#191970");
		tempMap.put("mintcream", "#f5fffa");
		tempMap.put("mistyrose", "#ffe4e1");
		tempMap.put("moccasin", "#ffe4b5");
		tempMap.put("navajowhite", "#ffdead");
		tempMap.put("navy", "#000080");
		tempMap.put("oldlace", "#fdf5e6");
		tempMap.put("olive", "#808000");
		tempMap.put("olivedrab", "#6b8e23");
		tempMap.put("orange", "#ffa500");
		tempMap.put("orangered", "#ff4500");
		tempMap.put("orchid", "#da70d6");
		tempMap.put("palegoldenrod", "#eee8aa");
		tempMap.put("palegreen", "#98fb98");
		tempMap.put("paleturquoise", "#afeeee");
		tempMap.put("palevioletred", "#db7093");
		tempMap.put("papayawhip", "#ffefd5");
		tempMap.put("peachpuff", "#ffdab9");
		tempMap.put("peru", "#cd853f");
		tempMap.put("pink", "#ffc0cb");
		tempMap.put("plum", "#dda0dd");
		tempMap.put("powderblue", "#b0e0e6");
		tempMap.put("purple", "#800080");
		tempMap.put("red", "#ff0000");
		tempMap.put("rosybrown", "#bc8f8f");
		tempMap.put("royalblue", "#4169e1");
		tempMap.put("saddlebrown", "#8b4513");
		tempMap.put("salmon", "#fa8072");
		tempMap.put("sandybrown", "#f4a460");
		tempMap.put("seagreen", "#2e8b57");
		tempMap.put("seashell", "#fff5ee");
		tempMap.put("sienna", "#a0522d");
		tempMap.put("silver", "#c0c0c0");
		tempMap.put("skyblue", "#87ceeb");
		tempMap.put("slateblue", "#6a5acd");
		tempMap.put("slategrey", "#708090");
		tempMap.put("slategray", "#708090");
		tempMap.put("snow", "#fffafa");
		tempMap.put("springgreen", "#00ff7f");
		tempMap.put("steelblue", "#4682b4");
		tempMap.put("tan", "#d2b48c");
		tempMap.put("teal", "#008080");
		tempMap.put("thistle", "#d8bfd8");
		tempMap.put("tomato", "#ff6347");
		tempMap.put("turquoise", "#40e0d0");
		tempMap.put("violet", "#ee82ee");
		tempMap.put("wheat", "#f5deb3");
		tempMap.put("white", "#ffffff");
		tempMap.put("whitesmoke", "#f5f5f5");
		tempMap.put("yellow", "#ffff00");
		tempMap.put("yellowgreen", "#9acd32");

		colorNames = Collections.unmodifiableMap(tempMap);
	}
	/**
	 * Constructors
	 */
	public Color() {
		red = 70;
		green = 130;
		blue = 180;
		alpha = 1.0f;  // default is Steel Blue
	}

	/**
	 *
	 * Constructor, given r, g, b, a components
	 *
	 * @param r, g, b, a  - checked to be in the range 0-255
	 *
	 */
	public Color(int r, int g, int b, float a) {
		setColor (r, g, b, a);
	}

	/**
	 *
	 * 	Constructor, given r, g, b components
	 *	alpha (opacity) defaults to 255
	 *
	 *	@param r, g, b, a  - checked to be in the range 0-255
	 *
	 */
	public Color(int r, int g, int b) {
		setColor (r, g, b, 1.0f);
	}

	/**
	 *
	 * 	Constructor, given color name
	 *
	 *	@param color - checked to be in the list of possible color names
	 *
	 */
	public Color(String color) {
		setColor (color);
	}


	/**
	 *
	 * 	sets color to the given r, g, b, a components
	 *
	 *	@param r, g, b, a  - checked to be in the range 0-255
	 *
	 */
	public void setColor(int r, int g, int b, float a) {
		// check color component ranges
		if (r >= 0 &&  r <= 255 && g >= 0 &&  g <= 255 &&
			b >= 0 && b <= 255 && a >= 0.0f && a <= 1.0f) {
			red = r;
			green = g;
			blue = b;
			alpha = a;
			return;
		}
		throw new InvalidValueException(
			"Invalid color range (r,g,b must be 0-255, alpha in 0-1)\n");
	}

	/**
	 *
	 * 	sets the red component
	 *
	 * @param r  - checked to be in the range 0-255
	 *
	 */
	public void setRed(int r) {
		if (r >= 0 && r <= 255) {
			red = r;
			return;
		}
		throw new InvalidValueException("Invalid color range(red):" +
			" must be in the range 0-255\n");
	}

	/**
	 *
	 * 	gets the red component
	 *
	 * 	@return  red - returns the red component of the color
	 *
	 */
	public int getRed() {
		return red;
	}

	/**
	 *
	 * 	sets the green component
	 *
	 * 	@param g  - checked to be in the range 0-255
	 *
	 */
	public void setGreen(int g) {
		if (g >= 0 && g <= 255) {
			green = g;
			return;
		}
		throw new InvalidValueException("Invalid color range(green):" +
			" must be in the range 0-255\n");
	}

	/**
	 *
	 * 	gets the green component
	 *
	 * 	@return  green - returns the green component of the color
	 *
	 */
	public int getGreen() {
		return green;
	}

	/**
	 *
	 * 	sets the blue component
	 *
	 * 	@param b  - checked to be in the range 0-255
	 *
	 */
	public void setBlue(int b) {
		if (b >= 0 && b <= 255) {
			blue = b;
			return;
		}
		throw new InvalidValueException("Invalid color range(blue):" +
			" must be in the range 0-255\n");
	}

	/**
	 *
	 * 	gets the blue component
	 *
	 * 	@return  blue - returns the blue component of the color
	 *
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 *
	 * 	sets the alpha(opacity) component
	 *
	 * 	@param a  - checked to be in the range 0-255
	 *
	 */
	public void setAlpha(float a) {
		if (a >= 0.0f && a <= 1.0f) {
			alpha = a;
			return;
		}
		throw new InvalidValueException("Invalid color range(alpha):" +
			" must be in the range 0.0-1.0\n");
	}

	/**
	 *
	 * 	gets the alpha component
	 *
	 * 	@return  alpha - returns the alpha(opacity) component of the color
	 *
	 */
	public float getAlpha() {
		return alpha;
	}

	/**
	 *
	 * 	gets the Color representation as a String
	 *
	 * 	@return - returns the color as a String with an RGBA array format
	 *
	 */
	public String getRepresentation() {
		return "[" + red + "," + green + "," + blue + "," + alpha + "]";
	}

	/**
	 *
	 * 	gets the RGB Color representation as a Hex String
	 *
	 * 	@return - returns the RGB color as hexadecimal String
	 *
	 */
	public String getHexRepresentation() {
		String hex = String.format("%02x%02x%02x", red, green, blue);
		// hex += Float.toHexString(alpha);
		return hex;
	}

	/**
	 *
	 * 	gets a Byte array representation of a Color
	 *
	 * 	@return - returns the RGBA color as a byte array
	 *
	 */
	public byte[] getByteRepresentation() {
		int r = red;
		int g = green;
		int b = blue;
		int a  = Math.round(255 * alpha);

		// keep lowest byte of each int
		byte rd = (byte) r;
		byte gn = (byte) g;
		byte bl = (byte) b;
		byte al = (byte) a;

		return ByteBuffer.allocate(4)
			.put(rd)
			.put(gn)
			.put(bl)
			.put(al)
			.array();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Color))
			return false;

		Color other = (Color)obj;
		return (this.red == other.red &&
				this.green == other.green &&
				this.blue == other.blue &&
				Float.compare(this.alpha, other.alpha) == 0);
	}

	@Override
	public String toString() {
		return String.format("Color RGBA:%d, %d, %d, %f", this.red, this.green, this.blue, this.alpha);
	}

	public static Color colorFromHex(String hexStr) throws IllegalArgumentException {
		Color col = new Color();
		if (hexStr.length() != 7 || !hexStr.startsWith("#")) {
			throw new IllegalArgumentException("Hex colors must follow the format '#000000' through '#FFFFFF");
		}
		col.red = Integer.valueOf(hexStr.substring(1, 3), 16);
		col.green = Integer.valueOf(hexStr.substring(3, 5), 16);
		col.blue = Integer.valueOf(hexStr.substring(5, 7), 16);

		return col;
	}
	/**
	 *
	 * 	sets the color to the RGBA components given the color name
	 *
	 *  @param col_name  color name
	 *
	 */
	public void setColor(String col_name) {
	    if (!colorNames.containsKey(col_name.toLowerCase()))
			throw new InvalidValueException("Invalid color name: " + col_name + "\n");

	    Color tempColor = colorFromHex(colorNames.get(col_name.toLowerCase()));
	    this.red = tempColor.red;
	    this.green = tempColor.green;
	    this.blue = tempColor.blue;
	    this.alpha = 1.0f;
	}
}

