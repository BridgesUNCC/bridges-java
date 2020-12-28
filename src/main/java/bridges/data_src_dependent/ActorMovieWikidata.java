package bridges.data_src_dependent;

/** 
 * This class is used with actor movie data retrieved from Wikidata
 *
 * @author Erik Saule
 */
public class ActorMovieWikidata {
	private String movieURI,
			actorURI,
			movieName,
			actorName;

	/**
	 * Constructor
	 * @param movieURI 
	 * @param actorURI
	 * @param movieName name of movie (string)
	 * @param actorName name of actor (string)
	 */
	public ActorMovieWikidata(String movieURI, String actorURI, String movieName, String actorName) {
		this.movieURI = movieURI;
		this.actorURI = actorURI;
		this.movieName = movieName;
		this.actorName = actorName;
	}

	/**
	 * get movie URI
	 * @return movie URI
	 */
	public String getMovieURI() {
		return movieURI;
	}

	/**
	 * set movie URI
	 * @param movie URI to be set
	 */
	public void setMovieURI(String movieURI) {
		this.movieURI = movieURI;
	}

	/**
	 * get actor  URI
	 * @return actor  URI
	 */
	public String getActorURI() {
		return actorURI;
	}

	/**
	 * set actor URI
	 * @param actor URI to be set
	 */
	public void setActorURI(String actorURI) {
		this.actorURI = actorURI;
	}

	/**
	 * get movie name
	 * @return movie name
	 */
	public String getMovieName() {
		return movieName;
	}

	/**
	 * set movie name
	 * @param movie name to be assigned
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	/**
	 * get actor name
	 * @return actor name
	 */
	public String getActorName() {
		return actorName;
	}

	/**
	 * set actor name
	 * @param actor name to be assigned
	 */
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
}
