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

public class TwitterWithEdgeWeights {

	public static void main(String[] args) throws Exception {
		// TODO Your code here
		GraphVisualizer gv = new GraphVisualizer();

		Bridge.init(0, "1022683069234", gv);
		Bridge.setServerURL("http://bridges-cs.herokuapp.com");
		int expands_remaining = 10;
		
		Deque<Vertex> frontier = new ArrayDeque<>();
		Map<String, Vertex> visited = new HashMap<>();
		Map<String, Vertex> parent_of = new HashMap<>();
		//actual user on Twitter
		String name = "twitter.com/Joey";
		//the 
		Vertex joey = new Vertex(name, gv);
		//make it easier to find the 'root'
		joey.setSize(20);
		joey.setColor("orange");
		
		Vertex source;
		
		frontier.add(joey);
		visited.put(name,  joey);
		System.out.println(
				Bridge.getAssociations(joey.getIdentifier(), 50));
		
		
		while ((!frontier.isEmpty()) && expands_remaining > 0) {
			source = frontier.pop();
			for (String friend_name : Bridge.getAssociations(source.getIdentifier(),50)) {
				
				Vertex target = visited.get(friend_name);
				if (target == null) {
					target = new Vertex(friend_name, gv);
					parent_of.put(target.getIdentifier(), source);
					// The student's do this part for fun! :P
					target.setSize(source.getSize() -4);
					visited.put(friend_name, target);
					frontier.add(target);
				}

				source.createEdge(target,"randWeight");
			}
			frontier.remove(source);
			expands_remaining -= 1;
		}
		
		Bridge.complete();
	}

}