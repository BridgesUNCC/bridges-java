package bridges_vs2.sources;

public class Actor {
	protected String anActor;
	

	/**
	 * The constructor
	 */
	public Actor(String anActor){
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
	public int compareTo(Actor anotherActor){
		return anActor.compareTo(anotherActor.getName());
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anActor == null) ? 0 : anActor.hashCode());
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
		Actor other = (Actor) obj;
		if (anActor == null) {
			if (other.anActor != null)
				return false;
		} else if (!anActor.equals(other.anActor))
			return false;
		return true;
	}
	
	
	/**
	 * This method returns the string value
	 */
	public String toString(){
		return anActor;
	}
	
}
