package bridges.base;

/**
 *
 * 	@brief This class is extended from the TreeElement class  and can be used to create 
 *	binary tree element objects.
 *
 * 	The BinTree element class is the building block for creating binary tree structures.
 * 	It contains two children (viz., left, right). 
 *
 *  BinTreeElement contains a visualizer (ElementVisualizer) object for setting visual
 *  attributes (color, shape, opacity, size), necessary for displaying them in a
 *  web browser.
 *
 *  Elements also have a LinkVisualizer object, that is used when they are linked to
 *  another element, appropriate for setting link attributes, for instance, between
 *  the current element and its left or  right child
 *
 * @param E he generic parameter object that is part of this element, representing 
 *		application specific data.
 *
 * @author Kalpathi Subramanian, Mihai Mehedint
 *
 * @date 6/22/16, 1/7/17, 5/17/17
 *
 * \sa Example Tutorial at <br> 
 *			http://bridgesuncc.github.io/Hello_World_Tutorials/BTree.html
 */

public class BinTreeElement<E> extends TreeElement<E>{
	private BinTreeElement<E> left; //the left pointer
	private BinTreeElement<E> right; //the right pointer
	
	/** 
	 *
	 * 	Constructs an empty Binary Tree Element with right and left 
	 *	pointers set to null. 
	 *
	 **/
	public BinTreeElement(){
		super();
		super.addChild(null);
		super.addChild(null);
	}
	
	/** 
	 *	Constructs a TreeElement holding an object "e" with right and left 
	 *	pointers set to null. 
	 *
	 *	@param e the generic object that TreeElement will hold 
	 *
	 **/
	public BinTreeElement (E e){
		super(e);
		super.addChild(null);
		super.addChild(null);
	}
	
	/** Constructs a TreeElement with label set to "label", holding an 
	 *	object "e". 
	 *
	 * @param label the label of TreeElement that shows up on the 
	 *	Bridges visualization
	 * @param e the generic object that TreeElement will hold 
	 **/
	public BinTreeElement (String label, E e){
		super(label, e);
		super.addChild(null);
		super.addChild(null);
	}
	
	/** Constructs an empty TreeElement left pointer pointing to 
	 *	"left" and right pointer pointing to "right". 
	 * 	@param left the TreeElement to be assigned to the left pointer of 
	 *		this TreeElement
	 * 	@param right the TreeElement to be assigned to the right pointer of 
	 *		this TreeElement
	 * 
	 **/
	public BinTreeElement(BinTreeElement<E> left, 
						BinTreeElement<E> right) {
		super();
		super.addChild(left);
		super.addChild(right);
	}
	
	/** 
	 *	Constructs a TreeElement holding the object "e", left pointer 
	 *	pointing to "left" and right pointer pointing to "right".
	 *
	 * 	@param e the generic object that TreeElement will hold 
	 * 	@param left the TreeElement to be assigned to the left pointer of this 
	 *		TreeElement
	 * 	@param right the TreeElement to be assigned to the right pointer of 
	 *		this TreeElement
	 *
	 **/
	public BinTreeElement(E e, BinTreeElement<E> left, 
							BinTreeElement<E> right) {
		super(e);
		super.addChild(left);
		super.addChild(left);
	}

	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 **/
	public String getDataStructType() {
		return "BinaryTree";
	}
	
	/**
	 *
	 * This method returns the left tree element pointer
	 * @return the left child of this TreeElement
	 *
	 */
	public BinTreeElement<E> getLeft() {
		return (BinTreeElement<E>) super.getChild(0);
	}
	
	/**
	 *
	 * This method sets the left tree element pointer
	 * @param left the TreeElement that should be assigned to the left child
	 *
	 */
	public void setLeft(BinTreeElement<E> left) {
		super.setChild(0, left);
	}
	

	/**
	 *
	 * This method returns the right tree element pointer
	 * @return the right child of this TreeElement
	 *
	 **/
	public BinTreeElement<E> getRight() {
		return (BinTreeElement<E>) super.getChild(1);
	}
	
	/**
	 *
	 * This method sets the right tree element pointer
	 * @param right the TreeElement that should be assigned to the right child
	 *
	 */
	public void setRight(BinTreeElement<E> right) {
		super.setChild(1, right);
	}
}
