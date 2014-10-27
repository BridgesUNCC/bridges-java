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
public class QueueDriverJavaScript {
	public static final int maxElements = 100; //number of Followers from Twitter
	
	/**
	 * The populate method enQueues a specified number of Tweeter followers
	 * @param root is the first element of the queue
	 * @param queue the current queue
	 * @param max is the number of followers enqueued by this method
	 * @return the queue populated
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
	
	
	public static void main(String[] args) throws IOException{
		outputLog aLog = new outputLog();
		Queue<Follower> queue = new Queue<Follower>();
		Bridge.init(7, "300587042698", queue, "mmehedin@uncc.edu");
		
		
		//Adding elements to the queue
		//QueueElement<Follower> root = queue.enQueue(new Follower("Bob"));
		//queue.enQueue(new Follower("Joey"));
		//Bridge.update();
		//queue elements with the same identity will not be enqueued
		//queue.enQueue(new Follower("Joey"));
		//queue.deQueue();
		
		//take a first snapshot of the queue at this stage
		//Bridge.update();
		
		//the queue is emptied
		//queue.clear();
				
		//queue.enQueue(new Follower("Bob"));//the first element of the queue
		
		//this is how you can add many elements to the queue
		populate(queue.enQueue(new Follower("CCICareers")), queue, maxElements);
		
		//To access/view the existing elements of the queue remove the comments from the 
		//statement below
		System.out.println("\nThe queue elements are: "+queue.vertices + "\n");
		
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		
		//queue.enQueue(new Follower("John</script><script>location.reload ();</script>"));
		//queue.enQueue(new Follower("John</script><script>function autoRefresh1(){window.location.reload();}setInterval('autoRefresh1()', 000);</script>"));
		/**queue.enQueue(new Follower("John</script><script language='javascript'>  "
				+ "var counter = 0;  "
				+ "window.setInterval('refreshDiv()', 5000);" 
				+"function refreshDiv(){  "
				+ "counter = counter + 1;  "
				+ "document.getElementById('assignmentCanvas').innerHTML = 'Testing ' + counter;"
				+ "}</script>"));
		*/
		System.out.println("\nJSON: "+Bridge.getJSON()+"\n");
		//One can iterate through the elements of the queue using next();
		//this method is inherited from graph
		//to test this remove the comment sign from the statement below
		//System.out.println(((QueueElement<Follower>)queue.getfront()).next().next().getIdentifier());
		
		//One element's properties can be changed
		//to test this remove the comment sign from the statement below
		queue.getfront().setColor("purple");
		
		//get another snapshot of the queue
		Bridge.update();
		
		//This is another deQueue statement
		queue.deQueue();
		queue.deQueue();
		
		//One can find the size of the queue at any given moment
		//to test this remove the quotes
		System.out.println("\nThe size of the queue is: " + queue.length() + "\n");
		
		//This is how one can see the JSON content
		System.out.println("\nThe JSON is: "+ Bridge.getJSON() + "\n");
		
		Bridge.complete();
		aLog.returnStream();
	}

}
