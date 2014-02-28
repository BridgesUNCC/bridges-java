package edu.uncc.cs.bridges
import java.util
import org.json.simple._
import scala.collection._
import scala.collection.JavaConverters._
import scala.collection.mutable.Set

/** Represent a Social Network User.
  * Includes a unique screen_name and a way to retrieve followers.
  * To upload visualization data:
  *   Make a java.util.Map<String, Set<String>> with screen names as keys
  *   and a list of screen names as values. Using Maps and Sets is intended
  *   to help avoid duplicates and possibly make searching easier. 
  */
class FollowGraphNode(
    bridge: Bridge,
    val screen_name: String
    ) {
    
    /** Create JSON for visualization server.
     *  Returns the URL associated with this assignment.
     *  
     *  nodes: An adjacency list, implemented as a Map from screen names to
     *      Iterables of screen names.
     */
    def display(nodes: java.util.Map[String, _ <: java.lang.Iterable[String]])= {
    	// Create and upload JSON
        bridge.post("/assignments/$assignment", json(nodes))
        // Return a URL to the user
        s"/assignments/${bridge.assignment}/YOUR_USERNAME"
    }
    
    /** List the user's followers as more FollowGraphNodes.
        Limit the result to `max` followers. Note that results are batched, so
        a large `max` (as high as 200) _may_ only count as one request.
        See Bridges.followgraph() for more about rate limiting. */
    def followers(max:Integer=100)= {
        (try {
	        val response = bridge.getjs(s"/streams/twitter.com/followers/$screen_name/$max")
	        val users = response.get("followers").asInstanceOf[util.List[JSONValue]]
	        if (users == null) {
	            // Twitter sometimes gives us garbage
	    		List()
	        } else {
	        	(0 until users.size()).map {
	        		i => new FollowGraphNode(bridge, users.get(i).asInstanceOf[String])
	        	}
	        }
        } catch {
        	case e: RateLimitException => List()
    	}).asJava
    }
    
    /** Create JSON for the visualization server.
     *  You should not need this in outside code. Use display() instead, which
     *  also uploads the result to the server.
     */
    def json(nodes: java.util.Map[String, _ <: java.lang.Iterable[String]])= {
        // Freeze the keys so that iteration order is consistent
    	val frozen_nodes = nodes.asScala.keys.toList
        // Convert links to JSON (with node numbers instead of node names)
        val links = nodes.asScala.flatMap { case (source, targets) =>
        	targets.asScala.map{ target =>
        	  	if (! frozen_nodes.contains(target)) throw new MissingNodeException(s"Edge $source->$target: Target not found. (Maybe you forgot to add $target to the map?)")
        	    s"""{"source":${frozen_nodes.indexOf(source)},"target":${frozen_nodes.indexOf(target)},"value":1}"""
    	    }
        }
        // Join nodes into one JSON array
        val node_json = frozen_nodes
    		.map(node => s"""{"name":"$node"}""")
    		.reduceOption(_ + "," + _)
    		.getOrElse("")
        // Join links into one JSON array
    	val link_json = links
        	.reduceOption(_ + "," + _)
        	.getOrElse("")
    	// Join nodes and links, and send them both
        s"""{"nodes":[$node_json],"links":[$link_json]}"""
    }
}

class MissingNodeException(msg: String) extends RuntimeException(msg)