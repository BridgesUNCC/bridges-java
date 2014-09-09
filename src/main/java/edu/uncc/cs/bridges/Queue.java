package edu.uncc.cs.bridges;

/**
 * The Queue Class extends the GraphVisualizer class and implements methods 
 * specific for queues
 * @author Mihai Mehedint
 *
 * @param <T> this parameter will contain the types: Follower, Movies, Actors
 */
public class Queue<T> extends GraphVisualizer<T>{
	
	private AbstractVertex<T> bottom=null;
	/**
	 * The constructor
	 */
	public Queue(){
		super();
	}
	
	/**
	 * This method removes one element(the bottom one) from the queue 
	 */
	public void pop(){
		System.out.println(bottom.identifier);
		System.out.println(this.vertices.entrySet().iterator().next().getValue().getIdentifier());
		System.out.println(this.vertices.entrySet().remove(bottom));
		if (this.vertices.isEmpty()) 
			bottom = null;
		else
			bottom = this.vertices.entrySet().iterator().next().getValue();
	}
	
	/**
	 * This method inserts one element into the queue
	 * @param identifier contains the element: Follower, Movies, etc.
	 */
	public void push(T identifier){
		AbstractVertex anEntity =new QueueElement(identifier, this);
		anEntity.setShape("rect");
		if (bottom==null) setBottom(anEntity);
		
	}
	
	/**
	 * This method saves the reference to the bottom element
	 * @param anEntity - this is the bottom element
	 */
	private void setBottom(AbstractVertex anEntity){
		anEntity.setColor("orange");
		bottom = anEntity;
	}
	
	/**
	 * This method retrieves the bottom of the queue 
	 * @return
	 */
	public AbstractVertex getBottom(){
		return bottom;
	}

	/**
	 * This method empties the queue
	 */
	public void clear(){
		this.vertices.clear();
	}

}//end of the class
