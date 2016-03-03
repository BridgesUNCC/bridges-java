package bridges.data_src_dependent;


import java.util.Date;
import java.util.Scanner;

import bridges.connect.DataFormatter;

/**
 * 
 * @author mihai mehedint
 *
 */
public class EarthquakeUSGS extends EarthquakeTweet{
	private double magnitude;
	private float latit;
	private float longit;
	private String location;
	private String title;
	private String url;
	private String properties;
	
	
	public EarthquakeUSGS(String content, Date date2, double magnitude,
			float latit, float longit, String location, String title, String url, String properties) {
		super(content, date2);
		this.magnitude = magnitude;
		this.latit = latit;
		this.longit = longit;
		this.location = location;
		this.title = title;
		this.url = url;
	}
		
	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public EarthquakeUSGS(String properties, Date time2) {
		super(properties, time2);
		setMagnitude();
		this.setContent(enterCarriageReturn(properties));
		
	}
	
	private static Date getTime(String properties) {
		// TODO Auto-generated method stub
		//here we parse the properties string for the Time:value in milisec since epoch 1970
		
		return null;
	}
	public float getLatit() {
		return latit;
	}
	public void setLatit(float latit) {
		this.latit = latit;
	}
	public float getLongit() {
		return longit;
	}
	public void setLongit(float longit) {
		this.longit = longit;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTitle() {
		return title;
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
	public EarthquakeUSGS(Tweet aTweet){
		this(aTweet.getContent(), aTweet.getDate());
		this.setContent(enterCarriageReturn(aTweet.getContent()));
	}
	
	public void eqProperties (String prop){
		Scanner scan = new Scanner(prop);
		
	}
	
	
	public void setMagnitude(){
		Scanner scan = new Scanner(this.getContent());
		StringBuilder str =  new StringBuilder();
		str.append(scan.next());
		DataFormatter.trimComma(str);
		magnitude = Double.parseDouble(str.toString());
		
	}
	
	public String enterCarriageReturn(String str){
		return str = str.replace(" ", "\\n");
	}
	
	public double getMagnitude(){
		return magnitude;
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
