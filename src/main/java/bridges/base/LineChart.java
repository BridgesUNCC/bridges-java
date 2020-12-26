package bridges.base;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.json.simple.JSONValue;

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
public class LineChart extends DataStruct {

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

	/**
	 * @brief Line chart default constructor
	 *
	 */
	public LineChart() {
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

	/**
	 * 	@brief gets the representation of the line chart as a JSON
	 *
	 *	@return JSON of line chart
	 */
	public String getDataStructType() {
		return "LineChart";
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
	 * @return label shown for the Y-axis
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
	 * @return label shown for the Y-axis
	 **/
	public String getXLabel() {
		return this.xLabel;
	}


	/**
	 * @brief Add a series (or update it)
	 *
	 * @param seriesName indicates the series to add (or change)
	 * @param xdata the X data in the series
	 * @param ydata the Y data in the series
	 **/
	public void setDataSeries(String seriesName, double[] xdata, double[] ydata) {
		setXData(seriesName, xdata);
		setYData(seriesName, ydata);
	}

	/**
	 * @brief Add a series (or update it)
	 *
	 * @param seriesName indicates the series to add (or change)
	 * @param xdata the X data in the series
	 * @param ydata the Y data in the series
	 **/
	public void setDataSeries(String seriesName, ArrayList<Double> xdata, ArrayList<Double> ydata) {
		setXData(seriesName, xdata);
		setYData(seriesName, ydata);
	}
	/**
	 * @brief Add a series (or update it)
	 *
	 * @param seriesName indicates the series to add (or change)
	 * @param xdata the X data in the series
	 * @param ydata the Y data in the series
	 **/
	public void setDataSeries(String seriesName, double[] xdata, ArrayList<Double> ydata) {
		setXData(seriesName, xdata);
		setYData(seriesName, ydata);
	}

	/**
	 * @brief Add a series (or update it)
	 *
	 * @param seriesName indicates the series to add (or change)
	 * @param xdata the X data in the series
	 * @param ydata the Y data in the series
	 **/
	public void setDataSeries(String seriesName, ArrayList<Double> xdata, double[] ydata) {
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
		xaxisData.put(series, xdata);
	}

	private double[] convert(ArrayList<Double> xdata) {
		double[] arr = new double[xdata.size()];
		for (int i = 0; i < xdata.size(); ++i)
			arr[i] = xdata.get(i);
		return arr;
	}

	/**
	 * @brief Changes the X data for a series
	 *
	 * @param series indicates the series to get
	 * @param xdata the X data in the series
	 **/
	public void setXData(String series, ArrayList<Double> xdata) {
		xaxisData.put(series, convert(xdata));
	}


	/**
	 * @brief Returns the X data for a series
	 *
	 * @param series indicate the series to get
	 * @return the X data of series or null if the series is not in there
	 **/
	public double[] getXData(String series) {
		return  xaxisData.get(series);
	}

	/**
	 * @brief Changes the Y data for a series
	 *
	 * @param series indicates the series to get
	 * @param ydata the Y data in the series
	 **/
	public void setYData(String series, ArrayList<Double> ydata) {
		yaxisData.put(series, convert(ydata));
	}

	/**
	 * @brief Changes the Y data for a series
	 *
	 * @param series indicates the series to get
	 * @param ydata the Y data in the series
	 **/
	public void setYData(String series, double[] ydata) {
		yaxisData.put(series, ydata);
	}

	/**
	 * @brief Returns the Y data for a series
	 *
	 * @param series indicate the series to get
	 * @return the Y data of series or null if the data is not in there
	 **/
	public double[] getYData(String series) {
		return yaxisData.get(series);
	}

	/**
	 * @brief check if the LineChart is in a valid state.
	 *
	 * Checks whether the xdata and ydata are present for each series.
	 * Checks whether the xdata and ydata are the same length for each series.
	 * Checks whether the data are positive if logarithmic scales are used.
	 *
	 * Print an error message to System.err if the data structure is in an invalid state.
	 *
	 * @return whether it is in a valid state
	 */
	private boolean check() {
		boolean correct = true;
		for (Entry<String, double[]> entry : xaxisData.entrySet()) {
			String series = entry.getKey();
			double[] xdata = entry.getValue();
			double[] ydata = yaxisData.get(series);
			if (ydata == null) {
				System.out.println("Series \"" + series + "\" has xdata but no ydata");
				correct = false;
			}
			if (xdata.length != ydata.length) {
				System.out.println("Series \"" + series + "\" has xdata and ydata of different sizes");
				correct = false;
			}
			if (logarithmicx) {
				for (int i = 0; i < xdata.length; ++i) {
					if (xdata[i] <= 0) {
						System.out.println("Xaxis scale is logarithmic but series \"" + series + "\" has xdata[" + i + "] = " + xdata[i] + " (should be stricly positive)");
					}
				}
			}
			if (logarithmicy) {
				for (int i = 0; i < ydata.length; ++i) {
					if (ydata[i] <= 0) {
						System.out.println("Yaxis scale is logarithmic but series \"" + series + "\" has ydata[" + i + "] = " + ydata[i] + " (should be stricly positive)");
					}
				}
			}
		}
		for (Entry<String, double[]> entry : yaxisData.entrySet()) {
			String series = entry.getKey();
			double[] ydata = entry.getValue();
			double[] xdata = xaxisData.get(series);
			if (xdata == null) {
				System.out.println("Series: " + series + " has ydata but no xdata");
				correct = false;
			}
			//Everything else already checked.
		}
		return correct;
	}

	public String getDataStructureRepresentation() {
		check();
		String xaxis_json = "";
		for (Entry<String, double[]> entry : xaxisData.entrySet()) {
			String key = entry.getKey();
			double[] value = entry.getValue();
			xaxis_json += OPEN_CURLY + JSONValue.toJSONString("Plot_Name") + COLON + JSONValue.toJSONString( key ) + COMMA +
				JSONValue.toJSONString("xaxis_data") + COLON + OPEN_BOX;
			for ( int i = 0; i < value.length ; i++) {
				xaxis_json += JSONValue.toJSONString(value[i]) + COMMA;
			}
			xaxis_json = xaxis_json.substring(0, xaxis_json.length() - 1);
			xaxis_json += CLOSE_BOX + CLOSE_CURLY + COMMA;
		}
		xaxis_json = xaxis_json.substring(0, xaxis_json.length() - 1);

		String yaxis_json = "";
		for (Entry<String, double[]> entry : yaxisData.entrySet()) {
			String key = entry.getKey();
			double[] value = entry.getValue();
			yaxis_json += OPEN_CURLY + JSONValue.toJSONString("Plot_Name") + COLON + JSONValue.toJSONString( key) + COMMA +
				JSONValue.toJSONString("yaxis_data") + COLON + OPEN_BOX;
			for ( int i = 0; i < value.length ; i++) {
				yaxis_json +=  JSONValue.toJSONString(value[i])  + COMMA;
			}
			yaxis_json = yaxis_json.substring(0, yaxis_json.length() - 1);
			yaxis_json += CLOSE_BOX + CLOSE_CURLY + COMMA;
		}
		yaxis_json = yaxis_json.substring(0, yaxis_json.length() - 1);


		String json_str = JSONValue.toJSONString("plot_title") + COLON +  JSONValue.toJSONString(this.getTitle()) + COMMA +
			JSONValue.toJSONString("subtitle") + COLON + JSONValue.toJSONString(this.getSubTitle())  + COMMA +
			JSONValue.toJSONString("xLabel") + COLON + JSONValue.toJSONString(this.getXLabel()) +  COMMA +
			JSONValue.toJSONString("yLabel") + COLON + JSONValue.toJSONString(this.getYLabel()) + COMMA +
			JSONValue.toJSONString("xaxisType") + COLON + this.logarithmicx + COMMA +
			JSONValue.toJSONString("yaxisType") + COLON + this.logarithmicy + COMMA +
			JSONValue.toJSONString("options") + COLON + OPEN_CURLY + JSONValue.toJSONString("mouseTracking") + COLON +
			this.mouseTrack + COMMA + JSONValue.toJSONString("dataLabels") + COLON + this.dataLabel + CLOSE_CURLY + COMMA +
			JSONValue.toJSONString("xaxis_data") + COLON + OPEN_BOX + xaxis_json + CLOSE_BOX + COMMA +
			JSONValue.toJSONString("yaxis_data") + COLON + OPEN_BOX + yaxis_json + CLOSE_BOX +
			CLOSE_CURLY;
		return json_str;

	}
}

