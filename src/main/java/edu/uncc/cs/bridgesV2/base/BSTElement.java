/**
 * 
 */
package edu.uncc.cs.bridgesV2.base;

/**
 * @author mihai mehedint
 *
 */
public class BSTElement<K, E> extends TreeElement<E>{
	private K key; //this is the BSTElement key 

	/**
	 * 
	 */
	public BSTElement() {
		super();
	}

	/**
	 * @param e
	 * @param left
	 * @param right
	 */
	public BSTElement(E e, BSTElement<K, E> left, BSTElement<K, E> right) {
		super(e, left, right);
	}
	
	/**
	 * @param key
	 * @param e
	 * @param left
	 * @param right
	 */
	public BSTElement(K key, E e, BSTElement<K,E> left, BSTElement<K, E> right) {
		super(e, left, right);
		this.key = key;
	}

	/**
	 * @param e
	 */
	public BSTElement(E e) {
		super(e);
	}
	/**
	 * @param key
	 * @param e
	 */
	public BSTElement(K key, E e) {
		super(e);
		this.key = key;
	}

	/**
	 * @param label
	 * @param e
	 */
	public BSTElement(String label, E e) {
		super(label, e);
	}
	
	/**
	 * @param label
	 * @param key
	 * @param e
	 */
	public BSTElement(String label, K key, E e) {
		super(label, e);
		this.key = key;
	}
	
	/**
	 * @param left
	 * @param right
	 */
	public BSTElement(BSTElement<K, E> left, BSTElement<K, E> right) {
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

	/* (non-Javadoc)
	 * @see edu.uncc.cs.bridgesV2.base.TreeElement#getLeft()
	 */
	@Override
	public BSTElement<?,E> getLeft() {
		// TODO Auto-generated method stub
		return (BSTElement<?,E>)super.getLeft();
	}

	/* (non-Javadoc)
	 * @see edu.uncc.cs.bridgesV2.base.TreeElement#getRight()
	 */
	@Override
	public BSTElement<?,E> getRight() {
		// TODO Auto-generated method stub
		return (BSTElement<?,E>)super.getRight();
	}	
}
