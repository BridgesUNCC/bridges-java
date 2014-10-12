package edu.uncc.cs.bridges;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * The Queue Class extends the GraphVisualizer class and implements methods 
 * specific for queues
 * @author Mihai Mehedint
 *
 * @param <T> this parameter will contain the types: Follower, Movies, Actors
 */
public class Queue<T> extends GraphVisualizer<T>{
	
	private QueueElement<T> rear = null; //save the link to rear element
	private QueueElement<T> front = null; //save the link to front element
	private QueueElement<T> prev = null; //save the link to the previous element
	private String frontColor = "red";//color of the front element
	private String rearColor = "orange";//color of the rear element
	private String defaultColor = "black";//color of the intermediary elements
	private boolean llist = true; //by default the visualization will display the links between the elements
	
	public Map<String, AbstractVertex<T>> vertices = new LinkedHashMap<>();//this overrides the vertices Map in Visualizer so the order of elements is maintained
	
	/**
	 * The constructor
	 */
	public Queue(){
		super();
		super.vertices = this.vertices; //overrides the vertices Map in GraphVisualizer
		super.setVisualizerType("list"); //overrides the type of visualizer to be displayed as a queue
	}
	
	/**
	 * This method removes one element(the rear one) from the queue 
	 * @return Returns the rear element after dequeue
	 */
	public QueueElement<T> deQueue(){
		
		QueueElement<T> temp = rear;
		this.vertices.values().removeAll(Collections.singleton(rear));
		
		if (this.vertices.isEmpty()) 
			front = prev = rear = null;
		else{
			setRear(this.vertices.entrySet().iterator().next().getValue());
		
		if(this.length() == 1)
			front = prev =rear;
		else if (this.length() == 2)
			prev = rear;
		}
		
		return temp;
	}
	
	/**
	 * This method inserts one element into the queue
	 * @param identifier contains the element: Follower, Movies, etc.
	 */
	public QueueElement<T> enQueue(T identifier){
		if (!this.vertices.keySet().contains(identifier)){	 
		int currentNumber = this.vertices.entrySet().size(); 
		
		if(currentNumber == 0){
			front=prev=rear = 
					new QueueElement<>(identifier, this);
			rearColor(rear);
		}
		else if (currentNumber == 1){
			front = new QueueElement<>(identifier, this);
			frontColor(front);
		}
		else {
			prev = front;
			defaultColor(prev);
			front = new QueueElement<>(identifier, this);
			frontColor(front);
			
		}
		
		if (!front.equals(prev) && llist)
			front.queueEdge(prev);
		}
		
		return front;
	}
	
	/**
	 * This method enQueues a queue element
	 * @param an element of the queue
	 */
	public QueueElement<T> enQueue(QueueElement<T> anElement){
		return this.enQueue(anElement.getIdentifier());
	}

	/**
	 * This method saves the reference to the rear element
	 * @param anEntity - this is the rear element
	 */
	private void setRear(AbstractVertex<T> anEntity){
		rearColor(anEntity);
		rear = (QueueElement<T>)anEntity;
		rear.outgoing.clear();
	}
	
	/**
	 * This method sets the color of the front element of the queue
	 * @param aVertex
	 */
	public void frontColor(AbstractVertex<T> aVertex){
		aVertex.setColor(frontColor);
		aVertex.setShape("rect");
	}
	
	/**
	 * This method sets the color for the rear element of the queue
	 * @param this is an AbstractVertex of type T
	 */
	public void rearColor(AbstractVertex<T> aVertex){
		aVertex.setColor(rearColor);
	}
	
	/**
	 * This method sets the default color value to elements of the queue different than 
	 * front or rear 
	 * @param this is an AbstractVertex type T
	 */
	private void defaultColor(AbstractVertex<T> aVertex){
		aVertex.setColor(defaultColor);
	}
	
	/**
	 * This method retrieves the rear of the queue 
	 * @return
	 */
	public AbstractVertex<T> getrear(){
		return rear;
	}
	
	/**
	 * This method retrieves the front of the queue 
	 * @return
	 */
	public AbstractVertex<T> getfront(){
		return front;
	}

	/**
	 * This method empties the queue
	 */
	public void clear(){
		this.vertices.clear();
		front = rear = prev = null;
	}
	
	/**
	 * This method sets the boolean variable llist
	 * to false and no lines will be displayed between the queue elements
	 */
	public void noLListVisualization(){
		llist = false;
	}
	
	/**
	 * This method returns the value of the boolean variable llist  
	 * which is true (by default) when the line connecting the queue elements is visible
	 * @return a boolean value
	 */
	public boolean LList(){
		return llist;
	}
	

	/**
	 * The length method returns the size of the queue
	 * @return
	 */
	public int length(){
		return this.vertices.size();
	}
	/**
	 * This method returns the next element in the queue
	 * @return an AbstractVertex<T> element
	 */
	public QueueElement<T> next(AbstractVertex<T> temp){
		Set aSet= vertices.entrySet();
		Iterator it = aSet.iterator();//.next().getValue();
		while(it.hasNext() && aSet.contains(temp) && !it.next().equals(temp)){
			it.next();
		}
		return (QueueElement<T>)it.next(); 
	}

}//end of the class
