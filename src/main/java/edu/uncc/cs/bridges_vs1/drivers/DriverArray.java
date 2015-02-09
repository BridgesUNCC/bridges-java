/**
 * 
 */
package edu.uncc.cs.bridges_vs1.drivers;

import edu.uncc.cs.bridgesV2.data_src_dependent.*;
import edu.uncc.cs.bridgesV2.base.SLelement;

/**
 * @author mihai mehedint
 *
 */
public class DriverArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//creating an array with elements with generic types
		SLelement<Tweet>[] array2 = (SLelement<Tweet>[]) new SLelement[10];
		//this doesn't work. it compiles but gives runtime errors
		//SLelement<Tweet>[] array3 = (SLelement<Tweet>[]) new Object[10];
		SLelement<Tweet> test = new SLelement<>("test", new Tweet("test"));
		//add one element to the array
		array2[0]=test;
		//check if the this works by printing the properties of the Element
		System.out.println(array2[0].getLabel()+" "+array2[0].getVisualizer().getColor());
	}
}
