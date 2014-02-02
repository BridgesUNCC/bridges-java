package edu.uncc.cs.bridges
import org.scalatest._
import org.apache.http.client.fluent
import java.io.IOException

class DummyConnectableTest extends FlatSpec with Matchers {
    "DummyConnectable" should "respond with a predefined string" in {
        val dummy = new EchoConnection("response")
        dummy.http(fluent.Request.Get("url")) should be("response")
        dummy.get("url") should be("response")
        dummy.post("url", Map()) should be("response")
        dummy.post("url", Map("x" -> "y")) should be("response")
    }
    
    it should "correctly handle empty responses" in {
        val dummy = new EchoConnection("")
        dummy.http(fluent.Request.Get("url")) should be("")
        dummy.get("url") should be("")
        dummy.post("url", Map()) should be("")
        dummy.post("url", Map("x" -> "y")) should be("")
    }
    
    it should "decode JSON" in {
        val dummy = new EchoConnection("""{"x": "y"}""")
        dummy.getjs("url") should not be(null)
        dummy.getjs("url").get("x") should be("y")
    }
    
    it should "complain about empty JSON" in {
        val dummy = new EchoConnection("")
        dummy.get("url") should be("")
        a [IOException] should be thrownBy {
            dummy.getjs("url")
        }
    }
    
    it should "return server error when provided" in {
        val dummy = new EchoConnection("""{"error": "message"}""")
        val error = evaluating {
          dummy.getjs("/url")
        } should produce [IOException]
        error.getMessage() should be ("Error on server: message")
    }
    
    it should "prepare URLs with bases assignment numbers" in {
    	val dummy = new EchoConnection("")
    	dummy.prepare("") should be (dummy.base)
    	dummy.prepare("$assignment") should be (dummy.base + "0")
    }
}