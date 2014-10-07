package edu.uncc.cs.bridgesdrivers;

import edu.uncc.cs.bridges.BSTNode;
import edu.uncc.cs.bridges.Bridge;

public class TreeDiver {

	public static void main(String[] args) throws Exception {

		TreeVisualizer tree = new TreeVisualizer();//Creating tree structure
		
		//Bridge.init(0, "796340034401", tree);//UNCC
		Bridge.init(23, "1157177351793", tree, "mmehedin@uncc.edu");//Heroku
		Bridge.setServerURL("http://bridges-cs.herokuapp.com");
		//Bridge.setServerURL("http://bridges.cs.uncc.edu");
		
		tree.insert(new BSTNode("Root, 12", 12));
		//System.out.println("Root: " + tree.getRoot().getIdentifier());
		
		tree.insert(new BSTNode("Steve, 15", 15));		
		//System.out.println("Right Child: " + tree.getRoot().getRightChild().getIdentifier());		
		
		tree.insert(new BSTNode("D, 18", 18));
		
		tree.insert(new BSTNode("John, 10", 10));
		//System.out.println("Left Child: " + tree.getRoot().getLeftChild().getIdentifier());
		
		tree.insert(new BSTNode("Frank, 11", 11));		
		//System.out.println("Left-Right Child: " + tree.getRoot().getLeftChild().getRightChild().getIdentifier());
		
		tree.insert(new BSTNode("Dave, 8", 8));
		
		tree.insert(new BSTNode("C, 13", 13));
		
		tree.insert(new BSTNode("Chris, 5", 5));
		
		tree.insert(new BSTNode("Ada, 1", 1));

		tree.insert(new BSTNode("Betsy, 7", 7));
		
		tree.insert(new BSTNode("B, 6", 6));
		
		//tree.getRoot().setColor("red");
		
		tree.fMax();
		
		//System.out.println("Is the number found: " + tree.find(7));
		tree.fMin();
		//tree.rMin();
		//System.out.println("Minimum is " + tree.fMin());		
	
		//System.out.println("Maximum is " + tree.fMax());
		//tree.getRoot().getRightEdge().setColor("yellow");
		Bridge.complete();
	}

}
