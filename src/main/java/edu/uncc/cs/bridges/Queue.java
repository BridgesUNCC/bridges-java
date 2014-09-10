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
	
	private AbstractVertex<T> bottom = null; //save the link to bottom element
	private AbstractVertex<T> top = null; //save the link to top element
	private String topColor = "red";//color of the top element
	private String bottomColor = "orange";//color of the bottom element
	private String defaultColor = "black";//color of the intermediary elements
	private boolean llist = true; //by default the visualization will display the links between the elements
	
	public Map<String, AbstractVertex<T>> vertices = new LinkedHashMap<>();//this overrides the vertices Map in Visualizer so the order of elements is maintained
	
	
	/**
	 * The constructor
	 */
	public Queue(){
		super();
		super.vertices=this.vertices;//overrides the vertices Map in GraphVisualizer
	}
	
	/**
	 * This method removes one element(the bottom one) from the queue 
	 */
	public void deQueue(){
		this.vertices.values().removeAll(Collections.singleton(bottom));
		
		if (this.vertices.isEmpty()) 
			bottom = null;
		else
			setBottom(this.vertices.entrySet().iterator().next().getValue());
	}
	
	/**
	 * This method inserts one element into the queue
	 * @param identifier contains the element: Follower, Movies, etc.
	 */
	public void enQueue(T identifier){
		int currentNumber = this.vertices.entrySet().size(); 
		if (bottom!=null & currentNumber != 1) {
			defaultColor(top);
			setTop(new QueueElement(identifier, this));
		}
		else if (currentNumber == 1){
			setTop(new QueueElement(identifier, this));		
			bottomColor(bottom);
		}
		else {
			setBottom(top=new QueueElement(identifier, this));
		}
	}
	
	private void setTop(AbstractVertex anEntity){
		topColor(anEntity);
		top = anEntity;
	}
	
	/**
	 * This method saves the reference to the bottom element
	 * @param anEntity - this is the bottom element
	 */
	private void setBottom(AbstractVertex anEntity){
		bottomColor(anEntity);
		bottom = anEntity;
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
	public AbstractVertex getBottom(){
		return bottom;
	}
	
	/**
	 * This method retrieves the top of the queue 
	 * @return
	 */
	public AbstractVertex getTop(){
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

}//end of the class
