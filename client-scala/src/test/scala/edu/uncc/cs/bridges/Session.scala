import org.scalatest._
import edu.uncc.cs.bridges._
import java.io.IOException

class BStreamStreamTest extends FlatSpec with Matchers {
    "BStream" should "load stream data from JSON" in {
    }
    
    it should "load an empty ArrayList when receiving an empty response" in {
    }
}
    
class FollowGraphTest extends FlatSpec with Matchers {
    "FollowGraph" should "complain helpfully about empty responses" in {
        val bridge = new Bridge(0) with DummyConnectable
        a [IOException] should be thrownBy {
            bridge.followgraph("screenname").followers()
        }
    }
    
    it should "load from a screenname via JSON" in {
        val bridge = new Bridge(0) with DummyConnectable
        bridge.response = """{"followers": []}"""
        
        val graph = bridge.followgraph("screenname")
        graph.screen_name should be("screenname")
        graph.followers().size() should be(0)
        
    }
    
    "FollowGraphNode" should "retrieve followers" in {
        val bridge = new Bridge(0) with DummyConnectable
        bridge.response = """{"followers": ["FredElmquist", "AltonIsenhour"]}"""
        
        val graph = bridge.followgraph("screenname")
        val followers = graph.followers()
        followers.size() should be(2)
        followers.get(0).screen_name should be("FredElmquist")
        followers.get(1).screen_name should be("AltonIsenhour")
    }
}