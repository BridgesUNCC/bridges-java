package bridges.benchmark;
import bridges.base.LineChart;

import java.util.Random;
import java.util.ArrayList;
import java.util.function.Consumer;


/**
 * @brief Benchmarks sorting algorithm
 *
 * Benchmarks sorting algorithms and add time series to a LineChart.
 *
 * The benchmark goes from an initial size controlled by
 * setBaseSize() to a largest size controlled by setMaxSize(). One
 * can also set a maximum time spent on a particular run using
 * setTimeCap().
 *
 * The benchmark goes from a array size of n to the next one of
 * geoBase * n + increment, where the base is controlled by
 * setGeometric() and increment is controlled by
 * setIncrement(). For simpler use one can set a purley linear
 * sampling with linearRange() or a purely geometric one with
 * geometricRange().
 *
 * The sorting algorithms must have for prototype:
 * static void mysort(int[]);
 * and can be passed to the run function for being benchmarked. A typical use would look something like
 *
 * \code{.java}
 * LineChart lc;
 * SortingBenchmark sb (lc);
 * sb.linearRange (100, 1000, 5);
 * sb.run("mysortingalgorithm", MyClass.mysort);
 * \endcode
 *
 * @author Erik Saule
 * @date 07/20/2019
 *
 **/
public class SortingBenchmark extends Benchmark {
	private boolean debug = false;

	private Random r;

	private int maxSize;
	private int baseSize;
	private int increment;
	private double geoBase;

	public SortingBenchmark(LineChart p) {
	    super(p);

		r = new Random();

		maxSize = 1;
		baseSize = 1;
		increment = 1;
		geoBase = 1.;
	}

	/**
	* @brief Puts a cap on the largest array to be used
	*
	* @param size Maximum size considered
	**/
	public void setMaxSize(int size) {
		maxSize = size;
	}

	/**
	 * @brief Smallest array to be used
	 *
	 * @param size of the smallest array to use/
	 **/
	public void setBaseSize(int size) {
		baseSize = size;
	}

	/**
	 * @brief Sets the increment for the benchmark size
	 *
	 * @param inc new value of the increment
	 **/
	public void setIncrement(int inc) {
		increment = inc;
	}

	/**
	 * @brief Sets a geometric progression for the benchmark size
	 *
	 * @param base new base of the geometric progression
	 **/
	public void setGeometric(double base) {
		geoBase = base;
	}

	/**
	 * @brief The benchmark will sample a range with a fixed number of
	 * points.
	 *
	 * The benchmark will sample about nbPoint equally distributed in
	 * the range [baseSize; maxSize]
	 *
	 * @param baseSize lower bound of the range sampled
	 * @param maxSize upper bound of the range sampled
	 * @param nbPoint number of sample
	 */
	public void linearRange(int baseSize, int maxSize, int nbPoint) {
		setBaseSize (baseSize);
		setMaxSize (maxSize);
		setIncrement ((maxSize - baseSize) / nbPoint);
		setGeometric (1.0);
	}

	/**
	 * @brief The benchmark will sample a range using in geometrically
	 * increasing sequence
	 *
	 * The benchmark will sample the range [baseSize; maxSize] using a
	 * geometric distribution in base base. That is to say, it will
	 * sample baseSize, base*baseSize, base*base*baseSize, ...
	 *
	 * @param baseSize lower bound of the range sampled
	 * @param maxSize upper bound of the range sampled
	 * @param base base of the geometric increase
	 */
	public void geometricRange(int baseSize, int maxSize, double base) {
		setBaseSize (baseSize);
		setMaxSize (maxSize);
		setIncrement (0);
		setGeometric (base);
		if (base <= 1.0) {
			System.err.println("base should be > 1.0");
		}
	}


	private void generate(int[] arr, int n) {
		for (int i = 0; i < n; i++) {
			arr[i] = r.nextInt(2 * n);
		}
	}

	private boolean check (int[] arr, int n) {
		boolean ok = true;
		for (int i = 1; i < n; ++i) {
			if (arr[i] < arr[i - 1]) {
				ok = false;
				break;
			}
		}
		return ok;
	}

	/**
	 * @brief benchmark a particular algorithm
	 *
	 * @param algoName Screen name of the algorithm
	 * @param runnable the actual algorithm
	 **/
	public void run(String algoName, Consumer<int[]> runnable) {

		ArrayList<Double> time = new ArrayList<Double>();
		ArrayList<Double> xData = new ArrayList<Double>();

		if (debug) {
			System.out.println(geoBase);
			System.out.println(increment);
		}

		for (int n = baseSize; n <= maxSize;
			n = Math.max((int)(geoBase * n) + increment, n + 1)) {

			//System.out.println(n);
			int[] arr = new int[n];

			generate(arr, n);

			long start = System.currentTimeMillis();
			runnable.accept(arr);
			long end = System.currentTimeMillis();
			long runTime = end - start;

			if (!check(arr, n)) {
				System.err.println("Sorting algorithm " + algoName + " is incorrect");
			}

			time.add ((double)runTime );
			xData.add ( (double)n );

			if (runTime > this.getTimeCap()) {
				break;
			}
		}
		this.plot.setXData(algoName, xData);
		this.plot.setYData(algoName, time);
	}

}
