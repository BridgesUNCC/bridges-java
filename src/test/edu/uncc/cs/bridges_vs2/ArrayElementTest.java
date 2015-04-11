package edu.uncc.cs.bridges_vs2;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.ArrayElement;
import edu.uncc.cs.bridgesV2.base.DLelement;


public class ArrayElementTest {
	static ArrayElement<String> a0;

	/** Set up static elements to for later tests. */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		a0 = new ArrayElement<String>("A", "a");
	}
	
	/** Tests whether constructor is working properly */
	@Test
	public void testArrayElement() {
		assertNotNull(a0);
		assertEquals("a", a0.getValue());
	}

}
