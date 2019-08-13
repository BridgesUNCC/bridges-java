package bridges.benchmark;


import bridges.base.LineChart;

/**
 * @brief Base class for a variety of benchmarks.
 *
 * This class is not meant to be used directly by students.
 **/
abstract class Benchmark {
    private long timeCap;
    LineChart plot;

    Benchmark(LineChart plot, long timeCap) {
		plot.setXLabel("Size of Array");
		plot.setYLabel("Runtime (in ms)");
    	this.plot = plot;
    	this.setTimeCap(timeCap);
	}

	Benchmark(LineChart plot) {
		this(plot, Long.MAX_VALUE);
	}

	/**
	 * @brief sets an upper bound to the time of a run.
	 *
	 * The benchmark will end after a run if it takes more than the
	 * given amount of time. So it is possible a particular run takes
	 * more than the alloted time, but that will be the last run.
	 *
	 * @param cap_in_ms time limit in milliseconds
	 **/
	void setTimeCap(long cap_in_ms) {
		this.timeCap = cap_in_ms;
	}

	/**
	 * @brief Return time limit of a run.
	 *
	 * The benchmark will end after a run if it takes more than the
	 * given amount of time. So it is possible a particular run takes
	 * more than the alloted time, but that will be the last run.
	 *
	 * @return the time upper bound (in seconds) of a particular run.
	 */
	long getTimeCap() {
		return this.timeCap;
	}
}
