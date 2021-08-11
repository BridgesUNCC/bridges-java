package bridges.data_src_dependent;

import java.util.Vector;
import java.lang.String;


/**
 * @brief  A Song object, used along with the Songs data source (using the
 *			Genius API.
 *
 * This is a convenience class provided for  users who wish to use this
 * data source as part of their application. It provides an API that makes
 * it easy to access the attributes of this data set. The Song object is typically obtained from calling bridges::connect::DataSource::getSongData() or bridges::connect::DataSource::getSong().
 *
 * Refer to tutorial for example of using this feature: https://bridgesuncc.github.io/tutorials/Data_Song_Lyrics.html
 *
 *
 * @author David Burlinson
 * @date   5/21/18
 *
 */
public class Song {
	private	String artist,
			song,	// song title
			album,	// album title
			lyrics,	// full lyrics
			release_date;

	/**
	 *
	 *	 Song constructors
	 *
	 */
	public	Song() {
		artist = song = album = lyrics = release_date = "";
	}

	/**
	 *	 Song constructor
	 *
	 * @param artist  artist of song
	 * @param song    song title
	 * @param album   album title
	 * @param lyrics  lyrics of song (string)
	 * @param release_date  date released
	 */
	public Song(String artist, String song, String album, String lyrics, String release_date) {
		this.artist = artist;
		this.song = song;
		this.album = album;
		this.lyrics = lyrics;
		this.release_date = release_date;
	}

	/**
	 *	 Get song artist
	 *   @return artist of song
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 *	 Set song artist
	 *   @param artist artist to set
	 */
	public 	void setArtist (String artist) {
		this.artist = artist;
	}

	/**
	 *	 Get song title
	 *   @return title of song (string)
	 */
	public 	String getSongTitle() {
		return song;
	}
	/**
	 *	 Set song title
	 *   @param song song title  to set
	 */
	public 	void setSongTitle(String song) {
		this.song = song;
	}

	/**
	 *	 Get album title containing the song
	 *   @return album title  (string)
	 */
	public 	String getAlbumTitle() {
		return album;
	}
	/**
	 *	 Set song album
	 *   @param album song album  to set
	 */
	public 	void setAlbumTitle(String album) {
		this.album = album;
	}

	/**
	 *	 Get lyrics of the song
	 *   @return lyrics of  song (string)
	 */
	public 	String getLyrics() {
		return lyrics;
	}
	/**
	 *	 Set song lyrics
	 *   @param lyrics of song to set
	 */
	public 	void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	/**
	 *	 Get release date  of the song
	 *   @return release date of  song (string)
	 */
	public String getReleaseDate() {
		return release_date;
	}
	/**
	 *	 Set release date of song
	 *   @param release_date date of release to set
	 */
	public void setReleaseDate(String release_date) {
		this.release_date = release_date;
	}
};
