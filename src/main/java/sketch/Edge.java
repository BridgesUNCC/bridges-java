package sketch;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Generic graph link, with visual components. 
 */
public class Edge extends AbstractEdge{

	/**
	 * Creates an edge connection between two vertices.
	 * @param source
	 * @param destination
	 */
	public Edge(AbstractVertex source, AbstractVertex destination, String identifier) {		
		super(source, destination, identifier);		
		//automates the connection process
		//creates both connections   O  ->  O
		//							    <-
		//Maybe put a check making sure the source and destination exist?
		/*
		source.outgoing.put(destination.getIdentifier(), this);
		destination.incoming.put(source.getIdentifier(), this);
		*/
		
		eOutgoing = new HashMap<>();
		//creates outgoing links from the associated vertices to the 'Edge'
		source.outgoing.put(identifier, this);
		destination.outgoing.put(identifier, this);
		//creates outgoing links from the 'Edge' to the associated Vertices
		this.eOutgoing.put(source.getIdentifier(), source);
		this.eOutgoing.put(destination.getIdentifier(), destination);
		source.edgeMap.put(identifier, this);
		destination.edgeMap.put(identifier, this);
		
	}
	/**
	 * Removes the edge that is used to call this method.
	 */
	//Being phased out
	public void remove(){		
		
		//source.outgoing.remove(destination.getIdentifier(), this);	
		//destination.incoming.remove(source.getIdentifier(), this);	
		//setting the nodes pointing to the 'Edge' to null so GC removes the 'Edge'
		for(AbstractVertex value : eOutgoing.values()){
			this.eOutgoing.get(value).outgoing = null;
		}		
		
		//TODO: message saying there was no edge to delete		
	}	
}
