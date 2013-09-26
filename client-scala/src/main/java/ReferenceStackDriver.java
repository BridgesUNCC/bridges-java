package bridges;
import bridges.Bridges;
// This would be replaced by whatever the student made
import bridges.ReferenceStack;


/** Run the reference student code.
  * 
  * Students should be able to write this themselves.
  */
public class ReferenceStackDriver {
    public static void main(String[] arguments) {
        ReferenceStack<Object> structure = new ReferenceStack<Object>();
        Bridges ret = new Bridges("geolist", structure, 0, "user", "pass");
        ret.get_multiple(true); // true: be interactive
    }
}