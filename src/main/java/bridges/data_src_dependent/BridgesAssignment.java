package bridges.data_src_dependent;
/**
 * @brief  A class which fetches assignments previously posted to BRIDGES server and reconstructs the corresponding
 * BRIDGES object / data structure.
 *
 * @author Alec Goncharow
 * @date   12/29/18
 *
 */
public class BridgesAssignment extends DataSource {

	/**Reconstruct a ColorGrid from an existing ColorGrid on the Bridges server
	 *
	 * @return the ColorGrid stored in the bridges server
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 * @param subassignment the ID of the subassignment to get
	 **/
	static bridges.base.ColorGrid getColorGridFromAssignment(String user, int assignment, int subassignment) {

		return null;
    }

    /**Reconstruct a ColorGrid from an existing ColorGrid on the Bridges server
	 *
	 * @return the ColorGrid stored in the bridges server
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 **/
    static bridges.base.ColorGrid getColorGridFromAssignment(String user, int assignment) {
		return getColorGridFromAssignment(user, assignment, 0);
	}

	/***
	 * This function obtains the JSON representation of a particular subassignment.
	 *
	 * @return a string that is the JSON representation of the subassignment as stored by the Bridges server.
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 * @param subassignment the ID of the subassignment to get
	 ***/
	private static String getAssignment(String user, int assignment, int subassignment) {

		return null;
	}
	/***
	 * This function obtains the JSON representation of the first subassignment of an assignment.
	 *
	 * @return a string that is the JSON representation of the subassignment as stored by the Bridges server.
	 * @param user the name of the user who uploaded the assignment
	 * @param assignment the ID of the assignment to get
	 ***/
	private static String getAssignment(String user, int assignment) {
        return getAssignment(user, assignment, 0);
	}
}
