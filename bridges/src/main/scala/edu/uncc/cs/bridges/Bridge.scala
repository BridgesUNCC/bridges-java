package edu.uncc.cs.bridges
import java.util
import java.util.ArrayDeque
import org.json.simple._
import scala.collection._
import scala.collection.JavaConverters._
import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

/** Network-enabled sample data aggregator.
  * Bridges offers connectivity for students to more easily use interesting real
  * world data for introductory projects. */
class Bridge(val assignment: Int) extends KeyConnectable {
    
    /** Connect to a streaming data source such as a social network feed.
        This feature is not yet complete. Use at your own peril. */
    def stream(name: String)= {
        new BStream(this, name)
    }
    
    /** Create JSON for visualization server.
     *  Returns the URL associated with this assignment.
     *  
     *  nodes: An adjacency list, implemented as a Map from screen names to
     *      Iterables of screen names.
     */
    def displayGraph(center: String, graph: Graph)= {
    	// Create and upload JSON
        post("/assignments/$assignment", generateGraphJson(center, graph))
        // Return a URL to the user
        s"/assignments/${assignment}/YOUR_USERNAME"
    }
    
    def splitIdentifier(identifier: String)= {
    	if (! identifier.contains("/")) {
    		throw new RuntimeException("Provider or screenname missing. Should look like: twitter.com/username")
    	}
    	identifier.split("/", 2) 
    }
    
    def generateGraphJson(center: String, graph: Graph)= {
    	val VISITED=173
    	val open = new ArrayDeque[String]();
    	graph.setMark(center, VISITED);
    	open.add(center);
    	
    	var edges = List[List[String]]()
    	var nodes = List[String]()
    	  
    	while (! open.isEmpty()) {
    	  val local = open.remove()
    	  nodes ++= List(splitIdentifier(local)(1))
    	  var neighbor = graph.first(local)
    	  while (neighbor != null) {
    	      edges ++= List(List(splitIdentifier(local)(1), splitIdentifier(neighbor)(1)))
    	      if (graph.getMark(neighbor) != VISITED) {
    	    	  graph.setMark(neighbor, VISITED)
    	    	  open.add(neighbor);
    	      }
    	      neighbor = graph.next(local)
    	  }
    	}
    	var node_string = nodes
			.map("{\"name\": \"" + _ + "\"}")
			.reduceOption(_ + "," + _)
			.getOrElse("")
    	var edge_string = edges
    		.map(x => List(nodes.indexOf(x(0)), nodes.indexOf(x(1))))
		    .map(x => s"""{"source":${x(0)},"target":${x(1)},"value":1}""")
    		.reduceOption(_ + "," + _)
    		.getOrElse("")
    	s"""{"nodes":[$node_string],"links":[$edge_string]}"""
    }
    
    /** List the user's followers as more FollowGraphNodes.
        Limit the result to `max` followers. Note that results are batched, so
        a large `max` (as high as 200) _may_ only count as one request.
        See Bridges.followgraph() for more about rate limiting. */
    def neighbors(identifier: String, max:Integer=100)= {
    	val Array(service, screen_name) = splitIdentifier(identifier) 
        (try {
	        val response = getjs(s"/streams/$service/followers/$screen_name/$max")
	        val users = response.get("followers").asInstanceOf[util.List[JSONValue]]
	        if (users == null) {
	            // Twitter sometimes gives us garbage
	    		List()
	        } else {
	        	(0 until users.size()).map {
	        		i => s"$service/${users.get(i).asInstanceOf[String]}"
	        	}
	        }
        } catch {
        	case e: RateLimitException => List()
    	}).asJava
    }
}