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
	
	private QueueElement<T> bottom = null; //save the link to bottom element
	private QueueElement<T> top = null; //save the link to top element
	private String topColor = "red";//color of the top element
	private String bottomColor = "orange";//color of the bottom element
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
	 * This method removes one element(the bottom one) from the queue 
	 */
	public void deQueue(){
		if (!bottom.outgoing.isEmpty()) 
			bottom.outgoing.clear();
		this.vertices.values().removeAll(Collections.singleton(bottom));
		
		if (this.vertices.isEmpty()) 
			bottom = null;
		else{
			setBottom(this.vertices.entrySet().iterator().next().getValue());
			if (top != null & circularLList) 
				this.circularLList();
		}
	}
	
	/**
	 * This method inserts one element into the queue
	 * @param identifier contains the element: Follower, Movies, etc.
	 */
	public void enQueue(T identifier){
		int currentNumber = this.vertices.entrySet().size(); 
		if (bottom != null & currentNumber != 1) {
			defaultColor(top);
			setTop(new QueueElement<>(identifier, this));
		}
		else if (currentNumber == 1){
			setTop(new QueueElement(identifier, this));		
			bottomColor(bottom);
		}
		else {
			setBottom(top = new QueueElement(identifier, this));
		}
	}
	
	private void setTop(QueueElement<T> anEntity){
		topColor(anEntity);
		//bottom.outgoing.clear();
		//top.outgoing.clear();
		if (this.LList() == true & this.vertices.size() != 0){
			anEntity.queueEdge(this.getTop());
		}
		top = anEntity;
		if (top != null & circularLList) 
			this.circularLList();
		
	}
	
	/**
	 * This method saves the reference to the bottom element
	 * @param anEntity - this is the bottom element
	 */
	private void setBottom(AbstractVertex<T> anEntity){
		bottomColor(anEntity);
		bottom = (QueueElement<T>)anEntity;
			bottom.outgoing.clear();
			top.outgoing.clear();
	}
	
	private void topColor(AbstractVertex<T> aVertex){
		aVertex.setColor(topColor);
		aVertex.setShape("rect");
	}
	
	private void bottomColor(AbstractVertex<T> aVertex){
		aVertex.setColor(bottomColor);
	}
	
	private void defaultColor(AbstractVertex<T> aVertex){
		aVertex.setColor(defaultColor);
	}
	/**
	 * This method retrieves the bottom of the queue 
	 * @return
	 */
	public AbstractVertex<T> getBottom(){
		return bottom;
	}
	
	/**
	 * This method retrieves the top of the queue 
	 * @return
	 */
	public AbstractVertex<T> getTop(){
		return top;
	}

	/**
	 * This method empties the queue
	 */
	public void clear(){
		this.vertices.clear();
		top = bottom = null;
	}
	
	public void noLListVisualization(){
		llist = false;
	}
	
	public boolean LList(){
		return llist;
	}
	
	public boolean circularLList(){
		bottom.queueEdge(top);
		return circularLList = true;
	}

}//end of the class
