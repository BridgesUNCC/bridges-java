package edu.uncc.cs.bridges

import org.apache.http.client.fluent


/** Returns predefined strings in place of HTTP requests for stubbing. */
trait DummyConnectable extends AnyConnectable {
    var response: String = ""
    
    abstract override def http(url: fluent.Request)= response
}