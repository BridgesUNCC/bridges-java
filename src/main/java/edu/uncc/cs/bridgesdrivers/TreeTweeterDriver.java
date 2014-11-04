package edu.uncc.cs.bridgesdrivers;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import edu.uncc.cs.bridges.*;
/**
 * 
 * @author Mihai Mehedint
 *
 */
public class TreeTweeterDriver {
	public static final int maxElements = 50; //number of Followers from Twitter
	
	/**
	 * The populate method enQueues a specified number of Tweeter followers
	 * @param root is the first element of the queue
	 * @param queue the current queue
	 * @param max is the number of followers enqueued by this method
	 * @return the queue populated
	 * @throws Exception 
	 */
	public static TreeVisualizer<Follower> populate(BSTNode<Follower> root, TreeVisualizer<Follower> tree, int max) throws Exception{
		if (max!=0){
			List<Follower>	temp= Bridge.getAssociations((Follower)root.getIdentifier(), max);
			Set<Map.Entry<String, AbstractVertex<Follower>>> existingElements=tree.vertices.entrySet();
			for(int i=0; i<temp.size();i++){
				if (!existingElements.contains(temp.get(i))){
					int nodeValue = Math.abs(temp.get(i).hashCode()) % 50;
					BSTNode<Follower> aNode = new BSTNode<Follower>(new Follower(temp.get(i)+", "+ nodeValue), nodeValue);
					//System.out.println("temp.get(i): "+ temp.get(i) + " the hashcode: "+temp.get(i).hashCode()+ " the mod: " + temp.get(i).hashCode() % 50);
					tree.insert(aNode);
					aNode.setSize(aNode.getVal());
				}	
			}
		}
		return tree;
	}
	
	
	public static void main(String[] args) throws Exception{
		outputLog aLog = new outputLog();
		TreeVisualizer<Follower> tree = new TreeVisualizer<Follower>();
		Bridge.init(7, "300587042698", tree, "mmehedin@uncc.edu");
		
		
		//Adding elements to the queue
		BSTNode<Follower> root = tree.insert(new BSTNode<Follower>(new Follower("Bob, 12"), 12));
		tree.insert(new BSTNode<Follower>(new Follower("Joey, 2"), 2));
		Bridge.update();
		
		//tree.removeN(root);
		
		//take a first snapshot of the queue at this stage
		Bridge.update();
				
		tree.insert(new BSTNode("Steve, 15", 15));		
		System.out.println("Right Child: " + tree.getRoot().getRightChild().getIdentifier());		
		
		tree.insert(new BSTNode("D, 18", 18));
		
		tree.insert(new BSTNode("John, 10", 10));
		//System.out.println("Left Child: " + tree.getRoot().getLeftChild().getIdentifier());
		
		Bridge.update();
		
		tree.insert(new BSTNode("Frank, 11", 11));		
		
		tree.insert(new BSTNode("Dave, 8", 8));
		
		tree.insert(new BSTNode("C, 13", 13));
		
		tree.insert(new BSTNode("Chris, 5", 5));
		
		tree.insert(new BSTNode("Ada, 1", 1));

		tree.insert(new BSTNode("Betsy, 7", 7));
		
		tree.insert(new BSTNode("B, 6", 6));
		
		tree.getRoot().setColor("red").setSize(50);
		
		//find and trace the route to max
		tree.fMax();
		
		//System.out.println("Is the number found: " + tree.find(7));
		//tree.fMin();
		
		Bridge.update();
		
		//add many elements to the tree using Twitter followers starting with the account Achusimjennifer
		int aStartingAccountValue = "Achusimjennifer".hashCode()%50;// this generates the hashcode value of the string "Achusimjennifer"
		Follower aStartingAccount = new Follower("Achusimjennifer, "+ aStartingAccountValue);
		populate(tree.insert(new BSTNode<Follower>(aStartingAccount, aStartingAccountValue)), tree, maxElements);
		
		//remove node method
		//remove root, this will cause errors
		//tree.removeN(tree.getRoot());
		
		Bridge.update();
		
		//remove minimum
		tree.rMin();
		
		//get another snapshot of the tree
		Bridge.update();
		
		//This is another removeN statement
		//tree.removeN(tree.getRoot());
		//tree.removeN(tree.getRoot());
		
		//find and trace the route to max using the yellow color
		tree.fMax();
				
		//System.out.println("Is the number found: " + tree.find(7));
		//find the min value in the tree
		tree.fMin();
		
		//Bridge.update();
		
		//To see the JSON content
		System.out.println("\nThe JSON is: "+ Bridge.getJSON() + "\n");		
		
		Bridge.complete();
		
		aLog.returnStream();
	}

}
