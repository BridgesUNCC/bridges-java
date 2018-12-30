package bridges.data_src_dependent;

/**
 * @brief Struct like class used to represent Bridges Assignments as they are returned from the server
 *
 * @author Alec Goncharow
 */
public class BridgesAssignment extends DataSource {
    public String username, assignmentName, assignmentNumber, title, description, vistype, dateCreated;
    public double assignmentID;
    public BridgesAssignmentData[] data;
}
