package bridges.data_src_dependent;
import java.util.Vector;
import java.lang.String;

import bridges.data_src_dependent.DataSource;
/**
 * @brief  A Game object, used along with the Games data source.
 *
 * This is a convenience class provided for  users who wish to use this
 * data source as part of their application. It provides an API that makes
 * it easy to access the attributes of this data set.
 *
 * Refer to tutorial examples to using this data source in data structure
 * assignments.
 *
 *  Refer to tutorial examples to using this data source in data structure
 *  assignments.
 *
 * @author Kalpathi Subramanian
 * @date   2/1/17
 *
 */

public class Game  extends DataSource {
	private	String title,		// game title
			platform;		// game platform type
	private	double rating;		// game rating
	Vector<String> genre;		// game type/category

	public	Game() {
		title = platform = "";
		rating = 0.0;
		genre = new Vector<String>();
	}

	public Game(String title, String platform, double rating,
		Vector<String> genre) {
		this.title = title;
		this.platform = platform;
		this.rating = rating;
		// copy vector
		for (int k = 0; k < genre.size(); k++)
			this.genre.add(genre.get(k));
	}

	public String getTitle() {
		return title;
	}
	public 	void setTitle (String title) {
		this.title = title;
	}

	public 	String getPlatformType() {
		return platform;
	}
	public 	void setPlatformType(String platform) {
		this.platform = platform;
	}

	public 	double getRating() {
		return rating;
	}
	public 	void setRating(double rating) {
		this.rating = rating;
	}
	public 	Vector<String> getGenre() {
		return genre;
	}
	public 	void setGenre(Vector<String> genre) {
		// copy vector
		for (int k = 0; k < genre.size(); k++)
			this.genre.add(genre.get(k));
	}
};
