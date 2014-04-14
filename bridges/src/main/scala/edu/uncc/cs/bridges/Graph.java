package edu.uncc.cs.bridges;

/** Source code example for "A Practical Introduction to Data
Structures and Algorithm Analysis, 3rd Edition (Java)" 
by Clifford A. Shaffer
Copyright 2008-2011 by Clifford A. Shaffer
*/
public interface Graph {         // Graph class ADT

/** @return v's first neighbor */
public String first(String v);

/** @return v's next neighbor */
public String next(String v);

public boolean add(String v);

/** Set the weight for an edge
  @param i,j The vertices
  @param wght Edge weight */
public void setEdge(String i, String j, int wght);

/** Set the mark value for a vertex
  @param v The vertex
  @param val The value to set */
public void setMark(String v, int val);

/** Get the mark value for a vertex
  @param v The vertex
  @return The value of the mark */
public int getMark(String v);

public void DFS(String string);
}
