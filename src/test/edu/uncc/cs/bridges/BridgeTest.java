package edu.uncc.cs.bridges;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BridgeTest {
	
	static GraphVisualizer<Follower> graph;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		graph = new GraphVisualizer<>();
		Bridge.init(0, "170195159766", graph, "mmehedin@uncc.edu");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testGetUserName() {
		assertEquals("The username has an unexpected value.", "mmehedin@uncc.edu", Bridge.getUserName());
	}

	@Test
	public final void testSetUserName() {
		Bridge.setUserName("mmehedin@uncc.edu");
		assertEquals("The username has an unexpected value. setUserName did not work.", "mmehedin@uncc.edu", Bridge.getUserName());
	}

	@Test
	public final void testInit() {
		//Bridge.init(assignment, key, visualizer, username);
		Bridge.init(1, "170195159766", graph, "mmehedin@uncc.edu");
		assertEquals("The assignment integer value is unexpected.", 1, Bridge.getAssignment());
		assertEquals("The key string value is unexpected.", "170195159766", Bridge.getKey());
		assertEquals("The visualizer value is unexpected.", graph, Bridge.getVisualizer());
		assertEquals("The username string value is unexpected.", "mmehedin@uncc.edu", Bridge.getUserName());
		
		
	}

	@Test
	public final void testGetAssignment() {
		assertEquals("The assignment integer value is unexpected.", 0, Bridge.getAssignment());
	}

	@Test
	public final void testSetAssignment() {
		Bridge.setAssignment(12);
		assertEquals("The assignment integer value is unexpected.", 12, Bridge.getAssignment());
		Bridge.setAssignment(0);
	}

	@Test
	public final void testGetKey() {
		assertEquals("The key has an unexpected string value.", "170195159766", Bridge.getKey());
	}

	@Test
	public final void testSetKey() {
		Bridge.setKey("170195159000");
		assertEquals("The key has an unexpected string value.", "170195159000", Bridge.getKey());
		Bridge.setKey("170195159766");
	}

	@Test
	public final void testGetServerURL() {
		assertEquals("The server URL is incorrect.","http://bridges-cs.herokuapp.com",Bridge.getServerURL());
	}

	@Test
	public final void testSetServerURL() {
		Bridge.setServerURL("http://bridges-cs.herokuapp.com");
		assertEquals("The server URL is incorrect.","http://bridges-cs.herokuapp.com",Bridge.getServerURL());
	}

	@Test
	public final void testGetVisualizer() {
		assertEquals("The visualizer has an unexected value.", graph, Bridge.getVisualizer());
	}

	@Test
	public final void testSetVisualizer() {
		GraphVisualizer<Actor> actorGraph = new GraphVisualizer<>();
		Bridge.setVisualizer(actorGraph);
		assertEquals("The visualizer has an unexected value.", actorGraph, Bridge.getVisualizer());
		Bridge.setVisualizer(graph);
	}

	@Test
	public final void testUpdate() {
		fail("Not yet implemented"); // TODO
		//assertEquals("", Bridge.backend);
	}

	@Test
	public final void testComplete() {
		fail("Not yet implemented"); // TODO
		//see update first
	}

	@Test
	public final void testTrimComma() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testQuote() {
		assertEquals("The internal formating for JSON is not working.", "\"mihai\"", Bridge.quote("mihai"));
	}

	@Test
	public final void testUnquote() {
		assertEquals("The internal formating for JSON is not working.", "mihai", Bridge.unquote("\"mihai\""));
	}

	@Test
	public final void testSorted_values() throws RateLimitException {
		Vertex aFollower = new Vertex("Bob", graph);
		Follower aF = new Follower("Bob");
		List aMap = Bridge.followers(aF, 5);
		List sorted_values = new ArrayList<>(Bridge.followers(aF, 5));

		//assertEquals("The sorted_values return an unexpected value.", Collections.sort(sorted_values), Bridge.sorted_values(aMap));
	}

	@Test
	public final void testSorted_entries() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAssociationsFollowerInt() {
		Follower aFollower = new Follower("Bob");
		assertEquals("The number of followers is not exact.", -1.0 ,Bridge.getAssociations(aFollower, 5).lastIndexOf("Bob"), 0.0001);
		assertNotNull("The getAssociations() method returned a null value.", Bridge.getAssociations(aFollower, 5));
		List aList = Bridge.getAssociations(aFollower, 5);
		assertEquals("The number of followers is not exact.", new Follower("Gabriel") ,aList.get(aList.size()-1));
		assertEquals("The size of the follower list is incorrect.", 3 ,aList.size());
	}

	@Test
	public final void testGetAssociationsActorInt() {
		Actor anActor = new Actor("Bob");
		assertEquals("The number of actors is not exact.", -1.0 ,Bridge.getAssociations(anActor, 5).lastIndexOf("Bob"), 0.0001);
		assertNotNull("The getAssociations() method returned a null value.", Bridge.getAssociations(anActor, 5));
		List aList = Bridge.getAssociations(anActor, 5);
		assertEquals("The number of actors is not exact.", new Actor("Jude Law") ,aList.get(aList.size()-1));
		assertEquals("The size of the actor list is incorrect.", 5 ,aList.size());
	}

	@Test
	public final void testGetAssociationsMovieInt() {
		Movie aMovie = new Movie("The Matrix");
		assertEquals("The number of movies is not exact.", -1.0 ,Bridge.getAssociations(aMovie, 5).lastIndexOf("The Matrix"), 0.0001);
		assertNotNull("The getAssociations() method returned a null value.", Bridge.getAssociations(aMovie, 5));
		List aList = Bridge.getAssociations(aMovie, 5);
		assertEquals("The number of movies is not exact.", new Movie("The Shawshank Redemption") ,aList.get(aList.size()-1));
		assertEquals("The size of the movies' list is incorrect.", 5 ,aList.size());
	}

	@Test
	public final void testFollowers() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetTwitterTimeline() throws RateLimitException {
		//Follower aFollower = new Follower("Bob");
		//List<Tweet> aList = new ArrayList<>();
		//aList.add(new Tweet("Tweet [content=PDF too big to email? Shrink PDFs from any browser with SmallPDF: , date=Wed Jan 07 08:42:57 EST 1970]"));
		//aList.add(new Tweet("Tweet [content=If you want the perfect egg salad, you should use your hands: , date=Thu Jan 15 11:25:39 EST 1970]"));
		//aList.add(new Tweet("Tweet [content=What's the weather like in Italy in June? Find out and share with Climatology for Android: , date= Fri Jan 02 15:15:26 EST 1970]"));
		//aList.add(new Tweet("Tweet [content=Ride your bike barefoot with a sponge on each pedal: , date=Sun Jan 25 02:13:33 EST 1970]"));
		//aList.add(new Tweet("Tweet [content=They say opposites attract, but they also annoy each other pretty quickly. Here's why and how to deal with it: , date=Sun Jan 04 06:17:15 EST 1970]"));
		//assertEquals("Twitter timeline malfunction.", 
		//		aList, Bridge.getTwitterTimeline(aFollower, 5));
	}

	@Test
	public final void testMovies() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testActors() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetEdgeWeight() {
		Follower follower1 = new Follower("John");
		Vertex<Follower> John=new Vertex<>(follower1, graph);
		Follower follower2 = new Follower("Jane");
		Vertex<Follower> Jane=new Vertex<>(follower2, graph);
		John.createEdge(Jane, 15.4);
		assertEquals("The edge weight is incorrect.", 15.4, John.getEdge(Jane).getWeight(), 0.0001);
	}

}
