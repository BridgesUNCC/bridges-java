package bridges;
import bridges.TwoNode;
import bridges.StudentLinkedList;
import java.lang.IndexOutOfBoundsException;

/** Pointer-based reference example of a student stack. */
public class ReferenceDoubleLinkedList<T> extends StudentLinkedList<T> {
    // The name of this private variable can be anything
    private TwoNode<T> top = null;
    
    /** Note the getter: students need to implement this for visuals. */
    public TwoNode<T> top() {
        return top;
    }
    
    public void push(T element) {
        if (top == null) {
            top = new TwoNode<T>(top, top, element);
            top.left = top;
            top.right = top;
        } else {
            StackNode<T> newnode = new TwoNode<T>(top.left, top, element);
            top.left = newnode;
            newnode.left.right = newnode;
        }
    }
        
    public T get(int index) {
        // You can go backward. That is a feature!
        if (top == null)
            throw new IndexOutOfBoundsException();
        TwoNode<T> node = top;
        if (index < 0) {
            do {
                node = node.left;
                index++;
            } while (index);
        } else {
            while (index) {
                node = node.right;
                index--;
            }
        }
        return node;
    }
}