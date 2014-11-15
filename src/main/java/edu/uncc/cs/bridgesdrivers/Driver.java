package edu.uncc.cs.bridgesdrivers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import edu.uncc.cs.bridges.*;

public class Driver {
		
	/**
	 * This method populates the graph with the Actor and some manually-added movies 
	 * @param graph 
	 * @param Actor 
	 * @return the IMDB graph
	 */
	public static GraphMovieActor<?> populate(GraphMovieActor<?> graph , Actor anActor){	
		
		String[] array = {"Air_Up_There,_The_(1994)","Animal_House_(1978)","Apollo_13_(1995)","Balto_(1995)","Few_Good_Men,_A_(1992)","Forty_Deuce_(1982)"};
		int i = 0;
		while(i<array.length){
			
			//create the actor vertex
			
			Vertex<Actor> aVertexActor;
			
			//check if the actor already exists
			if (graph.vertices.containsKey(anActor)){
				aVertexActor = (Vertex<Actor>) graph.vertices.get(anActor);
			}
			else{
				aVertexActor = new Vertex<>(anActor, graph).setColor("red");
			}
			//create the movie vertex
			Movie aMovie = new Movie(array[i]);
			Vertex<Movie> aVertexMovie;
			
			//check if the movie already exists
			if (graph.vertices.containsKey(aMovie)){
				aVertexMovie = (Vertex<Movie>) graph.vertices.get(aMovie);
			}
			else{
				aVertexMovie = new Vertex<>(aMovie, graph).setColor("blue");
			}		
					
			//create an edge between the actor and its movie
			aVertexActor.createEdge(aVertexMovie);
			i++;
		}			
			
		return graph;
	}
	
	
	/**
	 * the driver
	 * @param args
	 */
	public static void main(String[] args) {
		GraphMovieActor<?> graph = new GraphMovieActor<>();
		Bridge.init(71, "7855711730", graph, "pedram-bashiri");
		
		Actor anActor= new Actor("Kevin_Bacon_(I)");
		
		//Vertex<Actor> Kevin = new Vertex<>(new Actor("Kevin_Bacon_(I)"), graph).setColor("orange");
		populate(graph,anActor);
		Vertex<Actor> Kevin;
		Kevin = (Vertex<Actor>) graph.vertices.get(anActor);
		//System.out.println(graph.vertices.keySet().iterator().next());
		//Kevin = (Vertex<Actor>) graph.vertices.get(1);
		//System.out.println(graph.vertices);
//		System.out.println(Kevin.getNeighbors());
		
		//getting an iterator over the edges, 
		//and the address of the first edge in the list with next()
		Iterator<Entry<AbstractVertex<?>, String>> i = Kevin.getNeighbors().entrySet().iterator();
		while(i.hasNext()){
			System.out.println(i.next().getKey().getIdentifier());
		}

		System.out.println(Kevin.getNeighbors().keySet().iterator().next()); 
		System.out.println(Kevin.getNeighbors().entrySet().iterator().next());// this is the first child
		//---------------------------------------	

		//System.out.println(Kevin.outgoing.get(4).getDestination().getIdentifier());
		System.out.println("Kevin's first movie: " + Kevin.next().getIdentifier());
		System.out.println("Kevin's second movie: " + Kevin.next().getIdentifier());
		System.out.println("Kevin's second movie: " + Kevin.next().getIdentifier());
		System.out.println("Kevin's second movie: " + Kevin.next().getIdentifier());
		System.out.println("Kevin's second movie: " + Kevin.next().getIdentifier());
		
		
		System.out.println("Kevin's [1] movie: " + Kevin.next(1).getIdentifier());
		System.out.println("Kevin's [2] movie: " + Kevin.next(2).getIdentifier());
		System.out.println("Kevin's [3] movie: " + Kevin.next(3).getIdentifier());
		System.out.println("Kevin's [4] movie: " + Kevin.next(4).getIdentifier());
		
		//this node is set to invisible
		Kevin.setInvisible();
		
		//the node is still present in the client's structure
		//including his children and edges
		//but there is no visualization for it because was set to invisible
		System.out.println("Kevin's [1] movie: " + Kevin.next(1).getIdentifier());
		System.out.println("Kevin's [2] movie: " + Kevin.next(2).getIdentifier());
		System.out.println("Kevin's [3] movie: " + Kevin.next(3).getIdentifier());
		System.out.println("Kevin's [4] movie: " + Kevin.next(4).getIdentifier());
		
		//set visible again
		Kevin.setVisible();		
		Bridge.complete();
	}

}