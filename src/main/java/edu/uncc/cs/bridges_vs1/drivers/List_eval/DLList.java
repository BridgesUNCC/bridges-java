package edu.uncc.cs.bridges_vs1.drivers.List_eval;
/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
 */

import edu.uncc.cs.bridgesV2.base.*;

// Doubly linked list implementation
class DLList<E> implements List<E> {
	private DLelement<E> head; // Pointer to list header
	private DLelement<E> tail; // Pointer to last element in list
	protected DLelement<E> curr; // Pointer ahead of current element
	int cnt; // Size of list

	// Constructors
	DLList(int size) {
		this();
	} // Ignore size

	DLList() {
		curr = head = new DLelement<E>(); // Create header node
		tail = new DLelement<E>();
		tail.setPrev(head);
		head.setNext(tail);
	
		
		cnt = 0;
	}

	public void clear() { // Remove all elements from list
		head.setNext(null); // Drop access to rest of links
		curr = head = new DLelement<E>(); // Create header node
		tail = new DLelement<E>();
		tail.setPrev(head);
		head.setNext(tail);
		cnt = 0;
	}

	public void moveToStart() // Set curr at list start
	{
		curr = head;
	}

	public void moveToEnd() // Set curr at list end
	{
		curr = tail.getPrev();
	}

	/** Insert "it" at current position */
	public void insert(E it) {
		curr.setNext(new DLelement<E>(it, curr.getNext(), curr));
		curr.getNext().setLabel("" + it);
		curr.getNext().getNext().setPrev(curr.getNext());
		cnt++;
	}

	/** Append "it" to list, note DLelement constructor takes pointers in the
	 * reverse order of the book's implementation.
	 */
	public void append(E it) {
		tail.setPrev(new DLelement<E>(it, tail, tail.getPrev()));
		tail.getPrev().setLabel("" + it);
		tail.getPrev().getPrev().setNext(tail.getPrev());
		cnt++;
	}

	/** Remove and return current element */
	public E remove() {
		if (curr.getNext() == tail)
			return null; // Nothing to remove
		E it = curr.getNext().getValue(); // Remember value
		curr.getNext().getNext().setPrev(curr);
		curr.setNext(curr.getNext().getNext()); // Remove from list
		cnt--; // Decrement the count
		return it; // Return value removed
	}

	/** Move curr one step left; no change if at front */
	public void prev() {
		if (curr != head) // Can't back up from list head
			curr = curr.getPrev();
	}

	// Move curr one step right; no change if at end
	public void next() {
		if (curr != tail.getPrev())
			curr = curr.getNext();
	}

	public int length() {
		return cnt;
	}

	// Return the position of the current element
	public int currPos() {
		DLelement<E> temp = head;
		int i;
		for (i = 0; curr != temp; i++)
			temp = temp.getNext();
		return i;
	}

	// Move down list to "pos" position
	public void moveToPos(int pos) {
		assert (pos >= 0) && (pos <= cnt) : "Position out of range";
		curr = head;
		for (int i = 0; i < pos; i++)
			curr = curr.getNext();
	}

	public E getValue() { // Return current element
		if (curr.getNext() == tail)
			return null;
		return curr.getNext().getValue();
	}

	public DLelement<E> getFront() {
		return head;
	}
	
	// Extra stuff not printed in the book.

	/**
	 * Generate a human-readable representation of this list's contents that
	 * looks something like this: < 1 2 3 | 4 5 6 >. The vertical bar represents
	 * the current location of the fence. This method uses toString() on the
	 * individual elements.
	 * 
	 * @return The string representation of this list
	 */
	public String toString() {
		// Save the current position of the list
		int oldPos = currPos();
		int length = length();
		StringBuffer out = new StringBuffer((length() + 1) * 4);

		moveToStart();
		out.append("< ");
		for (int i = 0; i < oldPos; i++) {
			out.append(getValue());
			out.append(" ");
			next();
		}
		out.append("| ");
		for (int i = oldPos; i < length; i++) {
			out.append(getValue());
			out.append(" ");
			next();
		}
		out.append(">");
		moveToPos(oldPos); // Reset the fence to its original position
		return out.toString();
	}
}