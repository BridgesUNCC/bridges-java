package edu.uncc.cs.bridges

import scala.collection.JavaConverters._
import org.scalatest._

/** Optional Integration tests for bridges
 *  These require you to set up as server and are not intended to guarantee
 *  that the client conforms to a specification as much as verify that a
 *  correctly configured client and server will cooperate. These tests will not
 *   work correctly without appropriate setup and configuration, particularly 
 *   of the server. If you have not done so, do not be concerned if these fail.
 */ 

class ServerIntegrationTest extends FlatSpec with Matchers {
  
	"Interface" should "login" taggedAs(NetworkTest) in {
	    val bridge = new Bridge(0);
		bridge.login("1279205054113") // Paste your own key
		val followers = bridge.neighbors("twitter.com/twitterapi")
		// Twitterapi has way more than 10 followers so we should expect 100.
		followers.size() should be(100)
		
		// Begin Java-style code!
		/*val graph = new java.util.HashMap[String, java.util.Set[String]]()
		val follower_names = new java.util.HashSet[String]()
		followers.asScala.foreach {follower =>
		  	graph.put(follower.screen_name, new java.util.HashSet[String]())
		  	follower_names.add(follower.screen_name)
		}
		graph.put("twitterapi", follower_names)
		center.display(graph)*/
		// End Java-style code
		
		// At this point you should see a small graph of 11 nodes on the server
	}
}
