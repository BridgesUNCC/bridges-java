import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uncc.cs.bridgesV2.base.Edge;
import edu.uncc.cs.bridgesV2.base.LinkVisualizer;


public class LinkVisualizerTest {
	static String DEFAULT_COLOR = "black";
	static double DEFAULT_OPACITY = 1.0;
	static double DEFAULT_THICKNESS = 1.0;
	
	static LinkVisualizer lv;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		lv = new LinkVisualizer();
	}

	
	
	@Test
	public void testConstructorHasDefaultValues() {
		assertTrue("constructor did not assign default color", lv.getColor().equals(DEFAULT_COLOR));
		assertTrue("constructor did not assign default opacity", lv.getOpacity() == DEFAULT_OPACITY);
		assertTrue("constructor did not assign default thickness", lv.getThickness() == DEFAULT_THICKNESS);
	}

	
	@Test
	public void testSetColor()
	{
		LinkVisualizer lv = new LinkVisualizer();
		String color = "blue";
		lv.setColor(color);
		assertTrue("setColor() did not assign correct value", lv.getColor().equals(color));
	}
	
	@Test
	public void testSetOpacity()
	{
		LinkVisualizer lv = new LinkVisualizer();
		double opacity = 0.25;
		lv.setOpacity(opacity);
		assertTrue("setOpacity() did not assign correct value", lv.getOpacity() == opacity);
	}
	
	@Test
	public void testSetThickness()
	{
		LinkVisualizer lv = new LinkVisualizer();
		double thickness = 0.25;
		lv.setThickness(thickness);
		assertTrue("setThickness() did not assign correct value", lv.getThickness() == thickness);
	}
	
	
}
