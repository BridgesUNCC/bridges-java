package bridges.benchmark;

import bridges.base.GraphAdjList;
import bridges.data_src_dependent.OsmVertex;

import java.util.HashMap;

public class ShortestPathParams {
    public GraphAdjList<Integer, OsmVertex, Double> graph;
    public int source;
    public HashMap<Integer, Double> distance;
    public HashMap<Integer, Integer> parent;
}
