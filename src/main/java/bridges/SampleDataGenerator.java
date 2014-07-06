package bridges;

import java.util.ArrayList;
import java.util.List;

public class SampleDataGenerator {
	
	// Top baby names from 2013
	private static String[] names = {
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
	
	public static List<String> getFriends(String name, int max) {
		List<String> friends = new ArrayList<String>();
		int hash = name.hashCode();
		// Average about five friends
		// But to be kind, everyone has at least 1.
		do {
			// In Java, % can be negative.
			// Force it to be positive.
			// But don't use abs() since that skews the result.
			int h = hash % names.length;
			h = h < 0 ? h + names.length : h;
			
			if (friends.contains(names[h])) {
				// Oops, already have that one
				// Keep the hash changing
				hash += 1;
			} else {
				friends.add(names[h]);
				// Seed the next round
				hash = friends.hashCode();				
			}
			// Only add up to `max` friends.
			max -= 1;
		} while (max > 0 && hash % 5 != 0);
		return friends;
	}

}
