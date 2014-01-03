package bridges
import org.apache.http.client.fluent

abstract class AnyConnectable() {
    val username: String
    val password: String
    val base: String = "http://localhost:3000"
    val http_connection: fluent.Executor
    
    def http(request: fluent.Request)=
        Option(http_connection.execute(request).returnContent().asString())
    
    def get(url:String)=
        http(fluent.Request.Get(s"$base$url"))
        
    def post(url:String, arguments:Map[String, String])= {
        var req = fluent.Request.Post(s"$base$url")
        var form = fluent.Form.form()
        for (pair <- arguments) form = form.add(pair._1, pair._2)
        http(req.bodyForm(form.build()))
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
    
    override def http(url: fluent.Request)= Some(response)
}