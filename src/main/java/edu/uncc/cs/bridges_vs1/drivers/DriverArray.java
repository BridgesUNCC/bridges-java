/**
 * 
 */
package edu.uncc.cs.bridges_vs1.drivers;

import edu.uncc.cs.bridgesV2.connect.Bridges;
import edu.uncc.cs.bridgesV2.data_src_dependent.*;
import edu.uncc.cs.bridgesV2.base.ArrayElement;

/**
 * @author mihai mehedint
 *
 */
public class DriverArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bridges<Tweet> bridge = new Bridges<Tweet>(13, "300587042698", "mmehedin@uncc.edu");
		//creating an array with elements with generic types
		ArrayElement<Tweet>[] array = (ArrayElement<Tweet>[]) new ArrayElement[10];
		//this doesn't work. it compiles but gives runtime errors
		//SLelement<Tweet>[] array3 = (SLelement<Tweet>[]) new Object[10];
		ArrayElement<Tweet> test = new ArrayElement<>("test", new Tweet("test"));
		ArrayElement<Tweet> test2 = new ArrayElement<>("test2", new Tweet("test2"));
		ArrayElement<Tweet> test3 = new ArrayElement<>("test3", new Tweet("test3"));
		//add one element to the array
		array[0]=test;
		array[1] = test2;
		array[2] = test3;
		//printing the properties of the Element
		System.out.println(array[0].getLabel()+" "+array[0].getVisualizer().getColor());
		bridge.setDataStructure(array, "AList");
		bridge.toggleJSONdisplay();
		bridge.visualize();
	}
}
