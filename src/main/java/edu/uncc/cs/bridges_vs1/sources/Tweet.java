package edu.uncc.cs.bridges_vs1.sources;

import java.util.Date;
/**
 * @author Sean Gallager
 * @second author mihai mehedint
 *
 */
public class Tweet {

	private String content;
	private Date date;
	
	public Tweet(String content, Date date2) {
		this.content = content;
		this.date = date2;
	}

	public Tweet(String content) {
		this.content = content;
		this.date = new Date();
	}
	
	public Tweet(Tweet aTweetToCopy){
		this.content = aTweetToCopy.getContent();
		this.date = aTweetToCopy.getDate();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		result = prime * result + ((content == null) ? 0 : content.hashCode());
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
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tweet [content=" + content + ", date=" + date + "]";
	}

}
