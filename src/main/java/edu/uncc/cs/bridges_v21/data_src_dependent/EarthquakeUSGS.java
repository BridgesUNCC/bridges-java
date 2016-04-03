package bridges.data_src_dependent;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	private float latit;
	private float longit;
	private String location;
	private String title;
	private String url;

	private String properties;
	
	
	public EarthquakeUSGS(String content, Date date2, double magnitude,
			float longit, float latit, String location, String title, String url, String properties) {
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


	private static Date getTime(String properties) {
		// TODO Auto-generated method stub
		//here we parse the properties string for the Time:value in milisec since epoch 1970
		
		return null;
	}
	public float getLatit() {
		return this.latit;
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
