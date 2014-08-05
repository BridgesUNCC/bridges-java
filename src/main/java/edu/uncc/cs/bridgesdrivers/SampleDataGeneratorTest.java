/**
 * 
 */
package edu.uncc.cs.bridgesdrivers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.uncc.cs.bridges.Actor;
import edu.uncc.cs.bridges.Follower;
import edu.uncc.cs.bridges.Movie;
import edu.uncc.cs.bridges.SampleDataGenerator;

public class SampleDataGeneratorTest {

	/**
	 * Test method for {@link edu.uncc.cs.bridges.SampleDataGenerator#getFriends(java.lang.String, int)}.
	 */
	@Test
	public void testGetFriends() {
		// Typical
		List<Follower> names = new ArrayList<>();
		names.add(new Follower("Elizabeth"));
		assertEquals(names, SampleDataGenerator.getFriends("Joey", 5));
		
		// Empty
		assertEquals(5, SampleDataGenerator.getFriends("", 5).size());
		
		// Null
		assertEquals(3, SampleDataGenerator.getFriends(null, 5).size());
		
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.SampleDataGenerator#getActors(java.lang.String, int)}.
	 */
	@Test
	public void testGetCast() {
		List<Actor> names = new ArrayList<>();
		names.add(new Actor("Val Kilmer"));
		names.add(new Actor("Christian Bale"));
		assertEquals(names, SampleDataGenerator.getCast("Pulp Fiction", 5));
		
		// Empty
		assertEquals(5, SampleDataGenerator.getCast("", 5).size());
		
		// Null
		assertEquals(2, SampleDataGenerator.getCast(null, 5).size());
		
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.SampleDataGenerator#getMovies(java.lang.String, int)}.
	 */
	@Test
	public void testGetMovies() {
		List<Movie> names = new ArrayList<>();
		names.add(new Movie("Paths of Glory"));
		names.add(new Movie("It's a Wonderful Life"));
		names.add(new Movie("American Beauty"));
		names.add(new Movie("Monty Python and the Holy Grail"));
		assertEquals(names, SampleDataGenerator.getMovies("Joey", 5));
		
		// Empty
		assertEquals(5, SampleDataGenerator.getMovies("", 5).size());
		
		// Null
		assertEquals(5, SampleDataGenerator.getMovies(null, 5).size());
	}
	
	@Test
	public void testGetTwitterTimeline() {

		List<Movie> names = new ArrayList<>();
		names.add(new Movie("Paths of Glory"));
		names.add(new Movie("It's a Wonderful Life"));
		names.add(new Movie("American Beauty"));
		names.add(new Movie("Monty Python and the Holy Grail"));
		assertEquals(names, SampleDataGenerator.getMovies("Joey", 5));
		
		// Empty
		assertEquals(5, SampleDataGenerator.getMovies("", 5).size());
		
		// Null
		assertEquals(5, SampleDataGenerator.getMovies(null, 5).size());
	}

}
