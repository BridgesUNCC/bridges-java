package bridges;

public class testBridges {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GraphVisualizer gv = new GraphVisualizer();
		Bridge.init(0,"1022683069234", gv);
		Vertex HelloWorld = new Vertex("HelloWorld",gv);
		Vertex Hello = new Vertex("Hello",gv);
		System.out.println(Hello.toString());
		//create an edge
		Hello.createEdge(HelloWorld);
		//another way to add an edge
		Edge anEdge = new Edge(HelloWorld, Hello, "HELL0");
		Bridge.complete();

	}
}
