package edu.uncc.cs.bridges_vs1.drivers;

import java.util.HashMap;

import edu.uncc.cs.bridges_vs1.network.Bridges;
import edu.uncc.cs.bridges_vs1.sources.Tweet;
import edu.uncc.cs.bridges_vs1.structure.*;
import edu.uncc.cs.bridges_vs1.validation.OutputLog;

public class DriverGraphList{

	public static <E> void main(String[] args) throws Exception{
		
		//create a log for all outputs with or without errors in user's home directory
		//OutputLog aLog = new OutputLog();
		
		SLelement<Tweet> test = new SLelement<>("test", new Tweet("test"));
		//a duplicate value throws an exception (uncomment below)
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
		//System.out
		//test.setNext(test2);
		//test.setNext(test3);
		//test3.setNext(test4);
		

		HashMap<Element<Tweet>, GraphList<Tweet>> adjacencyList = new HashMap<>();
		//mapOfLinks.get(test).get("test");

		
		adjacencyList.put(test, new GraphList<Tweet>());
		adjacencyList.put(test2, new GraphList<Tweet>());
		adjacencyList.get(test).setNext(test2);
		//adjacencyList.get(test).put("test3", test3);
		//adjacencyList.get(test).put("test5", test5);
		
		Bridges<Tweet> bridge = new Bridges<Tweet>("300587042698", "mmehedin@uncc.edu");
		bridge.setDataStructure("graph", adjacencyList); //set the structure holding the nodes and links i.e. hashmap
									//set ADT type
		
		//bridge.add(test);
		//bridge.add(test2);
		//bridge.add(test3);
		//bridge.add(test4);
		//bridge.add(test5);
		//bridge.add(test6);
		//bridge.add(test7);
		
		//bridge.setLink(test, test2);
		//bridge.setLink(test, test3);
		//bridge.setLink(test, test5);
		//bridge.setLink(test2, test5);
		//bridge.setLink(test3, test4);
		
		//this is used to visualize the JSON before it is passed to the server
		//errors in JSON formatting can be visualized on the console
		System.out.println(bridge.getJSON());
		//aLog.returnStream();
		bridge.complete(13);
	}
}
