
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.Element;
import edu.uncc.cs.bridgesV2.base.ElementVisualizer;
import edu.uncc.cs.bridgesV2.base.LinkVisualizer;
import edu.uncc.cs.bridgesV2.base.SLelement;
import edu.uncc.cs.bridgesV2.base.TreeElement;

public class TreeElementTest {
	static TreeElement<String> te0;
	static TreeElement<String> te1;
	static TreeElement<String> te2;
	static TreeElement<String> te3;
	static TreeElement<String> te4;
	static TreeElement<String> te5;
	
	static JSONParser parser;





	/** Set up static variables to bet tested later */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		te0 = new TreeElement<String>();
		te1 = new TreeElement<String>("A", "A");
		te2 = new TreeElement<String>(te0, te1);
		te3 = new TreeElement<String>("B", te0, te1);
		te4 = new TreeElement<String>();
		te5 = new TreeElement<String>("B", "B");
		
		parser = new JSONParser();
		
	}


	@Test
	/** test getLeft() */
	public void testGetLeft() {
		assertNull("getLeft() should return null", te0.getLeft());
		assertNull("getLeft() should return null", te1.getLeft());
		assertEquals("getLeft() did not return correct object", te0, te2.getLeft());
		assertEquals("getLeft() did not return correct object", te0, te3.getLeft());
	}

	@Test
	/** test setLeft() on new TreeElement */
	public void testSetLeftToNewTreeElement() {
		te4.setLeft(te0);
		assertEquals("setLeft() set left to wrong object", te0, te4.getLeft());
	}
	
	@Test
	/** test setLeft() to null */
	public void testSetLeftToNull() {
		te4.setLeft(null);
		assertNull("setLeft() did not set left to null", te4.getLeft());
	}


	@Test
	/** test getRight() */
	public void testGetRight() {
		assertNull("getRight() should return null", te0.getRight());
		assertNull("getRight() should return null", te1.getRight());
		assertEquals("getRight() did not return correct object", te1, te2.getRight());
		assertEquals("getRight() did not return correct object", te1, te3.getRight());
	}

	@Test
	/** test setRight() on new TreeElement */
	public void testSetRightToNewTreeElement() {
		te4.setRight(te0);
		assertEquals("setRight() set Right to wrong object", te0, te4.getRight());
	}
	
	@Test
	/** test setRight() to null */
	public void testSetRightToNull() {
		te4.setRight(null);
		assertNull("setRight() did not set Right to null", te4.getRight());
	}

	
	
	//TESTS FOR METHODS INHERITED FROM ELEMENT
	
	@Test
	/** Test that arrangeLable converts \n to \\n for server */
	public void testArrangeLabelConvertsNewLineToServerNewLine(){
		TreeElement<String> te = new TreeElement<String>("aaaa\n aaaaa\n aaaaaa\n aaaaa\n aaaaaa\n aaaaaa\n aaaaaa aaaaaa\n aaaaaaaa aaaaaaaa\n aaaaaaaaa", "b");
		String arrangedLabel = te.arrangeLabel(te.getLabel(), 2);
		assertTrue("arrangeLabel() does not insert new line characters into label", arrangedLabel.contains("\\n"));
		assertFalse("arrangeLabel() does not insert new line characters into label", arrangedLabel.contains("\n"));	
	}
	
	@Test
	/** test that comparing elements is comparing the string labels of the element*/
	public void testCompareToElement(){
		assertEquals("compareTo does not return correct value when element is compared to itself", te1.compareTo(te1), 0);
		assertTrue("compareTo does not return correct value when smaller label element is compared to larger label element", te1.compareTo(te5) < 0);
		assertTrue("compareTo does not return correct value when larger label element is compared to smaller label element", te5.compareTo(te1) > 0);
		assertEquals("compareTo does not return zero when two unlabeled elements are compared", te2.compareTo(te3), 0);
	}
	
	
	
	@Test
	/** test that checking equality between elements compares identifier, value, visualizer, and label*/
	public void testEqualToElement(){
	
		assertTrue("equalTo does not return correct value when comparing element to itself", te1.equals(te1));
		assertFalse("equalTo does not return correct value when comparing element to a different element", te1.equals(te3));		
	}
	
	
	
	@Test
	/** test that method returns correct class name*/
	public void testGetClassName(){
		assertTrue("TreeElement did not return correct class name", te1.getClassName().equals(te1.getValue().getClass().getName()));
	}
	
	@Test
	/** test class variable used to assign TreeELement identifier at construction*/
	public void testIdentiferAssignmentAtConstruction() {
		int te0ID = Integer.parseInt(te0.getIdentifier());
		int te1ID = Integer.parseInt(te1.getIdentifier());
		int te2ID = Integer.parseInt(te2.getIdentifier());

		
		assertEquals("Identifier assigned at construction is not correct", 1,
				te1ID - te0ID);
		assertEquals("Identifier assigned at construction is not correct", 1,
				te2ID - te1ID);
	}

	@Test
	/** test that label was set correctly in the constructor*/
	public void testGetLabel(){
		assertTrue("getLabel() did not return label assigned at construction", te1.getLabel().equals("A"));
		assertTrue("getLabel() did not return label assigned at construction", te3.getLabel().equals(""));	}
	

	@Test
	/** test setting label when not passed to constructor*/
	public void testSetLabel(){
		TreeElement<String> te = new TreeElement<String>("F");
		te.setLabel("G");
		assertTrue("setLabel() did not set label to new value", te.getLabel().equals("G"));
	}
	
	
	
	@Test
	/** test that link visualizer does not return null*/
	public void testGetLinkVisualizer(){
		assertNotNull("getLinkVisualizer returned null", te0.getLinkVisualizer((Element) te0));
		assertTrue("getLinkVisualizer() does not return the class LinkVisualizer",  te1.getLinkVisualizer((Element) te0) instanceof LinkVisualizer);
	}
	


	@Test
	/** test that visualizer does not return null */
	public void testGetVisualizer(){
		assertNotNull("getVisualizer returned null", te0.getVisualizer());
		assertTrue("getVisualizer() does not return the class Visualizer",  te0.getVisualizer() instanceof ElementVisualizer);
	}
	

	@Test
	/** test that getValue returns value passed to constructor */
	public void testGetValue(){
		assertNull("TreeElement.getValue() did not return null for value.", te0.getValue());
		assertTrue("TreeElement.getValue() did not return correct value.", te1.getValue().equals("A"));
		assertNull("TreeElement.getValue() did not return null for value.", te2.getValue());
		assertNull("TreeElement.getValue() did not return null for value.", te3.getValue());

	}
	
	@Test
	/** test that setValue throws error when sent null parameter */
	public void testSetValueToNullThrowsError(){
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		te0.setValue(null);

		System.setErr(old_out);

		assertTrue("setValue(null) did not output a NullPointerException to System.err",
				bytes.size() != 0);
	}
	
	
	
	@Test
	/** test that getRepresentation returns a valid json */
	public void testGetRepresentationReturnsJSON(){
		try {
			parser.parse(te0.getRepresentation());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getRepresentation did not return a valid json");
		}
	}
	
	
}
