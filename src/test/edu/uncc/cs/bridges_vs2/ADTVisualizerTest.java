

import static org.junit.Assert.*;

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
import edu.uncc.cs.bridgesV2.base.DLelement;
import edu.uncc.cs.bridgesV2.base.Element;
import edu.uncc.cs.bridgesV2.base.SLelement;
import edu.uncc.cs.bridgesV2.base.TreeElement;

public class ADTVisualizerTest {
	static ADTVisualizer<String> adt0;
	static ADTVisualizer<String> adt1;
	static ADTVisualizer<String> adt2;
	
	static HashMap<String, Element<?>> hash;
	static Element<?>[] elementArray;

	static SLelement<String> sle1;
	static SLelement<String> sle2;

	static DLelement<String> dle1;
	static DLelement<String> dle2;
	
	static TreeElement<String> tree1;
	static TreeElement<String> tree2;
	
	static JSONParser parser;

	
	
	@BeforeClass
	public static void BeforeClass() {
		try {
			Element<String> e1 = new Element<String>("a");
			Element<String> e2 = new Element<String>("b");
			Element<String> e3 = new Element<String>("c");
			hash = new HashMap<String, Element<?>>();
			elementArray = new Element[2];
			
			hash.put("1", e3);
			
			elementArray[0] = e1;
			elementArray[1] = e2;
			
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
			
			parser = new JSONParser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

		
	/** only accepts hashmap or element arrays as valid object types 
	 * @throws Exception */
	@Test
	public void testValidateTypeWithValidInput() throws Exception{
		ADTVisualizer<String> adt3 = new ADTVisualizer<String>(hash);
		assertNotNull(adt3);
	}
	
	@Test(expected = Exception.class)
	public void testValidateTypeWithInvalidInput() throws Exception{
		ADTVisualizer<String> adt3 = new ADTVisualizer<String>("acvbde");
	}


	@Test
	public void testGetVisualizerType() {
		//this constructor gets a default setting to graphl
		assertTrue("getVisualizerType() does not return a valid type from ADT_TYPE", adt0.ADT_TYPE.containsValue(adt0.getVisualizerType()));
		
		//these constructors do not have a default setting
		try {
			assertTrue("getVisualizerType() does not return a valid type from ADT_TYPE", adt1.ADT_TYPE.containsValue(adt1.getVisualizerType()));
			assertTrue("getVisualizerType() does not return a valid type from ADT_TYPE", adt2.ADT_TYPE.containsValue(adt2.getVisualizerType()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue("visualizer set to null did not throw error", e instanceof NullPointerException);;
		}

	}

	@Test
	public void testSetVisualizerTypeToValidKey() {
		Set<String> keys = adt0.ADT_TYPE.keySet();
		
		try {
			for(String k : keys) {
					adt0.setVisualizerType(k);
					String target = adt0.ADT_TYPE.get(k);
	
					assertTrue("getVisualizerType does not equal string passed to setVisualizerType", adt0.ADT_TYPE.get(k).equals(adt0.getVisualizerType()));			
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Test(expected = Exception.class)
	public void testSetVisualizerTypeToNullThrowsError() throws Exception {
		adt0.setVisualizerType(null);
	}

	
	@Test
	public void testSetGraph() {
		adt0.setGraph();
		assertTrue("setgraph() did not set visualization type to \"graph\"", adt0.getVisualizerType().equals("graphl"));
		adt0.setGraph();
	}

	@Test
	public void testSetTree() {
		adt0.setTree();
		assertTrue("setTree() did not set visualization type to \"tree\"", adt0.getVisualizerType().equals("tree"));
		adt0.setGraph();
	}

	@Test
	public void testSetStack() {
		adt0.setStack();
		assertTrue("setStack() did not set visualization type to \"stack\"", adt0.getVisualizerType().equals("stack"));
		adt0.setGraph();
	}

	@Test
	public void testSetQueue() {
		adt0.setQueue();
		assertTrue("setqueue() did not set visualization type to \"queue\"", adt0.getVisualizerType().equals("queue"));
		adt0.setGraph();
	}

	@Test
	public void testGetGraphAdjList_Representation() {
		
		
		HashMap<String, SLelement<String>> hash = new HashMap<String, SLelement<String>>();
		
		hash.put("1", sle1);
		hash.put("2", sle2);
		
		String jsonString = adt0.getGraphAdjList_Representation(hash);
		
		
		
		try {
			JSONObject o = (JSONObject) parser.parse(jsonString);
		} catch (ParseException e) {
			fail("getGraphAdjList_Representation did not create a valid JSON");
		}
		
	}

	@Test
	public void testGetSLRepresentation() {
			
		JSONObject o;
		
		try {
			o = (JSONObject) parser.parse(adt0.getSLRepresentation(sle1));

		} catch (ParseException e) {
			fail("getSLRepresentation() did not return valid JSON");
		}
		
	}

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

	@Test
	public void testPreOrder() {
		// not sure how to test this
		fail("Not yet implemented");
	}

	@Test
	public void testGenerateJSON_SLL() {
		
		LinkedList<SLelement<String>> nodes = new LinkedList<SLelement<String>>();
		nodes.add(sle1);
		nodes.add(sle2);
		
		LinkedList<SLelement<String>> links = new LinkedList<SLelement<String>>();
		links.add(sle1);
		links.add(sle2);
		
		try {
			JSONObject o = (JSONObject) parser.parse(adt0.generateJSON_SLL(nodes, links));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("generateJSON_SLL() did not generate valid JSON");
		}
	}

	@Test
	public void testGenerateJSON_DLL() {
		
		LinkedList<DLelement<String>> nodes = new LinkedList<DLelement<String>>();
		nodes.add(dle1);
		nodes.add(dle2);
		
		LinkedList<DLelement<String>> links = new LinkedList<DLelement<String>>();
		links.add(dle1);
		links.add(dle2);
		
		try {
			JSONObject o = (JSONObject) parser.parse(adt0.generateJSON_DLL(nodes, links));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("generateJSON_SLL() did not generate valid JSON");
		}

	}

	@Test
	public void testGetJSON_BinaryTree() {
		
		LinkedList<TreeElement<String>> nodes = new LinkedList<TreeElement<String>>();
		nodes.add(tree1);
		nodes.add(tree2);
		
		LinkedList<TreeElement<String>> links = new LinkedList<TreeElement<String>>();
		links.add(tree1);
		links.add(tree2);
		
		
		try {
			JSONObject o = (JSONObject) parser.parse(adt0.getJSON_BinaryTree(nodes, links));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("getJSON_BinaryTree() did not generate valid JSON");
		}

	}

	@Test
	public void testGenerateJSON_Graph() {
		
		LinkedList<SLelement<String>> nodes = new LinkedList<SLelement<String>>();
		nodes.add(sle1);
		nodes.add(sle2);
		
		LinkedList<SLelement<String>> links = new LinkedList<SLelement<String>>();
		links.add(sle1);
		links.add(sle2);
		
		
		try {
			JSONObject o = (JSONObject) parser.parse(adt0.generateJSON_Graph(nodes, links));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("generateJSON_Graph() did not generate valid JSON");
		}
	}

	@Test
	public void testCompare() {
		Element<String> e1 = new Element<String>("a");
		Element<String> e2 = new Element<String>("b");
		


		
		assertEquals("compare returns incorrect value", -1, adt0.compare(e1, e2));
	}


	@Test
	public void testGetMapOfLinks() {
		//tested in testSetMapOfLinks()
	}

	@Test
	public void testSetMapOfLinks() {
		HashMap<Element<String>, HashMap<String, Element<String>>> map = new HashMap<Element<String>, HashMap<String,Element<String>>>();
		
		 HashMap<String,Element<String>> link = new HashMap<String, Element<String>>();
		 link.put("A", new Element<String>("A"));
		 
		
		map.put(new Element<String>("1"), link);
		
		adt0.setMapOfLinks(map);
		
		assertTrue("getMapOfLinks() does not return same value as setMapOfLinks()", adt0.getMapOfLinks().equals(map));
		
	}

	@Test
	public void testGetAdjacencyList() {
		//tested in setAdjacencyList
	}

	@Test
	public void testSetAdjacencyList() {
		HashMap<String, SLelement<String>> adjacencyList = new HashMap<String, SLelement<String>>();
		
		adjacencyList.put("A", new SLelement<String>("A", "A"));
		
		
		adt0.setAdjacencyList(adjacencyList);
		
		
		assertTrue("getAdjacencyList did not return same value as setAdjacencyList", adjacencyList.equals(adt0.getAdjacencyList()));
	}

	@Test
	public void testIsVisualizeJSON() {
		//tested in testSetVisualizeJSON()
	}

	@Test
	public void testSetVisualizeJSON() {
		adt0.setVisualizeJSON(true);
		
		assertTrue("setVisualizeJSON() did not set to true", adt0.isVisualizeJSON());
		
		adt0.setVisualizeJSON(false);
		
		assertFalse("setVisualizeJSON() did not set to false", adt0.isVisualizeJSON());

	}

	//protected
//	@Test
//	public void testGetVisualizerIdentifier() {
//		fail("Not yet implemented");
//	}

	//protected
//	@Test
//	public void testSetVisualizerIdentifier() {
//		fail("Not yet implemented");
//	}

}
