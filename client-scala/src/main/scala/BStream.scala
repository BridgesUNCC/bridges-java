package bridges
import dispatch._
import Defaults._
import net.liftweb.json
import org.json.simple._
import java.util
import java.lang
//import scalax.file.Path
//import com.github.nscala_time.time.Imports._
import org.apache.http.client.fluent

/** Provides a convenient way to read JSON lists from the server. */
class BStream(val bridge: Bridge, val stream: String) extends lang.Iterable[json.JValue] {
    implicit val formats = json.Serialization.formats(json.NoTypeHints)
    
    /** Fetch the latest entries from the server.
      * TODO: Implement caching with HTTPComponents
      * Returns an ArrayList for the sanity of Java developers */
    def fetch() : util.ArrayList[json.JValue]= {
        val request = bridge.get(s"/streams/$stream")
        // Serializing None is ambiguous
        if (request.nonEmpty)
            json.Serialization.read[util.ArrayList[json.JValue]](request.get)
        else
            new util.ArrayList[json.JValue]()
    }
    
    /** Send structure serialization to the server. */
    def send(serial: String) {
        val assignment = bridge.assignment
        val request = bridge.post("assign/$assignment", Map("serial" -> serial))
        if (request == None) {
            println("Status upload failed")
        }
    }
    
    def iterator()= {
        fetch().iterator()
    }
}