package bridges.benchmark;

import bridges.base.Array;
import bridges.base.Edge;
import bridges.base.GraphAdjList;
import bridges.base.LineChart;
import bridges.connect.DataSource;
import bridges.data_src_dependent.ActorMovieWikidata;
import bridges.data_src_dependent.OsmVertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * @brief Base class for a variety of graph based benchmark.
 *
 * This class is not meant to be used directly by students.
 **/
abstract class GraphBenchmark extends Benchmark {
    static final int CURRENT_YEAR = 2019;

    GraphBenchmark(LineChart plot) {
        super(plot);
    }

    GraphBenchmark(LineChart plot, long timeCap) {
        super(plot, timeCap);
    }

    void generateWikidataActorMovieData(int yearMin,
                                        int yearMax,
                                        long[] counts,
                                        GraphAdjList<String, String, String> graph)
    throws IOException {
        ArrayList<ActorMovieWikidata> data = DataSource.getWikidataActorMovie(yearMin, yearMax);
        long vertexCount = 0;
        long edgeCount = 0;

        for (ActorMovieWikidata entry : data) {

            // add the movie vertex
            if (graph.getVertex(entry.getMovieURI()) == null) {
                graph.addVertex(entry.getMovieURI(), entry.getMovieName());
                vertexCount++;
            }

            // add the actor vertex
            if (graph.getVertex(entry.getActorURI()) == null) {
                graph.addVertex(entry.getActorURI(), entry.getActorName());
                vertexCount++;
            }

            // add symmetric edges
            graph.addEdge(entry.getMovieURI(), entry.getActorURI());
            graph.addEdge(entry.getActorURI(), entry.getMovieURI());
            edgeCount += 2;
        }

        counts[0] = vertexCount;
        counts[1] = edgeCount;
    }

    String highestDegreeVertex(GraphAdjList<String, String, String> graph) {
        long maxDegree = -1;
        String ret = null;

        for (String key : graph.getVertices().keySet()) {
            long degree = 0;
            for (Edge<String, String> e : graph.outgoingEdgeSetOf(key)) {
                degree++;
            }
            if (degree > maxDegree) {
                maxDegree = degree;
                ret = key;
            }
        }

        return ret;
    }

}
