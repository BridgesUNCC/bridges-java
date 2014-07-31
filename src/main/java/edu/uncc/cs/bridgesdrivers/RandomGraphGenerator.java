
package edu.uncc.cs.bridgesdrivers;
import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import edu.uncc.cs.bridges.*;


public class RandomGraphGenerator {
	final static int numberOfChildren=30; //number of vertices for a parent node 
	final static int numberOfRecursiveCalls=10; 
	
	/**
	 * The randomVertex method selects a random Vertex from an existing graph
	 * @param graph - references an already populated graph
	 * @return
	 */
	public static Vertex randomVertex(GraphVisualizer graph){
		if (graph==null || graph.vertices.size()==0) 
			throw new IllegalArgumentException
			("No vertices were added to graph or graph was not initialized!");
		
		Collection vertices= graph.vertices.values();
		int a=new Random().nextInt(vertices.size());
		Object[] i= vertices.toArray();

		return (Vertex)i[new Random().nextInt(vertices.size())];
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
	public static GraphVisualizer populate(Vertex root, GraphVisualizer graph, int max){
		
		if (max!=0){
			Vertex aVertex=root;
			String aColor =randomColor();
			//System.out.println(max);
			List<String> temp= Bridge.getAssociations(root.getIdentifier(), numberOfChildren);
			Set<Map.Entry<String, AbstractVertex>> existingVertices=graph.vertices.entrySet();
			for(int i=0; i<temp.size();i++){
				if (!existingVertices.contains(temp.get(i))){ 
					aVertex=new Vertex(temp.get(i),graph).setColor(aColor);
					root.createEdge(aVertex, "randWeight");
				}
				else{
					aVertex=(Vertex)graph.vertices.get(temp.get(i));
					root.createEdge(aVertex).setColor(aColor);
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
	
	public static void main(String[] args) {
		// create a new graph
		GraphVisualizer graph = new GraphVisualizer();
		//initiate bridges
		Bridge.init(1, "693144430396", graph, "mmehedin@uncc.edu");
		//create the root node
		Vertex root= new Vertex("twitter.com/Riley",graph);
		root.setSize(10);
		root.setColor("red");
		
		//populate the graph with sampleGenerator data
		populate(root,graph,numberOfRecursiveCalls);
		
		Bridge.complete();
	}

}
