package edu.uncc.cs.bridgesV2.data_src_dependent;

public class Actor extends DataSource implements Source{
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
	 * This method sets the string name
	 */
	public void setName(String name){
		this.anActor = name;
	}
	
	@Override
	public void setLabel(String label){
		setName(label);
	}
	
	@Override
	public String getLabel(){
		return getName();
	}
	
	/**
	 * This method implements compareTo for the Actors
	 */
	@Override
	public int compareTo(DataSource anotherActor){
		if(anotherActor instanceof Actor)
			return this.anActor.compareTo(((Actor)anotherActor).getName());
		else{
			try {
				throw new ClassCastException("Expected an instance of Actor for the compareTo method.");
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
			return -1;
		}
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
