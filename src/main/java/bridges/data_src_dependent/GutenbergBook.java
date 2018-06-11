package bridges.data_src_dependent;
/**
 * @brief  A Gutenberg Book object, used along with the Gutenberg
 *	books data source (https://www.gutenberg.org/).
 *	
 *  This is a convenience class provided for  users who wish to use this
 *	data source as part of their application. It provides an API that makes
 *  it easy to access the attributes of this data set. It contains meta data
 *  of a 1000 books that are part of the Gutenberg book collection.
 *
 *  Refer to tutorial examples to using this data source in data structure
 *  assignments.
 *
 * @author Kalpathi Subramanian
 * @date   2/1/17
 *
 */
import java.util.Vector;
import java.lang.String;

import bridges.data_src_dependent.DataSource;

public class GutenbergBook  extends DataSource {
	private String authorName;
	private int authorBirth, 
		authorDeath;
	private String title;
	private Vector<String> languages, genre, subjects;
	private int numChars, numWords, numSentences, numDifficultWords;
	private String url;
	private int numDownLoads;

	public GutenbergBook () {
		authorName = title = url = "";
		authorBirth =  authorDeath =  numDownLoads =
		numChars = numWords = numSentences = numDifficultWords = 0;
		languages = new Vector<String>();
		genre = new Vector<String>();
		subjects = new Vector<String>();
	}
	public GutenbergBook(String authorName, int authorBirth, int authorDeath, 
				String title, Vector<String> languages, Vector<String> genre,
				Vector<String> subjects, int numChars, int numWords, 
				int numSentences, int numDifficultWords, String url, 
				int downloads) {

		this.authorName = authorName;
		this.authorBirth = authorBirth;  
		this.authorDeath = authorDeath;
		this.title =  title;
		this.languages = languages;
		this.genre = genre;
		this.subjects = subjects;
		this.url = url;
		this.numChars = numChars;
		this.numWords = numWords;
		this.numSentences = numSentences;
		this.numDifficultWords = numDifficultWords;
		this.numDownLoads = downloads;
	}

	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getAuthorBirth() {
		return this.authorBirth;
	}
	public void setAuthorBirth(int authorBirth) {
		this.authorBirth = authorBirth;
	}

	public int getAuthorDeath() {
		return this.authorDeath;
	}
	public void setAuthorDeath(int authorDeath) {
		this.authorDeath = authorDeath;
	}

	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Vector<String>  getLanguages() {
		return this.languages;
	}
	public void setLanguages(Vector<String> languages)  {
		for (int k = 0; k < languages.size(); k++)
			this.languages.add(languages.get(k));
	}

	public Vector<String>  getGenres() {
		return this.genre;
	}
	public void setGenres(Vector<String> genre)  {
		for (int k = 0; k < genre.size(); k++)
			this.genre.add(genre.get(k));
	}

	public Vector<String>  getSubjects() {
		return this.subjects;
	}
	public void setSubjects(Vector<String> subjects)  {
		for (int k = 0; k < subjects.size(); k++)
			this.subjects.add(subjects.get(k));
	}

	public String getURL() {
		return this.url;
	}
	public void setURL(String url) {
		this.url = url;
	}

	public int getNumChars() {
		return this.numChars;
	}
	public void setNumChars(int numChars) {
		this.numChars = numChars;
	}
	public int getNumWords() {
		return numWords;
	}
	public void setNumWords(int numWords) {
		this.numWords = numWords;
	}

	public int getNumSentences() {
		return this.numSentences;
	}
	public void setNumSentences(int numSentences) {
		this.numSentences = numSentences;
	}

	public int getNumDifficultWords() {
		return this.numDifficultWords;
	}
	public void setNumDifficultWords(int numDifficultWords) {
		this.numDifficultWords = numDifficultWords;
	}
	public int getNumDownloads() {
		return numDownLoads;
	}
	public void setNumDownloads(int dl) {
		this.numDownLoads = dl;
	}
};

 

