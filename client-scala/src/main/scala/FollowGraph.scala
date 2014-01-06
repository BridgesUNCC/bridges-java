package bridges
import org.json.simple._

/** Specialized frontend for the FollowGraph connector */
class FollowGraph(bridge: Bridge, screen_name: String = null, info: JSONObject = null) {
    assert(screen_name != null || info != null, "FollowGraphNode requires at least one of screen_name or info be supplied")
    val root = new FollowGraphNode(
      bridge,
      Option(info).getOrElse(bridge.getjs(s"/api/followgraph/user/$screen_name"))
    )
}