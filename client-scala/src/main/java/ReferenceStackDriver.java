package bridges;
import bridges.Retrieve;
import bridges.ReferenceStack; // This would be replaced by whatever the student made

// The student should be able to create this file

public class ReferenceStackDriver {
    public static void main(String[] arguments) {
        ReferenceStack<Object> structure = new ReferenceStack<Object>();
        Retrieve ret = new Retrieve("geolist", structure, "user", "pass");
        ret.get_multiple();
    }
}