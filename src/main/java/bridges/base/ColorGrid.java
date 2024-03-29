package bridges.base;
import java.util.*;
import bridges.base.Color;
import java.nio.ByteBuffer;
import java.nio.Buffer;
import org.apache.commons.codec.binary.Base64;

/**
 * @brief This is a class in BRIDGES for representing an image.
 *
 * A ColorGrid is essentially an image. One can construct an image of
 * a particular size using the ColorGrid() constructor to be either
 * blank or filled with a particular Color depending on which
 * constructor is called.
 *
 * One can change the color of a pixel with set(). For instance, like that:
 * \code{java}
 * ColorGrid cg = new ColorGrid(rows, columns);
 * cg.set (2, 3, new Color("lightsalmon");
 * \endcode
 *
 * You can get a ColorGrid from an existing Bridges ColorGrid assignment using
 * bridges.connect.DataSource.getColorGridFromAssignment()
 *
 * @sa See the tutorial about ColorGrid : https://bridgesuncc.github.io/tutorials/Grid.html
 *
 * @author David Burlinson, Erik Saule
**/
public class ColorGrid extends Grid<Color> {
	private static Color baseColor = new Color(0, 0, 0, 1.0f);

	/**
	 *	Get the data type name
	 *  @return the data type
	 */
	public String getDataStructType() {
		return "ColorGrid";
	}

	/**
	 * Construct a color grid with defaulty sizes:
	 *
	 */
	public ColorGrid() {
		this(defaultGridSize[0], defaultGridSize[1], baseColor);
	}

	/**
	 * Grid constructor with size arguments
	 *
	 * @param rows - int representing the number of rows of the grid
	 * @param cols - int representing the number of columns of the grid
	 *
	 */
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
	@Override
	public void resize(int rows, int cols) {
		super.resize(rows, cols);
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				if (this.get(i, j) == null)
					this.set(i, j, baseColor);
			}
		}
	}

	/**
	 *	Get the height of the color grid
	 *
	 *	@return the height (number of rows) of the grid
	 */
	public int getHeight() {
		return gridSize[0];
	}

	/**
	 *	Get the width of the color grid
	 *
	 *	@return the width (number of columns) of the grid
	 */
	public int getWidth() {
		return gridSize[1];
	}

	/**
	 *   Run Length Encoding of the ColorGrid
	 *	 @return byte buffer representation of the color grid
	 */
	private ByteBuffer getRLE() {
		ByteBuffer imageBytes = ByteBuffer.allocate(5 * gridSize[0] * gridSize[1]);
		int count = 0;
		int totalCount = 0;
		int pos = 0;
		Color last = grid.get(0).get(0);

		while (pos < gridSize[0] * gridSize[1]) {
			int posY = pos / gridSize[1];
			int posX = pos % gridSize[1];
			Color current = grid.get(posY).get(posX);

			if (count == 0) {
				count = 1;
				last = current;
			}
			else {
				if (last.equals(current)) {
					count++;
				}
				else {
					totalCount += count;
					imageBytes.put((byte)(count - 1));
					imageBytes.put(last.getByteRepresentation());
					count = 1;
					last = current;
				}
			}

			if (count == 256) {
				totalCount += count;
				imageBytes.put((byte)(count - 1));
				imageBytes.put(last.getByteRepresentation());
				count = 0;
			}
			pos++;
		}
		totalCount += count;
		imageBytes.put((byte) (count - 1));
		imageBytes.put(last.getByteRepresentation());

		if (totalCount != gridSize[0] * gridSize[1]) {
			System.err.println("Something broke in getRLE construction");
		}

		// trim the buffer down and reset position to 0
		((Buffer)imageBytes).flip();

		return imageBytes;
	}

	/**
	 *   Raw Encoding of the ColorGrid
	 *	 @return byte buffer representation of the color grid
	 */
	// get raw encoding of ColorGrid
	private ByteBuffer getRAW() {
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

		return imageBytes;
	}

	/**
	 * Get the JSON representation of the color grid
	 *
	 * @return the JSON representation (string) of the color grid
	**/
	public String getDataStructureRepresentation () {
		ByteBuffer byte_buff = getRLE();

		// store length of bytes in buffer and allocate new byte array of same size
		int len = byte_buff.remaining();
		byte[] byte_arr = new byte[len];

		// transfer bytes from buffer to array
		byte_buff.get(byte_arr);
		String encoding = "RLE";

		// if RLE encoding is larger than RAW, use RAW
		if (len > gridSize[0] * gridSize[1] * 4) {
			encoding = "RAW";
			byte_buff = getRAW();
			byte_arr = byte_buff.array();
		}

		// Add the byte representation of the grid
		String json_str =  QUOTE + "encoding" + QUOTE + COLON + QUOTE + encoding +
			QUOTE + COMMA + QUOTE + "nodes" + QUOTE + COLON + OPEN_BOX  + QUOTE +
			Base64.encodeBase64String(byte_arr) + QUOTE + CLOSE_BOX + COMMA;

		// Specify the dimensions of the grid
		json_str += QUOTE + "dimensions" + QUOTE + COLON +
			OPEN_BOX + gridSize[0] + "," + gridSize[1] + CLOSE_BOX + CLOSE_CURLY;

		return json_str;
	}
}
