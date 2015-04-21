package edu.uncc.cs.bridges_vs1.drivers.List_eval;

/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Stack ADT */
public interface Stack<E> {

  /** Reinitialize the stack.  The user is responsible for
      reclaiming the storage used by the stack elements. */
  public void clear();

  /** Push an element onto the top of the stack.
      @param it The element being pushed onto the stack. */
  public void push(E it);

  /** Remove and return the element at the top of the stack.
      @return The element at the top of the stack. */
  public E pop();

  /** @return A copy of the top element. */
  public E topValue();

  /** @return The number of elements in the stack. */
  public int length();
};
