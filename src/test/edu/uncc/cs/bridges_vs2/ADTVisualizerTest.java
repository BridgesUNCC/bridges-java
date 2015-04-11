package edu.uncc.cs.bridges_vs2;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.ADTVisualizer;
import edu.uncc.cs.bridgesV2.base.ArrayElement;
import edu.uncc.cs.bridgesV2.base.ArrayOfElement;
import edu.uncc.cs.bridgesV2.base.DLelement;
import edu.uncc.cs.bridgesV2.base.Edge;
import edu.uncc.cs.bridgesV2.base.Element;
import edu.uncc.cs.bridgesV2.base.SLelement;
import edu.uncc.cs.bridgesV2.base.TreeElement;

public class ADTVisualizerTest {
	static ADTVisualizer<String> adt0;
	static ADTVisualizer<String> adt1;
	static ADTVisualizer<String> adt2;

	static HashMap<String, Element<?>> hash;
//	static SLelement<String>[] elementArray;
	static ArrayElement<String>[] elementArray;

	static SLelement<String> sle1;
	static SLelement<String> sle2;

	static DLelement<String> dle1;
	static DLelement<String> dle2;

	static TreeElement<String> tree1;
	static TreeElement<String> tree2;

	static SLelement v1;
	static SLelement v2;

	static JSONParser parser;

	/** Set up static elements to for later tests. */
	@BeforeClass
	public static void BeforeClass() {
		try {
			Element<String> e1 = new Element<String>("a");
			Element<String> e2 = new Element<String>("b");
			Element<String> e3 = new Element<String>("c");
			hash = new HashMap<String, Element<?>>();
			
			elementArray = (ArrayElement<String>[]) new ArrayElement[10];
			
			hash.put("1", e3);


			sle1 = new SLelement<String>("a", "A");
			sle2 = new SLelement<String>("b", "B");
			sle1.setNext(sle2);

			dle1 = new DLelement<String>("aa", "AA");
			dle2 = new DLelement<String>("bb", "BB");
			dle1.setNext(dle2);

			tree1 = new TreeElement<String>("a tree");
			tree2 = new TreeElement<String>("b tree");
			tree1.setRight(tree2);

			adt0 = new ADTVisualizer<String>();
			adt1 = new ADTVisualizer<String>(elementArray);
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

		ADTVisualizer<String> adt3 = new ADTVisualizer<String>("acvbde");

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
				String target = adt0.ADT_TYPE.get(k);

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

	/** Tests that ADTVisualizer can be appropriately set to graph visualization. <p>Call setGraph(). Test that getVisualizerType is equal to "graph1" */
	@Test
	public void testSetGraph() {
		adt0.setGraph();
		assertTrue("setgraph() did not set visualization type to \"graph\"",
				adt0.getVisualizerType().equals("graphl"));
		adt0.setGraph();
	}
	
	/** Tests that ADTVisualizer can be appropriately set to tree visualization. <p>Call setTree(). Test that getVisualizerType is equal to "tree" */
	@Test
	public void testSetTree() {
		adt0.setTree();
		assertTrue("setTree() did not set visualization type to \"tree\"", adt0
				.getVisualizerType().equals("tree"));
		adt0.setGraph();
	}

	
	/** 
	 * Tests that ADTVisualizer can be appropriately set to stack visualization. <p>Call setStack(). Test that getVisualzerType is equal to "stack" 
	 */
	@Test
	public void testSetStack() {
		adt0.setStack();
		assertTrue("setStack() did not set visualization type to \"stack\"",
				adt0.getVisualizerType().equals("stack"));
		adt0.setGraph();
	}

	/**Tests that ADTVisualizer can be appropriately set to queue visualization. <p> Call setQueue(). Test that getVisualzerType is equal to "queue" */
	@Test
	public void testSetQueue() {
		adt0.setQueue();
		assertTrue("setqueue() did not set visualization type to \"queue\"",
				adt0.getVisualizerType().equals("queue"));
		adt0.setGraph();
	}

	/** Tests that getGraphAdjList_Representation returns a valid JSON. <p>Call getGraphAdjList_Representation() with a graph with an edge. Test that valid JSON was returned */
	@Test
	public void testGetGraphAdjList_Representation() {

		HashMap<String, SLelement<String>> hash = new HashMap<String, SLelement<String>>();

		hash.put("1", v1);
		hash.put("2", v2);

		Edge edge = new Edge(0, v2.getIdentifier());
		SLelement sleEdge = new SLelement<>("edge", edge);
		v1.setNext(sleEdge);

		String jsonString = adt0.getGraphAdjList_Representation(hash);

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

	/** NOT TESTED */
	@Test
	public void testPreOrder() {
		System.out.println("preOrder() has not been tested. ");
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
	public void testCompare() {
		Element<String> e1 = new Element<String>("a");
		Element<String> e2 = new Element<String>("b");

		assertEquals("compare returns incorrect value", -1,
				adt0.compare(e1, e2));
	}

	/** Not tested here, tested as part of testSetMapOfLinks() */
	@Test
	public void testGetMapOfLinks() {
		// tested in testSetMapOfLinks()
	}

	/** Test that setMapOfLinks() correctly sets HashMap. <p>Create HashMap and send to setMapOfLinks. Test that getMapOfLinks returns same HashMap */
	@Test
	public void testSetMapOfLinks() {
		HashMap<Element<String>, HashMap<String, Element<String>>> map = new HashMap<Element<String>, HashMap<String, Element<String>>>();

		HashMap<String, Element<String>> link = new HashMap<String, Element<String>>();
		link.put("A", new Element<String>("A"));

		map.put(new Element<String>("1"), link);

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
		HashMap<String, SLelement<String>> adjacencyList = new HashMap<String, SLelement<String>>();

		adjacencyList.put("A", new SLelement<String>("A", "A"));

		adt0.setAdjacencyList(adjacencyList);

		assertTrue(
				"getAdjacencyList did not return same value as setAdjacencyList",
				adjacencyList.equals(adt0.getAdjacencyList()));
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
