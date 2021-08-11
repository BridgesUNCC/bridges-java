package bridges.data_src_dependent;

import bridges.base.GraphAdjList;

/**
 * @brief  Class that hold Open Street Map vertices
 *
 * This class holds Open Street Map data, from https://openstreetmap.org
 *
 * Objects from this class are typically not created by the user but retruned by bridges::connect::DataSource::getOsmData()
 *
 * Check out how to use OSM data at: https://bridgesuncc.github.io/tutorials/Data_OSM.html
 *
 * @author Kalpathi Subramanian, Erik Saule
 *
 * @date 2/16/19, 12/26/20
 */
public class OsmData {

	private OsmVertex[] vertices;
	private OsmEdge[] edges;
	private double[] latitude_range, longitude_range, cartesian_range_x, cartesian_range_y;
	private String name;

	public OsmData() {
		this.vertices = null;
		this.edges = null;
		this.latitude_range = longitude_range = cartesian_range_x = cartesian_range_y = null;
		this.name = null;
	}

	/**
	 * Constructor
	 * @param vertices nodes of the map (array of OsmVertex)
	 * @param edges links between nodes (array of OsmEdge)
	 * @param name  dataset name (string)
	 */
	public OsmData(OsmVertex[] vertices, OsmEdge[] edges, String name) {
		this.setEdges(edges);
		this.setVertices(vertices);
		this.setName(name);
	}

	/**
	 * Gets the nodes of the dataset
	 * @return the nodes of the dataset (array of OsmVertex)
	 */
	public OsmVertex[] getVertices() {
		return vertices;
	}

	private void minMax(double[] arr, double val) {
		arr[0] = arr[0] > val ? val : arr[0];
		arr[1] = arr[1] < val ? val : arr[1];
	}


	/**
	 * Sets the nodes of the map
	 * @param vertices nodes of the map
	 */
	public void setVertices(OsmVertex[] vertices) {
		this.vertices = vertices;
		double[] lat_range = {Double.MAX_VALUE, -Double.MAX_VALUE},
				 lon_range = {Double.MAX_VALUE, -Double.MAX_VALUE},
				 cart_range_x = {Double.MAX_VALUE, -Double.MAX_VALUE},
				 cart_range_y = {Double.MAX_VALUE, -Double.MAX_VALUE};

		for (OsmVertex vertex : vertices) {
			double lat = vertex.getLatitude();
			double lon = vertex.getLongitude();
			double cart_x = vertex.getCartesian_coord()[0];
			double cart_y = vertex.getCartesian_coord()[1];

			minMax(lat_range, lat);
			minMax(lon_range, lon);
			minMax(cart_range_x, cart_x);
			minMax(cart_range_y, cart_y);
		}

		this.latitude_range = lat_range;
		this.longitude_range = lon_range;
		this.cartesian_range_x = cart_range_x;
		this.cartesian_range_y = cart_range_y;

	}

	/**
	 * get name of OsmData
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name of OsmData
	 * @param name: String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get edges of OsmData
	 * @return edges: OsmEdge[]
	 */
	public OsmEdge[] getEdges() {
		return edges;
	}


	/**
	 * set edges of OsmData
	 * @param edges: OsmEdge[]
	 */
	public void setEdges(OsmEdge[] edges) {
		this.edges = edges;
	}

	/**
	 * get range of the y cartesian coordinates of vertex locations
	 * @return double[]: {min_y, max_y}
	 */
	public double[] getCartesianRangeY() {
		return cartesian_range_y;
	}

	/**
	 * get range of the latitude of vertex locations
	 * @return double[]: {min_latitude, max_latitude}
	 */
	public double[] getLatitudeRange() {
		return latitude_range;
	}

	/**
	 * get range of longitude of vertex locations
	 * @return double[]: {min_longitude, max_longitude}
	 */
	public double[] getLongitudeRange() {
		return longitude_range;
	}

	/**
	 * get range of the x cartesian coordinates of vertex locations
	 * @return double[]: {min_x, max_x}
	 */
	public double[] getCartesianRangeX() {
		return cartesian_range_x;
	}

	/**
	 *   get the range of dataset in Cartesian coords
	 *
	 *   @param  latr: double[2]
	 *   @param  lonr: double[2]
	 *	 @return none
	 */
	public void getLatLongRange(double[] latr, double[] lonr) {
		latr[0] = latitude_range[0];
		latr[1] = latitude_range[1];
		lonr[0] = longitude_range[0];
		lonr[1] = longitude_range[1];
	}

	/**
	 * Construct a graph out of the vertex and edge
	 * data of the OSM object.  The graph will
	 * associate the length of the edge to the
	 * graph edge. No data is bound to the
	 * vertices.
	 *
	 * The vertices of the graph will be located at
	 * the location given in the data set
	 * converted to cartesian coordinate.
	 */
	public GraphAdjList<Integer, OsmVertex, Double> getGraph() {
		GraphAdjList<Integer, OsmVertex, Double> ret_graph = new GraphAdjList<>();

		for (int i = 0; i < this.vertices.length; ++i) {
			OsmVertex vertex = this.vertices[i];
			double[] cart_coord = vertex.getCartesian_coord();
			ret_graph.addVertex(i, vertex);
			ret_graph.getVertex(i).setLocation(cart_coord[0], cart_coord[1]);
			ret_graph.getVertex(i).setColor("green");
		}

		for (OsmEdge edge : this.edges) {
			ret_graph.addEdge(edge.getSource(), edge.getDestination(), edge.getDistance());
		}

		return ret_graph;
	}
}
