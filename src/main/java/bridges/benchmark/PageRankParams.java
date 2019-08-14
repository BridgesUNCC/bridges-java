package bridges.benchmark;

import bridges.base.GraphAdjList;

import java.util.HashMap;

public class PageRankParams extends BenchmarkParams {
    public GraphAdjList<String, String, String> graph;
    public HashMap<String, Double> pageRank;
}
