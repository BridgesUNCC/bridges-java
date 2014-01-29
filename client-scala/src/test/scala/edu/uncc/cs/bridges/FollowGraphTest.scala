package edu.uncc.cs.bridges
import org.scalatest._
import java.io.IOException
import org.apache.http.client.fluent

class BStreamTest extends FlatSpec with Matchers {
    "BStream" should "load stream data from JSON" in {
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
    "FollowGraph" should "complain helpfully about empty responses" in {
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
    
    "FollowGraphNode" should "retrieve followers" in {
        val bridge = new EchoBridge("""{"followers": ["FredElmquist", "AltonIsenhour"]}""")
        
        val graph = bridge.followgraph("screenname")
        val followers = graph.followers()
        followers.size() should be(2)
        followers.get(0).screen_name should be("FredElmquist")
        followers.get(1).screen_name should be("AltonIsenhour")
    }
}