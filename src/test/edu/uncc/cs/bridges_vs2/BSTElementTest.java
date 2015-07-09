

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.ADTVisualizer;
import edu.uncc.cs.bridgesV2.base.BSTElement;
import edu.uncc.cs.bridgesV2.base.DLelement;
import edu.uncc.cs.bridgesV2.base.Element;
import edu.uncc.cs.bridgesV2.base.ElementVisualizer;
import edu.uncc.cs.bridgesV2.base.LinkVisualizer;
import edu.uncc.cs.bridgesV2.base.SLelement;
import edu.uncc.cs.bridgesV2.base.TreeElement;

public class BSTElementTest {
	static BSTElement<Integer, String> bst0;
	static BSTElement<Integer, String> bst1;
	static BSTElement<Integer, String> bst2;
	static BSTElement<Integer, String> bst3;
	static BSTElement<Integer, String> bst4;
	static BSTElement<Integer, String> bst5;
	static BSTElement<Integer, String> bst6;
	static BSTElement<Integer, String> bst7;
	static BSTElement<Integer, String> bst8;
	static BSTElement<Integer, String> bst9;
	static BSTElement<Integer, String> bst10;
	static BSTElement<Integer, String> bstFull1;
	static BSTElement<Integer, String> bstFull2;
	static BSTElement<Integer, String> bstFull2clone;


	static JSONParser parser;

	
	/** Set up static elements to for later tests. */
	@BeforeClass
	public static void BeforeClass() {
		bst0 = new BSTElement<Integer, String>();
		bst1 = new BSTElement<Integer, String>("a");
		bst2 = new BSTElement<Integer, String>("b");
		bst3 = new BSTElement<Integer, String>(bst1, bst2);
		bst4 = new BSTElement<Integer, String>(1, "c");
		bst5 = new BSTElement<Integer, String>("d", bst1, bst2);
		bst6 = new BSTElement<Integer, String>("label", 2, "f");
		bst7 = new BSTElement<Integer, String>(3, "g", bst1, bst2);
		bst8 = new BSTElement<Integer, String>("label1", "c");
		bst9 = new BSTElement<Integer, String>("label2", "d");
		bst10 = new BSTElement<Integer, String>("label2", "d");
		
		bstFull1 = new BSTElement<Integer, String>(1, "a", bst1, bst2);
		bstFull1.setLabel("A");
		bstFull1.setValue("a");

		bstFull2 = new BSTElement<Integer, String>(1, "a", bst1, bst3);
		bstFull2.setLabel("A");
		bstFull2.setValue("b");

		bstFull2clone = new BSTElement<Integer, String>(1, "a", bst1, bst3);
		bstFull2clone.setLabel("A");
		bstFull2clone.setValue("b");

		
		parser = new JSONParser();
	}
	
	/** Test getKey() returns key passed to constructor */
	@Test
	public void testGetKey() {
		assertEquals("Null key passed to constructor did not return null", null, bst0.getKey());
		assertEquals("Null key passed to constructor did not return null", null, bst1.getKey());
		assertEquals("Null key passed to constructor did not return null", null, bst3.getKey());
		assertTrue("Valid integer key passed to constructor did not return same key", 1 == bst4.getKey());
		assertEquals("Null key passed to constructor did not return null", null, bst5.getKey());
		assertTrue("Valid integer key passed to constructor did not return same key", 2 == bst6.getKey());
		assertTrue("Valid integer key passed to constructor did not return same key", 3 == bst7.getKey());
	}

	/** Test setKey() sets a new key by calling setKey() and getKey() */
	@Test
	public void testSetKey() {
		bst2.setKey(33);
		assertTrue("getKey() did not return same value as setKey()", 33 == bst2.getKey());
	}

	/** Test getLeft() returns BSTElement at left child */
	@Test
	public void testGetLeft() {
		assertSame("getLeft() did not return same object as left child",  bst1, bst3.getLeft());
		
	}

	/** Test getRight() returns BSTElement at right child */
	@Test
	public void testGetRight() {
		assertSame("getLeft() did not return same object as right child",  bst2, bst3.getRight());
	}
	
	//TESTS FOR METHODS INHERITED FROM ELEMENT
	
	@Test
	/** */
	public void testArrangeLabel(){
		BSTElement<Integer, String> bst = new BSTElement<Integer, String>("aaaa aaaaa\n aaaaaa aaaaa\n aaaaaa\n aaaaaa aaaaaa aaaaaa aaaaaaaa aaaaaaaa\n aaaaaaaaa", "d");
		String arrangedLabel = bst.arrangeLabel(bst.getLabel(), 2);
		
		assertTrue("arrangeLabel() does not insert \\n line characters into label", arrangedLabel.contains("\\n"));
		assertFalse("arrangeLabel() does not replace \n line characters into label", arrangedLabel.contains("\n"));

	}
	
	@Test
	/** test that comparing elements is comparing the string labels of the element*/
	public void testCompareToElement(){

		assertEquals("compareTo does not return correct value when element is compared to itself", bst8.compareTo(bst8), 0);
		assertTrue("compareTo does not return correct value when smaller label element is compared to larger label element", bst8.compareTo(bst9) < 0);
		assertTrue("compareTo does not return correct value when larger label element is compared to smaller label element", bst9.compareTo(bst8) > 0);
	}
	
	
	@Test
	/** test that checking equality between elements compares identifier, value, visualizer, and label*/
	public void testEqualToElement(){
		assertTrue("equalTo does not return correct value when comparing element to itself", bst8.equals(bst8));
		assertFalse("equalTo does not return correct value when comparing element to a different element", bst8.equals(bst9));
	}
	
	
	
	@Test
	/** test that method returns correct class name*/
	public void testGetClassName(){
		assertTrue("BSTElement did not return correct class name", bst8.getClassName().equals(bst8.getValue().getClass().getName()));
	}
	
	@Test
	/** test class variable used to assign BSTElement identifier at construction*/
	public void testIdentiferAssignmentAtConstruction() {
		int bst0ID = Integer.parseInt(bst0.getIdentifier());
		int bst1ID = Integer.parseInt(bst1.getIdentifier());
		int bst2ID = Integer.parseInt(bst2.getIdentifier());

		
		assertEquals("Identifier assigned at construction is not correct", 1,
				bst1ID - bst0ID);
		assertEquals("Identifier assigned at construction is not correct", 1,
				bst2ID - bst1ID);
	}

	@Test
	/** test that label was set correctly in the constructor*/
	public void testGetLabel(){
		assertTrue("getLabel() did not return label assigned at construction", bst6.getLabel().equals("label"));
		assertTrue("getLabel() did not return label assigned at construction", bst8.getLabel().equals("label1"));
		assertTrue("getLabel() did not return label assigned at construction", bst9.getLabel().equals("label2"));
	}
	

	@Test
	/** test setting label when not passed to constructor*/
	public void testSetLabel(){
		BSTElement<Integer, String> bst = new BSTElement<Integer, String>("A", "d");

		bst.setLabel("G");
		assertTrue("setLabel() did not set label to new value", bst.getLabel().equals("G"));
	}
	
	
	
	@Test
	/** test that link visualizer does not return null*/
	public void testGetLinkVisualizer(){
		assertNotNull("getLinkVisualizer returned null", bst0.getLinkVisualizer((Element) bst0));
		assertTrue("getLinkVisualizer() does not return the class LinkVisualizer",  bst1.getLinkVisualizer((Element) bst0) instanceof LinkVisualizer);
	}
	


	@Test
	/** test that visualizer does not return null */
	public void testGetVisualizer(){
		assertNotNull("getVisualizer returned null", bst0.getVisualizer());
		assertTrue("getVisualizer() does not return the class Visualizer",  bst0.getVisualizer() instanceof ElementVisualizer);
	}
	

	@Test
	/** test that getValue returns value passed to constructor */
	public void testGetValue(){
		assertTrue("get value did not return correct value", bst1.getValue().equals("a"));
		assertTrue("get value did not return correct value", bst4.getValue().equals("c"));
	}
	
	@Test
	/** test that setValue throws error when sent null parameter */
	public void testSetValueToNullThrowsError(){
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		bst0.setValue(null);

		System.setErr(old_out);

		assertTrue("setValue(null) did not output a NullPointerException to System.err",
				bytes.size() != 0);


	}
	
	
	
	
	@Test
	/** test that getRepresentation returns a valid json */
	public void testGetRepresentationReturnsJSON(){
		try {
			parser.parse(bst0.getRepresentation());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getRepresentation did not return a valid json");
		}
	}
	
	@Test
	/** test BST returns true if identifier, key, label, left, right, value and visualizer match */
	public void testBSTCompareWhenAllBSTFieldsAreNotFilledIn(){
		try{
			bst6.compare(bst6);			
		} catch (NullPointerException e) {
			
			fail("BSTElement compare threw null pointer exception b/c of null fields. Funciton wants all fields to have a value");
		}
	}
	
	@Test
	/** test BST returns true if identifier, key, label, left, right, value and visualizer match */
	public void testBSTCompareWhenAllBSTFieldsAreFilledIn(){
		assertFalse("BSTElement compare did not return false when compared with different object", bstFull1.compare(bstFull2));
		assertFalse("BSTElement compare did not return false when compared with different object that only has different identifier", bstFull2clone.compare(bstFull2));		
	}
		
	@Test
	/** test BST returns true if identifier, key, label, left, right, value and visualizer match */
	public void testBSTCompareWhenAllBSTFieldsAreFilledInAndSameObject(){
		assertTrue("BSTElement compare did not return true when object compared to self", bstFull1.compare(bstFull1));
	}
	


}
