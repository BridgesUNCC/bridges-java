package bridges;

public class testingGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	//--------------------------Testing------------------------------------
    	//create a graph to add vertices to
    	GraphVisualizer gv = new GraphVisualizer();
    	
    	Vertex bob= new Vertex("actor/Bob", gv);
    	Vertex steve=new Vertex("actor/Steve", gv);
   
    	bob.createEdge(steve);    	
	}

}
