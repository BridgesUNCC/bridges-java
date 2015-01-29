/**
 * This class is created to solve the problem with 
 * generic arrays and type erasure
 */
package edu.uncc.cs.bridgesV2.base;

/**
 * @author mihai
 *
 */
public class ArrayOfElement<E>{

	  // declare the class instance
	  private Class<?> e;

	  // code to initialize tClass
	  /**
		 * @param tClass
		 */
		public ArrayOfElement(Class<?> e) {
			super();
			this.e = e;
		}

	  // returns an array of the parameterized type
	  public <E> Element<E>[] returnArray(){
	    return (Element<E>[])java.lang.reflect.Array.newInstance(e, 10);
	  }
	}
