/**
 * 
 */
package edu.uncc.cs.bridges;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridges.Actor;
import edu.uncc.cs.bridges.GraphVisualizer;
import edu.uncc.cs.bridges.Vertex;

/**
 * @author mihai
 *
 */
public class ActorTest {

	/**
	 * @throws java.lang.Exception
	 */
	static GraphVisualizer<Actor> graph;
	static Vertex<Actor> anActor;
	static Actor ac;
	static Vertex<Actor> sameActor;
	static Vertex<Actor> anotherActor;
	static Vertex<Actor> anEmptyActor;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac = new Actor("Bob");
		Actor sameAc = new Actor("Bob");
		graph = new GraphVisualizer<>();
		anActor = new Vertex<>(ac, graph);
		sameActor = new Vertex<>(sameAc, graph);
		Actor anotherAc = new Actor("Jane");
		anotherActor = new Vertex<>(anotherAc, graph);
		Actor anEmptyAc = new Actor("");
		anEmptyActor = new Vertex(anEmptyAc, graph);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.Actor#hashCode()}.
	 */
	@Test
	public final void testHashCode() {
		assertNotNull("The vertex created is a null object.", anActor);
		assertNotEquals("The hashcode identifier returns 31 corresponding to a vertex with no identifier.", 31, anActor.hashCode(),0.00001);
		assertEquals("The hashcode identifier does not return 31 corresponding to a vertex with no identifier.", 31, anEmptyActor.hashCode(),0.00001);
		
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.Actor#Actor(java.lang.String)}.
	 */
	@Test
	public final void testActor() {
		assertEquals("The name of the actor is not set correctly.", "Bob", anActor.getIdentifier());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.Actor#getName()}.
	 */
	@Test
	public final void testGetName() {
		assertNotNull("The GetName method returned a null value.", ac.getName());
		assertEquals("The GetName method did not return the expected value.", "Bob", ac.getName());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.Actor#compareTo(edu.uncc.cs.bridges.Actor)}.
	 */
	@Test
	public final void testCompareTo() {
		assertNotEquals("The actor vertices are equal.", 0, anActor.compareTo(anotherActor));
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.Actor#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject() {
		assertFalse("The vertices objects are equal", anActor.equals(anotherActor));
		assertTrue("The 2 vertices are not equal.", anActor.equals(sameActor));
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.Actor#toString()}.
	 */
	@Test
	public final void testToString() {
		assertEquals("The ToString method did not return the expected result.","Bob",anActor.getIdentifier());
		assertEquals("The ToString method did not return the expected result.","Bob",anActor.getIdentifier());
		assertEquals("The ToString method did not return the expected result.","",anEmptyActor.getIdentifier());
	}

}
