package edu.uncc.cs.bridges_vs2;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.brdigesV2.validation.*;

public class RateLimitExceptionTest {
	static RateLimitException e;
	
	
	/** Construct static variables to be tested in later classes */
	@BeforeClass
	static public void BeforeClass(){
		e = new RateLimitException("abce");
	}
	
	
	/** Test rateLimitException constructs correctly */
	@Test
	public void testRateLimitException() {
		assertNotNull(e);
		assertTrue(e instanceof RateLimitException);
	}

}
