

package edu.uncc.cs.bridges;

/**
 * This is an extension of the Exception class
 * adding my customized exceptions
 * @author mihai
 *
 */
public class BridgesException extends Exception {
		public String aMessage;
		public Throwable aCause;
	    
		public BridgesException () {
	    		super();
	    		aMessage = "Error unknown.";
	    }

	    public BridgesException (String message) {
	        super (message);
	        aMessage = message;
	        System.out.println(this.getMessage());
	    }

	    public BridgesException (Throwable cause) {
	        super (cause);
	        aCause = cause;
	    }

	    public BridgesException (String message, Throwable cause) {
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

