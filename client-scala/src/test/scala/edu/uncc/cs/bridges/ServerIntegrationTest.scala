package edu.uncc.cs.bridges

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
		// To run this test you will 
		bridge.api_key = "1241307114776"
		val center = bridge.followgraph("twitterapi")
		val followers = center.followers(10)
		// Twitterapi has way more than 10 followers so we should expect 10.
		followers.size() should be(10)
	}
}