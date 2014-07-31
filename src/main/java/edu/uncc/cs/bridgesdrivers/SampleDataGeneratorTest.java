/**
 * 
 */
package edu.uncc.cs.bridgesdrivers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
		List<String> names = new ArrayList<>();
		names.add("twitter.com/Carter");
		names.add("twitter.com/Addison");
		assertEquals(names, SampleDataGenerator.getFriends("twitter.com/Joey", 5));
		
		// Empty
		assertEquals(5, SampleDataGenerator.getFriends("", 5).size());
		
		// Null
		assertEquals(3, SampleDataGenerator.getFriends(null, 5).size());
		
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.SampleDataGenerator#getActors(java.lang.String, int)}.
	 */
	@Test
	public void testGetActors() {
		List<String> names = new ArrayList<>();
		names.add("actor/Billy Bob Thornton");
		names.add("actor/Daniel Day-Lewis");
		names.add("actor/Richard Gere");
		assertEquals(names, SampleDataGenerator.getActors("actor/Joey", 5));
		
		// Empty
		assertEquals(5, SampleDataGenerator.getActors("", 5).size());
		
		// Null
		assertEquals(2, SampleDataGenerator.getActors(null, 5).size());
		
	}

	/**
	 * Test method for {@link edu.uncc.cs.bridges.SampleDataGenerator#getMovies(java.lang.String, int)}.
	 */
	@Test
	public void testGetMovies() {
		List<String> names = new ArrayList<>();
		names.add("movie/A Clockwork Orange");
		names.add("movie/Singin' in the Rain");
		names.add("movie/Saving Private Ryan");
		names.add("movie/Spirited Away");
		assertEquals(names, SampleDataGenerator.getMovies("movie/Joey", 5));
		
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
