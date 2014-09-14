package edu.uncc.cs.bridges;
/**
 * 
 * @author Mihai Mehedint
 * @param <T>
 */
public class QueueEdge<T> extends AbstractEdge<T>{
	/**
	 * the constructor
	 * @param source
	 * @param destination
	 * @param identifier
	 */
	public QueueEdge(AbstractVertex<T> source, AbstractVertex<T> destination,
			T identifier) {
		super(source, destination, identifier);
	}
	
	public QueueEdge(String aValue){
		super(aValue);
	}

}
