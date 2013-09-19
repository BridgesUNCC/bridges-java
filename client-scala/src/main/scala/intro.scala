package bridges
import dispatch._
import Defaults._
import net.liftweb.json
//import java.nio.file._
import scalax.file.Path
import java.util.Date

/** Container for received JSON entries
  * 
  * refreshed: when the data was last downloaded
  * entries: the list of JSON values received from the server
  */
case class SessionStream(refreshed: Date, entries: List[json.JValue])

/** Container for information persisting between invocations
  *
  * cache: the most recent response from the server for several streams
  * username, password: exactly what the seem, as strings
  */
case class Session(username: String, password: String,
    var cache: Map[String, SessionStream] = Map()) {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
        
    def entries(stream: String)= {
        // TODO: expire
        if ( (cache contains stream) && (cache(stream).refreshed before new Date()))
            cache(stream).entries
        else {
            live(stream) foreach {cache += stream -> SessionStream(new Date, _)}
            cache.get(stream) map {_.entries} getOrElse List()
        }
    }
    
    def live(stream: String) : Option[List[json.JValue]]= {
        val location = dispatch.url(s"http://localhost/$stream?username=$username&password=$password")
        val request = Http.configure(_ setFollowRedirects true)(location OK as.String).option
        return request() map {json.Serialization.read[List[json.JValue]](_)}
    }
    
    def send_state(serial: String) {
        // TODO
        println("Unimplemented: session.send_state")
    }
}

/** Session factory */
object Session {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
    // TODO: Windows + Mac configuration locations
    val config_path = Path.fromString(System.getProperty("user.home")) / ".config" / "bridges"
    
    
    def load(username: String, password: String): Session= {
        if (config_path.exists) {
            try {
                return json.Serialization.read[Session](config_path.chars().mkString)
            } catch {
                case map_e: json.MappingException => None
            }
        }
        return Session(username, password)
    }
    
    def save(session: Session) {
        config_path.write(json.Serialization.writePretty(session))
    }
}

class Retrieve(stream: String, structure: StudentStructure[Any], username: String, password: String) {
    val session = Session.load(username, password)
    
    def get_multiple() {
        for (entry <- session.entries(stream))
            structure.push(entry)
        Session.save(session)
        session.send_state(structure.serialize())
    }
}

object Intro {
    // The student should be able to do this part
    def main(args: Array[String]) {
        val structure = new ReferenceStack[Any]
        val ret = new Retrieve("geolist", structure, "user", "pass")
        ret.get_multiple
    }
}