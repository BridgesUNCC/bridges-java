package bridges.data_src_dependent;

/**
 *  @brief  Class that hold individual Open Street Map Amenities
 *
 * This class holds the individual information for each amenity requested. 
 *
 * Usually this class is not created by the user but part of an AmenityData object returned by bridges::connect::DataSource::getAmenityData()
 *
 * Check out the tutorial on getting amenity data at https://bridgesuncc.github.io/tutorials/Data_Amenity.html
 *
 * @author Jay Strahler
 */

public class Amenities {

	private double id_val;
	private double lat;
	private double lon;
	private String name;
	private String[] other;

	/**
	 * Default Constructor
	 */
	public Amenities() {
		this.id_val = 0;
		this.lat = 0;
		this.lon = 0;
		this.name = null;
		this.other = null;
	}

	/**
	 * Constructor
	 * @param id_val  Amenity ID as stored in Open Street Maps
	 * @param lat  Latitude of Amenity
	 * @param lon  Longitude of Amenity
	 * @param name  Name of Amenity as found in Open Street Maps
	 * @param other  List of special values that may have come with the amenity
	 */
	public Amenities (double id_val, double lat, double lon, String name, String[] other) {
		this.setId(id_val);
		this.setLat(lat);
		this.setLon(lon);
		this.setName(name);
		this.setOther(other);
	}


	/**
	 * get the ID  of this amenity
	 * @return ID value
	 */
	public double getId() {
		return id_val;
	}

	/**
	 * set the ID value of this amenity
	 * @param id_val ID value to set
	 */
	public void setId(double id_val) {
		this.id_val = id_val;
	}

	/**
	 * get the latitude  of this amenity data
	 * @return amenity data latitude
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * set the latitude  value of this amenity
	 * @param lat latitude position to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * get the longitude  of this amenity data
	 * @return amenity data longitude
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * set the longitude  value of this amenity
	 * @param lon longitude position to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	/**
	 * get the name  of this amenity data
	 * @return name of this amenity
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the name of this amenity
	 * @param name name of amenity to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get the extra data present for certain amenities
	 * @return list of extra data
	 */
	public String[] getOther() {
		return other;
	}

	/**
	 * set the extra data present for certain amenities
	 * @param other
	 */
	public void setOther(String[] other) {
		this.other = other;
	}


}
