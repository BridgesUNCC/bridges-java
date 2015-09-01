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

import bridges_v21.base.*;
import bridges_v21.connect.*;
import bridges_v21.validation.*;

/* Bridges fields are held in class variables, not instance variables, meaning that once a 
 * Bridges object is constructed, any later Bridges object will overwrite the fields of the earlier object.
 */
public class BridgesTest {
	static Bridges<String, String> bridges1;
	static Bridges<String, String> bridges2;
	static Bridges<String, String> bridges3;
	static SLelement<String> sle1;
	static DLelement<String> dle1;
	static TreeElement<String> te1;
	static BSTElement<Integer, String> bst1;
	static ArrayOfElement<String> a0;
	static GraphAdjMatrix<String, String> adjMatrix;
	static GraphAdjList<String, String> adjList;

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
		bst1 = new BSTElement<Integer, String>("BST", 1, "B");
		a0 = new ArrayOfElement<String>(Element.class);
		adjMatrix = new GraphAdjMatrix<String, String>(1);
		adjList = new GraphAdjList<String, String>();
		
		adjMatrix.addVertex("Matrix", "Matrix");
		adjList.addVertex("List", "List");
		
		
		structureTypes = new String[] { DLLIST, GRAPH, GRAPHL, LLIST, QUEUE,
				STACK, TREE, ALIST };

		bridges1 = new Bridges<String, String>();
		bridges2 = new Bridges<String, String>(99, "691659187196", "cgrafer");
		bridges3 = new Bridges<String, String>(99, "691659187196", "cgrafer");
	}

	/** Test Bridges null constructor */
	@Test
	public void testBridges() throws Exception {
		Bridges<String, String> b = new Bridges<String, String>();
		assertNotNull(b);
	}

	/** Test Bridges constructor with assignment, api key, and username. */
	@Test
	public void testBridgesIntStringString() throws Exception {
		Bridges<String, String> b = new Bridges<String, String>(99, "691659187196", "cgrafer");
		assertNotNull(b);
	}

	

	/** Test init() sets assignment, api key, and username correctly */
	@Test
	public void testInitIntStringString() throws Exception {
		Bridges<String, String> b = new Bridges<String, String>();
		
		
		
		b.init(1, "abcd", "efgh");
		assertEquals("init did not set assignment correctly", "1.00",
				Bridges.getAssignment());
		assertEquals("init did not set api key correctly", "abcd", Bridges.getKey());
		assertEquals("init did not set user name correctly", "efgh",
				Bridges.getUserName());

		resetBridges();
	}

	
	
	
	
		
	


	/** Test whether getAssignment() returns correct assignment */
	@Test
	public void testGetAssignment() {
		Bridges.setAssignment(99);
		assertEquals("Get assignment returns incorrect assignment number",
				"99.00", Bridges.getAssignment());
	}

	/** Test whether getAssignment() after visualizing 10 times to check decimal is working correctly */
	@Test
	public void testGetAssignmentAfterVisualizing10times() {
		Bridges.setAssignment(99);
		
		Bridges<String, String> b = new Bridges<String, String>(99, "691659187196", "cgrafer");
		 b.setDataStructure(sle1);
		
		 
		 
		

		for(int i = 0; i < 10; i++){
			b.visualize();
		}
		
			 	
		
		String msg = "Get assignment returns assignment number " + b.getAssignment() +" instead of 99.10";
		assertEquals(msg, "99.10", Bridges.getAssignment());
	}
	
	/** Test setAssignment and make sure same value is returned by getAssignment(). */
	@Test 
	public void testSetAssignment() {
		Bridges.setAssignment(98);
		assertEquals(
				"Set assignment did not set bridges to correct assignment number",
				"98.00", Bridges.getAssignment());
		
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

		ADTVisualizer<String, String> visualizer = new ADTVisualizer<String, String>();
		bridges1.setVisualizer(visualizer);

		assertEquals("getVisualizer did not return correct ADTVisualizer",
				visualizer, bridges1.getVisualizer());
	}

	/** Test setDataStructure() with valid structure name */
	@Test
	public void testSetDataStructureSLelementOfEStringWithValidStructureName()
			throws Exception {

			bridges1.setDataStructure(sle1);
			
			String visType = bridges1.getVisualizer().visualizerType;
			String msg = "bridges visualizer type is " + visType + ", it should have been GraphAdjacencyList";
			
			assertTrue(msg, bridges1.getVisualizer().visualizerType.equals("SinglyLinkedList"));	
			}
	


	/** Test setDataStructure with DLelement */
	@Test
	public void testSetDataStructureDLelementOfEString() {

			bridges1.setDataStructure(dle1);
			
			String visType = bridges1.getVisualizer().visualizerType;
			String msg = "bridges visualizer type is " + visType + ", it should have been GraphAdjacencyList";
			
			assertTrue(msg, bridges1.getVisualizer().visualizerType.equals("DoublyLinkedList"));	
		
	}

	/** Test setDataStructure with TreeElement */
	@Test
	public void testSetDataStructureTreeElementOfEString() {

		bridges1.setDataStructure(te1);
		
		String visType = bridges1.getVisualizer().visualizerType;
		String msg = "bridges visualizer type is " + visType + ", it should have been GraphAdjacencyList";
		
		assertTrue(msg, bridges1.getVisualizer().visualizerType.equals("BinaryTree"));	
	
	}

	/** Test setDataStructure with BSTElement */
	@Test
	public void testSetDataStructureBSTElementOfEString() {

		bridges1.setDataStructure(bst1);
		
		
		String visType = bridges1.getVisualizer().visualizerType;
		String msg = "bridges visualizer type is " + visType + ", it should have been GraphAdjacencyList";
		
		assertTrue(msg, bridges1.getVisualizer().visualizerType.equals("BinarySearchTree"));	
	}

	
	
	
	
	/** Test setDataStructure with ArrayElement */
	@Test
	public void testSetDataStructureArrayElementOfEString() {
		Element<String>[] elementArray = a0.returnArray();
		
		bridges1.setDataStructure(elementArray, 0);
		
		String visType = bridges1.getVisualizer().visualizerType;
		String msg = "bridges visualizer type is " + visType + ", it should have been GraphAdjacencyList";
		
		assertTrue(msg, bridges1.getVisualizer().visualizerType.equals("Array"));	
	}
	
	

	/** Test setDataStructure with GraphAdjacnecyList */
	@Test
	public void testSetDataStructureGraphAdjList() {
		bridges1.setDataStructure(adjList);
		
		String visType = bridges1.getVisualizer().visualizerType;
		String msg = "bridges visualizer type is " + visType + ", it should have been GraphAdjacencyList";
		
		assertTrue(msg, bridges1.getVisualizer().visualizerType.equals("GraphAdjacencyList"));	
	}
	
	/** Test setDataStructure with GraphAdjacencyMatrix */
	@Test
	public void testSetDataStructureGraphAdjMatrix() {
		bridges1.setDataStructure(adjMatrix);

		String visType = bridges1.getVisualizer().visualizerType;
		String msg = "bridges visualizer type is " + visType + ", it should have been GraphAdjacencyList";
		
		assertTrue(msg, bridges1.getVisualizer().visualizerType.equals("GraphAdjacencyMatrix"));	
	}



	/** Test visualize() with SinglyLInkedList */
	@Test
	public void testVisualizeWhenSetDataStructureToLlist() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		bridges3.setDataStructure(sle1);
		bridges3.visualize();

		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a llist",
				bytes.size() == 0);

	}

	/** Test visualize() with "DoublyLinkedList" */
	@Test
	public void testVisualizeWhenSetDataStructureToDllist() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		//System.setErr(new PrintStream(bytes));

		bridges3.setDataStructure(dle1);
		bridges3.visualize();

		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a dllist",
				bytes.size() == 0);

	}
	
	/** Test visualize() with "Array" */
	@Test
	public void testVisualizeWhenSetDataStructureToArray() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error
		
		Element<String>[] elementArray = a0.returnArray();
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		//System.setErr(new PrintStream(bytes));

		bridges3.setDataStructure(elementArray, 0);
		bridges3.visualize();

		System.setErr(old_out);

		assertTrue("visualize() threw an error when making an array",
				bytes.size() == 0);
	}
	
	/** Test visualize() with "BinarySearchTree" */
	@Test
	public void testVisualizeWhenSetDataStructureToBinarySearchTree() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error
		
		
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		//System.setErr(new PrintStream(bytes));

		bridges3.setDataStructure(bst1);
		bridges3.visualize();

		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a binary search tree",
				bytes.size() == 0);

	}
	
		
	/** Test visualize() with "GraphAdjMatrix" */
	@Test
	public void testVisualizeWhenSetDataStructureToGraphAdjMatrix() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error
		
		
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		//System.setErr(new PrintStream(bytes));

		bridges3.setDataStructure(adjMatrix);
		bridges3.visualize();

		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a adjMatrix",
				bytes.size() == 0);

	}
	
	/** Test visualize() with "GraphAdjList" */
	@Test
	public void testVisualizeWhenSetDataStructureToGraphAdjList() {
		// bridges will print an error to System.err if somethign goes wrong
		// capture this error stream, if the resulting stream is not size zero,
		// there was an error
		
		
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		//System.setErr(new PrintStream(bytes));

		bridges3.setDataStructure(adjList);
		bridges3.visualize();

		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a adjList",
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

		bridges3.setDataStructure(sle1);
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

		bridges3.setDataStructure(sle1);
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

		
		bridges3.setDataStructure(te1);
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

		
		bridges3.setDataStructure(dle1);
		bridges3.visualize();


		System.setErr(old_out);

		assertTrue("visualize() threw an error when making a graph",
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
		bridges3.setRoot(sle1);
		
		assertEquals("getRoot() does not return correct root", sle1,
				bridges3.getRoot());
	}

	/** Test setRoot and make sure getRoot returns same value */
	@Test
	public void testSetRoot() {
		SLelement<String> sle2 = new SLelement<String>("B", "2");
		bridges1.setRoot(sle2);

		assertEquals("setRoot() does not set root correctly", sle2,
				(SLelement<String>) bridges1.getRoot());

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
