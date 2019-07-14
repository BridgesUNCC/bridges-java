package bridges.base;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.function.Consumer;


public class SortingBenchmark {
    private LineChart plot;

    private Random r;

    private int maxSize;
    private int baseSize;
    private int increment;
    private double geoBase;
    private long time_cap_ms;
    
    public SortingBenchmark(LineChart p) {
	this.plot = p;
	p.setXLabel("Size of Array");
	p.setYLabel("Runtime (in ms)");

	r = new Random();

	maxSize = 1;
	baseSize = 1;
	increment = 1;
	geoBase = 1.;
	time_cap_ms = Long.MAX_VALUE;
    }

    public void setMaxSize(int size) {
	maxSize = size;
    }

    public void setBaseSize(int size) {
	baseSize = size;
    }
    
    public void setIncrement(int inc) {
	increment = inc;
    }

    public void setGeometric(double base) {
	geoBase = base;
    }
    
    public void linearRange(int baseSize, int maxSize, int nbPoint) {
	setBaseSize (baseSize);
	setMaxSize (maxSize);
	setIncrement ((maxSize - baseSize)/nbPoint);
	setGeometric (1.0);
    }

    public void geometricRange(int baseSize, int maxSize, double base) {
	setBaseSize (baseSize);
	setMaxSize (maxSize);
	setIncrement (0);
	setGeometric (base);
	if (base <= 1.0) {
	    System.err.println("base should be > 1.0");
	}
    }
    
    public void setTimeCap(long cap_in_ms) {
	time_cap_ms = cap_in_ms;
    }

    private void generate(int[] arr, int n) {
	for(int i = 0; i < n; i++) {
	    arr[i] = r.nextInt(2*n);
	}
    }
    
    public void run(String algoName, Consumer<int[]> runnable) {

	ArrayList<Double> time = new ArrayList<Double>();
	ArrayList<Double> xData = new ArrayList<Double>();

	System.out.println(geoBase);
	System.out.println(increment);
	    
	for(int n = baseSize; n <= maxSize;
	    n=Math.max((int)(geoBase*n)+increment, n+1)) {

	    //System.out.println(n);
	    int[] arr = new int[n];
		
	    generate(arr, n);
		
	    long start = System.currentTimeMillis();
	    runnable.accept(arr);
	    long end = System.currentTimeMillis();
	    long runTime = end - start;
	    time.add ((double)runTime );
	    xData.add ( (double)n );

	    if (runTime > time_cap_ms) {
		break;
	    }
	}
	plot.setXData(algoName, xData);
	plot.setYData(algoName, time);
    }

}
