package sketch;

public class BSTNode extends AbstractVertex {
	
	private BSTEdge leftEdge, rightEdge;
	private String identifier;
	private int val;//temp till I research comparable
	
	/**
	 * Constructor for a new node.
	 * 
	 * @param identifier Name of the node.
	 * @param val The value so it can be sorted(temporary).
	 */
	public BSTNode(String identifier, int val){
		super(identifier);
		this.identifier = identifier;
		this.val = val;
	}	
	
	public int getVal(){
		return val;
	}
	public void setVal(int val){
		this.val = val;
	}
	/**
	 * Creates the left Edge connecting the calling node to the passed node.
	 * 
	 * @param n The left child of the calling node.
	 */
	public void setLeftChild(BSTNode n) throws Exception{
		if(this == null || n == null){
			throw new Exception("Left Child not created, one or more of the nodes was NULL.");
		}
		else this.setLeftEdge(n);
	}
	/**
	 * 
	 * @return The left child of the calling node.
	 */
	public BSTNode getLeftChild(){
		if(this.getLeftEdge() == null){
			return null;
		}else return this.getLeftEdge().getOutgoing();
	}
	//This shouldn't have to be used by the student
	/**
	 * Creates and sets the left edge for the calling node.
	 * 
	 * @param n2 The second node that makes up the edge.
	 */
	private void setLeftEdge(BSTNode n2){
		//removing the edgeIdentifier would make removal easier
		
		this.leftEdge = new BSTEdge(this, n2);//creates the edge, and has the left pointer reference it
	}
	/**
	 * Returns the left connected edge for property manipulation.
	 * 
	 * @return The left edge from the calling node.
	 */
	public BSTEdge getLeftEdge(){
		return leftEdge;//returns the left connected edge
	}
	/**
	 * Creates the right Edge connecting the calling node to the passed node.
	 * 
	 * @param n The right child of the calling node.
	 * @throws Exception 
	 */
	public void setRightChild(BSTNode n) throws Exception{
		if(this == null || n == null){
			throw new Exception("Right Child not created, one or more of the nodes was NULL.");
		}
		else this.setRightEdge(n);		
	}
	/**
	 * 
	 * @return The right child of the calling node.
	 */
	public BSTNode getRightChild(){
		if(this.getRightEdge() == null){
			return null;
		}else return this.getRightEdge().getOutgoing();
	}
	/**
	 * Creates and sets the right edge for the calling node.
	 * 
	 * @param n2 The second node that makes up the edge.
	 */
	private void setRightEdge(BSTNode n2){

		this.rightEdge = new BSTEdge(this, n2);		
	}
	/**
	 * Returns the right connected edge for property manipulation.
	 * 
	 * @return The right edge from the calling node.
	 */
	public BSTEdge getRightEdge(){
		return rightEdge;
	}
}
