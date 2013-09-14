package bridges
import dispatch._
import Defaults._
import net.liftweb.json
//import java.nio.file._
import scalax.file.Path


/** Session object capable of being loaded from a JSON file */
case class Session(url: String, var cache: Map[String, List[json.JValue]]) {
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
}

/** Session factory */
object Session {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
    val config_path = Path.fromString(System.getProperty("user.home")) / ".config" / "bridges"
    
    def new_session()= {
            /* 
             *  Initiate a session
             *  The API here isn't known yet; STUB, TODO
             */
        Session("Example", Map[String, List[json.JValue]]())
    }
    
    def load()= {
        // TODO: Windows + Mac configuration locations
        if (config_path.exists) {
            try {
                json.Serialization.read[Session](config_path.chars().mkString)
            } catch {
                case mape: json.MappingException => new_session()
            }
        } else {
            config_path.createFile()
            new_session()
        }
    }
    
    def refresh() {
        // STUB
        // Request a new session
        val session_init_url = url("http://localhost/session")
        // This API is not determined yet
        val request = Http(session_init_url.POST)
        
    }
    
    def save(session: Session) {
        config_path.write(json.Serialization.write(session))
    }
}

class Retrieve(stream: String, structure: StudentStructure[Any]) {
    val session = Session.load()
    
    def getMultiple() {
        for (entry <- session.entries(stream))
            structure.push(entry)
        Session.save(session)
    }
}

object Intro {
    // The student should be able to do this part
    def main(args: Array[String]) {
        val session = Session.load()
        val is = new ReferenceStack[Any]
        val ret = new Retrieve("geolist", is)
        ret.getMultiple
    }
}