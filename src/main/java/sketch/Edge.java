package sketch;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Generic graph link, with visual components. 
 */
public class Edge extends AbstractEdge{

	/**
	 * Takes two vertices and creates and edge between them.
	 * 
	 * @param source The source vertex.
	 * @param destination The destination vertex.
	 * @param identifier The name of the edge between the vertices.
	 */
	public Edge(AbstractVertex source, AbstractVertex destination, String identifier) {		
		super(source, destination, identifier);		
		//automates the connection process
		//creates both connections   O  ->  O
		//							    <-
		
		//Maybe put a check making sure the source and destination exist?		
		eOutgoing = new HashMap<>();
		//creates outgoing links from the associated vertices to the 'Edge'
		source.outgoing.put(identifier, this);
		destination.outgoing.put(identifier, this);
		//creates outgoing links from the 'Edge' to the associated Vertices
		this.eOutgoing.put(source.getIdentifier(), source);
		this.eOutgoing.put(destination.getIdentifier(), destination);
		
	}
}
