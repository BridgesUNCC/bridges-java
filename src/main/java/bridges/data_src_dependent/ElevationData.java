package bridges.data_src_dependent;

/**
 * Object that holds elevation data retrieved from NOAA repository
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
		this.maxVal = 0;
	}

	/**
	 * constructors
	 *
	 * @param data  elevation data (2D array of ints)
	 * @param cols  width of data 
	 * @param rows  height of data
	 * @param x11  lower left x coord of origin
	 * @param y11  lower left y coord of origin
	 * @param cellsize  size of each cell (resolution)
	 * @param maxVal  max value in dataset
	 */
	public ElevationData (int[][] data, int cols, int rows, double xll, double yll, double cellsize, int maxVal) {
		this.setData(data);
		this.setCols(cols);
		this.setRows(rows);
		this.setxll(xll);
		this.setyll(yll);
		this.setCellSize(cellsize);
		this.setMaxVal(maxVal);
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

	/**
	 *
	 * set elev. data
	 *
	 * @param data (2D array of ints)
	 */
	public void setData(int[][]data) {
		this.data = data;
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
	 * @param x11 lower left x coord of origin to be set
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
	 * @param y11 lower left y coord of origin to be set
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
}
