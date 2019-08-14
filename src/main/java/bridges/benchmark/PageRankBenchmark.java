package bridges.benchmark;

import bridges.base.GraphAdjList;
import bridges.base.LineChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;


/**
 * @brief Benchmarks Page Rank algorithms.
 *
 * Benchmarks PageRank algorithms and add time series to a LineChart.
 *
 * One can also set a maximum time spent on a particular run
 * using setTimeCap().
 *
 *
 * and can be passed to the run function for being
 * benchmarked. A typical use would look something like
 *
 * \code{.java}
 * LineChart lc;
 * PageRankBenchmark sb  = new PageRankBenchmark(lc);
 * sb.run("mybfsalgorithm", pralgo);
 * \endcode
 *
 * @author Erik Saule
 * @date 07/21/2019
 **/
public class PageRankBenchmark extends GraphBenchmark{

    public PageRankBenchmark(LineChart plot, long timeCap) {
        super(plot, timeCap);
        plot.setXLabel("Number of Edges");
        plot.setYLabel("Runtime (in ms)");
    }

    public PageRankBenchmark(LineChart plot) {
        this(plot, Long.MAX_VALUE);
    }

    public void run(String algoName, Consumer<PageRankParams> prAlgo) throws IOException {
        ArrayList<Double> time = new ArrayList<>();
        ArrayList<Double> vertexCounts = new ArrayList<>();
        ArrayList<Double> edgeCounts = new ArrayList<>();

        for (int years = 0; years < 120; years = ((int) (1.2 * years)) + 1) {
            int year = CURRENT_YEAR - years;
            System.err.print("*");
            GraphAdjList<String, String, String> graph = new GraphAdjList<>();
            long[] counts = new long[2];
            generateWikidataActorMovieData(year, CURRENT_YEAR, counts, graph);

            PageRankParams params = new PageRankParams();
            params.graph = graph;
            params.pageRank = new HashMap<>();

            long start = System.currentTimeMillis();
            prAlgo.accept(params);
            long end = System.currentTimeMillis();
            long runTime = end - start;

            time.add((double) runTime);
            vertexCounts.add((double) counts[0]);
            edgeCounts.add((double) counts[1]);

            if (runTime > this.getTimeCap()) {
                break;
            }
        }
        this.plot.setXData(algoName, edgeCounts);
        this.plot.setYData(algoName, time);
        System.err.println();
    }
}
