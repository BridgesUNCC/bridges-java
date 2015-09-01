

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import bridges_v21.base.*;


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
	
}
