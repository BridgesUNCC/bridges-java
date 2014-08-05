package edu.uncc.cs.bridges;

import java.util.Date;

public class Tweet {

	private String content;
	private Date date;
	
	public Tweet(String content, Date date2) {
		this.content = content;
		this.date = date2;
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

}
