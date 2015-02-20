/**
 * 
 */
package edu.uncc.cs.bridges_vs1.drivers;

import edu.uncc.cs.bridgesV2.connect.Bridges;
import edu.uncc.cs.bridgesV2.data_src_dependent.*;
import edu.uncc.cs.bridgesV2.base.*;

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
		SLelement<Tweet>[] array = (SLelement<Tweet>[]) new SLelement[10];
		//this doesn't work. it compiles but gives runtime errors
		//SLelement<Tweet>[] array3 = (SLelement<Tweet>[]) new Object[10];
		SLelement<Tweet> test = new SLelement<>("test test\ntest2", new Tweet("test"));
		SLelement<Tweet> test2 = new SLelement<>("test2 test", new Tweet("test2"));
		SLelement<Tweet> test3 = new SLelement<>("test3", new Tweet("test3"));
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
