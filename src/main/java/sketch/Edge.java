package sketch;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Generic graph link, with visual components. 
 */
public class Edge extends AbstractEdge{
	/**
	 * Visualization properties for this Link
	 * This could be made private.
	 */
	Map<String, String> properties = new HashMap<>();
	
	public Edge(AbstractVertex source, AbstractVertex destination) {		
		super(source, destination);
		
		//automates the connection process
		//creates both connections   O  ->  O
		//							    <-
		source.outgoing.put(destination.getIdentifier(), this);
		destination.incoming.put(source.getIdentifier(), this);
	}
	
	public void remove(){
		source.outgoing.remove(destination.getIdentifier());	
		destination.incoming.remove(source.getIdentifier());
	}	
}
