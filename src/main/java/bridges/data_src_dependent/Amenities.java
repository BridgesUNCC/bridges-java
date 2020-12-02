package bridges.data_src_dependent;

public class Amenities {

    private double id_val;
    private double lat;
    private double lon;
    private String name;
    private String[] other;

    public Amenities(){
        this.id_val = 0;
        this.lat = 0;
        this.lon = 0;
        this.name = null;
        this.other = null;
    }

    public Amenities (double id_val, double lat, double lon, String name, String[] other){
        this.setId(id_val);
        this.setLat(lat);
        this.setLon(lon);
        this.setName(name);
        this.setOther(other);
    }


    public double getId() {
        return id_val;
    }

    public void setId(double id_val){
        this.id_val = id_val;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon){
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getOther() {
        return other;
    }

    public void setOther(String[] other) {
        this.other = other;
    }


}