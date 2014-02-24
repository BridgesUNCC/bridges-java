package edu.uncc.cs.bridges
import org.scalatest._
import java.io.IOException
import org.apache.http.client.fluent

class BStreamTest extends FlatSpec with Matchers {
    it should "load stream data from JSON" in {
    	fail("Unimplemented");
    }
    
    it should "load an empty ArrayList when receiving an empty response" in {
    	fail("Unimplemented");
    }
}

class EchoBridge(var response: String) extends Bridge(0) {
	override val http_connection = null
    override def http(url: fluent.Request)= response
}
    
class FollowGraphTest extends FlatSpec with Matchers {
    "FollowGraphNode" should "complain helpfully about empty responses" in {
        val bridge = new EchoBridge("")
        a [IOException] should be thrownBy {
            bridge.followgraph("screenname").followers()
        }
    }
    
    it should "load from a screenname via JSON" in {
        val bridge = new EchoBridge("""{"followers": []}""")
        
        val graph = bridge.followgraph("screenname")
        graph.screen_name should be("screenname")
        graph.followers().size() should be(0)
        
    }
    
    it should "retrieve followers" in {
        val bridge = new EchoBridge("""{"followers": ["FredElmquist", "AltonIsenhour"]}""")
        
        val graph = bridge.followgraph("screenname")
        val followers = graph.followers()
        followers.size() should be(2)
        followers.get(0).screen_name should be("FredElmquist")
        followers.get(1).screen_name should be("AltonIsenhour")
    }
    
    it should "record graph operations" in {
    	val bridge = new EchoBridge("""{"followers": ["FredElmquist", "AltonIsenhour"]}""")
    	FollowGraphNode.history.clear
    	FollowGraphNode.opened.clear
    	
    	// Handle node, handle root
        val graph = bridge.followgraph("screenname")
        FollowGraphNode.history should have length (1)
    	var event = FollowGraphNode.history(0)
    	event.kind should be("node")
    	event.from should be(Some("screenname"))
    	event.to should be(None)
    	
    	// Handle edges
        val root_followers = graph.followers()
        FollowGraphNode.history should have length (2+2+1) // 2 edges + 2 nodes + 1 from before
    	event = FollowGraphNode.history(2) // [root, node, edge, node, edge]
    	event.kind should be("edge")
    	event.from should be(Some("screenname"))
    	event.to should be(Some("FredElmquist"))
    	
    	// Handle duplicates
    	bridge.response = """{"followers": ["FredElmquist"]}"""
    	val altons_followers = root_followers.get(1).followers()
    	altons_followers.get(0).screen_name should be ("FredElmquist")
    	FollowGraphNode.history should have length (6) // 5 from before + 1 edge + 0 nodes
    	event = FollowGraphNode.history(5)
    	event.kind should be("edge")
    	event.from should be(Some("AltonIsenhour"))
    	event.to should be(Some("FredElmquist"))
    }
    
    it should "generate graph op JSON" in {
    	val bridge = new EchoBridge("""{"followers": ["FredElmquist", "AltonIsenhour"]}""")
    	FollowGraphNode.history.clear
    	FollowGraphNode.opened.clear
        val graph = bridge.followgraph("screenname")
        graph.followers();
        FollowGraphNode.historyJSON should be(
            s"""{"nodes":[{"name":"screenname"},{"name":"FredElmquist"},{"name":"AltonIsenhour"}],"links":[{"source":0,"target":1,"value":1},{"source":0,"target":2,"value":1}]}""")
    }
}