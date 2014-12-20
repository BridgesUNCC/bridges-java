package edu.uncc.cs.bridgesdrivers;

import java.io.IOException;
import java.util.List;

import edu.uncc.cs.bridges.*;

public class HelloWorld {

	public static <Follower> void main(String[] args) throws Exception {
		// TODO Your code here
		GraphVisualizer<Follower> gv = new GraphVisualizer<>();
		Bridge.init(10, "300587042698", gv, "mmehedin@uncc.edu");
		//Bridge.setServerURL("http://edu.uncc.cs.bridges.cs.uncc.edu");
		
		Vertex<Follower> HelloWorld = new Vertex("HelloWorld", gv);
		
		Vertex<Follower> HiWorld = new Vertex("Back", gv);
		Vertex<Follower> Bob = new Vertex("Bob", gv);
		Vertex<Follower> Steve = new Vertex("Steve", gv);
		
		AbstractEdge<Follower> anEdge=HelloWorld.createEdge(HiWorld);
		HelloWorld.createEdge(Bob,12);
		HelloWorld.createEdge(Bob,1);
		Steve.createEdge(HelloWorld);
		
		Steve.createEdge(Bob);
		
		Vertex<Follower> John = new Vertex("John", gv);
		
		Vertex<Follower> Dave = new Vertex("Dave", gv);
		
		//John.createEdge(Dave);

		Dave.createEdge(John,"hashCodeWeight");
		
		Dave.getEdge(John).setColor("red");//works
		Dave.getEdge(John).setWidth(5);//works
		Dave.getEdge(John).setDash(new double[]{5, 10, 5});//works
		//Dave.getEdge(Dave).setOpacity(.1);//doesn't work unless the edge is created first
		Dave.setShape("Square");
		Bob.setShape("Square");//works
		Bob.setColor("pink");//works
		Bob.setOpacity(1);// works
		Bob.setSize(20);//works
		HelloWorld.createEdge(John);
		
		//---------------------------------------
		//getting the first vertex child of vertex Bob(to get the identifier use getIdentifier()
		//you can replace getIdentifier with any other method for setting the vertex attributes
		System.out.println(Bob.next().getIdentifier());
		
		//By calling a second time next() on the same vertex we can get another child of Bob
		//different from the first
		System.out.println(Bob.next().getIdentifier());
		
		//calling again the next() method on Bob returns null if there are no unvisited children left
		System.out.println(Bob.next());
		
		//the next(index) method can retrieve a specific element 
		//from the list of children, using its index
		System.out.println(Bob.next(1).getIdentifier());
		
		//setting the value of the first child of Bob to visited
		System.out.println(Bob.next(0).getIdentifier()+ " was visited is: " +((Vertex<Follower>) Bob.next(0)).isVisited());
		((Vertex<Follower>) Bob.next(0)).setVisited();
		//verifying if the first child vertex of Bob was visited
		System.out.println(Bob.next(0).getIdentifier()+ " was visited is: " +((Vertex<Follower>) Bob.next(0)).isVisited());
		
		//---------------------------------------
		
		//getting the collection of neighboring edges
		System.out.println(Bob.getNeighbors());
		
		//getting the entire list of neighboring edges, 
		//this is the address to the iterator over the hashmap of edges
		System.out.println(Bob.getNeighbors().iterator());
		
		//getting an iterator over the edges, 
		//and the address of the first edge in the list with next()
		System.out.println(Bob.getNeighbors().iterator().next());
		//---------------------------------------	
		System.out.println(Bridge.getJSON());
		Bridge.complete();
	}

}
