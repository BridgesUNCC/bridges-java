package edu.uncc.cs.bridges_vs2;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.ArrayElement;
import edu.uncc.cs.bridgesV2.base.ArrayOfElement;
import edu.uncc.cs.bridgesV2.base.Element;
import junit.framework.TestCase;


public class ArrayOfElementTest  {
	static ArrayOfElement<Element<String>> a0;

	/** Set up static elements to for later tests. */
	@BeforeClass
	public static void BeforeClass() throws Exception {
		a0 = new ArrayOfElement<Element<String>>(Element.class);
	}
	
	/** Tests whether constructor is working properly */
	@Test
	public void testArrayOfElement() {
		assertNotNull(a0);
	}

	/** Tests whether ArrayOfElements is properly holding array. */
	@Test
	public void testReturnArray() {
		Element<String>[] elementArray = a0.returnArray();
		ArrayElement<String> element = new ArrayElement<String>("A", "a");
		
		
		elementArray[0] = element;
		
		assertEquals("ArrayOfElement does not return correct array", element, elementArray[0]);
	}

}
