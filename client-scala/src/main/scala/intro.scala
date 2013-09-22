package bridges
import dispatch._
import Defaults._
import net.liftweb.json
//import java.nio.file._
import scalax.file.Path
import scala.reflect.runtime.universe._ // TypeTags

/** Session object capable of being loaded from a JSON file */
case class Session(var cache: Map[String, List[json.JValue]]) {
    def entries(stream: String)= {
        // TODO: expire
        if (cache.contains(stream)) {
            cache(stream)
        } else {
            cache += stream -> live(stream)
            cache
        }
    }
    
    def live(stream: String) : List[json.JValue]= {
        val location = dispatch.url(s"http://localhost/$stream.json")
        val request = Http.configure(_ setFollowRedirects true)(location OK as.String)
        json.parse(request()) match {
            case json.JArray(entries) => entries
            case _ => List() // Should come up with something better
        }
    }
    
    def send_state(serial: String) {
        // TODO
        println("Unimplemented: session.send_state")
    }
}

/** Session factory */
object Session {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
    val config_path = Path.fromString(System.getProperty("user.home")) / ".config" / "bridges"
    
    def new_session()= {
            /* 
             *  Initiate a session
             *  The API here isn't known yet; TODO
             */
        Session(Map[String, List[json.JValue]]())
    }
    
    def load()= {
        // TODO: Windows + Mac configuration locations
        if (config_path.exists) {
            try {
                json.Serialization.read[Session](config_path.chars().mkString)
            } catch {
                case map_e: json.MappingException => new_session()
            }
        } else {
            config_path.createFile()
            new_session()
        }
    }
    
    def refresh() {
        // STUB
        // Request a new session
        println("Unimplemented: Session.refresh()")
        val session_init_url = url("http://localhost/session")
        // This API is not determined yet
        val request = Http(session_init_url.POST)
        
    }
    
    def save(session: Session) {
        config_path.write(json.Serialization.writePretty(session))
    }
}

class Retrieve(stream: String, structure: StudentStructure[Any]) {
    val session = Session.load()
    
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
        val is = new ReferenceStack[Any]
        val ret = new Retrieve("geolist", is)
        ret.get_multiple()
    }
}