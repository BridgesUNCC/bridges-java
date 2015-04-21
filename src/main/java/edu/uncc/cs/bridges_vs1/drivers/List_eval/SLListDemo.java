package edu.uncc.cs.bridges_vs1.drivers.List_eval;

import java.util.HashMap;

import edu.uncc.cs.bridgesV2.base.SLelement;
import edu.uncc.cs.bridgesV2.connect.Bridges;

public class SLListDemo {
	private static final int ASSIGNMENT = 20;

	public static void printList(SLList list) {
		// remember original starting position
		int originalCurr = list.currPos();

		// move to start of list
		list.moveToStart();

		while (list.getValue() != null) {
			System.out.printf("%s", list.getValue());
			// go to next element
			list.next();
		}
		System.out.println();

		// move back to original current position
		list.moveToPos(originalCurr);
	}

	public static void main(String[] args) throws Exception {

		Bridges<Integer> b = new Bridges<Integer>(41, "691659187196", "cgrafer");

		// make list
		// SLList<Integer> list = new SLList<Integer>(sle0);
		SLList<Integer> list = new SLList<Integer>();
		list.append(1);
		list.append(2);
		list.append(3);
		list.append(4);

		// Output list to console
		System.out.println("Print List");
		printList(list);

		// Pass list to bridges
		b.setDataStructure(list.getFront(), "llist");
		b.visualize();
		System.out.println();

		System.out.println("Move to beginning and remove element");
		list.moveToStart();
		list.remove();
		printList(list);
		b.visualize();
		System.out.println();

		System.out.println("Move to 2nd position and remove");
		list.moveToPos(1); // positions start at 0
		list.remove();
		printList(list);
		b.visualize();
		System.out.println();

		System.out.println("Insert element #1 at 2nd position");
		list.moveToPos(1);
		list.insert(1);
		printList(list);
		b.visualize();
		System.out.println();

		System.out.println("Remove tail and append element #3");
		list.append(3);
		printList(list);
		b.visualize();
		System.out.println();

		System.out.println("Clear List");
		list.clear();
		printList(list);
		b.visualize();
		System.out.println();

	}

}
