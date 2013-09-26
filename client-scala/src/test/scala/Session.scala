import org.scalatest._
import bridges.Session

class SessionTest extends FlatSpec with Matchers {
    "Session" should "be able to initialize without a cache" in {
        var s = new Session("username", "password", assignment=0)
        s.cache should be (Map())
    }
}