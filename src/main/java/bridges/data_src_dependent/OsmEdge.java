package bridges.data_src_dependent;

public class OsmEdge {
    private int source, destination;
    private double distance;

    public OsmEdge(int source, int destination, double distance) {
        this.setSource(source);
        this.setDestination(destination);
        this.setDistance(distance);
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
