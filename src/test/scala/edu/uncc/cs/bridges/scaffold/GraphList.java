package edu.uncc.cs.bridges.scaffold;
/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Linked list for graphs: Provides access to curr */
class GraphList extends LList<Edge> {
  public Link<Edge> currLink() { return curr; }
  public void setCurr(Link<Edge> who) { curr = who; }
}
