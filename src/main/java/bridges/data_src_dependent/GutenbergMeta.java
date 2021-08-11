package bridges.data_src_dependent;

/** @brief Stores the meta data of a book as stored by project Gutenberg.
 *
 * This object stores the meta data of a book such as its title,
 * author and genre. The meta data come from https://www.gutenberg.org/.
 *
 * Objects of this type are typically not constructed by the user but
 * returned by a call to our Gutenberg API such as
 * bridges::connect::DataSource::getGutenbergBookMetaData() or
 * bridges::connect::DataSource::getAGutenbergBookMetaData(int).
 *
 * The object does not contain the text of the book itself. Though it
 * can be obtained using bridges::connect::DataSource::getGutenbergBookText()
 *
 * A tutorial of how to use the Gutenberg data in BRIDGES is available: https://bridgesuncc.github.io/tutorials/Data_Gutenberg.html
 *
 **/
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

    /**
     * @brief GutenbergID of the book
     */
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    
    /**
     * @brief Title of the book
     */
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    /**
     * @brief language of the book. 
     *
     * Note that translations of a text shows in the translated
     * language and not in the original language.
     **/
    public String getLanguage(){
        return lang;
    }

    public void setLanguage(String lang){
        this.lang = lang;
    }

    /**
     * @brief date at which the book was added to project Gutenberg.
     *
     * This is not a publication date!
     **/
    public String getDate(){
        return date_added;
    }

    public void setDate(String date){
        this.date_added = date;
    }

    /**
     * @brief List of authors
     **/
    public String [] getAuthors(){
        return authors;
    }

    public void setAuthors(String [] authors){
        this.authors = authors;
    }

    /**
     * @brief List of genres
     * 
     * Note that the genres stored by project Gutenberg may more detailled than one could expect.
     **/
    public String [] getGenres(){
        return genres;
    }

    public void setGenres(String [] genres){
        this.genres = genres;
    }

    /**
     * @brief List of libary of congress classifications
     * 
     **/
    public String [] getLoc(){
        return loc_class;
    }

    public void setLoc(String [] loc){
        this.loc_class = loc;
    }

}
