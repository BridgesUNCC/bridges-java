package bridges.base;
import bridges.base.*;
/**
 *  @brief This class extends the BSTElement class by adding a height and balance factor
 *	fields that are useful in AVL trees.
 *
 *	AVL tree elements include a 'height' and a 'balFactor' value,
 *	representing the height and balance factor of the AVL tree at
 *  that node, respectively. This is useful in representing
 *	AVL trees.
 *
 *	AVLTree elements contain a visualizer (ElementVisualizer) object for setting visual
 *  attributes (color, shape, opacity, size), necessary for displaying them in a
 *  web browser.
 *
 *  AVLTree elements also have a LinkVisualizer object, that is used when they are
 *	linked to another element, appropriate for setting link attributes, for instance,
 *	between  the current element and its left or right child
 *
 *
 *  @sa Example tutorial using AVLTreeElement at
 *      http://bridgesuncc.github.io/tutorials/AVL.html
 *
 *  @param E the generic parameter object that is part of this element, representing
 *      application specific data.
 *  @param K is the search key parameter in the AVL tree node; K must be orderable, such
 *      as integer, float, string, etc., on which relational operators work.
 *
 *  @author Kalpathi Subramanian, Mihai Mehedint
 *
 *  @date 6/22/16, 1/7/17, 5/17/17, 7/12/19
 */

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
	 *  @param  h height
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
	 *  This method sets the balance factor of the tree at this node
	 *
	 *  @param  bf balance factor
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

	/**
	 *  Augment the element with the "height" and "balance factor" fields.
	 *
	 *  @return the augmented JSON string
	 */
	public String getElementRepresentation() {
		String orig_json_str = super.getElementRepresentation();

		String avl_str = QUOTE + "height" + QUOTE + COLON +
			Integer.toString(this.getHeight()) +  COMMA +
			QUOTE + "balance_factor" + QUOTE + COLON +
			Integer.toString(this.getBalanceFactor());

		String json_str = orig_json_str.substring(0, orig_json_str.length() - 1) + COMMA +
			avl_str + CLOSE_CURLY;

		return json_str;
	}
}
