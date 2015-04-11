package edu.uncc.cs.bridges_vs2;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.connect.DataFormatter;
import edu.uncc.cs.brdigesV2.validation.*;

public class DataFormatterExceptionTest {
	static DataFormatterException df1;
	static DataFormatterException df2;
	static DataFormatterException df3;
	static DataFormatterException df4;
	
	static String msg = "this is the error message";
	
	/** Setup static variables for use in later tests */
	@BeforeClass
	static public void BeforeClass(){
		df1 = new DataFormatterException();
		df2 = new DataFormatterException(msg);
		df3 = new DataFormatterException(new NullPointerException());
		df4 = new DataFormatterException(msg, new NullPointerException());
	}

	/** Test getCause() returns correct type of error */
	@Test
	public void testGetCause() {
		assertNull("getCause() did not return null", df1.getCause());
		assertNull("getCuase() did not return null", df2.getCause());
		assertEquals("getCause() did not return correct throwable", NullPointerException.class, df3.getCause().getClass());
		assertEquals("getCause() did not return correct throwable", NullPointerException.class, df4.getCause().getClass());
				
	}

	/** Test getError() returns correct error message */
	@Test
	public void testGetError() {
		assertEquals("getError() did not return correct error message", "Error unknown.", df1.getError());
		assertEquals("getError() did not return correct error message", msg, df2.getError());
		assertEquals("getError() did not return correct error message", new NullPointerException().getMessage(), df3.getError());
		assertEquals("getError() did not return correct error message", msg, df4.getError());
	}

}
