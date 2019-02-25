package bridges.data_src_dependent;

import bridges.base.GraphAdjList;

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

    public OsmData(OsmVertex[] vertices, OsmEdge[] edges, String name) {
        this.setEdges(edges);
        this.setVertices(vertices);
        this.setName(name);
    }

    public OsmVertex[] getVertices() {
        return vertices;
    }

    private void minMax(double[] arr, double val) {
        arr[0] = arr[0] > val ? val : arr[0];
        arr[1] = arr[1] < val ? val : arr[1];
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OsmEdge[] getEdges() {
        return edges;
    }

    public void setEdges(OsmEdge[] edges) {
        this.edges = edges;
    }

    public double[] getCartesianRangeY() {
        return cartesian_range_y;
    }

    public double[] getLatitudeRange() {
        return latitude_range;
    }

    public double[] getLongitudeRange() {
        return longitude_range;
    }

    public double[] getCartesianRangeX() {
        return cartesian_range_x;
    }
	public void getLatLongRange(double[] latr, double[] lonr) {
		latr[0] = latitude_range[0];
		latr[1] = latitude_range[1];
		lonr[0] = longitude_range[0];
		lonr[1] = longitude_range[1];
	}

    public GraphAdjList<Integer, OsmVertex, Double> getGraph() {
        GraphAdjList<Integer, OsmVertex, Double> ret_graph = new GraphAdjList<>();

        for (int i = 0; i < this.vertices.length; ++i) {
            OsmVertex vertex = this.vertices[i];
            double[] cart_coord = vertex.getCartesian_coord();
            ret_graph.addVertex(i, vertex);
            ret_graph.getVertex(i).getVisualizer().setLocation(cart_coord[0], cart_coord[1]);
            ret_graph.getVertex(i).getVisualizer().setColor("green");
        }

        for (OsmEdge edge : this.edges) {
            ret_graph.addEdge(edge.getSource(), edge.getDestination(), (int)edge.getDistance());
        }

        return ret_graph;
    }
}
