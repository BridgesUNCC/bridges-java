package bridges.data_src_dependent;


public class IMDB {
	protected String imdb;
	
	/**
	 * @param imdb
	 */
	public IMDB(String imdb) {
		super();
		this.imdb = imdb;
	}

	public void setLabel(String label) {
		this.imdb = label;
	}

	public String getLabel() {
		return this.imdb;
	}

}
