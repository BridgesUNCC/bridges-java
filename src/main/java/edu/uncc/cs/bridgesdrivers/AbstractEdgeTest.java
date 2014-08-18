/**
 * 
 */
package edu.uncc.cs.bridges;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author mihai
 *
 */
public class AbstractEdgeTest {

	
	
	/**
	 * @throws java.lang.Exception
	 */
	static GraphVisualizer graph;
	static Vertex bob, jane, bill;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		graph=new GraphVisualizer();
		bob = new Vertex("Bob", graph);
		jane = new Vertex("Jane", graph);
		bill = new Vertex("Bill", graph);
		bob.createEdge(jane);
	}
	
	@Rule
	  public ExpectedException exception = ExpectedException.none();

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#AbstractEdge(edu.uncc.cs.bridges.AbstractVertex, edu.uncc.cs.bridges.AbstractVertex, java.lang.String)}.
	 */
	@Test
	public final void testAbstractEdgeAbstractVertexAbstractVertexString() {
		assertNotNull("The edge value is null.", bob.getEdge(jane));
		bill.createEdge(jane);
		assertNull("The reversed edge value is not null.", jane.getEdge(bill));
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#AbstractEdge(java.lang.String)}.
	 */
	@Test
	public final void testAbstractEdgeString() {
		jane.createEdge(bob,"randWeight");
		assertNotEquals("The edge value was not set correctly.", 1, jane.getEdge(bob).getWeight(), 0.0001);
		assertEquals("The default edge value is not 1.",1, bob.getEdge(jane).getWeight(),0.0001);
		
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#getIdentifier()}.
	 */
	@Test
	public final void testGetIdentifier() {
		assertNotNull("The vertex identifier is null.", bob.getIdentifier());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#getWeight()}.
	 */
	@Test
	public final void testGetWeight() {
		jane.createEdge(bob,"randWeight");
		assertNotEquals("The edge value was not set correctly.", 1, jane.getEdge(bob).getWeight(), 0.0001);
		jane.createEdge(bob, 12);
		assertEquals("The edge value was not set correctly.", 12, jane.getEdge(bob).getWeight(), 0.0001);
		assertEquals("The default edge value is not 1.",1, bob.getEdge(jane).getWeight(),0.0001);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#setWeight(double)}.
	 */
	@Test
	public final void testSetWeightDouble() {
		jane.createEdge(bob,"randWeight");
		assertNotEquals("The edge value was not set correctly.", 1, jane.getEdge(bob).getWeight(), 0.0001);
		jane.createEdge(bob, 12.3);
		assertEquals("The edge value was not set correctly.", 12.3, jane.getEdge(bob).getWeight(), 0.0001);
		assertEquals("The default edge value is not 1.",1, bob.getEdge(jane).getWeight(),0.0001);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#setWeight(edu.uncc.cs.bridges.AbstractVertex, edu.uncc.cs.bridges.AbstractVertex, java.lang.String)}.
	 */
	@Test
	public final void testSetWeightAbstractVertexAbstractVertexString() {
		jane.createEdge(bob,"randWeight");
		assertNotEquals("The edge value was not set correctly.", 1, jane.getEdge(bob).getWeight(), 0.0001);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#getColor()}.
	 */
	@Test
	public final void testGetColor() {
		AbstractEdge edge1 = bob.getEdge(jane).setColor("red");
		assertEquals("The edge color is not correctly set.","red",edge1.getColor());
		assertNotNull("The color attribute is null.",edge1.getColor());
		
		AbstractEdge edge2 = (new Vertex("x",graph)).createEdge(new Vertex("y", graph));
		assertEquals("The edge attribute is not null","" ,edge2.getColor());
		
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#setColor(java.lang.String)}.
	 */
	@Test
	public final void testSetColor() {
		AbstractEdge edge1 = bob.getEdge(jane).setColor("red");
		assertEquals("The edge color is not correctly set.","red",edge1.getColor());
		assertNotNull("The color attribute is null.",edge1.getColor());
		
		AbstractEdge edge2 = (new Vertex("x",graph)).createEdge(new Vertex("y", graph));
		assertEquals("The edge attribute is not null","" ,edge2.getColor());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#getDash()}.
	 */
	@Test
	public final void testGetDash() {
		assertEquals("The dash pattern was not set correctly.", "", bob.getEdge(jane).getDash());
		bob.getEdge(jane).setDash(new double[]{5,10,5});
		assertEquals("The dash pattern was not set correctly.", "5.0,10.0,5.0", bob.getEdge(jane).getDash());
		assertNotNull("The dash pattern is null", bob.getEdge(jane).getDash());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#setDash(double[])}.
	 */
	@Test
	public final void testSetDash() {
		AbstractEdge edge2 = (new Vertex("x",graph)).createEdge(new Vertex("y", graph));
		assertEquals("The dash parameter was empty", "", edge2.getDash());
		assertEquals("The dash pattern was not set correctly.", "5.0,10.0,5.0", bob.getEdge(jane).getDash());
		bob.getEdge(jane).setDash(new double[]{});
		assertEquals("The dash pattern was not set correctly.", "5.0,10.0,5.0", bob.getEdge(jane).getDash());
		bob.getEdge(jane).setDash(new double[]{1,2,3});
		assertEquals("The dash pattern was not set correctly.", "1.0,2.0,3.0", bob.getEdge(jane).getDash());
		assertNotNull("The dash pattern is null", bob.getEdge(jane).getDash());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#getWidth()}.
	 */
	
	@Test
	public final void testGetWidth() {
		
		assertEquals("The default width parameter (value 1.0) is not set correctly.",1.0 ,bob.getEdge(jane).getWidth(), 0.0001);
		bob.getEdge(jane).setWidth(0);
		assertEquals("The width parameter is not set correctly.",0 ,bob.getEdge(jane).getWidth(), 0.0001);
		bob.getEdge(jane).setWidth(12.4);
		assertEquals("The width parameter is not set correctly.",12.4 ,bob.getEdge(jane).getWidth(), 0.0001);
		
	}
	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#setWidth(double)}.
	 */
	@Test
	public final void testSetWidth() {
		bob.getEdge(jane).setWidth(0);
		assertEquals("The width parameter is not set correctly.",0 ,bob.getEdge(jane).getWidth(), 0.0001);
		bob.getEdge(jane).setWidth(12.4);
		assertEquals("The width parameter is not set correctly.",12.4 ,bob.getEdge(jane).getWidth(), 0.0001);
		
		boolean thrown = true;
		exception.expect(InvalidValueException.class);
			bob.getEdge(jane).setWidth(-12.4);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#getOpacity()}.
	 */
	@Test
	public final void testGetOpacity() {
		assertEquals("The opacity default value is not set to 1.", 1.0, bob.getEdge(jane).getOpacity(), 0.0001);
		bob.getEdge(jane).setOpacity(0.1);
		assertEquals("The opacity attribute is incorrect.", 0.1, bob.getEdge(jane).getOpacity(), 0.0001);
		
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#setOpacity(double)}.
	 */
	@Test
	public final void testSetOpacity() {
		bob.getEdge(jane).setOpacity(0.5);
		assertEquals("The opacity attribute is incorrect.", 0.5, bob.getEdge(jane).getOpacity(), 0.0001);
		exception.expect(InvalidValueException.class);
		bob.getEdge(jane).setOpacity(-1);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#getRepresentation(java.util.Map)}.
	 */
	@Test
	public final void testGetRepresentation() {
		assertNotNull("GetRepresentation returned a null value.", graph.getRepresentation());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractEdge#compareTo(edu.uncc.cs.bridges.AbstractEdge)}.
	 */
	@Test
	public final void testCompareTo() {
		Vertex vertex3 = new Vertex("vertex3", graph);
		Vertex vertex4 = new Vertex("vertex4", graph);
		vertex3.createEdge(vertex4);
		AbstractEdge edge5 = null;
		assertEquals("The compareTo with null returned a anexpected value.", 0,vertex3.getEdge(vertex4).compareTo(edge5));
		bob.createEdge(vertex3);
		assertNotEquals("The compareTo with null returned a anexpected value.", 0,vertex3.getEdge(vertex4).compareTo(bob.getEdge(vertex3)));
		
	}

}
