package bridges.base;

/**
 *	This is an abstract super class that is extended by all Bridges subclasses and
 *	provides some methods that are used universally across Bridges.
 *
 *	@author Kalpathi Subramanian, 
 *	@date  7/18/16
 *
 */
public abstract class DataStruct {
						// used by subclasses in JSON construction
	protected String
			QUOTE = "\"",
			COMMA = ",",
			COLON = ":",
			OPEN_CURLY = "{", 
			CLOSE_CURLY = "}", 
			OPEN_PAREN = "(",
			CLOSE_PAREN = ")",
			OPEN_BOX = "[",
			CLOSE_BOX = "]";

	protected abstract  String getDataStructType();
};

