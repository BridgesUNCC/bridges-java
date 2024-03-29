package bridges.data_src_dependent;

/**
 * @brief Represent Actor Movie relations extacted from Wikidata
 *
 * Note that the end user will not create an object of that type in regular circumstances. But rather, ActorMovieWikidata objects are returned by bridges::connect::DataSource::getWikidataActorMovie()
 *
 * @sa Exemple of how to access that type of data is provided at: https://bridgesuncc.github.io/tutorials/Data_WikiDataActor.html
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
	 * @brief get movie URI
	 * @return movie URI
	 */
	public String getMovieURI() {
		return movieURI;
	}

	/**
	 * @brief set movie URI
	 * @param movieURI movie's URI to be set
	 */
	public void setMovieURI(String movieURI) {
		this.movieURI = movieURI;
	}

	/**
	 * @brief get actor  URI
	 * @return actor  URI
	 */
	public String getActorURI() {
		return actorURI;
	}

	/**
	 * @brief set actor URI
	 * @param actorURI actor's URI to be set
	 */
	public void setActorURI(String actorURI) {
		this.actorURI = actorURI;
	}

	/**
	 * @brief get movie name
	 * @return movie name
	 */
	public String getMovieName() {
		return movieName;
	}

	/**
	 * @brief set movie name
	 * @param movieName movie name to be assigned
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	/**
	 * @brief get actor name
	 * @return actor name
	 */
	public String getActorName() {
		return actorName;
	}

	/**
	 * @brief set actor name
	 * @param actorName actor to be assigned
	 */
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
}
