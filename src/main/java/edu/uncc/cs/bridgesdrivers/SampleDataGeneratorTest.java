/**
 * 
 */
package edu.uncc.cs.bridgesdrivers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.uncc.cs.bridges.Actors;
import edu.uncc.cs.bridges.Follower;
import edu.uncc.cs.bridges.Movies;
import edu.uncc.cs.bridges.SampleDataGenerator;

/**
 * @author sean
 *
 */
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
		List<Actors> names = new ArrayList<>();
		names.add(new Actors("Val Kilmer"));
		names.add(new Actors("Christian Bale"));
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
		List<Movies> names = new ArrayList<>();
		names.add(new Movies("Paths of Glory"));
		names.add(new Movies("It's a Wonderful Life"));
		names.add(new Movies("American Beauty"));
		names.add(new Movies("Monty Python and the Holy Grail"));
		assertEquals(names, SampleDataGenerator.getMovies("Joey", 5));
		
		// Empty
		assertEquals(5, SampleDataGenerator.getMovies("", 5).size());
		
		// Null
		assertEquals(5, SampleDataGenerator.getMovies(null, 5).size());
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.SampleDataGenerator#getChoices(java.lang.String, java.lang.String, java.lang.String[], int, int, boolean)}.
	 */
	@Test
	public void testGetChoices() {
		assertTrue(true);
	}

}
