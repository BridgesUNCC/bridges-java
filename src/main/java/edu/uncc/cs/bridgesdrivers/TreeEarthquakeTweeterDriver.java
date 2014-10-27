package edu.uncc.cs.bridgesdrivers;

import java.io.IOException;
import java.util.ArrayList;
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
public class TreeEarthquakeTweeterDriver {
	public static final int maxElements = 10; //number of Followers from Twitter
	
	/**
	 * The populate method enQueues a specified number of Tweeter followers
	 * @param root is the first element of the queue
	 * @param queue the current queue
	 * @param max is the number of followers enqueued by this method
	 * @return the queue populated
	 * @throws Exception 
	 */
	public static TreeVisualizer<EarthquakeTweet> populate(List<EarthquakeTweet> aList, TreeVisualizer<EarthquakeTweet> tree) throws Exception{
		if (aList.size()!=0){
			Set<Map.Entry<String, AbstractVertex<EarthquakeTweet>>> existingElements=tree.vertices.entrySet();
			for(int i=0; i<aList.size();i++){
				if (!existingElements.contains(aList.get(i))){
					int aValue = (int)(aList.get(i).getMagnitude()); 
					BSTNode anEarthquake = new BSTNode(aList.get(i), aValue); 
					tree.insert(anEarthquake);
					double size = (aValue-5)*10;//object size range is 0-50; the actual value is adjusted for a better visualization
					anEarthquake.setSize(size);
				}	
			}
		}
		return tree;
	}
	
	
	public static void main(String[] args) throws Exception{
		outputLog aLog = new outputLog();
		TreeVisualizer<EarthquakeTweet> tree = new TreeVisualizer<EarthquakeTweet>();
		Bridge.init(73, "300587042698", tree, "mmehedin@uncc.edu");
		
		// Any actual user on Twitter; in this case we use the earthquake account
		TwitterAccount name = new TwitterAccount("earthquake"); 
		
		//retrieve a list of 5 tweets 
		List<Tweet> TweetList = Bridge.getAssociations(name, maxElements);
		
		//and convert them to EarthquakeTweets 
		//EarthquakeTweet is a special tweet containing also the magnitude field
		List<EarthquakeTweet> EarthquakeTweetList = Bridge.convertTweet (TweetList);
		
		//the the tweets to the tree
		populate(EarthquakeTweetList, tree);
		
		Bridge.update();
		
		//remove minimum
		tree.rMin();
				
		Bridge.update();
		
		//get a second list of earthquakes
		List<Tweet> aSecondTweetList = new ArrayList<>();
		
		Bridge.next(aSecondTweetList, 50);
		
		//add the new batch to the tree
		populate(Bridge.convertTweet (aSecondTweetList), tree);
		
		
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
