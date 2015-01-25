package edu.uncc.cs.bridges_vs1.drivers;

import java.util.HashMap;

import edu.uncc.cs.bridges_vs1.network.Bridges;
import edu.uncc.cs.bridges_vs1.sources.Tweet;
import edu.uncc.cs.bridges_vs1.structure.DLelement;
import edu.uncc.cs.bridges_vs1.structure.Element;
import edu.uncc.cs.bridges_vs1.structure.SLelement;
import edu.uncc.cs.bridges_vs1.structure.TreeElement;

public class DriverBinaryTree {

	public static void main(String[] args) throws Exception {
		TreeElement<Tweet> test1 = new TreeElement<>("test1", new Tweet("test1"));
		TreeElement<Tweet> test2 = new TreeElement<>("test2", new Tweet("test2"));
		TreeElement<Tweet> test3 = new TreeElement<>("test3", new Tweet("test3"));
		TreeElement<Tweet> test4 = new TreeElement<>("test4", new Tweet("test4"));
		TreeElement<Tweet> test5 = new TreeElement<>("test5", new Tweet("test5"));
		TreeElement<Tweet> test6 = new TreeElement<>("test6", new Tweet("test6"));
		
		//create tree links between the nodes
		test1.setLeft(test2);
		test1.setRight(test3);
		
		test2.setLeft(test4);
		test2.setRight(test5);
		
		test3.setLeft(test6);
		//changing the visual properties of the elements
		test1.getVisualizer().setColor("red");
		test1.getVisualizer().setSize(20);
	
		Bridges<Tweet> bridge = new Bridges<Tweet>(13, "300587042698", "mmehedin@uncc.edu");
		bridge.setDataStructure(test1, "tree"); //set the structure holding the nodes and links i.e. hashmap
									//set ADT type
		
		//this is used to visualize the JSON before it is passed to the server
		//errors in JSON formatting can be visualized on the console*/
		bridge.toggleJSONdisplay();
		//aLog.returnStream();
		bridge.visualize();
	}

}
