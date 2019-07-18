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
 *	This class can be used to create arrays of type Element<E>  where E
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
public class Array2D<E> extends Array<E>{
	private int size;						// array size
	private int num_rows, num_cols;			// dimensions of the array

	/*
	 * Construct a default array object
	 */
	public Array2D() {
		super();
		size = 0;
	}
	/**
	 *  Create an array object with the specified dimensions
	 *
	 *  @param num_dims number of dimensions of the array
	 *  @param dims size of each dimension
	 *
	 */
	public Array2D(int rows, int cols) {
		super();
		int[] dim = {cols, rows, 1};
		this.setSize (2, dim);
	}

	/**
	 *
	 *	Get the object at index x, y -- for 2D arrays
	 *
	 *	@param x  - column index
	 *	@param y  - row index
	 *	@return Element<E>  object at x, y
	 */
	public Element<E> getElement(int x, int y) {
		return super.getElement(y * num_cols + x);
	}

	/**
	 *
	 *	Set the input object at x, y - 2D arrays
	 *
	 *	@param x  column index into the array
	 *	@param y  row index into the array
	 *	@param el  element object to be assigned at 'indx'
	 *
	 */
	public void setElement(int x, int y, Element<E> el) {
		this.setElement(y * num_cols + x, el);
	}

}
