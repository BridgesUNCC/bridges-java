package bridges.data_src_dependent;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief  Class that hold Open Street Map Amenity Data
 * 
 * This class holds the data for all the amenities requested by the user
 * 
 * @author Jay Strahler
 *
 * @date 12/28/20 
 */
public class AmenityData {


    private List<Amenities> data;

    private double minLat;
    private double minLon;
    private double maxLat;
    private double maxLon;
    private int count;

    /**
     * Default Constructor
     */
    public AmenityData(){
        this.data = new ArrayList<>();
        this.minLat = 0;
        this.minLon = 0;
        this.maxLat = 0;
        this.maxLon = 0;
        this.count = 0;
    }

	/**
     * Constructor
	 *
     * @param data  amenity data
     * @param minLat  minimum latitude
     * @param minLon  minimum longitude
     * @param maxLat  maximum latitude
     * @param maxnLon  maximum longitude
     * @param count  number of amenities 
     */

    public AmenityData(List<Amenities> data, double minLat, double minLon, double maxLat, double maxLon, int count){
        this.data = data;
        this.setMinLat(minLat);
        this.setMinLon(minLon);
        this.setMaxLat(maxLat);
        this.setMaxLon(maxLon);
        this.setCount(count);
    }

	/**
	 * get Amenity data
	 * @return amenity data
	 */

    public List<Amenities> getAmenities(){
        return data;
    }

	/**
	 * set  Amenity data
	 * @param amenity amenity data to be added
	 */
    public void addAmenities(Amenities amenitiy){
        this.data.add(amenitiy);
    }

	/**
     * get the minimum latitude  of this amenity data
     * @return  latitude minimum
     */

    public double getMinLat(){
        return minLat;
    }

	/**
     * set the minimum latitude  of this amenity data
     * @param minLat  latitude minimum to be set
     */
    public void setMinLat(double minLat){
        this.minLat = minLat;
    }

	/**
     * get the minimum longitude  of this amenity data
     * @return  longitude minimum
     */
    public double getMinLon(){
        return minLon;
    }

	/**
     * set the minimum longitude  of this amenity data
     * @param minLon  longitude minimum to be set
     */
    public void setMinLon(double minLon){
        this.minLon = minLon;
    }
	/**
     * get the maximum latitude  of this amenity data
     * @return  latitude maximum
     */

    public double getMaxLat(){
        return maxLat;
    }

	/**
     * set the maximum latitude  of this amenity data
     * @param maxLat  latitude maximum to be set
     */
    public void setMaxLat(double maxLat){
        this.maxLat = maxLat;
    }

	/**
     * get the maximum longitude  of this amenity data
     * @return  longitude maximum
     */
    public double getMaxLon(){
        return maxLon;
    }

	/**
     * set the maximum longitude  of this amenity data
     * @param maxLon  longitude maximum to be set
     */
    public void setMaxLon(double maxLon){
        this.maxLon = maxLon;
    }

	/**
     * get the count of amenities
     * @param count of amenities
     */
    public int getCount(){
        return count;
    }


	/**
     * set the count of amenities
     * @param count  amenity count to be set
     */
    public void setCount(int count){
        this.count = count;
    }
};
