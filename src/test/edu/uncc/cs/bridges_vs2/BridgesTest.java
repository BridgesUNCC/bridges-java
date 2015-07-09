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

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.ADTVisualizer;
import edu.uncc.cs.bridgesV2.base.BSTElement;
import edu.uncc.cs.bridgesV2.base.DLelement;
import edu.uncc.cs.bridgesV2.base.Edge;
import edu.uncc.cs.bridgesV2.base.Element;
import edu.uncc.cs.bridgesV2.base.GraphList;
import edu.uncc.cs.bridgesV2.base.SLelement;
import edu.uncc.cs.bridgesV2.base.TreeElement;
import edu.uncc.cs.bridgesV2.connect.Bridges;
import edu.uncc.cs.bridgesV2.validation.Validation;

/* Bridges fields are held in class variables, not instance variables, meaning that once a 
 * Bridges object is constructed, any later Bridges object will overwrite the fields of the earlier object.
 */
public class BridgesTest {
	static Bridges<String> bridges1;
	static Bridges<String> bridges2;
	static Bridges<String> bridges3;
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
	static String ALIST	 = "AList";

	static String[] structureTypes;

	public void resetBridges(){
		Bridges.setKey("691659187196");
		Bridges.setUserName("cgrafer");
		Bridges.setAssignment(99);
	}
	
	/** Set up static elements to for later tests. */
	@BeforeClass
	public static void BeforeClass() throws Exception {
		sle1 = new SLelement<String>("A", "1");
		dle1 = new DLelement<String>("D", "2");
		te1 = new TreeElement<String>("T");

		structureTypes = new String[] { DLLIST, GRAPH, GRAPHL, LLIST, QUEUE,
				STACK, TREE, ALIST };

		bridges1 = new Bridges<String>();
		bridges2 = new Bridges<String>(99, "691659187196", "cgrafer");
		bridges3 = new Bridges<String>(99, "691659187196", sle1, "cgrafer");
	}

	/** Test Bridges null constructor */
	@Test
	public void testBridges() throws Exception {
		Bridges<String> b = new Bridges<String>();
		assertNotNull(b);
	}

	/** Test Bridges constructor with assignment, api key, and username. */
	@Test
	public void testBridgesIntStringString() throws Exception {
		Bridges<String> b = new Bridges<String>(99, "691659187196", "cgrafer");
		assertNotNull(b);
	}

	/** Test Bridges constructor with assignment, api key, data structure, and username. */
	@Test
	public void testBridgesIntStringSLelementOfEString() throws Exception {
		Bridges<String> b = new Bridges<String>(99, "691659187196", sle1, "cgrafer");
		assertNotNull(b);
	}

	/** Test init() sets assignment, api key, and username correctly */
	@Test
	public void testInitIntStringString() throws Exception {
		Bridges<String> b = new Bridges<String>();

		b.init(1, "abcd", "efgh");
		assertEquals("init did not set assignment correctly", "1.0",
				Bridges.getAssignment());
		assertEquals("init did not set api key correctly", "abcd", Bridges.getKey());
		assertEquals("init did not set user name correctly", "efgh",
				Bridges.getUserName());

		resetBridges();
	}

	/** Test init() sets assignment, api key, data structure and username correctly */
	@Test
	public void testInitIntStringSLElementString() throws Exception {
		Bridges<String> b = new Bridges<String>();
		SLelement<String> sle = new SLelement<String>("1", "2");

		b.init(2, "xyz", sle, "tuv");
		assertEquals("init did not set assignment correctly", "2.0",
				Bridges.getAssignment());
		assertEquals("init did not set api key correctly", "xyz", Bridges.getKey());
		assertEquals("init did not set user name correctly", "tuv",
				Bridges.getUserName());
		assertEquals("init did not set element correctly", sle, b.getRoot());
		
		resetBridges();

	}
	
	/** Test init() sets assignment, api key, data structure and username correctly */
	@Test
	public void testInitIntStringDLElementString() throws Exception {
		Bridges<String> b = new Bridges<String>();
		DLelement<String> dle = new DLelement<String>("1", "2");

		b.init(2, "xyz", dle, "tuv");
		assertEquals("init did not set assignment correctly", "2.0",
				Bridges.getAssignment());
		assertEquals("init did not set api key correctly", "xyz", Bridges.getKey());
		assertEquals("init did not set user name correctly", "tuv",
				Bridges.getUserName());
		assertEquals("init did not set element correctly", dle, b.getRoot());
		
		resetBridges();

	}
	
	/** Test init() sets assignment, api key, data structure and username correctly */
	@Test
	public void testInitIntStringTreeElementString() throws Exception {
		Bridges<String> b = new Bridges<String>();
		TreeElement<String> te = new TreeElement<String>("1", "2");
		
		
		b.init(2, "xyz", te, "tuv");
		assertEquals("init did not set assignment correctly", "2.0",
				Bridges.getAssignment());
		assertEquals("init did not set api key correctly", "xyz", Bridges.getKey());
		assertEquals("init did not set user name correctly", "tuv",
				Bridges.getUserName());
		assertEquals("init did not set element correctly", te, b.getRoot());
		
		resetBridges();

	}
	
	/** Test init() sets assignment, api key, data structure and username correctly */
	@Test
	public void testInitIntStringBSTElementString() throws Exception {
		Bridges<String> b = new Bridges<String>();
		BSTElement<Integer, String> bst = new BSTElement<Integer, String>("a");
		
		
		b.init(2, "xyz", bst, "tuv");
		assertEquals("init did not set assignment correctly", "2.0",
				Bridges.getAssignment());
		assertEquals("init did not set api key correctly", "xyz", Bridges.getKey());
		assertEquals("init did not set user name correctly", "tuv",
				Bridges.getUserName());
		assertEquals("init did not set element correctly", bst, b.getRoot());
		
		resetBridges();

	}


	/** Test whether getAssignment() returns correct assignment */
	@Test
	public void testGetAssignment() {
		Bridges.setAssignment(99);
		assertEquals("Get assignment returns incorrect assignment number",
				"99.0", Bridges.getAssignment());
	}

	/** Test setAssignment and make sure same value is returned by getAssignment(). */
	@Test 
	public void testSetAssignment() {
		Bridges.setAssignment(98);
		assertEquals(
				"Set assignment did not set bridges to correct assignment number",
				"98.0", Bridges.getAssignment());
		
		resetBridges();
	}
	
	
	
	
	/** Test setAssignment with zero value. */
	@Test 
	public void testSetAssignmentWithNegativeValue() {
		boolean thrown = false;
		try {
			Bridges.setAssignment(-1);			
		} catch (Exception e) {
			thrown = true;
		}
		
		if(!thrown) {
			fail("setAssignment(-1) did not throw error");
		}
	}
	
	

	/** Test whether getUserName() returns correct value */
	@Test
	public void testGetUserName() throws Exception {
		
		
		assertEquals("getUserName() returns incorrect name", "cgrafer",
				Bridges.getUserName());
	}

	/** Test setUserName() and make sure same value is returned by getUserName() */
	@Test
	public void testSetUserName() {
		Bridges.setUserName("a");
		assertEquals("setUserName() did not set user name correctly", "a",
				Bridges.getUserName());

		// return to orignal
		resetBridges();
	}

	/** Test whether getKey() returns correct value */
	@Test
	public void testGetKey() {
		
		Bridges.setKey("hijk");
		
		
		assertEquals("getKey() does not return correct API key",
				"hijk", Bridges.getKey());
		
		resetBridges();
	}

	/** Test setKey() and make sure same value is returned by getKey() */
	@Test
	public void testSetKey() {
		Bridges.setKey("1234");
		assertEquals("setKey() does not correctly set the API Key", "1234",
			Bridges.getKey());

		// return to orignal
		resetBridges();

	}

	/** Not tested here, tested in getVisualizer */
	@Test
	public void testGetVisualizer() {
		// tested in testSetVisualizer();
	}

	/** Test setVisualizer and make sure same value is returned by getVisualizer */
	@Test
	public void testSetVisualizer() {
		Bridges.setKey("691659187196");

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
	public void testSetDataStructureHashMapOfGraphList() {
		GraphList<String> adjList = new GraphList<String>();
		adjList.addEdge(new Edge(1, "A"));
		
		
		HashMap<String, GraphList<String>> hash = new HashMap<String, GraphList<String>>();
		hash.put("B", adjList);
		
		for (String s : structureTypes) {
			bridges1.setDataStructure(hash, s);
			assertEquals("setDataStructure for hashmap<string, graphlist<E> did not set %s correctly", s,
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

		//System.setErr(new PrintStream(bytes));

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

		bridges3.setDataStructure(sle1, "queue");
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
	
	/** Test visualize with invalide data type */
	@Test
	public void testVisualizeWithInvalidADTType() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		
		bridges3.setDataStructure(dle1, "grap");
		bridges3.visualize();


		System.setErr(old_out);

		assertTrue("visualize() did not throw an error with invalid ADT type",
				bytes.size() != 0);

	}
	
	
	/** Test setDataStructure() with "graphl" */	
	@Test
	public void testVisualizeWhenSetDataStructureToGraphWithHashMap() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		GraphList<String> adjListB = new GraphList<String>();
		//adjListB.setSourceVertex(new SLelement<String>("B", "B"));
		//adjListB.addEdge(new Edge(1, "A"));
	
		
		HashMap<String, GraphList<String>> hash = new HashMap<String, GraphList<String>>();
		hash.put("B", adjListB);
		
		
		
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
	
	/** Test setRoot and make sure getRoot returns same value */
	@Test
	public void testRoundWithNoPlaces() {
		boolean thrown = false;
		
		try{
			Bridges.round(3.0, -1);
		} catch (Exception e){
				thrown = true;
		}
		
		if(!thrown) {
			fail("round() did not throw exception when passed negative value for places");
		}
	}

	/**Test validateADTVisualizer throws exception if data structure not set **/
	
	@Test
	public void testValidate_ADTVisualizerThrowsExceptionWhenDataStructureNotSet(){
		boolean isThrown = false;
		
		Bridges<String> b = new Bridges<String>();
		
		try{
			Validation.validate_ADTVisualizer(b.getVisualizer(), b);
		} catch (IllegalArgumentException e) {
			isThrown = true;
		}
		
		if(!isThrown){
			fail("data structure not set, but error not thrown when Validation.validate_ADTVisualzer() called");
		}
		
				
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
