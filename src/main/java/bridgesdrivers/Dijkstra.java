package bridgesdrivers;

import bridges.Bridge;
import bridges.GraphVisualizer;
import bridges.Vertex;

public class Dijkstra {

	public static void main(String[] args) {
		GraphVisualizer graph = new GraphVisualizer();
		Bridge.init(0,"134695467477", graph);
		
		Vertex A, B, C, D , E;	
		A = new Vertex("A", graph);
		B = new Vertex("B", graph);
		C = new Vertex("C", graph);
		D = new Vertex("D", graph);
		E = new Vertex("E", graph);

		A.createEdge(B, 10);
		A.createEdge(C, 3);
		A.createEdge(D, 20);
		B.createEdge(D, 5);
		C.createEdge(B, 2);
		C.createEdge(E, 15);
		D.createEdge(E, 11);
		
		A.getEdge(B).setWeight(25);
		System.out.println(Bridge.getEdgeWeight("B","D"));
		
		//graph.Dijkstra("A");
		//graph.Dijkstra("C");
		Bridge.complete();
		
		/**

		graph.add("A");
		graph.add("B");
		graph.add("C");
		graph.add("D");
		graph.add("E");
		graph.add("F");
		graph.add("G");
		graph.add("H");
		graph.add("I");

		graph.setEdge("A", "B", 15);
		graph.setEdge("B", "A", 15);
		graph.setEdge("C", "A", 25);
		graph.setEdge("A", "C", 25);
		graph.setEdge("B", "E", 10);
		graph.setEdge("B", "H", 5);
		graph.setEdge("B", "I", 25);
		graph.setEdge("E", "B", 10);
		graph.setEdge("H", "B", 5);
		graph.setEdge("I", "B", 25);
		graph.setEdge("C", "D", 10);
		graph.setEdge("C", "E", 20);
		graph.setEdge("D", "C", 10);
		graph.setEdge("E", "C", 20);
		graph.setEdge("E", "F", 10);
		graph.setEdge("E", "G", 5);
		graph.setEdge("F", "E", 10);	
		graph.setEdge("G", "E", 5);
		graph.setEdge("H", "I", 15);
		graph.setEdge("I", "H", 15);

		graph.Dijkstra("E");
		graph.Dijkstra("B");
		*/

	}

}
