package bridges;

import java.util.ArrayList;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/*
 * Follower Graph Node
 * Uses Bridge API for twitter followers:
 * Implies that:
 * 1:
 *	/streams/twitter-friends/user/SCREENNAME
 *		->
 *	{ "id": 19091039490191 }
 *
 *	2:
 *	/streams/twitter-friends/followers/ID
 *	->
 *	[
 *		{"id": 193948401081
 *		 "screen_name": "Fred"}
 *	]
 */
public class FollowerGraphNode {
	public long userid;
	public String name;
	public boolean opened = false;
	
	FollowerGraph graph;
	ArrayList<FollowerGraphNode> followers;
	
	FollowerGraphNode(long id, String screen_name, FollowerGraph g) {
		userid = id;
		name = screen_name;
		graph = g;
	}
	
	// Convenience method, intended for creating the first node without knowing the id
	FollowerGraphNode(String screen_name, FollowerGraph g) {
		graph = g; 
		String user = graph.exec(Request.Get(graph.url("/user/" + screen_name)));
		userid = new JsonParser()
			.parse(user)
			.getAsJsonObject()
			.get("id")
			.getAsLong();
	}
	
	public ArrayList<FollowerGraphNode> getFollowers() {
		if (!opened) {
			String followers_string = graph.exec(Request.Get(graph.url("followers/" + userid)));
			JsonArray follower_json = new JsonParser()
				.parse(followers_string)
				.getAsJsonArray();
			
			for (JsonElement e : follower_json) {
				long id = e.getAsJsonObject().get("id").getAsLong();
				String name = e.getAsJsonObject().get("name").getAsString();
				if (graph.nodes.containsKey(id))
					followers.add(graph.nodes.get(id));
				else
					followers.add(new FollowerGraphNode(id, name, graph));
			}
			opened = true;
		}
		return followers;
	}
}
