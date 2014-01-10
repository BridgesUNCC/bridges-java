package bridges
import java.util
import org.json.simple._
import scala.collection.JavaConverters._

/** Represent a Social Network User.
  * Includes a unique id, a unique screen_name, a human-readable name, and
  * a way to retrieve the user's followers */
class FollowGraphNode(bridge: Bridge, val info:JSONObject) {
    // Data sanity check: may be unnecessary if tests are adequate
    assert(info != null, "Can't read null JSONObject")
    for (key <- List("id", "screen_name", "name"))
      assert(info.containsKey(key), s"User JSON missing required key '$key'")
      
    val id = info.get("id").asInstanceOf[Long]
    val screen_name = info.get("screen_name").asInstanceOf[String]
    val name = info.get("name").asInstanceOf[String]
    
    /** List the user's followers as more FollowGraphNodes.
        Limit the result to `max` followers. Note that results are batched, so
        a large `max` (as high as 200) _may_ only count as one request.
        See Bridges.followgraph() for more about rate limiting. */
    def followers(max:Integer=10)= {
        val response = bridge.getjs(s"/api/followgraph/followers/$id?max=$max")
        val users = response.get("followers").asInstanceOf[util.List[JSONValue]]
        
        (0 until users.size()).map {
            i => new FollowGraphNode(bridge, users.get(i).asInstanceOf[JSONObject])
        }.asJava
    }
}