package bridges.data_src_dependent;

abstract class DataSource implements Source{
	protected String label;
	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	/* (non-Javadoc)
	 * @see edu.uncc.cs.bridgesV2.data_src_dependent.Source#compareTo(edu.uncc.cs.bridgesV2.data_src_dependent.Source)
	 */
	@Override
	public int compareTo(DataSource o) {
		if( o != null)
			return this.getLabel().compareTo(o.getLabel());
		else{
			try {
				throw new ClassCastException("Expected an instance of DataSource or of its subclasses Actor, Movie, Tweet, etc. for the compareTo method.");
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
			return -1;
		}
			
	}

}
