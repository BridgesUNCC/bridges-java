package edu.uncc.cs.bridges_vs1.drivers.List_eval;

import edu.uncc.cs.bridgesV2.connect.Bridges;

public class DLListDemo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Bridges<Integer> b = new Bridges<Integer>(42, "691659187196", "cgrafer");

		DLList<Integer> dlist = new DLList<Integer>();


		
		System.out.println("Insert 1");
		dlist.insert(1);
		dlist.next();
		print(dlist);

		b.setDataStructure(dlist.getFront(), "Dllist");
		b.visualize();
		
		System.out.println("Insert 2");
		dlist.insert(2);
		print(dlist);
		b.visualize();

		
		System.out.println("append 3");
		dlist.append(3);
		print(dlist);
		b.visualize();

		
		System.out.println("insrt 4 at beginning");
		dlist.moveToPos(0);
		dlist.insert(4);
		print(dlist);
		b.visualize();

		
		System.out.println("remove 2");
		dlist.moveToPos(2);
		dlist.remove();
		print(dlist);
		b.visualize();

		
		System.out.println("clear and append 99");
		dlist.clear();
		dlist.append(99);
		print(dlist);
		b.setDataStructure(dlist.getFront(), "Dllist");
		b.visualize();

		
		
	}
	
	public static void print(DLList<Integer> l) {
		int start = l.currPos();
		
		l.moveToStart();
		
		for(int i = 0; i < l.length(); i++) {
			
			System.out.print(l.getValue() + " ");
			l.next();
			
		}
		
		System.out.println();
		System.out.println();

		
		l.moveToPos(start);
		
	}

}
