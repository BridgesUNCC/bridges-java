package bridges.data_src_dependent;

import java.util.Date;


/**
 *	This class holds the content of a Tweet; it is used by the Earthquake
 *  dataset, which is received as tweets
 *
 * @author Sean Gallager, Mihai Mehedint, Kalpathi Subramanian
 *
 * @date 12/26/20
 */
public class Tweet extends DataSource {
	private Date date;

	/**
	 * Constructor
	 * @param content  content of tweet (string)
	 * @param date2  date information
	 */
	public Tweet(String content, Date date2) {
		super.setLabel(content);
		this.date = date2;
	}

	/**
	 * Constructor
	 * @param content  content of tweet (string)
	 */
	public Tweet(String content) {
		super.setLabel(content);
		this.date = new Date();
	}

	/**
	 * Constructor
	 * @param aTweetCopy  copy a tweet
	 */
	public Tweet(Tweet aTweetToCopy) {
		super.setLabel(aTweetToCopy.getContent());
		this.date = aTweetToCopy.getDate();
	}

	/**
	 * Get the content of the tweet
	 * @return content of the tweet  
	 */
	public String getContent() {
		return super.getLabel();
	}

	/**
	 * Set the content of the tweet
	 * @param content content of tweet  to be set
	 */
	public void setContent(String content) {
		super.setLabel(content);
	}

	/**
	 * Set the label of the tweet
	 * @param label label of the tweet  to be set
	 */
	@Override
	public void setLabel(String label) {
		setContent(label);
	}


	/**
	 * Get the label of the tweet
	 * @return label of the tweet  
	 */
	@Override
	public String getLabel() {
		return getContent();
	}

	/**
	 * Get the date of the tweet
	 * @return date of the tweet  
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set data of tweet
	 * @param date  (of type Date) to be set
	 */
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
		}
		else if (!this.getContent().equals(other.getContent()))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		}
		else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tweet [content=" + this.getContent() + ", date=" + date + "]";
	}


}
