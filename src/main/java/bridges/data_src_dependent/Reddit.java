package bridges.data_src_dependent;

import java.lang.String;

public class Reddit {

    private String id, title, author, subreddit, url, text;
    private int  score,  comment_count, post_time;
    private float vote_ratio;
    

	public Reddit() {
		id = "";
		title = "";
		this.author = "";
		this.score = 0;
		this.vote_ratio = 0.0f;
		this.comment_count = 0;
		this.subreddit = "";
		this.post_time = 0;
		this.url = "";
		this.text = "";
	}

    
    public String getID() {
        return id;
	}

    public void setID(String i) {
        id = i;
	}

	public String  getTitle(){
		return title; 
	}

	public void  setTitle(String titl) {
		title = titl;
	}

	public String getAuthor() {
		return author;
	}

    public void setAuthor(String auth) {
        author = auth;
	}

    public int getScore() {
		return score;
	}

    public void setScore(int sc){
        score = sc;
    }
    public float  getVoteRatio() {
        return vote_ratio;
	}

    public void setVoteRatio(float vr) {
        vote_ratio = vr; 
	}

    public int  getCommentCount()  {
        return comment_count;
	}

    public void setCommentCount(int cnt) {
        comment_count = cnt;
	}

    public String  getSubreddit(){
        return subreddit;
    }

    public void setSubreddit(String sr){
        subreddit = sr;
	}

    public int  getPostTime() {
        return post_time;
	}

    public void  setPostTime(int pt){
        post_time = pt;
	} 

    public String  getUrl() {
        return url;
	}

    public void  setUrl(String u) {
        url = u; 
	}

    public String  getText() {
        return text;
	}

    public void setText(String txt) {  
        text = txt; 
	}
}
