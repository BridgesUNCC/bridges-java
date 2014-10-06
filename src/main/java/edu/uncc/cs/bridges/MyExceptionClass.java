

package edu.uncc.cs.bridges;

/**
 * This is an extension of the Exception class
 * adding my customized exceptions
 * @author mihai
 *
 */
public class MyExceptionClass extends Exception {
	    public MyExceptionClass () {

	    }

	    public MyExceptionClass (String message) {
	        super (message);
	    }

	    public MyExceptionClass (Throwable cause) {
	        super (cause);
	    }

	    public MyExceptionClass (String message, Throwable cause) {
	        super (message, cause);
	    }
}

