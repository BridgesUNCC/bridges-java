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
 *	This class can be used to create 3-dimensional arrays of type Element<E>  where E
 *	is a generic object representing application specific data.
 *
 *	Arrays are internally represented as 1D arrays
 *
 * @param <E> The generic parameter object that is part of this element, representing
 *          application specific data.
 *
 *  \sa Example Tutorial at <br>
 *		https://bridgesuncc.github.io/tutorials/Array.html (1D, 2D, and 3D Array)<br>
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
	 *  Create an array object with the specified dimensions, order is
	 *  slices, rows, columns, with slices varying the slowest, and columns
	 *	 verying the fastest
	 *
	 *  @param dims size of each dimension
	 *
	 */
	public Array3D(int[] dims) {
		super();
		this.setSize (3, dims);
		num_cols = dims[0];
		num_rows = dims[1];
		num_slices = dims[2];
	}
	/**
	 *  Create an array object with the specified dimensions
	 *
	 *  @param cols number of columns
	 *  @param rows number of rows
	 *  @param slices number of slices
	 *
	 */
	public Array3D(int slices, int rows, int cols) {
		super();
		int[] dims = {slices, rows, cols};
		this.setSize (3, dims);
		num_cols = cols;
		num_rows = rows;
		num_slices = slices;
	}

	/**
	 *
	 *	Get the object at slice, row, column -- for 3D arrays
	 *
	 *	@param slice  - slice index
	 *	@param row  - row index
	 *	@param col  - column index
	 *
	 *	@return Element<E>  object at slice, row, column
	 */
	public Element<E> getElement(int slice, int row, int col) {
		return super.getElement(slice * num_cols * num_rows + row * num_cols + col);
	}

	/**
	 *
	 *	Set the input object at 'col, row, slice'
	 *
	 *	@param slice  slice index into the array
	 *	@param row  row index into the array
	 *	@param col  column index into the array
	 *
	 *	@param el  element object to be assigned at 'indx'
	 *
	 */
	public void setElement(int slice, int row, int col, Element<E> el) {
		super.setElement(slice * num_cols * num_rows + row * num_cols + col, el);
	}
}
