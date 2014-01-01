package bridges;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.apache.http.client.fluent.Request;
import org.junit.Test;
import org.junit.Before;

public class FollowerGraphTest {
	@Before
	public void fromScreenName() {
		NetworkStubbedGraph graph = new NetworkStubbedGraph("");
		assertNotNull(graph);
	}

	@Test
	public void login() {
		//NetworkStubbedGraph graph = new NetworkStubbedGraph("{\"id\":1705191031,\"id_str\":\"1705191031\",\"name\":\"Sean Gallagher\",\"screen_name\":\"sean_tater\",\"location\":\"\",\"description\":\"CS+German Student, Research Assistant\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":0,\"friends_count\":10,\"listed_count\":0,\"created_at\":\"Tue Aug 27 17:09:26 +0000 2013\",\"favourites_count\":0,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":0,\"lang\":\"en\",\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_tile\":false,\"profile_image_url\":\"http://pbs.twimg.com/profile_images/378800000370559197/d9e2c1d6805f7b9852e8cddd5b300b90_normal.jpeg\",\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/378800000370559197/d9e2c1d6805f7b9852e8cddd5b300b90_normal.jpeg\",\"profile_link_color\":\"0084B4\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"default_profile\":true,\"default_profile_image\":false,\"following\":false,\"follow_request_sent\":false,\"notifications\":false}");
		NetworkStubbedGraph graph = new NetworkStubbedGraph("");
		assertNotNull(graph.root);
	}
	
	@Test
	public void getFollowers() {
		ArrayList<FollowerGraphNode> followers = graph().root.getFollowers();
		assertTrue(followers.size() > 0);
	}
}

class NetworkStubbedGraph extends FollowerGraph {
	String request_returns;
	NetworkStubbedGraph(String r) {
		super("name", "password", "stream");
		request_returns = r;
	}
	
	@Override
	public String exec(Request etc) {
		return request_returns;
	}
}