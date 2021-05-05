package bridges.data_src_dependent;

public class GutenbergMeta {
    
    private int id;
    private String title;
    private String lang;
    private String date_added;
    private String [] authors;
    private String [] genres;
    private String [] loc_class;

    
    public GutenbergMeta(){
        this.id = 0;
        this.title = null;
        this.lang = null;
        this.date_added = null;
        this.authors = null;
        this.genres = null;
        this.loc_class = null;
    }

    public GutenbergMeta(int id, String title, String lang, String date, String [] authors, String [] genres, String [] loc){
        this.setId(id);
        this.setTitle(title);
        this.setLanguage(lang);
        this.setDate(date);
        this.setAuthors(authors);
        this.setGenres(genres);
        this.setLoc(loc);
    }


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getLanguage(){
        return lang;
    }

    public void setLanguage(String lang){
        this.lang = lang;
    }

    public String getDate(){
        return date_added;
    }

    public void setDate(String date){
        this.date_added = date;
    }

    public String [] getAuthors(){
        return authors;
    }

    public void setAuthors(String [] authors){
        this.authors = authors;
    }

    public String [] getGenres(){
        return genres;
    }

    public void setGenres(String [] genres){
        this.genres = genres;
    }

    public String [] getLoc(){
        return loc_class;
    }

    public void setLoc(String [] loc){
        this.loc_class = loc;
    }

}
