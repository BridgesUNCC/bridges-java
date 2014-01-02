import org.scalatest._
import bridges.StreamSession

class SessionTest extends FlatSpec with Matchers {
    "Session" should "be able to initialize without a cache" in {
        val s = new StreamSession("", "", assignment=0)
        s.cache should be (Map())
    }
    
    "Session" should "fail gracefully for status uploads" in {
        val s = new StreamSession("", "", assignment=0)
        // Should not throw
        s.send_state("sample serial")
    }
}