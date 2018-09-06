package bridges.base;
import java.util.*;

/**
 * @brief This is a class in BRIDGES for representing an (m x n) grid.
 * @author David Burlinson
 * @param
**/

public class Grid<E> extends DataStruct {

  protected ArrayList<ArrayList<E>> grid;
  protected static int[] gridSize = {10, 10};
  protected static int[] maxGridSize = {1920, 1080};

  public String getDataStructType() {
		return "Grid";
	}

  /**
	 * Grid constructors
   *
	 */
	public Grid() {
    this(gridSize);
	}

  public Grid(int size) {
    this(new int[]{size, size});
  }

  public Grid(int rows, int cols) {
    this(new int[]{rows, cols});
  }

  public Grid (int[] size)  {
    if((size[0] <= 0 || size[0] > maxGridSize[0]) ||
      (size[1] <= 0 || size[1] > maxGridSize[1])){
      throw new IllegalArgumentException(
        "\nInvalid size: [" + size[0] + "," + size[1] + "]... please use values between (0 and " + maxGridSize[0] + "] for rows and values between (0 and " + maxGridSize[1] + "] for columns!\n");
    }

    // set up outer list capacity (rows)
    grid = new ArrayList<ArrayList<E>>(size[0]);


    for(int i = 0; i < size[0]; i++) {
      // set up inner lists (columns)
      grid.add(new ArrayList<E>(size[1]));

      // initialize values in inner lists
      for(int j = 0; j < size[1]; j++) {
        grid.get(i).add(null);
      }
    }
  }

  public int[] getDimensions() {
    return new int[]{gridSize[0], gridSize[1]};
  }

  // get the (row, col) element in the grid
  public E get(Integer row, Integer col) {
    try {
      return grid.get(row).get(col);
    } catch (Exception e) {
			e.printStackTrace();
      return null;
		}
  }

  // set the (row, col) element in the grid
  public void set(Integer row, Integer col, E val) {
    try {
      grid.get(row).set(col, val);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
