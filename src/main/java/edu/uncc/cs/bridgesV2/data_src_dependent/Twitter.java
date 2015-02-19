package edu.uncc.cs.bridgesV2.data_src_dependent;

public class Twitter extends DataSource implements Source{
	protected String account;
	@Override
	public void setLabel(String label) {
		this.account = label;
	}

	@Override
	public String getLabel() {
		return account;
	}

	@Override
	public int compareTo(DataSource anotherTwitter){
		if(anotherTwitter instanceof Twitter)
			return this.getLabel().compareTo(((Twitter)anotherTwitter).getLabel());
		else{
			try {
				throw new ClassCastException("Expected an instance of Twitter for the compareTo method.");
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
			return -1;
		}
	}

}
