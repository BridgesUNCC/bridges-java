package edu.uncc.cs.bridges

import org.apache.http.client.fluent
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.client.LaxRedirectStrategy


/** Executes HTTP requests with form-based authentication.
    This method uses a different (lax) redirect strategy. */
trait FormConnectable extends AnyConnectable {
    val http_connection = fluent.Executor.newInstance(
      HttpClientBuilder.create()
        .setRedirectStrategy(new LaxRedirectStrategy())
        .setDefaultCookieStore(new DiskCookieStore())
        .build()
    )
    // The CSRF token doesn't change, but can't be val because
    //    http() doesn't function properly at this point
    //    and lazy val will send you in loops
    var csrf_token = ""
    
    /** Implicitly logs in upon the first http request.
        Unless the session is still valid.
        Also requests a new CSRF token regardless. */
    abstract override def http(request: fluent.Request)= {
        // Once per program, get a new CSRF token
        if (csrf_token.isEmpty) {
          /* We need to getjs() but getjs() calls http().
           * Prevent a loop by keeping it from branching this way */
          csrf_token = " "
          
          /* Get CSRF token, find out if we need to sign in again */
          val session_status = getjs("/api/csrf")
          csrf_token = session_status.get("csrf_token").asInstanceOf[String]
          val user_signed_in = session_status.get("user_signed_in").asInstanceOf[Boolean]
          
          if (! user_signed_in) {
              val username = readLine("Username: ")
              // TODO: System.console is null in Eclipse. How to workaround password?
              val password = readLine("Password: ")
              post("/users/login", Map("user[email]" -> username, "user[password]" -> password))
          }
        }
          
        // Tack on the CSRF token (or the first time, " ") to every request
        super.http(request.addHeader("X-CSRF-Token", csrf_token))
    }
}