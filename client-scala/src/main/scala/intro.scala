import dispatch._
import Defaults._
import net.liftweb.json
//import java.nio.file._
import scalax.file.Path

/** Abstract base of student-implemented structures except Nodes
  *
  * This would be a trait, but it needs to be inherited in Java
  */
abstract class StudentStructure[T] {
    // Students need to subclass this
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
    def push(element: T)
    def serialize() : String
}

/** Abstract base of student-implemented structures except Nodes
  *
  * This would be a trait, but it needs to be inherited in Java
  */
case class StackNode[T](next: StackNode[T], element: T)

abstract class StudentStack[T] extends StudentStructure[T] {
    var root : StackNode[T]
    def serialize()= {
        val out = collection.mutable.ListBuffer[T]()
        var node = root
        while(node != null) {
            node = node.next
            out += node.element
        }
        json.Serialization.write(out.result)
    }
}

/** Pointer-based reference example of a student stack */
class ReferenceStack[T] extends StudentStack[T] {
    // TODO: Replace this with Java
    var root: StackNode[T] = null
    
    def push(element: T) {
        root = new StackNode[T](root, element)
    }
    
    def pop()= {
        if (root == null)
            null
        else {
            val e = root.element
            root = root.next
            e
        }
    }
}

/** Session object capable of being loaded from a JSON file */
case class Session(url: String, refreshed: Date, cache: Map[String, json.JObject]) {
    def entries= {
    }
    def live(stream: String) : List[Any]= {
        val location = url(s"http://localhost/$stream.json")
        val request = Http.configure(_ setFollowRedirects true)(location OK as.String)
        json.parse(request()) match {
            case json.JArray(entries) => entries
            case _ => List(1, 3, 5) // Should come up with something better
        }
    }
}

/** Session factory */
object Session {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
    val config_path = Path.fromString(System.getProperty("user.home")) / ".config" / "bridges"
    
    def load()= {
        // TODO: Windows + Mac configuration locations
        if (config_path.exists) {
            json.Serialization.read[Session](config_path.chars().mkString)
        } else {
            config_path.createFile()
            /* 
             *  Initiate a session
             *  The API here isn't known yet.
             */
        }
    }
    
    def refresh() {
        // STUB
        // Request a new session
        val session_init_url = url("http://localhost/session")
        // This API is not determined yet
        val request = Http(location.POST)
        
    }
    
    def dump(session: Session) {
        config_path.write(json.Serialization.write(session))
    }
}

class Retrieve(stream: String, structure: StudentStructure[Any]) {

    // TODO: Create a session, stream from and to the server using it
    // TODO: Cache
    val session: Session
    
    def getMultiple() {
        for (entry <- live(stream)) structure.push(entry)
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