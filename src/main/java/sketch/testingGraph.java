package sketch;

public class testingGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	//--------------------------Testing------------------------------------
    	//create a graph to add vertices to
    	GraphVisualizer gv = new GraphVisualizer();
    	
    	//create a vertex, (identifier, graph)	
    	Vertex bob = new Vertex("actor/Bob", gv);
   		Vertex fred = new Vertex("loafer/Fred", gv);
   		
   		//create edge between vertices, (vertex, vertex)
   		//Edge bob_to_fred = new Edge(bob, fred, "bob_to_fred");
   		

   		bob.createEdge(fred, "bobToFred");
   		
   		bob.removeEdge("bobToFred");
    		
   		Vertex steve = new Vertex("actor/Steve", gv);
   		//Edge bob_to_steve = new Edge(bob, steve, "bob_to_steve");
   		//Edge steve_to_fred = new Edge(steve, fred, "steve_to_fred");  
   		
   		//removes the vertex 
   		bob.remove();
 
   		//removes the edge, but not the vertices it may be connected to
   		//bob_to_steve.remove();
   		if(true){
   		//bob_to_fred.remove(); 
   		}
   		  	
   		

	}

}
