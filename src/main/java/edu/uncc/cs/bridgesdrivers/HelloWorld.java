package edu.uncc.cs.bridgesdrivers;

import java.io.IOException;
import java.util.List;

import edu.uncc.cs.bridges.*;

public class HelloWorld {

	public static void main(String[] args) throws Exception {
		// TODO Your code here
		GraphVisualizer gv = new GraphVisualizer();
		Bridge.init(0, "693144430396", gv, "mmehedin@uncc.edu");
		//Bridge.setServerURL("http://edu.uncc.cs.bridges.cs.uncc.edu");
		
		Vertex HelloWorld = new Vertex("HelloWorld", gv);
		
		Vertex HiWorld = new Vertex("Back", gv);
		Vertex Bob = new Vertex("Bob", gv);
		Vertex Steve = new Vertex("Steve", gv);
		
		AbstractEdge anEdge=HelloWorld.createEdge(HiWorld);
		System.out.println(anEdge);
		System.out.println(anEdge.getIdentifier());
		System.out.println(HelloWorld.getEdge(HiWorld));
		
		HelloWorld.createEdge(Bob,12);
		HelloWorld.createEdge(Bob,1);
		Steve.createEdge(HelloWorld);
		
		Steve.createEdge(Bob);
		
		Vertex John = new Vertex("John", gv);
		
		Vertex Dave = new Vertex("Dave", gv);
		
		//John.createEdge(Dave);

		Dave.createEdge(John);
		
		Dave.getEdge(John).setColor("red");//works
		Dave.getEdge(John).setWidth(5);//works
		Dave.getEdge(John).setDash(new double[]{5, 10, 5});//works
		Dave.getEdge(Dave).setOpacity(.5);//works
		Bob.setShape("Square");//works
		Bob.setColor("pink");//works
		Bob.setOpacity(1);// works
		Bob.setSize(20);//works
		
		Bridge.complete();
	}

}
