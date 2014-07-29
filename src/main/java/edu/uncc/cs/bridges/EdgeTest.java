package edu.uncc.cs.bridges;

import static org.junit.Assert.*;

import org.junit.Test;
import edu.uncc.cs.bridges.*;

public class EdgeTest {

	@Test
	public final void testEdgeAbstractVertexAbstractVertexString() {
		GraphVisualizer gv =new GraphVisualizer();
		Edge anEdge = new Edge(new Vertex("Bob",gv), new Vertex("Jane", gv), "movie");
	}

	@Test
	public final void testEdgeAbstractVertexAbstractVertexStringDouble() {
		GraphVisualizer gv =new GraphVisualizer();
		Vertex Bob = new Vertex("Bob",gv);
		Vertex Jane = new Vertex("Jane",gv);
		Edge anEdge1 = new Edge(Bob,Jane,"movie1",12.3);
		Edge anEdge2 = new Edge(Bob,Jane,"movie1",15);
		Edge anEdge3=null;
		assertNotNull("Error while creating the edge: null object", anEdge1);
		assertNull("Error",anEdge3);
		assertNotEquals("Two edges between the same vertices must be different", anEdge1, anEdge2);
		assertEquals("The source vertex is different than expected",Bob,anEdge1.source);
		assertEquals("The destination vertex is different than expected",Jane,anEdge1.destination);
	}

	@Test
	public final void testEdgeAbstractVertexAbstractVertexStringString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAbstractEdgeAbstractVertexAbstractVertexString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAbstractEdgeString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetIdentifier() {
		GraphVisualizer gv =new GraphVisualizer();
		Edge anEdge = new Edge(new Vertex("Bob",gv), new Vertex("Jane", gv), "movie");
		assertEquals("identifier error", "movie", anEdge.getIdentifier());
	}

	@Test
	public final void testGetWeight() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetWeightDouble() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetWeightAbstractVertexAbstractVertexString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetColor() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetColor() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetDash() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetDash() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetWidth() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetWidth() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetOpacity() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetOpacity() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetRepresentation() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCompareTo() {
		fail("Not yet implemented"); // TODO
	}

}
