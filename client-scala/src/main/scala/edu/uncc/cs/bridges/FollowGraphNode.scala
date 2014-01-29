package edu.uncc.cs.bridges
import java.util
import org.json.simple._
import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set

/** Represent a Social Network User.
  * Includes a unique id, a unique screen_name, a human-readable name, and
  * a way to retrieve the user's followers */
class FollowGraphNode(
    bridge: Bridge,
    val screen_name: String,
    val parent: Option[FollowGraphNode]=None
    ) {
	if (FollowGraphNode.opened.add(screen_name))
	    // Add the node if it doesn't already exist
		FollowGraphNode.history += HistoryEvent("node", System.nanoTime(), Option(screen_name), None)
	if (parent.nonEmpty)
	    // Add an edge if this is not the root node
		FollowGraphNode.history += HistoryEvent("edge", System.nanoTime(), parent.map(fgn => fgn.screen_name), Option(screen_name))
    
    /** List the user's followers as more FollowGraphNodes.
        Limit the result to `max` followers. Note that results are batched, so
        a large `max` (as high as 200) _may_ only count as one request.
        See Bridges.followgraph() for more about rate limiting. */
    def followers(max:Integer=10)= {
        val response = bridge.getjs(s"/streams/twitter.com/followgraph/$screen_name/$max")
        val users = response.get("followers").asInstanceOf[util.List[JSONValue]]
        
        val followerlist = (0 until users.size()).map {
            i => new FollowGraphNode(bridge, users.get(i).asInstanceOf[String], Option(this))
        }
        bridge.post("/assignments/$assignment/events", Map("history" -> FollowGraphNode.historyJSON))
        FollowGraphNode.history.clear
        followerlist.asJava
    }
}

object FollowGraphNode {
    var opened = Set[String]()
	var history = ListBuffer[HistoryEvent]()
	
	def historyJSON= {
    	val hst = history.map(_.json).reduce(_ + "," + _)
    	s"""{"history": [$hst]}"""
    }
}

case class HistoryEvent(
    val kind : String,
    val time : Double,
    val from : Option[String],
    val to : Option[String]
) {
	def json= {
	    s"""{"kind": "$kind",
            "time": $time,
	        "from": "$from",
	        "to": "$to"}"""
	}
}
