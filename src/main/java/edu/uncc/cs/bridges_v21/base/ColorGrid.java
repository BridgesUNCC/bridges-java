package bridges.base;

import bridges.base.Color;
import java.util.*;


/**
 * @brief This is a class in BRIDGES for representing a 2x2 grid.
 * @author David Burlinson
 * @param
**/
public class ColorGrid extends Grid<Color> {

  private static Color baseColor = new Color(0,0,0,1.0f);

  public String getDataStructType() {
		return "ColorGrid";
	}

  /**
   * Grid constructors
   *
  **/
  public ColorGrid() {
    this(gridSize, baseColor);
  }

  /**
   * Grid constructor with color string argument
   *
   * @param color - String representing the base color for the grid
   *
  **/
  public ColorGrid (String color)  {
    this(gridSize, color);
  }

  /**
   * Grid constructor with size argument
   *
   * @param size - Integer representing the number of rows (and columns) of the grid
   *
  **/
  public ColorGrid (Integer size) {
    this(size, baseColor);
  }

  /**
   * Grid constructor with size and color string argument
   *
   * @param size, color - The number of rows (and columns) and the base color of the grid
   *
  **/
  public ColorGrid (Integer size, String color)  {
    if(size <= 0 || size > maxGridSize) {
      throw new IllegalArgumentException(
        "\nInvalid size (" + size + "): please use a value between 0 and " + maxGridSize + "!\n");
    }
    baseColor.setColor(color);
    gridSize = size;

    initializeGrid();
  }

  /**
   * Grid constructor with size and color object argument
   *
   * @param size, color - The number of rows (and columns) and the base color of the grid
   *
  **/
  public ColorGrid (Integer size, Color color)  {
    if(size <= 0 || size > maxGridSize) {
      throw new IllegalArgumentException(
        "\nInvalid size (" + size + "): please use a value between 0 and " + maxGridSize + "!\n");
    }
    baseColor = color;
    gridSize = size;

    initializeGrid();
  }


  /**
   * Populate the 2x2 grid with the base color
   *
  **/
  private void initializeGrid() {
    // set up outer list capacity (rows)
    grid = new ArrayList<ArrayList<Color>>(gridSize);

    // set up inner list capacities (cols)
    for(int i = 0; i < gridSize; i++) {
      grid.add(new ArrayList<Color>(gridSize));

      // initialize inner lists
      for(int j = 0; j < gridSize; j++) {
        grid.get(i).add(baseColor);
      }
    }
  }


  /**
   * get the (row, col) element in the grid
   *
   * @param row, col - indices into the grid
   * @return Color - returns the Color object stored at the [row,col] location
  **/
  public Color get(Integer row, Integer col) {
    try {
      return grid.get(row).get(col);
    } catch (Exception e) {
			e.printStackTrace();
      return null;
		}
  }

  /**
   * set the (row, col) element in the grid
   *
  **/
  public void set(Integer row, Integer col, Color color) {
    try {
      grid.get(row).set(col, color);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * get the JSON representation of the color grid
   *
   * @return the JSON representation of the color grid
  **/
  public String getDataStructureRepresentation () {

    StringBuilder nodes_JSON = new StringBuilder();
    StringBuilder colors_JSON = new StringBuilder();
    String color;
    Map<String, Integer> color_index = new HashMap<String, Integer>();
    Integer color_count = 0;

    for (int i = 0; i < gridSize; i++) {
      if (grid.get(i) != null) {
        for (int j = 0; j < gridSize; j++) {
          if (grid.get(i).get(j) != null) {
            color = grid.get(i).get(j).getRepresentation();

            // we have already seen this color
            if(color_index.containsKey(color)) {
              nodes_JSON.append(color_index.get(color) + COMMA);
            } else { // new color
              color_index.put(color, color_count);
              nodes_JSON.append(color_count + COMMA);
              colors_JSON.append(color + COMMA);
              color_count++;
            }
          }
        }
      }
    }

    // remove last commas
    nodes_JSON.setLength(nodes_JSON.length() - 1);
    colors_JSON.setLength(colors_JSON.length() - 1);

    String json_str = QUOTE + "nodes" + QUOTE + COLON +
      OPEN_BOX  + nodes_JSON + CLOSE_BOX + COMMA + QUOTE + "colors" + QUOTE + COLON + OPEN_BOX + colors_JSON + CLOSE_BOX + CLOSE_CURLY;

    return json_str;
  }
}
