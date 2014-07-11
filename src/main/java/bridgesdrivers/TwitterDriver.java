package bridgesdrivers;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bridges.*;

public class TwitterDriver {

	public static void main(String[] args) throws Exception {
		// TODO Your code here
		GraphVisualizer gv = new GraphVisualizer();

		Bridge.init(0, "585371696619", gv);
		Bridge.setServerURL("http://bridges-cs.herokuapp.com");
		int expands_remaining = 10;
		
		Deque<Vertex> frontier = new ArrayDeque<>();
		Map<String, Vertex> visited = new HashMap<>();
		Map<String, Vertex> parent_of = new HashMap<>();
		String name = "twitter.com/Joey";
		Vertex joey = new Vertex(name, gv);
		//make it easier to find the 'root'
		joey.setSize(20);
		joey.setColor("orange");
		
		frontier.add(joey);
		visited.put(name,  joey);
		System.out.println(
				Bridge.getAssociations(joey.getIdentifier(), 50));
		
		
		while ((!frontier.isEmpty()) && expands_remaining > 0) {
			Vertex source = frontier.pop();
			for (String friend_name : Bridge.getAssociations(source.getIdentifier(),50)) {
				
				Vertex target = visited.get(friend_name);
				if (target == null) {
					target = new Vertex(friend_name, gv);
					parent_of.put(target.getIdentifier(), source);
					//System.out.println("Here: " + target.getIdentifier());
					// The student's do this part for fun! :P
					target.setSize(source.getSize() -4);
					visited.put(friend_name, target);
					frontier.add(target);
				}

				source.createEdge(target);
			}
			frontier.remove(source);
			expands_remaining -= 1;
		}
		
		// Find the route from Joey to Michael by going backward
		// A BFS will give an MST here because the weights are always 1.
		// Luckily, we just did a BFS
		/*String node = "twitter.com/lordsol_";
		visited.get(node).setColor("green");
		
		while (! node.equals("twitter.com/Joey")) {
			Vertex parent = parent_of.get(node);
			parent.getEdge(visited.get(node)).setColor("red");
			node = parent.getIdentifier();
		}*/
		
		Bridge.complete();
	}

}