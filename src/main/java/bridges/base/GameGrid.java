package bridges.base;

import bridges.base.Color;
import java.util.*;
import java.nio.ByteBuffer;
import org.apache.commons.codec.binary.Base64;

/**
 * @brief This is a class in BRIDGES for representing an (m x n) grid. Each position in the grid will hold a GameCell object, each of which has a foreground color, background color, and a symbol.
 *
 * @author David Burlinson
**/
public class GameGrid extends Grid<GameCell> {

  public String getDataStructType() {
		return "GameGrid";
	}

  /**
   * Default Game Grid constructor
   *
  **/
  public GameGrid() {
    this(gridSize[0], gridSize[1]);
  }

  /**
   * Grid constructor with grid size arguments
   *
   * @param rows - int representing the number of rows of the grid
   * @param cols - int representing the number of columns of the grid
   *
  **/
  public GameGrid (int rows, int cols) {
    super(rows, cols);

    initializeGameGrid();
  }

  /*
   * Populate the grid with default game cells
   */
  private void initializeGameGrid() {
    for(int i = 0; i < gridSize[0]; i++) {
      for(int j = 0; j < gridSize[1]; j++) {
        this.set(i, j, new GameCell());
      }
    }
  }

  /**
   *  Set background color of a cell using an enum argument
   *
   *  @param row, col - integer indices specifying the position to modify
   *  @param color - Named Color enum argument to set the background at the chosen position
   */
  public void setBGColor(Integer row, Integer col, NamedColor color) {
    this.get(row, col).setBGColor(color);
  }

  /**
   *  Set foreground color of a cell using an enum argument
   *
   *  @param row, col - integer indices specifying the position to modify
   *  @param color - Named Color enum argument to set the foreground at the chosen position
   */
  public void setFGColor(Integer row, Integer col, NamedColor color) {
    this.get(row, col).setFGColor(color);
  }

  /**
   *  Set background color of a cell using an enum argument
   *
   *  @param row, col - integer indices specifying the position to modify
   *  @param color - String color argument to set the background at the chosen position
   */
  public void setBGColor(Integer row, Integer col, String color) {
    this.setBGColor(row, col, NamedColor.valueOf(color));
  }

  /**
   *  Set background color of a cell using an enum argument
   *
   *  @param row, col - integer indices specifying the position to modify
   *  @param color - String color argument to set the background at the chosen position
   */
  public void setFGColor(Integer row, Integer col, String color) {
    this.setFGColor(row, col, NamedColor.valueOf(color));
  }


  /**
   *  Draw a symbol at the specified location
   *  @param row, col - integer indices specifying the position to modify
   *  @param symbol - Integer symbol argument to set the symbol at the chosen position
   */
  public void drawObject(Integer row, Integer col, Integer symbol) {
    this.get(row, col).setSymbol(symbol);
  }

  /**
   *  Draw a symbol at the specified location
   *  @param row, col - integer indices specifying the position to modify
   *  @param symbol - Integer symbol argument to set the symbol at the chosen position
   *  @param color - String color argument to set the background at the chosen position
   */
  public void drawObject(Integer row, Integer col, Integer symbol, String color) {
    this.drawObject(row, col, symbol, NamedColor.valueOf(color));
  }

  /**
   *  Draw a symbol at the specified location
   *  @param row, col - integer indices specifying the position to modify
   *  @param symbol - Integer symbol argument to set the symbol at the chosen position
   *  @param color - Named Color enum argument to set the foreground at the chosen position
   */
  public void drawObject(Integer row, Integer col, Integer symbol, NamedColor color) {
    this.get(row, col).setSymbol(symbol);
    this.get(row, col).setFGColor(color);
  }


  /**
   * get the JSON representation of the game grid. Contains separate foreground, background, and symbol arrays

        ***each of which can be run length encoded then base64'd***

   *
   * @return the JSON representation of the game grid
  **/
  public String getDataStructureRepresentation() {

    // Maintain a bytebuffer for the byte representations of each grid color
    // ByteBuffer imageBytes = ByteBuffer.allocate(4 * gridSize[0] * gridSize[1]);
    GameCell gc;
    int totalCells = gridSize[0] * gridSize[1];
    int count = 0;
    int[] bg = new int[totalCells];
    int[] fg = new int[totalCells];
    int[] symbols = new int[totalCells];

    // populate int arrays
    for (int i = 0; i < gridSize[0]; i++) {
      if (grid.get(i) != null) {
        for (int j = 0; j < gridSize[1]; j++) {
          if (grid.get(i).get(j) != null) {
            gc = grid.get(i).get(j);
            bg[count] = gc.getBGColor();
            fg[count] = gc.getFGColor();
            symbols[count] = gc.getSymbol();
            count++;
          }
        }
      }
    }

    System.out.println("\nSANITY CHEEEEECK:");
    System.out.println(bg.length + ", " + fg.length + ", " + symbols.length);

    System.out.print("bgcolors: ");
    for(int i = 0; i < bg.length; i++) {
      System.out.print(bg[i]);
    }
    System.out.print("\nfgcolors: ");
    for(int i = 0; i < fg.length; i++) {
      System.out.print(fg[i]);
    }
    System.out.print("\nsymbols: ");
    for(int i = 0; i < symbols.length; i++) {
      System.out.print(symbols[i]);
    }

    // // Add the byte representation of the grid
    // String json_str = QUOTE + "nodes" + QUOTE + COLON +
    //   OPEN_BOX  + QUOTE + Base64.encodeBase64String(imageBytes.array()) + QUOTE + CLOSE_BOX + COMMA;
    //
    // // Specify the dimensions of the grid
    // json_str += QUOTE + "dimensions" + QUOTE + COLON +
    //     OPEN_BOX + gridSize[0] + "," + gridSize[1] + CLOSE_BOX + CLOSE_CURLY;
    //
    // return json_str;
    return ":)";
  }
}
