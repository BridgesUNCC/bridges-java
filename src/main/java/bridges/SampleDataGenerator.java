package bridges;

import java.util.ArrayList;
import java.util.List;

public class SampleDataGenerator {
	
	// Top baby names from 2013
	public static final String[] available_friend_names = {
		"Liam", "Emma",	    		
		"Noah", "Olivia",	    		
		"Ethan", "Sophia",	    		
		"Mason", "Ava",	    		
		"Jacob", "Isabella",	    		
		"Jack", "Mia",	    		
		"Lucas", "Emily",	    		
		"Jackson", "Charlotte",	    		
		"Logan", "Ella",	    		
		"Aiden", "Amelia",	    		
		"Benjamin", "Abigail",	    		
		"James", "Madison",	    		
		"Elijah", "Lily",	    		
		"William", "Avery",	    		
		"Jayden", "Chloe",	    		
		"Oliver", "Harper",	    		
		"Alexander", "Sofia",	    		
		"Michael", "Hannah",	    		
		"Daniel", "Addison",	    		
		"Luke", "Grace",	    		
		"Matthew", "Aubrey",	    		
		"Ryan", "Zoey",	    		
		"Gabriel", "Zoe",	    		
		"Henry", "Layla",	    		
		"Joshua", "Evelyn",	    		
		"Carter", "Aria",	    		
		"Owen", "Natalie",	    		
		"Caleb", "Sophie",	    		
		"Nathan", "Elizabeth",	    		
		"Dylan", "Audrey",	    		
		"Isaac", "Ellie",	    		
		"Connor", "Lucy",	    		
		"Andrew", "Brooklyn",	    		
		"Eli", "Victoria",	    		
		"Wyatt", "Scarlett",	    		
		"Hunter", "Mila",	    		
		"Max", "Anna",	    		
		"Samuel", "Lillian",	    		
		"Evan", "Leah",	    		
		"David", "Riley",
	};
	
	private static String[] available_movie_names = {
			"The Shawshank Redemption",
			"The Godfather",
			"The Godfather: Part II",
			"The Dark Knight",
			"Pulp Fiction",
			"The Good, the Bad and the Ugly",
			"Schindler's List",
			"12 Angry Men",
			"The Lord of the Rings: The Return of the King",
			"Fight Club",
			"The Lord of the Rings: The Fellowship of the Ring",
			"Star Wars: Episode V - The Empire Strikes Back",
			"Inception",
			"Forrest Gump",	
			"One Flew Over the Cuckoo's Nest",
			"The Lord of the Rings: The Two Towers",
			"Goodfellas",
			"Star Wars: Episode IV - A New Hope",
			"The Matrix",
			"Seven Samurai",
			"City of God",
			"Se7en",
			"The Usual Suspects",
			"The Silence of the Lambs",
			"Once Upon a Time in the West",
			"It's a Wonderful Life",
			"Léon: The Professional",
			"Casablanca",
			"Life Is Beautiful",
			"Raiders of the Lost Ark",
			"American History X",
			"Psycho",
			"Rear Window",
			"City Lights",
			"Saving Private Ryan",
			"Spirited Away",
			"The Intouchables",
			"Memento",
			"Modern Times",
			"Terminator 2: Judgment Day",
			"Sunset Blvd.",
			"The Pianist",
			"Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb",
			"Apocalypse Now",
			"The Green Mile",
			"The Departed",
			"Gladiator",
			"Back to the Future",
			"Alien",
			"The Dark Knight Rises",
			"Django Unchained",
			"The Prestige",
			"The Lives of Others",
			"The Great Dictator",
			"The Shining",
			"Cinema Paradiso",
			"The Lion King",
			"Paths of Glory",
			"American Beauty",
			"WALL·E",
			"North by Northwest",
			"Amélie",
			"Citizen Kane",
			"Aliens",
			"Vertigo",
			"Toy Story 3",
			"M",
			"Das Boot",
			"A Clockwork Orange",
			"Taxi Driver",
			"Oldboy",
			"Double Indemnity",
			"Princess Mononoke",
			"Reservoir Dogs",
			"Star Wars: Episode VI - Return of the Jedi",
			"Once Upon a Time in America",
			"Requiem for a Dream",
			"To Kill a Mockingbird",
			"Braveheart",
			"Grave of the Fireflies",
			"Lawrence of Arabia",
			"Eternal Sunshine of the Spotless Mind",
			"Witness for the Prosecution",
			"Full Metal Jacket",
			"Singin' in the Rain",
			"The Sting",
			"Bicycle Thieves",
			"Monty Python and the Holy Grail",
			"Amadeus",
			"Snatch.",
			"All About Eve",
			"Rashomon",
			"L.A. Confidential",
			"The Apartment",
			"The Treasure of the Sierra Madre",
			"Some Like It Hot",
			"For a Few Dollars More",
			"The Third Man",
			"Indiana Jones and the Last Crusade",
			"Inglourious Basterds"
	};
	
	public static final String[] available_actor_names = {
		"Jack Nicholson",
		"Ralph Fiennes",
		"Daniel Day-Lewis",
		"Robert De Niro",
		"Al Pacino",
		"Dustin Hoffman",
		"Tom Hanks",
		"Brad Pitt",
		"Anthony Hopkins",
		"Marlon Brando",
		"Jeremy Irons",
		"Denzel Washington",
		"Gene Hackman",
		"Jeff Bridges",
		"Tim Robbins",
		"Henry Fonda",
		"William Hurt",
		"Kevin Costner",
		"Clint Eastwood",
		"Leonardo DiCaprio",
		"Mel Gibson",
		"Robert Duvall",
		"Samuel L. Jackson",
		"Tommy Lee Jones",
		"Kevin Spacey",
		"Nicolas Cage",
		"Kevin Kline",
		"Morgan Freeman",
		"Michael Caine",
		"Russell Crowe",
		"Bruce Willis",
		"Johnny Depp",
		"Ben Kingsley",
		"Steve McQueen",
		"Tom Cruise",
		"Heath Ledger",
		"Philip Seymour Hoffman",
		"John Malkovich",
		"Christian Bale",
		"Richard Dreyfuss",
		"Jason Robards",
		"Colin Firth",
		"George Clooney",
		"Edward Norton",
		"Sean Connery",
		"Yves Montand",
		"Richard Gere",
		"Gary Oldman",
		"Harrison Ford",
		"Matt Damon",
		"John Gielgud",
		"Joe Pesci",
		"Paul Newman",
		"Woody Harrelson",
		"John Hurt",
		"Sean Penn",
		"Christopher Walken",
		"Mickey Rourke",
		"Peter O'Toole",
		"Michael Douglas",
		"Willem Dafoe",
		"Charlton Heston",
		"Forest Whitaker",
		"James Coburn",
		"Liam Neeson",
		"Will Smith",
		"Robin Williams",
		"Keanu Reeves",
		"Harvey Keitel",
		"Michael Madsen",
		"Kevin Bacon",
		"Ed Harris",
		"Alain Delon",
		"Chris Cooper",
		"Gérard Depardieu",
		"Justin Theroux",
		"Nick Nolte",
		"Val Kilmer",
		"Joaquin Phoenix",
		"Jared Leto",
		"Laurence Fishburne",
		"Antonio Banderas",
		"John Travolta",
		"John Goodman",
		"Arnold Schwarzenegger",
		"Adrien Brody",
		"Michael Keaton",
		"Billy Bob Thornton",
		"Hugo Weaving",
		"Sam Shepard",
		"Jude Law",
		"Geoffrey Rush",
		"Roberto Benigni",
		"Jürgen Prochnow",
		"Alec Baldwin",
		"Joseph Fiennes",
		"Sebastian Koch",
		"F. Murray Abraham",
		"Javier Bardem",
		"John Wayne",
	};
	
	/**
	 * Pick some friends, consistently.
	 * This is a function: identical calls generate identical results.
	 * @param name	Any name
	 * @param max	Maximum number of names to return 
	 * @return about 5 names, up to max, chosen from a list of common names
	 */
	public static List<String> getFriends(String name, int max) {
		return getChoices(name, "twitter.com", available_friend_names, max, 5, false);
	}
	
	/**
	 * Pick some actors for a given movie.
	 * A list of actor names might be better.
	 * 
	 * @param movie		The name of the movie
	 * @param max		The maximum number of actors
	 * @return
	 */
	public static List<String> getActors(String movie, int max) {
		return getChoices(movie, "actor", available_actor_names, max, 7, true);
	}
	
	/**
	 * Pick some movies an actor played in.
	 * @param name		The name of the actor
	 * @param max		The maximum number of movies
	 * @return
	 */
	public static List<String> getMovies(String name, int max) {
		return getChoices(name, "movie", available_movie_names, max, 15, true);
	}
	
	/**
	 * Pick one of the names given a hash
	 * The reason for this is that you can easily pick from either list.
	 * @param available_friend_names
	 * @param hash
	 * @return a String from the list
	 */
	private static String pickOneOf(String[] choices, int hash) {
		// In Java, % can be negative.
		// Force it to be positive.
		// But don't use abs() since that skews the result.
		int h = hash % choices.length;
		if (h < 0)
			h = h + choices.length;
		return choices[h];
	}

	/**
	 * Generic graph generator, using a friend network as analogy.
	 * This is a function: identical calls generate identical results.
	 * 
	 * @param name		String used as a random seed.
	 * @param choices	Choices of 'friends'
	 * @param max		Maximum number of 'friends'
	 * @param average	lambda of friend count exponential distribution
	 * @param include_self	If `choices` contains `name`, should `name` be kept?
	 * @return	About `average` names (up to `max`, but always at least 1), \
	 * chosen from `choices`, except possibly `name` if it is in `choices` \
	 * and `include_self` is false.
	 */
	public static List<String> getChoices(
			String name,
			String provider,
			String[] choices,
			int max,
			int average,
			boolean include_self
			) {
		List<String> friends = new ArrayList<String>();
		int hash = name.hashCode();
		// (do .. while) to be kind, so everyone has at least 1 friend.
		do {
			String candidate = pickOneOf(choices, hash);
			
			if (friends.contains(candidate)
					|| ((!include_self) && name.equals(candidate))) {
				// Oops, already have that friend (or it's me)
				// Keep the hash changing
				hash += 1;
			} else {
				friends.add(provider + "/" + candidate);
				// Seed the next round
				hash = friends.hashCode();				
			}
		} while (friends.size() < max && hash % average != 0);
		return friends;
	}
	
	/**
	 * Debugging test function for Sample Data Generator
	 * 
	 * This should be removed when Bridges is released.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getFriends("Dylan", 5));
		System.out.println(getActors("The Shawshank Redemption", 20));
		System.out.println(getMovies("Isaac", 20));
	}
}
