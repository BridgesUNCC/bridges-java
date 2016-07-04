package bridges.base;

import bridges.base.*;

public class AVLTreeElement<K, E>  extends BSTElement<K, E> {
	private Integer height, balFactor;

	public AVLTreeElement() {
		super();
		height = balFactor = 0;
	}
	public AVLTreeElement(K k, E e) {
		super(e);
		setKey(k);
		height = balFactor = 0;
	}

    public int getHeight() {
        return height;
    }
    public void setHeight(int h) {
        height = h;
    }

    public int getBalanceFactor() {
        return balFactor;
    }
    public void setBalanceFactor(int bf) {
        balFactor = bf;
    }

	public AVLTreeElement<K, E> getLeft() {
		return (AVLTreeElement<K, E>) super.getLeft();
	}

	public AVLTreeElement<K, E> getRight() {
		return (AVLTreeElement<K, E>) super.getRight();
	}
}
