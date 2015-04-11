package edu.uncc.cs.bridges_vs2;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.uncc.cs.bridgesV2.base.*;

public class ElementTest {
	static Element<?> e0;
	static Element<?> e1;
	static Element<?> e2;
	static Element<?> e1Cloned;

	/**
	 * Setup static variables for use in later tests
	 */
	@BeforeClass
	public static void BeforeClass() {
		e0 = new Element<String>("A");
		e1 = new Element<String>("A", "a");
		e2 = new Element<String>("B", "b");
		e1Cloned = new Element<String>((Element) e1);
	}

	// Constructor tests
	/**
	 * Test null constructor prints stacktrace and does not throw error.
	 */
	@Test
	public void testNullStringPassedToConstructor() {

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		Element<String> e = new Element<String>((String) null);

		System.setErr(old_out);

		assertTrue("null constructor did not print stacktrace",
				bytes.size() != 0);
	}

	/**
	 * Test exception thrown at when null label passed to constructor. Note,
	 * this error will automatically print the stack trace to the console
	 */
	@Test(expected = NullPointerException.class)
	public void testNullStringPassedToLabelStringConstructor() {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		Element<String> e = new Element<String>(null, null);

		System.setErr(old_out);

		assertTrue("null label did not print stacktrace", bytes.size() != 0);
	}

	/**
	 * test exception thrown at null constructor. Note, this error will
	 * automatically print the stack trace to the console
	 */
	@Test(expected = NullPointerException.class)
	public void testNullElementPassedToConstructor() {
		Element<String> e = new Element<String>((Element<String>) null);
	}

	// Method Tests
	@Test
	/** Test compare(Element<E> e1) when same label*/
	public void testCompareWithSameLabel() {
		Element<String> e1 = new Element<String>("A", "a");
		Element<String> e2 = new Element<String>("A", "b");

		// e1 constructed first, should come first
		assertEquals("compare() should return 0 for elements with same label",
				0, e1.compareTo(e2));
	}

	@Test
	/** Test compare(Element<E> e1) when different labels */
	public void testCompareWithDifferentLabel() {
		Element<String> e1 = new Element<String>("A", "a");
		Element<String> e2 = new Element<String>("C", "c");
		Element<String> e3 = new Element<String>("B", "b");

		assertEquals(
				"compare() should return -1 for elements with lesser label",
				-2, e1.compareTo(e2));
		assertEquals(
				"compare() should return 1 for elements with greater label", 1,
				e3.compareTo(e1));

	}

	@Test
	/** Test getClassName() returns correct class name */
	public void testGetClassName() {
		assertEquals("Element does not have correct class name", e0.getValue()
				.getClass().getName(), e0.getClassName());
		assertEquals("Element does not have correct class name", e1.getValue()
				.getClass().getName(), e1.getClassName());
		assertEquals("Cloned Element does not have correct class name",
				e1Cloned.getValue().getClass().getName(),
				e1Cloned.getClassName());
	}

	@Test
	/** test class variable used to assign identifier at construction*/
	public void testIdentiferAssignmentAtConstruction() {
		int e0ID = Integer.parseInt(e0.getIdentifier());
		int e1ID = Integer.parseInt(e1.getIdentifier());
		int e2ID = Integer.parseInt(e2.getIdentifier());

		assertEquals("Identifier assigned at construction is not correct", 1,
				e2ID - e1ID);
		assertEquals("Identifier assigned at construction is not correct", 1,
				e2ID - e1ID);
		assertTrue("Identifier assigned at cloned construction is not correct",
				e1.getIdentifier().equals(e1Cloned.getIdentifier()));
	}

	@Test
	/** test getLabel() returns correct value*/
	public void testGetLabel() {
		assertEquals("getLabel() did not return the correct value", "",
				e0.getLabel());
		assertEquals("getLabel() did not return the correct value", "A",
				e1.getLabel());
		assertEquals(
				"getLabel() did not return the correct value for cloned element",
				e1.getLabel(), e1Cloned.getLabel());
	}

	@Test
	/** test getRepresentation() is a valid json construction */
	public void testgetRepresentationIsValidJSON() {
		assertTrue("Invalid JSON created in element",
				JSONValidator.isValidJSON(e0.getRepresentation()));
		assertTrue("Invalid JSON created in element",
				JSONValidator.isValidJSON(e1.getRepresentation()));
		assertTrue("Invalid JSON created in cloned element",
				JSONValidator.isValidJSON(e1Cloned.getRepresentation()));
	}

	@Test
	/** test toString() returns valid JSON */
	public void testToString() {
		assertTrue("Invalid JSON created in element",
				JSONValidator.isValidJSON(e0.toString()));
		assertTrue("Invalid JSON created in element",
				JSONValidator.isValidJSON(e1.toString()));
		assertTrue("Invalid JSON created in cloned element",
				JSONValidator.isValidJSON(e1Cloned.toString()));
	}

	@Test
	/** test getValue() returns correct value */
	public void testGetValue() {
		assertEquals("getValue() did not return the correct value", "A",
				e0.getValue());
		assertEquals("getValue() did not return the correct value", "a",
				e1.getValue());
		assertEquals(
				"getValue() did not return the correct value cloned element",
				e1.getValue(), e1Cloned.getValue());
	}

	@Test
	/** test getVisualizer returns a visualizer */
	public void testGetVisualizer() {
		assertTrue(
				"getVisualizer() does not return an instance of ElementVisualizer",
				e0.getVisualizer() instanceof ElementVisualizer);
		assertTrue(
				"getVisualizer() does not return an instance of ElementVisualizer",
				e1.getVisualizer() instanceof ElementVisualizer);
		assertTrue(
				"getVisualizer() does not return an instance of ElementVisualizer",
				e1Cloned.getVisualizer() instanceof ElementVisualizer);

		assertNotNull("getVisualizer() returns null", e0.getVisualizer());
		assertNotNull("getVisualizer() returns null", e1.getVisualizer());
		assertNotNull("getVisualizer() returns null", e1Cloned.getVisualizer());
	}

	@Test
	/** test setValue() and make sure getValue() returns same */
	public void testSetValue() {
		Element<String> e = new Element<String>("A", "a");
		e.setValue("b");
		assertEquals("setElement() did not set element correctly", "b",
				e.getValue());
	}

	/**
	 * Test stack trace is printed but error not thrown when null is passed to
	 * setValue()
	 */
	@Test
	public void testSetValueToNullThrowsNullPointerException() {

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		Element<String> e = new Element<String>((String) null);
		e.setValue(null);

		System.setErr(old_out);

		assertTrue(
				"stacktrace was not printed when null was passed to setElement()",
				bytes.size() != 0);
	}

	@Test
	/** Test compare(Element<E> e1) when this equals e1 */
	public void testCompareWhenDeepCopied() {
		Element<String> e1 = new Element<String>("a");
		Element<String> e2 = new Element<String>(e1);

		assertEquals(e1.compareTo(e2), 0);
	}

}
