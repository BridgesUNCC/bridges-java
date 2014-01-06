package bridges
import org.apache.http.client.fluent
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
    
    def http(request: fluent.Request)= {
        val response = http_connection.execute(request).returnContent().asString()
        assert(response != null)
        if (response == null || response.isEmpty)
            throw new IOException("Server returned empty response")
        else
            response
    }
    
    def get(url:String)= {
        http(fluent.Request.Get(s"$base$url"))
    }

    
    def getjs(url:String)=
        json(get(url))
        
    def post(url:String, arguments:Map[String, String])= {
        var req = fluent.Request.Post(s"$base$url")
        var form = fluent.Form.form()
        arguments.foreach { (pair) => form.add(pair._1, pair._2) }
        http(req.bodyForm(form.build()))
    }
        
}

/** Executes HTTP requests with form-based authentication. */
trait FormConnectable extends AnyConnectable {
    val http_connection = fluent.Executor.newInstance()
    // Get a csrf token, but define it as empty first
    //  so that the overridden http will work
  
    lazy val csrf_token = {
        val r = getjs("/api/csrf").get("csrf_token").asInstanceOf[String]
        // Then, login
        post("/users/session", Map("username" -> username, "password" -> password))
        r
    }
    
    abstract override def http(request: fluent.Request)= {
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