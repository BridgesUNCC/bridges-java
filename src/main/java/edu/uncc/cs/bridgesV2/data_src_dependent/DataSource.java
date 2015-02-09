package edu.uncc.cs.bridgesV2.data_src_dependent;

public abstract class DataSource implements Source{
	protected String label;
	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

}
