package sketch;

public class testingGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	//--------------------------Testing------------------------------------
    	//create a graph to add vertices to
    	GraphVisualizer gv = new GraphVisualizer();
    	
    	Vertex bob= new Vertex("actor/Bob", gv);
    	Vertex steve=new Vertex("actor/Steve", gv);
    	
    	bob.createEdge("bobToSteve", steve);
    	
    	bob.removeEdge("bobToSteve", steve);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	//create a vertex, (identifier, graph)
    	/*
    	Vertex bob = new Vertex("actor/Bob", gv);
   		Vertex fred = new Vertex("loafer/Fred", gv);   		

   		bob.createEdge("bobToFred", fred);
   		
   		bob.removeEdge("bobToFred", fred);
    		
   		Vertex steve = new Vertex("actor/Steve", gv); 
   		
   		//removes the vertex, not from memory just from the graph 
   		bob.remove(); 
   		bob.remove();
	*/
    	
    	
	}

}
