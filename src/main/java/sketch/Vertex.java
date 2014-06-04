package sketch;

import java.util.HashMap;

/**
 * An implementation of AbstractVertex with HashMap for adjacency.
 *  
 * @author Sean Gallagher
 */
public class Vertex extends AbstractVertex {
	public Vertex(String identifier) {
		super(identifier);
		links = new HashMap<>();
	}
}
