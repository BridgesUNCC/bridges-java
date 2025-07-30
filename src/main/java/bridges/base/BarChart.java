package bridges.base;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.ArrayList;
import org.json.simple.JSONValue;


/**
 * @brief Support for drawing Bar charts
 *
 * Bar charts (https://en.wikipedia.org/wiki/Bar_chart) are used to
 * represent categorical data as a series of rectangular bars with length
 * proportional to the values they represent.
 *
 * Series in a bar chart provides data for a number of categories
 * (sometimes called bins). Categories are defined using
 * setCategories() and the series are added using addDataSeries().
 * The series are rendered in the order in which they were added. Once
 * a series has been added, it can not be modified.
 *
 * One should always define the categories before adding data. Changing the
 * categories after series have been added will throw exceptions;
 * adding series with different number of values than the number of
 * categories will throw an exception.
 *
 * The Bar charts can have a title, subtitle. The charts can be
 * horizontal or vertically oriented, using setBarOrientation().
 *
 * A tooltip indicating the value of a series in a particular bin is
 * displayed by hovering on a bar. One can append a string to the
 * value using setTooltipSuffix() to specify units in the tooltip if desired.
 *
 * @sa See tutorial on using BarChart at:
 *		https://bridgesuncc.github.io/tutorials/BarChart.html
 *
 * @author Kalpathi Subramanian, Erik Saule
 *
 * @date 09/15/24
 *
 **/
public class BarChart extends DataStruct {
	class Pair<K, V> {
		private K key;
		private V value;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getFirst() {
			return key;
		}

		public void setFirst(K key) {
			this.key = key;
		}

		public V getSecond() {
			return value;
		}

		public void setSecond(V value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Pair{" + "key=" + key + ", value=" + value + '}';
		}
	}


	private String title;
	private String subtitle;
	private String cLabel;
	private String vLabel;
	private String tooltipSuffix;
	private String orientation;

	private ArrayList<Pair<String, double[] >> seriesData;
	private Vector<String> categories;

	/**
	 * @brief Line chart default constructor
	 *
	 */
	public BarChart() {
		this.title = "";
		this.subtitle = "";
		this.cLabel = "";
		this.vLabel = "";
		this.orientation = "horizontal";
		this.seriesData = new ArrayList<Pair<String, double[] >> ();
		this.categories = new Vector<String>();
	}

	/**
	 * 	@brief gets the data structure type.
	 *
	 *      This is essentially a BRIDGES internal function. The end-user should never need it.
	 *
	 *	@return data type of the bar chart
	 */
	public String getDataStructType() {
		return "BarChart";
	}


	/**
	 * @brief Title of the plot
	 *
	 * @param t the title of the bar chart
	 **/
	public void setTitle(String t) {
		this.title = t;
	}

	/**
	 * @brief Title of the plot
	 *
	 * @return the title of the bar chart
	 **/
	public String getTitle() {
		return this.title;
	}

	/**
	 * @brief Set subtitle of the plot
	 *
	 * @param s the subtitle of the bar chart
	 **/
	public void setSubTitle(String s) {
		this.subtitle = s;
	}

	/**
	 * @brief Get subtitle of the plot
	 *
	 * @return the subtitle of the bar chart.
	 **/
	public String getSubTitle() {
		return this.subtitle;
	}


	/**
	 * @brief Set the category axis label
	 *
	 * @param cAxisName label for the category axis
	 **/
	public void setCategoriesLabel(String cAxisName) {
		this.cLabel = cAxisName;
	}

	/**
	 * @brief Returns the label for the category axis
	 *
	 * @return labelfor the category axis
	 **/
	public String getCategoriesLabel() {
		return this.cLabel;
	}

	/**
	 * @brief Set the label for the value axis
	 *
	 * @param yaxisName label for the value axis
	 **/
	public void setValueLabel(String vAxisName) {
		this.vLabel = vAxisName;
	}

	/**
	 * @brief Returns the label for the vlue axis
	 *
	 * @return label for the value axis
	 **/
	public String getValueLabel() {
		return this.vLabel;
	}

	/**
	 * @brief sets the bar chart orientation
	 *
	 * Bar charts can be "horizontal" or "vertical". Throws exception on other values.
	 *
	 * @param orient
	 **/
	public void setBarOrientation(String orient) {
		if (!orient.equals("horizontal") && !orient.equals("vertical"))
			throw new IllegalArgumentException("Orientation should be \"horizontal\" or \"vertical\".");
		this.orientation = orient;
	}

	/**
	 * @brief gets the bar chart orientation
	 *
	 * @return orientation (either "horizontal" or "vertical")
	 **/
	public String getBarOrientation () {
		return this.orientation;
	}

	/**
	 * @brief sets the tooltip suffix
	 *
	 * This appends a string to the values in the hover tooltip.
	 *
	 * @param suffix string appended to the values in the tooltip
	 **/
	public void setTooltipSuffix(String suffix) {
		this.tooltipSuffix = suffix;
	}

	/**
	 * @brief gets the tooltip suffix
	 *
	 * @return suffix appended to values
	 **/
	public String getTooltipSuffix () {
		return this.tooltipSuffix;
	}

	/**
	 *  @brief set the category labels for this bar chart
	 *
	 * Will throw an exception if there are already data series defined.
	 *
	 *  @param categories the name of each category
	 */
	public void setCategories(Vector<String> categories) {
		if (seriesData.size() > 0)
			throw new IllegalStateException ("Can't change categoriess after series have been added.");
		this.categories = categories;
	}

	/**
	 * @brief Add a series of data
	 *
	 * This will throw an exception if the
	 * data vector does not have the same
	 * size as the number of categories.
	 *
	 * This will throw exceptions if two series have the same name.
	 *
	 * @param seriesName indicates the name of the data to add
	 * @param data values of that serie for each category
	 **/
	public void addDataSeries(String seriesName, double[] data) {
		if (data.length != categories.size())
			throw new IllegalArgumentException ("The data vector should have the same size as the number of categories.");

		for (Pair<String, double[]> entry : seriesData) {
			String key = entry.getFirst();
			if (seriesName.equals(key))
				throw new IllegalArgumentException ("Can't have two series with the same name.");
		}

		seriesData.add(new Pair<String, double[]>(seriesName, data));
	}

	/**
	 *  @brief get the data structure representation as a JSON
	 */
	public String getDataStructureRepresentation() {
		String bins_json = "";
		bins_json += JSONValue.toJSONString("xAxis") + COLON + OPEN_CURLY +
			JSONValue.toJSONString("categories") + COLON + OPEN_BOX;

		for (String entry : this.categories) {
			bins_json += JSONValue.toJSONString(entry) + COMMA;
		}
		bins_json = bins_json.substring(0, bins_json.length() - 1);
		bins_json += CLOSE_BOX + CLOSE_CURLY;

		String series_json = "";
		series_json += JSONValue.toJSONString("series") + COLON + OPEN_BOX;
		for (Pair<String, double[]> entry : seriesData) {
			String key = entry.getFirst();
			double[] value = entry.getSecond();
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
			JSONValue.toJSONString("xLabel") + COLON + JSONValue.toJSONString(this.getCategoriesLabel()) +  COMMA +
			JSONValue.toJSONString("yLabel") + COLON + JSONValue.toJSONString(this.getValueLabel()) + COMMA +
			JSONValue.toJSONString("tooltipSuffix") + COLON + JSONValue.toJSONString(getTooltipSuffix()) + COMMA +
			JSONValue.toJSONString("alignment") + COLON + JSONValue.toJSONString(getBarOrientation()) + COMMA +
			JSONValue.toJSONString("xaxis_data") + COLON + OPEN_CURLY + bins_json + CLOSE_CURLY + COMMA +
			JSONValue.toJSONString("yaxis_data") + COLON + OPEN_CURLY + series_json + CLOSE_CURLY + CLOSE_CURLY;

		return json_str;
	}
}

