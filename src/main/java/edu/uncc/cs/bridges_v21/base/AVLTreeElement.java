package bridges.base;
import bridges.base.*;
/**
 *  @brief This class extends the BSTElement class for use in building AVL 
 *	trees.
 *	AVL tree elements include a 'height' and a 'balFactor' value, 
 *	representing the height and balance factor of the AVL tree at 
 *  that node, respectively. This is useful in representing
 *	AVL trees.

 *  Generic paramaters: K - key value, E - appl dependent data component
 *
 * 	@author Kalpathi Subramanian
 * 	@date   8/8/16
 **/
public class AVLTreeElement<K, E>  extends BSTElement<K, E> {
	private Integer height, balFactor;

	/**
 	 *
 	 *  Construct an AVLTreeElement with default values 
	 *
	 */
	public AVLTreeElement() {
		super();
		height = balFactor = 0;
	}
	/**
 	 *
 	 *  Construct an AVLTreeElement holding a key value  "k" and an object "e" 
	 *
 	 *  @param k the search key
 	 *  @param e the appl specific object that Element is holding
 	 *
 	 */
	public AVLTreeElement(K k, E e) {
		super(e);
		setKey(k);
		height = balFactor = 0;
	}

	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 *
	 */
	public String getDataStructType() {
		return "AVLTree";
	}

	/**
	 *	This method returns the height of the tree at this node
	 *
	 *	@return  height
	 *
	 */
    public int getHeight() {
        return height;
    }

	/**
	 *	This method sets the height of the tree at this node
	 *
	 *  @param   height h
	 *
	 */
    public void setHeight(int h) {
        height = h;
    }

	/**
	 *	This method returns the balance factor  of the tree at this node
	 *
	 *	@return  balance factor
	 *
	 */
    public int getBalanceFactor() {
        return balFactor;
    }

	/**
	 *	This method sets the balance factor of the tree at this node
	 *
	 *  @param   balance factor  bf
	 */
    public void setBalanceFactor(int bf) {
        balFactor = bf;
    }

	/**
	 *
	 * This method returns the left child of the tree node
	 *
	 * @return the left child of this node
	 *
	 */
	public AVLTreeElement<K, E> getLeft() {
		return (AVLTreeElement<K, E>) super.getLeft();
	}

	/**
	 *
	 * This method returns the right child of tree node
	 *
	 * @return the right child of this node
	 *
	 */
	public AVLTreeElement<K, E> getRight() {
		return (AVLTreeElement<K, E>) super.getRight();
	}
}
