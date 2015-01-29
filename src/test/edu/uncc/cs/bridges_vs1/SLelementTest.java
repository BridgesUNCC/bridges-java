package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridges_vs1.structure.SLelement;

public class SLelementTest {
	static SLelement<?> sle0;
	static SLelement<?> sle1;
	static SLelement<?> sle2;
	static SLelement<?> sle3;
	static SLelement<?> sle4;
	
	static SLelement<?> sle0cloned;
	static SLelement<?> sle1cloned;

	/** use each constructor to make an element
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		sle0 = new SLelement<String>("A", "a");
		sle1 = new SLelement<String>("B",(SLelement<String>) sle0);
		sle2 = new SLelement<String>("C", "c");
		sle3 = new SLelement<String>("D", "d");
		sle4 = new SLelement<String>("E", "e");
		
		sle0cloned = new SLelement<String>((SLelement<String>) sle0);
		sle1cloned = new SLelement<String>((SLelement<String>) sle1);

	}
	
	@Test
	/** test class variable used to assign sle identifier at construction*/
	public void testIdentiferAssignmentAtConstruction() {
		assertEquals("Identifier assigned at construction is not zero", "0", sle0.getIdentifier());
		assertEquals("Identifier assigned at construction is not one", "1", sle1.getIdentifier());
		assertEquals("Identifier assigned at construction is not two", "2", sle2.getIdentifier());
	}

	@Test
	/** test class variable used to assign sle identifier when using clone constructor*/
	public void testIdentifierAssignmentAtClonedConstruction() {
		assertEquals("Identifier assigned at cloned construction is not zero", "0", sle0cloned.getIdentifier());
		assertEquals("Identifier assigned at cloned construction is not one", "1", sle1cloned.getIdentifier());		
	}
	
	@Test
	/** test getValue() */
	public void testGetValue() {
		assertEquals("Unexpected value returned from getValue()", "a", sle0.getValue());
		assertEquals("Unexpected value returned from getValue()", "B", sle1.getValue());
		assertEquals("Unexpected value returned from getValue()", "a", sle0cloned.getValue());
	}
	
	@Test
	/** testGetNext() */
	public void testGetNext() {
		assertNull("Expected null, getNext() did not return null", sle0.getNext());
		assertEquals("Unexpected value returned from getNext()", sle0, sle1.getNext());
		assertEquals("Unexpected value returned from getNext() on cloned element", sle0, sle1cloned.getNext());
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
	/** test toClone() method for unlinked SLelement*/
	public void testCloneDeepCopiesInformationForUnlinkedSLelement()
			throws CloneNotSupportedException {
		SLelement<String> sle2cloned = (SLelement<String>) sle2.clone();

		assertEquals("toClone() did not result in same identifiers", sle2.getIdentifier(), sle2cloned.getIdentifier());
		assertEquals("toClone() did not result in same values", sle2.getValue(), sle2cloned.getValue());
		assertNotEquals("toClone() did not create return a separate object", sle2, sle2cloned);
		assertEquals("toClone() did not result in same representation", sle2.getRepresentation(), sle2cloned.getRepresentation());
		assertEquals("toClone() did not result in same value from getNext()", sle2.getNext(), sle2cloned.getNext());
	}
	
	@Test
	/** test toClone() method for linked SLelement*/
	public void testCloneDeepCopiesInformationForlinkedSLelement()
			throws CloneNotSupportedException {
		
		//this is LOCAL copy of sle1cloned, not the static version
		SLelement<String> sle1cloned = (SLelement<String>) sle1.clone();

		assertEquals("toClone() did not result in same identifiers", sle1.getIdentifier(), sle1cloned.getIdentifier());
		assertEquals("toClone() did not result in same values", sle1.getValue(), sle1cloned.getValue());
		assertNotEquals("toClone() did not create return a separate object", sle1, sle1cloned);
		assertEquals("toClone() did not result in same representation", sle1.getRepresentation(), sle1cloned.getRepresentation());
		assertEquals("toClone() did not result in same value from getNext()", sle1.getNext(), sle1cloned.getNext());
	}


	@Test
	/** test toString() method (JSON file) */
	public void testToString() {
		assertTrue("Invalid JSON on unlinked SLelement", JSONValidator.isValidJSON(sle0.toString()));
		assertTrue("Invalid JSON on linked SLelement", JSONValidator.isValidJSON(sle1.toString()));
		assertTrue("Invalid JSON on unlinked cloned SLelement", JSONValidator.isValidJSON(sle0cloned.toString()));
		assertTrue("Invalid JSON on linked cloned SLelement", JSONValidator.isValidJSON(sle1cloned.toString()));

	}
	

	/** test  toString() method after elements have been changed(JSON file) */
	public void testToStringOnLinkedSLelementsAfterLinkingHasChanged() {
		((SLelement<String>) sle4).setNext((SLelement<String>) sle3);
		((SLelement<String>) sle3).setNext((SLelement<String>) sle2);
		((SLelement<String>) sle3).setNext((SLelement<String>) sle1);

		assertTrue("Invalid JSON on linked SLelement", JSONValidator.isValidJSON(sle4.toString()));
		assertTrue("Invalid JSON on linked SLelement", JSONValidator.isValidJSON(sle3.toString()));		
	}

	
}
