package bridges.data_src_dependent;

public class Twitter extends DataSource{
	
	/**
	 * Constructor
	 */
	public Twitter(String str) {
		super();
		super.setLabel(str);
	}

	@Override
	public void setLabel(String label) {
		super.setLabel(label);
	}

	@Override
	public String getLabel() {
		return super.getLabel();
	}

}
