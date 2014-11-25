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
/**
 * 
 * @author mihai mehedint
 *
 */
public class CCiCareers {
	public static final int maxElements = 40; //number of tweets
	
	/**
	 * The populate method enQueues a specified number of Tweeter followers
	 * @param root is the first element of the queue
	 * @param queue the current queue
	 * @param max is the number of followers enqueued by this method
	 * @return
	 * @throws BridgesException 
	 */
	public static Queue<Tweet> populate(List<Tweet> aList, Queue<Tweet> queue){
		//queue.clear();
		if (aList.size()!=0){
			Set<Map.Entry<String, AbstractVertex<Tweet>>> existingElements=queue.vertices.entrySet();
			for(int i=0; i<aList.size();i++){
				if (!existingElements.contains(aList.get(i))){ 
					queue.enQueue(aList.get(i));
					//double size = (queue.getfront().getIdentifier().getMagnitude()-5)*10;//object size range is 0-50; the actual value is adjusted for a better visualization
					//queue.getfront().setSize(size);
				}	
			}
		}
		return queue;
	}
	
	public static void main(String[] args) throws IOException {
		//create a log for all outputs with or without errors in user's home directory
		outputLog aLog = new outputLog();
		
		//create a queue of Earthquake tweets
		Queue<Tweet> aQueue = new Queue<>();
		
		// Assignment, API key, visualizer, username
		Bridge.init(17, "300587042698", aQueue, "mmehedin@uncc.edu");

		// Any actual user on Twitter; in this case we use the earthquake account
		TwitterAccount name = new TwitterAccount("CCiCareer"); //the CCiCareer lets max 6 tweets, CCiCareers works with 30
		
		
		//retrieve a list of maxElements number of tweets 
		List<Tweet> CareersTweetList = Bridge.getAssociations(name, maxElements);
		
		//and convert them to EarthquakeTweets 
		//EarthquakeTweet is a special tweet containing also the magnitude field
		//List<Tweet> CareersTweetList = Bridge.convertTweet (TweetList);
		
		//add one tweet (the latest tweet) to the queue
		//if there is an identical element in the queue the element will not be added
		//aQueue.enQueue(CareersTweetList.get(0));
		
		//populate the queue with maxElements tweets from @earthquake
		populate(CareersTweetList, aQueue);
		
		//dequeue one element
		//aQueue.deQueue();
		
		//get a snapshot of the queue
		//Bridge.update();
		
		//get another batch of 3 tweets to the queue
		//List<Tweet> aSecondTweetList = new ArrayList<>();
		
		//Bridge.next(aSecondTweetList, 50);
		
		//add the new batch to the queue
		//populate(aSecondTweetList, aQueue);
		
		//dequeue another tweet
		//aQueue.deQueue();
		
		//enQueue another tweet
		//aQueue.enQueue(CareersTweetList.get(1));
		System.out.println(Bridge.getJSON());
		
		//System.out.println(name.getNeighbors());
		Bridge.complete();
		
		aLog.returnStream();
	}

}
