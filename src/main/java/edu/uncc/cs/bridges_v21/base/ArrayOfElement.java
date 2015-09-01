/**
 * This class is created to solve the problem with 
 * generic arrays and type erasure
 */
package bridges.base;

/**
 * @author mihai
 * This class is used to allow the Bridges Visualization to represent an array.	
 */
public class ArrayOfElement<E extends Comparable <? super E>>{

	  // declare the class instance
	  private Class<?> e;

	  // code to initialize tClass
	  /**Construct an ArrayOfElement for a particular class.
		 * @param e the class to make an array of (e.g. SLelement.class). 
		 */
		public ArrayOfElement(Class<?> e) {
			super();
			this.e = e;
		}

		// returns an array of the parameterized type
		/**Retruns the array of elements. 
		 * @return the array of elements. 
*/
	  public <E extends Comparable <? super E>> Element<E>[] returnArray(){
	    return (Element<E>[])java.lang.reflect.Array.newInstance(e, 10);
	  }
	}
