package bridges.base;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Consumer;

public class Plot extends DataStruct{
	
	private String plotTitle;
	private String plotSubtitle;
	private String yLabel;
	private String xLabel;
	private boolean mouseTrack;
	private boolean dataLabel;
	private boolean logorithmic;
	
	private HashMap<String, double[]> yaxisData;
	private HashMap<String, double[]> xaxisData;
	
	public Plot(String title){
		this.plotTitle = title;
		this.plotSubtitle = "";
		this.yLabel = "";
		this.xLabel = "";
		this.yaxisData = new HashMap<String, double[]>();
		this.xaxisData = new HashMap<String, double[]>();
		this.mouseTrack = false;
		this.dataLabel = true;
		this.logorithmic = false;
	}
	
	public void plotOptions(boolean mTrack, boolean dLabel) {
		this.mouseTrack = mTrack;
		this.dataLabel = dLabel;
	}
	
	public String getDataStructType() {
		return "Plot";
	}
	
	public void toggleMouseTrack(boolean val) {
		this.mouseTrack = val;
	}
	
	public void toggleDataLabel(boolean val) {
		this.dataLabel = val;
	}
	
	public void toggleLogorithmic(boolean val) {
		this.logorithmic = val;
	}
	
	public void setTitle(String t) {
		this.plotTitle = t;
	}

	public String getTitle() {
		return this.plotTitle;
	}
	
	public void setSubTitle(String s) {
		this.plotSubtitle = s;
	}
	
	public String getSubTitle() {
		return this.plotSubtitle;
	}
	
	public void setYLabel(String yaxisName) {
		this.yLabel = yaxisName;
	}
	
	public String getYLabel() {
		return this.yLabel;
	}
	
	public void setXLabel(String xaxisName) {
		this.xLabel = xaxisName;
	}
	
	public String getXLabel() {
		return this.xLabel;
	}
	
	public void setXData(String key, double[] d) {
		if (!xaxisData.containsKey(key)) {
			xaxisData.put(key, d);
		} 
	}
	
	public double[] getXData(String key) {
		if(xaxisData.containsKey(key)) {
			double[] result = xaxisData.get(key);
			return result;
		}
		return null;
	}
	
	public void setYData(String key, double[] d) {
		if (!yaxisData.containsKey(key)) {
			yaxisData.put(key, d);
		}
	}
	
	public double[] getYData(String key) {
		if(yaxisData.containsKey(key)) {
			double[] result = yaxisData.get(key);
			return result;
		}
		return null;
	}
	
	public String getDataStructureRepresentation() {
		String xaxis_json = "";
		for (Entry<String, double[]> entry : xaxisData.entrySet()) {
		    String key = entry.getKey();
		    double[] value = entry.getValue();
		    xaxis_json += OPEN_CURLY + QUOTE + "Plot_Name" + QUOTE + COLON + QUOTE + key + QUOTE + COMMA +
		    			  QUOTE + "xaxis_data" + QUOTE + COLON + OPEN_BOX;
		    for( int i = 0; i < value.length ; i++) {
		    	xaxis_json += QUOTE + value[i] + QUOTE + COMMA;
		    }
		    xaxis_json = xaxis_json.substring(0, xaxis_json.length() - 1);
			xaxis_json += CLOSE_BOX + CLOSE_CURLY + COMMA;
		}
	    xaxis_json = xaxis_json.substring(0, xaxis_json.length() - 1);

		
		String yaxis_json = "";
		for (Entry<String, double[]> entry : yaxisData.entrySet()) {
		    String key = entry.getKey();
		    double[] value = entry.getValue();
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
						  QUOTE +"subtitle" + QUOTE + COLON + QUOTE + this.getSubTitle() + QUOTE + COMMA +
						  QUOTE +"xLabel" + QUOTE + COLON + QUOTE + this.getXLabel() + QUOTE + COMMA +
						  QUOTE +"yLabel" + QUOTE + COLON + QUOTE + this.getYLabel() + QUOTE + COMMA +
						  QUOTE + "axisType" + QUOTE + COLON + this.logorithmic + COMMA +
						  QUOTE + "options" + QUOTE + COLON + OPEN_CURLY + QUOTE + "mouseTracking" + QUOTE + COLON +
				          this.mouseTrack + COMMA + QUOTE + "dataLabels" + QUOTE + COLON + this.dataLabel + CLOSE_CURLY + COMMA +
						  QUOTE + "xaxis_data" + QUOTE + COLON + OPEN_BOX + xaxis_json + CLOSE_BOX + COMMA +
						  QUOTE + "yaxis_data" + QUOTE + COLON + OPEN_BOX + yaxis_json + CLOSE_BOX +
						  CLOSE_CURLY;
		return json_str;
		
	}
}

