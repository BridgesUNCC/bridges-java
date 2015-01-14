package edu.uncc.cs.bridges_vs1.sources;

public class Movie {
	protected String aMovie;
	
	public Movie(String aMovie){
		this.aMovie=aMovie;
	}
	
	/**
	 * This method returns the string name
	 */
	public String getName(){
		return aMovie;
	}
	
	/**
	 * This method implements compareTo for the Movies
	 */
	public int compareTo(Movie anotherMovie){
		return aMovie.compareTo(anotherMovie.getName());
	}
	
	/**
	 * This method returns the string value
	 */
	public String toString(){
		return aMovie;
	}

	@Override
	public int hashCode() {
		return aMovie.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (aMovie == null) {
			if (other.aMovie != null)
				return false;
		} else if (!aMovie.equals(other.aMovie))
			return false;
		return true;
	}

}
