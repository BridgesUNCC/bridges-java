package testing;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.ArrayOfElement;

public class ArrayOfElementTest {
	static ArrayOfElement<String> a0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		a0 = new ArrayOfElement<String>(String.class);
	}

	@Test
	public void test() {
		assertNotNull(a0);
	}

}
