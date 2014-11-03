package edu.uncc.cs.bridgesdrivers;

import edu.uncc.cs.bridges.*;

public class TreeAndQueueDriver {

	public static void main(String[] args) throws Exception {

		TreeVisualizer<EarthquakeTweet> tree = new TreeVisualizer<>();//Creating tree structure
		Queue<EarthquakeTweet> aQueue = new Queue<>();
		//Bridge.init(0, "796340034401", tree);//UNCC
		Bridge firstBridge = new Bridge();
		Bridge secondBridge = new Bridge();
		firstBridge.init(23, "300587042698", tree, "mmehedin@uncc.edu");//Heroku
		secondBridge.init(24, "300587042698", aQueue, "mmehedin@uncc.edu");//Heroku
		aQueue.enQueue(new Tweet("Jack"));
		firstBridge.update();
		//Bridge.setServerURL("http://bridges-cs.herokuapp.com");
		//Bridge.setServerURL("http://bridges.cs.uncc.edu");
		
		tree.insert(new BSTNode("Root, 12", 12));
		System.out.println("Root: " + tree.getRoot().getIdentifier());
		
		tree.insert(new BSTNode("Steve, 15", 15));		
		System.out.println("Right Child: " + tree.getRoot().getRightChild().getIdentifier());		
		
		tree.insert(new BSTNode("D, 18", 18));
		
		tree.insert(new BSTNode("John, 10", 10));
		//System.out.println("Left Child: " + tree.getRoot().getLeftChild().getIdentifier());
		
		Bridge.update();
		
		tree.insert(new BSTNode("Frank, 11", 11));		
		//System.out.println("Left-Right Child: " + tree.getRoot().getLeftChild().getRightChild().getIdentifier());
		
		tree.insert(new BSTNode("Dave, 8", 8));
		
		tree.insert(new BSTNode("C, 13", 13));
		
		tree.insert(new BSTNode("Chris, 5", 5));
		
		tree.insert(new BSTNode("Ada, 1", 1));

		tree.insert(new BSTNode("Betsy, 7", 7));
		
		tree.insert(new BSTNode("B, 6", 6));
		
		tree.getRoot().setColor("red").setSize(50);
		
		tree.fMax();
		
		//System.out.println("Is the number found: " + tree.find(7));
		tree.fMin();
		//tree.rMin();
		//System.out.println("Minimum is " + tree.fMin());		
		System.out.println(Bridge.getJSON());
		//System.out.println("Maximum is " + tree.fMax());
		//tree.getRoot().getRightEdge().setColor("yellow");
		Bridge.complete();
	}

}
