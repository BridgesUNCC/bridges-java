/**
 * 
 */
package edu.uncc.cs.bridges_vs1.drivers;

import edu.uncc.cs.bridges_vs1.network.DataFormatter;
import edu.uncc.cs.bridges_vs1.sources.Tweet;
import edu.uncc.cs.bridges_vs1.structure.ADTVisualizer;
import edu.uncc.cs.bridges_vs1.structure.SLelement;

/**
 * @author mihai
 *
 */
public class DriverLList {

		public static void main(String[] args) throws Exception{
			//create 2 SLelements
			SLelement<Tweet> test = new SLelement<>("test", new Tweet("test"));
			SLelement<Tweet> test2 = new SLelement<>("test2", new Tweet("test2"));
			//create an edge between the 2 elements
			test.setNext(test2);
			//create 2 SLelements
			SLelement<Tweet> test3 = new SLelement<>("test3", new Tweet("test3"));
			SLelement<Tweet> test4 = new SLelement<>("test4", new Tweet("test4"));
			//create an edge between the 2 elements
			test3.setNext(test4);
			test2.setNext(test3);
			//create a DataFormater object
			DataFormatter<Tweet> bridge = new DataFormatter<Tweet>(13,"300587042698", "mmehedin@uncc.edu");
			//set visualizer type
			bridge.setVisParam(test, "llist");
			//Print the JSON to console
			System.out.println(bridge.getJSON());
			bridge.complete();
		}
}


