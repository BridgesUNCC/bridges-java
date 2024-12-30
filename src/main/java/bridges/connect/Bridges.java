package bridges.connect;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.json.simple.JSONValue;

import bridges.base.*;
import bridges.data_src_dependent.*;
import bridges.validation.*;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.xml.crypto.Data;


/**
 * 	@brief The Bridges class is the main class that provides interfaces to datasets,
 *	maintains user and assignment information, and connects to the Bridges server.
 *
 * 	The Bridges class is responsible  for initializing the Bridges system, specifying
 * 	parameters (user id, assignment id, title, description, data structure
 *	type, etc) for the student assignment, generating the data structure representation
 *	and transmission to the Bridges server. In addition, it provides interfaces to
 *	a number of real-world datasets, that makes it easy to access the data for use
 * 	algorithms/data structure assignments. <br>
 *
 *  <b>Datasets.</b> The datasets that are currently supported through the BRIDGES API
 *	include USGS Earthquake Data, IMDB Actor/Movie Data (2 versions), Gutenberg Book
 *	Collection Meta Data, a Video Game Dataset and Shakespeare Dataset. More information
 *	is found in the respective methods (below) and at <p>
 *	https://bridgesuncc.github.io/datasets.html <p>
 *
 *	A typical Bridges program includes creating the Bridges object, followed by creation
 *      of the data structure by the user, assigning visual attributes to elements of the
 *	data structure, followed by specification of the data structure type  and the
 *	call to visualize the data structure (Bridges::setDataStructure() and visualize()
 *	methods).
 *
 * If the FORCE_BRIDGES_APIKEY environment variable is set,
 * use the environment variable as APIkey in all cases.
 *
 * If the FORCE_BRIDGES_USERNAME environment variable is set,
 * use the environment variable as username in all cases.
 *
 * If the FORCE_BRIDGES_ASSIGNMENT environment variable is set,
 * use the environment variable as assignment number in all cases.
 *
 * If the FORCE_BRIDGES_APISERVER environment variable is set,
 * use the environment variable as API server in all cases.
 *
 * If the FORCE_BRIDGES_DATADEBUG environment variable is set,
 * output debug information about access data sources.
 *
 * 	@author Sean Gallagher, Kalpathi Subramanaian, Mihai Mehedint, David Burlinson.
 *
 * 	@date  1/16/17, 5/19/17
 *
 *	\sa Tutorial examples at <br>
 *	https://bridgesuncc.github.io/tutorials/Overview.html
 */

public class Bridges {

	private static int assignmentDecimal = 0;
	private  Connector connector;
	private int element_array_size;
	private static boolean json_flag = false;
	private static boolean label_flag = false;
	private static boolean post_link_url_flag = true;
	private static boolean map_as_json = false;
	private static int assignment;
	private static int assignment_part;
	private static String key;
	private static Boolean debug_flag = false;   // debug mode
	private static String userName, vis_type,
			title = "", description = "";
	private static Integer MaxTitleSize = 200,
						   MaxDescrSize = 1000;
	private static String[] projection_options = {"cartesian", "albersusa", "equirectangular", "window"};
	private static String map = new String();
	private static Boolean map_overlay = false;	// default to no map overlay
	private static double[] window;
	private static String display_mode = "slide"; // default to slide (vs stack)
	private static String coord_system_type = projection_options[0];	// default to Cartesian space
	
	private DataStruct ds_handle = null;		// data structure handle

	//  string constants  for use in constructing JSON
	//  representation of the data structure

	private static String
	QUOTE = "\"",
	COMMA = ",",
	COLON = ":",
	OPEN_CURLY = "{",
	CLOSE_CURLY = "}",
	OPEN_PAREN = "(",
	CLOSE_PAREN = ")",
	OPEN_BOX = "[",
	CLOSE_BOX = "]";
	/**
	 *
	 *	Constructors
	 *
	 * If the FORCE_BRIDGES_APIKEY environment variable is set,
	 * use the environment variable as APIkey in all cases.
	 *
	 * If the FORCE_BRIDGES_USERNAME environment variable is set,
	 * use the environment variable as username in all cases.
	 *
	 * If the FORCE_BRIDGES_ASSIGNMENT environment variable is set,
	 * use the environment variable as assignment number in all cases.
	 *
	 */
	public Bridges() {
		super();
		connector = new Connector();
		assignment_part = 0;
		init(0, "", "");
	}

	/**
	 *	@brief get the current data source object being used by BRIDGES
	 *
	 *	@return data source object
	 */
	public DataSource getDataSource () {
		return new DataSource(this);
	}

	/**
	 * Initialize Bridges (Constructor)
	 *
	 * If the FORCE_BRIDGES_APIKEY environment variable is set,
	 * use the environment variable as APIkey in all cases.
	 *
	 * If the FORCE_BRIDGES_USERNAME environment variable is set,
	 * use the environment variable as username in all cases.
	 *
	 * If the FORCE_BRIDGES_ASSIGNMENT environment variable is set,
	 * use the environment variable as assignment number in all cases.
	 *
	 * @param assignment this is the assignmen id (integer)
	 * @param appl_id    this is the appl authentication key(from the Bridges account)
	 * @param username   this is the username (from the Bridges account)
	 *
	 */
	public Bridges(int assignment, String username, String appl_id) {
		this();
		init(assignment, username, appl_id);
	}

	/**
	 *
	 * Initialize Bridges with assignment number, user name and application id
	 *
	 * @param assignment this is the assignmen id (integer)
	 * @param appl_id    this is the appl authentication key(from the Bridges account)
	 * @param username   this is the username (from the Bridges account)
	 *
	 */
	protected void init(int assignment, String username, String appl_id) {
		String envAssignment = System.getenv("FORCE_BRIDGES_ASSIGNMENT");
		String envApiKey = System.getenv("FORCE_BRIDGES_APIKEY");
		String envUser = System.getenv("FORCE_BRIDGES_USERNAME");
		if (envAssignment == null) {
			Bridges.setAssignment(assignment);
		}
		else {
			Bridges.setAssignment(Integer.parseInt(envAssignment));
		}
		Bridges.key = envApiKey != null ? envApiKey : appl_id;
		Bridges.userName = envUser != null ? envUser : username;

		if (debug_flag) {
			System.err.println ("Bridges.init: assignment=" + Bridges.assignment + " username=" + Bridges.userName + " apikey=" + Bridges.key);
		}
	}


	/**
	 * @brief Change the title of the assignment.
	 *
	 * The title is capped at MaxTitleSize characters.
	 *
	 * @param title title used in the visualization;
	 *
	 */
	public void setTitle(String title) {
		if (title.length() > MaxTitleSize) {
			System.out.println ("Visualization Title restricted to " + MaxTitleSize + " characters."
				+ " Truncating title..");
			this.title = title.substring(0, MaxTitleSize);
		}
		else
			this.title = title;
	}

	/**
	 * @brief Change the textual description of the assignment.
	 *
	 * This description is capped at MaxDescrSize characters.
	 *
	 * @param description description to annotate the visualization;
	 *
	 */
	public void setDescription(String description) {
		if (description.length() > MaxDescrSize) {
			System.out.println ("Visualization Description restricted to " + MaxDescrSize + " characters."
				+ " Truncating description..");
			this.description = description.substring(0, MaxDescrSize);
		}
		else
			this.description = description;
	}

	/**
	 *  @brief  sets the server type.
	 *
	 *	Options are: ['live', 'local', 'clone'], and 'live' is the default;
	 *
	 * 	@param  server server to which to connect.
	 *
	 */
	public void setServer(String server) {
		connector.setServer(server);
	}

	/**
	 *  @brief  sets a debug flag, used for debugging BRIDGES
	 *
	 * 	@param  flag  boolean
	 *
	 */
	public static void setDebugFlag (Boolean flag) {
		debug_flag = flag;
	}

	/**
	 *  @brief  gets the  debug flag value, used for debugging BRIDGES
	 *
	 * 	@return  boolean flag
	 *
	 */
	public static Boolean getDebugFlag () {
		return debug_flag;
	}

	/**
	 *  @brief Turns on map overlay for subsequent visualizations - used with location specific
	 *  datasets
	 *
	 *  @param flag     this is the boolean flag for displaying a map overlay
	 *
	 **/
	public void setMapOverlay (Boolean flag) {
		map_overlay = flag;
	}
	
	/**
	 *  @brief Sets the type of map overlay to use
	 *
	 *  @param map     this is an Array describing the map overlay. The first element of the array is which map to use: "world" or "us"
	 *  and the second element is what attribute from the map to show: a country from world map, or a state from US map.
	 *
	 **/
	public void setMap(String map_str) {
		this.map = map;
		setMapAsJSON(false);
	}

	public void setMap(AbstrMap map) {
		String map_str = map.getMapRepresentation();
		setMapOverlay (map.getOverlay());
		setCoordSystemType(map.getProjection());
		this.map = map_str;
		setMapAsJSON(true);
	}

	public void setMapAsJSON(Boolean b) {
		map_as_json = b;
	}

	/**
	 * Set the current assignment display mode to slide or stack, or throw an error;
	 * @param mode	One of: ['slide', 'stack'].
	 */
	public void setDisplayMode(String mode) throws IllegalArgumentException {
		switch (mode) {
			case "stack":
			case "slide":
				display_mode = mode;
				break;
			default:
				throw new IllegalArgumentException("Invalid display mode '" + mode + "'. Please use one of the following options: ['slide', 'stack'].");
		}
	}


	/**
	 * 	@brief Sets the coordinate system type.
	 *
	 *	Coordinate system type options are: ['cartesian',
	 *	'albersusa', 'equirectangular', 'window'], and 'cartesian'
	 *	is the default; The "window" option
	 *	only works for graphs and will automatically scale the view on
	 *	the browser to include all vertices which have a fixed location.
	 *	A different window can be specified using setWindow().
	 *
	 * 	@param coord 	this is the desired coordinate space
	 *
	 **/
	public void setCoordSystemType (String coord) {
		if (java.util.Arrays.asList(projection_options).indexOf(coord) >= 0) {
			coord_system_type = coord;
		}
		else  {
			System.err.println("Unrecognized coordinate system \'" + coord + "\', defaulting to cartesian. Options:");
			for (String prj : projection_options) {
				System.err.print(prj + "\t");
			}
			coord_system_type = "cartesian";
		}
	}
	

	/**
	 * @brief Specify the window that will be used to render the view by default.
	 *
	 * This function enables specifying the window that will rendered by
	 *	default in the view. This only works for graph data types.
	 *	And the coordinate system need ot be set to "window" using
	 *	setCoordSystemType().
	 *
	 * 	@param x1 	minimum window x
	 * 	@param x2 	maximum window x
	 * 	@param y1 	minimum window y
	 * 	@param y2 	maximum window y
	 **/
	public void setWindow (int x1, int x2, int y1, int y2) {
		setWindow((double) x1, (double) x2, (double) y1, (double) y2);
	}
	/**
	 * @brief Specify the window that will be used to render the view by default.
	 *
	 * This function enables specifying the window that will rendered by default in the view. This only works for graph data types. And the coordinate system need ot be set to "window" using setCoordSystemType().
	 *
	 * 	@param x1 	minimum window x
	 * 	@param x2 	maximum window x
	 * 	@param y1 	minimum window y
	 * 	@param y2 	maximum window y
	 **/
	public void setWindow (float x1, float x2, float y1, float y2) {
		setWindow((double) x1, (double) x2, (double) y1, (double) y2);
	}
	/**
	 * @brief Specify the window that will be used to render the view by default.
	 *
	 * This function enables specifying the window that will rendered by default in the view. This only works for graph data types. And the coordinate system need ot be set to "window" using setCoordSystemType().
	 *
	 * 	@param x1 	minimum window x
	 * 	@param x2 	maximum window x
	 * 	@param y1 	minimum window y
	 * 	@param y2 	maximum window y
	 **/
	public void setWindow (double x1, double x2, double y1, double y2) {
		window = new double[] {x1, x2, y1, y2};
	}

	/**
	 *	@brief Flag to control printing the JSON of the data structure.
	 *		Used only for debugging BRIDGES
	 * 	@return check if the flag to output the JSON is set
	**/
	public boolean getJSONFlag() {
		return json_flag;
	}

	/**
	 * 	@param flag the flag to print the JSON represenation of the data structure
	 *		to standard output. Used for debugging BRIDGES
	 **/
	public void setJSONFlag (boolean flag) {
		json_flag = flag;
	}

	/**
	 * 	@param flag the flag to turn labels on/off on the entire visualization
     *
	 **/
	public void setLabelFlag (boolean flag) {
		label_flag = flag;
	}

	/**
	 *  This method is used to suppress the visualization link that is
	 *  usually printed to the console
	 *
	 *  @param link_url_flag - flag  that controls if the link is printed
	 *              to console
	 *  @return none
	 *
	 */
	public void  postVisualizationLink(boolean link_url_flag) {
		post_link_url_flag = link_url_flag;
	}

	/**
	 *	@brief Get the assignment id
	 *
	 *  @return assignment as a string
	 *
	 */
	public static String getAssignment() {
		return (assignment_part < 10)
			? String.valueOf(assignment) + ".0" +
			String.valueOf(assignment_part)
			: String.valueOf(assignment) + "." +
			String.valueOf(assignment_part);
	}

	/**
	 *	@brief Get the assignment id as an integer
	 *
	 *  @return assignment as a string
	 *
	 */
	public static int getAssignmentID() {
		return assignment;
	}


	/**
	 *	set the assignment id
	 *
	 * @param assignment number (int)
	 *
	 **/
	public static void setAssignment(int assignment) {
		if (assignment  <  0)
			throw new IllegalArgumentException(
				"\n Assignment value must be >=  0.\n");
		else if (Bridges.assignment >= 0)
			Bridges.assignment_part = 0;

		Bridges.assignment = assignment;
	}

	/**
	 *
	 *	This exists to prevent duplicate error traces.
	 *
	 *	@return user id (string)
	 */
	public static String getUserName() {
		return userName.replace(" ", "+");
	}

	/**
	 *	set User id
	 *
	 *	@param userName (string)
	 *
	 */
	public static void setUserName(String userName) {
		Bridges.userName = userName;
	}

	/**
	 *
	 *	Get application key
	 *
	 *	@return application key value (string)
	 *
	 */
	public static String getKey() {
		return key;
	}

	/**
	 *
	 *	Set application key
	 *
	 *	@param  key application key value (string)
	 *
	 */
	public static void setKey(String key) {
		Bridges.key = key;
	}

	/**
	 *
	 *  @brief Provide BRIDGES  a handle to the data structure to be visualized.
	 *
	 * 	This method sets  the handle to the current data structure; this can
	 *	be an array, the head of a linked list, root of a tree structure, a graph
	 *	Arrays of upto 3 dimensions are suppported. It can be any of the data
	 *	structures supported by BRIDGES. Polymorphism and type casting is used
	 *	to determine the actual data structure and extract its representtion.
	 *
	 * @param ds   The data structure to set (any of the subclasses of DataStruct)
	 *
	 */
	public void setDataStructure(DataStruct ds) throws NullPointerException {
		try {
			ds_handle = ds;
			vis_type =   ds.getDataStructType();
		}
		catch (NullPointerException e) {
			System.out.println("Exception Thrown: Data structure passed to BRIDGES is null!\n" + e);
			return ;
		}
	}

	/**
	 *
	 * This method deletes the user's current assignment from the Bridges server
	 *
	 * @throws IOException
	 */
	public void clearAssignment() {
		String response = "";

		try {
			response = connector.delete("/clearAssignment/" + assignment + "?apikey=" + getKey() + "&username=" + getUserName());
		}
		catch (IOException e) {
			System.err.println("There was a problem removing the assignment from the server. \n"
				+ e.getMessage());
		}
		catch (RateLimitException e) {
			System.err.println("There was a RateLimitException from the server. \n"
				+ e.getMessage());
		}

		// print the response
		if (response.length() > 0) {
			System.out.println(response);
		}
	}

	/**
	 *
	 * This method generates the representation of the current data structure (JSON)
	 * and sends that to the Bridges server for generating a visualization.
	 *
	 * @throws RateLimitException
	 * @throws IOException
	 */
	public void visualize()  throws IOException, RateLimitException {
		String[] nodes_links = new String[2];
		String response = "";

		// There are two ways to use map from user perspective.
		// Either use it as parameter to setMap() to get an overlay.
		// Or pass it as a datastructure. This ugly piece of code handles that case.
		if (ds_handle instanceof AbstrMap)
		    setMap((AbstrMap)ds_handle);
		
		String nodes_links_str = this.ds_handle.getDataStructureRepresentation();

		String json_hdr = getJSONHeader();


		String ds_json =  json_hdr + nodes_links_str;

		if (json_flag)		// print the JSON (mostly for debugging)
			System.out.println("\nJSON String:\n" + ds_json);

		// send the data structure to the server and visualize
		try {
			response = connector.post("/assignments/" + getAssignment(), ds_json);
		}
		catch (IOException e) {
			System.err.println("There was a problem sending the visualization"
				+ " representation to the server. \n"
				+ e.getMessage());
		}
		catch (RateLimitException e) {
			System.err.println("There was a problem sending the visualization"
				+ " representation to the server. However, it responded with"
				+ " an impossible 'RateLimitException'. "
				+ e.getMessage());
		}

		// Only print a url and increment assignment part when a successful upload has completed
		if (response.length() > 0) {
			// Return a URL to the user
			if (post_link_url_flag) {
			    System.out.println("\nCheck Your Visualization at the following link:\n\n" + getVisualizeURL()
					 + "\n\n");
			}

			// Increment the subassignment counter
			assignment_part++;
		}
	}
    
    public String getVisualizeURL() {
	return connector.getServerURL() + "/assignments/" + assignment + "/"
	    + userName;
    }
	String getJSONHeader() {

		String json_hdr =
			OPEN_CURLY +
			QUOTE + "visual"  + QUOTE + COLON + QUOTE + vis_type + QUOTE + COMMA +
			QUOTE + "title"   + QUOTE + COLON + QUOTE + JSONValue.escape(title) + QUOTE + COMMA +
			QUOTE + "description" + QUOTE + COLON + QUOTE + JSONValue.escape(description) +
			QUOTE + COMMA +
			QUOTE + "label_flag" + QUOTE + COLON + label_flag + COMMA +
			QUOTE + "display_mode" + QUOTE + COLON + QUOTE + display_mode + QUOTE + COMMA +
			QUOTE + "coord_system_type" + QUOTE + COLON + QUOTE + coord_system_type + QUOTE + COMMA +
			QUOTE + "map_overlay" + QUOTE + COLON + map_overlay + COMMA;
		if (map_as_json) 
			json_hdr += QUOTE + "map" + QUOTE + COLON + map + COMMA;
		else 
			json_hdr += QUOTE + "map" + QUOTE + COLON + QUOTE + map + QUOTE + COMMA;

		// if window is specified, add it to JSON
		if (window != null && window.length == 4) {
			json_hdr += QUOTE + "window" + QUOTE + COLON + OPEN_BOX;
			json_hdr += window[0] + COMMA + window[1] + COMMA + window[2] + COMMA + window[3];
			json_hdr += CLOSE_BOX + COMMA;
		}

		return json_hdr;
	}

	String getServerURL() {
		return connector.getServerURL();
	}
}
