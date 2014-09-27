package edu.uncc.cs.bridges;

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
	private boolean circularLList = false; //this can be set to true in case we want to display the queue as a circular LList
	
	public Map<String, AbstractVertex<T>> vertices = new LinkedHashMap<>();//this overrides the vertices Map in Visualizer so the order of elements is maintained
	
	/**
	 * The constructor
	 */
	public Queue(){
		super();
		super.vertices = this.vertices; //overrides the vertices Map in GraphVisualizer	
	}
	
	/**
	 * This method removes one element(the rear one) from the queue 
	 * @return Returns the rear element after dequeue
	 */
	public QueueElement<T> deQueue(){
		if (!rear.outgoing.isEmpty()) {
			rear.outgoing.clear();
			front.outgoing.clear();
		}
		
		QueueElement<T> temp = rear;
		this.vertices.values().removeAll(Collections.singleton(rear));
		
		if (this.vertices.isEmpty()) 
			rear = null;
		else{
			setRear(this.vertices.entrySet().iterator().next().getValue());
			if (front != null & circularLList) {
				front.outgoing.clear();
				rear.outgoing.clear();
				front.queueEdge(rear);
				front.queueEdge(prev);
			}
		}
		return temp;
	}
	
	/**
	 * This method inserts one element into the queue
	 * @param identifier contains the element: Follower, Movies, etc.
	 */
	public QueueElement<T> enQueue(T identifier){
		int currentNumber = this.vertices.entrySet().size(); 
		if (rear != null & currentNumber != 1) {
			defaultColor(front);
			setFront(new QueueElement<>(identifier, this));
		}
		else if (currentNumber == 1){
			setFront(new QueueElement(identifier, this));		
			rearColor(rear);
		}
		else {
			setRear(prev = front = new QueueElement(identifier, this));
		}
		return front;
	}
	
	/**
	 * This method enQueues a queue element
	 * @param anElement
	 * @return
	 */
	public QueueElement<T> enQueue(QueueElement<T> anElement){
		return this.enQueue(anElement.getIdentifier());
	}
	
	/**
	 * This method sets a new front element for the queue
	 * @param anEntity
	 */
	private void setFront(QueueElement<T> anEntity){
		frontColor(anEntity);
		if (this.LList() & this.vertices.size() != 0 & !circularLList){
			this.changeEdges(anEntity);
		}
		if (front != null & front != rear & rear != null & circularLList){
			this.changeEdges(anEntity);
			this.setCircularLList();
		}
		else
			front = anEntity;
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
	 * This metod sets the color for the rear element of the queue
	 * @param aVertex
	 */
	public void rearColor(AbstractVertex<T> aVertex){
		aVertex.setColor(rearColor);
	}
	
	/**
	 * This method sets the default color value to elements of the queue different than 
	 * front or rear 
	 * @param aVertex
	 */
	private void defaultColor(AbstractVertex<T> aVertex){
		aVertex.setColor(defaultColor);
	}
	
	/**
	 * This method rearranges the links (or edges) after one element is inserted  
	 * @param anEntity
	 */
	private void changeEdges(QueueElement<T> anEntity){
		front.outgoing.clear();
		rear.outgoing.clear();
		front.queueEdge(prev);
		prev = front;
		front = anEntity;
		front.queueEdge(prev);
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
	protected void noLListVisualization(){
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
	 * This method sets the status of the queue to circular
	 * @return
	 */
	protected boolean circularLList(){
		//setCircularLList();
		return circularLList = true;	
	}
	
	/**
	 * This method closes the loop between the rear and the front of the queue
	 * in a circular list
	 */
	public void setCircularLList(){
		front.queueEdge(rear);
	}
	
	/**
	 * The length method returns the size of the queue
	 * @return
	 */
	public int length(){
		return this.vertices.size();
	} 

}//end of the class
