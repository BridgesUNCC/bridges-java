package bridges.data_src_dependent;
import java.math.*;

public class OsmVertex {
    private double latitude, longitude;
    private double[] cartesian_coord = {0, 0};

    public OsmVertex(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.to_cartesian_coord();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
        to_cartesian_coord();
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
        to_cartesian_coord();
    }

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
