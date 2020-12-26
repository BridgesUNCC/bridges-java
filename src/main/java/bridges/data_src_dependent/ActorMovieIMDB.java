package bridges.data_src_dependent;

import java.util.Vector;

/**
 * @brief This class represents an actor-movie pair object, to be used with the 
 * IMDB actor-movie dataset
 *
 * @author Kalpathi Subramanian
 */
public class ActorMovieIMDB extends DataSource {

	private String actor, movie;
	private double rating;
	private Vector<String> genres;

	/**
	 * Constructor
	 */
	public ActorMovieIMDB () {
		super.setLabel("");
	}

	/**
	 * Constructor
	 * @param act_movie label for dataset
	 */
	public ActorMovieIMDB () {
	}
	public ActorMovieIMDB(String act_movie) {
		super.setLabel(act_movie);
	}

	/**
	 * This method returns the dataset label
	 * @return the dataset label
	 */
	public String getName() {
		return super.getLabel();
	}

	/**
	 * This method sets the data set label
	 * @param name  label to be added to dataset
	 */
	public void setName(String name) {
		super.setLabel(name);
	}

	/**
	 * get the actor from this object
	 * @return actor (string) 
	 */
	public String getActor() {
		return actor;
	}
	/**
	 * set the actor from this object
	 * @param actor actor (string) to be set
	 */
	public void setActor (String a) {
		actor = a;
	}

	/**
	 * get the movie from this object
	 * @return movie (string) 
	 */
	public String getMovie() {
		return movie;
	}
	/**
	 * set the movie from this object
	 * @param m movie (string) to be set
	 */
	public void setMovie (String m) {
		movie = m;
	}

	/**
	 * get the movie rating from this object
	 * @return rating (double) 
	 */
	public double getMovieRating() {
		return rating;
	}

	/**
	 * set the movie rating for this object
	 * @param r rating (double) to be set
	 */
	public void setMovieRating(double r) {
		this.rating = r;
	}

	/**
	 * get the genres of this movie
	 * @return genres  (vector of strings)
	 */
	public Vector<String> getGenres() {
		return genres;
	}

	/**
	 * set the genres for this movie
	 * @param g genres to be set for the movie (vector of strings)
	 */
	public void setGenres(Vector<String> g) {
		this.genres = g;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		if (this.getName() == null) {
			if (other.getName() != null)
				return false;
		}
		else if (!this.getName().equals(other.getName()))
			return false;
		return true;
	}


	/**
	 * This method returns the string value
	 */
	public String toString() {
		return this.getName();
	}

}
