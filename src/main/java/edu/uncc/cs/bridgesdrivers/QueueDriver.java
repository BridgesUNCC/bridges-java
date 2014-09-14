package edu.uncc.cs.bridgesdrivers;

import edu.uncc.cs.bridges.*;

public class QueueDriver {

	public static void main(String[] args) {
		Queue<Follower> queue = new Queue<Follower>();
		Bridge.init(6, "1157177351793", queue, "mmehedin@uncc.edu");
		
		//this statement sets the status of the queue to circular
		//queue.circularLList();
		//This works also:
		//AbstractVertex<Follower> entity1= new QueueElement<>(new Follower("entity1"), queue);
		//AbstractVertex<Follower> entity2= new QueueElement<>(new Follower("entity2"), queue);
		
		//Adding elements to the queue
		queue.enQueue(new Follower("entity1"));
		queue.enQueue(new Follower("entity2"));
		
		//the queue is emptied
		queue.clear();
		
		
		queue.enQueue(new Follower("entity2"));
		queue.enQueue(new Follower("entity3"));
		queue.enQueue(new Follower("entity4"));
		
		//after the noLListVisualization statement the elements are not connected visually by lines
		//the user can display the queue with or without lines in between
		//circularLList overrides noLListVisualization
		//queue.noLListVisualization();
		
		queue.enQueue(new Follower("entity5"));
		queue.enQueue(new Follower("entity6"));
		queue.enQueue(new Follower("entity7"));
		queue.enQueue(new Follower("entity8"));
		queue.enQueue(new Follower("entity9"));
		queue.enQueue(new Follower("entity10"));
		//System.out.println(queue.vertices);
		
		//create a circular Queue based on LList
		//queue.circularLList();
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		
		//to set properties to elements
		QueueElement<Follower> entity11 = queue.enQueue(new Follower("entity11"));
		QueueElement<Follower> entity12 = queue.enQueue(new Follower("entity12"));
		//entity12.getQueueEdge(entity11).setColor("purple");
		//entity11.setColor("grey");
		queue.enQueue(new Follower("entity13"));
		queue.enQueue(new Follower("entity13"));
		queue.deQueue();
		queue.deQueue();
		
		
		
		Bridge.complete();

	}

}
