import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import bridges_v21.base.Element;
import bridges_v21.base.ElementVisualizer;
import bridges_v21.base.LinkVisualizer;
import bridges_v21.base.SLelement;


public class ElementTest {
	
	static Element<String> e0;
	static Element<String> e1;
	static Element<String> e2;
	static Element<String> e3;
	static Element<String> e4;
	static Element<String> e2copy;


	
	
	/** Setup static variables for use in later tests. */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		e0 = new Element<String>();
		e1 = new Element<String>("a");
		e2 = new Element<String>("A", "a");
		e3 = new Element<String>("b", "b");
		e4 = new Element<String>("C", "c");
		e2copy = new Element<String>(e2);	

	}
	
	
	@Test
	/** test whether getClassName returns the name of the class that Element is holding **/
	public void testGetClassNameReturnsClassOfGeneric() {
		assertTrue("getClassName did not return String when String held as generic", e2.getClassName().equals("java.lang.String"));
	}
	
	@Test
	/** test whether getClassName returns the label of the element **/
	public void testGetLabelReturnsCorrectLabel() {
		assertTrue("getClassName did not return the correct label", e2.getLabel().equals("A"));
	}
	
	
	@Test
	/** test whether compareTo returns correct integer */
	public void testCompareToCorrectlyComparesElementLabels(){
		int e3compe2 = e3.getLabel().compareTo(e2.getLabel());
		int e3compe4 = e3.getLabel().compareTo(e4.getLabel());
		
		assertTrue("e3.compareTo(e2) did not return same value as comparing labels", e3.compareTo(e2) == e3compe2);
		assertTrue("e3.compareTo(e4) did not return same value as comparing labels", e3.compareTo(e4) == e3compe4);
		assertEquals("label of element to copy of element did not return 0", e2.compareTo(e2copy), 0);
	}
	
	@Test
	/** test that trying to compare to null throws error */
	public void TestCompareToNullThrowsError(){
		boolean threwError = false;
		Element<String> el;
		el = null;
		
		try{
			e1.compareTo(el);
		} catch (Exception e) {
			threwError = true;
		}
		
		if(!threwError) {
			fail("compare to null did not throw error");
		}
	}
	
	@Test
	/** test that equals(null) throws error */
	public void TestEqualToNullThrowsError(){
		boolean threwError = false;
		
		try{
			e1.equals(null);
		} catch (Exception e) {
			threwError = true;
		}
		
		if(!threwError) {
			fail("compare to null did not throw error");
		}
	}
	
	
	@Test
	/** test whether getIdentifier returns different values, even for cloned items */
	public void testGetIdentifierReturnsDifferentValuesForDifferentElements(){
		assertTrue("different elements have same identifiers", !e2.getIdentifier().equals(e3.getIdentifier()));
		assertTrue("deep copied elements have same identifiers", !e2.getIdentifier().equals(e2copy.getIdentifier()));
	}
	
	
	@Test
	/**test that getRepresentation returns valid JSON */
	public void testGetRepresentationReturnsValidJSON(){
		JSONParser p = new JSONParser();
		try {
			p.parse(e3.getRepresentation());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getRepresentation did not return valid JSON");
		}
	}
	
	@Test
	/** test getValue returns value entered when constructed */
	public void testGetValueReturnsCorrectValueFromConstructor() {
		assertTrue("getValue did not return correct value", e1.getValue().equals("a"));
		assertTrue("getValue did not return correct value", e3.getValue().equals("b"));
		assertTrue("getValue did not return correct value", e0.getValue() == null);
	}
	
	@Test
	/** test setValue correctly sets element to new value */
	public void testSetValueCorrectlySetsElementToNewValue(){
		Element<Integer> e5 = new Element<Integer>();
		Element<Integer> e6 = new Element<Integer>(3);

		e5.setValue(10);
		e6.setValue(20);
		
		assertTrue("setValue did not change value", e5.getValue() == 10);
		assertTrue("setValue did not change value", e6.getValue() == 20);
	}
	
	@Test
	/** test equals only evaluates to true when exactly the same (i.e. not cloned)*/
	public void testEqualsReturnsTrueForSameElement(){
		Element<String> e7 = e1;
			
		assertTrue("equals() did not return true for same element", e7.equals(e1));
		assertTrue("equals() returned true for different elements", !e1.equals(e2));
		assertTrue("equals() returned false for cloned elements", !e2.equals(e2copy));
	}
	
	
	@Test
	/** test toString does not return null */
	public void testToStringDoesNotReturnNull(){
		assertTrue("toString returned null", e1.toString() != null);
	}
	
	@Test
	/** test toString names Element class correctly */
	public void testToStringContainsElementClass(){
		assertTrue("toString returned null", e1.toString().contains("Element"));
	}
	
	@Test
	/** test that creating more than 1000 elements sends error message */
	public void testCreating1000ElementsPrintsError(){
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		Element<String> last = new Element<String>();
		
		for (int i = 0; i < 1010; i++){
			Element<String> e;

			e = new Element<String>();
			last = e;
		}
		
		Element<String> e2 = new Element<String>(last);
		
		System.setErr(old_out);

		assertTrue("creatign more than 1000 elements did not send error to System.err",
				bytes.size() != 0);
	}

	
	@Test
	/** test constructor with Null throws error */
	public void testConstructorWithNullThrowsError(){
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		Element<String> e = new Element<String>((String) null);
		
		System.setErr(old_out);

		assertTrue("constructor with null did not throw error",
				bytes.size() != 0);
	}
	
	
	
	
	@Test
	/** test getLinkVisualizer returns value */
	public void testLinkVisualizerReturnsValue(){
		assertTrue("getLinkVisualizer returned null", e0.getLinkVisualizer(e0) != null);
		assertTrue("getLinkVisualizer() does not return the class LinkVisualizer",  e0.getLinkVisualizer(e0) instanceof LinkVisualizer);
	}

	@Test
	/** test getVisualizer returns value */
	public void testVisualizerReturnsValue(){
		assertTrue("getVisualizer returned null", e0.getVisualizer() != null);
		assertTrue("getVisualizer() does not return the class LinkVisualizer",  e0.getVisualizer() instanceof ElementVisualizer);
	}

	
	@Test
	/** test arrange label correctly changes string */
	public void testArrangeLabel(){
		Element<String> e = new SLelement<String>("aaaa\n aaaaa\n aaaaaa\n aaaaa\n aaaaaa\n aaaaaa\n aaaaaa aaaaaa\n aaaaaaaa aaaaaaaa\n aaaaaaaaa", "b");
		String arrangedLabel = e.arrangeLabel(e.getLabel(), 2);
		assertTrue("arrangeLabel() does not insert new line characters into label", arrangedLabel.contains("\\n"));
		assertFalse("arrangeLabel() does not insert new line characters into label", arrangedLabel.contains("\n"));
	}

	
	@Test
	public void test(){
	}
	
	

}
