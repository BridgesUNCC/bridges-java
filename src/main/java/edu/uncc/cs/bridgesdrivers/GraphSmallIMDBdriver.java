/**
 * @author mihai mehedint
 * @date Nov 07 2014
 * @content This driver reads the IMBD file and creates a graph with actors vertices and movie vertices
 */
package edu.uncc.cs.bridgesdrivers;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import edu.uncc.cs.bridges.*;

public class GraphSmallIMDBdriver {

	public static int max=500; //this variable holds the number of vertices
	
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
		populate(graph, max);
		
		Bridge.complete();
	}

}
