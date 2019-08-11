package bridges.data_src_dependent;

public class ActorMovieWikidata extends DataSource {
    private String movieURI,
        actorURI,
        movieName,
        actorName;

    public ActorMovieWikidata(String movieURI, String actorURI, String movieName, String actorName) {
        this.movieURI = movieURI;
        this.actorURI = actorURI;
        this.movieName = movieName;
        this.actorName = actorName;
    }

    public String getMovieURI() {
        return movieURI;
    }

    public void setMovieURI(String movieURI) {
        this.movieURI = movieURI;
    }

    public String getActorURI() {
        return actorURI;
    }

    public void setActorURI(String actorURI) {
        this.actorURI = actorURI;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
}
