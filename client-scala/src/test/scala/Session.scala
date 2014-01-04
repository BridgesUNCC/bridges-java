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
        val bridge = new Bridge("user", "pass", 0) with DummyConnectable
        bridge.response = """{"id": 098098283} """
        bridge.followgraph("screenname")
        
    }
}