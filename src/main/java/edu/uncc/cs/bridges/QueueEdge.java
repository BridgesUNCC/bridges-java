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
	
	public AbstractEdge<T> setDash(double[] aPattern){
		return super.setDash(aPattern);
	}
	
	public AbstractEdge<T> setColor(String color){
		return super.setColor(color);
	}

}
