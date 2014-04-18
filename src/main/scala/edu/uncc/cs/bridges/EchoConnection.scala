package edu.uncc.cs.bridges

import org.apache.http.client.fluent

/** Returns predefined strings in place of HTTP requests for stubbing. */
class EchoConnection(var response: String) extends AnyConnectable {
	val assignment = 0
	val http_connection = null
    override def http(url: fluent.Request)= response
}