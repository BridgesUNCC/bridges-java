package edu.uncc.cs.bridgesdrivers;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.uncc.cs.bridges.*;

public class TwitterWithEdgeWeights {

	public static<T> void main(String[] args) throws Exception {
		// TODO Your code here
		GraphVisualizer<Follower> gv = new GraphVisualizer<>();

		Bridge.init(0, "693144430396", gv, "mmehedin@uncc.edu");
		int expands_remaining = 10;
		
		Deque<Vertex<Follower>> frontier = new ArrayDeque<>();
		Map<String, Vertex<Follower>> visited = new HashMap<>();
		Map<String, Vertex<Follower>> parent_of = new HashMap<>();
		//actual user on Twitter
		Follower name = new Follower("Joey");
		//the 
		Vertex<Follower> joey = new Vertex(name, gv);
		//make it easier to find the 'root'
		joey.setSize(20);
		joey.setColor("orange");
		
		Vertex<Follower> source;
		
		frontier.add(joey);
		visited.put(name.getName(),  joey);
		
		while ((!frontier.isEmpty()) && expands_remaining > 0) {
			source = frontier.pop();
			for (Follower friend_name : Bridge.getAssociations(source.getIdentifier(),50)) {
				
				Vertex<Follower> target = visited.get(friend_name);
				if (target == null) {
					target = new Vertex(friend_name, gv);
					parent_of.put(target.getIdentifier().getName(), source);
					// The student's do this part for fun! :P
					target.setSize(source.getSize() -4);
					visited.put(friend_name.getName(), target);
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