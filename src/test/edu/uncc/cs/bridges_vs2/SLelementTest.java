package edu.uncc.cs.bridges_vs2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.*;

public class SLelementTest {
	static SLelement<?> sle1;
	static SLelement<?> sle2;
	static SLelement<?> sle3;
	static SLelement<?> sle4;
	static SLelement<?> sle0;
	static SLelement<?> sle5clone0;

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

	@Test
	/** test toString() method (JSON file) */
	public void testToString() {

		try {
			JSONObject o = (JSONObject) parser.parse(sle0.toString());
		} catch (Exception e) {
			fail("toString() does not return valid JSON on unlinked element");
		}

		try {
			JSONObject o = (JSONObject) parser.parse(sle1.toString());
		} catch (Exception e) {
			fail("toString() does not return valid JSON on linked element");
		}
	}

	@Test
	/** test  toString() method after elements have been changed(JSON file) */
	public void testToStringOnLinkedSLelementsAfterLinkingHasChanged() {
		((SLelement<String>) sle4).setNext((SLelement<String>) sle3);
		((SLelement<String>) sle3).setNext((SLelement<String>) sle2);
		((SLelement<String>) sle3).setNext((SLelement<String>) sle1);

		try {
			JSONObject o1 = (JSONObject) parser.parse(sle4.toString());
			JSONObject o2 = (JSONObject) parser.parse(sle3.toString());
		} catch (Exception e) {
			fail("Invalid JSON on linked SLelement");
		}
	}

	/** test that clone method has same identifier */
	@Test
	public void testCloneMethodGetsNewIdentifer() {
		assertNotEquals(sle0.getIdentifier(), sle5clone0.getIdentifier());
		assertNotEquals(sle5clone0, sle0);
	}

	/** test that cloned object is different object from original */
	@Test
	public void testCloneIsDifferentObject() throws CloneNotSupportedException {
		assertNotEquals(sle5clone0, sle0);
		assertEquals(sle5clone0.getIdentifier(), sle0.getIdentifier());
		assertEquals(sle5clone0.getLabel(), sle0.getLabel());
		assertEquals(sle5clone0.getValue(), sle0.getValue());

	}
}
