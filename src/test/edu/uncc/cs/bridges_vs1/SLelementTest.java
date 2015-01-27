package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.uncc.cs.bridges_vs1.structure.SLelement;

public class SLelementTest {

	@Test
	/** test toString() method */
	public void testToString() {
		SLelement<String> sle1 = new SLelement<String>("A", "a");
		assertNotNull(sle1.toString());
	}

	@Test
	/** SLelement increments a Class variable on instantiation to track the identifier.
	 * Test to make sure this identifier is incrementing correctly.
	 */
	public void testSLelementIdentifierClassVariableOnNewCreation() {
		SLelement<String> sle1 = new SLelement<String>("z", "z");
		SLelement<String> sle2 = new SLelement<String>("B", sle1);
		SLelement<String> sle3 = new SLelement<String>("C", "d");


		assertNotEquals(sle1.getIdentifier(), sle2.getIdentifier());
		assertNotEquals(sle2.getIdentifier(), sle3.getIdentifier());
		assertNotEquals(sle1.getIdentifier(), sle3.getIdentifier());
	}

	
	@Test
	/** Test SLelement<E>(SLelement<E> original) constructor 
	 * which should deep copy the original without incrementing class counter
	 */
	public void testSLelementStringEfromUnlinkedOriginal() {
		SLelement<String> sle1 = new SLelement<String>("a", "b");		
		SLelement<String> sle2 = new SLelement<String>(sle1);
		
		assertEquals(sle1.getIdentifier(), sle2.getIdentifier());
		assertEquals("b", sle2.getValue());
		assertNull(sle2.getNext());
	}
	
	@Test
	/** Test SLelement<E>(SLelement<E> original) constructor when setNext() has been called 
	 * which should deep copy the original without incrementing class counter
	 */
	public void testSLelementStringEfromLinkedOriginal() {
		SLelement<String> sle1 = new SLelement<String>("a", "b");
		SLelement<String> sle2 = new SLelement<String>("c", "d");

		sle1.setNext(sle2);
		
		SLelement<String> sle3 = new SLelement<String>(sle1);
		
		assertEquals(sle1.getIdentifier(), sle3.getIdentifier());
		assertEquals("b", sle3.getValue());
		assertEquals(sle2, sle3.getNext());
	}

	
	@Test
	/** Test SLelement<E>(E e, SLelement<E> next) constructor */
	public void testSLelementESLelementOfE() {
		SLelement<String> sle1 = new SLelement<String>("A", "a");
		SLelement<String> sle2 = new SLelement<String>("B", sle1);
		
		assertNotNull(sle2);
		assertEquals(sle2.getNext(), sle1);
		assertNotEquals(sle1.getIdentifier(), sle2.getIdentifier());
		assertEquals(sle2.getValue(), "B");
	}


	@Test
	/** test setNext() and getNext() methods */
	public void testGetandSetNext() {
		SLelement<String> sle1 = new SLelement<String>("A", "a");
		
		SLelement<String> sle2 = new SLelement<String>("B", "b");
		
		sle1.setNext(sle2);
		
		assertEquals(sle2, sle1.getNext());
	}

	
	@Test
	/** test toClone() method */
	public void testCloneDeepCopiesInformation() throws CloneNotSupportedException {
		SLelement<String> sle1 = new SLelement<String>("k", "l");
		
		SLelement<String> sle2 = sle1.clone();

		assertEquals(sle1.getIdentifier(), sle2.getIdentifier());
		assertEquals(sle1.getValue(), sle2.getValue());
		assertNotEquals(sle1, sle2);
	}
	
}
