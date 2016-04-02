package bridges.data_src_dependent;


import java.util.Date;
import java.util.Scanner;

import bridges.connect.DataFormatter;

/**
 * 
 * @author mihai mehedint
 *
 */
public class EarthquakeTweet extends Tweet{
	private double magnitude;
	
	public EarthquakeTweet(String content, Date date2) {
		super(content, date2);
		setMagnitude();
		//this.setContent(enterCarriageReturn(content));
		
	}
	public EarthquakeTweet(Tweet aTweet){
		this(aTweet.getContent(), aTweet.getDate());
		this.setContent(enterCarriageReturn(aTweet.getContent()));
	}
	
	public void setMagnitude(){
		Scanner scan = new Scanner(this.getContent());
		scan.next();
		scan.next();
		StringBuilder str =  new StringBuilder();
		str.append(scan.next());
		DataFormatter.trimComma(str);
		magnitude = Double.parseDouble(str.toString());
		
	}
	
	public void setMagnitude(double mag){
		this.magnitude = mag;
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
			return ((Double)this.getMagnitude()).compareTo(((Double)((EarthquakeTweet)o).getMagnitude()));
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
