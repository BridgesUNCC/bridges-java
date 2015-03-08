

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.TreeElement;

public class TreeElementTest {
	static TreeElement<String> te0;
	static TreeElement<String> te1;
	static TreeElement<String> te2;
	static TreeElement<String> te3;
	static TreeElement<String> te4;





	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		te0 = new TreeElement<String>();
		te1 = new TreeElement<String>("A");
		te2 = new TreeElement<String>(te0, te1);
		te3 = new TreeElement<String>("B", te0, te1);
		
		te4 = new TreeElement<String>();
	}


	@Test
	/** test getLeft() */
	public void testGetLeft() {
		assertNull("getLeft() should return null", te0.getLeft());
		assertNull("getLeft() should return null", te1.getLeft());
		assertEquals("getLeft() did not return correct object", te0, te2.getLeft());
		assertEquals("getLeft() did not return correct object", te0, te3.getLeft());
	}

	@Test
	/** test setLeft() on new TreeElement */
	public void testSetLeftToNewTreeElement() {
		te4.setLeft(te0);
		assertEquals("setLeft() set left to wrong object", te0, te4.getLeft());
	}
	
	@Test
	/** test setLeft() to null */
	public void testSetLeftToNull() {
		te4.setLeft(null);
		assertNull("setLeft() did not set left to null", te4.getLeft());
	}


	@Test
	/** test getRight() */
	public void testGetRight() {
		assertNull("getRight() should return null", te0.getRight());
		assertNull("getRight() should return null", te1.getRight());
		assertEquals("getRight() did not return correct object", te1, te2.getRight());
		assertEquals("getRight() did not return correct object", te1, te3.getRight());
	}

	@Test
	/** test setRight() on new TreeElement */
	public void testSetRightToNewTreeElement() {
		te4.setRight(te0);
		assertEquals("setRight() set Right to wrong object", te0, te4.getRight());
	}
	
	@Test
	/** test setRight() to null */
	public void testSetRightToNull() {
		te4.setRight(null);
		assertNull("setRight() did not set Right to null", te4.getRight());
	}

}
