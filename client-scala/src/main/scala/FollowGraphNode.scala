package bridges
import java.util
import org.json.simple._
import scala.collection.JavaConverters._

class FollowGraphNode(bridge: Bridge, val info:JSONObject) {
    assert(info != null, "Can't read null JSONObject")
    for (key <- List("id", "screen_name", "name"))
      assert(info.containsKey(key), s"User JSON missing required key '$key'")
    val id = info.get("id").asInstanceOf[Long]
    val screen_name = info.get("screen_name").asInstanceOf[String]
    val name = info.get("name").asInstanceOf[String]
    
    def followers= {
        val response = bridge.getjs(s"/api/followgraph/followers/$id")
        val users = response.get("followers").asInstanceOf[util.List[JSONValue]]
        
        (0 until users.size()).map {
            i => new FollowGraphNode(bridge, users.get(i).asInstanceOf[JSONObject])
        }.asJava
    }
}