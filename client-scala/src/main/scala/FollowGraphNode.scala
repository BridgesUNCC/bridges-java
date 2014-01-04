package bridges
import org.json.simple._
import scala.collection.JavaConverters._

class FollowGraphNode(bridge: Bridge, val info:JSONObject) {
    assert(info != null, "Can't read null JSONObject")
    for (key <- List("id", "screen_name", "name"))
      assert(info.containsKey(key), "User JSON missing required key $key")
    val id = info.get("id").asInstanceOf[Long]
    val screen_name = info.get("screen_name").asInstanceOf[String]
    val name = info.get("name").asInstanceOf[String]
    
    def followers= {
        val response = bridge.get("/stream/followgraph/followers/$id")
        val users = List(bridge.json[JSONObject](response.get)
            .get("followers").asInstanceOf[JSONArray])
        Console.println(users)
        users.map(user => new FollowGraphNode(bridge, user.asInstanceOf[JSONObject]))
          .asJava
    }
}