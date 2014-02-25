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
	def boilerplate= {
	    val bridge = new Bridge(0);
		// To run this test you will need to change this line
		bridge.api_key = "504999054938"
		bridge.followgraph("twitterapi")
	}
  
	"Interface" should "login" taggedAs(NetworkTest) in {
		val center = boilerplate
		val followers = center.followers(10)
		// Twitterapi has way more than 10 followers so we should expect 10.
		followers.size() should be(10)
	}
	
	it should "go deep" taggedAs(NetworkTest) in {
		val center = boilerplate
		val inner_circle = center.followers(100).asScala.toList
		
		def next_followers(last_followers: List[FollowGraphNode])= {
			last_followers.map { follower => // Collect their followers
			  try {
				  Option(follower.followers(100))
			  } catch {
				  // Some users don't allow reading their followers it seems
			      case e: java.io.IOException => None
			  }
			}
			.filter {_.nonEmpty} // Ignore None's
			.map {_.get.asScala} // Convert inner lists to scala
			.reduceOption {_ ++ _} // Join lists
			.getOrElse(List()) // If all are empty, make a List()
			.slice(0, 300) // Cap at 300 people
		}
		next_followers(inner_circle)
	}
}
