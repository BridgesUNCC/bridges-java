package bridges.data_src_dependent;
/**
 * @brief  A Lyrics object, used along with the Lyrics data source.
 *
 * This is a convenience class provided for  users who wish to use this
 * data source as part of their application. It provides an API that makes
 * it easy to access the attributes of this data set.
 *
 * Refer to tutorial examples to using this data source in data structure
 * assignments.
 *
 *
 * @author David Burlinson
 * @date   5/21/18
 *
 */
import java.util.Vector;
import java.lang.String;

import bridges.data_src_dependent.DataSource;

public class Song extends DataSource {
	private	String artist,
		song,	// song title
		album,	// album title
		lyrics;	// full lyrics
	private	int year;		// year published
	Vector<String> genre;		// song genres

	public	Song() {
		artist = song = album = lyrics = "";
		year = 0;
		genre = new Vector<String>();
	}

	public Song(String artist, String song, String album, String lyrics, int rating,
						Vector<String> genre) {
		this.artist = artist;
		this.song = song;
		this.album = album;
		this.lyrics = lyrics;
		this.year = year;

		// copy vector
		for (int k = 0; k < genre.size(); k++)
			this.genre.add(genre.get(k));
	}

	public String getArtist() {
		return artist;
	}
	public 	void setArtist (String artist) {
		this.artist = artist;
	}

	public 	String getSongTitle() {
		return song;
	}
	public 	void setSongTitle(String song) {
		this.song = song;
	}

	public 	String getAlbumTitle() {
		return album;
	}
	public 	void setAlbumTitle(String album) {
		this.album = album;
	}

	public 	String getLyrics() {
		return lyrics;
	}
	public 	void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
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
