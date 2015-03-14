package edu.uncc.cs.bridges_vs2;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.validation.DataFormatterException;
import edu.uncc.cs.bridgesV2.validation.RateLimitException;


public class RateLimitExceptionTest {
	static RateLimitException e;
	
	
	@BeforeClass
	static public void BeforeClass(){
		e = new RateLimitException("abce");
	}
	
	@Test
	public void testRateLimitException() {
		assertNotNull(e);
		assertTrue(e instanceof RateLimitException);
	}

}
