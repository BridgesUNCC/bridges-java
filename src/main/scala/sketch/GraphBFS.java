package sketch;
/**
 * Title: Graph ADT with BFS traversal call for Bridges
 * @author mihai mehedint
 * @version1.0 modified after the driver provided during 2214 spring 2014 by krs
 * @05.30.2014
 */

public class GraphBFS {
	
	private static final String MEGAN = "Megan Fox";
	private static final String GARY = "Gary Oldman";
	private static final String SEAN = "Sean Connery";
	private static final String JENNIFER = "Jennifer Lawrence";
	private static final String EMMA = "Emma Watson";
	private static final String DANIEL = "Daniel Radcliffe";

	public static void main(String[] args) {
		Visualizer myGraph = new GraphVisualizer();
		myGraph.add(DANIEL);
		myGraph.get(DANIEL).setColor("blue");
		myGraph.get(DANIEL).setSize(8.0);
		myGraph.add(EMMA);
		myGraph.get(EMMA).setColor("blue");
		myGraph.get(EMMA).setSize(8.0);
		myGraph.add(JENNIFER);
		myGraph.get(JENNIFER).setColor("blue");
		myGraph.get(JENNIFER).setSize(8.0);
		myGraph.add(SEAN);
		myGraph.get(SEAN).setColor("blue");
		myGraph.get(SEAN).setSize(8.0);
		myGraph.add(GARY);
		myGraph.get(GARY).setColor("blue");
		myGraph.get(GARY).setSize(8.0);
		myGraph.add(MEGAN);
		myGraph.get(MEGAN).setColor("blue");
		myGraph.get(MEGAN).setSize(8.0);
		
		myGraph.setEdge(MEGAN, DANIEL, "Movie1");
		myGraph.setEdge(MEGAN, JENNIFER, "Movie2");
		myGraph.setEdge(SEAN, EMMA, "Movie3");
		myGraph.setEdge(EMMA, GARY, "Movie4");
		myGraph.setEdge(EMMA, DANIEL, "Movie5");
		myGraph.setEdge(JENNIFER, GARY, "Movie6");
		myGraph.setEdge(JENNIFER, SEAN, "Movie7");
		myGraph.setEdge(GARY, SEAN, "Movie8");
		myGraph.setEdge(DANIEL, MEGAN, "Movie9");
		
		myGraph.setColor(MEGAN, DANIEL, "purple");
		myGraph.setColor(MEGAN, JENNIFER, "blue");
		myGraph.setColor(SEAN, EMMA, "red");
		myGraph.setColor(EMMA, GARY, "green");
		myGraph.setColor(EMMA, DANIEL, "yellow");
		myGraph.setColor(JENNIFER, GARY, "brown");
		myGraph.setColor(JENNIFER, SEAN, "purple");
		myGraph.setColor(GARY, SEAN, "blue");
		myGraph.setColor(DANIEL, MEGAN, "red");
		
		myGraph.setDash(MEGAN, DANIEL, 2);
		myGraph.setDash(MEGAN, JENNIFER, 1);
		myGraph.setDash(SEAN, EMMA, 3);
		myGraph.setDash(EMMA, GARY, 1);
		myGraph.setDash(EMMA, DANIEL, 4);
		myGraph.setDash(JENNIFER, GARY, 2);
		myGraph.setDash(JENNIFER, SEAN, 5);
		myGraph.setDash(GARY, SEAN, 4);
		myGraph.setDash(DANIEL, MEGAN, 5);
		
		
		myGraph.setThickness(MEGAN, DANIEL, 2);
		myGraph.setThickness(MEGAN, JENNIFER, 1);
		myGraph.setThickness(SEAN, EMMA, 3);
		myGraph.setThickness(EMMA, GARY, 1);
		myGraph.setThickness(EMMA, DANIEL, 4);
		myGraph.setThickness(JENNIFER, GARY, 2);
		myGraph.setThickness(JENNIFER, SEAN, 5);
		myGraph.setThickness(GARY, SEAN, 4);
		myGraph.setThickness(DANIEL, MEGAN, 5);
		
		myGraph.setOpacity(MEGAN, DANIEL, 2);
		myGraph.setOpacity(MEGAN, JENNIFER, 1);
		myGraph.setOpacity(SEAN, EMMA, 3);
		myGraph.setOpacity(EMMA, GARY, 1);
		myGraph.setOpacity(EMMA, DANIEL, 4);
		myGraph.setOpacity(JENNIFER, GARY, 2);
		myGraph.setOpacity(JENNIFER, SEAN, 5);
		myGraph.setOpacity(GARY, SEAN, 4);
		myGraph.setOpacity(DANIEL, MEGAN, 5);
		
		
		System.out.println("Starting BFS on " + JENNIFER);
		myGraph.BFS(JENNIFER);
		System.out.println("Ended BFS on " + JENNIFER);
		
		System.out.println();
		
		System.out.println("Starting BFS on " + MEGAN);
		myGraph.BFS(MEGAN);
		System.out.println("Ended BFS on " + MEGAN);
	}
}