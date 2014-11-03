/*

This is what the student would have to write. The methods would be empty for them and they would just fill them out to learn the BST data structure.

*/
package edu.uncc.cs.bridges;

public class TreeVisualizer<T> extends GraphVisualizer<T> {
		
	private BSTNode<T> root = null;	 //the root node
	private BSTNode<T> curr = null; //last inserted node
	private String visualizerType;
	
	public TreeVisualizer(){
		setVisualizerType("tree");
	}
	public BSTNode<T> getRoot(){
		return root;
	}
	
	public BSTNode<T> insert(BSTNode<T> newNode) throws Exception{
		if (root == null)
			return root = curr = insertNode(curr, newNode);
		return curr = insertNode(curr, newNode);
	}
	/**
	 * Inserts a new node into the correct position in the tree.
	 * 
	 * @param rt The root node.
	 * @param newNode The node that is to be inserted.
	 * @return The newly generated tree structure.
	 * @throws Exception 
	 */
	private BSTNode<T> insertNode(BSTNode<T> rt, BSTNode<T> newNode) throws Exception{
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
	
	public BSTNode<T> getNode(BSTNode<T>rt, int val){
		if(rt.getLeftChild() == null && rt.getVal() != val){
			return null;
		}
		else if(val == rt.getVal()){
			return rt;
		}else if(val < rt.getVal()){
			if(rt.getLeftChild() == null){
				return null;
			}else return getNode(rt.getLeftChild(), val);			
		}else{
			if(rt.getRightChild() == null){
				return null;
			}else return getNode(rt.getRightChild(), val);			
		}
	}
	/**
	 * Determines whether or not the tree contains the value being searched for.
	 * 
	 * @param rt The root node.
	 * @param val The value being looked for.
	 * @return True if found, false if not.
	 */
	private boolean findNode(BSTNode<T> rt, int val){
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
	private int findMin(BSTNode<T> rt){
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
	private BSTNode<T> removeMin(BSTNode<T> rt) throws Exception{
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
	private int findMax(BSTNode<T> rt){
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
	private BSTNode<T> findMinNode(BSTNode<T> rt){
		if(rt.getLeftChild() == null){
			return rt;
		}else return rt.getLeftChild();				
	}
	
	/**
	 * This method removes a specific node from the tree
	 * @param node
	 * @throws Exception
	 */
	public BSTNode<T> removeN(BSTNode<T> node) throws Exception{
		if(root == null){
			throw new Exception("Nothing to remove, the tree is empty.");
		}
		return removeNode(root, node);
	}
	/**
	 * Removes the specified node from the tree.
	 * 
	 * @param rt The root node.
	 * @param node The node to be removed.
	 * @return The new tree structure after the node is removed.
	 * @throws Exception 
	 */
	//Probably need to find based of identifier
	private BSTNode<T> removeNode(BSTNode<T> rt, BSTNode<T> node) throws Exception{
		//if the node is found that you are trying to remove
			BSTNode<T> aLeaf = this.findLeaf(root); //check if the node = aLeaf
			System.out.println(rt.getIdentifier()+"  "+aLeaf.getIdentifier());
			//swapNodes(node, aLeaf);
			//reHeap(aLeaf);
			//returns updated tree
			return aLeaf;
	}	
	
	public void reHeap(BSTNode<T> aLeaf) throws Exception{
		if(aLeaf.getVal()<aLeaf.getLeftChild().getVal()){
			swapNodes(aLeaf, aLeaf.getLeftChild());
			reHeap(aLeaf);
		}
		else if(aLeaf.getVal() > aLeaf.getRightChild().getVal()){
			swapNodes(aLeaf, aLeaf.getRightChild());
			reHeap(aLeaf);
		}
	}
	
	/**
	 * Swaps one node in the tree, with the minimum value node. 
	 * @param nodeToRemove
	 * @param minLeaf
	 * @throws Exception
	 */
	protected void swapNodes(BSTNode<T> nodeToRemove, BSTNode<T> aLeaf) throws Exception{
		BSTNode<T> temp = aLeaf;
		//temp.getParent().
		aLeaf = nodeToRemove;
		nodeToRemove = temp;
		if (nodeToRemove.getParent().getLeftChild().equals(aLeaf))
			nodeToRemove.setLeftChild(null);
		else
			nodeToRemove.setRightChild(null);
		
		if (aLeaf.getLeftChild() != null){
			nodeToRemove.setLeftChild(aLeaf.getLeftChild());
			aLeaf.setLeftChild(null);
		}
		
		if (aLeaf.getRightChild() != null){
			nodeToRemove.setRightChild(aLeaf.getRightChild());
			aLeaf.setRightChild(null);
		}
	}
	
	/**
	 * This method returns a leaf
	 * @param root is the node we start when looking for a leaf 
	 * @return the leaf node
	 */
	protected BSTNode<T> findLeaf(BSTNode<T> root){
		if(root == null){
			return null;
		} else if (root.getLeftChild() == null && root.getRightChild() == null){
			return root;
		} else if (root.getLeftChild() == null)
			return findLeaf (root.getRightChild());
		else
			return findLeaf(root.getLeftChild());
	}
	
	/**
	 * This method determines whether a node is a leaf
	 * @param aNode
	 * @return true if aNode is a leaf and false otherwise
	 */
	protected boolean isLeaf(BSTNode<T> aNode){
		if (aNode.getLeftChild() == null && aNode.getRightChild() == null)
			return true;
		else 
			return false;
	}
	
	/**
	 * This method finds if there is a leaf with a certain value
	 * @param val is the value of the node 
	 * @return true if there is a leaf with value val and it returns false otherwise
	 */
	protected boolean isLeaf(int val){
		BSTNode<T> aNode = this.getNode(root, val);
		if (aNode.getLeftChild() == null && aNode.getRightChild() == null)
			return true;
		else 
			return false;
	}
	
	//Maybe put this in abstractVertex and tailor it to handle BST? make specific method call?
	protected String getRepresentation() {
		StringBuilder nodes = new StringBuilder();
		StringBuilder links = new StringBuilder();
		
		getRoot().getNodeRepresentation(nodes, links);
		
		return "{"
		+ "\"name\": \"edu.uncc.cs.bridges\","
		+ "\"version\": \"0.4.0\","
		+ "\"visual\": \"" + getVisualizerType() + "\","
		+ "\"nodes\": [" + Bridge.trimComma(nodes) + "],"
		+ "\"links\": [" + Bridge.trimComma(links) + "]"
		+ "}";
	}

	@Override
	protected String setVisualizerType(String type) {
		this.visualizerType = type;
		return type;
	}
	
	protected String getVisualizerType() {
		return this.visualizerType;
	}

}
