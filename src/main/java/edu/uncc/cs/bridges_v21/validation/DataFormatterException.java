package bridges.validation;

/**
 * This is an extension of the Exception class
 * adding my customized exceptions
 * @author mihai
 *
 */
public class DataFormatterException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7258433246711655706L;
		public String aMessage;
		public Throwable aCause;
	    
		
		public DataFormatterException () {
	    		super();
	    		aMessage = "Error unknown.";
	    }

	    public DataFormatterException (String message) {
	        super (message);
	        aMessage = message;
	        System.out.println(this.getMessage());
	    }

	    public DataFormatterException (Throwable cause) {
	        super (cause);
	        aCause = cause;
	    }

	    public DataFormatterException (String message, Throwable cause) {
	        super(message, cause);
	        aMessage = message;
	        aCause = cause;  
	    }
	    
	    public String getError(){
	    		return aMessage;
	    }
	    
	    public Throwable getCause(){
	    		return aCause;
	    }

}

