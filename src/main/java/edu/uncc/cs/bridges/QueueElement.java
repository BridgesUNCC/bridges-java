package edu.uncc.cs.bridges;

import java.util.ArrayList;
/**
 * The QueueElement method extends the AbstractVertex method 
 * @author Mihai Mehedint
 *
 * @param <T>
 */
public class QueueElement<T> extends AbstractVertex{
	/**
	 * The constructor
	 * @param identifier contains the identifier object: Follower, Movie, Actor
	 * @param queue is the Queue structure harboring the element
	 */
	public QueueElement(T identifier, Queue queue) {
		super(identifier);
		outgoing = new ArrayList<AbstractEdge<T>>();
		queue.vertices.put(identifier, this);
		this.setSize(20);
	}
	
	private void queueEdge(){
		
	}
	


}
