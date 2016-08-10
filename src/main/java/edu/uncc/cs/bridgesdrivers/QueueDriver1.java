package queueTest;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import edu.uncc.cs.bridges.*;


public class QueueDriver1 {

public static final int maxElements = 10; //number of Followers from Twitter
	
	/**
	 * The populate method enQueues a specified number of Tweeter followers
	 * @param root is the first element of the queue
	 * @param queue the current queue
	 * @param max is the number of followers enqueued by this method
	 * @return
	 */
	public static Queue<EarthquakeTweet> populate(List<EarthquakeTweet> aList, Queue<EarthquakeTweet> queue){
		if (aList.size()!=0){
			Set<Map.Entry<String, AbstractVertex<EarthquakeTweet>>> existingElements=queue.vertices.entrySet();
			for(int i=0; i<aList.size();i++){
				if (!existingElements.contains(aList.get(i))){ 
					queue.enQueue(aList.get(i));					
				}	
			}
		}
		return queue;
	}
	
	
	public static void main(String[] args) throws IOException{
		outputLog aLog = new outputLog();
		//create a queue of Earthquake tweets
		Queue<EarthquakeTweet> eQueue = new Queue<>();
		
		Bridge.init(7, "7855711730", eQueue, "pedram-bashiri");
		
		
		// Any actual user on Twitter; in this case we use the earthquake account
		TwitterAccount name = new TwitterAccount("earthquake"); 
				
		//retrieve a list of 5 tweets 
		List<Tweet> TweetList = Bridge.getAssociations(name, maxElements);
				
		//and convert them to EarthquakeTweets 
		//EarthquakeTweet is a special tweet containing also the magnitude field
		List<EarthquakeTweet> EarthquakeTweetList = Bridge.convertTweet (TweetList);
				
				
		//add one tweet (the latest tweet) to the queue
		//if there is an identical element in the queue the element will not be added
		eQueue.enQueue(EarthquakeTweetList.get(0));
				
		//populate the queue with maxElements tweets from @earthquake
		populate(EarthquakeTweetList, eQueue);
				
	
		
		Bridge.complete();
		aLog.returnStream();

	}
}
