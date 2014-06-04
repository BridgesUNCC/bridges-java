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
		Edge cowduck = new Edge(moo, quack);
		moo.links.put("Quack", cowduck);
		
		// Two vertices and an edge. Works?
		assertEquals(
				"{\"name\": \"bridges\",\"version\": \"0.4.0\",\"visual\": \"graph\",\"nodes\": [{\"name\": \"Moo\"},{\"name\": \"Quack\"}],\"links\": [{\"source\":0,\"target\":1}]}",
				gv.getRepresentation()
				);
		
		// Now add all the properties
		moo.setColor("white");
		moo.setOpacity(0.9);
		moo.setSize(4000.0);
		moo.setShape("square");
		
		quack.setColor("green");
		quack.setOpacity(1.0);
		quack.setSize(12.0);
		quack.setShape("circle");

		cowduck.setColor("pink");
		cowduck.setOpacity(0.0001);
		cowduck.setThickness(5);
		cowduck.setDash(new double[]{10,10,10});
		}

}
