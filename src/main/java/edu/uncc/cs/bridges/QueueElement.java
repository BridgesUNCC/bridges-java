package edu.uncc.cs.bridges;

import java.util.ArrayList;
/**
 * The QueueElement method extends the AbstractVertex method 
 * @author Mihai Mehedint
 *
 * @param <T>
 */
public class QueueElement<T> extends AbstractVertex{
	
	private int elementSize = 30;
	/**
	 * The constructor
	 * @param identifier contains the identifier object: Follower, Movie, Actor
	 * @param queue is the Queue structure harboring the element
	 */
	public QueueElement(T identifier, Queue queue) {
		super(identifier);
		outgoing = new ArrayList<AbstractEdge<T>>();
		if (queue.LList() == true & queue.vertices.size() != 0){
			this.queueEdge(queue.getTop());
		}
		queue.vertices.put(identifier, this);
		this.setSize(elementSize);
	}
	
	private Edge queueEdge(AbstractVertex<T> v2){
		String ident = this.getIdentifier() +"To"+ v2.getIdentifier();
		return new Edge(this, v2, ident);
		
	}
	


}
