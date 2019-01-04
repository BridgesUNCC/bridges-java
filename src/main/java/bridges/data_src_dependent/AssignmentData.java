package bridges.data_src_dependent;
/**
 * @brief Struct like class used to represent Bridges Assignment Data as returned from the server
 *
 * @author Alec Goncharow
 */
public class AssignmentData {
	public String coord_system_type, encoding;
	public Object[] nodes;
	boolean map_overlay;
	public int[] dimensions;
}
