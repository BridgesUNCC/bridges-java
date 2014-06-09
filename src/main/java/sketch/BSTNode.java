package sketch;

public class BSTNode extends AbstractVertex {

	private GraphVisualizer graph;
	private Edge left, right;
	private String name;
	
	public BSTNode(String identifier, GraphVisualizer graph){
		super(identifier);
	}
	
	public void setLeft(Edge e){
		left = e;
	}
	public void setRight(Edge e){
		right = e;
	}
	public void createEdge(String identifier, BSTNode n2){
		Edge tempEdge = new Edge(this, n2, identifier);
	}
}
