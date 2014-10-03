package edu.uncc.cs.bridges;

import java.util.Date;
import java.util.Scanner;

public class EarthquakeTweet extends Tweet{
	private double magnitude;
	
	public EarthquakeTweet(String content, Date date2) {
		super(content, date2);
		setMagnitude(); 
	}
	public EarthquakeTweet(Tweet aTweet){
		this(aTweet.getContent(), aTweet.getDate());
	}
	
	public void setMagnitude(){
		Scanner scan = new Scanner(this.getContent());
		scan.next();
		scan.next();
		StringBuilder str =  new StringBuilder();
		str.append(scan.next());
		Bridge.trimComma(str);
		magnitude = Double.parseDouble(str.toString());
		
	}
	public double getMagnitude(){
		return magnitude;
	}
	
}
