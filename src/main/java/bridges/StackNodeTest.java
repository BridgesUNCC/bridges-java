package bridges;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class StackNodeTest {

	static StackNode root, top;
	
	@BeforeClass
	public static void beforeClass() throws Exception{
		root = new StackNode("Steve");
		top = root;
	}

	@Test
	public final void testSetNextNode() throws Exception {
		top.setNextNode(new StackNode("John"));
		top = top.getNextNode();
		top.setNextNode(new StackNode("Bob"));
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
