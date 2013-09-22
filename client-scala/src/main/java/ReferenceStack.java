package bridges;
import bridges.OneNode;
import bridges.StudentStack;
import java.util.EmptyStackException;

/** Pointer-based reference example of a student stack. */
public class ReferenceStack<T> extends StudentStack<T> {
    // The name of this private variable can be anything
    private OneNode<T> top = null;
    
    /** Note the getter: students need to implement this for visuals. */
    public OneNode<T> top() {
        return top;
    }
    
    public void push(T element) {
        top = new OneNode<T>(top, element);
    }
    
    public T pop() {
        if (top == null)
            throw new EmptyStackException();
        T element = top.element();
        top = top.next();
        return element;
    }
}