import java.util.HashMap;

import edu.uncc.cs.bridges.Bridge;
import edu.uncc.cs.bridges_vs1.network.Bridges;
import edu.uncc.cs.bridges_vs1.sources.Tweet;
import edu.uncc.cs.bridges_vs1.structure.ADTVisualizer;
import edu.uncc.cs.bridges_vs1.structure.Element;
import edu.uncc.cs.bridges_vs1.structure.SLelement;



public class SLListDemo {
	private static final int ASSIGNMENT = 20;
	
	public static void printList(SLList list) {
		//remember original starting position
		int originalCurr = list.currPos();
		
		//move to start of list
		list.moveToStart();
		
		while(list.getValue() != null) {
			System.out.printf("%d  ", list.getValue().getElement());
			//go to next element
			list.next();
		}
		System.out.println()
		;
		
		//move back to original current position
		list.moveToPos(originalCurr);
	}
	
	public static void main(String[] args) throws Exception {		
		//intialize bridges
		Bridges<Integer> b = new Bridges<Integer>("691659187196", "cgrafer");
		
		//elements
		SLelement<Integer> sle0 = new SLelement<Integer>("head", 0);
		SLelement<Integer> sle1 = new SLelement<Integer>("A = 1", 1);
		SLelement<Integer> sle2 = new SLelement<Integer>("B = 2", 2);
		SLelement<Integer> sle3 = new SLelement<Integer>("C = 3", 3);
		SLelement<Integer> sle4 = new SLelement<Integer>("D = 4", 4);
		
		//make list
		SLList<Integer> list = new SLList<Integer>(sle0);
		list.append(sle1);
		list.append(sle2);
		list.append(sle3);
		list.append(sle4);


		//Output list to console
		System.out.println("Print List");
		printList(list);

		//Pass list to bridges
		b.setDataStructure(sle0, "llist", 13);
		b.complete(ASSIGNMENT);
		System.out.println();

		
		System.out.println("Move to beginning and remove element");
		list.moveToStart();
		list.remove();
		printList(list);
		b.updateSL(ASSIGNMENT);
		System.out.println();
		
		
		System.out.println("Move to 2nd position and remove");
		list.moveToPos(1);			//positions start at 0
		list.remove();
		printList(list);
		b.updateSL(ASSIGNMENT);
		System.out.println();

		
		System.out.println("Insert element #1 at 2nd position");
		list.moveToPos(1);
		list.insert(sle1);
		printList(list);
		b.updateSL(ASSIGNMENT);
		System.out.println();

		
		System.out.println("Remove tail and append element #3");
		list.append(sle3);
		printList(list);
		b.updateSL(ASSIGNMENT);
		System.out.println();

	}

}
