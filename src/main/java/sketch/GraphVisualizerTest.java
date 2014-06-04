package sketch;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphVisualizerTest {

	@Test
	public void testGetRepresentation() {
		GraphVisualizer gv = new GraphVisualizer();
		Vertex moo = new Vertex("Moo");
		gv.vertices.put("Moo", moo);
		Vertex quack = new Vertex("Quack");
		gv.vertices.put("Quack", quack);
		Edge cowduck = moo.links.put("Quack", new Edge(moo, quack));
		
		assertEquals(
				gv.getRepresentation(),
				"");
	}

}
