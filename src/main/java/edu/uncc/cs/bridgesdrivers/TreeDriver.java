package edu.uncc.cs.bridgesdrivers;

import edu.uncc.cs.bridges.*;

public class TreeDriver {

	public static void main(String[] args) throws Exception {

		TreeVisualizer<Actor> tree = new TreeVisualizer<>();//Creating tree structure
		
		Bridge.init(23, "300587042698", tree, "mmehedin@uncc.edu");//Heroku
		//Bridge.update(); //this doesn't work, the tree is empty
				
		tree.insert(new BSTNode<>(new Actor("Root, 12"), 12));
		System.out.println("Root: " + tree.getRoot().getIdentifier());
		
		tree.insert(new BSTNode<>(new Actor("Steve, 15"), 15));		
		System.out.println("Right Child: " + tree.getRoot().getRightChild().getIdentifier());		
		
		tree.insert(new BSTNode<>(new Actor("D, 18"), 18));
		
		tree.insert(new BSTNode<>(new Actor("John, 10"), 10));
		//System.out.println("Left Child: " + tree.getRoot().getLeftChild().getIdentifier());
		
		Bridge.update();
		
		tree.insert(new BSTNode<>(new Actor("Frank, 11"), 11));		
		//System.out.println("Left-Right Child: " + tree.getRoot().getLeftChild().getRightChild().getIdentifier());
		
		tree.insert(new BSTNode<>(new Actor("Dave, 8"), 8));
		
		tree.insert(new BSTNode<>(new Actor("C, 13"), 13));
		
		tree.insert(new BSTNode<>(new Actor("Chris, 5"), 5));
		
		tree.insert(new BSTNode<>(new Actor("Ada, 1"), 1));

		tree.insert(new BSTNode<>(new Actor("Betsy, 7"), 7));
		
		tree.insert(new BSTNode<>(new Actor("B, 6"), 6));
		
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
