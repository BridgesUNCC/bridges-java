import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


import bridges_v21.base.*;
import bridges_v21.validation.*;

public class ElementVisualizerTest {

	static String DEFAULT_SHAPE = "circle";
	static String DEFAULT_COLOR = "green";
	static double DEFAULT_OPACITY = 1.0;
	static double DEFAULT_SIZE = 10.0;

	
	static ElementVisualizer ev0;
	static ElementVisualizer ev1;
	static ElementVisualizer ev2;
	static ElementVisualizer ev3;
	static ElementVisualizer ev4;
	static ElementVisualizer ev5;
	static ElementVisualizer ev6;

	
	
	/** Setup static variables for use in later tests. */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ev0 = new ElementVisualizer();
		ev1 = new ElementVisualizer(2.0);
		ev2 = new ElementVisualizer("blue");
		ev3 = new ElementVisualizer("yellow", "square");
		ev4 = new ElementVisualizer("red", "triangle-up", .5, 5.0);
		ev5 = new ElementVisualizer(ev4);
	}
	
	@Test
	/** test size assigned at construction */
	public void testGetSize() {
		assertTrue("getSize() was not assigned default value at construction", ev0.getSize() == DEFAULT_SIZE);
		assertTrue("getSize() was not assigned default value at construction", ev2.getSize() == DEFAULT_SIZE);
		assertTrue("getSize() was not assigned default value at construction", ev3.getSize() == DEFAULT_SIZE);


		assertTrue("getSize() did not return value assigned at construction", ev1.getSize() == 2.0);
		assertTrue("getSize() did not return value assigned at construction", ev4.getSize() == 5.0);
		assertTrue("getSize() did not return value assigned at construction", ev5.getSize() == 5.0);
	}
	
	@Test
	/** test setSize assigns correct value */
	public void testSetSize() {
		double val = 25.0;
		ElementVisualizer ev = new ElementVisualizer();
		ev.setSize(val);
		assertTrue("setSize() did not assign correct value", ev.getSize() == val);
	}
	
	@Test
	/** test getColor returns correct value */
	public void testGetColor(){
		assertTrue("getColor() was not assigned default value at construction", ev0.getColor().equals(DEFAULT_COLOR));
		assertTrue("getColor() was not assigned default value at construction", ev1.getColor().equals(DEFAULT_COLOR));


		assertTrue("getColor() did not return value assigned at construction", ev2.getColor().equals("blue"));
		assertTrue("getColor() did not return value assigned at construction", ev3.getColor().equals("yellow"));
		assertTrue("getColor() did not return value assigned at construction", ev4.getColor().equals("red"));
		assertTrue("getColor() did not return value assigned at construction", ev5.getColor().equals("red"));

	}

	@Test
	/** test setColor assigns correct value */
	public void testSetColor() {
		String color = "orange";
		ElementVisualizer ev = new ElementVisualizer();
		ev.setColor(color);
		assertTrue("setColor() did not assign correct value", ev.getColor().equals((color)));
	}

	
	@Test
	/** test setColor with null string throws NullPointerException */
	public void testSetColorWithNullString() {
		boolean thrown = false;
		ElementVisualizer ev = new ElementVisualizer();
		
		try{
			ev.setColor(null);
		} catch(NullPointerException e) {
			thrown = true;
		}
		
		if(!thrown) {
			fail("setColor(null) did not throw NullPointerException");
		}
		
	}
	
	@Test
	/** test setOpacity with null string throws NullPointerException */
	public void testSetOpacityWithNullString() {
		boolean thrown = false;
		ElementVisualizer ev = new ElementVisualizer();
		
		Double d = 2.0;
		d = null;
		
		try{
			ev.setOpacity(d);
			ev.getOpacity();
			
		} catch(NullPointerException e) {
			thrown = true;
		}
		
		if(!thrown) {
			fail("setColor(null) did not throw NullPointerException");
		}
		
	}

	
	
	@Test
	/** test shape assigned at construction */
	public void testGetShape() {
		assertTrue("getShape() was not assigned default value at construction", ev0.getShape().equals(DEFAULT_SHAPE));
		assertTrue("getShape() was not assigned default value at construction", ev1.getShape().equals(DEFAULT_SHAPE));
		assertTrue("getShape() was not assigned default value at construction", ev2.getShape().equals(DEFAULT_SHAPE));


		assertTrue("getShape() did not return value assigned at construction", ev3.getShape().equals("square"));
		assertTrue("getShape() did not return value assigned at construction", ev4.getShape().equals("triangle-up"));
		assertTrue("getShape() did not return value assigned at construction", ev5.getShape().equals("triangle-up"));
	}
	
	@Test
	/** test setShape assigns correct value */
	public void testSetShape() {
		String shape = "diamond";
		ElementVisualizer ev = new ElementVisualizer();
		ev.setShape(shape);
		assertTrue("setShape() did not assign correct value", ev.getShape().equals((shape)));
	}
	
	@Test
	/** test opacity assigned at construction */
	public void testGetOpacity() {
		assertTrue("getOpacity() was not assigned default value at construction", ev0.getOpacity() == DEFAULT_OPACITY);
		assertTrue("getOpacity() was not assigned default value at construction", ev1.getOpacity() == DEFAULT_OPACITY);
		assertTrue("getOpacity() was not assigned default value at construction", ev2.getOpacity() == DEFAULT_OPACITY);
		assertTrue("getOpacity() was not assigned default value at construction", ev3.getOpacity() == DEFAULT_OPACITY);

		
		assertTrue("getOpacity() did not return value assigned at construction", ev4.getOpacity() == 0.5);
		assertTrue("getOpacity() did not return value assigned at construction", ev5.getOpacity() == 0.5);
	}
	
	@Test
	/** test setOpacity assigns correct value */
	public void testSetOpacity() {
		double val = 0.75;
		ElementVisualizer ev = new ElementVisualizer();
		ev.setOpacity(val);
		assertTrue("setOpacity() did not assign correct value", ev.getOpacity() == val);
	}
	
	@Test
	/** test randomColor returns valid color */
	public void testRandomColor100times() {
		for(int i = 0; i < 100; i++){
			String color = ev0.randomColor();
			try{
				Validation.validateColor(color);
			} catch (Exception e) {
				fail("randomColor() did not return valid color");
			}
		}
	}


}
