/**
 * 
 */
package bridges.base;

import java.security.Key;
import java.util.Map.Entry;

/**
 * @author mihai mehedint
 * This class extends the TreeElement class by adding a key property to allow for easier use in a binary search tree implementation. 
 */
public class BSTElement<K, E> extends TreeElement<E>{
	private K key; //this is the BSTElement key
	

	/**
	 * Construct an empty BSTElement with no key assigned and left and right pointers set to null.
	 */
	public BSTElement() {
		super();
	}

	/**Construct a BSTElement holding an object "e" with a left pointer assigned to "left" and a right pointer assigned to "right".
	 * @param e the object that BSTElement is holding
	 * @param left the BSTElement that should be assigned to the left pointer
	 * @param right the BSTElemetn taht should be assigned to the right pointer
	 */
	public BSTElement(E e, BSTElement<K, E> left, BSTElement<K, E> right) {
		super(e, left, right);
	}
	
	/**Construct a BSTElement with a key "key", holding an object "e" with a left pointer assigned to "left" and a right pointer assigned to "right".
	 * @param key the key to be used in a binary search tree implementation
	 * @param e the object this BSTElement is holding
	 * @param left the BSTElement that should be assigned to the left pointer
	 * @param right the BSTElement that should be assigned to the right pointer
	 */
	public BSTElement(K key, E e, BSTElement<K,E> left, BSTElement<K, E> right) {
		super(e, left, right);
		setKey(key);
	}

	/**Cosntruct a BSTElement holding the object "e", with no key assigned and left and right pointers set to null.
	 * @param e the object this BSTElement is holding
	 */
	public BSTElement(E e) {
		super(e);
	}

	/**Construct a BSTElement holding the object "e", with key "key" assigned and left and right pointers set to null.
	 * @param key the key to be used in a binary search tree implementation
	 * @param e the object this BSTElement is holding
	 */
	public BSTElement(K key, E e) {
		super(e);
		setKey(key);
	}

	/**Construct a BSTElement holding the object "e", with label set to "label", with no key assigned, and left and right pointers set to null.
	 * @param label the label of BSTElement that shows up on the Bridges visualization
	 * @param e the object this BSTElement is holding
	 */
	public BSTElement(String label, E e) {
		super(label, e);
	}
	
	/**Construct a BSTElement holding the object "e", with label set to "label", with "key" assigned to key, and left and right pointers set to null.
	 * @param label the label of BSTElement that shows up on the Bridges visualization
	 * @param key the key to be used in a binary search tree implementation
	 * @param e the object this BSTElement is holding
	 */
	public BSTElement(String label, K key, E e) {
		super(label, e);
		setKey(key);
	}
	
	/**Construct an empty BSTElement, with no key assigned, and left and right pointers set to null.
	 * @param left the BSTElement that should be assigned to the left pointer
	 * @param right the BSTElement that should be assigned to the right pointer
	 */
	public BSTElement(BSTElement<K, E> left, BSTElement<K, E> right) {
		super(left, right);
	}

	/**Return the key of the BSTElement
	 * @return the key of this BSTElement
	 */
	public K getKey() {
		return key;
	}

	/**Set the key of the BSTElement to key
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
					// add this property to the element representation 
		this.getVisualizer().setKey(key.toString());
	}

	/* (non-Javadoc)
	 * @see edu.uncc.cs.bridgesV2.base.TreeElement#getLeft()
	 */
	@Override
	public BSTElement<K,E> getLeft() {
		return (BSTElement<K,E>) super.getLeft();
	}

	/* (non-Javadoc)
	 * @see edu.uncc.cs.bridgesV2.base.TreeElement#getRight()
	 */
	@Override
	public BSTElement<K,E> getRight() {
		return (BSTElement<K,E>) super.getRight();
	}

	/* (non-Javadoc)
	 * @see edu.uncc.cs.bridgesV2.base.Element#getRepresentation()
	 */
	@Override
	public String getRepresentation() {
		String locx = "0.0", locy = "0.0";
		String json = "{";
		for (Entry<String, String> entry : super.getVisualizer().properties.entrySet()) {
			if (entry.getKey() == "locationX")
				locx = entry.getValue();
			else if (entry.getKey() == "locationY")
				locy = entry.getValue();
            else
				json += String.format("\"%s\": \"%s\", ", entry.getKey(), 
									entry.getValue());
		}
		json += String.format("\"location\": [ %s , %s ], ", locx, locy);
		json += String.format("\"name\": \"%s\", ", super.getLabel());
							// must check type 
		String s = ((Object) this.getKey()).getClass().getName();
		if ( (s == "java.lang.Integer") || (s == "java.lang.Long") )
			json += String.format("\"key\": \"%d\"", this.getKey());
		else if ( (s == "java.lang.Float") || (s == "java.lang.Double") )
			json += String.format("\"key\": \"%f\"", this.getKey());
		else if ( (s == "java.lang.String") )
			json += String.format("\"key\": \"%s\"", this.getKey());
			
		return json + "}";
	}
	
	
	/**
	 * Comparing this BSTElement with another BSTElement
	 * @param e1
	 * @return boolean value
	 */
/*
	public boolean compare(BSTElement<K,E> e1){
		return this.getIdentifier().equals(e1.getIdentifier()) &&
				this.getKey().equals(e1.getKey()) &&
				this.getLabel().equals(e1.getLabel()) &&
				this.getLeft().equals(e1.getLeft()) &&
				this.getRight().equals(e1.getRight()) &&
				this.getValue().equals(e1.getValue()) &&
				this.getVisualizer().equals(e1.getVisualizer());
	} 	
*/
}
