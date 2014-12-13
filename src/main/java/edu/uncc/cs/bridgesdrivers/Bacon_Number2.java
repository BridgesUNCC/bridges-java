package edu.uncc.cs.bridgesdrivers;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

import edu.uncc.cs.bridges.*;

public class Bacon_Number2 {
				
				
			/**
			 * the driver
			 * @param args
			 * @throws FileNotFoundException 
			 */
			public static void main(String[] args) throws FileNotFoundException {
				
				GraphVisualizer<?> gv = new GraphVisualizer();
				Bridge.init(1, "486749122386", gv, "kalpathi60");
				
			
				File file = new File("/Users/mihai/Documents/workspace/bridges/src/main/java/edu/uncc/cs/bridgesdrivers/imdb-small-Revised.txt");
				//File file = new File("/C:/Users/Pedram/Downloads/test.txt");
				Scanner input = new Scanner(file);
				
				int lines = 0;
				String line = null;
				String parts[] = null;
				String actorName = null;
				String movieName = null;
				
				while(input.hasNextLine())
				{
					lines++;
					line =input.nextLine();
					parts = line.split(" ");
					actorName = parts[0];
					movieName=parts[1];
					
					Vertex<String> aVertexActor;
					Vertex<String> aVertexMovie;
					AbstractEdge edge;
					
					if (gv.vertices.containsKey(actorName)){
						aVertexActor = (Vertex<String>) gv.vertices.get(actorName);
					}
					else{
						aVertexActor = new Vertex<String>(actorName,gv);
						aVertexActor.setColor("red");
						aVertexActor.setOpacity(0.7);
						aVertexActor.setInvisible();
					}
					
					if (gv.vertices.containsKey(movieName)){
						aVertexMovie = (Vertex<String>) gv.vertices.get(movieName);
					}
					else{
						aVertexMovie = new Vertex<String>(movieName,gv);
						aVertexMovie.setColor("green");
						aVertexMovie.setOpacity(0.7);
						aVertexMovie.setInvisible();
					}
					
					edge=aVertexActor.createEdge(aVertexMovie);
					edge.setColor("red");
					edge.setOpacity(0.2);
					edge=aVertexMovie.createEdge(aVertexActor);
					edge.setColor("green");
					edge.setOpacity(0.2);
					
				}
				
				Vertex<String> kevin = (Vertex<String>) gv.vertices.get("Kevin_Bacon_(I)");
				Vertex<String>	footloose = (Vertex<String>) gv.vertices.get("Christian_Slater");
				
				Map<Vertex<String>, Vertex<String>> prev = BFS2(gv, footloose, kevin);
				Vertex<String> target = kevin;
				// display the path
				System.out.println(prev);
				for (Vertex<String>  v = prev.get(target); v != null; v = prev.get(v)) {
					System.out.println(v.getIdentifier());
					//System.out.println(v.getVisibility()+" "+target.getVisibility());
					v.getEdge(target).setColor("blue");
					target = v;
				}
				Bridge.complete();
			}
			
					
			//This method does the BFS for 2 levels
			public static void BFS(GraphVisualizer<?> G, Vertex<String> start, int level) {
				Queue<Vertex<String>> Q = new Queue<Vertex<String>>();
				
				int levelCount = 0;
				int nodesInThisLevel = 1;
				int nodesToNextLevel = 0;
				
				visitBFSnode(start);
				
				Q.enQueue((Vertex<String>)start);
				
				
				while(Q.length() > 0 && levelCount < level) {
					System.out.println("Queue size:" + Q.length());
					
					Vertex<String> startNode =  Q.deQueue().getIdentifier();
					System.out.println("Dequeing " + startNode.getIdentifier());
					int numChildren = startNode.getNeighbors().size();
					System.out.println("Num Children(" + levelCount + "):" + numChildren);
					for(int i=0; i<startNode.getNeighbors().size();i++){
						
						Vertex<String> v = (Vertex<String>) startNode.next();
						System.out.println("Child:" + v.getIdentifier());

						//if v is not visited already
						if(!v.isVisited()) {
							visitBFSnode(v);
		
							Q.enQueue(v);
							nodesToNextLevel++;  //tracks nodes in next level of traversal
						}
					}
					
													//handle depth
					nodesInThisLevel--;
					if(nodesInThisLevel == 0) {
						levelCount++;
						nodesInThisLevel = nodesToNextLevel;
						nodesToNextLevel = 0;
					}	
				}
			}

			public static Map<Vertex<String>, Vertex<String>> BFS2(GraphVisualizer<?> G, Vertex<String> start, Vertex<String> target) {
				Queue<Vertex<String>> Q = new Queue<Vertex<String>>();
				
				Map<String, Integer> distances = new HashMap<String, Integer>();
				Map<Vertex<String>, Vertex<String>> prev = new HashMap<Vertex<String>, Vertex<String>>();

				
				visitBFSnode(start);
				prev.put(start, null);
				distances.put(start.getIdentifier(), 0);
				
				Q.enQueue((Vertex<String>)start);
				
				while(Q.length() != 0) {
					
					Vertex<String> startNode =  Q.deQueue().getIdentifier();
					System.out.println("Dequeing " + startNode.getIdentifier());
					for(int i=0; i<startNode.getNeighbors().size();i++){
						
						Vertex<String> v = (Vertex<String>) startNode.next();
						
						//System.out.println("Child:" + v.getIdentifier());
													// update its distance from the start node
						distances.put(v.getIdentifier(), distances.get(startNode.getIdentifier()) + 1);
						prev.put(v, startNode);
						if (v.getIdentifier() == target.getIdentifier()){	//found the target!
							System.out.println("Found the Target - Kevin Bacon Node! Distance is " +
									distances.get(target.getIdentifier()));
							visitBFSnode(v);
							return prev;
						}
															//if v is unvisited, then visit and enqueue
						if(!v.isVisited()) {
							visitBFSnode(v);
							Q.enQueue(v);
						}
					}	
				}							
				
				return prev;
			}
			private static void visitBFSnode(Vertex<String> v) {
							
				v.setVisited();
				v.setVisible();
			}

		}