package edu.uncc.cs.bridgesdrivers;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridges.BSTNode;

public class BSTNodeTest {
	
	static BSTNode root = new BSTNode("Bob", 6);

	@BeforeClass
	public static void beforeClass() throws Exception{
		root.setLeftChild(new BSTNode("Steve", 2));
		root.setRightChild(new BSTNode("John", 10));
	}
	
	@Test
	public final void testGetVal() {
		assertEquals(6, root.getVal());

	}

	@Test
	public final void testSetLeftChild() throws Exception {
		
		assertNotNull(root.getLeftChild());
		assertEquals(2,  root.getLeftChild().getVal());

	}

	@Test
	public final void testGetLeftChild() {
		assertEquals("Steve", root.getLeftChild().getIdentifier());
		
	}

	@Test
	public final void testGetLeftEdge() {
		assertNotNull(root.getLeftEdge());

	}

	@Test
	public final void testSetRightChild() {
		assertNotNull(root.getRightChild());
		assertEquals(10,  root.getRightChild().getVal());

	}

	@Test
	public final void testGetRightChild() {
		assertEquals("John", root.getRightChild().getIdentifier());

	}

	@Test
	public final void testGetRightEdge() {
		assertNotNull(root.getRightEdge());

	}

}
