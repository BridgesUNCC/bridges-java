package bridges;
import bridges.BStream;
// This would be replaced by whatever the student made
import bridges.ReferenceStack;
import scala.net.liftweb.json;


/** Run the reference student code.
  * 
  * Students should be able to write this themselves.
  */
public class ReferenceStackDriver {
    public static void main(String[] arguments) {
        ReferenceStack<Object> structure = new ReferenceStack<Object>();
        Bridge bridge = new Bridge("user", "pass", 0);
        BStream ret = bridge.stream("geolist");
        for (json.JValue jv : ret)
            structure.push(jv);
    }
}