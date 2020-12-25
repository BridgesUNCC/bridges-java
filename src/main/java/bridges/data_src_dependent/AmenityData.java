package bridges.data_src_dependent;

import java.util.ArrayList;
import java.util.List;

/**
 * Object that holds amenity data retrieved from OSM repository
 * 
 * @author Jay Strahler
 *
 */


public class AmenityData {


    private List<Amenities> data;

    private double minLat;
    private double minLon;
    private double maxLat;
    private double maxLon;
    private int count;


    public AmenityData(){
        this.data = new ArrayList<>();
        this.minLat = 0;
        this.minLon = 0;
        this.maxLat = 0;
        this.maxLon = 0;
        this.count = 0;
    }

    public AmenityData(List<Amenities> data, double minLat, double minLon, double maxLat, double maxLon, int count){
        this.data = data;
        this.setMinLat(minLat);
        this.setMinLon(minLon);
        this.setMaxLat(maxLat);
        this.setMaxLon(maxLon);
        this.setCount(count);
    }

    

    public List<Amenities> getAmenities(){
        return data;
    }

    public void addAmenities(Amenities amenitiy){
        this.data.add(amenitiy);
    }

    public double getMinLat(){
        return minLat;
    }

    public void setMinLat(double minLat){
        this.minLat = minLat;
    }

    public double getMinLon(){
        return minLon;
    }

    public void setMinLon(double minLon){
        this.minLon = minLon;
    }

    public double getMaxLat(){
        return maxLat;
    }

    public void setMaxLat(double maxLat){
        this.maxLat = maxLat;
    }

    public double getMaxLon(){
        return maxLon;
    }

    public void setMaxLon(double maxLon){
        this.maxLon = maxLon;
    }

    public int getCount(){
        return count;
    }


    public void setCount(int count){
        this.count = count;
    }
    
}
