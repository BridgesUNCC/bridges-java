package sketch;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import scala.actors.threadpool.Arrays;

public class Validation {

	private static final Set<String> VALID_COLORS = new HashSet<>();
	private static final Set<Pattern> COLOR_PATTERNS = new HashSet<>();
	
	static {
		COLOR_PATTERNS.add(Pattern.compile("#([0-9a-fA-F]{3}|[0-9a-fA-F]{6})"));
		//TODO: Support more colors
		VALID_COLORS.addAll(Arrays.asList(new String[] {
				"aliceblue",
				"antiquewhite",
				"aqua",
				"aquamarine",
				"azure",
				"beige",
				"bisque",
				"black",
				"blanchedalmond",
				"blue",
				"blueviolet",
				"brown",
				"burlywood",
				"cadetblue",
				"chartreuse",
				"chocolate",
				"coral",
				"cornflowerblue",
				"cornsilk",
				"crimson",
				"cyan",
				"darkblue",
				"darkcyan",
				"darkgoldenrod",
				"darkgray",
				"darkgreen",
				"darkgrey",
				"darkkhaki",
				"darkmagenta",
				"darkolivegreen",
				"darkorange",
				"darkorchid",
				"darkred",
				"darksalmon",
				"darkseagreen",
				"darkslateblue",
				"darkslategray",
				"darkslategrey",
				"darkturquoise",
				"darkviolet",
				"deeppink",
				"deepskyblue",
				"dimgray",
				"dimgrey",
				"dodgerblue",
				"firebrick",
				"floralwhite",
				"forestgreen",
				"fuchsia",
				"gainsboro",
				"ghostwhite",
				"gold",
				"goldenrod",
				"gray",
				"green",
				"greenyellow",
				"grey",
				"honeydew",
				"hotpink",
				"indianred",
				"indigo",
				"ivory",
				"khaki",
				"lavender",
				"lavenderblush",
				"lawngreen",
				"lemonchiffon",
				"lightblue",
				"lightcoral",
				"lightcyan",
				"lightgoldenrodyellow",
				"lightgray",
				"lightgreen",
				"lightgrey",
				"lightpink",
				"lightsalmon",
				"lightseagreen",
				"lightskyblue",
				"lightslategray",
				"lightslategrey",
				"lightsteelblue",
				"lightyellow",
				"lime",
				"limegreen",
				"linen",
				"magenta",
				"maroon",
				"mediumaquamarine",
				"mediumblue",
				"mediumorchid",
				"mediumpurple",
				"mediumseagreen",
				"mediumslateblue",
				"mediumspringgreen",
				"mediumturquoise",
				"mediumvioletred",
				"midnightblue",
				"mintcream",
				"mistyrose",
				"moccasin",
				"navajowhite",
				"navy",
				"oldlace",
				"olive",
				"olivedrab",
				"orange",
				"orangered",
				"orchid",
				"palegoldenrod",
				"palegreen",
				"paleturquoise",
				"palevioletred",
				"papayawhip",
				"peachpuff",
				"peru",
				"pink",
				"plum",
				"powderblue",
				"purple",
				"red",
				"rosybrown",
				"royalblue",
				"saddlebrown",
				"salmon",
				"sandybrown",
				"seagreen",
				"seashell",
				"sienna",
				"silver",
				"skyblue",
				"slateblue",
				"slategray",
				"slategrey",
				"snow",
				"springgreen",
				"steelblue",
				"tan",
				"teal",
				"thistle",
				"tomato",
				"turquoise",
				"violet",
				"wheat",
				"white",
				"whitesmoke",
				"yellow",
				"yellowgreen"
		}));
	}
	
	/**
	 * Determine if a color is supported by CSS.
	 * 
	 * This method only supports a subject of CSS (yet). (1) 173 CSS extended
	 * color names, (2) #RRGGBB or #RGB, where R, G and B are red, green, blue
	 * values as hexadecimal digits.
	 * 
	 * @param color
	 * 
	 * @return whether the color is valid
	 */
	public static void validateColor(String color) {
		if (VALID_COLORS.contains(color)) {
			// Named color
			return;
		} else {
			for (Pattern p : COLOR_PATTERNS) {
				if (p.matcher(color).matches()) {
					return;
				}
			}
		}
		throw new InvalidCSSValueException("Invalid color value' " + color + "'. Expected"
				+ "CSS color name, or #RRGGBB or #RGB formats.");
	}

}

/**
 * Exception indicating invalid CSS values.
 * Examples of uses for this include sizes with invalid units, and invalid
 * colors.
 * @author Sean Gallagher
 *
 */
class InvalidCSSValueException extends RuntimeException {
	public InvalidCSSValueException(String message) {
		super(message);
	}

	/**
	 * Auto-generated ID
	 */
	private static final long serialVersionUID = 8664177180092591999L;
	
}
