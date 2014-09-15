/**
 * 
 */
package edu.uncc.cs.bridges;

import java.util.ArrayList;

/**
 * @author Mihai Mehedint
 *
 */
public class StackElement <T> extends AbstractVertex<T>{
		
		private int elementSize = 30;
		private int id;
		
		
		/**
		 * The constructor
		 * @param identifier contains the identifier object: Follower, Movie, Actor
		 * @param queue is the Queue structure harboring the element
		 */
		public StackElement(T identifier, Stack aStack) {
			super(identifier);
			outgoing = new ArrayList<AbstractEdge<T>>();
			aStack.vertices.put(identifier, this);
			this.setSize(elementSize);
		}
		
		/**
		 * This method sets a (visual) connection object between 2 queue elements
		 * @param v2
		 * @return
		 */
		boolean setStackEdge(StackElement<T> v2){
			String ident = this.getIdentifier() +"To"+ v2.getIdentifier();
			return outgoing.add(new StackEdge(this, v2, ident));	
		}
		
		/**
		 * This method retrieves the line object connecting 2 elements of the queue
		 * element1.getQueueEdge(element2);
		 * @param anElement
		 * @return
		 */
		public StackEdge<T> getStackEdge(StackElement<T> anElement){
			for(int i = 0; i < this.outgoing.size(); i++){ 
				AbstractEdge<T> anEdge = (StackEdge<T>)this.outgoing.get(i);
					if(anEdge.destination.compareTo((AbstractVertex<T>)anElement)==0){				
						return (StackEdge<T>)this.outgoing.get(i);
				}
			}		
			return null;	
		}
		
		/**
		 * This method compares two elements
		 * @param o
		 * @return
		 */
		public int compareTo(StackElement<T> o) {
			if (o != null) {
				return Integer.compare(id, o.id);
			}
			return 0;
		}
		
		/**
		 * This method returns the next element in the queue
		 * @return
		 */
		public StackElement<T> next(){
			StackEdge<T> anEdge = (StackEdge<T>)this.outgoing.iterator().next(); 
			return (StackElement<T>)anEdge.destination;
		}

	}
