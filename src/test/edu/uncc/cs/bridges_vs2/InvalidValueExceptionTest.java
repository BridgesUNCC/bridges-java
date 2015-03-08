import edu.uncc.cs.bridgesV2.validation.InvalidValueException;
import edu.uncc.cs.bridgesV2.validation.RateLimitException;
import junit.framework.TestCase;


public class InvalidValueExceptionTest extends TestCase {

	public void testInvalidValueException() {
		InvalidValueException e = new InvalidValueException("error message");
		assertNotNull(e);
		assertTrue(e instanceof InvalidValueException);
	}

}
