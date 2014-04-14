package edu.uncc.cs.bridges

import org.apache.http.client.fluent
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.client.LaxRedirectStrategy

/** Executes HTTP requests with API-Key-based authentication. */
trait KeyConnectable extends AnyConnectable {
    val http_connection = fluent.Executor.newInstance(
      HttpClientBuilder.create()
        .setRedirectStrategy(new LaxRedirectStrategy())
        .build()
    )
    
    /** This is the key sent to the server on each request. */
    var api_key = ""
    
    /** Set the API key to be used on the next request.
     *  This pluggable trait does not connect to the internet.
     */
    def login(key: String) { api_key = key; }
    
    /** Proxy to login(String) that uses an Integer instead.
     */
    def login(key: Int) { api_key = key.toString(); }
    
    /** Tacks on an API key to every request before executing it.
     *  See AnyConnectable.prepare also.
     */
    abstract override def prepare(url: String)= {
        super.prepare(url) + s"?apikey=$api_key"
    }
}