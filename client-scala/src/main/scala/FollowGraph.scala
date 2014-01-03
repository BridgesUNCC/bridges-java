package bridges
import org.apache.http.client.fluent
import net.liftweb.json

/** Specialized frontend for the FollowGraph connector */
abstract class FollowGraph(bridge: Bridge, screen_name: String = null, info: json.JValue = null) {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
    val user_json = if (info != null) {
        info
    } else if (screen_name != null) {
        json.parse(http(fluent.Request.Get(
          s"http://localhost:3000/streams/followgraph/user/$screen_name"
          )).get)
    } else {
        throw new Exception("At least one of screen_name or info must be supplied")
    }
    
    val id = (user_json \ "id").extract[Long]
    
    def http(url: fluent.Request): Option[String]
}