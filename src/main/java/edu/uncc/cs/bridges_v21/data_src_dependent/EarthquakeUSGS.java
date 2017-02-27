package bridges.data_src_dependent;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import bridges.connect.DataFormatter;

/**
 * 
 * @author mihai mehedint
 *
 */
public class EarthquakeUSGS extends Tweet{
	private double magnitude;
	private double latit;
	private double longit;
	private String location;
	private String title;
	private String url;
	private String time;

	private String properties;
	
	
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

	public EarthquakeUSGS(String content, Date date2, double magnitude,
			double longit, double latit, String location, String title, String url, String properties) {

		super("USGSeq magnitude "+ magnitude+" "+title, date2);
		this.magnitude = magnitude;
		this.latit = latit;
		this.longit = longit;
		this.location = location;
		this.title = this.enterCarriageReturn(title);
		this.url = url;
		this.properties = properties;	
	}
		
	
	public String getProperties() {
		return this.properties;
	}


	public void setProperties(String properties) {
		this.properties = properties;
	}


		
//	private static Date getTime(String properties) {
//		// TODO Auto-generated method stub
//		//here we parse the properties string for the Time:value in milisec since epoch 1970
		
//		return null;
//	}
	public double getLatit() {
		return this.latit;
	}
	public void setLatit(double latit) {
		this.latit = latit;
	}
	public double getLongit() {
		return longit;
	}
	public void setLongit(double longit) {
		this.longit = longit;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}
	public void setTime (String t) {
							// convert this to a sane format
		DateFormat df = new SimpleDateFormat("MMM dd yyyy  HH:mm:ss.SSS zzz");

		Date date = new Date(Long.parseLong(t));
		this.time = df.format(date);
	}
	public String getTime () {
		return this.time;
	}
	public EarthquakeUSGS(EarthquakeUSGS eq){
		super("USGSeq magnitude "+ eq.magnitude+" "+eq.getTitle(), eq.getDate());
		this.magnitude = eq.magnitude;
		this.latit = eq.latit;
		this.longit = eq.longit;
		this.location = eq.location;
		this.title = eq.getTitle();
		this.url = eq.url;
		this.properties=eq.getProperties();
		//this(eq.getContent(), eq.getDate());
		//this.setContent(enterCarriageReturn(aTweet.getContent()));
	}
	
	public void eqProperties (String prop){
		Scanner scan = new Scanner(prop);
		
	}
	
	
	public void setMagnitude(Double mag){
		/*Scanner scan = new Scanner(this.getContent());
		StringBuilder str =  new StringBuilder();
		str.append(scan.next());
		DataFormatter.trimComma(str);
		magnitude = Double.parseDouble(str.toString());*/
		this.magnitude = mag;
		
	}
	
	public String enterCarriageReturn(String str){
		return str = str.replace(" ", "\\n");
	}
	
	public double getMagnitude(){
		return this.magnitude;
	}
	/* (non-Javadoc)
	 * @see edu.uncc.cs.bridgesV2.data_src_dependent.DataSource#compareTo(edu.uncc.cs.bridgesV2.data_src_dependent.DataSource)
	 */
	@Override
	public int compareTo(DataSource o) {
		if (o!=null)
			return ((Double)this.getMagnitude()).compareTo(((Double)((EarthquakeUSGS)o).getMagnitude()));
		else{
			try {
				throw new ClassCastException("Expected an instance of DataSource or of its subclasses Actor, Movie, Tweet, etc. for the compareTo method.");
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
			return -1;
		}
	}
	
	
}
