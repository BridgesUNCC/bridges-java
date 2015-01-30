package testing; 

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.*;


public class SLelementTest {
	static SLelement<?> sle0;
	static SLelement<?> sle1;
	static SLelement<?> sle2;
	static SLelement<?> sle3;
	static SLelement<?> sle4;
	static SLelement<?> sle5clone0;
	

	/** use each constructor to make an element
	 * @throws CloneNotSupportedException 
	 */
	@BeforeClass
	public static void BeforeClass() throws CloneNotSupportedException {
		sle0 = new SLelement<String>("A", "a");
		sle1 = new SLelement<String>("B",(SLelement<String>) sle0);
		sle2 = new SLelement<String>((SLelement<String>) sle1);
		sle3 = new SLelement<String>("D", "d");
		sle4 = new SLelement<String>("E", "e");
		sle5clone0 = sle0.clone();
	}
	
	@Test
	/** test class variable used to assign sle identifier at construction*/
	public void testIdentiferAssignmentAtConstruction() {
		assertEquals("Identifier assigned at construction is not correct", "0", sle0.getIdentifier());
		assertEquals("Identifier assigned at construction is not correct", "1", sle1.getIdentifier());
		assertEquals("Identifier assigned at construction is not correct", "2", sle2.getIdentifier());
	}
	
	@Test
	public void testNextIsNullWhenNotPassedSLEonConstruction() {
		assertNull("Next element is not null when constructor was not passed next element", sle0.getNext());
		assertNotNull("Next element is set to null when constructor received next element", sle1.getNext());
		assertNotNull("Next element is set to null when constructor received next element", sle2.getNext());
	}
	
	
	
	@Test
	/** testGetNext() */
	public void testGetNext() {
		assertNull("Expected null, getNext() did not return null", sle0.getNext());
		assertEquals("Unexpected value returned from getNext()", sle0, sle1.getNext());
		assertEquals("Unexpected value returned from getNext()", sle1, sle2.getNext());
	}
	
	@Test
	/** testSetNext() method */
	public void testSetNext() {
		//setNext to new value
		((SLelement<String>) sle4).setNext((SLelement<String>) sle3);
		assertEquals("setNext() does not set pointer to correct object", sle3, sle4.getNext());
		
		//setNext to Null
		sle4.setNext(null);
		assertNull("setNext() to null did not set pointer to null", sle4.getNext());
	}
	
	@Test
	/** test toString() method (JSON file) */
	public void testToString() {
		assertTrue("Invalid JSON on unlinked SLelement", JSONValidator.isValidJSON(sle0.toString()));
		assertTrue("Invalid JSON on linked SLelement", JSONValidator.isValidJSON(sle1.toString()));
	}
	
	@Test
	/** test  toString() method after elements have been changed(JSON file) */
	public void testToStringOnLinkedSLelementsAfterLinkingHasChanged() {
		((SLelement<String>) sle4).setNext((SLelement<String>) sle3);
		((SLelement<String>) sle3).setNext((SLelement<String>) sle2);
		((SLelement<String>) sle3).setNext((SLelement<String>) sle1);

		assertTrue("Invalid JSON on linked SLelement", JSONValidator.isValidJSON(sle4.toString()));
		assertTrue("Invalid JSON on linked SLelement", JSONValidator.isValidJSON(sle3.toString()));		
	}

	@Test
	public void testCloneMethodGetsNewIdentifer () {
		assertEquals("Identifier from clone method is not correct", "5", sle5clone0.getIdentifier());
		assertNotEquals(sle5clone0, sle0);
	}
	
	@Test
	public void testCloneIsDifferentObject() throws CloneNotSupportedException {
		assertNotEquals(sle5clone0, sle0);
		assertEquals(sle5clone0.getIdentifier(), sle0.getIdentifier());
		assertEquals(sle5clone0.getLabel(), sle0.getLabel());
		assertEquals(sle5clone0.getValue(), sle0.getValue());


	}
}
