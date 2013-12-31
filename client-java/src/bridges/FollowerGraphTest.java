package bridges;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;


public class FollowerGraphTest {
	FollowerGraph graph;
	
	@Before
	public void setUp() {
		graph = new FollowerGraph("stgallag@gmail.com", "clancy42", "twitterapi");
	}

	@Test
	public void login() {
		assertNotNull(graph.root);
	}
	
	@Test
	public void getFollowers() {
		ArrayList<FollowerGraphNode> followers = graph.root.getFollowers();
		assertTrue(followers.size() > 0);
	}
}