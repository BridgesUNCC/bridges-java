
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
	final static int numberOfRecursiveCalls=10; 
	
		public static <T> void main(String[] args) {
		// create a new graph
		RandomGraph<Follower> graph = new RandomGraph<Follower>();
		//initiate bridges
		Bridge.init(1, "693144430396", graph, "mmehedin@uncc.edu");
		//create the root node
		Vertex<Follower> root= new Vertex<Follower>(new Follower("Riley"),graph);
		root.setSize(10);
		root.setColor("red");
		
		//populate the graph with sampleGenerator data
		graph.populate(root,graph,numberOfRecursiveCalls);
		
		Bridge.complete();
	}

}
