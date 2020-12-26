package bridges.data_src_dependent;

/**
 * This class is a helper class to be used with amenities data retrieved from
 * OpenStreet Map data
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
    public Amenities(){
        this.id_val = 0;
        this.lat = 0;
        this.lon = 0;
        this.name = null;
        this.other = null;
    }

 	/**
	 * Constructor
	 * @param id_val
	 * @param lat
	 * @param long
	 * @param name
	 * @param other
	 */
    public Amenities (double id_val, double lat, double lon, String name, String[] other){
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
    public void setId(double id_val){
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
    public void setLat(double lat){
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
	 * @param lat longitude position to set
	 */
    public void setLon(double lon){
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
	 * get the other ??
	 * @return other?? 
	 */
    public String[] getOther() {
        return other;
    }

	/**
	 * set the other  ??
	 * @param other 
	 */
    public void setOther(String[] other) {
        this.other = other;
    }


}
