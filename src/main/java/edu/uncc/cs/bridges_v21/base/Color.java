package bridges.base;

import java.util.Map;
import java.util.HashMap;
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
 *
 *	@author K.R. Subramanian,
 *	@date 7/14/16
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
		// } catch ( Exception err ) {
		// 	throw new InvalidValueException("Invalid color name: " + color +
		// 		"\n");
		// }
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
	 * 	sets the color to the RGBA components given the color name
	 *
	 *  @param col_name  color name
	 *
	 */
	public void setColor(String col_name) {
		switch (col_name) {
			case "red":
				red = 255;
				green = blue = 0;
				alpha = 1.0f;
				break;
			case "green":
				red = 0;
				green = 255;
				blue = 0;
				alpha = 1.0f;
				break;
			case "blue":
				red = 0;
				green = 0;
				blue = 255;
				alpha = 1.0f;
				break;
			case "yellow":
				red = 255;
				green = 255;
				blue = 0;
				alpha = 1.0f;
				break;
			case "cyan":
				red = 0;
				green = 255;
				blue = 255;
				alpha = 1.0f;
				break;
			case "magenta":
				red = 255;
				green = 0;
				blue = 255;
				alpha = 1.0f;
				break;
			case "white":
				red = 255;
				green = 255;
				blue = 255;
				alpha = 1.0f;
				break;
			case "black":
				red = 0;
				green = 0;
				blue = 0;
				alpha = 1.0f;
				break;
			case "orange":
				red = 255;
				green = 155;
				blue = 0;
				alpha = 1.0f;
				break;
			case "turquoise":
				red = 173;
				green = 234;
				blue = 234;
				alpha = 1.0f;
				break;
			case "maroon":
				red = 176;
				green = 48;
				blue = 96;
				alpha = 1.0f;
				break;
			case "aquamarine":
				red = 127;
				green = 255;
				blue = 212;
				alpha = 1.0f;
				break;
			case "azure":
				red = 240;
				green = 255;
				blue = 255;
				alpha = 1.0f;
				break;
			case "beige":
				red = 245;
				green = 245;
				blue = 220;
				alpha = 1.0f;
				break;
			case "brown":
				red = 166;
				green = 42;
				blue = 42;
				alpha = 1.0f;
				break;
			case "tan":
				red = 210;
				green = 180;
				blue = 140;
				alpha = 1.0f;
				break;
			case "olive":
				red = 128;
				green = 128;
				blue = 0;
				alpha = 1.0f;
				break;
			case "chartreuse":
				red = 127;
				green = 255;
				blue = 0;
				alpha = 1.0f;
				break;
			case "khaki":
				red = 240;
				green = 230;
				blue = 140;
				alpha = 1.0f;
				break;
			case "bisque":
				red = 255;
				green = 228;
				blue = 196;
				alpha = 1.0f;
				break;
			case "coral":
				red = 255;
				green = 127;
				blue = 0;
				alpha = 1.0f;
				break;
			case "pink":
				red = 255;
				green = 192;
				blue = 203;
				alpha = 1.0f;
				break;
			case "lavender":
				red = 230;
				green = 230;
				blue = 250;
				alpha = 1.0f;
				break;
			case "purple":
				red = 160;
				green = 32;
				blue = 240;
				alpha = 1.0f;
				break;
			case "gold":
				red = 255;
				green = 215;
				blue = 0;
				alpha = 1.0f;
				break;
			default:
				throw new InvalidValueException("Invalid color name: " + col_name + "\n");
		}
	}
}
