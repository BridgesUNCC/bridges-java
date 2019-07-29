package bridges.base;

import java.security.Key;
import java.util.Map.Entry;

/**
 *  @brief The This class can be used to create K-d Tree  elements, derived
 *  from BSTElement. K-D trees can be thought of as the spatial equivalent
 *  binary search trees and operate on multiple dimensions (2D and 3D are
 *  most common). These trees serve as a representation of the underlying
 *  geometrically defined spaces. Specialized versions of these trees
 *  include quadtrees and octrees, which subdivide into equal sized quadrants
 *  octants at each level, respectively.

 *  This class extends the BSTElement class by adding a dimension
 *  property.  It also includes a thickness property for displaying
 *  the partitioning lines generated by the convex decomposition. This
 *  thickness get passed to some constructor KdTreeElement() and to
 *  setThickness(). It is purely a graphical attribute.
 *
 *  convenient to generate visual representation
 *  to allow for use in a binary search tree implementation.
 *
 * Generic Parameters:
 *      K that is the search key type - this is usually a number, integer
 *          or float
 *      E the application data type
 *
 * @author Kalpathi Subramanian
 * @date 12/26/18, 7/12/19
 */

public class KdTreeElement<K, E> extends BSTElement<K, E> {
	private int dimension = 0;      // dimension, in the range [0..k-1]
	private float thickness = 0;        // thickness of partitioning lines


	/**
	  * Constructs a KdTreeElement with the provided value, label, key,
	  * left and right KdTree elements.  The defaults will be used if
	  * not provided.
	  *
	  * @param k The key for ordering
	  * @param dim number of dimensions
	  * @param th thickness (used in rendering)
	  * @param l The left KdTree Element
	  * @param r The right KdTree Element
	  * @param val The data to hold
	  * @param lab The label to show
	  */
	public  KdTreeElement(K k, int dim, float th, KdTreeElement<K, E> l, KdTreeElement<K, E> r,
		E val, String lab) {
		super (val, l, r);
		this.thickness = th;
		this.dimension = dim ;
		this.setKey(k);
	}

	/**
	 *
	 * 	Construct an empty KdTreeElement with no key assigned and left and
	 *	right pointers set to null.
	 *
	 **/
	public KdTreeElement() {
		super();
		this.thickness = 0.0f;
		this.dimension = 0;
	}

	/**
	 *
	 * 	Construct a KdTreeElement holding an object "e" with a left pointer
	 *	assigned to "left" and a right pointer assigned to "right".
	 * 	@param e the object that KdTreeElement is holding
	 * 	@param left the KdTreeElement that should be assigned to the left pointer
	 * 	@param right the KdTreeElement taht should be assigned to the right pointer
	 *
	 */
	public KdTreeElement(E e, KdTreeElement<K, E> left, KdTreeElement<K, E> right) {
		super(e, left, right);
		this.thickness = 0.0f;
		this.dimension = 0;
	}

	/**
	 *	Construct a KdTreeElement with a key "key", holding an object "e"
	 *	with a left pointer assigned to "left" and a right pointer assigned
	 *	to "right".
	 *
	 * @param key the key to be used in a binary search tree implementation
	 * @param e the object this KdTreeElement is holding
	 * @param left the KdTreeElement that should be assigned to the left pointer
	 * @param right the KdTreeElement that should be assigned to the right pointer
	 *
	 **/
	public KdTreeElement(K key, E e, KdTreeElement<K, E> left, KdTreeElement<K, E> right) {
		super(e, left, right);
		this.setKey(key);
	}

	/**
	 *	Construct a KdTreeElement holding the object "e", with no key assigned
	 *	and left and right pointers set to null.
	 *
	 * 	@param e the object this KdTreeElement is holding
	 **/
	public KdTreeElement(E e) {
		super(e);
	}

	/**
	 *	Construct a KdTreeElement given the key and dimension
	 *
	 * 	@param key the key to be used in a binary search tree implementation
	 * 	@param dim the dimension of partitioning
	 **/
	public KdTreeElement(K key, int dim) {
		super();
		this.setKey(key);
		this.dimension = dim;
		this.thickness = 0.0f;
	}

	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 **/
	public String getDataStructType() {
		return "KdTree";
	}

	/**
	 *
	 *	Construct a KdTreeElement holding the object "e", with key "key"
	 *	assigned and left and right pointers set to null.
	 * 	@param key the key to be used in a binary search tree implementation
	 * 	@param e the object this KdTreeElement is holding
	 *
	 **/
	public KdTreeElement(K key, E e) {
		super(e);
		this.setKey(key);
	}

	/**
	 *	Construct a KdTreeElement holding the object "e", with label set to
	 *	"label", with no key assigned, and left and right pointers set to null.
	 * 	@param label the label of KdTreeElement that shows up on the Bridges
	 *	visualization
	 * 	@param e the object this KdTreeElement is holding
	 **/
	public KdTreeElement(String label, E e) {
		super(label, e);
	}

	/**
	 *	Construct a KdTreeElement holding the object "e", with label set to
	 *	"label", with "key" assigned to key, and left and right pointers
	 *	set to null.
	 *
	 * @param label the label of KdTreeElement that shows up on the Bridges
	 *		visualization
	 * @param key the key to be used in a binary search tree implementation
	 * @param e the object this KdTreeElement is holding
	 *
	 **/
	public KdTreeElement(String label, K key, E e) {
		super(label, e);
		this.setKey(key);
	}

	/**
	 *
	 *	Construct an empty KdTreeElement, with no key assigned, and left and
	 *	right pointers set to null.
	 * 	@param left the KdTreeElement that should be assigned to the left pointer
	 * 	@param right the KdTreeElement that should be assigned to the right pointer
	 *
	 **/
	public KdTreeElement(KdTreeElement<K, E> left, KdTreeElement<K, E> right) {
		super(left, right);
	}

	/**
	 *	Return the partitioning dimension
	 *
	 * 	@return the partitioning dimension of this KdTreeElement
	 *
	 **/
	public int getDimension() {
		return dimension;
	}

	/**
	 *
	 *	Set the dimension of the KdTreElement to key
	 * 	@param dim the dimension to set
	 **/
	public void setDimension(int dim) {
		this.dimension = dimension;
	}
	/**
	 *	Return the thickness of the KdtreeElement;
	 * 		thickness is used in the visualization to draw the partitioning lines
	 *
	 * 	@return the thickness of this KdTreeElement
	 *
	 **/
	public float getThickness() {
		return thickness;
	}

	/**
	 *
	 *	Set the thickness of the KdTreeElement
	 * 	@param th thickness of the partitioner to set
	 *			thickness is used in the visualization to draw the partitioning lines
	 **/
	public void setThickness(float th) {
		this.thickness = th;
	}

	/**
	 *	Return the left child
	 *
	 * 	@return the left child of this KdTreeElement
	 *
	 **/
	@Override
	public KdTreeElement<K, E> getLeft() {
		return (KdTreeElement<K, E>) super.getLeft();
	}

	/**
	 *	Return the right child
	 *
	 * 	@return the right child of this KdTreeElement
	 *
	 **/
	@Override
	public KdTreeElement<K, E> getRight() {
		return (KdTreeElement<K, E>) super.getRight();
	}

	/**
	 *  Augment the element with the "height" and "balance factor" fields.
	 *
	 *  @return the augmented JSON string
	 */
	public String getElementRepresentation() {
		String orig_json_str = super.getElementRepresentation();

		String kdt_str = QUOTE + "dimension" + QUOTE + COLON +
			Integer.toString(this.getDimension()) +  COMMA +
			QUOTE + "thickness" + QUOTE + COLON +
			Float.toString(this.getThickness());

		String json_str = orig_json_str.substring(0, orig_json_str.length() - 1) + COMMA +
			kdt_str + CLOSE_CURLY;

		return json_str;
	}
}
