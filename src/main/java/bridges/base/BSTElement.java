package bridges.base;

import java.security.Key;
import java.util.Map.Entry;
import org.json.simple.JSONValue;

/**
 *  @brief The BSTElement class is the building block for creating binary search trees.
 *
 *	The BSTElement class is the building block for creating binary search tree structures.
 *  It contains two children (viz., left, right), and a search key, to be used
 *  in search operations .
 *
 *  BSTElement contains a visualizer (ElementVisualizer) object for setting visual
 *  attributes (color, shape, opacity, size), necessary for displaying them in a
 *  web browser.
 *
 *  BST Elements also have a LinkVisualizer object, that is used when they are linked to
 *  another element, appropriate for setting link attributes, for instance, between
 *  the current element and its left or right child
 *
 *	@param E he generic parameter object that is part of this element, representing
 *      application specific data.
 *	@param K is the search key parameter in the BST node; K must be orderable, such
 *		as integer, float, string, etc., on which relational operators work.
 *
 *	@author Kalpathi Subramanian, Mihai Mehedint
 *
 *	@date 6/22/16, 1/7/17, 5/17/17
 *
 *	@brief This class extends the BinTreeElement class by adding a 'key' value
 *	for use in a binary search tree implementations.
 *
 *	\sa Example tutorial using BSTElement at  <br>
 *		http://bridgesuncc.github.io/Hello_World_Tutorials/BST.html
 *
 */
public class BSTElement<K, E> extends BinTreeElement<E> {
	private K key; //this is the BSTElement key


	/**
	 *
	 * 	Construct an empty BSTElement with no key assigned and left and
	 *	right pointers set to null.
	 *
	 **/
	public BSTElement() {
		super();
	}

	/**
	 *
	 * 	Construct a BSTElement holding an object "e" with a left pointer
	 *	assigned to "left" and a right pointer assigned to "right".
	 * 	@param e the object that BSTElement is holding
	 * 	@param left the BSTElement that should be assigned to the left pointer
	 * 	@param right the BSTElemetn taht should be assigned to the right pointer
	 *
	 */
	public BSTElement(E e, BSTElement<K, E> left, BSTElement<K, E> right) {
		super(e, left, right);
	}

	/**
	 *	Construct a BSTElement with a key "key", holding an object "e"
	 *	with a left pointer assigned to "left" and a right pointer assigned
	 *	to "right".
	 *
	 * @param key the key to be used in a binary search tree implementation
	 * @param e the object this BSTElement is holding
	 * @param left the BSTElement that should be assigned to the left pointer
	 * @param right the BSTElement that should be assigned to the right pointer
	 *
	 **/
	public BSTElement(K key, E e, BSTElement<K, E> left, BSTElement<K, E> right) {
		super(e, left, right);
		setKey(key);
	}

	/**
	 *	Construct a BSTElement holding the object "e", with no key assigned
	 *	and left and right pointers set to null.
	 *
	 * 	@param e the object this BSTElement is holding
	 **/
	public BSTElement(E e) {
		super(e);
	}

	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 **/
	public String getDataStructType() {
		return "BinarySearchTree";
	}

	/**
	 *
	 *	Construct a BSTElement holding the object "e", with key "key"
	 *	assigned and left and right pointers set to null.
	 * 	@param key the key to be used in a binary search tree implementation
	 * 	@param e the object this BSTElement is holding
	 *
	 **/
	public BSTElement(K key, E e) {
		super(e);
		setKey(key);
	}

	/**
	 *	Construct a BSTElement holding the object "e", with label set to
	 *	"label", with no key assigned, and left and right pointers set to null.
	 * 	@param label the label of BSTElement that shows up on the Bridges
	 *	visualization
	 * 	@param e the object this BSTElement is holding
	 **/
	public BSTElement(String label, E e) {
		super(label, e);
	}

	/**
	 *	Construct a BSTElement holding the object "e", with label set to
	 *	"label", with "key" assigned to key, and left and right pointers
	 *	set to null.
	 *
	 * @param label the label of BSTElement that shows up on the Bridges
	 *		visualization
	 * @param key the key to be used in a binary search tree implementation
	 * @param e the object this BSTElement is holding
	 *
	 **/
	public BSTElement(String label, K key, E e) {
		super(label, e);
		setKey(key);
	}

	/**
	 *
	 *	Construct an empty BSTElement, with no key assigned, and left and
	 *	right pointers set to null.
	 * 	@param left the BSTElement that should be assigned to the left pointer
	 * 	@param right the BSTElement that should be assigned to the right pointer
	 *
	 **/
	public BSTElement(BSTElement<K, E> left, BSTElement<K, E> right) {
		super(left, right);
	}

	/**
	 *	Return the key of the BSTElement
	 *
	 * 	@return the key of this BSTElement
	 *
	 **/
	public K getKey() {
		return key;
	}

	/**
	 *
	 *	Set the key of the BSTElement to key
	 * 	@param key the key to set
	 **/
	public void setKey(K key) {
		this.key = key;
		// add this to the element's properties
	}

	/**
	 *	Return the left child of the BSTElement
	 *
	 * 	@return the left child of this BSTElement
	 *
	 **/
	@Override
	public BSTElement<K, E> getLeft() {
		return (BSTElement<K, E>) super.getLeft();
	}

	/**
	 *	Return the right child of the BSTElement
	 *
	 * 	@return the right child of this BSTElement
	 *
	 **/
	@Override
	public BSTElement<K, E> getRight() {
		return (BSTElement<K, E>) super.getRight();
	}

	/**
	 *  Augment the element with the "key" field.
	 *
	 *  @return the augmented JSON string
	 */

	@Override
	public String getElementRepresentation() {
		String orig_json_str = super.getElementRepresentation();

		String key_str = QUOTE + "key" + QUOTE + COLON +
			QUOTE + JSONValue.escape(this.getKey().toString()) +  QUOTE;

		String json_str = orig_json_str.substring(0, orig_json_str.length() - 1) + COMMA +
			key_str + CLOSE_CURLY;

		return json_str;
	}

}
