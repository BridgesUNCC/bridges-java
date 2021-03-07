
package bridges.base;
import java.util.*;

/**
 * @brief This is a class in BRIDGES for representing an (m x n) grid.
 *
 * @author David Burlinson
 * @date  5/14/18, 7/14/19
 **/

public class Grid<E> extends DataStruct {

	protected ArrayList<ArrayList<E>> grid;
	protected static final int[] defaultGridSize = {10, 10};
	protected int[] gridSize;
	protected static int[] maxGridSize = {1080, 1920};

	/**
	 *   Get data structure name
	 *   @return data type name
	 */
	public String getDataStructType() {
		return "Grid";
	}

	/**
	 * Construct a grid with default sizes
	 *
	 */
	public Grid() {
		this( defaultGridSize );
	}

	/**
	 * Construct a size x size grid
	 *
	 */
	public Grid(int size) {
		this(new int[] {size, size});
	}

	/**
	 * @brief Construct a rows x cols  size grid
	 *
	 * @param rows number of rows in grid
	 * @param cols number of rows in grid
	 */
	public Grid(int rows, int cols) {
		this(new int[] {rows, cols});
	}

	/**
	 *
	 * @brief Construct a size[0] by size[1] sized grid
	 * @param size[] specifies rows and column sizes of the grid
	 *
	 */
	public Grid (int[] size)  {
		if ((size[0] <= 0 || size[0] > maxGridSize[0]) ||
			(size[1] <= 0 || size[1] > maxGridSize[1])) {
			throw new IllegalArgumentException(
				"\nInvalid size: [" + size[0] + "," + size[1] + "]... please use values between (0 and " + maxGridSize[0] + "] for rows and values between (0 and " + maxGridSize[1] + "] for columns!\n");
		}

		gridSize = size.clone();

		// set up outer list capacity (rows)
		grid = new ArrayList<ArrayList<E>>(size[0]);

		for (int i = 0; i < size[0]; i++) {
			// set up inner lists (columns)
			grid.add(new ArrayList<E>(size[1]));

			// initialize values in inner lists
			for (int j = 0; j < size[1]; j++) {
				grid.get(i).add(null);
			}
		}
	}

	/**
	 *
	 * @brief Get the grid dimensions
	 *
	 * @return an array of two values (rows, cols) of the grid
	 */
	public int[] getDimensions() {
		return new int[] {gridSize[0], gridSize[1]};
	}

	/**
	 *	@brief Get the (row, col) element in the grid
	 * 	@param row row index
	 * 	@param col column index
	 *  @return the element at (row, col)
	 */
	public E get(Integer row, Integer col) {
		try {
			return grid.get(row).get(col);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *	Set the (row, col) element in the grid
	 * 	@param row,col  cell to change
	 *	@param val the value to be set to
	 */
	public void set(Integer row, Integer col, E val) {
		try {
			grid.get(row).set(col, val);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @brief Get data structure representation
	 * @return the JSON of the grid representation
	 */
	public String getDataStructureRepresentation() {
		return null;
	}
}
