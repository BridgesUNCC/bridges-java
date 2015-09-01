package bridges.data_src_dependent;

public class Follower extends DataSource{
	
	public Follower(String aFollower){
		this.setLabel(aFollower);
	}
	
	/**
	 * This method returns the string name
	 */
	public String getName(){
		return this.getLabel();
	}
	
	/**
	 * This method sets the string name
	 */
	public void setName(String name){
		this.setLabel(name);
	}
	
	
	/**
	 * This method returns the string value
	 */
	public String toString(){
		return this.getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.getName() == null) ? 0 : this.getName().hashCode());
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
		if (this.getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!this.getName().equals(other.getName()))
			return false;
		return true;
	}
	
}
