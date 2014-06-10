
/** Binary Search Tree implementation for Dictionary ADT 
 The BST implements Dictionary and its methods
 *  * Title: Binary Search Tree ADT with Strings for Bridges
 * @author mihai mehedint
 * @version1.0 
 * @05.25.2014
 */
class BST<Key extends Comparable<? super Key>, E>
         implements Dictionary<Key, E> {
  private BST_Node<Key,E> root; // Root of the BST
  private int nodecount;       // Number of nodes in the BST
  /** Constructor */
  BST() { root = null; nodecount = 0; }
  
  
  /** @return The current subtree, modified to contain
   the new item */
private BST_Node<Key,E> inserthelp(BST_Node<Key,E> rt,
                                  Key k, E e) {

  if (rt == null) return new BST_Node<Key,E>(k, e);
  if (rt.key().compareTo(k) > 0)
      rt.setLeft(inserthelp(rt.left(), k, e));
  else
    rt.setRight(inserthelp(rt.right(), k, e));
  return rt;
}//end of insert help

/** Remove a node with key value k
    @return The tree with the node removed */
private BST_Node<Key,E> removehelp(BST_Node<Key,E> rt,Key k) {
  if (rt == null) return null;
  if (rt.key().compareTo(k) > 0)
    rt.setLeft(removehelp(rt.left(), k));
  else if (rt.key().compareTo(k) < 0)
    rt.setRight(removehelp(rt.right(), k));
  else { // Found it
    if (rt.left() == null) return rt.right();
    else if (rt.right() == null) return rt.left();
    else { // Two children
      BST_Node<Key,E> temp = getmin(rt.right());
      rt.setElement(temp.element());
      rt.setKey(temp.key());
      rt.setRight(deletemin(rt.right()));
        } 
  }
return rt; 
}//end of removehelp

//the findhelp finds and returns the object with key value k
private E findhelp(BST_Node<Key,E> rt, Key k) {
  if (rt == null) return null;
  if (rt.key().compareTo(k) > 0)
    return findhelp(rt.left(), k);
  else if (rt.key().compareTo(k) == 0) return rt.element();
  else return findhelp(rt.right(), k);
}//end of findhelp

//returns the min key value stored in the tree
private BST_Node<Key,E> getmin(BST_Node<Key,E> rt) {
  if (rt.left() == null) return rt;
  return getmin(rt.left());
}//end of getmin

//deletes the min value
private BST_Node<Key,E> deletemin(BST_Node<Key,E> rt) {
  if (rt.left() == null) return rt.right();
  rt.setLeft(deletemin(rt.left()));
  return rt;
}//end of deletemin

  /** Reinitialize tree */
  public void clear() { root = null; nodecount = 0; }
  /** Insert a record into the tree.
      @param k Key value of the record.
      @param e The record to insert. */
  public void insert(Key k, E e) {
    root = inserthelp(root, k, e);
    nodecount++;
}//end of clear
  
  
  /** Remove a record from the tree.
      @param k Key value of record to remove.
      @return The record removed, null if there is none. */
  public E remove(Key k) {
    E temp = findhelp(root, k);   // First find it
    if (temp != null) {
      root = removehelp(root, k); // Now remove it
      nodecount--;
    }
    return temp;
  }//end of remove method
  
  
  /** Remove and return the root node from the dictionary.
      @return The record removed, null if tree is empty. */
  public E removeAny() {
    if (root == null) return null;
    E temp = root.element();
    root = removehelp(root, root.key());
    nodecount--;
    return temp;
}//end of removeAny method
 
  
  /** @return Record with key value k, null if none exist.
      @param k The key value to find. */
  public E find(Key k) { return findhelp(root, k); }
  /** @return The number of records in the dictionary. */
  public int size() { return nodecount; }
  
  //this method traverses the tree and 
  //builds a StringBuilder object containing all the nodes' values
   public StringBuilder printTree(BST_Node<Key,E> rt, StringBuilder aCurrentTree){
      
      aCurrentTree.append(rt.key()+" "); //append the key value of current node visited 
      if (rt.left()!=null)
          printTree(rt.left(), aCurrentTree);//visit left node when the left node is not null
      if (rt.right()!=null)
          printTree(rt.right(), aCurrentTree);//visit right node when is not null
      
      return aCurrentTree;//return the the values of the tree in the form of a StringBuilder object
  }//end of printTree method
  
  //this method returns the values stored in the tree as a string
  public String toString()
  {
      StringBuilder aCurrentTree = new StringBuilder();
      //call the printTree method
      //the starting values for traversing the tree and
      //build the String containing the nodes' values are passed
      //to printTree method
      aCurrentTree = printTree(root, aCurrentTree);  
      return aCurrentTree.toString();//return the string value

  }//end of to STring
}//end of BST class