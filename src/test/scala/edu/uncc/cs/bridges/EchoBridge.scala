package edu.uncc.cs.bridges
import org.scalatest._
import org.apache.http.client.fluent._

class EchoBridge(var response: String) extends Bridge(0) {
	override val http_connection = null
    override def http(url: Request)= response
}