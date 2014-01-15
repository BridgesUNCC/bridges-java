package bridges
import org.apache.http.client.fluent
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.client.LaxRedirectStrategy
import org.json.simple._
import java.io.IOException


/** Mixin enabling Bridges network connectivity with more user-friendly error
    handling. */
abstract class AnyConnectable() {
    val base: String = "http://localhost:3000"
    val http_connection: fluent.Executor
    
    /** Convert text into a guaranteed non-null JSON format.
        Throws IOException if the result was null
        (meaning that the JSON was empty or malformed) */
    def json(text: String)= {
        Option(JSONValue.parse(text).asInstanceOf[JSONObject]).getOrElse(
            throw new IOException("Received empty JSON response")
        )
    }
    
    /** Execute an Apache Fluent Request.
        Decorates HTTP error tracebacks with urls and server {"error": "..."}
        responses.
        Throws an IOException with URL if the server returns an empty response.
        Returns server response if the status code is >= 400
            but can still throw other exceptions if JSON parsing fails when
            formatting server JSON response
        */
    def http(request: fluent.Request)= {
        // It's possible we need to reimplement this as a ResponseHandler
        
        // Execute the HTTP request
        val response = try {
            http_connection.execute(request).returnResponse()
        } catch {
            // Something happened during the request that we can't handle
            case e: org.apache.http.client.HttpResponseException => {
                println(s"Connection error encountered in processing '$request'\n")
            }
            throw e
        }
            
        /* This is somewhat complicated for getting a string, but:
         *   ...execute(request).returnContent().asString() won't give error codes
         *   ...execute(request).returnResponse().getEntity().asInstanceOf[Stream] won't cast
         */
        var text = io.Source.fromInputStream(
            response.getEntity().getContent()
        ).mkString
        
        // The request succeeded but the server threw an error
        if (response.getStatusLine().getStatusCode() >= 400) {
            println(s"HTTP error encountered in processing '$request'\n")
          
            /* By convention, the server responds {"error": "message"}
             * Try to extract that, but don't obfuscate the real error if
             * parsing json fails */
            
            
            // Try to parse, but if it's null, keep text instead
            text = Option(json(text).asInstanceOf[JSONObject]
                                .get("error").asInstanceOf[String]
                                ).getOrElse(text)
            
            throw new IOException(s"Error on request '$request.'\n" +
                                  s"Server responds: '$text'\n" +
                                  "Consider filing a bug report with this" +
                                  " at http://github.com/SeanTater/bridges")
        }
        
        // Handle empty responses
        if (text == null || text.isEmpty)
            throw new IOException(s"Server returned empty response for '$request'")
        else
            text
    }
    
    /** Execute a simple GET request relative to the server root.
        Omit the leading http://hostname, but include the leading /:
        [good]: /api/followgraph/user/sean
        [bad]: api/followgraph/user/sean
        [bad]: http://myserver:9183/api/followgraph/user/sean  */
    def get(url:String)=
        http(fluent.Request.Get(s"$base$url"))

    /** Execute a simple GET request, implicitly converting to non-null JSON.
        See get() and json() for more information. */
    def getjs(url:String)=
        json(get(url))
    
    /** Execute a simple POST request with relative paths, taking a Scala Map()
        of request parameters. */
    def post(url:String, arguments:Map[String, String])= {
        var req = fluent.Request.Post(s"$base$url")
        var form = fluent.Form.form()
        arguments.foreach { (pair) => form.add(pair._1, pair._2) }
        http(req.bodyForm(form.build()))
    }
        
}

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

/** Executes HTTP requests with api key-based authentication.
    This method uses a different (lax) redirect strategy. */
trait KeyConnectable extends AnyConnectable {
    val http_connection = fluent.Executor.newInstance(
      HttpClientBuilder.create()
        .setRedirectStrategy(new LaxRedirectStrategy())
        .build()
    )
    var api_key = "FILL_IN_PUBLIC_API_KEY"
    
    def authorize(new_key: String)
        api_key = new_key
    
    /** Tacks on an API key to every request before executing. */
    abstract override def http(request: fluent.Request)= {
          
        // TODO: add the API key, as either header or param
        super.http(request)
    }
}

/** Returns predefined strings in place of HTTP requests for stubbing. */
trait DummyConnectable extends AnyConnectable {
    var response: String = ""
    
    abstract override def http(url: fluent.Request)= response
}