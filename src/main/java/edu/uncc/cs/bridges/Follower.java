package edu.uncc.cs.bridges;

public class Follower {
	protected String aFollower;
	
	public Follower(String aFollower){
		this.aFollower=aFollower;
	}
	
	/**
	 * This method returns the string name
	 */
	public String getName(){
		return aFollower;
	}
	
	/**
	 * This method implements compareTo for the Follower
	 */
	public int compareTo(Follower anotherFollower){
		return aFollower.compareTo(anotherFollower.getName());
	}
	
	
	/**
	 * This method returns the string value
	 */
	public String toString(){
		return aFollower;
	}
	
	
	
}
