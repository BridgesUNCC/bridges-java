/**
 * This class is created to solve the problem with 
 * generic arrays and type erasure
 */
package bridges_vs2.structure;

/**
 * @author mihai
 *
 */
public class ArrayOfElement<E>{

	  // declare the class instance
	  private Class<E> tClass;

	  // code to initialize tClass
	  /**
		 * @param tClass
		 */
		public ArrayOfElement(Class<E> tClass) {
			super();
			this.tClass = tClass;
		}

	  // returns an array of the parameterized type
	  public <E> E[] returnArray(){
	    return (E[])java.lang.reflect.Array.newInstance(tClass, 10);
	  }
	}