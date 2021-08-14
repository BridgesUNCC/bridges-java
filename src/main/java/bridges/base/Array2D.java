package bridges.base;

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
 *	This class can be used to create 2D arrays of type Element<E>  where E
 *	is a generic object representing application specific data.
 *
 *	Arrays are internally represented as 1D arrays;
 *
 * @param <E> The generic parameter object that is part of this element, representing
 *          application specific data.
 *
 *  \sa Example Tutorial at <br>
 *		https://bridgesuncc.github.io/tutorials/Array.html (1D, 2D, and 3D Array)<br>
 *
 */
public class Array2D<E> extends Array<E> {
	private int size;						// array size
	private int num_rows, num_cols;			// dimensions of the array

	/*
	 * Construct a default array object
	 */
	public Array2D() {
		super();
		size = 0;
		num_rows = num_cols = 0;
	}
	/**
	 *  Create an array object with the specified dimensions
	 *
	 * @param rows,cols size of the array
	 *
	 */
	public Array2D(int rows, int cols) {
		super();
		int[] dim = {cols, rows, 1};
		num_rows = rows;
		num_cols = cols;
		this.setSize (2, dim);
	}

	/**
	 *
	 *	Get the object at index x, y -- for 2D arrays
	 *
	 *	@param row  - row index
	 *	@param col  - column index
	 *	@return Element<E>  object at 2D index:  row, col
	 */
	public Element<E> getElement(int row, int col) {
		return super.getElement(row * num_cols + col);
	}

	/**
	 *
	 *	Set the input object at row, col - 2D arrays
	 *
	 *	@param row  row index into the array
	 *	@param col  index index into the array
	 *	@param el  element object to be assigned at 'indx'
	 *
	 */
	public void setElement(int row, int col, Element<E> el) {
		this.setElement(row * num_cols + col, el);
	}
}
