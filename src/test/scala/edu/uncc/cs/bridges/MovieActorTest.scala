package edu.uncc.cs.bridges

import org.scalatest._
import java.util.ArrayDeque
import java.util.HashMap

class MovieActorTest extends FlatSpec with Matchers {
  /**
   * Create a degrees-of-Kevin-Bacon graph
   */
	"movies() and actors()" should "be able to create connected graphs in connection with the server" in {
	    // Boilerplate login to Bridges: Assignment 1
		val bridge = new Bridge(1)
		bridge.server("http://127.0.0.1:3000")
		bridge.login("1279205054113")
		
	    /*
	     * The hard part: make the graph.
	     * 
	     * To make things easier, we will add a letter, where
	     *     if it starts with a, it's an actor
	     *     if it starts with m, it's a movie
	     */
		/*val graph = new scaffold.Graphl()
		val frontier = new ArrayDeque[String]()
		var total_nodes = 0
		frontier.add("aKevin Bacon") // see above
		
		while (total_nodes < 1000) {
		    // Unlimited Width Breadth First Search
			val local_center = frontier.remove()
			var local_neighbors = new ArrayDeque[String]()
			if (local_center.charAt(0) == 'a') {
			}
		}*/
	}
	
}