package edu.uncc.cs.bridges;

/** Source code example for "A Practical Introduction to Data
Structures and Algorithm Analysis, 3rd Edition (Java)" 
by Clifford A. Shaffer
Copyright 2008-2011 by Clifford A. Shaffer
 */
/** Graph ADT */
public interface Graph {         // Graph class ADT

	/// Manage Vertices
	/** Add a vertex
	 * 
	 * @param v Name of the new vertex
	 * @return Whether the vertex was successfully added.
	 */
	public boolean add(String v);

	/** Clear all the marks for all vertices */
	public void clearMarks();
	
	/** Set the mark value for a vertex
      @param v The vertex
      @param val The value to set */
	public void setMark(String v, int val);

	/** Get the mark value for a vertex
      @param v The vertex
      @return The value of the mark */
	public int getMark(String v);

	/** @return the color of the node for visualization */
	public String getNodeColor(String v);

	/** Set the color of a node in visualization */
	public void setNodeColor(String v, String color);

	
	/// Traverse Edges
	/** Traverse the neighbors of a vertex
	 *  @return v's first neighbor */
	public String first(String v);

	/** Traverse the neighbors of a vertex
	 * @return v's next neighbor */
	public String next(String v);

	
	/// Manage Edges
	/** Get the weight for an edge
	 * 
  	 * @param i,j The vertices
     * @param wght Edge weight */
	public int getEdge(String i, String j);

	/** Set the weight for an edge
	 * @param i,j The vertices
	 * @param wght Edge weight */
	public void setEdge(String i, String j, int wght);

	/** Set the edge's color
	 * @returns whether the edge was succesfully set*/
	public boolean setEdgeColor(String i, String j, String color);

	/** Get the edge's color 
	 * @returns the edge's color */
	public String getEdgeColor(String i, String j);
	
	// What exactly does this do again?
	public void DFS(String string);
}