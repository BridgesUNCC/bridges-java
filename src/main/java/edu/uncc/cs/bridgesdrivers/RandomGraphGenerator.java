
package edu.uncc.cs.bridgesdrivers;

import java.io.IOException;

import edu.uncc.cs.bridges.*;

/**
 * 
 * @author mihai mehedint
 *
 */
public class RandomGraphGenerator { 
	final static int numberOfRecursiveCalls=10; 
	
		public static <T> void main(String[] args) throws IOException {
		outputLog aLog = new outputLog();
			
		// create a new graph
		RandomGraph<Follower> graph = new RandomGraph<Follower>();
		//initiate bridges
		Bridge.init(1, "1157177351793", graph, "mmehedin@uncc.edu");
		//create the root node
		Vertex<Follower> root= new Vertex<Follower>(new Follower("Riley"),graph);
		root.setSize(10);
		root.setColor("red");
		
		//populate the graph with sampleGenerator data
		graph.populate(root,graph,numberOfRecursiveCalls);
		
		Bridge.complete();
		
		aLog.returnStream();
	}

}
