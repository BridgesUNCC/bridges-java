/**
 * @author mihai mehedint
 * @date Nov 07 2014
 * @content This driver reads the IMBD file and creates a graph with actors vertices and movie vertices
 */
package edu.uncc.cs.bridgesdrivers;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import edu.uncc.cs.bridges.*;

public class GraphSmallIMDBdriver {

	public static int max=1500; //this variable holds the number of vertices
	
	public static File imdb;
	public static String pathToFile = "/Users/mihai/Documents/workspace/bridges/src/main/java/edu/uncc/cs/bridgesdrivers/imdb-small-Revised.txt"; 
	
	/**
	 * this method reads the file and creates a file object
	 * @return the file object
	 */
	public static File readFile(){
		imdb = new File(pathToFile);
		 if (!imdb.exists())
	        {
	            System.out.println("\n*************************************************************\n"+
	                               "The file does not exist in the specified location: \n" +
	                                pathToFile + 
	                               "\n*************************************************************\n");
	            
	            System.exit(0);
	        }
		return imdb;
	}
	/**
	 * This method populates the graph with movies and actors from the IMDB file
	 * @param graph 
	 * @param max number of actor-movie associations
	 * @return the IMDB graph
	 */
	public static GraphMovieActor<?> populate(GraphMovieActor<?> graph, int max){	
		Scanner scan = null;
		try {
			scan = new Scanner(readFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int i = 0;
		while(i<max){
			String newLine = scan.nextLine();
			Scanner item = new Scanner(newLine);
			//create the actor vertex
			Actor anActor= new Actor(item.next());
			Vertex<Actor> aVertexActor;
			if (graph.vertices.containsKey(anActor)){
				aVertexActor = (Vertex<Actor>) graph.vertices.get(anActor);
			}
			else{
				aVertexActor = new Vertex<>(anActor, graph).setColor("red");
			}
			//create the movie vertex
			Movie aMovie = new Movie(item.next());
			Vertex<Movie> aVertexMovie;
			if (graph.vertices.containsKey(aMovie)){
				aVertexMovie = (Vertex<Movie>) graph.vertices.get(aMovie);
			}
			else{
				aVertexMovie = new Vertex<>(aMovie, graph).setColor("blue");
			}		
					
			//create an edge between the actor and its movie
			aVertexActor.createEdge(aVertexMovie);
			aVertexMovie.createEdge(aVertexActor);
			i++;
			item.close();
		}
		scan.close();
		return graph;
	}
	
	
	/**
	 * the driver
	 * @param args
	 */
	public static void main(String[] args) {
		GraphMovieActor<?> graph = new GraphMovieActor<>();
		Bridge.init(71, "300587042698", graph, "mmehedin@uncc.edu");
		Vertex<Actor> Kevin = new Vertex<>(new Actor("Kevin_Bacon_(I)"), graph).setColor("orange");
		populate(graph, max);
		//System.out.println(graph.vertices.keySet().iterator().next());
		//Kevin = (Vertex<Actor>) graph.vertices.get(1);
		//---------------------------------------
				//getting the first vertex child of vertex Bob(to get the identifier use getIdentifier()
				//you can replace getIdentifier with any other method for setting the vertex attributes
				System.out.println("Kevin's movie: " + Kevin.next().getIdentifier());
				
				//By calling a second time next() on the same vertex we can get another child of Bob
				//different from the first
				System.out.println(Kevin.next().getIdentifier());
				
				//calling again the next() method on Bob returns null if there are no unvisited children left
				System.out.println(Kevin.next());
				
				//the next(index) method can retrieve a specific element 
				//from the list of children, using its index
				System.out.println(Kevin.next(1).getIdentifier());
				
				//setting the value of the first child of Bob to visited
				System.out.println(Kevin.next(0).getIdentifier()+ " was visited is: " +((Vertex<Actor>) Kevin.next(0)).isVisited());
				((Vertex<Actor>) Kevin.next(0)).setVisited();
				//verifying if the first child vertex of Bob was visited
				System.out.println(Kevin.next(0).getIdentifier()+ " was visited is: " +((Vertex<Actor>) Kevin.next(0)).isVisited());
				
				//---------------------------------------
				
				//getting the collection of neighboring edges
				System.out.println(Kevin.getNeighbors());
				
				//getting the entire list of neighboring edges, 
				//this is the address to the iterator over the hashmap of edges
				System.out.println(Kevin.getNeighbors().entrySet().iterator());
				Iterator<Entry<AbstractVertex<?>, String>> i = Kevin.getNeighbors().entrySet().iterator();
				//getting an iterator over the edges, 
				//and the address of the first edge in the list with next()
				while(i.hasNext()){
					System.out.println(i.next().getKey().getIdentifier());
				}
				System.out.println(Kevin.getNeighbors().entrySet().iterator().next());
				//---------------------------------------	
		
		Bridge.complete();
	}

}
