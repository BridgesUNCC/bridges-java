package edu.uncc.cs.bridges_vs1.drivers;

import java.util.HashMap;

import edu.uncc.cs.bridges_vs1.network.Bridges;
import edu.uncc.cs.bridges_vs1.sources.Tweet;
import edu.uncc.cs.bridges_vs1.structure.*;
import edu.uncc.cs.bridges_vs1.validation.OutputLog;

public class DriverGraphAdjacencyList{

	public static <E> void main(String[] args) throws Exception{
		
		//create a log for all outputs with or without errors in user's home directory
		//OutputLog aLog = new OutputLog();
		
		SLelement<Tweet> test = new SLelement<>("test", new Tweet("test"));
		//a duplicate value throws an exception (uncomment the line below to test)
		//DLelement<Tweet> test = new DLelement<>("test", new Tweet("test"));
		
		SLelement<Tweet> test2 = new SLelement<>("test2", new Tweet("test2"));
		SLelement<Tweet> test3 = new SLelement<>("test3", new Tweet("test3"));
		SLelement<Tweet> test4 = new SLelement<>("test4", new Tweet("test4"));
		SLelement<Tweet> test5 = new SLelement<>("test5", new Tweet("test5"));
		SLelement<Tweet> test6 = new SLelement<>("test6", new Tweet("test6"));
		SLelement<Tweet> test7 = new SLelement<>("test7", new Tweet("test7"));
		
		//a null element cannot be added to the ADT
		//test7 = null;
		
		//changing the visual properties of the elements
		test.getVisualizer().setColor("red");
		test.getVisualizer().setSize(20);
		//test.setNext(new SLelement<>(test2).setNext(new SLelement<>(test3)));
		test.setNext(new SLelement<>(test2));
		test2.setNext(new SLelement<>(test4));
		//test2.setNext(test);
		//this is the adjacency list, with the HashMap<key, value> where 
		//key represents the element's identifier and 
		//value is the element.
		
		HashMap<String, SLelement<Tweet>> adjacencyList = new HashMap<>();
		adjacencyList.put("test", test);
		adjacencyList.put("test2", test2);
		adjacencyList.put("test4", test4);
		
		Bridges<Tweet> bridge = new Bridges<Tweet>(13, "300587042698", "mmehedin@uncc.edu");
		bridge.setDataStructure("graphl", adjacencyList); //set the structure holding the nodes and links i.e. Singly linked list
									//set ADT type
				
		//this is used to visualize the JSON before it is passed to the server
		//errors in JSON formatting can be visualized on the console
		bridge.toggleJSONdisplay();
		//aLog.returnStream();
		bridge.visualize();
	}
}
