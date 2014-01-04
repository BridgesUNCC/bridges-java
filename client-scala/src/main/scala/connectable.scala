package bridges
import org.apache.http.client.fluent
import org.json.simple._
import java.io.IOException

abstract class AnyConnectable() {
    val username: String
    val password: String
    val base: String = "http://localhost:3000"
    val http_connection: fluent.Executor
    
    def json[T](text: String)= {
        val js = JSONValue.parse(text).asInstanceOf[T]
        if (js == null)
            throw new IOException("Server returned malformed or empty JSON")
        else
            js
    }
    
    def http(request: fluent.Request, must:Boolean=true)= {
        val response = Option(http_connection.execute(request).returnContent().asString())
        if (response.isEmpty && must)
            throw new IOException("Server returned empty response")
        else
            response
    }
    
    def get(url:String, must:Boolean=true)=
        http(fluent.Request.Get(s"$base$url"), must)
        
    def post(url:String, arguments:Map[String, String], must:Boolean=true)= {
        var req = fluent.Request.Post(s"$base$url")
        var form = fluent.Form.form()
        for (pair <- arguments) form = form.add(pair._1, pair._2)
        http(req.bodyForm(form.build()), must)
    }
        
}

/** Executes HTTP requests with form-based authentication. */
trait FormConnectable extends AnyConnectable {
    val http_connection = fluent.Executor.newInstance()
    post("/users/session", Map("username" -> username, "password" -> password))
    
}

/** Executes HTTP requests with basic authentication. */
trait BasicConnectable extends AnyConnectable {
    val http_connection = fluent.Executor.newInstance().auth(username, password)
}

/** Returns predefined strings in place of HTTP requests for stubbing. */
trait DummyConnectable extends AnyConnectable {
    var response: String = _
    
    override def http(url: fluent.Request, must:Boolean=false)= Some(response)
}