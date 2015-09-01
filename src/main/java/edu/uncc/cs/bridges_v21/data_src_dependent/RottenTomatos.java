package bridges.data_src_dependent;


public class RottenTomatos extends DataSource{
	
	/**
	 * Constructor
	 */
	public RottenTomatos(String src) {
		super();
		super.setLabel(src);
	}

	@Override
	public void setLabel(String label) {
		super.setLabel(label);
	}

	@Override
	public String getLabel() {
		return super.getLabel();
	}
	
	@Override
	public String toString(){
		return this.getLabel();
	}
}
