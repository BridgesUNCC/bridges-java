package edu.uncc.cs.bridges;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


	public class Stack<T> extends GraphVisualizer<T>{
		
		private StackElement<T> bottom = null; //save the link to bottom element
		private StackElement<T> top = null; //save the link to top element
		private StackElement<T> prev = null; //save the link to the previous element
		private String topColor = "red";//color of the top element
		private String bottomColor = "orange";//color of the bottom element
		private String defaultColor = "black";//color of the intermediary elements
		private boolean llist = true; //by default the visualization will display the links between the elements
		private boolean circularLList = false; //this can be set to true in case we want to display the stack as a circular LList
		
		public Map<String, AbstractVertex<T>> vertices = new LinkedHashMap<>();//this overrides the vertices Map in Visualizer so the order of elements is maintained
		
		/**
		 * The constructor
		 */
		public Stack(){
			super();
			super.vertices = this.vertices; //overrides the vertices Map in GraphVisualizer	
		}
		
		/**
		 * This method removes one element(the bottom one) from the stack 
		 */
		public void pop(){
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
					top.setStackEdge(bottom);
					top.setStackEdge(prev);
				}
			}
		}
		
		/**
		 * This method inserts one element into the stack
		 * @param identifier contains the element: Follower, Movies, etc.
		 */
		public StackElement<T> push(T identifier){
			int currentNumber = this.vertices.entrySet().size(); 
			if (bottom != null & currentNumber != 1) {
				defaultColor(top);
				setTop(new StackElement<>(identifier, this));
			}
			else if (currentNumber == 1){
				setTop(new StackElement(identifier, this));		
				bottomColor(bottom);
			}
			else {
				setBottom(prev = top = new StackElement(identifier, this));
			}
			return top;
		}
		
		/**
		 * This method sets a new top element for the stack
		 * @param anEntity
		 */
		private void setTop(StackElement<T> anEntity){
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
			bottom = (StackElement<T>)anEntity;
			bottom.outgoing.clear();
		}
		
		/**
		 * This method sets the color of the top element of the stack
		 * @param aVertex
		 */
		private void topColor(AbstractVertex<T> aVertex){
			aVertex.setColor(topColor);
			aVertex.setShape("rect");
		}
		
		/**
		 * This metod sets the color for the bottom element of the stack
		 * @param aVertex
		 */
		private void bottomColor(AbstractVertex<T> aVertex){
			aVertex.setColor(bottomColor);
		}
		
		/**
		 * This method sets the default color value to elements of the stack different than 
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
		private void changeEdges(StackElement<T> anEntity){
			top.outgoing.clear();
			bottom.outgoing.clear();
			top.setStackEdge(prev);
			prev = top;
			top = anEntity;
			top.setStackEdge(prev);
		}
		
		/**
		 * This method retrieves the bottom of the stack 
		 * @return
		 */
		public AbstractVertex<T> getBottom(){
			return bottom;
		}
		
		/**
		 * This method retrieves the top of the stack 
		 * @return
		 */
		public AbstractVertex<T> getTop(){
			return top;
		}

		/**
		 * This method empties the stack
		 */
		public void clear(){
			this.vertices.clear();
			top = bottom = prev = null;
		}
		
		/**
		 * This method sets the boolean variable llist
		 * to false and no lines will be displayed between the stack elements
		 */
		public void noLListVisualization(){
			llist = false;
		}
		
		/**
		 * This method returns the value of the boolean variable llist  
		 * which is true (by default) when the line connecting the stack elements is visible
		 * @return a boolean value
		 */
		public boolean LList(){
			return llist;
		}
		
		/**
		 * This method sets the status of the stack to circular
		 * @return
		 */
		public boolean circularLList(){
			//setCircularLList();
			return circularLList = true;	
		}
		
		/**
		 * This method closes the loop between the bottom and the top of the stack
		 * in a circular list
		 */
		public void setCircularLList(){
			top.setStackEdge(bottom);
		}

	}//end of the class
