import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.Edge;
import edu.uncc.cs.bridgesV2.base.GraphList;
import edu.uncc.cs.bridgesV2.base.SLelement;


public class GraphListTest {
	static GraphList<String> gl;
	


	/** Setup static variables for use in later tests. */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gl = new GraphList<String>();
	}

	
	@Test
	/** Test that getVertex() returns vertex that was assigned */
	public void testSetAndGetSourceVertex(){
		SLelement<String> v = new SLelement<String>("a", new SLelement<String>("B", "b"));
		gl.setSourceVertex(v);
		assertTrue("getVertex() did not return assigned vertex", gl.getSourceVertex().equals(v));
		
	}
	
	@Test
	/** Test that getAdjacencyList() returns adjacency list that was assigned */
	public void testSetAndGetAdjacencyList(){
		Edge e1 = new Edge(1, "a");
		Edge e2 = new Edge();
		SLelement<Edge> adj = new SLelement<Edge>(e1, new SLelement<Edge>("label", e2));
		gl.setAdjacencyList(adj);
		assertTrue("getVertex() did not return assigned adjacency list", gl.getAdjacencyList().equals(adj));
		
	}
	
	@Test
	/** Test that addEdge using Edge */
	public void testAddEdgeWithNoWeight(){
		Edge e1 = new Edge(1, "a");
		Edge e2 = new Edge();
		
		GraphList<String> gl = new GraphList<String>();
		
		gl.addEdge(e1);
		
		assertTrue("added edge when graphList had not prior edge added did not work", gl.getAdjacencyList().getValue().equals(e1));
	
		gl.addEdge(e2);
		
		assertTrue("added edge when graphList when prior edge was added did not work", gl.getAdjacencyList().getValue().equals(e2));

	}
	
	@Test
	/** Test addEdge using string and integer*/
	public void testAddEdgeWithStringAndInteger(){		
		GraphList<String> gl = new GraphList<String>();
		
		Edge e1 = new Edge(1, "A");
		
		gl.addEdge("A", 1);
		
		String vertex = gl.getAdjacencyList().getValue().getVertex();
		int weight = gl.getAdjacencyList().getValue().getWeight();
		
		
		assertTrue("added edge when graphList had not prior edge added did not work", e1.getVertex().equals(vertex));
		assertTrue("added edge when graphList had not prior edge added did not work", e1.getWeight() == weight);
	
	}

	
	@Test
	/** Test that getAdjacencyList() returns adjacency list that was assigned */
	public void testAddEdgeByPassingVertexAndWeight(){		
		GraphList<String> gl = new GraphList<String>();
		
		gl.addEdge("a", 1);
		Edge e1 = gl.getAdjacencyList().getValue();
		
		
		assertTrue("added edge did not return correct vertex", gl.getAdjacencyList().getValue().getVertex().equals("a"));
		assertTrue("added edge did not return correct weight", gl.getAdjacencyList().getValue().getWeight() == 1);
	
		gl.addEdge("b", 2);
		Edge e2 = gl.getAdjacencyList().getValue();
		
		assertTrue("added edge did not return correct vertex", gl.getAdjacencyList().getValue().getVertex().equals("b"));
		assertTrue("added edge did not return correct weight", gl.getAdjacencyList().getValue().getWeight() == 2);

	}


}
