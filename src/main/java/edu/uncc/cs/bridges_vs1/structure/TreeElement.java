/**
 * 
 */
package edu.uncc.cs.bridges_vs1.structure;

/**
 * @author mihai Mehedint
 * This class can be used to create tree element objects
 * with left and right pointers
 *
 */

public class TreeElement<E> extends Element<E>{
	private TreeElement<E> left; //the left pointer
	private TreeElement<E> right; //the right pointer
	
	/**
	 * constructors
	 */
	public TreeElement(){
		super();
		this.left = null;
		this.right = null;
	}
	
	public TreeElement (String identifier, E e){
		super(e, identifier);
		this.left = null;
		this.right = null;
	}
	
	public TreeElement(TreeElement<E> left, TreeElement<E> right) {
		super();
		this.left = left;
		this.right = right;
	}
	
	public TreeElement(E e, TreeElement<E> left, TreeElement<E> right) {
		super();
		this.right = right;
		this.left = left;
	}
	
	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
	public void copyTreeElement (TreeElement<E> original){
		this.identifier = new String(original.getIdentifier());
		this.visualizer = new ElementVisualizer(original.getVisualizer());
		this.left = original.left;
		this.right = original.right;
	}
	
	/**
	 * This method returns the left tree element pointer
	 * @return
	 */
	public TreeElement<E> getLeft() {
		return left;
	}
	

	/**
	 * This method sets the left tree element pointer
	 * 
	 */
	public void setLeft(TreeElement<E> left) {
		this.left = left;
	}
	

	/**
	 * This method returns the right tree element pointer
	 * @return
	 */
	public TreeElement<E> getRight() {
		return right;
	}
	

	/**
	 * This method sets the right tree element pointer
	 * 
	 */
	public void setRight(TreeElement<E> right) {
		this.right = right;
	}
}
