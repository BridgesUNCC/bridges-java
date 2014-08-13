package edu.uncc.cs.bridgesdrivers;

/**
 * The minHeap class uses a minHeap to select the edge with the smallest cost emerging
 * from a given vertex. This can be used as part of more complex algorithms like Dijakstra 
 * to select the shortest path.
 * @Title minHeap and minHeap sort of edges based on their cost 
 * @author Mihai Mehedint
 * @date August 2014
 *  
 */
		
import java.util.ArrayList;
import java.util.Iterator;

import edu.uncc.cs.bridges.*;

public class minHeapDriver extends minHeapEdges {

	/**
	 * the main driver, 
	 * populates a random graph and creates a minHeap using the edge costs that connect root
	 * to its children
	 * @param args
	 */
	public static  void main(String[] args) {
		graph = new RandomGraph<>();
		//initiate bridges
		Bridge.init(1, "1157177351793", graph, "mmehedin@uncc.edu");
		root= new Vertex<>(new Follower("Riley"),graph);
		root.setSize(10);
		root.setColor("red");
		
		graph.setNumberOfChildren(4);
		//populate the graph with sampleGenerator data
		graph.populate(root,graph,numberOfRecursiveCalls);		
		//System.out.println("This is the edge: "+root.getEdge(root.next()).getIdentifier()+" "+root.getEdge(root.next()).getWeight());		
		
		createHeap(minHeap, root);
		
		System.out.println("Created a minHeap using the edges emerging from root: ");
		print(minHeap);
		System.out.println("\nThis is a minHeap: " + isHeap(minHeap,1));
		System.out.println();
		
		//Performing the heap sort using the minimum heap 
		heapSort(minHeap,minHeap.size()-1);
		
		//printing the sorted edge values in descending order
		//System.out.println("Sorted: "+minHeapSorted);
		System.out.println("Sorted minHeap: \n");
		print(minHeapSorted);
		
		//remove minHeap
		//minHeapSorted.add(0, null);
		//System.out.println("Sorted: "+minHeapSorted);
		//removeHeap(minHeapSorted,minHeapSorted.size()-1);
		
		minHeapSorted.get(0).setColor("red").setOpacity(1);
		Bridge.complete();
	}

}
