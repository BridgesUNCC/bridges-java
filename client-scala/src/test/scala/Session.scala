import org.scalatest._
import bridges._

class BStreamStreamTest extends FlatSpec with Matchers {
    "BStream" should "load stream data from JSON" in {
    }
    
    it should "load an empty ArrayList when receiving an empty response" in {
    }
}
    
class FollowGraphTest extends FlatSpec with Matchers {
    "FollowGraph" should "die when loaded without anything" in {
        val bridge = new Bridge("user", "pass", 0)
        a [Exception] should be thrownBy {
          val graph = bridge.followgraph("screenname")
        }
    }
    
    "FollowGraph" should "load from a screenname via JSON" in {
        val graph = bridge.followgraph("screenname")
        graph.root.id should be(9)
        graph.root.screen_name should be("John")
        graph.root.name should be("John S. Abernathy")
        
    }
    
    "FollowGraphNode" should "retrieve followers" in {
        val graph = bridge.followgraph("screenname")
        bridge.response = """{"followers": [{"id": 8, "screen_name": "Fred", "name": "Fred Elmquist"}, {"id": 5, "screen_name": "Alton", "name": "Alton Isenhour"}]}"""
        val followers = graph.root.followers
        followers.get(0).id should be(8)
        followers.get(1).id should be(5)
    }
    
    def bridge= {
        val br = new Bridge("user", "pass", 0) with DummyConnectable
        br.response = """{"id": 9, "screen_name": "John", "name": "John S. Abernathy"}"""
        br
    }
}