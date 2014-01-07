package bridges
import org.apache.http.client.fluent
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.client.LaxRedirectStrategy
import org.json.simple._
import java.io.IOException

abstract class AnyConnectable() {
    val username: String
    val password: String
    val base: String = "http://localhost:3000"
    val http_connection: fluent.Executor
    
    def json(text: String)= {
        Option(JSONValue.parse(text).asInstanceOf[JSONObject]).getOrElse(
            throw new IOException("Received empty JSON response")
        )
    }
    
    /** Execute an Apache Fluent Request. */
    def http(request: fluent.Request)= {
        val response = try {
            http_connection.execute(request).returnContent().asString()
        } catch {
            case e: org.apache.http.client.HttpResponseException => {
                println(s"Error encountered in processing '$request'\n")
            }
            throw e
        }
        if (response == null || response.isEmpty)
            throw new IOException(s"Server returned empty response for '$request'")
        else
            response
    }
    
    def get(url:String)=
        http(fluent.Request.Get(s"$base$url"))

    
    def getjs(url:String)=
        json(get(url))
        
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
      HttpClientBuilder.create().setRedirectStrategy(
        new LaxRedirectStrategy()
      ).build()
    )
    // The CSRF token doesn't change, but can't be val because
    //    http() doesn't function properly at this point
    //    and lazy val will send you in loops
    var csrf_token = ""
    
    abstract override def http(request: fluent.Request)= {
        if (csrf_token.isEmpty) {
          csrf_token = " " // Prevent a loop
          csrf_token = getjs("/api/csrf").get("csrf_token").asInstanceOf[String]
          post("/users/login", Map("user[email]" -> username, "user[password]" -> password))
        }
        super.http(request.addHeader("X-CSRF-Token", csrf_token))
    }
    
}

/** Executes HTTP requests with basic authentication. */
trait BasicConnectable extends AnyConnectable {
    val http_connection = fluent.Executor.newInstance().auth(username, password)
}

/** Returns predefined strings in place of HTTP requests for stubbing. */
trait DummyConnectable extends AnyConnectable {
    var response: String = ""
    
    abstract override def http(url: fluent.Request)= response
}