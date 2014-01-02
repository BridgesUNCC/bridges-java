package bridges
import dispatch._
import Defaults._
import net.liftweb.json
//import java.nio.file._
import scalax.file.Path
import com.github.nscala_time.time.Imports._
import org.apache.http.client.fluent

/** Contains received JSON entries.
  * 
  * refreshed: when the data was last downloaded
  * entries: the list of JSON values received from the server
  */
case class SessionStream(refreshed: DateTime, entries: List[json.JValue])

/** Executes HTTP requests.
  *
  * This is so that it can be easily stubbed out
  */
trait SessionConnectable {
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

/** Contains information persisting between invocations.
  *
  * cache: the most recent response from the server for several streams
  * username, password: exactly what the seem, as strings
  * This is a case class in order to automate JSON serialization
  */
class StreamSession(val username: String, val password: String, val assignment: Int,
    var cache: Map[String, SessionStream] = Map()) {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)

    def http(url: fluent.Request) : Option[String]= None
        
    /** Request the latest entries, cached if fresh, or if fetch() fails. */
    def entries(stream: String)= {
        // TODO: expire
        if ( cache.contains(stream) &&
            (cache(stream).refreshed + 15.minutes).isAfterNow )
            cache(stream).entries
        else {
            fetch(stream) foreach {
                cache += stream -> SessionStream(DateTime.now, _)
            }
            cache.get(stream) map {_.entries} getOrElse List()
        }
    }
    
    /** Fetch the latest entries from the server. */
    def fetch(stream: String) : Option[List[json.JValue]]= {
        val location = fluent.Request.Get(s"http://localhost/streams/$stream")
        val request = http(location)
        // Serializing None is ambiguous
        return request map {
            json.Serialization.read[List[json.JValue]](_)
        }
    }
    
    def save() {
        StreamSession.config_path.write(json.Serialization.writePretty(this))
    }
    
    /** Send structure serialization to the server. */
    def send_state(serial: String) {
        val location = fluent.Request.
            Post(s"http://localhost/assign/$assignment").bodyForm(
                fluent.Form.form().add("serial", serial).build()
            )
        val request = http(location)
        if (request == None) {
            println("Status upload failed")
        }
    }
}

/** Generate sessions from JSON files and save them back.
  * 
  * A username and password are necessary for login to the server (currently)
  */
object StreamSession {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
    // TODO: Windows + Mac configuration locations
    val config_path = Path.fromString(System.getProperty("user.home")) / ".config" / "bridges"
    
    
    def load(username: String, password: String, assignment: Int): StreamSession= {
        if (config_path.exists) {
            try {
                var session = json.Serialization.read[StreamSession](config_path.chars().mkString)
                session = new StreamSession(username, password, assignment, session.cache)
            } catch {
                case map_e: json.MappingException => None
            }
        }
        return new StreamSession(username, password, assignment)
    }
}