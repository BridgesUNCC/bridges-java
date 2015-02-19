package edu.uncc.cs.bridgesV2.data_src_dependent;


public class RottenTomatos extends DataSource implements Source{
	protected String rTom;
	@Override
	public void setLabel(String label) {
		this.rTom = label;
	}

	@Override
	public String getLabel() {
		return rTom;
	}

	@Override
	public int compareTo(DataSource rTom){
		if(rTom instanceof RottenTomatos)
			return this.rTom.compareTo(((RottenTomatos)rTom).getLabel());
		else{
			try {
				throw new ClassCastException("Expected an instance of RottenTomatos for the compareTo method.");
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
			return -1;
		}
	}
}
