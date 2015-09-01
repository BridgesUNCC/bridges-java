package bridges.base;


/**
 * 
 * @author Mihai Mehedint
 * @param <E>
 * @Description This class can be used to create arrays with generic types
 * as follows: 
 * ArrayElement<E>[] myArray = (ArrayElement<E>[]) new ArrayElement[10];
 * Where E is: Tweet, Actor, Movie, Integer, String or other generic type
 */
public class ArrayElement<E> extends Element<E>{
	public static int index;
	
	/** Construct an array labeled "label" and holding elements of "type". 	 
	 * @param label the label of ArrayElement that shows up on the Bridges visualization
	 * @param type the type of Element this array should be holding
	 */
	public ArrayElement(String label, E type){
		super(label, type);
	}	
}
