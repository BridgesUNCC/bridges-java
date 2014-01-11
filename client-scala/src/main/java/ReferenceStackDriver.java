package bridges;
import bridges.BStream;
// This would be replaced by whatever the student made
import bridges.ReferenceStack;
import org.json.simple.*;

/** Run the reference student code.
  * 
  * Students should be able to write this themselves.
  */
public class ReferenceStackDriver {
    public static void main(String[] arguments) {
        ReferenceStack<JSONValue> structure = new ReferenceStack<JSONValue>();
        Bridge bridge = new Bridge(0);
        BStream ret = bridge.stream("geolist");
        for (Object o : ret.fetch())
            // If you want to do something more interesting with it, cast it:
            structure.push((JSONValue) o);
    }
}