/**
 * 
 */
package bridges.base;

/**
 * @author mihai Mehedint
 * This class can be used to create tree element objects
 * with left and right pointers
 *
 */

public class TreeElement<E> extends Element<E>{
	private TreeElement<E> left; //the left pointer
	private TreeElement<E> right; //the right pointer
	
	/** Constructs an empty TreeElement with right and left pointers set to null. */
	public TreeElement(){
		super();
		this.left = null;
		this.right = null;
	}
	
	/** Constructs a TreeElement holding an object "e" with right and left pointers set to null. 
	 * @param e the generic object that TreeElement will hold 
	 */
	public TreeElement (E e){
		super(e);
		this.left = null;
		this.right = null;
	}
	
	/** Constructs a TreeElement with label set to "label", holding an object "e". 
	 * @param label the label of TreeElement that shows up on the Bridges visualization
	 * @param e the generic object that TreeElement will hold 
	 * */
	public TreeElement (String label, E e){
		super(label, e);
		this.left = null;
		this.right = null;
	}
	
	/** Constructs an empty TreeElement left pointer pointing to "left" and right pointer pointing to "right". 
	 * @param left the TreeElement to be assigned to the left pointer of this TreeElement
	 * @param right the TreeElement to be assigned to the right pointer of this TreeElement
	 * */
	public TreeElement(TreeElement<E> left, TreeElement<E> right) {
		super();
		this.left = left;
		this.right = right;
	}
	
	/** Constructs a TreeElement holding the object "e", left pointer pointing to "left" and right pointer pointing to "right".
	 * @param e the generic object that TreeElement will hold 
	 * @param left the TreeElement to be assigned to the left pointer of this TreeElement
	 * @param right the TreeElement to be assigned to the right pointer of this TreeElement
	 * */
	public TreeElement(E e, TreeElement<E> left, TreeElement<E> right) {
		super();
		this.right = right;
		this.left = left;
	}
/*
	/**
	 * performing deep copy of an element when needed
	 * @param identifier
	 */
/*
	public void copyTreeElement (TreeElement<E> original){
		this.setIdentifier(new String(original.getIdentifier()));
		this.setVisualizer( new ElementVisualizer(original.getVisualizer()));
		this.left = original.left;
		this.right = original.right;
	}
*/
	
	/**
	 * This method returns the left tree element pointer
	 * @return the left child of this TreeElement
	 */
	public TreeElement<E> getLeft() {
		return left;
	}
	

	/**
	 * This method sets the left tree element pointer
	 * @param left the TreeElement that should be assigned to the left child
	 */
	public void setLeft(TreeElement<E> left) {
		this.left = left;
	}
	

	/**
	 * This method returns the right tree element pointer
	 * @return the right child of this TreeElement
	 */
	public TreeElement<E> getRight() {
		return right;
	}
	

	/**
	 * This method sets the right tree element pointer
	 * @param right the TreeElement that should be assigned to the right child
	 */
	public void setRight(TreeElement<E> right) {
		this.right = right;
	}

	/**
	 * Comparing 2 tree elements
	 * @param e1
	 * @return
	 */
	public int compareTo(TreeElement<E> e1) {
		return super.compareTo(e1);
	}
	
}
