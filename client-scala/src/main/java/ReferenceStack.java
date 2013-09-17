package bridges;
import bridges.Retrieve;
import bridges.StackNode;
import bridges.StudentStack;

/** Pointer-based reference example of a student stack */
public class ReferenceStack<T> extends StudentStack<T> {
    private StackNode<T> root = null;
    public StackNode<T> root() {
        return root;
    }
    public void root_$eq(StackNode<T> node) {
        root=node;
    }
    
    public void push(T element) {
        root = new StackNode<T>(root, element);
    }
    
    public T pop() {
        if (root == null)
            return null;
        T element = root.element();
        root = root.next();
        return element;
    }
}