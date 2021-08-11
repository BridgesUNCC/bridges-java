package bridges.data_src_dependent;

/**
 * @brief this is a class that defines an OpenStreet Map edge
 *
 * Objects from this class are typically not created by the user but contained in the OsmData object returned by bridges::connect::DataSource::getOsmData()
 *
 * Check out how to use OSM data at: https://bridgesuncc.github.io/tutorials/Data_OSM.html
 *
 * @author Erik Saule, Kalpathi Subramanian,
 * @date 12/26/20
 */


public class OsmEdge {
	private int source, destination;
	private double distance;

	/**
	 * constructor for OsmEdge
	 * @param source: int, index of source vertex
	 * @param destination: int. index of destination vertex
	 * @param distance: double, distance between source and destination vertices
	 */
	public OsmEdge(int source, int destination, double distance) {
		this.setSource(source);
		this.setDestination(destination);
		this.setDistance(distance);
	}

	/**
	 * get index of source vertex
	 * @return int
	 */
	public int getSource() {
		return source;
	}

	/**
	 * set index of source vertex
	 * @param source: int
	 */
	public void setSource(int source) {
		this.source = source;
	}

	/**
	 * get index of destination vertex
	 * @return int
	 */
	public int getDestination() {
		return destination;
	}

	/**
	 * set index of destination vertex
	 * @param destination: int
	 */
	public void setDestination(int destination) {
		this.destination = destination;
	}

	/**
	 * get distance between the source and destination vertices
	 * @return int
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * set the distance between the source and destination vertices
	 * @param distance: double
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
}
