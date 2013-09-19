package bridges;
import bridges.Retrieve;
import bridges.StackNode;
import bridges.StudentStack;
import java.util.EmptyStackException;

/** Pointer-based reference example of a student stack */
public class ReferenceStack<T> extends StudentStack<T> {
    private StackNode<T> root = null;
    
    public void push(T element) {
        root = new StackNode<T>(root, element);
    }
    
    public T pop() {
        if (root == null)
            throw new EmptyStackException();
        T element = root.element();
        root = root.next();
        return element;
    }
}