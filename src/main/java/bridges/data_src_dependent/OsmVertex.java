package bridges.data_src_dependent;
/**
 * @brief this is a class that defines an OpenStreet Map node or vertex
 *
 * @author Erik Saule, Kalpathi Subramanian, 
 * @date 12/26/20
 */

public class OsmVertex {
	private double latitude, longitude;
	private double[] cartesian_coord = {0, 0};

	/**
	 * constructor for OsmVertex
	 * @param latitude: double, latitude of vertex
	 * @param longitude: double, longitude of vertex
	 */
	public OsmVertex(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.to_cartesian_coord();
	}

	/**
	 * get latitude of vertex
	 * @return double
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * set latitude of vertex
	 * @param latitude: double
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
		to_cartesian_coord();
	}

	/**
	 * get longitude of vertex
	 * @return double
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * set longitude of vertex
	 * @param longitude: double
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
		to_cartesian_coord();
	}

	/**
	 * get cartesian coordinates of vertex, translated from the latitude and longitude of the vertex.
	 * @return double[], {x, y}
	 */
	public double[] getCartesian_coord() {
		return cartesian_coord;
	}

	private void to_cartesian_coord() {
		final int earth_radius = 6378;
		double lat_rad = this.latitude * Math.PI / 180;
		double lon_rad = this.longitude * Math.PI / 180;
		this.cartesian_coord[0] = earth_radius * Math.cos(lat_rad) * Math.cos(lon_rad);
		this.cartesian_coord[1] = earth_radius * Math.cos(lat_rad) * Math.sin(lon_rad);
	}
}
