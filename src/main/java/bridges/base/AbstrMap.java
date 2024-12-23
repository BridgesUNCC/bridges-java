package bridges.base;


public abstract class AbstrMap {

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

	public abstract String getProjection();
	public abstract Boolean getOverlay();
	public abstract String  getMapRepresentation();
};
