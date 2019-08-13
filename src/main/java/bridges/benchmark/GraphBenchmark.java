package bridges.benchmark;

import bridges.base.LineChart;

/**
 * @brief Base class for a variety of graph based benchmark.
 *
 * This class is not meant to be used directly by students.
 **/
abstract class GraphBenchmark extends Benchmark {

    GraphBenchmark(LineChart plot) {
        super(plot);
    }

    GraphBenchmark(LineChart plot, long timeCap) {
        super(plot, timeCap);
    }
}
