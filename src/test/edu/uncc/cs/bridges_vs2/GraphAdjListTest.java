import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.apache.http.impl.auth.GGSSchemeBase;
import org.junit.BeforeClass;
import org.junit.Test;

import bridges_v21.base.*;

public class GraphAdjListTest {
	static GraphAdjList<String, String> gl;
	


	/** Setup static variables for use in later tests. */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gl = new GraphAdjList<String, String>();

	}

	
	@Test
	/** Test that getVertex() returns vertex that was assigned */
	public void testSetAndGetSourceVertex(){
		SLelement<String> v = new SLelement<String>("a", new SLelement<String>("B", "b"));
		gl.addVertex("B", "b");
		assertTrue("getVertex() did not return assigned vertex", gl.getVertices().containsKey("B"));
		
	}
	
	@Test
	/** Test that getAdjacencyList() returns entire adjacency list that was assigned */
	public void testSetAndGetWholeAdjacencyList(){
		GraphAdjList<String, Integer> g = new GraphAdjList<String, Integer>();
	
		g.addVertex("C", 1);
		g.addVertex("D", 2);
		g.addVertex("A", 3);
		g.addVertex("B", 4);
						
		g.addEdge("C", "D", 10);
		g.addEdge("A", "B", 12);

		HashMap<String, SLelement<Edge<String>>> adjList = g.getAdjacencyList();
		
		Edge<String> e1 = adjList.get("C").getValue();
		Edge<String> e2 = adjList.get("A").getValue();
		
		assertTrue("graphAdjList did not return correct terminating vertex for edge", e1.getVertex().equals("D"));
		assertTrue("graphAdjList did not return correct terminating vertex for edge", e2.getVertex().equals("B"));
		assertTrue("graphAdjList with 4 vertices does not have 4 elements in table", adjList.size() == 4);
		
	}
	

	@Test
	/** Test that getAdjacencyList(key) returns adjacency list for one vertex */
	public void testSetAndGetOneAdjacencyList(){
		GraphAdjList<String, Integer> g = new GraphAdjList<String, Integer>();
	
		g.addVertex("C", 1);
		g.addVertex("D", 2);
		g.addVertex("A", 3);
		g.addVertex("B", 4);
						
		g.addEdge("C", "D", 10);
		g.addEdge("A", "B", 12);

		SLelement<Edge<String>> adjList = g.getAdjacencyList("C");
		
		Edge<String> e1 = adjList.getValue();
		
		assertTrue("graphAdjList did not return correct terminating vertex for edge", e1.getVertex().equals("D"));
		
	}
	

	@Test
	/** Test addEdge using string and integer*/
	public void testAddEdgeWithNonExistantVertexPrintsNullPointerException(){		
		GraphAdjList<String, String> gl = new GraphAdjList<String, String>();
		
		gl.addVertex("A", "a");
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		gl.addEdge("A", "b", 1);
				
		System.setErr(old_out);

		assertTrue("adding edge to nonexistent vertex did not print NullPointerException",
				bytes.size() != 0);
	}


}
