package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.uncc.cs.bridges.Visualizer;
import edu.uncc.cs.bridges_vs1.structure.Element;
import edu.uncc.cs.bridges_vs1.structure.ElementVisualizer;
import edu.uncc.cs.bridges_vs1.validation.InvalidValueException;

public class ElementTest {

	@Test
	/** test toString() method */
	public void testToString() {
		Element<Integer> e = new Element<Integer>(4);

		assertNotNull(e.toString());
	}

	@Test
	/** test that Element class variable for identifier is incrementing correctly */
	public void testElementIdentifierClassVariable() {
		Element<String> e1 = new Element<String>("a");
		Element<String> e2 = new Element<String>("b");
		Element<String> e3 = new Element<String>("c");

		assertNotEquals(e1.getIdentifier(), e2.getIdentifier());
		assertNotEquals(e2.getIdentifier(), e3.getIdentifier());
		assertNotEquals(e1.getIdentifier(), e3.getIdentifier());
	}

	@Test
	/** test Element(E val) constructor */
	public void testElementE() {
		Element<String> e = new Element<String>("a");

		assertNotNull(e);
		assertEquals("a", e.getValue());
		assertEquals("a", e.getLabel());
		assertNotNull(e.getVisualizer());
		assertNotNull(e.getRepresentation());
	}

	@Test
	/** test Element(String label, E val) constructor */
	public void testElementStringE() {
		Element<String> e = new Element<String>("a", "b");

		assertEquals("b", e.getValue());
		assertEquals("a", e.getLabel());
		assertNotNull(e.getVisualizer());
		assertNotNull(e.getRepresentation());
	}

	@Test
	/** Test Element (Element<E> original) constructor performs deep copy */
	public void testElementElementOfE() {
		Element<String> e1 = new Element<String>("a");
		Element<String> e2 = new Element<String>(e1);

		assertEquals(e1.getIdentifier(), e2.getIdentifier());
		assertEquals(e1.getValue(), e2.getValue());
		assertEquals(e1.getClass(), e2.getClass());
		assertEquals(e1.getClassName(), e2.getClassName());
		assertEquals(e1.getLabel(), e2.getLabel());

		assertNotEquals(e1.getVisualizer(), e2.getVisualizer());
		assertNotEquals(e1, e2);
	}

	@Test
	/** Test getIdentifier() if setIdentifier() has not been called explicitly */
	public void testGetIdentifierWhenSetIdentifierNotCalledExplicitly() {
		Element<String> e1 = new Element<String>("a");

		assertNotNull(e1.getIdentifier());
	}

	@Test
	/** Test getIdentifier() and setIdentifier() when setIdentifier has been called explicitly */
	public void testGetIdentifierWhenSetIdentifierCalledExplicitly() {
		Element<String> e1 = new Element<String>("a");
		e1.setIdentifier("abcd");

		assertEquals("abcd", e1.getIdentifier());
	}

	@Test
	/** Test getVisualizer() */
	public void testGetVisualizer() {
		Element<String> e = new Element<String>("a");

		assertEquals(new ElementVisualizer().getClass(), e.getVisualizer()
				.getClass());
	}

	@Test
	/** Test getClassName() */
	public void testGetClassName() {
		Element<String> e = new Element<String>("a");

		assertEquals(e.getValue().getClass().getName(), e.getClassName());
	}

	@Test
	/** Test compare(Element<E> e1) when this equals e1 */
	public void testCompareWhenDeepCopied() {
		Element<String> e1 = new Element<String>("a");
		Element<String> e2 = new Element<String>(e1);

		assertEquals(e1.compare(e2), 0);
	}

	@Test
	/** Test compare(Element<E> e1) when same label, different identifier*/
	public void testCompareWhenSameLabelDifferentIdentifier() {
		Element<String> e1 = new Element<String>("A", "a");
		Element<String> e2 = new Element<String>("A", "b");

		// e1 constructed first, should come first
		assertTrue(e1.compare(e2) < 0);
		assertTrue(e2.compare(e1) > 0);
	}

	@Test
	/** Test setVisualizer() */
	public void testSetValue() {
		Element<String> e = new Element<String>("a");

		e.setValue("BCDE");

		assertEquals("BCDE", e.getValue());
	}

}
