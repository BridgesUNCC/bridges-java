package bridges.base;

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

 * Supported named colors are:
 * 
 * <b>"red", "green",
 * "blue",
 * "yellow",
 * "cyan",
 * "magenta",
 * "white",
 * "black",
 * "orange",
 * "green",
 * "turquoise",
 * "maroon",
 * "aquamarine",
 * "azure",
 * "beige",
 * "brown",
 * "tan",
 * "olive",
 * "chartreuse",
 * "khaki",
 * "bisque",
 * "coral",
 * "pink",
 * "lavender",
 * "purple",
 * "gold",
 * "steelblue"</b>
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
	private static final Map<String, String> ColorNames
		= new HashMap<String, String>();
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
	/**
	 *
	 * 	sets the color to the RGBA components given the color name
	 *
	 *  @param col_name  color name
	 *
	 */
	public void setColor(String col_name) {
		switch (col_name) {
			case "red": 
				red = 255; green = blue = 0; alpha = 1.0f;
				break;
			case "green": 
				red = 0; green = 255; blue = 0; alpha = 1.0f;
				break;
			case "blue":
				red = 0; green = 0; blue = 255; alpha = 1.0f;
				break;
			case "yellow":
				red = 255; green = 255; blue = 0; alpha = 1.0f;
				break;
			case "cyan":
				red = 0; green = 255; blue = 255; alpha = 1.0f;
				break;
			case "magenta":
				red = 255; green = 0; blue = 255; alpha = 1.0f;
				break;
			case "white":
				red = 255; green = 255; blue = 255; alpha = 1.0f;
				break;
			case "black":
				red = 0; green = 0; blue = 0; alpha = 1.0f;
				break;
			case "orange":
				red = 255; green = 155; blue = 0; alpha = 1.0f;
				break;
			case "turquoise":
				red = 64; green = 224; blue = 208; alpha = 1.0f;
				break;
			case "maroon":
				red = 176; green = 48; blue = 96; alpha = 1.0f;
				break;
			case "aquamarine":
				red = 127; green = 255; blue = 212; alpha = 1.0f;
				break;
			case "azure":
				red = 240; green = 255; blue = 255; alpha = 1.0f;
				break;
			case "beige":
				red = 245; green = 245; blue = 220; alpha = 1.0f;
				break;
			case "brown":
				red = 166; green = 42; blue = 42; alpha = 1.0f;
				break;
			case "tan":
				red = 210; green = 180; blue = 140; alpha = 1.0f;
				break;
			case "olive":
				red = 128; green = 128; blue = 0; alpha = 1.0f;
				break;
			case "chartreuse":
				red = 127; green = 255; blue = 0; alpha = 1.0f;
				break;
			case "khaki":
				red = 240; green = 230; blue = 140; alpha = 1.0f;
				break;
			case "bisque":
				red = 255; green = 228; blue = 196; alpha = 1.0f;
				break;
			case "coral":
				red = 255; green = 127; blue = 80; alpha = 1.0f;
				break;
			case "pink":
				red = 255; green = 192; blue = 203; alpha = 1.0f;
				break;
			case "lavender":
				red = 230; green = 230; blue = 250; alpha = 1.0f;
				break;
			case "purple":
				red = 128; green = 0; blue = 128; alpha = 1.0f;
				break;
			case "gold":
				red = 255; green = 215; blue = 0; alpha = 1.0f;
				break;
			case "steelblue":
				red = 70; green = 130; blue = 180; alpha = 1.0f;
				break;
			default:
				throw new InvalidValueException("Invalid color name: " + col_name + "\n");
		}
	}
}
