package edu.uncc.cs.bridges.scaffold;
/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

// Edge class for Adjacency List graph representation
class Edge {
  private String vert;
  private int wt;
  private String clr;

  /** Create an edge 
   * @param v  Destination vertex
   * @param w  Edge Weight
   * @param c  Color for visualization
   */
  public Edge(String v, int w, String c) {
	  vert = v; 
	  wt = w;
	  clr = c;
  }

  public String vertex() { return vert; }
  public int weight() { return wt; }
  public String color() { return clr; }
  
}
