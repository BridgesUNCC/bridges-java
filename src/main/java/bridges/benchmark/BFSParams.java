package bridges.benchmark;

import bridges.base.GraphAdjList;

import java.util.HashMap;

public class BFSParams extends BenchmarkParams {
    public GraphAdjList<String, String, String> graph;
    public String root;
    public HashMap<String, Integer> level;
    public HashMap<String, String> parent;
}
