/**
 * 
 */
package edu.uncc.cs.bridges;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author mihai
 *
 */

public class StackElementTest {

	/**
	 * @throws java.lang.Exception
	 */
	static StackElement<Follower> root, top;
	static Stack<Follower> stack;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		stack = new Stack<>();
		root = new StackElement<>(new Follower("Steve"), stack);
		top = root;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.StackElement#StackElement(java.lang.Object, edu.uncc.cs.bridges.Stack)}.
	 */
	@Test
	public final void testStackElement() {
		//fail("Not yet implemented"); // TODO
		assertNotNull(stack);
		assertNotNull(root);
		assertEquals(root.getIdentifier(), new Follower("Steve"));
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.StackElement#setStackEdge(edu.uncc.cs.bridges.StackElement)}.
	 */
	@Test
	public final void testSetStackEdge() {
		//fail("Not yet implemented"); // TODO
		StackElement<Follower> anElement = new StackElement<>(new Follower("David"), stack);
		anElement.setStackEdge(root);
		assertNotNull(anElement.getStackEdge(root));
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.StackElement#getStackEdge(edu.uncc.cs.bridges.StackElement)}.
	 */
	@Test
	public final void testGetStackEdge() {
		//fail("Not yet implemented"); // TODO
		StackElement<Follower> anElement = new StackElement<>(new Follower("David"), stack);
		anElement.setStackEdge(top);
		assertNotNull(anElement.getStackEdge(top));
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.StackElement#compareTo(edu.uncc.cs.bridges.StackElement)}.
	 */
	@Test
	public final void testCompareToStackElementOfT() {
		//fail("Not yet implemented"); // TODO
		assertEquals(root, top);
		StackElement<Follower> anElement = new StackElement<>(new Follower("David"), stack);
		assertNotEquals(root, anElement);
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.StackElement#next()}.
	 */
	@Test
	public final void testNext() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.StackElement#remove(edu.uncc.cs.bridges.StackElement)}.
	 */
	@Test
	public final void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#hashCode()}.
	 */
	@Test
	public final void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#AbstractVertex(java.lang.Object)}.
	 */
	@Test
	public final void testAbstractVertex() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getIdentifier()}.
	 */
	@Test
	public final void testGetIdentifier() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getColor()}.
	 */
	@Test
	public final void testGetColor() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#setColor(java.lang.String)}.
	 */
	@Test
	public final void testSetColor() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getShape()}.
	 */
	@Test
	public final void testGetShape() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#setShape(java.lang.String)}.
	 */
	@Test
	public final void testSetShape() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getSize()}.
	 */
	@Test
	public final void testGetSize() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#setSize(double)}.
	 */
	@Test
	public final void testSetSize() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getOpacity()}.
	 */
	@Test
	public final void testGetOpacity() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#setOpacity(double)}.
	 */
	@Test
	public final void testSetOpacity() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#equals(java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObject() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#getRepresentation()}.
	 */
	@Test
	public final void testGetRepresentation() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.AbstractVertex#compareTo(edu.uncc.cs.bridges.AbstractVertex)}.
	 */
	@Test
	public final void testCompareToAbstractVertexOfT() {
		fail("Not yet implemented"); // TODO
	}

}
