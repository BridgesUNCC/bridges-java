import org.scalatest._
import bridges._
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
            bridge.followgraph("screenname")
        }
    }
    
    it should "load from a screenname via JSON" in {
        val graph = newbridge.followgraph("screenname")
        graph.root.id should be(9)
        graph.root.screen_name should be("John")
        graph.root.name should be("John S. Abernathy")
        graph.root.followers().size() should be(0)
        
    }
    
    "FollowGraphNode" should "retrieve followers" in {
        val bridge = newbridge
        val graph = bridge.followgraph("screenname")
        bridge.response = """{"followers": [{"id": 8, "screen_name": "Fred", "name": "Fred Elmquist"}, {"id": 5, "screen_name": "Alton", "name": "Alton Isenhour"}]}"""
        val followers = graph.root.followers()
        followers.size() should be(2)
        followers.get(0).id should be(8)
        followers.get(1).id should be(5)
    }
    
    def newbridge= {
        val br = new Bridge(0) with DummyConnectable
        br.response = """{"id": 9, "screen_name": "John", "name": "John S. Abernathy", "followers": []}"""
        br
    }
}