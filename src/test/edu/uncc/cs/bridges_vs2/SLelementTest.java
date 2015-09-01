

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

import bridges_v21.base.*;


public class SLelementTest {
	static SLelement<String> sle1;
	static SLelement<String> sle2;
	static SLelement<String> sle3;
	static SLelement<String> sle4;
	static SLelement<String> sle0;
	static SLelement<String> sle1Clone;

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

	
	@Test
	/** test that toString returns a valid string*/
	public void testToString(){
		assertNotNull("toString() returned null", sle1.toString());
		assertTrue("toString() does not contain correct name of class (SLelement", sle1.toString().contains("SLelement"));
	}

	
	
}
