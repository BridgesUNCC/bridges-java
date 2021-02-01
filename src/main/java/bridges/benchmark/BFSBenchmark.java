package bridges.benchmark;

import bridges.base.GraphAdjList;
import bridges.base.LineChart;
import bridges.connect.DataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;


/**
 * @brief Benchmarks Breadth First Search algorithms.
 *
 * Benchmarks BFS algorithms and add time series to a LineChart.
 *
 * One can also set a maximum time spent on a particular run
 * using setTimeCap().
 *
 * and can be passed to the run function for being
 * benchmarked. A typical use would look something like
 *
 * \code{.java}
 * LineChart lc;
 * BFSBenchmark sb (lc) = new BFSBenchmark(lc);
 * sb.run("mybfsalgorithm", bfsalgo);
 * \endcode
 *
 * @author Erik Saule
 * @date 07/21/2019
 **/
public class BFSBenchmark extends GraphBenchmark {

    public BFSBenchmark(LineChart plot, long timeCap) {
        super(plot, timeCap);
        plot.setXLabel("Number of Edges");
        plot.setYLabel("Runtime (in ms)");
    }

    public BFSBenchmark(LineChart plot) {
        this(plot, Long.MAX_VALUE);
    }

    /**
	 * @brief benchmark a particular BFS algorithm that accepts a single BFSParams argument
	 *
	 * @param algoName Screen name of the algorithm
	 * @param bfsAlgo the actual algorithm
	 **/
    public void run(String algoName, DataSource ds, Consumer<BFSParams> bfsAlgo) throws IOException {
        ArrayList<Double> time = new ArrayList<>();
        ArrayList<Double> vertexCounts = new ArrayList<>();
        ArrayList<Double> edgeCounts = new ArrayList<>();

        for (int years = 0; years < 120; years = ((int)(1.2 * years)) + 1) {
            int year = CURRENT_YEAR - years;
            System.err.print("*");
            GraphAdjList<String, String, String> graph = new GraphAdjList<>();
            long[] counts = new long[2];
            generateWikidataActorMovieData(year, CURRENT_YEAR, counts, graph, ds);
            vertexCounts.add((double) counts[0]);
            edgeCounts.add((double) counts[1]);

            String root = highestDegreeVertex(graph);
            BFSParams params = new BFSParams();
            params.graph = graph;
            params.root = root;
            params.level = new HashMap<>();
            params.parent = new HashMap<>();

            long start = System.currentTimeMillis();
            bfsAlgo.accept(params);
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
