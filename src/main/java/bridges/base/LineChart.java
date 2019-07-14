package bridges.base;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @brief Show series of data or functions using a line chart.
 *
 * Line charts (https://en.wikipedia.org/wiki/Line_chart) are used to
 * represent graphically functions such as f(x) = 3*x+1, or data such
 * as temperature of a liquid on a stove as time passes. A individual
 * function or a set of data are called "series".
 *
 * A series is represented by two arrays xdata and ydata such that
 * there is a point at (xdata[0], ydata[0]), an other at (xdata[1],
 * ydata[1]), ... One can add a series by passing the two arrays using
 * setDataSeries() or add the arrays individually using setXData() and
 * setYData().
 *
 * The different series have a label associated with them by default
 * which can be disabled (see toggleSeriesLabel()).
 *
 * The data is typically shown with axes that use a linear
 * scale. However, the scale can be changed to logarithmic for each
 * axis individually (see toggleLogarithmicX() and
 * toggleLogarithmic()).
 *
 * The LineChart can have a title (see getTitle() and setTitle()) and
 * a subtitle (see setSubTitle() and getSubTitle()).
 *
 *
 **/
public class LineChart extends DataStruct{
	
    private String plotTitle;
    private String plotSubtitle;
    private String yLabel;
    private String xLabel;
    private boolean mouseTrack;
    private boolean dataLabel;
    private boolean logarithmicx;
    private boolean logarithmicy;
	
    private HashMap<String, double[]> yaxisData;
    private HashMap<String, double[]> xaxisData;
	
    public LineChart(){
	this.plotTitle = "";
	this.plotSubtitle = "";
	this.yLabel = "";
	this.xLabel = "";
	this.yaxisData = new HashMap<String, double[]>();
	this.xaxisData = new HashMap<String, double[]>();
	this.mouseTrack = false;
	this.dataLabel = true;
	this.logarithmicx = false;
	this.logarithmicy = false;
    }
		
    public String getDataStructType() {
	return "Plot";
    }

    /**
     * @brief Enables or disables mouse tracking
     *
     * Mouse tracking show the value of a data point when the mouse
     * hovers on a data point.
     *
     * @param val Should mouse tracking be activated or not.
     **/
    public void toggleMouseTrack(boolean val) {
	this.mouseTrack = val;
    }

    /**
     * @brief Enables or disables series labels.
     *
     * When enabled, the name of the series will be shown on the line
     * chart.
     *
     * @param val Should series be labeled or not
     **/
    public void toggleSeriesLabel(boolean val) {
	this.dataLabel = val;
    }
	
    /**
     * @brief Change the X-axis scale to logarithmic.
     *
     * When enabled, the X-axis scale becomes logarithmic (as opposed to linear).
     *
     * @param val Should the X-axis use a logarithmic scale?
     **/
    public void toggleLogarithmicX(boolean val) {
	this.logarithmicx = val;
    }

    /**
     * @brief Change the Y-axis scale to logarithmic.
     *
     * When enabled, the Y-axis scale becomes logarithmic (as opposed to linear).
     *
     * @param val Should the Y-axis use a logarithmic scale?
     **/
    public void toggleLogarithmicY(boolean val) {
	this.logarithmicy = val;
    }

    /**
     * @brief Title of the plot
     *
     * @param t the title to be shown
     **/
    public void setTitle(String t) {
	this.plotTitle = t;
    }

    /**
     * @brief Title of the plot
     *
     * @return the title to be shown
     **/
    public String getTitle() {
	return this.plotTitle;
    }
	
    /**
     * @brief Subtitle of the plot
     *
     * @param s the subtitle to be shown
     **/
    public void setSubTitle(String s) {
	this.plotSubtitle = s;
    }

    /**
     * @brief Subtitle of the plot
     *
     * @return the subtitle to be shown
     **/
    public String getSubTitle() {
	return this.plotSubtitle;
    }

    
    /**
     * @brief Change the label for the Y-axis
     *
     * @param yaxisName label to show for the Y-axis
     **/
    public void setYLabel(String yaxisName) {
	this.yLabel = yaxisName;
    }
	
    /**
     * @brief Returns the label for the Y-axis
     *
     * @param label shown for the Y-axis
     **/
    public String getYLabel() {
	return this.yLabel;
    }
	
    /**
     * @brief Change the label for the X-axis
     *
     * @param xaxisName label to use for the X-axis
     **/
    public void setXLabel(String xaxisName) {
	this.xLabel = xaxisName;
    }
	
    /**
     * @brief Returns the label for the Y-axis
     *
     * @param label shown for the Y-axis
     **/
    public String getXLabel() {
	return this.xLabel;
    }


    /**
     * @brief Add a series (or update it)
     *
     * @param series indicates the series to add (or change)
     * @param xdata the X data in the series
     * @param ydata the Y data in the series
     **/
    public void setDataSeries(String seriesName, double[] xdata, double[] ydata) {
	setXData(seriesName, xdata);
	setYData(seriesName, ydata);
    }
    
    /**
     * @brief Changes the X data for a series
     *
     * @param series indicates the series to get
     * @param xdata the X data in the series
     **/
    public void setXData(String series, double[] xdata) {
	if (!xaxisData.containsKey(series)) {
	    xaxisData.put(series, xdata);
	} 
    }
	
    /**
     * @brief Returns the X data for a series
     *
     * @param series indicate the series to get
     * @return the X data of series
     **/
    public double[] getXData(String series) {
	if(xaxisData.containsKey(series)) {
	    double[] result = xaxisData.get(series);
	    return result;
	}
	return null;
    }

    /**
     * @brief Changes the Y data for a series
     *
     * @param series indicates the series to get
     * @param ydata the Y data in the series
     **/
    public void setYData(String series, double[] ydata) {
	if (!yaxisData.containsKey(series)) {
	    yaxisData.put(series, ydata);
	}
    }
	
    /**
     * @brief Returns the Y data for a series
     *
     * @param series indicate the series to get
     * @return the Y data of series
     **/
    public double[] getYData(String series) {
	if(yaxisData.containsKey(series)) {
	    double[] result = yaxisData.get(series);
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
	    QUOTE + "axisType" + QUOTE + COLON + this.logarithmicx + COMMA +
	    QUOTE + "options" + QUOTE + COLON + OPEN_CURLY + QUOTE + "mouseTracking" + QUOTE + COLON +
	    this.mouseTrack + COMMA + QUOTE + "dataLabels" + QUOTE + COLON + this.dataLabel + CLOSE_CURLY + COMMA +
	    QUOTE + "xaxis_data" + QUOTE + COLON + OPEN_BOX + xaxis_json + CLOSE_BOX + COMMA +
	    QUOTE + "yaxis_data" + QUOTE + COLON + OPEN_BOX + yaxis_json + CLOSE_BOX +
	    CLOSE_CURLY;
	return json_str;
		
    }
}

