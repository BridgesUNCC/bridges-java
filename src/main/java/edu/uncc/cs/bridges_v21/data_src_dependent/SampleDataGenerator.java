package bridges.data_src_dependent;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			"L??????on: The Professional",
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
			"WALL??????E",
			"North by Northwest",
			"Am??????lie",
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
		"G??????rard Depardieu",
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
		"J??????rgen Prochnow",
		"Alec Baldwin",
		"Joseph Fiennes",
		"Sebastian Koch",
		"F. Murray Abraham",
		"Javier Bardem",
		"John Wayne",
	};
	
	private static String[] available_tweets = {
		"My house is full of traps.",
		"There is a horse on aisle five.",
		"Beat jet lag by skipping a couple of meals before you travel: ",
		 "Peeling shallots is a pain, but here's the secret: Blanch them in water first:  ",
		 "Disable Android's full screen keyboard and see what you're typing with this Xposed module:  ",
		 "You don't need to be an artist to use basic design principles effectively in every day life: ",
		 "Here's how a debt settlement can affect your taxes: ",
		 "TorrenTV streams torrents to your Apple TV while they download:  ",
		 "Timeful (free for iOS) recommends how to schedule your calendar for to-dos and good habits:  ",
		 "So now that we have this new DIY blog: what kind of projects would you like to see? Let us know here: ",
		 "Build a DIY water misting rack to keep your backyard (and you) cool in the summer: ",
		 "Going grocery shopping after work ends today? Stop, you might spend more than you should: ",
		 "Not sure if you can use an image you found on the internet? This flow chart will tell you: ",
		 "Today's featured bag is packed tight with all kinds of fancy gadgets:  ",
		 "Dried beans are tasty and cheap, but you have to soak them before cooking. This cheat sheet tells you how long: ",
		 "Need to share hotel or tethered Wi-Fi? This tiny adapter makes your laptop a base station:  ",
		 "CyberGhost is one of our favorite VPNs, and now that have a client for the Mac:  ",
		 "How to effectively work and communicate with people who are smarter than you:  ",
		 "Speaking in public? Win over your audience from the get-go by opening with a puzzle:  ",
		 "Own your attention, it\'s all you really have.",
		 "Looking to get started with DIY electronics? This list demystifies all those crazy components:  ",
		 "Have a small space for a workshop? No worries, here's how to make the most of it: ",
		 "Ever wonder why Comcast has that obnoxious two-hour repair window they're always late for? Here you go: ",
		 "Need help creating the perfect presentation? Come talk with Nancy Duarte of @Duarte right now! ",
		 "Motivation for Chrome reminds you of your age--counting up while you waste time--to fight procrastination: ",
		 "Flying to Europe isn't cheap, but these airlines are the least expensive: ",
		 "Remember, before you set an out-of-office, warn the person you're directing people to? It's only fair: ",
		 "Slide Dial provides swipe access to your Android dialer from any app: ",
		 "Should you let that certification expire just because you don't use it? Let's take a look:  ",
		 "Introducing two new Lifehacker sub-blogs: @WayfarerLH (travel) and @WorkshopLH (DIY)! Read more here: ",
		 "This afternoon we'll be talking with an expert about how to create and deliver the perfect presentation. 12pm PT! ",
		 "You (probably) don\'t have one, perfect, 'soul mate' of a job: ",
		 "How do you determine the statute of limitations on your debts? And what does that mean, anyway? ",
		 "Some bank accounts offer financial incentives to help reach your savings goals: ",
		 "If you're having trouble investing in your future, you might need to give yourself some short-term consequences: ",
		 "Free app Poppy gives email popup notifications on Android with quick preview/reply/delete:  ",
		 "When you open the fridge, a light comes on &amp; goes off when you close it. For $5, you can do the same for your cooler: ",
		 "How firefighters sort patients can help you sort your email inbox faster: ",
		 "Just like you would 'practice yoga', 'practice workouts' for a long-lasting attitude to fitness: ",
		 "Use this flowchart to know what type of procrastinator you are (and what to do about it):  ",
		 "Remember the 'Jesus Pose' to connect with your audience when you give a speech:  ",
		 "PDF too big to email? Shrink PDFs from any browser with SmallPDF:  ",
		 "Check out the best questions and answers from this week's open thread at @HackerspaceBlog: ",
		 "Get on a flight attendant's good side by lending them a pen...says a flight attendant: ",
		 "When you proofread your work, change the font temporarily. It can help you notice mistakes: ",
		 "Don't agree to resign without negotiating key benefits: ",
		 "In the market for a thin, light, powerful laptop? One of these Ultrabooks will do:  ",
		 "If you're receiving an award on stage, accept it with your left hand. Here's why: ",
		 "Here were our top downloads this week: ",
		 "Make your own portable battery charger for smartphones and tablets: ",
		 "Here's a better way to put that lime in your beer: use a knife. ",
		 "Obligation + Vacation = Oblication. Don't be afraid to say no to these sneaky budget-killers. ",
		 "If your last email got ignored, don\'t 'bump' the thread--follow up with new information: ",
		 "Ride your bike barefoot with a sponge on each pedal:  ",
		 "Create an 'accountability chart' to track your productivity and stay on task: ",
		 "Pin up these food infographics in your kitchen or save them to your phone:  ",
		 "Not sure if you can use that photo on your blog? This chart explains everything about the Creative Commons license: ",
		 "Heading outdoors or camping this weekend? Be prepared:  ",
		 "Dungeons &amp; Dragons can make you a better storyteller:  ",
		 "Not sure what went wrong with your cake? Consult this chart:  ",
		 "A claw hammer and a pair of vice grips can get a broken nail out, no problem:  ",
		 "Just say no to new projects when you return from vacation (at least for a day or two): ",
		 "It's legal to unlock your cell phone again. Here's what you need to know: ",
		 "Build this amazing little MIDI controller using an Arduino: ",
		 "What historical figures do you want to see productivity tips from? ",
		 "Increase your chances of getting better customer service from Comcast (and maybe other companies): ",
		 "Meeting new people, eating well on a budget, and cleaning up our closets were just some of our top posts this week: ",
		 "Want to check into a hotel earlier than the 2pm-3pm set time? Try just telling them when you're arriving: ",
		 "Grab 30 Android apps, normally $100, for free from Amazon ASAP:  ",
		 "Don't check the time if you wake up at night--you'll get better rest, says this sleep expert:  ",
		 "XBMC rebrands and renames itself - it's now Kodi Entertainment Center:  ",
		 "30-day challenges are a great way to break out of your comfort zone. Here are six challenges to get you started: ",
		 "Switch between apps with a single tap using Last App Switcher for Android: ",
		 "Organize your under-sink storage with desktop in-trays:  ",
		 "Seven important lessons we could all learn from world religions:  ",
		 "Not all clutter is bad, if you can't afford to replace it after it's gone: ",
		 "A beginner's guide to using credit card rewards for travel: ",
		 "Gmail's canned messages are an even better way to choose from multiple signatures in your email: ",
		 "Don't include all your work in your portfolio. Just the stuff you're the most satisfied with: ",
		 "Build your own backyard DIY dunk bucket:  ",
		 "Are you ready for home ownership? Mortgage Hippo tests you to find out:  ",
		 "Bone conducting headphones are surprisingly easy to make. You learn a lot about speakers too:  ",
		 "It's clear skies on this desktop. Here's how to make your computer look like this:  ",
		 "Ten malware removal apps went through months of rigorous testing.  Who came out on top?  ",
		 "Decorate the ideal cake and cookies with an oral syringe filled with frosting:  ",
		 "If you don't like being asked what you do for a living, it might be time to change course:  ",
		 "Love public radio? NPR One is a great app that provides and endless stream of curated news stories: ",
		 "What to do when you feel too embarrassed to exercise: ",
		 "Use so many cloud services you can't keep track of your files? This Mac app helps: ",
		 "The order you present your information matters. Create an information hierarchy when sharing info: ",
		 "Have a company you'd love to work for? Be an advocate--you'll get noticed quickly: ",
		 "Worth a try: Find out if you can get free school supplies from these organizations:  ",
		 "This awesome, free book will teach you everything you need to know about JavaScript:  ",
		 "Running even just 5 minutes a day could help you live longer:  ",
		 "August starts tomorrow! Here are the best things to buy for deals in August: ",
		 "Easy Uninstaller clears space on your Android by batch removing apps and their leftover files:  ",
		 "One of our favorite to-do apps, @Wunderlist, just got a huge update! Here's what's new:  ",
		 "Think you can't customize the looks and functionality of a Mac? Think again: ",
		 "Create refill station for your next outdoor party you don't have to run to the house for plates, napkins, and forks: ",
		 "Confidence and arrogance sometimes look alike, but they're very different. Here's how to tell: ",
		 "Free Android app Unclouded sorts Google Drive or Dropbox by size, category or duplicates:  ",
		 "Popular music recognition app @Shazam is now available for free on Mac OS X:  ",
		 "We're on the hunt for the best Ultrabooks (thin, powerful laptops.) Which are the best?  ",
		 "A few trays and baskets are all you need to start living in a clutter-free, clean home: ",
		 "Looking for new music? Here are the best services you're probably not using (but should be):  ",
		 "The Met just uploaded 400,000 images from its collection. So happy Wallpaper Wednesday!  ",
		 "Pick the second cheapest glass on the menu and other tips for ordering wine:  ",
		 "Credit Karma already tracks and protects your finances. Now they're offering free weekly credit reports. Seriously: ",
		 "Amazon Prime subcriber? Choose no-rush shipping and earn $1 in instant video credit per order:  ",
		 "Egg shells in your bowl? Wet your fingers before reaching in to grab them:  ",
		 "Eight power networking tips to help you make more meaningful connections: ",
		 "If you're putting things away in storage, learn how to pack them away properly with this:  ",
		 "This DIY USB charger can be powered by just about anything from solar panels to car batteries:  ",
		 "BitTorrent just opened up invitations to the pre-alpha of its new anonymous, serverless chat client, Bleep: ",
		 "We spoke with the @PlexApp development team to learn the history behind the app: ",
		 "Back to our old format today with a speedy external drive, vacuum-packed wine, gadget chargers, and more #deals. ",
		 "Ever wanted to create your own Google Now voice command? Now you can. Here's how: ",
		 "This guide from the government tells you how to prepare to retire: ",
		 "Learn how to eat well on just $35 a week:  ",
		 "Sleeping away from home? Here's how to get a great night's sleep wherever you are:  ",
		 "Save time in useless meetings and writing emails--Teamreporter gets everyone up to date for you, automatically: ",
		 "We love Audacity, but if you need something lighter and easier, try Ocenaudio:  ",
		 "Looking to add style and storage to a small space? Try room dividers with storage like these:  ",
		 "Clean your closet by making quick decisions with this handy flowchart:  ",
		 "Blab quickly sends video messages; the other guy doesn't even need the app installed:  ",
		 "Gym clothes stink (yes, even after washing) so keep them separate by picking them in only two colors: ",
		 "Want to use Amazon's Subscribe and Save but don't know where to start? We're building a list of the best items: ",
		 "Sick of manually switching between OS X partitions? Here's an AppleScript to make it easier: ",
		 "No rolling pin? No problem! Use an empty wine or liquor bottle instead:  ",
		 "What video games or DLC give the best return on investment? This interactive chart shows you:  ",
		 "Dadhacker - Do you have enough will power? - ",
		 "Wondering what old accessories will work with that new Raspberry Pi model? Thankfully, most of them: ",
		 "Instagram will soon be launching their own ephemeral photo messaging app, named Bolt: ",
		 "Why creative side projects are good for you:  ",
		 "They say opposites attract, but they also annoy each other pretty quickly. Here's why and how to deal with it: ",
		 "This data might help find out someone's age from their first name (and, more importantly, shows it can be done): ",
		 "AppDowner makes it easy to install old versions of Android apps if you don't like the bloat that comes with new ones: ",
		 "Don't use Dropbox as your sole backup solution:  ",
		 "Want to learn Linux? This course is normally more than $2,000, but it's free now: ",
		 "OneNote is getting a few significant updates for Mac and iPhone/iPad: ",
		 "The best productivity tricks you can learn from rock stars:  ",
		 "Sunburnt and no aloe around? Crack open the fridge and use milk or yogurt to soothe the burn:  ",
		 "Jam-packed #deals post today, with Galaxy Tab Pro, UE Mini Boom, gaming gear, and more.  ",
		 ".@Asana for the iPhone and iPad got a major overhaul and looks great! Heres what's new:  ",
		 "Transform a crappy patio umbrella into a $300 designer lookalike on the cheap:  ",
		 "Hang out with a nine year old for a while and you learn a few things about life: ",
		 "No Wake On Charge stops your Samsung's screen lighting up when charging: ",
		 "Spotify just added a surprisingly exciting equalizer to its iOS app  ",
		 "You should rethink these outdated retirement rules of thumb: ",
		 "Just because you have a gas grill doesn't mean you can't get wood smoked flavor! @TestKitchen shows us how: ",
		 "Your giant American refrigerator is making you fat and poor ",
		 "Yes, you can talk pay with coworkers, and no, they can't force you to resign. These and other office falsehoods: ",
		 "Write emails with 'If...Then' statements and your back-and-forths will greatly reduce:  ",
		 "Get Wunderlist in a tiny window docked to your screen with Wunderlist Chrome Panel:  ",
		 "What's the weather like in Italy in June? Find out and share with Climatology for Android:  ",
		 "These are the generic foods and ingredients professional chefs choose to buy over brand names: ",
		 "Just because you have a gas grill doesn't mean you can't get wood smoked flavor! @TestKitchen shows us how: ",
		 "Our featured bag this week is actually three bags, packed with everything a photographer needs  ",
		 "Use the Army Ranger Roll technique to save space packing your sweatpants or pajama bottoms:  ",
		 "Basic home security systems might not be giving you as much protection as you think:  ",
		 "The Moto Stream is a pretty awesome way to add feature-heavy Bluetooth to your car: ",
		 "Avoiding alcohol can help keep those pesky mosquitoes at bay:  ",
		 "Sick of the OS X dock? This app stuffs all your dock icons into the menu bar: ",
		 "Practice self-compassion to improve how you feel about yourself:  ",
		 "Looking for some advice from a friend? Here's how to ask so you get useful info: ",
		 "Diligence isn\'t a personality type. It\'s a skill you learn.",
		 "Come ask @DickTalens of @Fitocracy about how you can stay fit even if you lead a busy life: ",
		 "Wondering how good your company's 401k is? This chart looks at the U.S.'s top 250 companies and compares: ",
		 "Floatify lets you create customizable pop-up notifications on Android: ",
		 "Brian Eno's Oblique Strategies cards help you overcome creative blocks:  ",
		 "Don't get tricked into double-tipping your waiter on vacation: ",
		 "Meeting new people? Here's how to make a good impression:  ",
		 "This afternoon we have a live Q&amp;A with @DickTalens on how workaholics can still stay in shape and exercise. 12pm PT! ",
		 "Crazy fines from your homeowner's association for a less-than-perfect lawn? Paint it and avoid the fees: ",
		 "Four creative ways to use your printer that aren't just plain old paper:  ",
		 "This DIY wine glass rack looks great, and is super-easy to make:  ",
		 "If you have ten minutes, you have time for these three decluttering techniques: ",
		 "Soaking up some sun? @adafruit's DIY UV sensor hat tells you when to reapply sunscreen:  ",
		 "Speak a website's name to switch to that tab in Chrome with free Tabs Board extension:  ",
		 "Skillets are better than saucepans for poaching eggs; and vinegar is the magic ingredient:  ",
		 "Avoid the 'rush hour' of business travellers and you'll save a lot of money on hotel bookings: ",
		 "Are glass screen protectors better than plastic ones?  ",
		 "Ever want to know what Uber drivers think of you? This script will tell you:  ",
		 "Try 'Tabless Thursdays' to encourage single tasking, just one day a week: ",
		 "If you're a current (or former) member of the military,h ere's a big list of places you can get a military discount: ",
		 "Check out the best from this week's open thread at @HackerspaceBlog: ",
		 "Some jobs are too good to be true. Know the warning signs of a job scam: ",
		 "f you can, use your audience to prove a point--not a slide in your presentation. ",
		 "This is a pretty interesting tool: Answer a few questions and find the city that matches your political beliefs best. ",
		 "Looking for faster, easier downloads? Try these five great Usenet providers:  ",
		 "If you want the perfect egg salad, you should use your hands: ",
		 "Lifehacker Packs, antivirus apps, Android security, and more: Here were this week's top downloads. ",
		 "Solidify your career path with the G+P+V formula: Gifts+Passions+Values. ",
		 "Running out of iCloud space? Change your backup settings on iOS:  ",
		 "Get a good deal on Craigslist by searching for key phrases, like 'leaving the country:' ",
		 "Take an appreciation break to boost your self-esteem: ",
		 "Trying to pitch an idea to someone? Lunch meetings are ideal: ",
		 "Ditch the sugar packets: Fix an uneven chair leg with a wine cork."
		 };
	
	/*
	 * This method generates random weights for edges
	 * @name Name of the node
	 * @max Maximum number of friends
	 * @return Returns the map of weights corresponding to the edges connected to a specific node
	 */
	public static Map<Follower, Double> getFriendsLikeness (String name, int max){
			List<Follower> aList=getFriends(name, max);
			Map<Follower, Double> aMap = new HashMap<>();
			for (Follower friend: aList){
				aMap.put(friend, Math.abs((friend+name).hashCode()%100.0));
			}
			return aMap;
	}
	/**
	 * Pick some friends, consistently.
	 * This is a function: identical calls generate identical results.
	 * @param name	Any name
	 * @param max	Maximum number of names to return 
	 * @return about 5 names, up to max, chosen from a list of common names
	 */
	public static List<Follower> getFriends(String name, int max) {
		List<String> names =  getChoices(name, available_friend_names, max, 5, false);
		List<Follower> followers  = new ArrayList<>();
		for (String n : names)
			followers.add(new Follower(n));
		return followers;
	}
	
	/**
	 * Pick some tweets (most of which are from Lifehacker, others from XKCD.
	 * @param name The name of the twitter user
	 * @param max  Maximum number of tweets
	 * @return about 20 tweets, up to max, chosen from a list
	 */
	public static List<Tweet> getTwitterTimeline(String name, int max) {
		List<String> tweet_texts =  getChoices(name, available_tweets, max, 20, false);
		List<Tweet> tweets  = new ArrayList<>();
		for (String t : tweet_texts)
			tweets.add(new Tweet(t, new Date(Math.abs(t.hashCode()))));
		return tweets;
	} 
	
	/**
	 * Pick some actors for a given movie.
	 * A list of actor names might be better.
	 * 
	 * @param movie		The name of the movie
	 * @param max		The maximum number of actors
	 * @return
	 */
	public static List<Actor> getCast(String movie, int max) {
		List<String> actors=getChoices(movie, available_actor_names, max, 7, true);
		List<Actor> theActors= new ArrayList<>();
		for (String str: actors)
			theActors.add(new Actor(str));
		return theActors;
	}
	
	/**
	 * Pick some movies an actor played in.
	 * @param name		The name of the actor
	 * @param max		The maximum number of movies
	 * @return
	 */
	public static List<Movie> getMovies(String name, int max) {
		List<String> movies=getChoices(name, available_movie_names, max, 15, true);
		List <Movie> theMovies = new ArrayList<>();
		for (String str: movies)
			theMovies.add(new Movie(str));
		return theMovies;
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
			String[] choices,
			int max,
			int average,
			boolean include_self
			) {
		List<String> friends = new ArrayList<>();
		int hash = name == null ? 17 : name.hashCode();
		// (do .. while) to be kind, so everyone has at least 1 friend.
		do {
			String candidate = pickOneOf(choices, hash);
			
			if (friends.contains(candidate)
					|| ((!include_self) && candidate.equals(name))) {
				// Oops, already have that friend (or it's me)
				// Keep the hash changing
				hash += 1;
			} else {
				friends.add(candidate);
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
		System.out.println(getCast("The Shawshank Redemption", 20));
		System.out.println(getMovies("Isaac", 20));
	}
	
}
