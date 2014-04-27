package edu.uncc.cs.bridges

import org.scalatest._
import scala.collection.JavaConverters._
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
	     * It's actually a bipartite graph. But neighbors() will switch between
	     * movies() and actors() depending on the provider (which is part of
	     * the string)
	     */
		val graph = new scaffold.Graphl()
		val frontier = new ArrayDeque[String]()
		var total_nodes = 0
		frontier.add("movie/Noah") // see above
		
		while (total_nodes < 100 && !frontier.isEmpty()) {
		    // Unlimited Width Breadth First Search
			val local_center = frontier.remove()
			graph.add(local_center)
			// Make a css string that looks like: #0a0a0a (dark gray)
			// The first opened node is black, but later nodes become white.
			var color = Integer.toHexString(total_nodes)
			color = "#" + color + color + color
			graph.setNodeColor(local_center, color)
			
			val neighbors = bridge.neighbors(local_center).asScala
			
			neighbors.foreach { neighbor =>
			  	// Only branch to this node if it does not already exist
			  	if (graph.add(neighbor)) {
			  		frontier.add(neighbor)
			  	}
				graph.setEdge(local_center, neighbor, 1)
			}
		}
		
		bridge.displayGraph("movie/Noah", graph)
	}
	
}