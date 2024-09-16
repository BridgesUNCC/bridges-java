package bridges.base;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;
import org.json.simple.JSONValue;

/**
 * @brief Support for drawing Bar charts 
 *
 * Bar charts (https://en.wikipedia.org/wiki/Bar_chart) are used to
 * represent categorical data as a series of rectangular bars with heights
 * proportional to the values they represent
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
 * @sa See tutorial on using BarChart at:
 *		https://bridgesuncc.github.io/tutorials/BarChart.html
 *
 * @author Kalpathi Subramanian
 *
 * @date 09/15/24
 *
 **/
public class BarChart extends DataStruct {

	private String title;
	private String subtitle;
	private String xLabel;
	private String yLabel;
	private String tooltipSuffix;
	private String alignment;

	private HashMap<String, double[]> seriesData;
	private Vector<String> seriesBins;

	/**
	 * @brief Line chart default constructor
	 *
	 */
	public BarChart() {
		this.title = "";
		this.subtitle = "";
		this.xLabel = "";
		this.yLabel = "";
		this.alignment = "horizontal";
		this.seriesData = new HashMap<String, double[]>();
		this.seriesBins = new Vector<String>();
	}

	/**
	 * 	@brief gets the data type
	 *
	 *	@return data type of the bar chart
	 */
	public String getDataStructType() {
		return "BarChart";
	}


	/**
	 * @brief Title of the plot
	 *
	 * @param t the title to be shown
	 **/
	public void setTitle(String t) {
		this.title = t;
	}

	/**
	 * @brief Title of the plot
	 *
	 * @return the title to be shown
	 **/
	public String getTitle() {
		return this.title;
	}

	/**
	 * @brief Subtitle of the plot
	 *
	 * @param s the subtitle to be shown
	 **/
	public void setSubTitle(String s) {
		this.subtitle = s;
	}

	/**
	 * @brief Subtitle of the plot
	 *
	 * @return the subtitle to be shown
	 **/
	public String getSubTitle() {
		return this.subtitle;
	}


	/**
	 * @brief Change the bins label (X axis)
	 *
	 * @param xaxisNamel label to show for the X-axis
	 **/
	public void setBinsLabel(String xaxisName) {
		this.xLabel = xaxisName;
	}

	/**
	 * @brief Returns the label for the X-axis
	 *
	 * @return label shown for the X-axis
	 **/
	public String getBinsLabel() {
		return this.xLabel;
	}

	/**
	 * @brief Change the label for the Y-axis
	 *
	 * @param yaxisName label to use for the Y-axis
	 **/
	public void setSeriesLabel(String yaxisName) {
		this.yLabel = yaxisName;
	}

	/**
	 * @brief Returns the label for the Y-axis
	 *
	 * @return label shown for the Y-axis
	 **/
	public String getSeriesLabel() {
		return this.yLabel;
	}

	/**
	 * @brief sets the bar chart alignment
	 *
	 * @param align 
	 **/
	public void setBarAlignment(String align) {
		this.alignment = align;
	}

	/**
	 * @brief gets the bar chart alignment
	 *
	 * @return alignment 
	 **/
	public String getBarAlignment () {
		return this.alignment;
	}

	/**
	 * @brief sets the tooltip suffix
	 *
	 * @param suffix 
	 **/
	public void setTooltipSuffix(String suffix) {
		this.tooltipSuffix = suffix;
	}

	/**
	 * @brief gets the tooltip suffix
	 *
	 * @return suffix 
	 **/
	public String getTooltipSuffix () {
		return this.tooltipSuffix;
	}

	/**
	 *  @brief set the bins for this bar chart
	 *
	 *  @param bins
	 */
	public void setSeriesBins(Vector<String> bins) {
		this.seriesBins = bins;
	}

	/**
	 * @brief Add a series (or update it)
	 *
	 * @param seriesName indicates the series to add (or change)
	 * @param data the X data in the series
	 *
	 **/

	public void addDataSeries(String seriesName, double[] data) {
		seriesData.put(seriesName, data);
	}

	/**
	 *  @brief get the data structure representation as a JSON
	 */
	public String getDataStructureRepresentation() {
		String bins_json = "";
		bins_json += JSONValue.toJSONString("xAxis") + COLON + OPEN_CURLY + 
				JSONValue.toJSONString("categories") + COLON + OPEN_BOX;

		for (String entry : this.seriesBins) {
			bins_json += JSONValue.toJSONString(entry) + COMMA;
		}
		bins_json = bins_json.substring(0, bins_json.length()-1);
		bins_json += CLOSE_BOX + CLOSE_CURLY;

		String series_json = "";
		series_json += JSONValue.toJSONString("series") + COLON + OPEN_BOX;
		for (Entry<String, double[]> entry : seriesData.entrySet()) {
			series_json += OPEN_CURLY;
			String key = entry.getKey();
			double[] value = entry.getValue();
			series_json += OPEN_CURLY + JSONValue.toJSONString("name") + COLON + JSONValue.toJSONString(key) + COMMA + JSONValue.toJSONString("data") + COLON + OPEN_BOX;

			for ( int i = 0; i < value.length ; i++) {
				series_json += JSONValue.toJSONString(value[i]) + COMMA;
			}
			series_json = series_json.substring(0, series_json.length() - 1);
			series_json += CLOSE_BOX + CLOSE_CURLY + COMMA;
		}
		series_json = series_json.substring(0, series_json.length() - 1);
		series_json += CLOSE_BOX;


		String json_str = JSONValue.toJSONString("plot_title") + COLON +  JSONValue.toJSONString(this.getTitle()) + COMMA +
			JSONValue.toJSONString("subtitle") + COLON + JSONValue.toJSONString(this.getSubTitle())  + COMMA +
			JSONValue.toJSONString("xLabel") + COLON + JSONValue.toJSONString(this.getBinsLabel()) +  COMMA +
			JSONValue.toJSONString("yLabel") + COLON + JSONValue.toJSONString(this.getSeriesLabel()) + COMMA +
			JSONValue.toJSONString("tooltipSuffix") + COLON + JSONValue.toJSONString(getTooltipSuffix()) + COMMA +
			JSONValue.toJSONString("alignment") + COLON + JSONValue.toJSONString(getBarAlignment()) + COMMA +
			JSONValue.toJSONString("xaxis_data") + COLON + OPEN_CURLY + bins_json + CLOSE_CURLY + COMMA + 
			JSONValue.toJSONString("yaxis_data") + COLON + OPEN_CURLY + series_json + CLOSE_CURLY + CLOSE_CURLY;

		return json_str;
	}
}

