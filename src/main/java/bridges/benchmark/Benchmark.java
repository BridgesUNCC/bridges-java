package bridges.benchmark;


/**
 * @brief Base class for a variety of benchmarks.
 *
 * This class is not meant to be used directly by students.
 **/
public abstract class Benchmark {
    private long timeCap;

    public Benchmark(long timeCap) {
    	this.setTimeCap(timeCap);
	}

	public Benchmark() {
		this.setTimeCap(Long.MAX_VALUE);
	}

	/**
	 * @brief sets an upper bound to the time of a run.
	 *
	 * The benchmark will end after a run if it takes more than the
	 * given amount of time. So it is possible a particular run takes
	 * more than the alloted time, but that will be the last run.
	 *
	 * @param cap_in_s time limit in seconds
	 **/
	void setTimeCap(long cap_in_s) {
		this.timeCap = cap_in_s;
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
