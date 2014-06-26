package bridges;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	 */
	public Edge(AbstractVertex source, AbstractVertex destination, String identifier) {		
		super(source, destination, identifier);		
		//automates the connection process
		//creates both connections   O  ->  O
		//							    <-
		
		//Maybe put a check making sure the source and destination exist?		
		eOutgoing = new ArrayList<AbstractVertex>();//each edge gets a list (of 2) connected vertices
		//creates outgoing links from the associated vertices to the 'Edge'
		//Adds the this edge to the lists associated with the two connecting vertices
		source.outgoing.add(this);
		destination.outgoing.add(this);
		identifier = source.getIdentifier() + destination.getIdentifier();
		//creates outgoing links from the 'Edge' to the associated Vertices
		//Adds the destination and the source to the list of vertices connected to the edge
		this.eOutgoing.add(source);
		this.eOutgoing.add(destination);		
	}
}
