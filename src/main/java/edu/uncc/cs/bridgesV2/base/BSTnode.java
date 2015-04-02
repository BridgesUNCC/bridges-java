/**
 * 
 */
package edu.uncc.cs.bridgesV2.base;

/**
 * @author mihai mehedint
 *
 */
public class BSTnode<K, E> extends TreeElement<E>{
	private K key; //this is the BSTnode key 

	/**
	 * 
	 */
	public BSTnode() {
		super();
	}

	/**
	 * @param e
	 * @param left
	 * @param right
	 */
	public BSTnode(E e, BSTnode<K, E> left, BSTnode<K, E> right) {
		super(e, left, right);
	}
	
	/**
	 * @param key
	 * @param e
	 * @param left
	 * @param right
	 */
	public BSTnode(K key, E e, BSTnode<K,E> left, BSTnode<K, E> right) {
		super(e, left, right);
		this.key = key;
	}

	/**
	 * @param e
	 */
	public BSTnode(E e) {
		super(e);
	}
	/**
	 * @param key
	 * @param e
	 */
	public BSTnode(K key, E e) {
		super(e);
		this.key = key;
	}

	/**
	 * @param label
	 * @param e
	 */
	public BSTnode(String label, E e) {
		super(label, e);
	}
	
	/**
	 * @param label
	 * @param key
	 * @param e
	 */
	public BSTnode(String label, K key, E e) {
		super(label, e);
		this.key = key;
	}
	
	/**
	 * @param left
	 * @param right
	 */
	public BSTnode(BSTnode<K, E> left, BSTnode<K, E> right) {
		super(left, right);
	}

	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}	
}
