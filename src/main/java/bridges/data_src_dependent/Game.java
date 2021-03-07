package bridges.data_src_dependent;
import java.util.Vector;
import java.lang.String;

/**
 * @brief  A Game object, used along with the Games data source.
 *
 * This is a convenience class provided for  users who wish to use this
 * data source as part of their application. It provides an API that makes
 * it easy to access the attributes of this data set.
 *
 * Each game has a title, platform on which it can be played, rating and
 *  a list of genres
 *
 * Refer to tutorial examples to using this data source in data structure
 * assignments.
 *
 *  Refer to tutorial examples to using this data source in data structure
 *  assignments.
 *
 * @author Kalpathi Subramanian
 * @date   2/1/17, 12/26/20
 *
 */

public class Game  {
	private	String title,		// game title
			platform;		// game platform type
	private	double rating;		// game rating
	Vector<String> genre;		// game type/category

	/**
	 * Constructor
	 */
	public	Game() {
		title = platform = "";
		rating = 0.0;
		genre = new Vector<String>();
	}

	/**
	 * Constructor
	 *
	 * @param title title of game
	 * @param platform platform on which it can be played (String)
	 * @param rating  rating of game (double)
	 * @param genre  genres of game (vector of strings)
	 */
	public Game(String title, String platform, double rating,
		Vector<String> genre) {
		this.title = title;
		this.platform = platform;
		this.rating = rating;
		// copy vector
		for (int k = 0; k < genre.size(); k++)
			this.genre.add(genre.get(k));
	}

	/**
	 * get title of game
	 * @return title of game (string)
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * set title of game
	 * @param title game title to be set (string)
	 */
	public 	void setTitle (String title) {
		this.title = title;
	}

	/**
	 * get platform type of game
	 * @return platform types (string)
	 */
	public 	String getPlatformType() {
		return platform;
	}
	/**
	 * set platform type  of game
	 * @param platform platform type to be set (string)
	 */
	public 	void setPlatformType(String platform) {
		this.platform = platform;
	}

	/**
	 * get rating of game
	 * @return rating (double)
	 */
	public 	double getRating() {
		return rating;
	}
	/**
	 * set rating of game
	 * @param rating rating to be set (double)
	 */
	public 	void setRating(double rating) {
		this.rating = rating;
	}
	/**
	 * get genres of game
	 * @return genres (vector of strings)
	 */
	public 	Vector<String> getGenre() {
		return genre;
	}
	/**
	 * set genres of game
	 * @param genre genres to be set (vector of strings)
	 */
	public 	void setGenre(Vector<String> genre) {
		// copy vector
		for (int k = 0; k < genre.size(); k++)
			this.genre.add(genre.get(k));
	}
};
