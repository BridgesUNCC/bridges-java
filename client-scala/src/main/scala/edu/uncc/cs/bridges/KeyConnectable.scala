package edu.uncc.cs.bridges

import org.apache.http.client.fluent
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.client.LaxRedirectStrategy

/** Executes HTTP requests with api key-based authentication.
    This method uses a different (lax) redirect strategy. */
trait KeyConnectable extends AnyConnectable {
    val http_connection = fluent.Executor.newInstance(
      HttpClientBuilder.create()
        .setRedirectStrategy(new LaxRedirectStrategy())
        .build()
    )
    var api_key = "FILL_IN_PUBLIC_API_KEY"
    
    /** Tacks on an API key to every request before executing. */
    abstract override def prepare(url: String)= {
        super.prepare(url) + s"/$api_key"
    }
}