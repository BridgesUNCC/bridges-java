package bridges.data_src_dependent;

public class Movie extends DataSource{
	
	public Movie(String aMovie){
		super.setLabel(aMovie);
	}
	
	/**
	 * This method returns the string name
	 */
	public String getName(){
		return super.getLabel();
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
		return this.getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (this.getName()== null) {
			if (other.getName() != null)
				return false;
		} else if (!this.getName().equals(other.getName()))
			return false;
		return true;
	}

}
