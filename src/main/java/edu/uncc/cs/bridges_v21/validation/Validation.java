package bridges.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Validation {

	public static final Set<String> COLOR_NAMES = new HashSet<String>();
	public static final Set<Pattern> COLOR_PATTERNS = new HashSet<Pattern>();
	public static final Set<String> NODE_SHAPES = new HashSet<String>();
			//	this variable holds the maximum number of allowed elements
	private static int MAX_ELEMENTS_ALLOWED = 5000; 
	
	static {
		COLOR_PATTERNS.add(Pattern.compile("#([0-9a-fA-F]{3}|[0-9a-fA-F]{6})"));
		COLOR_NAMES.addAll(Arrays.asList(new String[] {
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
		NODE_SHAPES.addAll(Arrays.asList(new String[]{
				"circle",
				"square",
				"diamond",
				"cross",
				"triangle-down",
				"triangle-up"
		}));
	}
	
	/**
	 * Determine if a color is supported by CSS.
	 * 
	 * This method only supports a subject of CSS (yet). (1) 173 CSS extended
	 * color names, (2) #RRGGBB or #RGB, where R, G and B are red, green, blue
	 * values as hexadecimal digits.
	 * 
	 * This method does not check for null because null has special meaning.
	 * 
	 * @param color
	 * 
	 * @return whether the color is valid
	 */
	public static void validateColor(String color) throws InvalidValueException{
		if (COLOR_NAMES.contains(color)) {
			// Named color
			return;
		} else {
			for (Pattern p : COLOR_PATTERNS) {
				if (p.matcher(color).matches()) {
					return;
				}
			}
		}
		throw new InvalidValueException("Invalid color' " + color + "'. Expected"
				+ "CSS color name, or #RRGGBB or #RGB formats.");
	}
	
	/**
	 * Determines if the shape is supported.
	 * 
	 * @param shape
	 */
	public static void validateShape(String shape) {
		if (NODE_SHAPES.contains(shape)) {
			return;
		} 
		else {
			throw new InvalidValueException("Invalid shape' " + 
				shape + "'. Expected " + "one of: " + NODE_SHAPES);
		}
	}
	
	/**
	 * Determines if the value passed is an acceptable value to set the 
	 * opacity to.
	 * 	
	 * @param val
	 */
	public static void validateOpacity(double val){
		if(val >= 0.0 && val <= 1.0){
			return;
		}else{
			throw new InvalidValueException("Invalid value' " + val + "'. Expected"
					+ " a value between 0.0 and 1.0.");
		}
	}
	
	/**
	 * Determines if the value passed is an acceptable value to set the size to.
	 * 
	 * @param val
	 */
	//Temporary Values
	public static void validateSize(double val){
		if(val >= 0.0 && val <= 50.0){
			return;
		}
		else{
			throw new InvalidValueException("Invalid value' " + val + 
				".  Expected" + " a value between 0.0 and 50.0.");
		}
	}
	public static void validate_ADT_size(int i){
		try {
			if (i > MAX_ELEMENTS_ALLOWED)
				throw new Exception(
						"No more than 1000 elements can be created!");
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.exit(42);
		}
	}
}
