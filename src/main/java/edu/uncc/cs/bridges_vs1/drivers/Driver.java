package edu.uncc.cs.bridges_vs1.drivers;

import java.util.HashMap;

import edu.uncc.cs.bridges_vs1.network.Bridges;
import edu.uncc.cs.bridges_vs1.sources.Tweet;
import edu.uncc.cs.bridges_vs1.structure.*;
import edu.uncc.cs.bridges_vs1.validation.OutputLog;

public class Driver{

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
		SLelement<Tweet> test8 = new SLelement<>(test7);
		SLelement<Tweet> test9 = new SLelement<>("test9", new Tweet("test9"));
		//test7.setNext(null);
		//a null element cannot be added to the ADT
		//test7 = null;
		
		//changing the visual properties of the elements
		test.getVisualizer().setColor("red");
		test.getVisualizer().setSize(20);
		//the setNext() is still available but this is not taken into account 
		//when generating the visualization of Graph as SLelements 
		//elements must be added to a HashMap of HashMaps of elements as you see below
		test.setNext(test2);
		test.setNext(test3);
		test3.setNext(test4);
		
		//this hashmap stores the links between the nodes
		//it is initialized here
		//the students have the option of using an array structure instead
		//other types passed to the ADTvisualizer will throw an explicit error message
		HashMap<Element<Tweet>, HashMap<String, Element<Tweet>>> mapOfLinks = new HashMap<>();
		
		//mapOfLinks.get(test).get("test2"); //gettting 
		//this could be used to initialize the array
		//the cast is necessary to solve the problem of creating an array of generic types
		//however casting to a subtype gives runtime errors for arrays
		//Element<Tweet> [] array = (Element<Tweet> []) new Object[10]; 
		//Element<Tweet> [] array = new Element<Tweet> [10];
		
		//elements can be added to the HashMap structure in this way
		mapOfLinks.put(test, new HashMap<String, Element<Tweet>>());
		//create edges between the nodes
		
		
		Bridges<Tweet> bridge = new Bridges<Tweet>(13, "300587042698", "mmehedin@uncc.edu");
		bridge.setDataStructure(mapOfLinks, "graph"); //set the structure holding the nodes and links i.e. hashmap
									//set ADT type
		
		//alternatively,
		//elements can be added to ADT by using .add(Element<E>)
		//elements created in the driver but not added, cannot be visualize
		bridge.add(test);
		bridge.add(test2);
		bridge.add(test3);
		bridge.add(test4);
		bridge.add(test5);
		bridge.add(test6);
		bridge.add(test7);
		bridge.add(test8);//this is an object with the same label as object test7
		
		//This is how links are created in the Graph structure using HashMaps
		mapOfLinks.get(test).put("test2", test2);
		mapOfLinks.get(test).put("test3", test3);
		mapOfLinks.get(test).put("test5", test5);
		
		//bridge.setLink(test, test2);
		//bridge.setLink(test, test3);
		//bridge.setLink(test, test5);
		//links can be set using .setLink(source, target)
		//bridge.setLink(test2, test5);
		bridge.setLink(test3, test4);
		
		//setting the link between 2 elements with the same label
		bridge.setLink(test7, test8);
		
		//creating the copy of an element
		SLelement<Tweet> test10 = new SLelement<>("test10", new Tweet("test10"));
		bridge.add(test10);
		bridge.setLink(test2, test10);
		//System.out.println(test8.getIdentifier());
		
		//this is used to visualize the JSON before it is passed to the server
		//errors in JSON formatting can be visualized on the console
		bridge.toggleJSONdisplay();
		//aLog.returnStream();
		bridge.visualize();
	}
}
