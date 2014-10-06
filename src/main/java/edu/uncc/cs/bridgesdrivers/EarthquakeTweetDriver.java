package edu.uncc.cs.bridgesdrivers;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.uncc.cs.bridges.*;

public class EarthquakeTweetDriver {
	public static final int maxElements = 10; //number of tweets
	
	/**
	 * The populate method enQueues a specified number of Tweeter followers
	 * @param root is the first element of the queue
	 * @param queue the current queue
	 * @param max is the number of followers enqueued by this method
	 * @return
	 */
	public static Queue<EarthquakeTweet> populate(List<EarthquakeTweet> aList, Queue<EarthquakeTweet> queue){
		//queue.clear();
		if (aList.size()!=0){
			Set<Map.Entry<String, AbstractVertex<EarthquakeTweet>>> existingElements=queue.vertices.entrySet();
			for(int i=0; i<aList.size();i++){
				if (!existingElements.contains(aList.get(i))){ 
					queue.enQueue(aList.get(i));
					double size = (queue.getfront().getIdentifier().getMagnitude()-5)*10;//object size range is 0-50; the actual value is adjusted for a better visualization
					queue.getfront().setSize(size);
				}	
			}
		}
		return queue;
	}
	
	public static void main(String[] args) throws IOException {
		//create a log for all outputs with or without errors in user's home directory
		outputLog aLog = new outputLog();
		
		//create a queue of Earthquake tweets
		Queue<EarthquakeTweet> aQueue = new Queue<>();
		
		// Assignment, API key, visualizer, username
		Bridge.init(0, "1157177351793", aQueue, "mmehedin@uncc.edu");

		// Any actual user on Twitter; in this case we use the earthquake account
		TwitterAccount name = new TwitterAccount("earthquake"); 
		
		//retrieve a list of 5 tweets 
		List<Tweet> TweetList = Bridge.getAssociations(name, maxElements);
		
		//and convert them to EarthquakeTweets 
		//EarthquakeTweet is a special tweet containing also the magnitude field
		List<EarthquakeTweet> EarthquakeTweetList = Bridge.convertTweet (TweetList);
		
		//add one tweet (the latest tweet) to the queue
		//if there is an identical element in the queue the element will not be added
		aQueue.enQueue(EarthquakeTweetList.get(0));
		
		//populate the queue with maxElements tweets from @earthquake
		populate(EarthquakeTweetList, aQueue);
		
		//dequeue one element
		aQueue.deQueue();
		
		//get another batch of 3 tweets to the queue
		List<Tweet> aSecondTweetList = new ArrayList<>();
		
		Bridge.next(aSecondTweetList, -2);
		
		//add the new batch to the queue
		populate(Bridge.convertTweet (aSecondTweetList), aQueue);
		
		//dequeue another tweet
		aQueue.deQueue();
		
		//enQueue another tweet
		aQueue.enQueue(EarthquakeTweetList.get(1));
		
		//System.out.println(name.getNeighbors());
		Bridge.complete();
	aLog.returnStream();
	}

}
