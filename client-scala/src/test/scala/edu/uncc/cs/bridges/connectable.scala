package edu.uncc.cs.bridges
import org.scalatest._
import org.apache.http.client.fluent
import org.json.simple._
import java.io.IOException

class Echo(r: String) extends DummyConnectable {
	response = r
	val assignment = 0
	val http_connection = null
}
class EchoKey(r: String) extends DummyConnectable with KeyConnectable {
	response = r
	val assignment = 0
	override val http_connection = null
}

class ConnectableTest extends FlatSpec with Matchers {
    "DummyConnectable" should "respond with a predefined string" in {
        val dummy = new Echo("response")
        dummy.http(fluent.Request.Get("url")) should be("response")
        dummy.get("url") should be("response")
        dummy.post("url", Map()) should be("response")
        dummy.post("url", Map("x" -> "y")) should be("response")
    }
    
    it should "correctly handle empty responses" in {
        val dummy = new Echo("")
        dummy.http(fluent.Request.Get("url")) should be("")
        dummy.get("url") should be("")
        dummy.post("url", Map()) should be("")
        dummy.post("url", Map("x" -> "y")) should be("")
    }
    
    it should "decode JSON" in {
        val dummy = new Echo("""{"x": "y"}""")
        dummy.getjs("url") should not be(null)
        dummy.getjs("url").get("x") should be("y")
    }
    
    it should "complain about empty JSON" in {
        val dummy = new Echo("")
        dummy.get("url") should be("")
        a [IOException] should be thrownBy {
            dummy.getjs("url")
        }
    }
    
    it should "prepare URLs with bases assignment numbers" in {
    	val dummy = new Echo("")
    	dummy.prepare("") should be (dummy.base)
    	dummy.prepare("$assignment") should be (dummy.base + "0")
    }
    
    "KeyConnectable" should "fill in an api key" in {
        val keyconn = new EchoKey("")
        keyconn.api_key = "key"
        keyconn.prepare("") should be (keyconn.base + "/key")
        keyconn.prepare("/$assignment") should be (keyconn.base + "/0/key")
        keyconn.prepare("/possible/path") should be(keyconn.base + "/possible/path/key")
    }
}