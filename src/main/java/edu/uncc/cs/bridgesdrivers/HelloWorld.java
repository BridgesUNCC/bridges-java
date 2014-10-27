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
		
		Bridge.complete();
	}

}
