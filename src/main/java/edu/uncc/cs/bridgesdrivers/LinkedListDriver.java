package edu.uncc.cs.bridgesdrivers;

import edu.uncc.cs.bridges.Bridge;
import edu.uncc.cs.bridges.LinkedList;
import edu.uncc.cs.bridges.Vertex;

public class LinkedListDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList aList= new LinkedList();
		
		Bridge.init(0, "1157177351793", aList.list, "mmehedin@uncc.edu");
		
		aList.insert("A", 1);
		aList.insert("B", 1);
		aList.append("C");
		
		Bridge.complete();

	}

}
