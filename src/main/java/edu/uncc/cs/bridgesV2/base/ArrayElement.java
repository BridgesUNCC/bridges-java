package edu.uncc.cs.bridgesV2.base;


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
	
	public ArrayElement(String label, E type){
		super(label, type);
	}	
}
