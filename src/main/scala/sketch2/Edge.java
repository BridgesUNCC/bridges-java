package sketch;
import java.util.Map;

/**
 * Generic graph link, with visual components. 
 */
interface Edge {
	public String color;
	public double[] dash;
	public double width;
	public double opacity;
	public AbstractVertex source;
	public AbstractVertex destination;
}