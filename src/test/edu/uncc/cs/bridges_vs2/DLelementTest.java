package edu.uncc.cs.bridges_vs2;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.DLelement;

public class DLelementTest {
	static DLelement<String> dle0;
	static DLelement<String> dle1;
	static DLelement<String> dle2;
	static DLelement<String> dle3;
	static DLelement<String> dle4;
	static DLelement<String> dle5;

	/** Setup static variables for later tests */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dle0 = new DLelement<String>();
		dle1 = new DLelement<String>("A", "a");
		dle2 = new DLelement<String>(dle1, dle0);
		dle3 = new DLelement<String>("B", "b");
		dle4 = new DLelement<String>("c", dle2, dle3);
		
		dle5 = new DLelement<String>();
	}

	@Test
	/** Test getNext() returns correct element*/
	public void testGetNext() {
		assertNull("getNext() should be null", dle0.getNext());
		assertNull("getNext() should be null", dle1.getNext());
		assertNotNull("getNext() should not be null", dle2.getNext());
		assertNotNull("getNext() should not be null", dle4.getNext());
	}

	@Test
	/** Test setNext() returns correct element next set to new DLelement*/
	public void testSetNextToNewDLelement() {
		dle5.setNext(dle1);
		assertEquals("setNext() did not return correct object", dle1,  dle5.getNext());		
	}

	@Test
	/** Test setNext() method when next set to null*/
	public void testSetNextToNull() {
		dle5.setNext(null);
		assertNull("setNext() did not return null", dle5.getNext());
	}

	@Test
	/** Test getPrev() returns correct element*/
	public void testGetPrev() {
		assertNull("getPrev() should be null", dle0.getPrev());
		assertNull("getPrev() should be null", dle1.getPrev());
		assertNotNull("getPrev() should not be null", dle2.getPrev());
		assertNotNull("getPrev() should not be null", dle4.getPrev());
	}

	@Test
	/** Test setPrev() when prev set to new DLelement */
	public void testSetPrevtoNewDLelement() {
		dle5.setPrev(dle1);
		assertEquals("setPrev() did not return correct object", dle1,  dle5.getPrev());		
	}

	@Test
	/** Test setPrev() when prev set to null */
	public void testSetPrevToNull() {
		dle5.setPrev(null);
		assertNull("setPrev() did not return null", dle5.getPrev());
	}


}
