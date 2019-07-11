package bridges.base;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Consumer;
import org.json.simple.JSONValue;

public class Plot extends DataStruct{
	
	private String plotTitle;
	private String plotSubtitle;
	private String plotYaxis;
	
	private long[] plotYData;
	private int[] plotXData;
	private int plotPointStart;
	private int runtime;
	
	private HashMap<String, long[]> yaxisData;
	
	public Plot(String title){
		this.plotTitle = title;
		this.yaxisData = new HashMap<String, long[]>();
	}
	
	public String getDataStructType() {
		return "Plot";
	}
	
	public void setTitle(String t) {
		plotTitle = t;
	}

	public String getTitle() {
		return plotTitle;
	}
	
	public void setSubTitle(String s) {
		plotSubtitle = s;
	}
	
	public String getSubtitle() {
		return plotSubtitle;
	}
	
	public void setYaxis(String yaxisName) {
		plotYaxis = yaxisName;
	}
	
	public String getYaxis() {
		return plotYaxis;
	}
	
	public void setXData(int[] d) {
		plotXData = d;
	}
	
	public int[] getXData() {
		return plotXData;
	}
	
	public void setYData(String key, long[] d) {
		if (!yaxisData.containsKey(key)) {
			yaxisData.put(key, d);
		}
	}
	
	public long[] getYData(String key) {
		if(yaxisData.containsKey(key)) {
			long[] result = yaxisData.get(key);
			return result;
		}
		return null;
	}
	
	public void runtime(String title, int iter, int maxRun, Consumer<int[]> runnable) throws InterruptedException {
		Random r = new Random();
		long[] time = new long[iter];
		int[] xData = new int[iter];
		int size = maxRun/iter;
		int n = 0;
		
		for(int j = 1; j <= iter; j++) {
			n += size;
			System.out.println(n);
			int[] arr = new int[n];
			for(int i = 0; i < arr.length; i++) {
				arr[i] = r.nextInt(10000);
			}
			long start = System.currentTimeMillis();
			runnable.accept(arr);
			long end = System.currentTimeMillis();
			long runTime = end - start;
			
			time[j-1] = runTime;
			xData[j-1] = n;
			System.out.println("here");
		}
		
		this.setXData(xData);
		this.setYData(title, time);
		
	}
	
	public String getDataStructureRepresentation() {
		String xaxis_json = OPEN_BOX;
		for(int i = 0; i < plotXData.length; i++) {
			xaxis_json +=  QUOTE + plotXData[i] + QUOTE + COMMA;
		}
		xaxis_json = xaxis_json.substring(0, xaxis_json.length() - 1);
		xaxis_json += CLOSE_BOX + COMMA;
		
		String yaxis_json = "";
		for (Entry<String, long[]> entry : yaxisData.entrySet()) {
		    String key = entry.getKey();
		    long[] value = entry.getValue();
		    yaxis_json += OPEN_CURLY + QUOTE + "Plot_Name" + QUOTE + COLON + QUOTE + key + QUOTE + COMMA +
		    			  QUOTE + "yaxis_data" + QUOTE + COLON + OPEN_BOX;
		    for( int i = 0; i < value.length ; i++) {
		    	yaxis_json += QUOTE + value[i] + QUOTE + COMMA;
		    }
		    yaxis_json = yaxis_json.substring(0, yaxis_json.length() - 1);
			yaxis_json += CLOSE_BOX + CLOSE_CURLY + COMMA;
		}
	    yaxis_json = yaxis_json.substring(0, yaxis_json.length() - 1);
		
		
		String json_str = QUOTE +"plot_title" + QUOTE + COLON + QUOTE + this.getTitle() + QUOTE + COMMA +
						  QUOTE + "xaxis_data" + QUOTE + COLON + xaxis_json +
						  QUOTE + "plots" + QUOTE + COLON + OPEN_BOX + yaxis_json + CLOSE_BOX + CLOSE_CURLY;
		return json_str;
		
	}
}

