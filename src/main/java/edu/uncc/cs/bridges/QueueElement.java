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
	private int id;
	
	/**
	 * The constructor
	 * @param identifier contains the identifier object: Follower, Movie, Actor
	 * @param queue is the Queue structure harboring the element
	 */
	public QueueElement(T identifier, Queue queue) {
		super(identifier);
		outgoing = new ArrayList<AbstractEdge<T>>();
		queue.vertices.put(identifier, this);
		this.setSize(elementSize);
		
	}
	
	boolean queueEdge(AbstractVertex<T> v2){
		String ident = this.getIdentifier() +"To"+ v2.getIdentifier();
		return outgoing.add(new Edge(this, v2, ident));	
	}
	
	Object getQueueEdge(QueueElement<T> anElement){
		for(int i = 0; i < this.outgoing.size(); i++){ 
			AbstractEdge<T> anEdge = (Edge)this.outgoing.get(i);
				if(anEdge.destination.compareTo((AbstractVertex<T>)anElement)==0){				
					return this.outgoing.get(i);
			}
		}		
		return null;
		
	}
	
	public int compareTo(QueueElement<T> o) {
		
		if (o != null) {
			return Integer.compare(id, o.id);
		}
		return 0;
	}

}
