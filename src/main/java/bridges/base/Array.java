package bridges.base;

import java.util.ArrayList;

import bridges.validation.InvalidValueException;
import bridges.validation.Validation;

/**
 *
 * @brief This class can be used to create arrays of type Element<E>.
 *
 * @author 	Kalpathi Subramanian
 *
 * @date  	10/8/16, 5/17/17, 5/30/18
 *
 *	This class can be used to create arrays of type Element<E>  where E
 *	is a generic object representing application specific data.
 *
 *  This class is not directly called; instead use subclasses of arrays to
 *  construct arrays, depending on their dimension.
 *
 *	Arrays are internally represented as 1D arrays; currently 1D, 2D  and
 *	3D arrays are supported.
 *
 * dims is cols, rows, slices
 *
 * @param <E> The generic parameter object that is part of this element, representing
 *          application specific data.
 *
 *  \sa Example Tutorial at <br>
 *		https://bridgesuncc.github.io/tutorials/Array.html (1D, 2D, and 3D Array)<br>
 *
 */
public class Array<E> extends DataStruct {
	private ArrayList<Element<E >> array_data;
	private int num_dims;					// 1D, 2D and 3D arrays supported
	private int[] dims = {1, 1, 1};			// array dimensions
	private int size;						// array size

	/*
	 * Construct a default array object
	 */
	public Array() {
		array_data = new ArrayList<Element<E >> ();
		num_dims = 1;
		dims[0] = dims[1] = dims[2] = size = 0;
		size = 0;
	}
	/**
	 *  Create an array object with the specified dimensions
	 *
	 *  @param num_dims number of dimensions of the array
	 *  @param dims size of each dimension
	 *
	 */
	public Array(int num_dims, int[] dims) {
		setSize(num_dims, dims);
	}
	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 */
	public String getDataStructType() {
		if ((num_dims >= 1) && (num_dims <= 3))
			return "Array";
		else {
			throw new InvalidValueException("Invalid number of dimensions. Only 1D, 2D and 3D arrays supported at this time");
		}
	}
	/**
	 *
	 *	Set the number of dimensions of the array;
	 *
	 *	@param nd  number of dimensions
	 */

	public void setNumDimensions(int nd) {
		if (nd > 3) {
			throw new InvalidValueException("Invalid number of dimensions. Only 1D, 2D and 3D arrays supported at this time");
		}
		num_dims = nd;
	}
	/**
	 *
	 *	Get the number of dimensions of the array;
	 *
	 *	@return   number of dimensions
	 */

	public int getNumDimensions() {
		if (num_dims > 3) {
			throw new InvalidValueException("Invalid number of dimensions. Only 1D, 2D and 3D  arrays supported at this time");
		}
		return num_dims;
	}

	/**
	 *	@brief Set the size of each dimensions; also allocates  array space
	 *
	 *  @param nd number of dimension
	 *	@param dim  size of each dimension
	 */
	public void setSize(int nd, int[] dim) {
		// check for invalid dimension values
		if (dim[0] <= 0 || dim[1] <= 0 || dim[2] <= 0)
			throw new InvalidValueException("Invalid dimension value, must be  positive");

		// set dimensions, size
		dims[0] = dim[0];
		dims[1] = dim[1];
		dims[2] = dim[2];
		num_dims = nd;
		size = dim[0] * dim[1] * dim[2];

		// allocate space for the array  (is there a better way?)
		for (int k = 0; k < size; k++)
			array_data.add (null);

		// set elements
		array_data.ensureCapacity(size);
		for (int k = 0; k < size; k++)
			array_data.set(k, new Element<E>());
	}
	/**
	 *	@brief Get the size of each dimensions;
	 *
	 *	@param dim a 1D array of size at least 3 where each dimension gets returned
	 */
	public void getDimensions(int[] dim) {
		dim[0] = dims[0];
		dim[1] = dims[1];
		dim[2] = dims[2];
	}

	/**
	 *
	 *	Get the array size
	 *
	 *	@return size of the array
	 */
	public int  getSize() {
		return size;
	}

	/**
	 *
	 *	Get the object at 'indx'
	 *
	 *	@param indx  index into the array
	 *	@return Element<E>  object at 'indx'
	 */
	protected Element<E> getElement (int indx) {
		return array_data.get(indx);
	}

	/**
	 *
	 *	Set the input object at 'indx' - for 1D array
	 *
	 *	@param indx  index into the array
	 *	@param el  element object to be assigned at 'indx'
	 *
	 *
	 **/
	protected void setElement(int indx, Element<E> el) {
		array_data.set(indx, el);
	}

	/**
	 *
	 *	Gets the data structure representation of the array (as JSON)
	 *
	 *	@return  array representation as a JSON
	 *
	 */
	public String getDataStructureRepresentation () {

		StringBuilder nodes_JSON = new StringBuilder();
		StringBuilder links_JSON = new StringBuilder();
		Validation.validate_ADT_size(size);
		for (int i = 0; i < size; i++) {
			if (array_data.get(i) != null) {
				nodes_JSON.append(array_data.get(i).getElementRepresentation() + ",");
			}
		}
		// remove last comma
		nodes_JSON.setLength(nodes_JSON.length() - 1);

		// add dimension  and the element information

		String json_str =
			QUOTE + "dims" + QUOTE + COLON +
			OPEN_BOX +
			dims[0] + COMMA + dims[1] + COMMA + dims[2] +
			CLOSE_BOX + COMMA +
			QUOTE + "nodes"  + QUOTE + COLON +
			OPEN_BOX  + nodes_JSON + CLOSE_BOX + CLOSE_CURLY;

		return json_str;
	}
}
