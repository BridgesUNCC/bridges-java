package bridges.base;

import bridges.base.Color;
import java.util.*;
import java.nio.ByteBuffer;
import org.apache.commons.codec.binary.Base64;

/**
 * @brief This class in BRIDGES is part of the BRIDGS Game API. It is for 
 *	representing an (m x n) game grid. Each position in the grid will hold 
 * 	a GameCell object, each of which has a foreground color, background color, 
 *	and a symbol.
 *
 *  The API supports 2D nonblocking games
 *
 * @author David Burlinson, Erik Saule
 *
 **/
public class GameGrid extends Grid<GameCell> {

	ByteBuffer bf_bg;
	ByteBuffer bf_fg;
	ByteBuffer bf_symbols;
	String encoding = "raw";

	/** 
	 *	@brief Enable changing the game grid encoding when building JSON 
	 *		representation.
	 *
	 *  @param encoding type of encoding. Supports "raw" and "rle"
	 **/
	public void setEncoding(String encoding) {
		if (encoding.equals("raw") || encoding.equals("rle")) {
			this.encoding = encoding;
		}
		else  {
			System.err.println("Unrecognized encoding \'" + encoding + "\', defaulting to raw. Options: raw, rle");
			this.encoding = "raw";
		}
	}

	/**
	 *	@brief Get the data structure type (string)
	 *
	 *	@return data structure type
	 */
	public String getDataStructType() {
		return "GameGrid";
	}

	/**
	 * Default Game Grid constructor
	 * Default size is 10 by 10
	 *
	**/
	public GameGrid() {
		this(defaultGridSize[0], defaultGridSize[1]);
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
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				this.set(i, j, new GameCell());
			}
		}
		bf_bg = ByteBuffer.allocate(gridSize[0] * gridSize[1]);
		bf_fg = ByteBuffer.allocate(gridSize[0] * gridSize[1]);
		bf_symbols = ByteBuffer.allocate(gridSize[0] * gridSize[1]);

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
	 *  Get background color of a cell 
	 *
	 *  @param row, col - integer indices specifying the position 
	 *
	 *	@return background color of cell
	 */
	public NamedColor getBGColor(Integer row, Integer col) {
		return this.get(row, col).getBGColor();
	}

	/**
	 *  Get symbol at a cell 
	 *
	 *  @param row, col - integer indices specifying the position 
	 *
	 *	@return cell symbol
	 */
	public NamedSymbol getSymbol(Integer row, Integer col) {
		return this.get(row, col).getSymbol();
	}

	/**
	 *  Get symbol color of a cell 
	 *
	 *  @param row, col - integer indices specifying the position 
	 *
	 *	@return cell symbol color
	 */
	public NamedColor getSymbolColor(Integer row, Integer col) {
		return this.get(row, col).getFGColor();
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
	 *  Set foreground color of a cell using an enum argument
	 *
	 *  @param row, col - integer indices specifying the position to modify
	 *  @param color - String color argument to set the foreground at the chosen position
	 */
	public void setFGColor(Integer row, Integer col, String color) {
		this.setFGColor(row, col, NamedColor.valueOf(color));
	}


	/**
	 *  Draw a symbol at the specified location
	 *  @param row, col - integer indices specifying the position to modify
	 *  @param symbol - Integer symbol argument to set the symbol at the chosen position
	 */
	public void drawSymbol(Integer row, Integer col, Integer symbol) {
		this.get(row, col).setSymbol(symbol);
	}

	/**
	 *  Draw a symbol at the specified location
	 *  @param row, col - integer indices specifying the position to modify
	 *  @param symbol - Named symbol enum argument to set the symbol at the chosen position
	 */
	public void drawSymbol(Integer row, Integer col, NamedSymbol symbol) {
		this.get(row, col).setSymbol(symbol);
	}

	/**
	 *  Draw a symbol at the specified location
	 *  @param row, col - integer indices specifying the position to modify
	 *  @param symbol - Integer symbol argument to set the symbol at the chosen position
	 *  @param color - String color argument to set the background at the chosen position
	 */
	public void drawSymbol(Integer row, Integer col, Integer symbol, String color) {
		this.drawSymbol(row, col, symbol, NamedColor.valueOf(color));
	}

	/**
	 *  Draw a symbol at the specified location
	 *  @param row, col - integer indices specifying the position to modify
	 *  @param symbol - Named Symbol enum argument to set the symbol at the chosen position
	 *  @param color - String color argument to set the background at the chosen position
	 */
	public void drawSymbol(Integer row, Integer col, NamedSymbol symbol, String color) {
		this.drawSymbol(row, col, symbol, NamedColor.valueOf(color));
	}

	/**
	 *  Draw a symbol at the specified location
	 *  @param row, col - integer indices specifying the position to modify
	 *  @param symbol - Integer symbol argument to set the symbol at the chosen position
	 *  @param color - Named Color enum argument to set the foreground at the chosen position
	 */
	public void drawSymbol(Integer row, Integer col, Integer symbol, NamedColor color) {
		this.get(row, col).setSymbol(symbol);
		this.get(row, col).setFGColor(color);
	}

	/**
	 *  Draw a symbol at the specified location
	 *  @param row, col - integer indices specifying the position to modify
	 *  @param symbol - Named Symbol enum argument to set the symbol at the chosen position
	 *  @param color - Named Color enum argument to set the foreground at the chosen position
	 */
	public void drawSymbol(Integer row, Integer col, NamedSymbol symbol, NamedColor color) {
		this.get(row, col).setSymbol(symbol);
		this.get(row, col).setFGColor(color);
	}


	/**
	 * get the JSON representation of the game grid. Contains separate foreground, background, and symbol arrays
	 *
	 * @return the JSON representation of the game grid
	**/
	public String getDataStructureRepresentation() {

		// Maintain a bytebuffer for the byte representations of each game cell attribute
		GameCell gc;
		int totalCells = gridSize[0] * gridSize[1];
		int count = 0;

		String json_str = "";

		// specify the encoding
		json_str = QUOTE + "encoding" + QUOTE + COLON + QUOTE + encoding + QUOTE + COMMA;

		// specify the dimensions of the gamegrid
		json_str += QUOTE + "dimensions" + QUOTE + COLON +
			OPEN_BOX + gridSize[0] + "," + gridSize[1] + CLOSE_BOX + COMMA;

		if (encoding.equals("rle") ) {
			int[] bg = new int[totalCells];
			int[] fg = new int[totalCells];
			int[] symbols = new int[totalCells];

			// populate int arrays
			for (int i = 0; i < gridSize[0]; i++) {
				if (grid.get(i) != null) {
					for (int j = 0; j < gridSize[1]; j++) {
						if (grid.get(i).get(j) != null) {
							gc = grid.get(i).get(j);
							bg[count] = gc.getBGColor().ordinal();
							fg[count] = gc.getFGColor().ordinal();
							symbols[count] = gc.getSymbol().ordinal();
							count++;
						}
					}
				}
			}

			// Add the representation of the gamegrid
			json_str += QUOTE + "bg" + QUOTE + COLON + QUOTE + runlength(bg) + QUOTE + COMMA;
			json_str += QUOTE + "fg" + QUOTE + COLON + QUOTE + runlength(fg) + QUOTE + COMMA;
			json_str += QUOTE + "symbols" + QUOTE + COLON + QUOTE + runlength(symbols) + QUOTE;
		}

		if (encoding.equals("raw") ) {
			bf_bg.clear();
			bf_fg.clear();
			bf_symbols.clear();

			// populate int arrays
			for (int i = 0; i < gridSize[0]; i++) {
				if (grid.get(i) != null) {
					for (int j = 0; j < gridSize[1]; j++) {
						if (grid.get(i).get(j) != null) {
							gc = grid.get(i).get(j);
							bf_bg.put((byte)gc.getBGColor().ordinal());
							bf_fg.put((byte)gc.getFGColor().ordinal());
							bf_symbols.put((byte)gc.getSymbol().ordinal());
						}
					}
				}
			}

			// Add the representation of the gamegrid
			json_str += QUOTE + "bg" + QUOTE + COLON + QUOTE + Base64.encodeBase64String(bf_bg.array()) + QUOTE + COMMA;
			json_str += QUOTE + "fg" + QUOTE + COLON + QUOTE + Base64.encodeBase64String(bf_fg.array()) + QUOTE + COMMA;
			json_str += QUOTE + "symbols" + QUOTE + COLON + QUOTE + Base64.encodeBase64String(bf_symbols.array()) + QUOTE;
		}

		return json_str + CLOSE_CURLY;
	}


	/**
	 *  Perform run length encoding on an array of integers
	 *  @param arr - an array of integers
	 *  @return a string with the run length encoding
	 */
	private String runlength(int[] arr) {
		StringBuilder out = new StringBuilder();
		int count = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] == arr[i]) { // if same as prev, keep counting
				count++;
				if (arr.length - i == 1) { // append if last value
					out.append(arr[i] + "x" + count);
				}
			}
			else {   // otherwise, add to output
				out.append(arr[i - 1] + "x" + count + ",");
				count = 1;
				if (arr.length - i == 1) { // append if last value
					out.append(arr[i] + "x" + count);
				}
			}
		}
		return out.toString();
	}
}
