package edu.uncc.cs.bridges

import org.scalatest._

class EchoKey(r: String) extends DummyConnectable with KeyConnectable {
	response = r
	val assignment = 0
	override val http_connection = null
}

class KeyConnectableTest extends FlatSpec with Matchers {
    "KeyConnectable" should "fill in an api key" in {
        val keyconn = new EchoKey("")
        keyconn.api_key = "key"
        keyconn.prepare("") should be (keyconn.base + "/key")
        keyconn.prepare("/$assignment") should be (keyconn.base + "/0/key")
        keyconn.prepare("/possible/path") should be(keyconn.base + "/possible/path/key")
    }
}