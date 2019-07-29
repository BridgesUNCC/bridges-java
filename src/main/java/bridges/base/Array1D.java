package bridges.base;

import bridges.base.Array;
import bridges.validation.InvalidValueException;
import bridges.validation.Validation;
import java.util.Iterator;

/**
 *
 * @brief This is a class can be used to create 1 dimensional arrays of type Element<E>.
 *
 * @author 	Kalpathi Subramanian
 *
 * @date  	7/18/19
 *
 *	This class can be used to create 1D arrays of type Element<E>  where E
 *	is a generic object representing application specific data.
 *
 * Array1D has iterator semantic to enable range for loops. For instance,
 *
 * \code{java}
 * Array1D<Integer> arr = something();
 * for (Integer i : arr)
 *   System.out.println(i);
 * \endcode
 *
 *
 * @param <E> The generic parameter object that is part of this element, representing
 *          application specific data.
 *
 *  \sa Example Tutorial at <br>
 *		http://bridgesuncc.github.io/tutorials/Array.html (1D, 2D, and 3D Array)<br>
 *
 */
public class Array1D<E> extends Array<E> implements Iterable<E> {
	private int size;						// array size

	/*
	 * Construct a default array object
	 */
	public Array1D() {
		super();
		size = 0;
	}

	/**
	 *  Create a 1D array object
	 *
	 *  @param sz number of elements in the array
	 *
	 */
	public Array1D(int sz) {
		super();
		size = sz;
		int[] dim = {sz, 1, 1};
		this.setSize(1, dim);
	}

	/**
	 *
	 *	Get the object at 'indx'
	 *
	 *	@param indx  index into the array
	 *	@return Element<E>  object at 'indx'
	 */
	public Element<E> getElement (int indx) {
		return super.getElement(indx);
	}

	/**
	 *	Set the input object at 'indx' - for 1D array
	 *
	 *	@param indx  index into the array
	 *	@param el  element object to be assigned at 'indx'
	 **/
	public void setElement(int indx, Element<E> el) {
		super.setElement(indx, el);
	}


	public Iterator<E> iterator() {
		class Array1DIterator<E> implements Iterator<E> {
			public E next() {
				E ret = arr.getElement(index).getValue();
				index++;
				return ret;
			}
			public boolean hasNext() {
				return index < arr.size;
			}
			public Array1D<E> arr;
			public int index;
			Array1DIterator (Array1D<E> a) {
				arr = a;
				index = 0;
			}
		}
		return new Array1DIterator (this);
	}
}
