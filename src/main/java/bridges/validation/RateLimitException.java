package bridges.validation;
/**
 * Throws exception to handle overloading the server to allow the student 
 * to stop querying the server and send in their visualizations.
 * This exception is expected every time the program runs to limit the 
 * amount of requests going to Twitter etc.
 * 
 * @author Michael
 *
 */
public class RateLimitException extends Exception {

	public RateLimitException(String string) {
		super(string);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 9071223926318659417L;
}
