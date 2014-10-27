/*

This is what the student would have to write. The methods would be empty for them and they would just fill them out to learn the BST data structure.

*/
package edu.uncc.cs.bridges;

import java.util.HashMap;
import java.util.Map;

//import java.util.HashMap;
//import java.util.Map;

public class TreeVisualizer<T> extends GraphVisualizer<T> {
		
	private BSTNode root;
	private String visualizerType;
	
	public TreeVisualizer(){
		setVisualizerType("tree");
	}
	public BSTNode getRoot(){
		return root;
	}
	
	public BSTNode<T> insert(BSTNode<T> newNode) throws Exception{
		return root = insertNode(root, newNode);
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
			if(rt.getLeftEdge() == null){
				rt.setLeftChild(newNode); 				
			}else{
				rt.setLeftChild(insertNode(rt.getLeftChild(), newNode));				
			}				
		}else{
			if(rt.getRightEdge() == null){
				rt.setRightChild(newNode);				
			}else{				
				rt.setRightChild(insertNode(rt.getRightChild(), newNode));				
			}				
		}
		return rt;
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
	
	/**
	 * This method returns the minimum value
	 * @return minimum value in the tree
	 */
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
			rt.setColor("red");
			return rt.getVal();
		}else {
			if(rt != root)
				rt.setColor("red");//tracing to min node
			if(rt.getLeftEdge() != null){
				rt.getLeftEdge().setColor("red");
			}
			return findMin(rt.getLeftChild());				
		}
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
			if(rt.getRightChild() == null){
				return null;
			}
				return rt.getRightChild();
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
			rt.setColor("Orange");			
			return rt.getVal();
		}else{
			if(rt != root)//tracing route to max node
				rt.setColor("Orange");
			rt.getRightEdge().setColor("Orange");
			return findMax(rt.getRightChild());		
		}
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
	//Probably need to find based of identifier
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
	protected String getRepresentation() {
		StringBuilder nodes = new StringBuilder();
		StringBuilder links = new StringBuilder();
		
		getRoot().getNodeRepresentation(nodes, links);
		
		return "{"
		+ "\"name\": \"edu.uncc.cs.bridges\","
		+ "\"version\": \"0.4.0\","
		+ "\"visual\": \"tree\","
		+ "\"nodes\": [" + Bridge.trimComma(nodes) + "],"
		+ "\"links\": [" + Bridge.trimComma(links) + "]"
		+ "}";
	}

	@Override
	protected String setVisualizerType(String type) {
		this.visualizerType = type;
		return null;
	}

}
