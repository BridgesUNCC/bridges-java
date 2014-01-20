package edu.uncc.cs.bridges
import java.util
import org.json.simple._
import scala.collection.JavaConverters._

/** Represent a Social Network User.
  * Includes a unique id, a unique screen_name, a human-readable name, and
  * a way to retrieve the user's followers */
class FollowGraphNode(bridge: Bridge, val screen_name: String) {
    
    /** List the user's followers as more FollowGraphNodes.
        Limit the result to `max` followers. Note that results are batched, so
        a large `max` (as high as 200) _may_ only count as one request.
        See Bridges.followgraph() for more about rate limiting. */
    def followers(max:Integer=10)= {
        val response = bridge.getjs(s"/streams/twitter.com/followgraph/$screen_name/$max")
        val users = response.get("followers").asInstanceOf[util.List[JSONValue]]
        
        (0 until users.size()).map {
            i => new FollowGraphNode(bridge, users.get(i).asInstanceOf[String])
        }.asJava
    }
}