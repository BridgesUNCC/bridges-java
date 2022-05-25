package bridges.data_src_dependent;

/**
 * @brief Object that holds elevation data retrieved from NOAA repository
 *
 * A user would not normally  create an ElevationData object but rather obtain one from calling bridges::connect::DataSource::getElevationData()
 *
 * A tutorial on how to use the Elevation dataset is available at: https://bridgesuncc.github.io/tutorials/Data_Elevation.html
 *
 * @author Jay Strahler
 *
 * @date 12/26/20
 */

public class ElevationData {

	// elevation data
	private int [][] data;

	// data dimensions
	private int cols;
	private int rows;

	// origin
	private double xll;
	private double yll;

	// cell size
	private double cellsize;

	// max val in dataset
	private int maxVal;
    private int minVal;


	/**
	 *
	 * constructors
	 *
	 */
	public ElevationData() {
		this.data = null;
		this.cols = 0;
		this.rows = 0;
		this.xll = 0;
		this.yll = 0;
		this.cellsize = 0;
		this.maxVal = Integer.MIN_VALUE;
		this.minVal = Integer.MAX_VALUE;
	}

	/**
	 * constructors
	 *
	 * @param data  elevation data (2D array of ints)
	 * @param cols  width of data
	 * @param rows  height of data
	 * @param xll  lower left x coord of origin
	 * @param yll  lower left y coord of origin
	 * @param cellsize  size of each cell (resolution)
	 * @param maxVal  max value in dataset
	 */
    public ElevationData (int[][] data, int cols, int rows, double xll, double yll, double cellsize, int maxVal, int minVal) {
		this.setData(data);
		this.setCols(cols);
		this.setRows(rows);
		this.setxll(xll);
		this.setyll(yll);
		this.setCellSize(cellsize);
		this.setMaxVal(maxVal);
		this.setMinVal(minVal);
	}

	/**
	 *
	 * get elev. data
	 *
	 * @return the current dataset (2D array of ints)
	 */
	public int[][] getData() {
		return data;
	}

    public int getVal(int r, int c) {
	return data[r][c];
    }
    
	/**
	 *
	 * set elev. data
	 *
	 * @param data (2D array of ints)
	 */
	public void setData(int[][] data) {
		this.data = data;

		//recompute min and max
		this.maxVal = Integer.MIN_VALUE;
		this.minVal = Integer.MAX_VALUE;

		for (int i=0; i<rows; i++)
		    for (int j=0; j<cols; j++) {
			if (data[i][j] > this.maxVal)
			    this.maxVal = data[i][j];
			if (data[i][j] < this.minVal)
			    this.minVal = data[i][j];
		    }
	}

	/**
	 *
	 * get width
	 *
	 * @return dataset width
	 */
	public int getCols() {
		return cols;
	}

	/**
	 *
	 * set width
	 * @param cols  width to be set
	 *
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 *
	 * get height
	 *
	 * @return dataset height
	 */
	public int getRows() {
		return rows;
	}

	/**
	 *
	 * set height
	 * @param rows height to be set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 *
	 * get origin X
	 *
	 * @return lower left x coord of origin
	 */
	public double getxll() {
		return xll;
	}

	/**
	 *
	 * set origin X
	 *
	 * @param xll lower left x coord of origin to be set
	 */
	public void setxll(double xll) {
		this.xll = xll;
	}

	/**
	 *
	 * get origin Y
	 *
	 * @return lower left y coord of origin
	 */
	public double getyll() {
		return yll;
	}

	/**
	 *
	 * set origin X
	 *
	 * @param yll lower left y coord of origin to be set
	 */
	public void setyll(double yll) {
		this.yll = yll;
	}

	/**
	 *
	 * get cell size
	 *
	 * return the current cell size
	 */
	public double getCellSize() {
		return cellsize;
	}

	/**
	 *
	 * set cell size
	 *
	 * @param cellsize  set the cell size
	 */
	public void setCellSize(double cellsize) {
		this.cellsize = cellsize;
	}

	/**
	 *
	 * get max value
	 *
	 * @return max value of dataset
	 */
	public int getMaxVal() {
		return maxVal;
	}

	/**
	 *
	 * set max value
	 *
	 * @param maxVal max value to be set
	 */
	public void setMaxVal(int maxVal) {
		this.maxVal = maxVal;
	}

	/**
	 *
	 * get min value
	 *
	 * @return min value of dataset
	 */
	public int getMinVal() {
		return minVal;
	}

	/**
	 *
	 * set min value
	 *
	 * @param minVal min value to be set
	 */
	public void setMinVal(int minVal) {
		this.minVal = minVal;
	}

}
