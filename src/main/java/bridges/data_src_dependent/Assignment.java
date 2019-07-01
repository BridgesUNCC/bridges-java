package bridges.data_src_dependent;

/**
 * @brief Struct like class used to represent Bridges Assignments as they are returned from the server
 *
 * @author Alec Goncharow
 */
public class Assignment extends DataSource {
	public String username, title, assignmentNumber, subAssignment, description, vistype, dateCreated, assignment_type;
	public double assignmentID;
	public AssignmentData[] data;
}
