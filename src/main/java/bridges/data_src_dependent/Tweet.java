package bridges.data_src_dependent;

import java.util.Date;
/**
 * @author Sean Gallager
 * @second author mihai mehedint
 *
 */
public class Tweet extends DataSource{
	private Date date;
	
	public Tweet(String content, Date date2) {
		super.setLabel(content);
		this.date = date2;
	}

	public Tweet(String content) {
		super.setLabel(content);
		this.date = new Date();
	}
	
	public Tweet(Tweet aTweetToCopy){
		super.setLabel(aTweetToCopy.getContent());
		this.date = aTweetToCopy.getDate();
	}

	public String getContent() {
		return super.getLabel();
	}

	public void setContent(String content) {
		super.setLabel(content);
	}
	
	/* (non-Javadoc)
	 * @see edu.uncc.cs.bridgesV2.data_src_dependent.Source#setLabel(java.lang.String)
	 */
	@Override
	public void setLabel(String label){
		setContent(label);
	}
	

	/* (non-Javadoc)
	 * @see edu.uncc.cs.bridgesV2.data_src_dependent.Source#getLabel()
	 */
	@Override
	public String getLabel(){
		return getContent();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getContent() == null) ? 0 : this.getContent().hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see edu.uncc.cs.bridgesV2.data_src_dependent.Source#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (this.getContent() == null) {
			if (other.getContent() != null)
				return false;
		} else if (!this.getContent().equals(other.getContent()))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see edu.uncc.cs.bridgesV2.data_src_dependent.Source#toString()
	 */
	@Override
	public String toString() {
		return "Tweet [content=" + this.getContent() + ", date=" + date + "]";
	}


}
