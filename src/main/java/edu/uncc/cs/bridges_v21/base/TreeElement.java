package bridges.base;

import java.util.Vector;
/**
 * @brief This class extends Element to represent general trees with
 *	arbitrary number of children.
 *
 * 	TreeElement nodes can have an arbitrary number of child nodes(held in
 * 	in a vector in the order in which they were added). The
 * 	visualization of trees assumes that the children are drawn in order
 * 	from left to right.
 *
 *  Tree Elements have labels (string) that are displayed on the visualization.
 *  Elements take an generic object E as a user defined parameter, which can be
 *  any native type or object.
 *
 *  Elements contain a visualizer (ElementVisualizer) object for setting visual
 *  attributes (color, shape, opacity, size), necessary for displaying them in a web
 *  browser.
 *
 *  Elements also have a LinkVisualizer object that is used when they are
 *  linked to another element, appropriate for setting link attributes, between parent
 * 	and child nodes.
 *
 * 	@author Kalpathi  Subramanian
 *
 * 	@date  6/22/16, 5/17/17
 *
 *  @param <E> The generic parameter object that is part of this element, representing
 *          application specific data.
 *
 *	\sa Example tutorial at <br> ??
 */
public class TreeElement<E> extends Element<E> {

	// holds all children of the node
	private Vector<TreeElement<E>> children;

	/**
	 *
	 *	Constructs an empty TreeElement with first two children
	 *	set to null.
	 *
	 */
	public TreeElement() {
		super();
		children = new Vector<TreeElement <E>> (2);
	}

	/**
	 *
	 *	Constructs a TreeElement holding an object "e" with first two children
	 *	set to null.
	 *
	 * @param e the generic object that TreeElement will hold
	 *
	 */
	public TreeElement (E e) {
		super(e);
		children = new Vector<TreeElement <E>> (2);
	}

	/**
	 *	Constructs a TreeElement with label set to "label", holding an
	 *		object "e".
	 * 	@param label the label of TreeElement that shows up on the Bridges
	 *		visualization
	 * 	@param e the generic object that TreeElement will hold
	 *
	 */
	public TreeElement (String label, E e) {
		super(label, e);
		children = new Vector<TreeElement <E>> (2);
	}

	/**
	 *
	 *	Constructs an empty TreeElement left pointer pointing to child 0
	 *	and right pointer pointing to child 1.
	 *
	 * 	@param left the TreeElement to be assigned to the child 0 of
	 *		this TreeElement
	 * 	@param right the TreeElement to be assigned to the child 1
	 *		of this TreeElement
	 *
	 */
	public TreeElement(TreeElement<E> left, TreeElement<E> right) {
		super();
		children = new Vector<TreeElement <E>> (2);
		children.add(left);
		children.add(right);
	}

	/**
	 *
	 *	Constructs a TreeElement holding the object "e", left pointer
	 *	pointing to first child  and right pointer pointing to second child
	 *
	 * @param e the generic object that TreeElement will hold
	 * @param left the TreeElement to be assigned to the first child of
	 *		this TreeElement
	 * @param right the TreeElement to be assigned to the second child
	 *		of this TreeElement
	 *
	 */
	public TreeElement(E e, TreeElement<E> left, TreeElement<E> right) {
		super(e);
		children = new Vector<TreeElement <E>> (2);
		children.add(left);
		children.add(right);
	}

	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 */
	public String getDataStructType() {
		return "Tree";
	}

	/**
	 *  Adds a child to the node
	 *
	 */
	public void addChild(TreeElement<E> child) {
		children.add(child);
	}

	/**
	 *
	 * Returns the number of children at this node
	 *
	 * @return  number of children
	 *
	 */
	public int getNumberOfChildren() {
		return children.size();
	}

	/**
	 *	adds a child to the node - will be added at the next open position
	 *
	 * @param  child to be added
	 *
	 * @return none
	 *
	 */
	public void setChild (int index, TreeElement<E> child) {
		if (index < children.size())
			children.set(index, child);
	}

	/**
	 *	gets a child at a particular index
	 *
	 * @param  index into the list of children
	 *
	 * @return child to be returned
	 *
	 */
	public TreeElement<E> getChild(int index) {
		if (index < children.size())
			return  children.get(index);
		else
			return null;
	}

	/**
	 *	Get  hierarchical JSON of the tree representation
	 *
	 *	@return the JSON string
	 */
	public String[] getDataStructureRepresentation() {
		//	String[] nodes_links = new String[2];
		//	nodes_links[0] = preOrder(this);
		//	nodes_links[1] = "";

		String[] nodes_links = {preOrder(this), ""};

		return nodes_links;
	}

	/**
	 *
	 *	Use a preorder traversal to directly extract a hierarchical JSON
	 *	representation of the tree.
	 *
	 */
	private String preOrder(TreeElement<E> root) {
		String json_str = "", children = "", link_props = "", elem_rep = "";
		String t_str;
		int num = root.getNumberOfChildren();
		if (root != null) {
			// first get the node representation
			elem_rep = root.getElementRepresentation();
			// remove surrounding curly braces
			t_str = elem_rep.substring(1, elem_rep.length() - 1);
			json_str += t_str;
			// now get the children
			if (root.getNumberOfChildren() > 0)
				json_str += COMMA + QUOTE + "children" + QUOTE + COLON + OPEN_BOX ;
			//			else json_str += CLOSE_CURLY;
			for (int k = 0; k < root.getNumberOfChildren(); k++) {
				if (root.getChild(k) == null) {
					json_str += OPEN_CURLY + QUOTE + "name" + QUOTE + COLON +
						QUOTE + "NULL" + QUOTE + CLOSE_CURLY + COMMA;
				}
				else {
					LinkVisualizer lv =
						root.getLinkVisualizer(root.getChild(k));
					json_str += OPEN_CURLY;
					if (lv != null) {
						json_str +=
							QUOTE + "linkProperties" + QUOTE + COLON + OPEN_CURLY +
							QUOTE + "color" + QUOTE + COLON +
							OPEN_BOX +
							Integer.toString(lv.getColor().getRed()) + COMMA +
							Integer.toString(lv.getColor().getGreen()) + COMMA +
							Integer.toString(lv.getColor().getBlue()) + COMMA +
							Float.toString(lv.getColor().getAlpha()) +
							CLOSE_BOX + COMMA +
							QUOTE + "thickness" + QUOTE + COLON +
							String.valueOf(lv.getThickness()) +
							CLOSE_CURLY + COMMA;
					}
					else
						json_str += "linkProperties" + COLON + "{}" + COMMA;
					// process its children
					json_str +=	preOrder(root.getChild(k));
					json_str += CLOSE_CURLY + COMMA;
				}
			}
			// remove last comma
			json_str = json_str.substring(0, json_str.length() - 1);
			// end of children
			json_str += CLOSE_BOX;
		}
		return json_str;
	}
}
