package edu.uncc.cs.bridges;

public class Actors {
	protected String anActor;
	
	/**
	 * The constructor
	 */
	public Actors(String anActor){
		this.anActor=anActor;
	}
	
	/**
	 * This method returns the string name
	 */
	public String getName(){
		return anActor;
	}
	
	/**
	 * This method implements compareTo for the Actors
	 */
	public int compareTo(Actors anotherActor){
		return anActor.compareTo(anotherActor.getName());
	}
	
	
	/**
	 * This method returns the string value
	 */
	public String toString(){
		return anActor;
	}
	
}
