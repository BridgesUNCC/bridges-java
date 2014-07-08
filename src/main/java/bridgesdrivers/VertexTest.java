package bridgesdrivers;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bridges.GraphVisualizer;
import bridges.Vertex;

public class VertexTest {
	static GraphVisualizer graph = new GraphVisualizer();
	static Vertex Bob, Steve, John;
	
	@BeforeClass
	public static void BeforeClass(){
		Bob = new Vertex("Bob", graph );
		Steve = new Vertex("Steve", graph);
		John = new Vertex("John", graph);
	}

	@Test
	public final void testCreateEdge() {
		Bob.createEdge(Steve);
		assertNotNull(Bob.getEdge(Steve));
		Steve.createEdge(John);
		assertNotNull(John.getEdge(Steve));
	}
}
