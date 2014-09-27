package edu.uncc.cs.bridges;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridges.StackElement;

public class StackNodeTest {

	static StackElement root, top;
	
	@BeforeClass
	public static void beforeClass() throws Exception{
		root = new StackElement("Steve");
		top = root;
	}

	@Test
	public final void testSetNextNode() throws Exception {
		top.setNextNode(new StackElement("John"));
		top = top.getNextNode();
		top.setNextNode(new StackElement("Bob"));
		top = top.getNextNode();
		assertEquals("John", root.getNextNode().getIdentifier());
	}
	
	@Test
	public final void testGetOutgoing() {
		assertNotNull(root.getOutgoing());
	}

	@Test
	public final void testGetNextNode() {
		assertNotNull(root.getNextNode());
	}

	@Test
	public final void testGetNodeIdentifier() {
		assertEquals("Bob", top.getIdentifier());
	}

}
