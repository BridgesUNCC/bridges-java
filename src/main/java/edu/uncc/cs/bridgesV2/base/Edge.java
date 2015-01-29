package edu.uncc.cs.bridges_v2.base;
import java.util.Map.Entry;


/**
 * Edge class
 * @author krs
 *
 * @param <E>
 */
public class Edge{
	
	private int weight;
	private String vertex;			// refers to a terminating vertex
	
	/**
	 * Edge constructors - used only for graphs
	 */
	public Edge(){
		super();
		this.weight = 0;
		vertex = null;
	}

	public  Edge(int wt){
		super();
		this.weight = wt;
		vertex = null;
	}
	public Edge(int wt, String v){
		super();
		this.weight = wt;
		vertex = v;
	}

	/**
	 * set edge weight
	 * @param wt 
	 */
	public void setWeight (int wt){
		this.weight = wt;
	}
	/**
	 * get edge weight
	 * @param 
	 */
	public int getWeight (){
		return this.weight;
	}
	/**
	 * set terminating vertex of edge
	 * @param vertex v 
	 */
	public void setVertex (String v){
		this.vertex = v;
	}
	/**
	 * get terminating vertex of edge
	 * @param 
	 */
	public String  getVertex (){
		return this.vertex;
	}

	/**
	 * set edge
	 * @param weight
	 * @param vertex
	 */
	public void setEdge(int wt, String v) {
		this.weight = wt;
		this.vertex = v;
	}	
	/**
	 * get edge
	 */
	public Edge  getEdge() {
		return this;
	}
};
