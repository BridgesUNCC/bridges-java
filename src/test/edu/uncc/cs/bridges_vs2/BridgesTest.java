package edu.uncc.cs.bridges_vs2;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.apache.http.client.entity.InputStreamFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.ADTVisualizer;
import edu.uncc.cs.bridgesV2.base.DLelement;
import edu.uncc.cs.bridgesV2.base.Element;
import edu.uncc.cs.bridgesV2.base.SLelement;
import edu.uncc.cs.bridgesV2.base.TreeElement;
import edu.uncc.cs.bridgesV2.connect.Bridges;

/* Bridges fields are held in class variables, not instance variables, meaning that once a 
 * Bridges object is constructed, any later Bridges object will overwrite the fields of the earlier object.
 */
public class BridgesTest {
	static Bridges bridges1;
	static Bridges bridges2;
	static Bridges bridges3;
	static SLelement<String> sle1;
	static DLelement<String> dle1;
	static TreeElement<String> te1;

	// structure types
	static String DLLIST = "Dllist";
	static String GRAPH = "graph";
	static String GRAPHL = "graphl";
	static String LLIST = "llist";
	static String QUEUE = "queue";
	static String STACK = "stack";
	static String TREE = "tree";

	static String[] structureTypes;

	/** Set up static elements to for later tests. */
	@BeforeClass
	public static void BeforeClass() throws Exception {
		sle1 = new SLelement<String>("A", "1");
		dle1 = new DLelement<String>("D", "2");
		te1 = new TreeElement<String>("T");

		structureTypes = new String[] { DLLIST, GRAPH, GRAPHL, LLIST, QUEUE,
				STACK, TREE };

		bridges1 = new Bridges<String>();
		bridges2 = new Bridges<String>(99, "691659187196", "cgrafer");
		bridges3 = new Bridges<String>(99, "691659187196", sle1, "cgrafer");
	}

	/** Test Bridges null constructor */
	@Test
	public void testBridges() throws Exception {
		Bridges b = new Bridges<String>();
		assertNotNull(b);
	}

	/** Test Bridges constructor with assignment, api key, and username. */
	@Test
	public void testBridgesIntStringString() throws Exception {
		Bridges b = new Bridges<String>(99, "691659187196", "cgrafer");
		assertNotNull(b);
	}

	/** Test Bridges constructor with assignment, api key, data structure, and username. */
	@Test
	public void testBridgesIntStringSLelementOfEString() throws Exception {
		Bridges b = new Bridges<String>(99, "691659187196", sle1, "cgrafer");
		assertNotNull(b);
	}

	/** Test init() sets assignment, api key, and username correctly */
	@Test
	public void testInitIntStringString() throws Exception {
		Bridges<String> b = new Bridges<String>();

		b.init(1, "abcd", "efgh");
		assertEquals("init did not set assignment correctly", "1.0",
				b.getAssignment());
		assertEquals("init did not set api key correctly", "abcd", b.getKey());
		assertEquals("init did not set user name correctly", "efgh",
				b.getUserName());

		b = new Bridges<String>(99, "691659187196", sle1, "cgrafer");

	}

	/** Test init() sets assignment, api key, data structure and username correctly */
	@Test
	public void testInitIntStringElementOfEString() throws Exception {
		Bridges<String> b = new Bridges<String>();
		SLelement<String> sle = new SLelement<String>("1", "2");

		b.init(2, "xyz", sle, "tuv");
		assertEquals("init did not set assignment correctly", "2.0",
				b.getAssignment());
		assertEquals("init did not set api key correctly", "xyz", b.getKey());
		assertEquals("init did not set user name correctly", "tuv",
				b.getUserName());
		assertEquals("init did not set element correctly", sle, b.getRoot());

	}

	/** Test whether getAssignment() returns correct assignment */
	@Test
	public void testGetAssignment() {
		assertEquals("Get assignment returns incorrect assignment number",
				"99.0", bridges1.getAssignment());
		assertEquals("Get assignment returns incorrect assignment number",
				"99.0", bridges2.getAssignment());
		assertEquals("Get assignment returns incorrect assignment number",
				"99.0", bridges3.getAssignment());
	}

	/** Test setAssignment and make sure same value is returned by getAssignment(). */
	@Test 
	public void testSetAssignment() {
		bridges1.setAssignment(98);
		assertEquals(
				"Set assignment did not set bridges to correct assignment number",
				"98.0", bridges1.getAssignment());
	}

	/** Test whether getUserName() returns correct value */
	@Test
	public void testGetUserName() throws Exception {
		assertEquals("getUserName() returns incorrect name", "cgrafer",
				bridges1.getUserName());
		assertEquals("getUserName() returns incorrect name", "cgrafer",
				bridges2.getUserName());
		assertEquals("getUserName() returns incorrect name", "cgrafer",
				bridges3.getUserName());
	}

	/** Test setUserName() and make sure same value is returned by getUserName() */
	@Test
	public void testSetUserName() {
		bridges1.setUserName("a");
		assertEquals("setUserName() did not set user name correctly", "a",
				bridges1.getUserName());

		// return to orignal
		bridges1.setUserName("cgrafer");

	}

	/** Test whether getKey() returns correct value */
	@Test
	public void testGetKey() {
		assertEquals("getKey() does not return correct API key",
				"691659187196", bridges1.getKey());
		assertEquals("getKey() does not return correct API key",
				"691659187196", bridges2.getKey());
		assertEquals("getKey() does not return correct API key",
				"691659187196", bridges3.getKey());
	}

	/** Test setKey() and make sure same value is returned by getKey() */
	@Test
	public void testSetKey() {
		bridges1.setKey("1234");
		assertEquals("setKey() does not correctly set the API Key", "1234",
				bridges1.getKey());

		// return to orignal
		bridges1.setKey("691659187196");

	}

	/** Not tested here, tested in getVisualizer */
	@Test
	public void testGetVisualizer() {
		// tested in testSetVisualizer();
	}

	/** Test setVisualizer and make sure same value is returned by getVisualizer */
	@Test
	public void testSetVisualizer() {
		ADTVisualizer<String> visualizer = new ADTVisualizer<String>();
		bridges1.setVisualizer(visualizer);

		assertEquals("getVisualizer did not return correct ADTVisualizer",
				visualizer, bridges1.getVisualizer());
	}

	/** Test setDataStructure() with valid structure name */
	@Test
	public void testSetDataStructureSLelementOfEStringWithValidStructureName()
			throws Exception {

		for (String s : structureTypes) {
			bridges1.setDataStructure(sle1, s);
			assertEquals("setDataStructure did not set %s correctly", s,
					bridges1.getVisualizer().getVisualizerType());
		}
	}


	/** Test setDataStructure with DLelement */
	@Test
	public void testSetDataStructureDLelementOfEString() {
		for (String s : structureTypes) {
			bridges1.setDataStructure(dle1, s);
			assertEquals("setDataStructure did not set %s correctly", s,
					bridges1.getVisualizer().getVisualizerType());
		}
	}

	/** Test setDataStructure with TreeElement */
	@Test
	public void testSetDataStructureTreeElementOfEString() {
		for (String s : structureTypes) {
			bridges1.setDataStructure(te1, s);
			assertEquals("setDataStructure did not set %s correctly", s,
					bridges1.getVisualizer().getVisualizerType());
		}
	}

	/** Test setDataStructure with HashMap */
	@Test
	public void testSetDataStructureHashMapOfStringSLelementOfEString() {
		HashMap<String, SLelement<String>> hash = new HashMap<String, SLelement<String>>();
		hash.put("A", new SLelement<String>("Z", "ZZ"));
		
		for (String s : structureTypes) {
			bridges1.setDataStructure(hash, s);
			assertEquals("setDataStructure did not set %s correctly", s,
					bridges1.getVisualizer().getVisualizerType());
		}
		
	}

	/** Not tested - add() does nothing - needs to be deprecated. */
	@Test
	public void testAdd() {
	}

	/** Test whether toggle changes value of isVisualizeJSON */
	@Test
	public void testToggleJSONdisplay() {
		boolean oldValue = bridges1.getVisualizer().isVisualizeJSON();

		bridges1.toggleJSONdisplay();

		assertNotEquals("toggleJSONDisplay() did not toggle JSON display",
				oldValue, bridges1.getVisualizer().isVisualizeJSON());

		// toggle to reset
		bridges1.toggleJSONdisplay();
	}

	/** Test setDataStructure() with "llist" */
	@Test
	public void testVisualizeWhenSetDataStructureToLlist() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		bridges3.setDataStructure(sle1, "llist");
		bridges3.visualize();

		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a llist",
				bytes.size() == 0);

	}

	/** Test setDataStructure() with "Dllist" */
	@Test
	public void testVisualizeWhenSetDataStructureToDllist() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		bridges3.setDataStructure(dle1, "Dllist");
		bridges3.visualize();

		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a dllist",
				bytes.size() == 0);

	}

	/** Test setDataStructure() with "stack" */
	@Test
	public void testVisualizeWhenSetDataStructureToStack() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;
		System.setErr(new PrintStream(bytes));

		bridges3.setDataStructure(sle1, "stack");
		bridges3.visualize();

		System.setErr(old_out);

		assertTrue("visualize() threw an error when  making a stack",
				bytes.size() == 0);

	}

	/** Test setDataStructure() with "queue" */
	@Test
	public void testVisualizeWhenSetDataStructureToQueue() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		ByteArrayOutputStream errorBytes = new ByteArrayOutputStream();

		PrintStream old_error_stream = System.err;
		System.setErr(new PrintStream(errorBytes));

		bridges3.setDataStructure(dle1, "queue");
		bridges3.visualize();

		System.setErr(old_error_stream);

		assertTrue("visualize() threw an error when trying to make a queue",
				errorBytes.size() == 0);

	}

	/** Test setDataStructure() with "tree" */
	@Test
	public void testVisualizeWhenSetDataStructureToTree() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		
		bridges3.setDataStructure(te1, "tree");
		bridges3.visualize();


		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a tree",
				bytes.size() == 0);

	}
	
	/** Test setDataStructure() with DLelement and type is "graph" */
	@Test
	public void testVisualizeWhenSetDataStructureToGraphwithDLelement() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		
		bridges3.setDataStructure(dle1, "graph");
		bridges3.visualize();


		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a graph",
				bytes.size() == 0);

	}
	
	/** Test setDataStructure() with "graphl" */	
	@Test
	public void testVisualizeWhenSetDataStructureToGraphWithHashMap() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		HashMap<String, SLelement<String>> hash = new HashMap<String, SLelement<String>>();
		hash.put("A", new SLelement<String>("Z", "ZZ"));
		
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		
		bridges3.setDataStructure(hash, "graphl");
		bridges3.visualize();


		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a graph",
				bytes.size() == 0);

	}
	
	/** Test setDataStructure() with "graphl" */		
	@Test
	public void testVisualizeWhenSetDataStructureToGraphL() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		
		bridges3.setDataStructure(sle1, "graphl");
		bridges3.visualize();


		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a graphl",
				bytes.size() == 0);

	}

	/** Not tested - only calls visualize() */
	@Test
	public void testComplete() {

		// this method only calls visualize, tested in visualize
	}

	/** Test whether correct root is returned */
	@Test
	public void testGetRoot() {
		assertEquals("getRoot() does not return correct root", sle1,
				bridges3.getRoot());
	}

	/** Test setRoot and make sure getRoot returns same value */
	@Test
	public void testSetRoot() {
		SLelement<String> sle2 = new SLelement<String>("B", "2");
		bridges1.setRoot(sle2);

		assertEquals("setRoot() does not set root correctly", sle2,
				bridges1.getRoot());

		// return to original
		bridges1.setRoot(sle1);
	}

	// all protected methods

	// @Test
	// public void testUpdateSL() {
	// }
	//
	// @Test
	// public void testUpdateDL() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testUpdateTree() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testUpdateGraphL() {
	// fail("Not yet implemented");
	// }

}
