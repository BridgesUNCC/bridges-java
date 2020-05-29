package bridges.data_src_dependent;

/**
 * Object that holds elevation data retrieved from NOAA repository

 * @author Jay Strahler
 *
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
	 */
	public int[][] getData() {
		return data;
	}

	/**
	 *
	 * set elev. data
	 *
	 */
	public void setData(int[][]data) {
		this.data = data;
	}

	/**
	 *
	 * get width
	 *
	 */
	public int getCols() {
		return cols;
	}

	/**
	 *
	 * set width
	 *
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 *
	 * get height
	 *
	 */
	public int getRows() {
		return rows;
	}

	/**
	 *
	 * set height
	 *
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 *
	 * get origin X
	 *
	 */
	public double getxll() {
		return xll;
	}

	/**
	 *
	 * set origin X
	 *
	 */
	public void setxll(double xll) {
		this.xll = xll;
	}

	/**
	 *
	 * get origin Y
	 *
	 */
	public double getyll() {
		return yll;
	}

	/**
	 *
	 * set origin X
	 *
	 */
	public void setyll(double yll) {
		this.yll = yll;
	}

	/**
	 *
	 * get cell size
	 *
	 */
	public double getCellSize() {
		return cellsize;
	}

	/**
	 *
	 * set cell size
	 *
	 */
	public void setCellSize(double cellsize) {
		this.cellsize = cellsize;
	}

	/**
	 *
	 * get max value
	 *
	 */
	public int getMaxVal() {
		return maxVal;
	}

	/**
	 *
	 * set max value
	 *
	 */
	public void setMaxVal(int maxVal) {
		this.maxVal = maxVal;
	}
}
