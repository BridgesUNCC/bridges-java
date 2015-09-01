

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bridges_v21.base.*;


public class EdgeTest {
	static Edge e0;
	static Edge e1;
	static Edge e2;


	/** Setup static variables for use in later tests. */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		e0 = new Edge();
		e1 = new Edge(10);
		e2 = new Edge(20, "A");
	}

	@Test
	/** Test setWeight() correctly sets to new integer */
	public void testSetWeightToInteger() {
		e0.setWeight(5);
		assertEquals("setWeight() did not set weight correctly", 5, e0.getWeight());
		e0.setWeight(0);
	}
	
	@Test
	/** Test getWeight() returns correct value */
	public void testGetWeight() {
		assertEquals("getWeight() does not return correct weight", 0, e0.getWeight());
		assertEquals("getWeight() does not return correct weight", 10, e1.getWeight());
		assertEquals("getWeight() does not return correct weight", 20, e2.getWeight());
	}

	@Test
	/** Test setVertex() and make sure getVertex() returns same value*/
	public void testSetVertexToValue() {
		e0.setVertex("A");
		assertEquals("setVertex() did not set vertex to correct value", "A", e0.getVertex());
		e0.setVertex(null);
	}

	@Test
	/** Test setVertex() to null*/
	public void testSetVertexToNull() {
		e0.setVertex("A");
		e0.setVertex(null);
		assertNull("setVertex() did not set vertex to null", e0.getVertex());
	}

	
	@Test
	/** Test getVertex() returns correct value */
	public void testGetVertex() {
		assertEquals("getVertex() did not return correct value", "A", e2.getVertex());
		assertNull("getVertex() did not return null", e0.getVertex());
		assertNull("getVertex() did not return null", e1.getVertex());
	}

	@Test
	/** Test setEdge() and make sure getEdge() returns same value*/
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
	/** Test getEdge() returns correct value*/
	public void testGetEdge() {
		assertEquals("getEdge() not returning correct object", e0, e0.getEdge());
		assertEquals("getEdge() not returning correct object", e1, e1.getEdge());
		assertEquals("getEdge() not returning correct object", e2, e2.getEdge());	
	}
	
	
}
