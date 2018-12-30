package bridges.data_src_dependent;

import bridges.base.DataStruct;
import bridges.connect.Bridges;

import java.util.List;

/**
 * TODO Think about if it is worth it to capture an entire assignment, storing subassignments in a List of some sort
 *
 * @author Alec Goncharow
 * @date   12/29/18
 *
 */
public class BridgesAssignment extends DataSource {
    private String username, assignmentName, assignmentNumber, title, description, vistype;
    private int assignmentID;
    private List<DataStruct> assignments;
    public BridgesAssignment() {}
}
