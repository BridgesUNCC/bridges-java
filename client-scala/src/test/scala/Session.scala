import org.scalatest._
import bridges._

class StreamSessionTest extends FlatSpec with Matchers {
    "StreamSession" should "be able to initialize without a cache" in {
        val s = new StreamSession("", "", assignment=0) with DummyConnectable
        s.cache should be (Map())
    }
    
    "StreamSession" should "fail gracefully for status uploads" in {
        val s = new StreamSession("", "", assignment=0) with DummyConnectable
        // Should not throw
        s.send_state("sample serial")
    }
}
    
class InteractiveSessionTest extends FlatSpec with Matchers {
}