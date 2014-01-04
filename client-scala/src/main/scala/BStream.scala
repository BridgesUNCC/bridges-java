package bridges
import dispatch._
import Defaults._
import org.json.simple._
import java.util
import java.lang
//import scalax.file.Path
//import com.github.nscala_time.time.Imports._
import org.apache.http.client.fluent

/** Provides a convenient way to read JSON lists from the server.
    TODO: It's not clear if inheriting from JSONArray is best. */
class BStream(val bridge: Bridge, val stream: String) {
    
    /** Fetch the latest entries from the server.
      * TODO: Implement caching with HTTPComponents
      * Returns an ArrayList for the sanity of Java developers */
    def fetch()= {
        val request = bridge.get(s"/streams/$stream")
        // Serializing None is ambiguous
        if (request.nonEmpty)
            JSONValue.parse(request.get).asInstanceOf[JSONArray]
        else
            new JSONArray()
    }
    
    /** Send structure serialization to the server. */
    def send(serial: String) {
        val assignment = bridge.assignment
        val request = bridge.post("assign/$assignment", Map("serial" -> serial))
        if (request == None) {
            println("Status upload failed")
        }
    }
    
    //def iterator()= {
    //    fetch().iterator()
    //}
}