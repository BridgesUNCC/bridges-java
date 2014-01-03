package bridges
import org.apache.http.client.fluent
import org.json.simple._

/** Specialized frontend for the FollowGraph connector */
class FollowGraph(bridge: Bridge, screen_name: String = null, info: JSONObject = null) {
    val user_json = if (info != null) {
        info
    } else if (screen_name != null) {
        bridge.json[JSONObject](bridge.get(s"/streams/followgraph/user/$screen_name").get)
    } else {
        throw new Exception("At least one of screen_name or info must be supplied")
    }
    
    val id = user_json.get("id").asInstanceOf[Long]
}