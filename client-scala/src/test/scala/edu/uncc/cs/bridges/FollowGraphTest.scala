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
    
    it should "generate graph op JSON" in {
        val graph = new java.util.HashMap[String, java.util.Set[String]]()
        val followers = new java.util.HashSet[String]();
    	followers.add("FredElmquist")
    	followers.add("AltonIsenhour")
        graph.put("screenname", followers)
        
        // At this point some edges reference missing nodes 
        a [MissingNodeException] should be thrownBy {
    	  new EchoBridge("").followgraph("").json(graph)
    	}
    	
		graph.put("FredElmquist", new java.util.HashSet[String]())
		graph.put("AltonIsenhour", new java.util.HashSet[String]())
    	
    	new EchoBridge("").followgraph("").json(graph) should be(
            s"""{"nodes":[{"name":"screenname"},{"name":"FredElmquist"},{"name":"AltonIsenhour"}],"links":[{"source":0,"target":1,"value":1},{"source":0,"target":2,"value":1}]}""")
    }
}