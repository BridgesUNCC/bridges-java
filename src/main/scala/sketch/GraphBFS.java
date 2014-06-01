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
		//adding vertices
		myGraph.add(DANIEL);
		myGraph.add(EMMA);
		myGraph.add(JENNIFER);
		myGraph.add(SEAN);
		myGraph.add(GARY);
		myGraph.add(MEGAN);
		
		//setting parameters for vertices
		Vertex aVertex=myGraph.get(DANIEL);
			aVertex.setColor("blue");
			aVertex.setSize(8.0);
		Vertex aVertex=myGraph.get(EMMA);
			aVertex.setColor("blue");
			aVertex.setSize(8.0);
		Vertex aVertex=myGraph.get(JENNIFER);
			aVertex.setColor("blue");
			aVertex.setSize(8.0);
		Vertex aVertex=myGraph.get(SEAN);
			aVertex.setColor("blue");
			aVertex.setSize(8.0);
		Vertex aVertex=myGraph.get(GARY);
			aVertex.setColor("blue");
			aVertex.setSize(8.0);		
		Vertex aVertex=myGraph.get(MEGAN);
			aVertex.setColor("blue");
			aVertex.setSize(8.0);
		
		//add Edges
		/**
		 * for now; 
		 * in the future: myGraph.get(MEGAN)  // A Vertex
    							.get(DANIEL)    //An Edge 
    							.setLabel("Movie1");
		 */
		myGraph.setEdge(MEGAN, DANIEL); 
		myGraph.setEdge(MEGAN, JENNIFER);
		myGraph.setEdge(SEAN, EMMA);
		myGraph.setEdge(EMMA, GARY);
		myGraph.setEdge(EMMA, DANIEL);
		myGraph.setEdge(JENNIFER, GARY);
		myGraph.setEdge(JENNIFER, SEAN);
		myGraph.setEdge(GARY, SEAN);
		myGraph.setEdge(DANIEL, MEGAN);
		
		//set parameters for Edges current version
		//future version: setEdgeColor(MEGAN, DANIEL, "purple");
		//future version: setEdgeOpacity(MEGAN, DANIEL, 1.0);
		Edge anEdge = myGraph.get(MEGAN).get(DANIEL);
			anEdge.setColor("purple");
			anEdge.setDash(new double[]{3.0,3.0});
			anEdge.setOpacity(1.0);
			anEdge.setThickness(1.0);
		Edge anEdge = myGraph.get(MEGAN).get(JENNIFER);
			anEdge.setColor("purple");
			anEdge.setDash(new double[]{3.0,3.0});
			anEdge.setOpacity(1.0);
			anEdge.setThickness(1.0);
		Edge anEdge = myGraph.get(SEAN).get(EMMA);
			anEdge.setColor("purple");
			anEdge.setDash(new double[]{3.0,3.0});
			anEdge.setOpacity(1.0);
			anEdge.setThickness(1.0);
		Edge anEdge = myGraph.get(EMMA).get(GARY);
			anEdge.setColor("purple");
			anEdge.setDash(new double[]{3.0,3.0});
			anEdge.setOpacity(1.0);
			anEdge.setThickness(1.0);
		Edge anEdge = myGraph.get(EMMA).get(DANIEL);
			anEdge.setColor("purple");
			anEdge.setDash(new double[]{3.0,3.0});
			anEdge.setOpacity(1.0);
			anEdge.setThickness(1.0);
		Edge anEdge = myGraph.get(JENNIFER).get(GARY);
			anEdge.setColor("purple");
			anEdge.setDash(new double[]{3.0,3.0});
			anEdge.setOpacity(1.0);
			anEdge.setThickness(1.0);	
		Edge anEdge = myGraph.get(JENNIFER).get(SEAN);
			anEdge.setColor("purple");
			anEdge.setDash(new double[]{3.0,3.0});
			anEdge.setOpacity(1.0);
			anEdge.setThickness(1.0);
		Edge anEdge = myGraph.get(GARY).get(SEAN);
			anEdge.setColor("purple");
			anEdge.setDash(new double[]{3.0,3.0});
			anEdge.setOpacity(1.0);
			anEdge.setThickness(1.0);
		Edge anEdge = myGraph.get(DANIEL).get(MEGAN);
			anEdge.setColor("purple");
			anEdge.setDash(new double[]{3.0,3.0});
			anEdge.setOpacity(1.0);
			anEdge.setThickness(1.0);
		
		
		System.out.println("Starting BFS on " + JENNIFER);
		myGraph.BFS(JENNIFER);
		System.out.println("Ended BFS on " + JENNIFER);
		
		//edit the attributes for edges according to the BFS path goes here (color the edges again etc.)
		
		System.out.println();
		
		System.out.println("Starting BFS on " + MEGAN);
		myGraph.BFS(MEGAN);
		System.out.println("Ended BFS on " + MEGAN);
		
		//edit the attributes for edges according to the BFS path goes here (color the edges again etc.)
		
	}
}