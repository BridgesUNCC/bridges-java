

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.Edge;

public class EdgeTest {
	static Edge e0;
	static Edge e1;
	static Edge e2;


	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		e0 = new Edge();
		e1 = new Edge(10);
		e2 = new Edge(20, "A");
	}

	@Test
	/** test setWeight() to integer */
	public void testSetWeightToInteger() {
		e0.setWeight(5);
		assertEquals("setWeight() did not set weight correctly", 5, e0.getWeight());
		e0.setWeight(0);
	}
	
	@Test
	/** test getWeight() method */
	public void testGetWeight() {
		assertEquals("getWeight() does not return correct weight", 0, e0.getWeight());
		assertEquals("getWeight() does not return correct weight", 10, e1.getWeight());
		assertEquals("getWeight() does not return correct weight", 20, e2.getWeight());
	}

	@Test
	/** test setVertex() method on a value*/
	public void testSetVertexToValue() {
		e0.setVertex("A");
		assertEquals("setVertex() did not set vertex to correct value", "A", e0.getVertex());
		e0.setVertex(null);
	}

	@Test
	/** test setVertex() method to null*/
	public void testSetVertexToNull() {
		e0.setVertex("A");
		e0.setVertex(null);
		assertNull("setVertex() did not set vertex to null", e0.getVertex());
	}

	
	@Test
	/** test getVertex() method */
	public void testGetVertex() {
		assertEquals("getVertex() did not return correct value", "A", e2.getVertex());
		assertNull("getVertex() did not return null", e0.getVertex());
		assertNull("getVertex() did not return null", e1.getVertex());
	}

	@Test
	/** test setEdge() */
	public void testSetEdge() {
		Edge e3 = new Edge();
		
		e3.setEdge(23, "C");
		assertEquals("setEdge() did not set weight correctly", 23, e3.getEdge().getWeight());
		assertEquals("setEdge() did not set vertex correctly", "C", e3.getEdge().getVertex());
		
		e3.setEdge(12, "D");
		assertEquals("setEdge() did not set weight correctly", 12, e3.getEdge().getWeight());
		assertEquals("setEdge() did not set vertex correctly", "D", e3.getEdge().getVertex());
	}

	@Test
	/** test getEdge() */
	public void testGetEdge() {
		assertEquals("getEdge() not returning correct object", e0, e0.getEdge());
		assertEquals("getEdge() not returning correct object", e1, e1.getEdge());
		assertEquals("getEdge() not returning correct object", e2, e2.getEdge());
		
	}

}
