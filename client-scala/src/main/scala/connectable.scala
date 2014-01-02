package bridges
import org.apache.http.client.fluent

/** Executes HTTP requests with form-based authentication. */
trait FormConnectable {
    val username: String
    val password: String
    val http_connection = fluent.Executor.newInstance()
    http(
        fluent.Request.Post("http://localhost:3000/users/session").bodyForm(
            fluent.Form.form().add("username", username).add("password", password).build()
        )
    )
    
    def http(url: fluent.Request)= {
        val res = http_connection.execute(url).returnContent().asString()
        if (res == null)
            None
        else
            Some(res)
    }
}

/** Executes HTTP requests with basic authentication. */
trait BasicConnectable {
    val username: String
    val password: String
    val http_connection = fluent.Executor.newInstance().auth(username, password)
    def http(url: fluent.Request)= {
        val res = http_connection.execute(url).returnContent().asString()
        if (res == null)
            None
        else
            Some(res)
    }
}

/** Returns predefined strings in place of HTTP requests for stubbing. */
trait DummyConnectable {
    var response: String = _
    
    def http(url: fluent.Request)= Some(response)
}