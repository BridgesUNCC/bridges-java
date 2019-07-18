package bridges.base;

import bridges.validation.InvalidValueException;
import bridges.validation.Validation;

/**
 *
 * @brief This class can be used to create two dimensional arrays of type Element<E>.
 *
 * @author 	Kalpathi Subramanian
 *
 * @date  	7/18/19
 *
 *	This class can be used to create three dimensional arrays of type Element<E>  where E
 *	is a generic object representing application specific data.
 *
 *	Arrays are internally represented as 1D arrays; currently 1D, 2D  and
 *	3D arrays are supported.
 *
 * @param <E> The generic parameter object that is part of this element, representing
 *          application specific data.
 *
 *  \sa Example Tutorial at <br>
 *		http://bridgesuncc.github.io/tutorials/Array.html (1D, 2D, and 3D Array)<br>
 *
 */
public class Array3D<E> extends Array<E> {
	private int size;							// array size
	private int num_cols, num_rows, num_slices; // dimensions of the array

	/*
	 * Construct a default array object
	 */
	public Array3D() {
		super();
		num_cols = num_rows = num_slices = size = 0;
	}
	/**
	 *  Create an array object with the specified dimensions
	 *
	 *  @param dims size of each dimension
	 *
	 */
	public Array3D(int[] dims) {
		super();
		this.setSize (3, dims);
		num_cols = dims[0]; num_rows = dims[1]; num_slices = dims[2]; 
	}
	/**
	 *  Create an array object with the specified dimensions
	 *
	 *  @param cols number of columns
	 *  @param rows number of rows
	 *  @param slices number of slices
	 *
	 */
	public Array3D(int cols, int rows, int slices) {
		super();
		int[] dims = {cols, rows, slices};
		this.setSize (3, dims);
		num_cols = cols; num_rows = rows; num_slices = slices;
	}

	/**
	 *
	 *	Get the object at x, y, z -- for 3D arrays
	 *
	 *	@param x  - column index
	 *	@param y  - row index
	 *	@param z  - slice index
	 *
	 *	@return Element<E>  object at x, y, z
	 */
	public Element<E> getElement(int x, int y, int z) {
		return super.getElement(z * num_cols*num_rows + y * num_cols + x);
	}

	/**
	 *
	 *	Set the input object at 'col, row, slice'
	 *
	 *	@param col  column index into the array
	 *	@param row  row index into the array
	 *	@param slice  slice index into the array
	 *
	 *	@param el  element object to be assigned at 'indx'
	 *
	 */
	public void setElement(int col, int row, int slice, Element<E> el) {
		super.setElement(slice * num_cols*num_rows + row * num_cols + col, el);
	}
}
