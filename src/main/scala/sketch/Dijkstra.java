package sketch;
/**
 * @author mihai mehedint
 * @date 05/02/2014
 * @Description: You will implement the Dijkstra's shortest path
 *              algorithm using the graph ADT
 * The driver is modified after the one provided during class 2214 spring 2014
 */
public class Dijkstra {
	
    private static final String MEGAN = "Megan Fox";
    private static final String GARY = "Gary Oldman";
    private static final String SEAN = "Sean Connery";
    private static final String JENNIFER = "Jennifer Lawrence";
    private static final String EMMA = "Emma Watson";
    private static final String DANIEL = "Daniel Radcliffe";
    
    public static void main(String args[]){
	Graph graph = new Graphl();

	graph.add(MEGAN);
	graph.add(GARY);
	graph.add(SEAN);
	graph.add(JENNIFER);
	graph.add(EMMA);
	graph.add(DANIEL);

	graph.setEdge(MEGAN, GARY, "The Unborn");
	graph.setEdge("A", "C", 3);
	graph.setEdge("A", "D", 20);
	graph.setEdge("B", "D", 5);
	graph.setEdge("C", "B", 2);
	graph.setEdge("C", "E", 15);
	graph.setEdge("D", "E", 11);

	graph.Dijkstra("A");
	graph.Dijkstra("C");

	graph = new Graphl();

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
	
}

}