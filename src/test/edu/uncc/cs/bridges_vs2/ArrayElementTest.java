
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import bridges_v21.base.*;

public class ArrayElementTest {
	static ArrayElement<String> a0;
	static ArrayElement<String> a1;
	static ArrayElement<String> a2;
	
	static JSONParser parser;


	
	/** Set up static elements to for later tests. */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		a0 = new ArrayElement<String>("A", "a");
		a1 = new ArrayElement<String>("B", "b");
		a2 = new ArrayElement<String>("C", "c");
		
		parser = new JSONParser();
		
	}
	
	/** Tests whether constructor is working properly */
	@Test
	public void testArrayElement() {
		assertNotNull(a0);
		assertEquals("a", a0.getValue());
	}

	
	//TESTS FOR METHODS INHERITED FROM ELEMENT
	
	@Test
	/** */
	public void testArrangeLabel(){
		ArrayElement<String> a = new ArrayElement<String>("aaaa aaaaa\n aaaaaa aaaaa\n aaaaaa aaaaaa aaaaaa\n aaaaaa aaaaaaaa aaaaaaaa aaaaaaaaa", "b");
		String arrangedLabel = a.arrangeLabel(a.getLabel(), 2);
		
		
		assertTrue("arrangeLabel() does not insert new line characters into label", arrangedLabel.contains("\\n"));
		assertFalse("arrangeLabel() does not replace \n characters into label", arrangedLabel.contains("\n"));

	}
	
	@Test
	/** test that comparing elements is comparing the string labels of the element*/
	public void testCompareToElement(){
		assertEquals("compareTo does not return correct value when element is compared to itself", a0.compareTo(a0), 0);
		assertTrue("compareTo does not return correct value when smaller label element is compared to larger label element", a0.compareTo(a1) < 0);
		assertTrue("compareTo does not return correct value when larger label element is compared to smaller label element", a1.compareTo(a0) > 0);
	}
	
	
	
	@Test
	/** test that checking equality between elements compares identifier, value, visualizer, and label*/
	public void testEqualToElement(){
		assertTrue("equalTo does not return correct value when comparing element to itself", a0.equals(a0));
		assertFalse("equalTo does not return correct value when comparing element to a different element", a0.equals(a1));
	}
	
	
	
	@Test
	/** test that method returns correct class name*/
	public void testGetClassName(){
		assertTrue("ArrayElement did not return correct class name", a1.getClassName().equals(a1.getValue().getClass().getName()));
	}
	
	@Test
	/** test class variable used to assign SLement identifier at construction*/
	public void testIdentiferAssignmentAtConstruction() {
		int a0ID = Integer.parseInt(a0.getIdentifier());
		int a1ID = Integer.parseInt(a1.getIdentifier());
		int a2ID = Integer.parseInt(a2.getIdentifier());

		
		assertEquals("Identifier assigned at construction is not correct", 1,
				a1ID - a0ID);
		assertEquals("Identifier assigned at construction is not correct", 1,
				a2ID - a1ID);
	}

	@Test
	/** test that label was set correctly in the constructor*/
	public void testGetLabel(){
		assertTrue("getLabel() did not return label assigned at construction", a0.getLabel().equals("A"));
	}
	

	@Test
	/** test setting label when not passed to constructor*/
	public void testSetLabel(){
		ArrayElement<String> a = new ArrayElement<String>("A", "a");
		a.setLabel("G");
		assertTrue("setLabel() did not set label to new value", a.getLabel().equals("G"));
	}
	
	
	
	@Test
	/** test that link visualizer does not return null*/
	public void testGetLinkVisualizer(){
		assertNotNull("getLinkVisualizer returned null", a1.getLinkVisualizer((Element) a0));
		assertTrue("getLinkVisualizer() does not return the class LinkVisualizer",  a1.getLinkVisualizer((Element) a0) instanceof LinkVisualizer);
	}
	


	@Test
	/** test that visualizer does not return null */
	public void testGetVisualizer(){
		assertNotNull("getVisualizer returned null", a0.getVisualizer());
		assertTrue("getVisualizer() does not return the class Visualizer",  a0.getVisualizer() instanceof ElementVisualizer);
	}
	

	@Test
	/** test that getValue returns value passed to constructor */
	public void testGetValue(){
		assertTrue("get value did not return correct value", a0.getValue().equals("a"));
	}
	
	@Test
	/** test that setValue throws error when sent null parameter */
	public void testSetValueToNullThrowsError(){
		ArrayElement<String> a = new ArrayElement<String>("A", "a");

		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		a.setValue(null);

		System.setErr(old_out);

		assertTrue("setValue(null) did not output a NullPointerException to System.err",
				bytes.size() != 0);


	}
	
	
	
	
	@Test
	/** test that getRepresentation returns a valid json */
	public void testGetRepresentationReturnsJSON(){
		try {
			parser.parse(a0.getRepresentation());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getRepresentation did not return a valid json");
		}
	}
	
	

	
}
