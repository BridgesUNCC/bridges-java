

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.*;

public class SLelementTest {
	static SLelement<String> sle1;
	static SLelement<String> sle2;
	static SLelement<String> sle3;
	static SLelement<String> sle4;
	static SLelement<String> sle0;
	static SLelement<String> sle5clone0;

	static JSONParser parser;

	/**
	 * use each constructor to make an element
	 * 
	 * @throws CloneNotSupportedException
	 */
	@BeforeClass
	public static void BeforeClass() throws CloneNotSupportedException {
		sle0 = new SLelement<String>("A", "a");
		sle1 = new SLelement<String>("B", (SLelement<String>) sle0);
		sle2 = new SLelement<String>((SLelement<String>) sle1);
		sle3 = new SLelement<String>("D", "d");
		sle4 = new SLelement<String>("E", "e");
		sle5clone0 = sle0.clone();
		parser = new JSONParser();
	}

	
	
	
	
	@Test
	/** test GetNext() returns same value as was passed to constructor */
	public void testGetNext() {
		assertNull("Expected null, getNext() did not return null",
				sle0.getNext());
		assertEquals("Unexpected value returned from getNext()", sle0,
				sle1.getNext());
		assertEquals("Unexpected value returned from getNext()", sle1,
				sle2.getNext());
	}

	@Test
	/** Test setNext() to new value and getNext() returns new value */
	public void testSetNext() {
		// setNext to new value
		((SLelement<String>) sle4).setNext((SLelement<String>) sle3);
		assertEquals("setNext() does not set pointer to correct object", sle3,
				sle4.getNext());

		// setNext to Null
		sle4.setNext(null);
		assertNull("setNext() to null did not set pointer to null",
				sle4.getNext());
	}

	

	/** test that cloned object is different object from original */
	@Test
	public void testCloneIsDifferentObject() throws CloneNotSupportedException {
		assertFalse("clone did not create a new object", sle5clone0 == sle0);
		assertEquals(sle5clone0.getIdentifier(), sle0.getIdentifier());
		assertEquals(sle5clone0.getLabel(), sle0.getLabel());
		assertEquals(sle5clone0.getValue(), sle0.getValue());

	}
	
	//TESTS FOR METHODS INHERITED FROM ELEMENT
	
	@Test
	/** Test that arrangeLable converts \n to \\n for server */
	public void testArrangeLabelConvertsNewLineToServerNewLine(){
		SLelement<String> sle = new SLelement<String>("aaaa\n aaaaa\n aaaaaa\n aaaaa\n aaaaaa\n aaaaaa\n aaaaaa aaaaaa\n aaaaaaaa aaaaaaaa\n aaaaaaaaa", "b");
		String arrangedLabel = sle.arrangeLabel(sle.getLabel(), 2);
		assertTrue("arrangeLabel() does not insert new line characters into label", arrangedLabel.contains("\\n"));
		assertFalse("arrangeLabel() does not insert new line characters into label", arrangedLabel.contains("\n"));	
	}
	
	@Test
	/** test that comparing elements is comparing the string labels of the element*/
	public void testCompareToElement(){	
		
		
		assertEquals("compareTo does not return correct value when element is compared to itself", sle0.compareTo(sle0), 0);
		assertTrue("compareTo does not return correct value when smaller label element is compared to larger label element", sle0.compareTo(sle3) < 0);
		assertTrue("compareTo does not return correct value when larger label element is compared to smaller label element", sle3.compareTo(sle0) > 0);
	}
	
	
	
	@Test
	/** test that checking equality between elements compares identifier, value, visualizer, and label*/
	public void testEqualToElement(){
		assertFalse("equalTo does not return correct value when comparing element to a different element", sle0.equals(sle1));
		assertTrue("equalTo does not return correct value when comparing element to its clone", sle0.equals(sle5clone0));
	}
	
	@Test
	/** test that checking equality throws NullPointerException when element has no label*/
	public void testEqualToElementWhenElementHasNoLabel(){
		SLelement<String> sle = new SLelement<String>(sle1);
		boolean thrown = false;
		try {
			sle.equals(sle);
		} catch (NullPointerException e) {
			thrown = true;
		}
		
		if(!thrown) {
			fail("calling equals() on element with no label did not throw NullPointerException");
		}
	}
	
	
	@Test
	/** test that method returns correct class name*/
	public void testGetClassName(){
		assertTrue("SLelement did not return correct class name", sle1.getClassName().equals(sle1.getValue().getClass().getName()));
	}
	
	@Test
	/** test class variable used to assign SLement identifier at construction*/
	public void testIdentiferAssignmentAtConstruction() {
		int sle0ID = Integer.parseInt(sle0.getIdentifier());
		int sle1ID = Integer.parseInt(sle1.getIdentifier());
		int sle2ID = Integer.parseInt(sle2.getIdentifier());

		
		assertEquals("Identifier assigned at construction is not correct", 1,
				sle1ID - sle0ID);
		assertEquals("Identifier assigned at construction is not correct", 1,
				sle2ID - sle1ID);
	}

	@Test
	/** test that label was set correctly in the constructor*/
	public void testGetLabel(){
		assertTrue("getLabel() did not return label assigned at construction", sle0.getLabel().equals("A"));
		assertTrue("getLabel() did not return label assigned at construction", sle1.getLabel().equals(""));
		assertTrue("getLabel() did not return label assigned at construction", sle3.getLabel().equals("D"));
		assertTrue("getLabel() did not return label assigned at construction", sle4.getLabel().equals("E"));
	}
	

	@Test
	/** test setting label when not passed to constructor*/
	public void testSetLabel(){
		SLelement<String> sle6 = new SLelement<String>("F", "f");
		sle6.setLabel("G");
		assertTrue("setLabel() did not set label to new value", sle6.getLabel().equals("G"));
	}
	
	
	
	@Test
	/** test that link visualizer does not return null*/
	public void testGetLinkVisualizer(){
		assertNotNull("getLinkVisualizer returned null", sle1.getLinkVisualizer((Element) sle0));
		assertTrue("getLinkVisualizer() does not return the class LinkVisualizer",  sle1.getLinkVisualizer((Element) sle0) instanceof LinkVisualizer);
	}
	


	@Test
	/** test that visualizer does not return null */
	public void testGetVisualizer(){
		assertNotNull("getVisualizer returned null", sle0.getVisualizer());
		assertTrue("getVisualizer() does not return the class Visualizer",  sle0.getVisualizer() instanceof ElementVisualizer);
	}
	

	@Test
	/** test that getValue returns value passed to constructor */
	public void testGetValue(){
		assertTrue("get value did not return correct value", sle0.getValue().equals("a"));
		assertTrue("get value did not return correct value", sle1.getValue().equals("B"));
	}
	
	@Test
	/** test that setValue throws error when sent null parameter */
	public void testSetValueToNullThrowsError(){
		
		SLelement<String> sle = new SLelement<String>("A", "A");
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		sle.setValue(null);

		System.setErr(old_out);

		assertTrue("setValue(null) did not output a NullPointerException to System.err",
				bytes.size() != 0);


	}
	
	
	
	
	@Test
	/** test that getRepresentation returns a valid json */
	public void testGetRepresentationReturnsJSON(){
		try {
			parser.parse(sle0.getRepresentation());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getRepresentation did not return a valid json");
		}
	}
	
	
	@Test
	/** test that toString returns a valid string*/
	public void testToString(){
		assertNotNull("toString() returned null", sle1.toString());
	}

	
	
}
