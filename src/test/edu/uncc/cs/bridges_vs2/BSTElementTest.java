package edu.uncc.cs.bridges_vs2;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.simple.parser.JSONParser;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.ADTVisualizer;
import edu.uncc.cs.bridgesV2.base.BSTElement;
import edu.uncc.cs.bridgesV2.base.DLelement;
import edu.uncc.cs.bridgesV2.base.Element;
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

}
