package edu.uncc.cs.bridgesdrivers;

import java.util.List;

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
		
		//retrieve a list of 5 tweets 
		List<Tweet> TweetList = Bridge.getAssociations(new TwitterAccount("earthquake"), 5);
		
		//and convert them to EarthquakeTweets 
		//EarthquakeTweet is a special tweet containing also the magnitude field
		List<EarthquakeTweet> EarthquakeTweetList = Bridge.convertTweet (TweetList);
		
		//add one tweet (the latest tweet) to the queue
		//if there is an identical element in the queue the element will not be added
		aQueue.enQueue(EarthquakeTweetList.get(0));
	
		firstBridge.update();
		
		tree.insert(new BSTNode(EarthquakeTweetList.get(1), 12));
		//System.out.println("Root: " + tree.getRoot().getIdentifier());
		
		tree.insert(new BSTNode(EarthquakeTweetList.get(2), 15));				
		
		secondBridge.complete();
	}

}
