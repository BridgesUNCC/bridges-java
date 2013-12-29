package bridges;
import java.util.HashMap;

public class FollowerGraph extends Network {
	FollowerGraphNode root;
	public HashMap<Long, FollowerGraphNode> nodes;
	
	FollowerGraph(String username, String password, String screen_name) {
		super(username, password);
		rebase("/streams/followgraph");
		
		// Find your own userid
		root = new FollowerGraphNode(screen_name, this);
	}
	
}
