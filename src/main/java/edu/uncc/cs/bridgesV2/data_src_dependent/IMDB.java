package edu.uncc.cs.bridgesV2.data_src_dependent;


public class IMDB extends DataSource implements Source{
	protected String imdb;
	@Override
	public void setLabel(String label) {
		this.imdb = label;
	}

	@Override
	public String getLabel() {
		return this.imdb;
	}

	@Override
	public int compareTo(DataSource o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
