package bridges;
import bridges.TwoNode;
import bridges.StudentDoubleLinkedList;
import java.lang.IndexOutOfBoundsException;

/** Pointer-based reference example of a student stack. */
public class ReferenceDoubleLinkedList<T> extends StudentDoubleLinkedList<T> {
    // The name of this private variable can be anything
    private TwoNode<T> top = null;
    
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
    }
        
    public T get(int index) {
        // You can go backward. That is a feature!
        if (top == null)
            throw new IndexOutOfBoundsException();
        TwoNode<T> node = top;
        if (index < 0) {
            do {
                node = node.getLeft();
                index++;
            } while (index > 0);
        } else {
            while (index > 0) {
                node = node.getRight();
                index--;
            }
        }
        return node.getElement();
    }
}