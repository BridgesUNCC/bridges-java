package edu.uncc.cs.bridgesdrivers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import edu.uncc.cs.bridges.*;

public class Twitter {

	public static <T> void main(String[] args) {
		GraphVisualizer<T> gv = new GraphVisualizer<>();
		
		// Assignment, API key, visualizer, username
		Bridge.init(0, "1157177351793", gv, "mmehedin@uncc.edu");
		
		Deque<Vertex<Follower>> frontier = new ArrayDeque<>();
		Map<String, Vertex<Follower>> visited = new HashMap<>();
		
		// Any actual user on Twitter
		Follower name = new Follower("Joey"); 
		Vertex<Follower> joey = new Vertex(name, gv);
		
		frontier.add(joey);
		visited.put(name.getName(), joey);
		
		for (int expands=0; expands < 10 && !frontier.isEmpty(); expands++) {
			Vertex<Follower> source = frontier.pop();
			for (Follower friend_name : Bridge.getAssociations(source.getIdentifier(), 50)) {
				
				Vertex<Follower> target = visited.get(friend_name);
				if (target == null) {
					target = new Vertex(friend_name, gv);
					visited.put(friend_name.getName(), target);
					frontier.add(target);
				}
				source.createEdge(target);
				
			}
			frontier.remove(source);
		}
		
		Bridge.complete();
	}

}