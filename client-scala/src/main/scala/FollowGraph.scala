package bridges
import org.json.simple._

/** Represent a Social Network as a graph.
  * Users are FollowGraphNodes, edges are implicit, indicated by another node's
  * presence in followers()*/
class FollowGraph(bridge: Bridge, screen_name: String = null, info: JSONObject = null) {
    // Sanity checks: maybe obviated by tests
    assert(screen_name != null || info != null, "A FollowGraph requires at least one of screen_name or info be supplied")
    
    val root = new FollowGraphNode(
      bridge,
      Option(info).getOrElse(bridge.getjs(s"/streams/twitter.com/followgraph/$screen_name"))
    )
}