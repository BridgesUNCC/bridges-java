/**
 * 
 */
package edu.uncc.cs.bridges_vs1.drivers;

import edu.uncc.cs.bridges_vs1.network.Bridges;
import edu.uncc.cs.bridges_vs1.sources.Tweet;
import edu.uncc.cs.bridges_vs1.structure.DLelement;
import edu.uncc.cs.bridges_vs1.structure.SLelement;

/**
 * @author mihai
 *
 */
public class DriverDLList {

		public static void main(String[] args) throws Exception{
			//create 2 SLelements
			DLelement<Tweet> test = new DLelement<>("test", new Tweet("test"));
			DLelement<Tweet> test2 = new DLelement<>("test2", new Tweet("test2"));
			//create an edge between the 2 elements
			test.setNext(test2);
			//create 2 SLelements
			DLelement<Tweet> test3 = new DLelement<>("test3", new Tweet("test3"));
			DLelement<Tweet> test4 = new DLelement<>("test4", new Tweet("test4"));
			//create an edge between the 2 elements
			test3.setNext(test4);
			test2.setNext(test3);
			test3.setPrev(test2);
			//create a DataFormater object
			Bridges<Tweet> bridge = new Bridges<Tweet>(13, "300587042698", "mmehedin@uncc.edu");
			//set visualizer type
			bridge.setDataStructure(test, "Dllist");
			//Print the JSON to console
			System.out.println(bridge.getJSON());
			bridge.visualize();
		}
}



