package edu.uncc.cs.bridgesdrivers;

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
public class QueueDriver {
	public static final int maxElements = 10; //number of Followers from Twitter
	
	/**
	 * The populate method enQueues a specified number of Tweeter followers
	 * @param root is the first element of the queue
	 * @param queue the current queue
	 * @param max is the number of followers enqueued by this method
	 * @return
	 */
	public static Queue<Follower> populate(QueueElement<Follower> root, Queue<Follower> queue, int max){
		if (max!=0){
			List<Follower>	temp= Bridge.getAssociations((Follower)root.getIdentifier(), max);
			Set<Map.Entry<String, AbstractVertex<Follower>>> existingElements=queue.vertices.entrySet();
			for(int i=0; i<temp.size();i++){
				if (!existingElements.contains(temp.get(i))){ 
					queue.enQueue(temp.get(i));
				}	
			}
		}
		return queue;
	}
	
	
	public static void main(String[] args) {
		Queue<Follower> queue = new Queue<Follower>();
		Bridge.init(6, "1157177351793", queue, "mmehedin@uncc.edu");
		
		//Adding elements to the queue
		QueueElement<Follower> root = queue.enQueue(new Follower("Joey"));
		queue.enQueue(new Follower("Jane"));
		
		//the queue is emptied
		queue.clear();
				
		queue.enQueue(new Follower("Bob"));//the first element of the queue
		populate(queue.enQueue(new Follower("Riley")), queue, maxElements);
		
		//This is how you can access the existing elements of the queue, just remove the comments from the 
		//statement below
		System.out.println("The queue elements: "+queue.vertices);
		
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		queue.enQueue(new Follower("John"));
		//One can iterate through the elements of the queue using next();
		//this method is inherited from graph
		//to test this remove the comment sign from the statement below
		//System.out.println(((QueueElement<Follower>)queue.getfront()).next().next().getIdentifier());
		
		//One element's properties can be changed
		//to test this remove the comment sign from the statement below
		//queue.getfront().setColor("purple");
		
		//This is another deQueue statement
		queue.deQueue();
		//queue.deQueue();
		
		//One can find the size of the queue at any given moment
		//to test this remove the quotes
		//System.out.println(queue.length());
		
		System.out.print("\nJSON: ");
		Bridge.complete();

	}

}
