package edu.uncc.cs.bridges_vs1.drivers.List_eval;
import edu.uncc.cs.bridgesV2.base.SLelement;

// Linked stack implementation
class LStack<E> implements Stack<E> {
  private SLelement<E> top;          // Pointer to first element
  private int size;		// Number of elements

  //Constructors
  public LStack() { top = null; size = 0; }
  public LStack(int size) { top = null; size = 0; }

  // Reinitialize stack
  public void clear() { top = null; size = 0; }
  
  public void push(E it) {      // Put "it" on stack
    top = new SLelement<E>(it, top);
    size++;
  }

  public E pop() {              // Remove "it" from stack
    assert top != null : "Stack is empty";
    E it = top.getValue();
    top = top.getNext();
    size--;
    return it;
  }

  public E topValue() {         // Return top value
    assert top != null : "Stack is empty";
    return top.getValue();
  }

  public SLelement<E>  stackTop() {         // Return top value
    return top;
  }

  public int length() { return size; } // Return length

// Extra stuff not printed in the book.

  /**
   * Generate a human-readable representation of this stack's contents
   * that looks something like this: < 1 2 3 >.
   * This method uses toString() on the individual elements.
   * @return The string representation of this stack
   */
  public String toString()
  {
    StringBuffer out = new StringBuffer((length() + 1) * 4);
    out.append("< ");
    SLelement<E> temp = top;
    while (temp != null) {
      out.append(temp.getValue());
      out.append(" ");
      temp = temp.getNext();
    }
    out.append(">");
    return out.toString();
  }
}
