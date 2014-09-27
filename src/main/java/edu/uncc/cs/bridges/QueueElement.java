package edu.uncc.cs.bridges;

import java.util.ArrayList;
/**
 * The QueueElement method extends the AbstractVertex method 
 * @author Mihai Mehedint
 *
 * @param <T>
 */
public class QueueElement<T> extends AbstractVertex<T>{
	
	private int elementSize = 30;
	private int id;
	
	
	/**
	 * The constructor
	 * @param identifier contains the identifier object: Follower, Movie, Actor
	 * @param queue is the Queue structure harboring the element
	 */
	protected QueueElement(T identifier, Queue queue) {
		super(identifier);
		outgoing = new ArrayList<AbstractEdge<T>>();
		//queue.enQueue(identifier);
		queue.vertices.put(identifier, this);
		if(queue.vertices.size()==1)
			queue.frontColor(this);
		else if (queue.vertices.size()==2)
			queue.rearColor(this);
		else {
			queue.rearColor(this);
		}
		this.setSize(elementSize);
	}
	
	/**
	 * This method sets a (visual) connection object between 2 queue elements
	 * @param v2
	 * @return
	 */
	boolean queueEdge(QueueElement<T> v2){
		String ident = this.getIdentifier() +"To"+ v2.getIdentifier();
		return outgoing.add(new QueueEdge(this, v2, ident));	
	}
	
	/**
	 * This method retrieves the line object connecting 2 elements of the queue
	 * element1.getQueueEdge(element2);
	 * @param anElement
	 * @return
	 */
	protected QueueEdge<T> getQueueEdge(QueueElement<T> anElement){
		for(int i = 0; i < this.outgoing.size(); i++){ 
			AbstractEdge<T> anEdge = (QueueEdge<T>)this.outgoing.get(i);
				if(anEdge.destination.compareTo((AbstractVertex<T>)anElement)==0){				
					return (QueueEdge<T>)this.outgoing.get(i);
			}
		}		
		return null;	
	}
	
	/**
	 * This method compares two elements
	 * @param o
	 * @return
	 */
	public int compareTo(QueueElement<T> o) {
		if (o != null) {
			return Integer.compare(id, o.id);
		}
		return 0;
	}
	
	/**
	 * This method returns the next element in the queue
	 * @return
	 */
	public QueueElement<T> next(){
		QueueEdge<T> anEdge = (QueueEdge<T>)this.outgoing.iterator().next(); 
		return (QueueElement<T>)anEdge.destination;
	}

}
