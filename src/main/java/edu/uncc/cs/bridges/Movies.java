package edu.uncc.cs.bridges;

public class Movies {
	protected String aMovie;
	
	public Movies(String aMovie){
		this.aMovie=aMovie;
	}
	
	/**
	 * This method returns the string name
	 */
	public String getName(){
		return aMovie;
	}
	
	/**
	 * This method implements compareTo for the Movies
	 */
	public int compareTo(Movies anotherMovie){
		return aMovie.compareTo(anotherMovie.getName());
	}
	
	/**
	 * This method returns the string value
	 */
	public String toString(){
		return aMovie;
	}

}
