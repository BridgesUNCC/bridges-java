package edu.uncc.cs.bridgesdrivers;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import edu.uncc.cs.bridges.*;

public class EdgeTest {
	
	static GraphVisualizer gv;
	static Vertex Bob;
	static Vertex Jane;
	static AbstractEdge temp;
	
	@Test
	public void setUp(){
		gv =new GraphVisualizer();
		Bob = new Vertex("Bob",gv);
		Jane = new Vertex("Jane",gv);
		temp=Bob.createEdge(Jane, 12.3);
		
	}
	@Test
	public final void testEdgeAbstractVertexAbstractVertexString() {
		temp=Bob.createEdge(Jane,15);
		assertEquals("The destination vertex is different than expected",temp, Bob.getEdge(Jane));
	}

	@Test
	public final void testEdgeAbstractVertexAbstractVertexStringDouble() {
		assertEquals("The destination vertex is different than expected",temp, Bob.getEdge(Jane));
		assertEquals("The identifier does not match the expected value", Jane, temp.eOutgoing);
		assertNull("The edge is not null", Jane.getEdge(Bob));
		assertEquals("The value of edge weight does not match the expected value.", 15, temp.getWeight());
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
		assertEquals("Incorrect identifier.", "BobToJane",temp.getIdentifier());
	}

	@Test
	public final void testGetWeight() {
		assertEquals("The edge weight parameter has an unexpected value", 15,temp.getWeight());
	}

	@Test
	public final void testSetWeightDouble() {
		assertEquals("The edge weight parameter is incorrect.",15,temp.getWeight());
	}

	@Test
	public final void testSetWeightAbstractVertexAbstractVertexString() {
		temp.setWeight(Bob,Jane,"randWeight");
		//assertEquals("The double value of edge weight is incorrect", ,temp.weight);
	}

	@Test
	public final void testGetColor() {
		temp.setColor("blue");
		assertEquals("The set color is incorrect","blue",temp.getColor());
		assertNotNull("The set color is incorrect",temp.getColor());
	}

	@Test
	public final void testSetColor() {
		temp.setColor("blue");
		assertEquals("The set color is incorrect","blue",temp.getColor());
		assertNotNull("The set color is incorrect",temp.getColor());
	}

	@Test
	public final void testGetDash() {
		double [] dash={5.0, 10.0,5.0};
		StringBuilder sb = new StringBuilder("");
		for (double aDash : dash) {
			sb.append(aDash);
			sb.append(',');
		}
		sb.deleteCharAt(sb.length()-1);
		String str= sb.toString();
		temp.setDash(dash);
		
		for(double aDouble:dash)
		assertNull("The dash string is null.",temp.getDash());
		assertEquals("The dash string is empty.","",temp.getDash());
		assertEquals("The dash array is not set properly.",str,temp.getDash());
		
	}

	@Test
	public final void testSetDash() {
		double [] dash={5.0, 10.0, 5.0};
		StringBuilder sb = new StringBuilder("");
		for (double aDash : dash) {
			sb.append(aDash);
			sb.append(',');
		}
		sb.deleteCharAt(sb.length()-1);
		String str= sb.toString();
		temp.setDash(dash);
		
		for(double aDouble:dash)
		assertNull("The dash string is null.",temp.getDash());
		assertEquals("The dash string is empty.","",temp.getDash());
		assertEquals("The dash array is not set properly.",str,temp.getDash());
	}

	@Test
	public final void testGetWidth() {
		temp.setWidth(10.2);
		assertEquals("The width value is not set properly.",10.2,temp.getWidth());
	}

	@Test
	public final void testSetWidth() {
		temp.setWidth(10.2);
		assertEquals("The width value is not set corectly.",10.2,temp.getWidth());
	}

	@Test
	public final void testGetOpacity() {
		temp.setOpacity(5);
		assertEquals("The opacity is not set correctly",5, temp.getOpacity());
	}

	@Test
	public final void testSetOpacity() {
		temp.setOpacity(5);
		assertEquals("The opacity is not set correctly",5, temp.getOpacity());
	}

	@Test
	public final void testGetRepresentation() {
		assertNull("Representation is null",gv.);
	}

	@Test
	public final void testCompareTo() {
		AbstractEdge aNullEdge=null;
		AbstractEdge anEdge=Bob.createEdge(new Vertex("jack",gv));
		assertEquals("The edge is not null.",0,temp.compareTo(aNullEdge));
		assertEquals("The compareTo method is not returning the correct", ,temp.compareTo(anEdge));
	}

}
