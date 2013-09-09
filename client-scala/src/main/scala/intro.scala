import dispatch._
import dispatch.url
import Defaults._
import Http._
import net.liftweb.json._
import net.liftweb.json.Serialization.{read, write}

abstract class StudentStructure[T] {
    // Students need to subclass this
    implicit val formats = Serialization.formats(NoTypeHints)
    def push(element: T)
    def serialize() : String
}

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
        write(out.result)
    }
}

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


class Retrieve(stream: String, structure: StudentStructure[Any]) {

    // TODO: Create a session, stream from and to the server using it
    // TODO: Cache
    def session() {
        val init_session_url = url
    }
    
    def live(stream: String) : List[Any]= {
        val location = url(s"http://localhost/$stream.json")
        val request = Http.configure(_ setFollowRedirects true)(location OK as.String)
        parse(request()) match {
            case JArray(entries) => entries
            case _ => List(1, 3, 5) // Should come up with something better
        }
    }
    
    def getMultiple() {
        for (entry <- live(stream)) structure.push(entry)
    }
}

object Intro {
    // The student should be able to do this part
    def main(args: Array[String]) {
        val is = new ReferenceStack[Any]
        val ret = new Retrieve("geolist", is)
        ret.getMultiple
    }
}