/*

This is what the student would have to write. The methods would be empty for them and they would just fill them out to learn the BST data structure.

*/
package bridges;

//import java.util.HashMap;
//import java.util.Map;

public class TreeVisualizer extends AbstractVertex {
	
	private BSTNode root;
	public TreeVisualizer(String identifier){
		super(identifier);
	}
	public void insert(BSTNode newNode) throws Exception{
		root = insertNode(root, newNode);
	}
	/**
	 * Inserts a new node into the correct position in the tree.
	 * 
	 * @param rt The root node.
	 * @param newNode The node that is to be inserted.
	 * @return The newly generated tree structure.
	 * @throws Exception 
	 */
	private BSTNode insertNode(BSTNode rt, BSTNode newNode) throws Exception{
		if(rt == null){			
			return newNode;			
		}else if(newNode.getVal() < rt.getVal() ){
			if(rt.getLeftChild() == null){
				rt.setLeftChild(newNode); 
				return rt;
			}else{				
				return insertNode(rt.getLeftChild(), newNode);
			}				
		}else{
			if(rt.getRightEdge() == null){
				rt.setLeftChild(newNode);
				return rt;
			}else{				
				return insertNode(rt.getRightChild(), newNode);
			}				
		}
	}
	//Could easily change this to return the name of the object.
	public boolean find(int val){
		return findNode(root, val);
	}
	/**
	 * Determines whether or not the tree contains the value being searched for.
	 * 
	 * @param rt The root node.
	 * @param val The value being looked for.
	 * @return True if found, false if not.
	 */
	private boolean findNode(BSTNode rt, int val){
		if(rt == null){
			return false;
		}
		else if(val == rt.getVal()){
			return true;
		}else if(val < rt.getVal()){
			if(rt.getLeftChild() == null){
				return false;
			}else return findNode(rt.getLeftChild(), val);			
		}else{
			if(rt.getRightChild() == null){
				return false;
			}else return findNode(rt.getRightChild(), val);			
		}		
	}
	public int fMin(){
		return findMin(root);
	}
	/**
	 * Finds and returns the minimum value contained in the tree.
	 * 
	 * @param rt The root node of the tree.
	 * @return The minimum value contained in the tree.
	 */
	private int findMin(BSTNode rt){
		if(rt.getLeftChild() == null){
			return rt.getVal();
		}else return findMin(rt.getLeftChild());				
	}
	public void rMin() throws Exception{			
		if(root == null){
			throw new Exception("There is no minimum to remove because the tree is empty.");
		}
		root = removeMin(root);
	}
	/**
	 * Finds and removes the node that contains the minimum value contained in the tree.
	 * 
	 * @param rt The root node of the tree.
	 * @return A new tree minus the minimum value.
	 * @throws Exception 
	 */
	private BSTNode removeMin(BSTNode rt) throws Exception{
		if(rt.getLeftChild() == null){
			return null;
		}else{
			rt.setLeftChild(removeMin(rt.getLeftChild()));//check logic
			return rt;
		}
	}
	public int fMax() throws Exception{
		if(root == null){
			throw new Exception("There is no maximum because the tree is empty.");
		}
		return findMax(root);
	}
	/**
	 * Finds and returns the maximum value contained in the tree.
	 * 
	 * @param rt The root node of the tree.
	 * @return The maximum value contained in the tree.
	 */
	private int findMax(BSTNode rt){
		if(rt.getRightChild() == null){
			return rt.getVal();
		}else return findMax(rt.getRightChild());		
	}
	//used for node removal ONLY
	private BSTNode findMinNode(BSTNode rt){
		if(rt.getLeftChild() == null){
			return rt;
		}else return rt.getLeftChild();				
	}
	public void removeN(BSTNode node) throws Exception{
		if(root == null){
			throw new Exception("Nothing to remove, the tree is empty.");
		}
		root = removeNode(root, node);
	}
	/**
	 * Removes the specified node from the tree.
	 * 
	 * @param rt The root node.
	 * @param node The node to be removed.
	 * @return The new tree structure after the node is removed.
	 */
	private BSTNode removeNode(BSTNode rt, BSTNode node){
		//if the node is found that you are trying to remove
		if( rt.getVal() == node.getVal()){
			//if there are no children
			if(rt.getLeftChild() == null && rt.getRightChild() == null){
				return null;
			}
			//if there is only one child
			else if(rt.getLeftChild() == null || rt.getRightChild() == null){
				if(rt.getRightChild() == null){
					return rt.getLeftChild();
				}else return rt.getRightChild();				
			}
			//if the node has 2 children
			//finds min node of the right tree
			BSTNode temp = findMinNode(rt.getRightChild());
			//removes the min of the right tree returning an updated sub-tree
			rt = removeNode(rt, temp);
			//updates the node to be removed to reflect the removal/balancing
			rt.setVal(temp.getVal());
			//returns updated tree
			return rt;
		}//if the value is larger
		else if(node.getVal() > rt.getVal()){
			return removeNode(rt.getRightChild(), node);
		//if the value is smaller
		}else return removeNode(rt.getLeftChild(), node);				
	}	
	//Maybe put this in abstractVertex and tailor it to handle BST? make specific method call?
	@Override
	String getRepresentation() {
		String nodes = "";
		String links = "";
		Map<AbstractVertex, Integer> vertex_to_index = new HashMap<>();
		
		int i=0;
		for (AbstractVertex v : Bridge.sorted_values(vertices)) {
			// Manage vertex properties
			// Encapsulate in {}, and remove the trailing comma.
			nodes += v.getRepresentation() + ",";
			vertex_to_index.put(v, i);
			i++;
		}
		
		// You have to finish all the vertices before you can start any of the edges
		//  because otherwise you might meet a vertex without an index
		for (AbstractVertex v : Bridge.sorted_values(vertices)) {
			// Manage link properties
			for (Edge e : Bridge.sorted_values(v.outgoing)) {
				// Encapsulate in {}, and remove the trailing comma.
				links += e.getRepresentation(vertex_to_index) + ",";
			}
		}
		return "{"
				+ "\"name\": \"bridges\","
				+ "\"version\": \"0.4.0\","
				+ "\"visual\": \"graph\","
				+ "\"nodes\": [" + Bridge.trimComma(nodes) + "],"
				+ "\"links\": [" + Bridge.trimComma(links) + "]"
				+ "}";
	} 

}
