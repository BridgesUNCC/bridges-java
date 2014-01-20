package edu.uncc.cs.bridges;
import edu.uncc.cs.bridges.*;
import java.lang.IndexOutOfBoundsException;

/** Pointer-based reference example of a student stack. */
public class ReferenceDoubleLinkedList<T> extends StudentDoubleLinkedList<T> {
    // The name of this private variable can be anything
    private TwoNode<T> top = null;
    private int length = 0;
    
    /** Note the getter: students need to implement this for visuals. */
    public TwoNode<T> top() {
        return top;
    }
    
    public void push(T element) {
        if (top == null) {
            top = new TwoNode<T>(top, top, element);
            top.setLeft(top);
            top.setRight(top);
        } else {
            TwoNode<T> newnode = new TwoNode<T>(top.getLeft(), top, element);
            top.setLeft(newnode);
            newnode.getLeft().setRight(newnode);
        }
        length++;
    }
    
    private TwoNode<T> scroll(int index) {
        // You can go backward. That is a feature!
        if (top == null || index >= length || index < -length)
            throw new IndexOutOfBoundsException();
        TwoNode<T> node = top;
        if (index < 0) {
            do {
                node = node.getLeft();
                index++;
            } while (index != 0);
        } else {
            while (index != 0) {
                node = node.getRight();
                index--;
            }
        }
        return node;
    }
        
    public T get(int index) {
        return scroll(index).getElement();
    }
    
    public T set(int index, T value) {
        scroll(index).setElement(value);
        return value; // May help method chaining
    }
    
    public T delete(int index) {
        TwoNode<T> node = scroll(index);
        node.getLeft().setRight( node.getRight() );
        node.getRight().setLeft( node.getLeft() );
        length--;
        return node.getElement();
    }
}