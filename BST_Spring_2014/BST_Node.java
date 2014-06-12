/** Binary tree node implementation: Pointers to children
 *  @author: Mihai Mehedint
    @param E The data element
    @param Key The associated key for the record */
 class BST_Node<Key, E> {
  private Key key;              // Key for this node
  private E element;            // Element for this node
  private BST_Node<Key,E> left;  // Pointer to left child
  private BST_Node<Key,E> right; // Pointer to right child
  /** Constructors */
  public BST_Node() {left = right = null; }
  public BST_Node(Key k, E val)
  { left = right = null; key = k; element = val; }
  public BST_Node(Key k, E val,
                 BST_Node<Key,E> l, BST_Node<Key,E> r)
  { left = l; right = r; key = k; element = val; }
  /** Get and set the key value */
  public Key key() { return key; }
  public void setKey(Key k) { key = k; }
  /** Get and set the element value */
  public E element() { return element; }
  public void setElement(E v) { element = v; }
  /** Get and set the left child */
  public BST_Node<Key,E> left() { return left; }
  public void setLeft(BST_Node<Key,E> p) { left = p; }
  /** Get and set the right child */
  public BST_Node<Key,E> right() { return right; }
  public void setRight(BST_Node<Key,E> p) { right = p; }
  /** @return True if a leaf node, false otherwise */
  public boolean isLeaf()
  { return (left == null) && (right == null); }
}
