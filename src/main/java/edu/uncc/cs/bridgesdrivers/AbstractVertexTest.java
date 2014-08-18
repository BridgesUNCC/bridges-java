/**
 * 
 */
package edu.uncc.cs.bridgesdrivers;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridges.*;

/**
 * @author mihai mehedint
 *
 */
public class AbstractVertexTest {
	static GraphVisualizer<?> graph ;
	static Vertex<?> testVertex1;
	static Vertex<?> testVertex2;
	static Vertex<?> testVertex;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		graph = new GraphVisualizer<Object>();
		testVertex1 = new Vertex<String>("vertex1", graph);
		testVertex2 = new Vertex<String>("vertex2", graph);
		testVertex = new Vertex<String>("vertex1", graph);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#hashCode()}.
	 */
	@Test
	public final void testHashCode() {
		assertEquals("The hashcode generated a different number.", 3.5163422E8, 31+testVertex1.getIdentifier().hashCode(), 0.00001);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#AbstractVertex(java.lang.Object)}.
	 */
	@Test
	public final void testAbstractVertex() {
		
		assertNotNull("The vertex was not created.",testVertex1);
		assertNotEquals("There is an error in creating different vertex objects.",testVertex1, testVertex2);
		assertNotSame("The vertices refer to the same object",testVertex1, testVertex2);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getIdentifier()}.
	 */
	@Test
	public final void testGetIdentifier() {
		assertNotNull("The string identifier of vertex is null.",testVertex1.getIdentifier());
		assertEquals("The identifier has an unexpected value.", "vertex1", testVertex1.getIdentifier());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getColor()}.
	 */
	@Test
	public final void testGetColor() {
		assertEquals("The default color of vertex is not set ot black", "black", testVertex1.getColor());
		testVertex1.setColor("red");
		assertEquals("The setColor() of vertex is not working of getColor() returns the wrog color.", "red", testVertex1.getColor());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#setColor(java.lang.String)}.
	 */
	@Test
	public final void testSetColor() {
		testVertex1.setColor("yellow");
		assertEquals("The setColor() of vertex is not working of getColor() returns the wrog color.", "yellow", testVertex1.getColor());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getShape()}.
	 */
	@Test
	public final void testGetShape() {
		assertEquals("The default shape is not set correctly.", "circle", testVertex1.getShape());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#setShape(java.lang.String)}.
	 */
	@Test
	public final void testSetShape() {
		testVertex1.setShape("rect");
		assertEquals("The default shape is not set correctly.", "rect", testVertex1.getShape());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getSize()}.
	 */
	@Test
	public final void testGetSize() {
		assertEquals("The default size has an unexpected value.", 1, testVertex1.getSize(), 0.00001);
		testVertex1.setSize(0.6);
		assertEquals("The getSize() or setSize() method doesn't work.", 0.6, testVertex1.getSize(), 0.00001);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#setSize(double)}.
	 */
	@Test
	public final void testSetSize() {
		testVertex1.setSize(0.6);
		assertEquals("The getSize() or setSize() method doesn't work.", 0.6, testVertex1.getSize(), 0.00001);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getOpacity()}.
	 */
	@Test
	public final void testGetOpacity() {
		assertEquals("The default value of opacity is not set correctly.", 1.0, testVertex1.getOpacity(), 0.00001);
		testVertex1.setOpacity(0.2);
		assertEquals("The setOpacity() or getOpacity() methods gives an unexpected result.", 0.2, testVertex1.getOpacity(), 0.00001);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#setOpacity(double)}.
	 */
	@Test
	public final void testSetOpacity() {
		testVertex1.setOpacity(0.2);
		assertEquals("The setOpacity() or getOpacity() methods gives an unexpected result.", 0.2, testVertex1.getOpacity(), 0.00001);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject() {
		assertFalse("The vertex objects are equal.", testVertex1.equals(testVertex2));
		assertTrue("The vertex objects are not equal.", testVertex.equals(testVertex1));
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getRepresentation()}.
	 */
	@Test
	public final void testGetRepresentation() {
		assertNotNull("The representation returns a null object", testVertex1.getRepresentation());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#compareTo(edu.uncc.cs.bridges.AbstractVertex)}.
	 */
	@Test
	public final void testCompareTo() {
		AbstractVertex<Follower> nullVertex = null;
		AbstractVertex vertex1 = new Vertex("aTest1", graph);
		AbstractVertex vertex2 = new Vertex("aTest2", graph);
		assertEquals("The comparison to a null vertex is not 0.", 0, vertex1.compareTo(nullVertex));
		assertNotEquals("The comparison to a null vertex is not 0.", 0, vertex1.compareTo(vertex2));
	}

}
