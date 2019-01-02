package bridges.data_src_dependent;
/**
 * @brief Struct like class used to represent Bridges Assignment Data as returned from the server
 *
 * @author Alec Goncharow
 */
public class BridgesAssignmentData {
	public String visual, title, description, coord_system_type, encoding;
	public Object[] nodes;
	public int[] dimensions;
}
