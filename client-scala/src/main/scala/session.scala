package bridges
import dispatch._
import Defaults._
import net.liftweb.json
//import java.nio.file._
import scalax.file.Path
import com.github.nscala_time.time.Imports._

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
trait Connectable {
    def http(url: dispatch.Req)= {
         (Http.configure(_ setFollowRedirects true)(url OK as.String).option) ()
    }
}

/** Contains information persisting between invocations.
  *
  * cache: the most recent response from the server for several streams
  * username, password: exactly what the seem, as strings
  * This is a case class in order to automate JSON serialization
  */
class Session(val username: String, val password: String, val assignment: Int,
    var cache: Map[String, SessionStream] = Map()) {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)

    def http(url: dispatch.Req) : Option[String]= None
        
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
        val location = dispatch.url(s"http://localhost/$stream?username=$username&password=$password")
        val request = http(location)
        // Serializing None is ambiguous
        return request map {
            json.Serialization.read[List[json.JValue]](_)
        }
    }
    
    /** Send structure serialization to the server. */
    def send_state(serial: String) {
        val location = dispatch.url(s"http://localhost/assign/$assignment").
            POST.addParameter("username", username).
            addParameter("password", password).
            addParameter("serial", serial)
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
object Session {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
    // TODO: Windows + Mac configuration locations
    val config_path = Path.fromString(System.getProperty("user.home")) / ".config" / "bridges"
    
    
    def load(username: String, password: String, assignment: Int): Session= {
        if (config_path.exists) {
            try {
                var session = json.Serialization.read[Session](config_path.chars().mkString)
                session = new Session(username, password, assignment, session.cache)
            } catch {
                case map_e: json.MappingException => None
            }
        }
        return new Session(username, password, assignment)
    }
    
    def save(session: Session) {
        config_path.write(json.Serialization.writePretty(session))
    }
}