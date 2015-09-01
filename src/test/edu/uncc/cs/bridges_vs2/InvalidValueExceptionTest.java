import bridges_v21.validation.*;
import junit.framework.TestCase;


public class InvalidValueExceptionTest extends TestCase {

	/** Test InvalidValueException constructs correctly */
	public void testInvalidValueException() {
		InvalidValueException e = new InvalidValueException("error message");
		assertNotNull(e);
		assertTrue(e instanceof InvalidValueException);
	}

}
