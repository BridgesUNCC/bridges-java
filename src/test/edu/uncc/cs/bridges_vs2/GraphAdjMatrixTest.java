import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import bridges_v21.base.Edge;
import bridges_v21.base.Element;
import bridges_v21.base.GraphAdjList;
import bridges_v21.base.GraphAdjMatrix;


public class GraphAdjMatrixTest {
	
	static GraphAdjMatrix<String, String> g1Vertex = new GraphAdjMatrix<String, String>(1);
	static GraphAdjMatrix<String, String> g2Vertex = new GraphAdjMatrix<String, String>(2);
	static GraphAdjMatrix<String, String> g3withEdge = new GraphAdjMatrix<String, String>(2);
	

	
	/** Setup static variables for use in later tests. */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		g1Vertex.addVertex("A", "a");
		g2Vertex.addVertex("A", "a");
		g3withEdge.addVertex("A", "a");
		g3withEdge.addVertex("B", "b");
		g3withEdge.addEdge("A", "B", 1);
		
	}
	
	
	

	@Test
	/** test whether adding a vertex when the matrix is full prints an error */
	public void testAddVertexWhenMatrixIsFullPrintsException() {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		g1Vertex.addVertex("B", "b");
		
		System.setErr(old_out);

		assertTrue("adding an vertex to full matrix did not throw error",
				bytes.size() != 0);
	}
	
	@Test
	/** test whether get Vertices returns vertices */
	public void testGetVertices(){
		HashMap<String, Element<String>> vertices = g3withEdge.getVertices();
		assertTrue("getVertices() did not return correct terminating edge", vertices.get("A").getValue().equals("a"));
		assertTrue("getVertices() did not return correct terminating edge", vertices.get("B").getValue().equals("b"));
	}

	@Test
	/** test whether get adjacencyMatrix returns all vertices */
	public void testGetAdjacencyMatrix(){
		HashMap<String, HashMap<String, Integer>> adjMatrix = g3withEdge.getAdjacencyMatrix();
		HashMap<String, Integer> vertices = adjMatrix.get("A");
		
		assertTrue("getVertices() did not return correct terminating edge", vertices.keySet().contains("A"));
		assertTrue("getVertices() did not return correct terminating edge", vertices.keySet().contains("B"));
	}
	
	@Test
	/** Test addEdge prints error when terminating edge does not exist*/
	public void testAddEdgeWithNonExistantVertexPrintsNullPointerException(){		
		
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintStream old_out = System.err;

		System.setErr(new PrintStream(bytes));

		g1Vertex.addEdge("A", "b", 1);
				
		System.setErr(old_out);

		assertTrue("adding edge to nonexistent vertex did not print NullPointerException",
				bytes.size() != 0);
	}

	
	
}
