package bridges.data_src_dependent;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import bridges.connect.DataFormatter;

/**
 * This class keeps earthquake tweet data, retrieved from US Geological Survey 
 *	Data
 *
 * @author Mihai Mehedint, Kalpathi Subramanian
 *
 * @date 12/26/20
 */
public class EarthquakeUSGS extends Tweet {

	// attributes of an earthquake
	private double magnitude;
	private double latit;
	private double longit;
	private String location;
	private String title;
	private String url;
	private String time;

	private String properties;


	/**
	 * Constructor
	 */
	public EarthquakeUSGS() {
		super ("", null);
		this.magnitude = 0.0;
		this.latit = 0.0;
		this.longit = 0.0;
		this.location = "";
		this.title = "";
		this.url = "";
		this.properties = "";
	}

	/**
	 * Constructor
	 *
	 * @param content  content of tweet (string)
	 * @param date2  when the quake occurred
	 * @param magnitude  magnitude of quake
	 * @param longit  longitude of quake
	 * @param latit  latitude of quake
	 * @param location where it occurred
	 * @param title  title, contains some of the content
	 * @param url   url of quake
	 * @param properties other properties
	 */
	public EarthquakeUSGS(String content, Date date2, double magnitude,
		double longit, double latit, String location, String title, String url, String properties) {

		super("USGSeq magnitude " + magnitude + " " + title, date2);
		this.magnitude = magnitude;
		this.latit = latit;
		this.longit = longit;
		this.location = location;
		this.title = this.enterCarriageReturn(title);
		this.url = url;
		this.properties = properties;
	}


	/**
	 * Get properties of quake 
	 *
	 * @return properties of quake (string)
	 */
	public String getProperties() {
		return this.properties;
	}


	/**
	 * set properties of quake 
	 *
	 * @param properties properties of quake (string) to be set
	 */
	public void setProperties(String properties) {
		this.properties = properties;
	}



	//	private static Date getTime(String properties) {
	//		// TODO Auto-generated method stub
	//		//here we parse the properties string for the Time:value in milisec since epoch 1970

	//		return null;
	//	}
	/**
	 * Get latitude of quake 
	 *
	 * @return latitude of quake (double)
	 */
	public double getLatit() {
		return this.latit;
	}
	/**
	 * Set latitude of quake 
	 *
	 * @param latit latitude  of quake to be set
	 */
	public void setLatit(double latit) {
		this.latit = latit;
	}
	/**
	 * Get longitude of quake 
	 *
	 * @return longitude of quake (double)
	 */
	public double getLongit() {
		return longit;
	}
	/**
	 * Set longitude of quake 
	 *
	 * @param longit longitude  of quake to be set
	 */
	public void setLongit(double longit) {
		this.longit = longit;
	}
	/**
	 * Get location of quake 
	 *
	 * @return location of quake (string)
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * Set location of quake 
	 *
	 * @param location location  of quake to be set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * Get title of quake  - has some of the quake info
	 *
	 * @return title of quake (string)
	 */
	public String getTitle() {
		return this.title;
	}
	/**
	 * Set title of quake 
	 *
	 * @param title title  of quake to be set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Get url of quake 
	 *
	 * @return url of quake (string)
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Set url of quake 
	 *
	 * @param url url  of quake to be set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * Set magnitude of quake 
	 *
	 * @param magnitude magnitude (double)  of quake to be set
	 */
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}

	/**
	 * Set time of quake in a user readable format
	 *
	 * @param t time (string)  of quake to be set
	 */
	public void setTime (String t) {
		// convert this to a sane format
		DateFormat df = new SimpleDateFormat("MMM dd yyyy  HH:mm:ss.SSS zzz");

		Date date = new Date(Long.parseLong(t));
		this.time = df.format(date);
	}
	/**
	 * Get time of quake 
	 *
	 * @return time of quake (string)
	 */
	public String getTime () {
		return this.time;
	}
	/**
	 * copy constructor 
	 * @param eq the earthquake data to be copied into this object
	 *
	 */
	public EarthquakeUSGS(EarthquakeUSGS eq) {
		super("USGSeq magnitude " + eq.magnitude + " " + eq.getTitle(), eq.getDate());
		this.magnitude = eq.magnitude;
		this.latit = eq.latit;
		this.longit = eq.longit;
		this.location = eq.location;
		this.title = eq.getTitle();
		this.url = eq.url;
		this.properties = eq.getProperties();
		//this(eq.getContent(), eq.getDate());
		//this.setContent(enterCarriageReturn(aTweet.getContent()));
	}

	public void eqProperties (String prop) {
		Scanner scan = new Scanner(prop);

	}

	/**
	 * Set magnitude of quake
	 *
	 * @param mag magnitude (double) of quake to be set
	 */
	public void setMagnitude(Double mag) {
		this.magnitude = mag;

	}

	/**
	 * function for internal use
	 */
	public String enterCarriageReturn(String str) {
		return str = str.replace(" ", "\\n");
	}

	/**
	 * get magnitude of quake
	 *
	 * @return magnitude (double) of quake
	 */
	public double getMagnitude() {
		return this.magnitude;
	}

	@Override
	public int compareTo(DataSource o) {
		if (o != null)
			return ((Double)this.getMagnitude()).compareTo(((Double)((EarthquakeUSGS)o).getMagnitude()));
		else {
			try {
				throw new ClassCastException("Expected an instance of DataSource or of its subclasses Actor, Movie, Tweet, etc. for the compareTo method.");
			}
			catch (ClassCastException e) {
				e.printStackTrace();
			}
			return -1;
		}
	}


}
