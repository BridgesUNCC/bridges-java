package edu.uncc.cs.bridgesdrivers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import edu.uncc.cs.bridges.*;

public class EdgeTest {
	
	static GraphVisualizer gv;
	static Vertex Bob;
	static Vertex Jane;
	static AbstractEdge temp;
	
	@Before
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
		assertEquals("The value of edge weight does not match the expected value.", 15, temp.getWeight(),0.0001);
	}

	@Test
	public final void testEdgeAbstractVertexAbstractVertexStringDouble() {
		assertEquals("The destination vertex is different than expected",temp, Bob.getEdge(Jane));
		assertNull("The edge is not null", Jane.getEdge(Bob));
		assertEquals("The value of edge weight does not match the expected value.", 12.3, temp.getWeight(),0.0001);
	}

	@Test
	public final void testEdgeAbstractVertexAbstractVertexStringString() {
		Bob.createEdge(Jane,"randWeight");
		assertEquals("The destination vertex is different than expected",temp, Bob.getEdge(Jane));
		assertNull("The edge is not null", Jane.getEdge(Bob));
		assertEquals("The value of edge weight does not match the expected value.", 5, temp.getWeight(),5);
	}

	@Test
	public final void testAbstractEdgeAbstractVertexAbstractVertexString() {
		assertEquals("The destination vertex is different than expected",temp, Bob.getEdge(Jane));
		assertNull("The edge is not null", Jane.getEdge(Bob));
		assertEquals("The value of edge weight does not match the expected value.", 12.3, temp.getWeight(),0.0001);
	}

	@Test
	public final void testAbstractEdgeString() {
		AbstractVertex Jack = new Vertex("Jack", gv);
		AbstractEdge tempRandom = new Edge(Bob, Jack,"randWeight");
		AbstractEdge  tempNull = new Edge(Bob, Jack,"rand");
		assertNotNull("The weight parameter is Null",tempRandom.getWeight());
		assertEquals("The weight parameter is null",1, tempNull.getWeight(),0.0001);
	}

	@Test
	public final void testGetIdentifier() {
		assertEquals("Incorrect identifier.", "BobToJane",temp.getIdentifier());
	}

	@Test
	public final void testGetWeight() {
		AbstractEdge anEdge = Bob.createEdge(new Vertex("Bill", gv));
		assertEquals("The edge weight is not set. Weight is 1 by default.", 1,anEdge.getWeight(), 0.0001);
		assertEquals("The edge weight parameter has an unexpected value by GetWeight method.", 12.3, temp.getWeight(), 0.0001);
		Bob.getEdge(Jane).setWeight(20);
		assertEquals("The edge weight parameter is incorrect.",20,temp.getWeight(),0.0001);
	}

	@Test
	public final void testSetWeightDouble() {
		AbstractEdge anEdge = Bob.createEdge(new Vertex("Bill", gv));
		assertEquals("The edge weight is not set. Weight is 1 by default.", 1,anEdge.getWeight(), 0.0001);
		assertEquals("The edge weight parameter is incorrect.",12.3,temp.getWeight(),0.0001);
		Bob.getEdge(Jane).setWeight(20);
		assertEquals("The edge weight parameter is incorrect.",20,temp.getWeight(),0.0001);
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
		assertNotNull("The dash string is null.",temp.getDash());
		assertNotEquals("The dash string is empty.","",temp.getDash());
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
		assertNotNull("The dash string is null.",temp.getDash());
		assertNotEquals("The dash string is empty.","",temp.getDash());
		assertEquals("The dash array is not set properly.",str,temp.getDash());
	}

	@Test
	public final void testGetWidth() {
		temp.setWidth(10.2);
		assertEquals("The width value is not set properly.",10.2,temp.getWidth(),0.0001);
	}

	@Test
	public final void testSetWidth() {
		temp.setWidth(10.2);
		assertEquals("The width value is not set corectly.",10.2,temp.getWidth(),0.0001);
	}

	@SuppressWarnings("deprecation")
	@Test
	public final void testGetOpacity() {
		temp.setOpacity(0.4);
		assertNotNull("The opacity was not set.",temp.getOpacity());
		assertEquals("The opacity is not set correctly",0.4, temp.getOpacity(), 0.0001);
	}

	@Test
	public final void testSetOpacity() {
		temp.setOpacity(0.1);
		assertNotNull("The opacity was not set.",temp.getOpacity());
		assertEquals("The opacity is not set correctly", 0.1, temp.getOpacity(), 0.0001);
	}

	@Test
	public final void testGetRepresentation() {
		assertNotNull("Representation is null",gv);
	}

	@Test
	public final void testCompareTo() {
		AbstractEdge aNullEdge=null;
		AbstractEdge anEdge=Bob.createEdge(new Vertex("jack",gv));
		assertEquals("The expected null edge is not null.",0,temp.compareTo(aNullEdge));
		assertNotEquals("The compareTo method is not returning the correct value", 0, anEdge.compareTo(temp));
	}

}
