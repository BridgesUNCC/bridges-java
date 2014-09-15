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
	private QueueElement<T> prev = null; //save the link to the previous element
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
		if (!bottom.outgoing.isEmpty()) {
			bottom.outgoing.clear();
			top.outgoing.clear();
		}
		this.vertices.values().removeAll(Collections.singleton(bottom));
		
		if (this.vertices.isEmpty()) 
			bottom = null;
		else{
			setBottom(this.vertices.entrySet().iterator().next().getValue());
			if (top != null & circularLList) {
				top.outgoing.clear();
				bottom.outgoing.clear();
				top.queueEdge(bottom);
				top.queueEdge(prev);
			}
		}
	}
	
	/**
	 * This method inserts one element into the queue
	 * @param identifier contains the element: Follower, Movies, etc.
	 */
	public QueueElement<T> enQueue(T identifier){
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
			setBottom(prev = top = new QueueElement(identifier, this));
		}
		return top;
	}
	
	/**
	 * This method sets a new top element for the queue
	 * @param anEntity
	 */
	private void setTop(QueueElement<T> anEntity){
		topColor(anEntity);
		if (this.LList() & this.vertices.size() != 0 & !circularLList){
			this.changeEdges(anEntity);
		}
		if (top != null & top != bottom & bottom != null & circularLList){
			this.changeEdges(anEntity);
			this.setCircularLList();
		}
		else
			top = anEntity;
	}
	
	/**
	 * This method saves the reference to the bottom element
	 * @param anEntity - this is the bottom element
	 */
	private void setBottom(AbstractVertex<T> anEntity){
		bottomColor(anEntity);
		bottom = (QueueElement<T>)anEntity;
		bottom.outgoing.clear();
	}
	
	/**
	 * This method sets the color of the top element of the queue
	 * @param aVertex
	 */
	private void topColor(AbstractVertex<T> aVertex){
		aVertex.setColor(topColor);
		aVertex.setShape("rect");
	}
	
	/**
	 * This metod sets the color for the bottom element of the queue
	 * @param aVertex
	 */
	private void bottomColor(AbstractVertex<T> aVertex){
		aVertex.setColor(bottomColor);
	}
	
	/**
	 * This method sets the default color value to elements of the queue different than 
	 * top or bottom 
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
		top.outgoing.clear();
		bottom.outgoing.clear();
		top.queueEdge(prev);
		prev = top;
		top = anEntity;
		top.queueEdge(prev);
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
		top = bottom = prev = null;
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
	 * This method sets the status of the queue to circular
	 * @return
	 */
	public boolean circularLList(){
		//setCircularLList();
		return circularLList = true;	
	}
	
	/**
	 * This method closes the loop between the bottom and the top of the queue
	 * in a circular list
	 */
	public void setCircularLList(){
		top.queueEdge(bottom);
	}

}//end of the class
