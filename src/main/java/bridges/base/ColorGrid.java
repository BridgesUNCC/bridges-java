package bridges.base;

import bridges.base.Color;
import java.util.*;
import java.nio.ByteBuffer;
import org.apache.commons.codec.binary.Base64;

/**
 * @brief This is a class in BRIDGES for representing an (n x n) grid.
 * @author David Burlinson
 * @param
**/
public class ColorGrid extends Grid<Color> {
	private static Color baseColor = new Color(0, 0, 0, 1.0f);

	public String getDataStructType() {
		return "ColorGrid";
	}

	/**
	 * Grid constructors
	 *
	**/
	public ColorGrid() {
		this(gridSize[0], gridSize[1], baseColor);
	}

	/**
	 * Grid constructor with size arguments
	 *
	 * @param rows - int representing the number of rows of the grid
	 * @param cols - int representing the number of columns of the grid
	 *
	**/
	public ColorGrid (int rows, int cols) {
		this(rows, cols, baseColor);
	}

	/**
	 * Grid constructor with size and color string argument
	 *
	 * @param rows - int representing the number of rows of the grid
	 * @param cols - int representing the number of columns of the grid
	 * @param color - Color object
	 *
	**/
	public ColorGrid (int rows, int cols, Color color)  {
		super(new int[] {rows, cols});

		baseColor = color;
		gridSize = new int[] {rows, cols};

		initializeGrid();
	}

	/**
	 * Populate the grid with the base color
	 *
	**/
	private void initializeGrid() {
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				this.set(i, j, baseColor);
			}
		}
	}

	// set the (row, col) element in the ColorGrid
	public void set(Integer row, Integer col, Color color) {
		super.set(row, col, color);
	}

	/**
	 * get the JSON representation of the color grid
	 *
	 * @return the JSON representation of the color grid
	**/
	public String getDataStructureRepresentation () {

		// Maintain a bytebuffer for the byte representations of each grid color
		ByteBuffer imageBytes = ByteBuffer.allocate(4 * gridSize[0] * gridSize[1]);
		Color color;

		for (int i = 0; i < gridSize[0]; i++) {
			if (grid.get(i) != null) {
				for (int j = 0; j < gridSize[1]; j++) {
					if (grid.get(i).get(j) != null) {
						color = grid.get(i).get(j);
						imageBytes.put(color.getByteRepresentation());
					}
				}
			}
		}

		// Add the byte representation of the grid
		String json_str = QUOTE + "nodes" + QUOTE + COLON +
			OPEN_BOX  + QUOTE + Base64.encodeBase64String(imageBytes.array()) + QUOTE + CLOSE_BOX + COMMA;

		// Specify the dimensions of the grid
		json_str += QUOTE + "dimensions" + QUOTE + COLON +
			OPEN_BOX + gridSize[0] + "," + gridSize[1] + CLOSE_BOX + CLOSE_CURLY;

		return json_str;
	}
}
