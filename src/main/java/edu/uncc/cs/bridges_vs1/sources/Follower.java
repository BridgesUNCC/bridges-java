package edu.uncc.cs.bridges_vs1.sources;

public class Follower implements Comparable<Follower> {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aFollower == null) ? 0 : aFollower.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Follower other = (Follower) obj;
		if (aFollower == null) {
			if (other.aFollower != null)
				return false;
		} else if (!aFollower.equals(other.aFollower))
			return false;
		return true;
	}
	
}
