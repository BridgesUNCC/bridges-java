package edu.uncc.cs.bridgesdrivers;

import edu.uncc.cs.bridges.*;

public class StackDriver {

	public static void main(String[] args) {
		Stack<Follower> stack = new Stack<Follower>();
		Bridge.init(6, "1157177351793", stack, "mmehedin@uncc.edu");
		
		//this statement sets the status of the stack to circular
		stack.circularLList();
		//This works also:
		//AbstractVertex<Follower> entity1= new stackElement<>(new Follower("entity1"), stack);
		//AbstractVertex<Follower> entity2= new stackElement<>(new Follower("entity2"), stack);
		
		//Adding elements to the stack
		stack.push(new Follower("entity1"));
		stack.push(new Follower("entity2"));
		
		//the stack is emptied
		stack.clear();
		
		
		stack.push(new Follower("entity2"));
		stack.push(new Follower("entity3"));
		stack.push(new Follower("entity4"));
		
		//after the noLListVisualization statement the elements are not connected visually by lines
		//the user can display the stack with or without lines in between
		//circularLList overrides noLListVisualization
		//stack.noLListVisualization();
		
		stack.push(new Follower("entity5"));
		stack.push(new Follower("entity6"));
		stack.push(new Follower("entity7"));
		stack.push(new Follower("entity8"));
		stack.push(new Follower("entity9"));
		stack.push(new Follower("entity10"));
		//System.out.println(stack.vertices);
		
		//create a circular stack based on LList
		//stack.circularLList();
		stack.pop();
		stack.pop();
		stack.pop();
		
		//to set properties to elements
		StackElement<Follower> entity11 = stack.push(new Follower("entity11"));
		StackElement<Follower> entity12 = stack.push(new Follower("entity12"));
		//retrieve an line object between 2 stack elements
		//to test this remove the comment sign from the statement below
		System.out.println(entity12.getStackEdge(entity11).setColor("red").setDash(new double[]{5,10,5}));
		
		//One can iterate through the elements of the stack using next();
		//to test this remove the comment sign from the statement below
		//System.out.println(entity11.next().next().setColor("blue"));
		
		//Element's properties can be changed
		//to test this remove the comment sign from the statement below
		//entity11.setColor("grey");
		
		stack.push(new Follower("entity13"));
		stack.push(new Follower("entity14"));
		stack.pop();
		stack.pop();
		
		System.out.print("\nJSON: ");
		Bridge.complete();

	}

}
