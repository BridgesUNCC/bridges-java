package bridges.base;
import java.util.*;

/**
 * @brief This is a class in BRIDGES for representing a 2x2 grid.
 * @author David Burlinson
 * @param
**/

public class Grid<E> extends DataStruct {

  protected ArrayList<ArrayList<E>> grid;
  protected static Integer gridSize = 100;
  protected static Integer maxGridSize = 500;

  public String getDataStructType() {
		return "Grid";
	}

  /**
	 * Grid constructor
   *
	 */
	public Grid() {
    this(gridSize);
	}

  public Grid (Integer size)  {
    if(size <= 0 || size > maxGridSize) {
      throw new IllegalArgumentException(
        "\nInvalid size (" + size + "): please use a value between 0 and " + maxGridSize + "!\n");
    }

    // set up outer list capacity (rows)
    grid = new ArrayList<ArrayList<E>>(size);

    // set up inner list capacities (cols)
    for(int i = 0; i < size; i++) {
      grid.add(new ArrayList<E>(size));

      // initialize inner lists
      for(int j = 0; j < size; j++) {
        grid.get(i).add(null);
      }
    }
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
