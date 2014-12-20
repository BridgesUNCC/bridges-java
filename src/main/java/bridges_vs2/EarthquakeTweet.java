package bridges_vs2;

import java.util.Date;
import java.util.Scanner;
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
		this.setContent(enterCarriageReturn(content));
		
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
	
	public String enterCarriageReturn(String str){
		return str = str.replace(" ", " \\n\\r");
	}
	
	public double getMagnitude(){
		return magnitude;
	}
	
}
