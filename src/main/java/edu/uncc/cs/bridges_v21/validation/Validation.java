package bridges.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Validation {

	public static final Set<String> COLOR_NAMES = new HashSet<String>();
//	public static final Map<String, String> COLOR_NAMES;
//						new HashMap<String, String>();
//	public static final Map<String, ArrayList<Integer> > COLOR_NAMES;
	public static final Set<Pattern> COLOR_PATTERNS = new HashSet<Pattern>();
	public static final Set<String> NODE_SHAPES = new HashSet<String>();
			//	this variable holds the maximum number of allowed elements
	private static int MAX_ELEMENTS_ALLOWED = 5000; 
	
	static {
		COLOR_PATTERNS.add(Pattern.compile("#([0-9a-fA-F]{3}|[0-9a-fA-F]{6})"));
/*
		COLOR_NAMES = new HashMap<String,ArrayList<Integer>>(){{
			put("red",  new ArrayList<>(Arrays.asList(255, 0, 0, 255)));
			put("green",  new ArrayList<>(Arrays.asList(0, 255, 0, 255)));
			put("blue",  new ArrayList<>(Arrays.asList(0, 0, 255, 255)));
			put("yellow",  new ArrayList<>(Arrays.asList(255, 255, 0, 255)));
			put("magenta",  new ArrayList<>(Arrays.asList(255, 0, 255, 255)));
			put("black",  new ArrayList<>(Arrays.asList(0, 0, 0, 255)));
			put("white",  new ArrayList<>(Arrays.asList(255, 255, 255, 255)));
			put("purple",  new ArrayList<>(Arrays.asList(128, 0, 128, 255)));
			put("brown",  new ArrayList<>(Arrays.asList(255, 165, 0, 255)));
		}};
		
		COLOR_NAMES = new HashMap<String,String>(){{
			put("red",   "[255,0,0,255)]"); //Primary
			put("green", "[0,255,0,255]");	//Secondary(full 255 is considered lime by CSS)
			put("blue", "[0,0,255,255]"); //Primary
			put("yellow","[255,255,0,255]"); //subtractive Primary
			put("cyan","[0,255,255,255]");   //subtractive primary
			put("magenta","[255,0,255,255]"); //subtractive primary
			put("orange","[255,165,0,255]"); //Secondary
			put("purple","[128,0,128,255]"); //Secondary
			put("brown","[165,42,42,255]"); //Neutral
			put("black","[0,0,0,255]"); //Monochrome
			put("grey","[192,192,192,255]"); //Monochrome
			put("white","[255,255,255,255]");//Monochrome
		}};
*/

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
				"triangle",
				"star",
				"wye"
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
	public static void validateColor(String color) throws 
							InvalidValueException{
		if (COLOR_NAMES.contains(color)) {
									// Named color
			switch (color) {
            	case "red": 	color =  "[255, 0, 0, 255]"; break;
            	case "green": 	color =  "[0, 255, 0, 255]"; break;
            	case "blue":  	color =  "[0, 0, 255, 255]"; break;
            	case "yellow": 	color =  "[255, 0, 255, 255]"; break;
            	case "cyan": 	color =  "[0, 255, 255, 255]"; break;
            	case "magenta": color =  "[255, 255, 0, 255]"; break;
            	case "white": 	color =  "[255, 255, 255, 255]"; break;
            	case "black": 	color =  "[0, 0, 0, 255]"; break;
			}
			return;
		}
//		else {
//			for (Pattern p : COLOR_PATTERNS) {
//				if (p.matcher(color).matches()) {
//					return color;
//				}
//			}
//		}
		throw new InvalidValueException("Invalid color' " + color + 
			"'. Expected" + "CSS color name, or #RRGGBB or #RGB formats.");
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
				shape + "'. Expected one of: " + NODE_SHAPES);
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
	 * Determines if the size value passed is an acceptable value to set 
	 * the size to.
	 * 
	 * @param val
	 */
	public static void validateSize(double val){
		if(val >= 0.0 && val <= 50.0){
			return;
		}
		else{
			throw new InvalidValueException("Invalid value' " + val + 
				".  Expected" + " a size value between 0.0 and 50.0.");
		}
	}

	/**
	 * Determines if the link thickness value passed is an acceptable 
	 * value to set the size to.
	 * 
	 * @param val
	 */
	public static void validateThickness(double val){
		if(val >= 0.0 && val <= 50.0){
			return;
		}
		else{
			throw new InvalidValueException("Invalid value' " + val + 
				".  Expected" + " a thickness value between 0.0 and 50.0.");
		}
	}

	public static void validate_ADT_size(int i){
		try {
			if (i > MAX_ELEMENTS_ALLOWED)
				throw new Exception(
					"No more than " + MAX_ELEMENTS_ALLOWED + 
						" elements can be created!");
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.exit(42);
		}
	}
}
