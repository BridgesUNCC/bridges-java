/**
 * 
 */
package bridges_vs2.drivers;


import bridges_vs2.network.DataFormatter;
import bridges_vs2.sources.Tweet;
import bridges_vs2.structure.ADTVisualizer;
import bridges_vs2.structure.SLelement;

/**
 * @author mihai
 *
 */
public class DriverLList {

		public static void main(String[] args) throws Exception{
			SLelement<Tweet> test = new SLelement<>("test", new Tweet("test"));
			SLelement<Tweet> test2 = new SLelement<>("test2", new Tweet("test2"));
			test.setNext(test2);
			DataFormatter<Tweet> bridge = new DataFormatter<Tweet>(13,"300587042698", "mmehedin@uncc.edu");
			bridge.setVisParam(test, "llist");
			
			System.out.println(bridge.getJSON());
			bridge.complete();
		}
	}



