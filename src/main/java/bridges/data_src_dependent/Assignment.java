package bridges.data_src_dependent;

/**
 * @brief Struct like class used to represent Bridges Assignments as 
 *		they are returned from the server
 *
 *  An assignment has user name, title, assignment number, sub assignment number,
 *  description, visualization type, creation data, and assignment type, 
 *	assignment id and the assignment content.
 *
 * @author Alec Goncharow
 * @date 12/26/20
 */
public class Assignment {
	public String username, title, assignmentNumber, 
			subAssignment, description, vistype, dateCreated, assignment_type;
	public double assignmentID;
	public AssignmentData[] data;
}
