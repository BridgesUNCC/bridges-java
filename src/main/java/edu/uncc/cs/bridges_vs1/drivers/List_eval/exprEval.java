package edu.uncc.cs.bridges_vs1.drivers.List_eval;
import edu.uncc.cs.bridgesV2.validation.OutputLog;
import edu.uncc.cs.bridgesV2.connect.Bridges;
import edu.uncc.cs.bridgesV2.base.SLelement;

import java.util.Map;
import java.util.HashMap;

public class exprEval{
	public static void main(String[] args) throws Exception{
    	OutputLog alog = new OutputLog();
    								//create the Bridges object
		Bridges<Integer> bridge = new Bridges<Integer>(3, "486749122386", 
										"kalpathi60");
		bridge.toggleJSONdisplay();
    								//create  a linked list stack
		LStack<Integer> lstack = new LStack<Integer>();

		HashMap<String,Integer> hmap = new HashMap<String, Integer>();
			hmap.put ("a", 5);
			hmap.put ("b", 10);
			hmap.put ("c", 3);
			hmap.put ("d", 4);
			hmap.put ("e", 7);
			hmap.put ("f", 1);
			hmap.put ("g", 10);

		String expr = "abc/-d+";
			//set visualizer type
		//bridge.setDataStructure(lstack.stackTop(), "llist");	
		bridge.visualize();
		evalPostfix(lstack, hmap, bridge, expr);
  
									//Print the JSON to console
//		System.out.println(bridge.getJSON());
        
        							// visualize the list
		bridge.visualize();
		alog.returnStream();
    }
	
	public static void  evalPostfix(LStack<Integer> lstack, 
			Map<String, Integer> symbol_table, Bridges<Integer> br, String expr) {
			System.out.println(symbol_table);
		int val = 0;
		for (int k = 0; k < expr.length(); k++) {
			char c = expr.charAt(k);
			if (c == 'a' || c == 'b' || c == 'c' || c == 'd' || 
				c == 'e' || c == 'f' || c == 'g' ) {
System.out.println("Input char: " + c);
				val = symbol_table.get(String.valueOf(c));
				lstack.push(symbol_table.get(String.valueOf(c)) );
			}
			else {  // its an operator, push into stack
				int opnd2 = lstack.pop();
System.out.println("popping: " + opnd2);
				int opnd1 = lstack.pop();
System.out.println("popping: " + opnd1);
				switch (c) {
					case '+': 
						val =  opnd1 + opnd2; 
						break;
					case '-': 
						val =  opnd1 - opnd2; 
						break;
					case '*': 
						val =  opnd1 * opnd2; 
						break;
					case '/': 
						val =  opnd1 / opnd2; 
						break;
				}
				lstack.push(val);
				lstack.stackTop().setLabel(String.valueOf(val));
				//br.setDataStructure(lstack.stackTop(), "llist");	
				br.visualize();
			}
		}
		System.out.println("Evaluated Value:" + lstack.pop());
	}
}
