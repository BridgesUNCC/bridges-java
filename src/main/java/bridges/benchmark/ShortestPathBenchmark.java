package bridges.benchmark;

import bridges.base.Edge;
import bridges.base.GraphAdjList;
import bridges.base.LineChart;
import bridges.connect.DataSource;
import bridges.data_src_dependent.OsmData;
import bridges.data_src_dependent.OsmVertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * @brief Benchmarks Shortest Path algorithms.
 *
 * Benchmarks Shortest Path algorithms and add time series to a LineChart.
 *
 * One can also set a maximum time spent on a particular run
 * using setTimeCap();
 *
 * and can be passed to the run function for being
 * benchmarked. A typical use would look something like
 *
 * \code{.java}
 * LineChart lc;
 * ShortestPathBenchmark sb = new ShortestPathBenchmark(lc);
 * sb.run("mybfsalgorithm", spalgo);
 * \endcode
 *
 * @author Erik Saule
 * @date 07/21/2019
 **/
public class ShortestPathBenchmark extends GraphBenchmark {

    public ShortestPathBenchmark(LineChart plot, long timeCap) {
        super(plot, timeCap);
        plot.setXLabel("Number of Edges");
        plot.setYLabel("Runtime (in ms)");
    }

    public ShortestPathBenchmark(LineChart plot) {
        this(plot, Long.MAX_VALUE);
    }

    private double distFunction(OsmVertex v, double latc, double lonc) {
        return (v.getLatitude() - latc) * (v.getLatitude() - latc) +
                (v.getLongitude() - lonc) * (v.getLongitude() - lonc);
    }

    private int getCenter(OsmData osmData,
                          GraphAdjList<Integer, OsmVertex, Double> graph,
                          double latc,
                          double lonc) {

        int minIndex = 0;
        double dist = distFunction(graph.getVertex(minIndex).getValue(), latc, lonc);

        for (int i = 1; i < graph.getVertices().size(); ++i) {
            double locDist = distFunction(graph.getVertex(i).getValue(), latc, lonc);
            if (locDist < dist) {
                minIndex = i;
                dist = locDist;
            }
        }

        return minIndex;
    }

      /**
	 * @brief benchmark a particular Shortest Path algorithm that accepts a single ShortestPathParams argument
	 *
	 * @param algoName Screen name of the algorithm
	 * @param spAlgo the actual algorithm
	 **/
    public void run(String algoName, Consumer<ShortestPathParams> spAlgo) throws IOException {
        ArrayList<Double> time = new ArrayList<>();
        ArrayList<Double> vertexCounts = new ArrayList<>();
        ArrayList<Double> edgeCounts = new ArrayList<>();

        double reflat = 40.74; //New York City, NY
        double reflong = -73.98;

        for (double radius = 0.02; radius < 0.15; radius += 0.02) {
            OsmData osmData = DataSource.getOSMData(reflat - radius, reflong - radius,
                    reflat + radius, reflong + radius);
            GraphAdjList<Integer, OsmVertex, Double> graph = osmData.getGraph();

            long vertexCount = graph.getVertices().size();
            long edgeCount = 0;
            for (int k : graph.getVertices().keySet()) {
                for (Edge<Integer, Double> e : graph.outgoingEdgeSetOf(k)) {
                    edgeCount++;
                }
            }

            int root = getCenter(osmData, graph, reflat, reflong);

            ShortestPathParams params = new ShortestPathParams();
            params.graph = graph;
            params.source = root;
            params.distance = new HashMap<>();
            params.parent = new HashMap<>();

            long start = System.currentTimeMillis();
            spAlgo.accept(params);
            long end = System.currentTimeMillis();
            long runTime = end - start;

            time.add((double) runTime);


            if (runTime > this.getTimeCap()) {
                break;
            }

        }
        this.plot.setXData(algoName, edgeCounts);
        this.plot.setYData(algoName, time);
        System.err.println();
    }
}
