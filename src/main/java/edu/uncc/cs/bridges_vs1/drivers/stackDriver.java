package edu.uncc.cs.bridges_vs1.drivers;


import edu.uncc.cs.bridges_vs1.network.Bridges;
import edu.uncc.cs.bridges_vs1.sources.Tweet;
import edu.uncc.cs.bridges_vs1.structure.SLelement;

public class stackDriver {
	public static void main(String[] args) throws Exception{
    	
    								//create the Bridges object
		Bridges<Integer> bridge = new Bridges<Integer>(13, "300587042698", 
										"mmehedin@uncc.edu");
        
    								//create  a linked list stack
		LStack<Integer> lstack = new LStack<Integer>();

    								//push some elements 
		lstack.push(new Integer(1));
		lstack.push(new Integer(2));
		lstack.push(new Integer(3));
		lstack.push(new Integer(4));
		lstack.push(new Integer(5));

             						//set visualizer type
		bridge.setDataStructure(lstack.stackTop(), "llist");
        
									//Print the JSON to console
		bridge.toggleJSONdisplay();
        
        							// visualize the list
		bridge.visualize();
    }
}
