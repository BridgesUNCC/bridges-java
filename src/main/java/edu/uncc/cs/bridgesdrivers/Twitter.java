package edu.uncc.cs.bridgesdrivers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import edu.uncc.cs.bridges.*;

public class Twitter {

	public static void main(String[] args) {
		GraphVisualizer gv = new GraphVisualizer();
		
		// Assignment, API key, visualizer, username
		Bridge.init(0, "693144430396", gv, "mmehedin@uncc.edu");
		
		Deque<Vertex> frontier = new ArrayDeque<>();
		Map<String, Vertex> visited = new HashMap<>();
		
		// Any actual user on Twitter
		String name = "twitter.com/Joey"; 
		Vertex joey = new Vertex(name, gv);
		
		frontier.add(joey);
		visited.put(name, joey);
		
		for (int expands=0; expands < 10 && !frontier.isEmpty(); expands++) {
			Vertex source = frontier.pop();
			for (String friend_name : Bridge.getAssociations(source.getIdentifier(), 50)) {
				
				Vertex target = visited.get(friend_name);
				if (target == null) {
					target = new Vertex(friend_name, gv);
					visited.put(friend_name, target);
					frontier.add(target);
				}
				source.createEdge(target);
				
			}
			frontier.remove(source);
		}
		
		Bridge.complete();
	}

}