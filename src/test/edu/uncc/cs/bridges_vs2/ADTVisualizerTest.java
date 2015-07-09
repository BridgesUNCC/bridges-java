

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.ADTVisualizer;
import edu.uncc.cs.bridgesV2.base.ArrayElement;
import edu.uncc.cs.bridgesV2.base.ArrayOfElement;
import edu.uncc.cs.bridgesV2.base.BSTElement;
import edu.uncc.cs.bridgesV2.base.DLelement;
import edu.uncc.cs.bridgesV2.base.Edge;
import edu.uncc.cs.bridgesV2.base.Element;
import edu.uncc.cs.bridgesV2.base.GraphList;
import edu.uncc.cs.bridgesV2.base.SLelement;
import edu.uncc.cs.bridgesV2.base.TreeElement;

public class ADTVisualizerTest {
	static ADTVisualizer<String> adt0;
	static ADTVisualizer<String> adt1;
	static ADTVisualizer<String> adt2;

	static HashMap<String, Element<?>> hash;
	static ArrayElement<String>[] elementArray;
	static ArrayElement<String>[] elementArrayNull;

	static SLelement<String> sle1;
	static SLelement<String> sle2;
	static SLelement<String>[] sleArray;
	
	
	static DLelement<String> dle1;
	static DLelement<String> dle2;
	static DLelement<String> dle3;

	static TreeElement<String> tree1;
	static TreeElement<String> tree2;
	static TreeElement<String> tree3;

	
	static BSTElement<Integer, String> bst1;
	static BSTElement<Integer, String> bst2;

	
	static SLelement v1;
	static SLelement v2;

	static JSONParser parser;

	/** Set up static elements to for later tests. */
	@BeforeClass
	public static void BeforeClass() {
		try {
			hash = new HashMap<String, Element<?>>();
			
			
			
			elementArrayNull = (ArrayElement<String>[]) new ArrayElement[10];
			elementArray = (ArrayElement<String>[]) new ArrayElement[10];
			elementArray[0] = new ArrayElement<String>("a", "string");
			elementArray[1] = new ArrayElement<String>("b", "string");
			
			


			sle1 = new SLelement<String>("a", "A");
			sle2 = new SLelement<String>("b", "B");
			sle1.setNext(sle2);

			dle1 = new DLelement<String>("aa", "AA");
			dle2 = new DLelement<String>("bb", "BB");
			dle3 = new DLelement<String>("cc", "CC");
			dle1.setNext(dle2);
			dle1.setPrev(dle3);

			tree1 = new TreeElement<String>("a tree");
			tree2 = new TreeElement<String>("b tree");
			tree3 = new TreeElement<String>("c tree");
			tree1.setRight(tree2);
			tree1.setLeft(tree3);
			
			bst1 = new BSTElement<Integer, String>(1, "A");
			bst2 = new BSTElement<Integer, String>(2, "B");
			bst1.setRight(bst2);
					

			adt0 = new ADTVisualizer<String>();
			adt1 = new ADTVisualizer<String>(elementArrayNull);
			adt2 = new ADTVisualizer<String>(hash);

			// for graph testing
			v1 = new SLelement<String>("vertex1", "vertex1");
			v2 = new SLelement<String>("vertex2", "vertex2");

			parser = new JSONParser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** Tests validateType with HashMap.
	 * <p>Send HashMap to ADTVisualizer constructor. Test that no error is thrown. */
	@Test
	public void testValidateTypeWithValidInput() throws Exception {
		ADTVisualizer<String> adt3 = new ADTVisualizer<String>(hash);
		assertNotNull(adt3);
	}

	/** Tests validateType with invalid type. <p>Send invalid input to validateType. Test that stacktrace is printed to System.out but error is not thrown.*/
	@Test
	public void testValidateTypeWithInvalidInput() throws Exception {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		adt0.validateType("abcde");
		
		System.setErr(old_out);

		assertTrue("validateType() did not print stacktrace when sent an invalid type",
				bytes.size() != 0);
	}

	/** Tests validateType with Object type. <p>Send invalid input to validateType. Test that stacktrace is printed to System.out but error is not thrown.*/
	@Test
	public void testValidateTypeWithObject() throws Exception {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		adt0.validateType(new Object());
		
		System.setErr(old_out);

		assertTrue("validateType() did not print stacktrace when sent an invalid type",
				bytes.size() != 0);
	}
	
	/** Test that getVisualizerType returns a valid type from ADT_TYPE*/
	@Test
	public void testGetVisualizerType() {
		// this constructor gets a default setting to graphl
		assertTrue(
				"getVisualizerType() does not return a valid type from ADT_TYPE",
				adt0.ADT_TYPE.containsValue(adt0.getVisualizerType()));

		// these constructors do not have a default setting
		try {
			assertTrue(
					"getVisualizerType() does not return a valid type from ADT_TYPE",
					adt1.ADT_TYPE.containsValue(adt1.getVisualizerType()));
			assertTrue(
					"getVisualizerType() does not return a valid type from ADT_TYPE",
					adt2.ADT_TYPE.containsValue(adt2.getVisualizerType()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue("visualizer set to null did not throw error",
					e instanceof NullPointerException);
			;
		}

	}

	/** Tests setVisualizerType() with valid key. <p>Call setVisualizerType and getVisualizerType for all valid ADT_TYPES. Test that input passed to setVisualizerType is equal to return value from getVisualizerType.*/
	@Test
	public void testSetVisualizerTypeToValidKey() {
		Set<String> keys = adt0.ADT_TYPE.keySet();

		try {
			for (String k : keys) {
				adt0.setVisualizerType(k);

				assertTrue(
						"getVisualizerType does not equal string passed to setVisualizerType",
						adt0.ADT_TYPE.get(k).equals(adt0.getVisualizerType()));
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/** Tests setVisualizerType() with null value. <p>Set visualizerType to null. Test that stacktrace is printed to System.out, but error is not thrown */
	@Test
	public void testSetVisualizerTypeToNullThrowsError() throws Exception {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		adt0.setVisualizerType(null);


		System.setErr(old_out);

		assertTrue(" setVisualizerType(null) did not print stacktrace when sent null",
				bytes.size() != 0);
	}

	/** Tests setVisualizerType() with invalid type. <p>Set visualizerType to invalid type. Test that stacktrace is printed to System.out, but error is not thrown */
	@Test
	public void testSetVisualizerTypeWithInvalidType() throws Exception {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		adt0.setVisualizerType("abcde");


		System.setErr(old_out);

		assertTrue(" setVisualizerType(null) did not print stacktrace when sent null",
				bytes.size() != 0);
	}
	
	/** Tests that ADTVisualizer can be appropriately set to graph visualization. <p>Call setGraph(). Test that getVisualizerType is equal to "graph1" */
	@Test
	public void testSetGraph() {		
		boolean thrown = false;
		adt0.setGraph();
		
		
		try {
			assertTrue("setgraph() did not set visualization type to \"graph\"",
					adt0.getVisualizerType().equals("graphl"));
		} catch (NullPointerException e) {
			thrown = true;
		}
		
		if(thrown) {
			fail("getVisualizer() returned a null value");
		}
		
		adt0.setGraph();

	}
	
	/** Tests that ADTVisualizer can be appropriately set to tree visualization. <p>Call setTree(). Test that getVisualizerType is equal to "tree" */
	@Test
	public void testSetTree() {		
		boolean thrown = false;
		adt0.setTree();
		
		
		try {
			assertTrue("setTree() did not set visualization type to \"tree\"", adt0
					.getVisualizerType().equals("tree"));			
		} catch (NullPointerException e) {
			thrown = true;
		}
		
		if(thrown) {
			fail("getVisualizer() returned a null value");
		}
		
		adt0.setGraph();
	}

	/** Tests that ADTVisualizer can be appropriately set to array visualization. <p>Call setArray(). Test that getVisualizerType is equal to "AList" */
	@Test
	public void testSetArray() {		
		boolean thrown = false;
		adt0.setArray();
		
		
		try {
			assertTrue("setArray() did not set visualization type to \"array\"", adt0
					.getVisualizerType().equals("AList"));			
		} catch (NullPointerException e) {
			thrown = true;
		}
		
		if(thrown) {
			fail("getVisualizer() returned a null value");
		}
		
		adt0.setGraph();
	}
	
	
	/** 
	 * Tests that ADTVisualizer can be appropriately set to stack visualization. <p>Call setStack(). Test that getVisualzerType is equal to "stack" 
	 */
	@Test
	public void testSetStack() {
		boolean thrown = false;
		adt0.setStack();
		
		
		try {
			assertTrue("setStack() did not set visualization type to \"tree\"", adt0
					.getVisualizerType().equals("stack"));			
		} catch (NullPointerException e) {
			thrown = true;
		}
		
		if(thrown) {
			fail("getVisualizer() returned a null value");
		}
		
		adt0.setGraph();
	}

	/**Tests that ADTVisualizer can be appropriately set to queue visualization. <p> Call setQueue(). Test that getVisualzerType is equal to "queue" */
	@Test
	public void testSetQueue() {
		boolean thrown = false;
		adt0.setQueue();
	
		
		
		try {
			assertTrue("setqueue() did not set visualization type to \"queue\"",
					adt0.getVisualizerType().equals("queue"));
		} catch (NullPointerException e) {
			thrown = true;
		}
		
		if(thrown) {
			fail("getVisualizer() returned a null value");
		}
				adt0.setGraph();
	}

	/** Tests that getGraphAdjList_Representation returns a valid JSON. <p>Call getGraphAdjList_Representation() with a graph with an edge. Test that valid JSON was returned */
	@Test
	public void testGetGraphAdjList_Representation() {
		ADTVisualizer<String> adt = new ADTVisualizer<String>();
		Edge edge = new Edge(1, "A");
		edge.setVertex("B");
		
		GraphList<String> adjList = new GraphList<String>();
		adjList.setSourceVertex(new SLelement<String>("B", "B"));
		adjList.addEdge(edge);
		
		
		
		HashMap<String, GraphList<String>> hash = new HashMap<String, GraphList<String>>();
		hash.put("B", adjList);
		
		adt.setAdjacencyList(hash);
		
		String jsonString = adt.getGraphAdjList_Representation(hash);

		try {
			JSONObject o = (JSONObject) parser.parse(jsonString);
		} catch (ParseException e) {
			fail("getGraphAdjList_Representation did not create a valid JSON");
		}

	}

	/** Tests that getSLRepresentation() returns a valid JSON. <p>Call getSLRepresentation() on a valid ADTVisualizer. Test that a valid JSON was returned */
	@Test
	public void testGetSLRepresentation() {

		JSONObject o;

		try {
			o = (JSONObject) parser.parse(adt0.getSLRepresentation(sle1));

		} catch (ParseException e) {
			fail("getSLRepresentation() did not return valid JSON");
		}

	}

	/** Tests that getDLRepresentation() returns a valid JSON. <p>Call getDLRepresentation() on a valid ADTVisualizer. Test that a valid JSON was returned */
	@Test
	public void testGetDLRepresentation() {
		JSONObject o;

		try {
			o = (JSONObject) parser.parse(adt0.getDLRepresentation(dle1));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getDLRepresentation() did not return valid JSON");
		}
	}

	/** Tests that getTreeRepresentation() returns a valid JSON. <p>Call getTreeRepresentation() on a valid ADTVisualizer. Test that a valid JSON was returned */
	@Test
	public void testGetTreeRepresentation() {
		JSONObject o;

		try {
			o = (JSONObject) parser.parse(adt0.getTreeRepresentation(tree1));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getDLRepresentation() did not return valid JSON");
		}
	}
	
	/** Tests that getALRepresentation() returns a valid JSON when array is null. <p>Call getALRepresentation() on a valid ADTVisualizer. Test that a valid JSON was returned */
	@Test
	public void testGetALRepresentationWithNullArray() {
		JSONObject o;

		adt1.setArray(elementArrayNull);
		
		try {
			o = (JSONObject) parser.parse(adt1.getALRepresentation());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getDLRepresentation() did not return valid JSON");
		}
	}

	/** Tests that getALRepresentation() returns a valid JSON when array is not null. <p>Call getALRepresentation() on a valid ADTVisualizer. Test that a valid JSON was returned */
	@Test
	public void testGetALRepresentationWithValidArray() {
		JSONObject o;

		adt1.setArray(elementArray);
		
		try {
			o = (JSONObject) parser.parse(adt1.getALRepresentation());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getDLRepresentation() did not return valid JSON");
		}
	}
	

	/** Tests that generateJSON_SLL() returns a valid JSON. <p>Create linked lists of nodes and links for SLelements. Test that generateJSON_SLL returns valid JSON */
	@Test
	public void testGenerateJSON_SLL() {

		LinkedList<SLelement<String>> nodes = new LinkedList<SLelement<String>>();
		nodes.add(sle1);
		nodes.add(sle2);

		LinkedList<SLelement<String>> links = new LinkedList<SLelement<String>>();
		links.add(sle1);
		links.add(sle2);

		try {
			JSONObject o = (JSONObject) parser.parse(adt0.generateJSON_SLL(
					nodes, links));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("generateJSON_SLL() did not generate valid JSON");
		}
	}

	/** Tests that generateJSON_DLL() returns a valid JSON. <p>Create linked lists of nodes and links for DLelements. Test that generateJSON_DLL returns valid JSON */
	@Test
	public void testGenerateJSON_DLL() {

		LinkedList<DLelement<String>> nodes = new LinkedList<DLelement<String>>();
		nodes.add(dle1);
		nodes.add(dle2);

		LinkedList<DLelement<String>> links = new LinkedList<DLelement<String>>();
		links.add(dle1);
		links.add(dle2);

		try {
			JSONObject o = (JSONObject) parser.parse(adt0.generateJSON_DLL(
					nodes, links));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("generateJSON_SLL() did not generate valid JSON");
		}

	}

	/** Tests that getJSON_BinaryTree() returns a valid JSON. <p>Send tree of TreeElements to getJSON_BinaryTree(). Test that valid JSON is returned */
	@Test
	public void testGetJSON_BinaryTree() {

		LinkedList<TreeElement<String>> nodes = new LinkedList<TreeElement<String>>();
		nodes.add(tree1);
		nodes.add(tree2);

		LinkedList<TreeElement<String>> links = new LinkedList<TreeElement<String>>();
		links.add(tree1);
		links.add(tree2);

		try {
			JSONObject o = (JSONObject) parser.parse(adt0.getJSON_BinaryTree(
					nodes, links));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getJSON_BinaryTree() did not generate valid JSON");
		}

	}

	/** Not tested here, tested as part of testGetAdjacencyList. */
	@Test
	public void testGenerateJSON_Graph() {
	}

	
	/** Test compare returns correct ordering for two elements. <p>Create two elements and pass to compare(). Test that compare() returns correct result. */
	@Test
	public void testCompareWithDifferentElements() {
		SLelement<String> sle1 = new SLelement<String>("a", "a");
		SLelement<String> sle2 = new SLelement<String>("b", "b");
		
		assertEquals("compare returns incorrect value", -1,
				adt0.compare(sle1, sle2));
	}


	
	/** Not tested here, tested as part of testSetMapOfLinks() */
	@Test
	public void testGetMapOfLinks() {
		// tested in testSetMapOfLinks()
	}

	/** Test that setMapOfLinks() correctly sets HashMap. <p>Create HashMap and send to setMapOfLinks. Test that getMapOfLinks returns same HashMap */
	@Test
	public void testSetMapOfLinks() {
		HashMap<Element<String>, HashMap<String, Element<String>>> map = new HashMap<Element<String>, HashMap<String,Element<String>>>(); 
				
				
		HashMap<String, Element<String>> link = new HashMap<String, Element<String>>();
		link.put("1", new SLelement("A", "A"));

		map.put(new SLelement("1", "1"), link);

		adt0.setMapOfLinks(map);

		assertTrue(
				"getMapOfLinks() does not return same value as setMapOfLinks()",
				adt0.getMapOfLinks().equals(map));

	}

	/** Not tested here, tested in testSetAdjacencyList */
	@Test
	public void testGetAdjacencyList() {
		// tested in setAdjacencyList
	}

	/** Test setAdjacenclyList() correctly sets HashMap. <p>Create send a HashMap<String, SLeLement<String> and send to setAdjacencyList(). Test that same HashMap is resturned from getAdjacencyList() */
	@Test
	public void testSetAdjacencyList() {
		HashMap<String, GraphList<String>> adjList = new HashMap<String, GraphList<String>>();

		adjList.put("A", new GraphList<String>());

		adt0.setAdjacencyList(adjList);

		assertTrue(
				"getAdjacencyList did not return same value as setAdjacencyList",
				adjList.equals(adt0.getAdjacencyList()));
		assertNotNull("getAdjacencyList returned null when should have returned an empty GraphList", adt0.getAdjacencyList());
	}

	/** Not tested here, tested in testSetVisualizeJSON */
	@Test
	public void testIsVisualizeJSON() {
		// tested in testSetVisualizeJSON()
	}

	/** Test whether setVisualizeJSON() correctly keeps boolean sent to it. <p>Call setVisualizeJSON with "true" and "false". Test that isVisualizeJSON() returns the same boolean that was called with setVisualizeJSON */
	@Test
	public void testSetVisualizeJSON() {
		adt0.setVisualizeJSON(true);

		assertTrue("setVisualizeJSON() did not set to true",
				adt0.isVisualizeJSON());

		adt0.setVisualizeJSON(false);

		assertFalse("setVisualizeJSON() did not set to false",
				adt0.isVisualizeJSON());

	}
	

	/** NOT TESTED */
	@Test
	public void testPreOrder() {
		fail("ADTVisualizer preOrder() has not been tested. ");
	}

	
	// protected
	// @Test
	// public void testGetVisualizerIdentifier() {
	// fail("Not yet implemented");
	// }

	// protected
	// @Test
	// public void testSetVisualizerIdentifier() {
	// fail("Not yet implemented");
	// }

}
