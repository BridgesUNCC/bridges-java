package edu.uncc.cs.bridges_vs1.drivers;
import edu.uncc.cs.bridgesV2.connect.Bridges;import
edu.uncc.cs.bridgesV2.base.SLelement;

public class DriverTestShapes {    
	
	
	public static void main(String[] args)throws Exception{

        //create the Bridges object        
		Bridges<String> bridge =
new Bridges<String>(0, "300587042698", "mmehedin@uncc.edu");

        //create singly-linked elements        
SLelement e0 = new
SLelement<String>("Hello", "");        
SLelement e1 = new
SLelement<String>("World", "");
e1.getVisualizer().setShape("triangle-up");
e0.getVisualizer().setShape("circle");
e0.getVisualizer().setSize(50);
        //create a singly-linked list by adding e1 as e0's next element
	e0.setNext(e1);

        //edit some visual properties of the two elements
e0.getVisualizer().setColor("black");
e0.getVisualizer().setOpacity(0.5);
e1.getVisualizer().setColor("green");

        //pass the first element of the list and the type to the bridges object        
bridge.setDataStructure(e0, "llist");

        // visualize the list        
bridge.visualize();    
}
	}