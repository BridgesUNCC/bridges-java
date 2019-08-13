package bridges.benchmark;

import bridges.base.GraphAdjList;
import bridges.base.LineChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class BFSBenchmark extends GraphBenchmark {
    static final int CURRENT_YEAR = 2019;

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
    public void run(String algoName, Consumer<BFSParams> bfsAlgo) throws IOException {
        ArrayList<Double> time = new ArrayList<>();
        ArrayList<Double> vertexCounts = new ArrayList<>();
        ArrayList<Double> edgeCounts = new ArrayList<>();

        for (int years = 0; years < 120; years = ((int)(1.2 * years)) + 1) {
            int year = CURRENT_YEAR - years;
            System.err.print("*");
            GraphAdjList<String, String, String> graph = new GraphAdjList<>();
            long[] counts = new long[2];

            generateWikidataActorMovieData(year, CURRENT_YEAR, counts, graph);
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
