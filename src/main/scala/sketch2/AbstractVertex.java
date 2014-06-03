package sketch2;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import sketch.AbstractVertex;
import sketch.Object;
import sketch.Override;

/**
 * Abstract graph vertex, with an unspecified adjacency construct.
 * Whatever is used must be compatible with Map. The default implementation
 * Vertex uses a HashMap. You are free to use any you prefer.
 * 
 * This class provides delegates for its {@link links} member for convenience.
 */
public class AbstractVertex {
	/**
	 * This is the string by which this AbstractVertex should be found.
	 * This is not a label; it includes provider information as well.
	 */
	public String identifier;
	
	/**
	 * Links, with properties other than just target Node.
	 */
	public Map<String, Edge> links;
	
	public String color;
	public String shape;
	public double size;
	public double opacity;
	
	/// Hash code and equals: implements map, but it only uses this.identifier

	/**
	 * Get the hash code for this AbstractVertex.
	 * The code is only based on the identifier.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	/**
	 * Find whether two AbstractVertex's are the same.
	 * Based only on the identifier.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractVertex other = (AbstractVertex) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}
}
