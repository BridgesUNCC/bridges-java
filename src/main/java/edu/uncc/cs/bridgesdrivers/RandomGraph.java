package edu.uncc.cs.bridgesdrivers;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import edu.uncc.cs.bridges.*;

public class RandomGraph<T> extends GraphVisualizer<T> {
	protected GraphVisualizer<T> graph;
	protected  Vertex<T> root;
	static int numberOfChildren=30; //number of vertices for a parent node 
	static int numberOfRecursiveCalls=10; 
	
	
	/**
	 * Constructor
	 * @param root holds the root node
	 */
	public RandomGraph(){
		graph = new GraphVisualizer<T>();
	}
	/**
	 * The randomVertex method selects a random Vertex from an existing graph
	 * @param graph - references an already populated graph
	 * @return
	 */
	public Vertex<Follower> randomVertex(GraphVisualizer<Follower> graph){
		if (graph==null || graph.vertices.size()==0) 
			throw new IllegalArgumentException
			("No vertices were added to graph or graph was not initialized!");
		Collection<AbstractVertex<Follower>> vertices= graph.vertices.values();
		Object[] i= vertices.toArray();

		return (Vertex<Follower>)i[new Random().nextInt(vertices.size())];
	}
	/**
	 * The randomColor method selects a random color from the available
	 * list of colors found in Validation.java
	 * @return a color name as a string value
	 */
	public static String randomColor(){
		Object [] a=Validation.COLOR_NAMES.toArray();
		return a[new Random().nextInt(a.length)].toString();
	}
	/**
	 * This method populates the graph
	 * @param root this holds the current parent root value while generating children nodes
	 * @param graph holds the current graph reference
	 * @param max is the number of recursive calls made
	 * @return Returns a populated graph
	 */
	public GraphVisualizer<Follower> populate(Vertex<Follower> root, GraphVisualizer<Follower> graph, int max){
		
		if (max!=0){
			Vertex<Follower> aVertex=root;
			AbstractEdge<Follower> anEdge;
			String aColor =randomColor();
			//System.out.println(max);
			
			List<Follower>	temp= Bridge.getAssociations((Follower)root.getIdentifier(), numberOfChildren);
			Set<Map.Entry<String, AbstractVertex<Follower>>> existingVertices=graph.vertices.entrySet();
			for(int i=0; i<temp.size();i++){
				if (!existingVertices.contains(temp.get(i))){ 
					aVertex=new Vertex<Follower>(temp.get(i), graph).setColor(aColor).setOpacity(0.2);
					root.createEdge(aVertex, "randWeight").setOpacity(0.2);
				}
				else{
					aVertex=(Vertex<Follower>)graph.vertices.get(temp.get(i));
					root.createEdge(aVertex).setColor(aColor).setOpacity(0.2);
				}	
			}
			populate(randomVertex(graph),graph,--max);
			
			//randomly connect vertices to increase complexity
			
			for (int i=1; i<10;i++){
				randomVertex(graph).createEdge(randomVertex(graph));
			}
			
		}
		return graph;
	}
	/**
	 * Sets the number of recursive calls made
	 * @param numberOfRecursiveCalls
	 * @return the current number of calls
	 */
	public int setNumberOfCalls(int numberOfRecursiveCalls){
		return this.numberOfRecursiveCalls=numberOfRecursiveCalls;
	}
	
	/**
	 * Sets the number of children a node has
	 * @param numberOfChildren
	 * @return the number of children
	 */
	public int setNumberOfChildren(int numberOfChildren){
		return this.numberOfChildren=numberOfChildren;
	}
}
