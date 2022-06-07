package bridges.data_src_dependent;

import java.util.Vector;
import java.lang.String;

public class Reddit {

	private String title, author, subreddit, url, text;
	private int id, score, vote_ratio, comment_count, post_time;

    

	public Reddit() {
		id = 0;
		title = "";
		this.author = "";
		this.score = 0;
		this.vote_ratio = 0;
		this.comment_count = 0;
		this.subreddit = "";
		this.post_time = 0;
		this.url = "";
		this.text = "";
	}

    //this should be an other constructor
//    def __init__(self, id = 0, title = "", author= "", score = 0, vote_ratio = 0, comment_count = 0, subreddit = "", post_time = 0, url = "", text = ""):
    
    
    public int getID() {
        return id;
	}

    public void setID(int i) {
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

    public int getSscore() {
		return score;
	}

    public void setScore(int sc){
        score = sc;
    }
    public int  getVoteRatio() {
        return vote_ratio;
	}

    public void setVoteRatio(int vr) {
        vote_ratio = vr; 
	}

    public int  getCommentCount()  {
        return comment_count;
	}

    public void setCommentCount(int cnt) {
        comment_count = cnt;
	}

    public String  getSubReddit(){
        return subreddit;
    }

    public void setSubReddit(String sr){
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
