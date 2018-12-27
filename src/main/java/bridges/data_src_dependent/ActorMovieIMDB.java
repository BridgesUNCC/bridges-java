package bridges.data_src_dependent;

import java.util.Vector;

public class ActorMovieIMDB extends DataSource {

	/**
	 * The constructor
	 */
	private String actor, movie;
	private double rating;
	private Vector<String> genres;

	public ActorMovieIMDB () {
		super.setLabel("");
	}

	public ActorMovieIMDB(String act_movie) {
		super.setLabel(act_movie);
	}

	/**
	 * This method returns the string name
	 */
	public String getName() {
		return super.getLabel();
	}

	/**
	 * This method sets the string name
	 */
	public void setName(String name) {
		super.setLabel(name);
	}

	public String getActor() {
		return actor;
	}
	public void setActor (String a) {
		actor = a;
	}

	public String getMovie() {
		return movie;
	}
	public void setMovie (String m) {
		movie = m;
	}

	public double getMovieRating() {
		return rating;
	}

	public void setMovieRating(double r) {
		this.rating = r;
	}

	public Vector<String> getGenres() {
		return genres;
	}

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
