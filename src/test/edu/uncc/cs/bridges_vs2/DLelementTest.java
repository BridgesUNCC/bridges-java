

import static org.junit.Assert.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.DLelement;
import edu.uncc.cs.bridgesV2.base.Element;
import edu.uncc.cs.bridgesV2.base.ElementVisualizer;
import edu.uncc.cs.bridgesV2.base.LinkVisualizer;
import edu.uncc.cs.bridgesV2.base.SLelement;

public class DLelementTest {
	static DLelement<String> dle0;
	static DLelement<String> dle1;
	static DLelement<String> dle2;
	static DLelement<String> dle3;
	static DLelement<String> dle4;
	static DLelement<String> dle5;
	static DLelement<String> dle6;
	static DLelement<String> dle6clone;

	static JSONParser parser;

	
	/** Setup static variables for later tests */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dle0 = new DLelement<String>();
		dle1 = new DLelement<String>("A", "a");
		dle2 = new DLelement<String>(dle1, dle0);
		dle3 = new DLelement<String>("B", "b");
		dle4 = new DLelement<String>("c", dle2, dle3);
		dle6 = new DLelement<String>("d", dle2, dle3);
		dle6clone = new DLelement<String>("d", dle2, dle3); 
				
		dle5 = new DLelement<String>();
		
		parser = new JSONParser();
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


	//TESTS FOR METHODS INHERITED FROM ELEMENT
	
	@Test
	/** Test that arrangeLable converts \n to \\n for server */
	public void testArrangeLabelConvertsNewLineToServerNewLine(){
		DLelement<String> dle = new DLelement<String>("aaaa\n aaaaa\n aaaaaa\n aaaaa\n aaaaaa\n aaaaaa\n aaaaaa aaaaaa\n aaaaaaaa aaaaaaaa\n aaaaaaaaa", "b");
		String arrangedLabel = dle.arrangeLabel(dle.getLabel(), 2);
		assertTrue("arrangeLabel() does not insert new line characters into label", arrangedLabel.contains("\\n"));
		assertFalse("arrangeLabel() does not insert new line characters into label", arrangedLabel.contains("\n"));	
	}
	
	@Test
	/** test that comparing elements is comparing the string labels of the element*/
	public void testCompareToElement(){
		assertEquals("compareTo does not return correct value when element is compared to itself", dle1.compareTo(dle1), 0);
		assertTrue("compareTo does not return correct value when smaller label element is compared to larger label element", dle1.compareTo(dle3) < 0);
		assertTrue("compareTo does not return correct value when larger label element is compared to smaller label element", dle3.compareTo(dle1) > 0);
	}
	
	@Test
	/** test that compareTo() throws NullPointerException when passed null*/
	public void testCompareToNullElementThrowsNullPointerException(){
		boolean isThrown = false;
		
		try{
			dle1.compareTo(null);
		}catch (NullPointerException e) {
			isThrown = true;
		}
		
		if(!isThrown) {
			fail("compareTo() did not throw NullPointerException when passed null");
		}
		
	}
	
	
	@Test
	/** test that equals() does not throw error if all fields are not assigned*/
	public void testEqualToElementWhenAllFieldsNotAssigned(){
		try{
			dle0.equals(dle0);
		} catch (Exception e) {
			fail("calling .equals() when all fields are not filled in throws NullPointerException");
		}
	}
	

	@Test
	/** test that checking equality between elements compares identifier, value, visualizer, and label*/
	public void testEqualToElementWhenAllFieldsAreAssigned(){
			assertTrue("equals() does not return true when compared to self", dle1.equals(dle1));
			assertFalse("equals() does not return false when compared to clone", dle6.equals((dle6clone)));
			assertFalse("equals() does not return false when compared to different element", dle6.equals((dle4)));
	}
	
	@Test
	/** test that checking equality throws NullPointerException when passed null*/
	public void testEqualToElementThrowsNullPointerExceptionWhenPassedNull(){
		boolean isThrown = false;
		
		try{
			dle1.equals(null);
		} catch (NullPointerException e) {
			isThrown = true;
		}
		
		if(!isThrown) {
			fail("equal() did not throw NullPointerException when passed null");
		}
	}

	
	@Test
	/** test that method returns correct class name*/
	public void testGetClassName(){
		assertTrue("DLelement did not return correct class name", dle4.getClassName().equals(dle4.getValue().getClass().getName()));
	}
	
	@Test
	/** test class variable used to assign DLement identifier at construction*/
	public void testIdentiferAssignmentAtConstruction() {
		int dle0ID = Integer.parseInt(dle0.getIdentifier());
		int dle1ID = Integer.parseInt(dle1.getIdentifier());
		int dle2ID = Integer.parseInt(dle2.getIdentifier());

		
		assertEquals("Identifier assigned at construction is not correct", 1,
				dle1ID - dle0ID);
		assertEquals("Identifier assigned at construction is not correct", 1,
				dle2ID - dle1ID);
	}

	@Test
	/** test that label was set correctly in the constructor*/
	public void testGetLabel(){
		assertTrue("getLabel() did not return label assigned at construction", dle1.getLabel().equals("A"));
		assertTrue("getLabel() did not return label assigned at construction", dle3.getLabel().equals("B"));
	}
	

	@Test
	/** test setting label when not passed to constructor*/
	public void testSetLabel(){
		DLelement<String> dle = new DLelement<String>("F", "f");
		dle.setLabel("G");
		assertTrue("setLabel() did not set label to new value", dle.getLabel().equals("G"));
	}
	
	
	
	@Test
	/** test that link visualizer does not return null*/
	public void testGetLinkVisualizer(){
		assertNotNull("getLinkVisualizer returned null", dle1.getLinkVisualizer((Element) dle0));
		assertTrue("getLinkVisualizer() does not return the class LinkVisualizer",  dle1.getLinkVisualizer((Element) dle0) instanceof LinkVisualizer);
	}
	


	@Test
	/** test that visualizer does not return null */
	public void testGetVisualizer(){
		assertNotNull("getVisualizer returned null", dle0.getVisualizer());
		assertTrue("getVisualizer() does not return the class Visualizer",  dle0.getVisualizer() instanceof ElementVisualizer);
	}
	

	@Test
	/** test that getValue returns value passed to constructor */
	public void testGetValue(){
		assertTrue("get value did not return correct value", dle1.getValue().equals("a"));
		assertTrue("get value did not return correct value", dle3.getValue().equals("b"));
	}
	
	@Test
	/** test that getRepresentation returns a valid json */
	public void testGetRepresentationReturnsJSON(){
		try {
			parser.parse(dle4.getRepresentation());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getRepresentation did not return a valid json");
		}
	}
	
	

	
	
}
