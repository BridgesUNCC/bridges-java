package bridges.data_src_dependent;

import java.lang.String;

/**
 * @brief  An object to represent a Reddit post, used along with the Reddit data source
 *
 * This is a convenience class provided for  users who wish to use this
 * data source as part of their application. It provides an API that makes
 * it easy to access the attributes of this data set.
 *
 * The Reddit object is typically not created by a student but rather
 * obtained from calling bridges::connect::DataSource::getRedditData().
 *
 * Refer to tutorial for example of using this feature: https://bridgesuncc.github.io/tutorials/
 *
 *
 * @author Erik Saule
 * @date   7/12/22
 *
 */
public class Reddit {

	private String id, title, author, subreddit, url, text;
	private int  score,  comment_count, post_time;
	private float vote_ratio;


	/**
	 * @brief Reddit default constructor
	 *
	 */
	public Reddit() {
		this.id = "";
		this.title = "";
		this.author = "";
		this.score = 0;
		this.vote_ratio = 0.0f;
		this.comment_count = 0;
		this.subreddit = "";
		this.post_time = 0;
		this.url = "";
		this.text = "";
	}

	/**
	*           @brief return id of the reddit post
	*
	*   @return id
	*/
	public String getID() {
		return id;
	}

	public void setID(String i) {
		id = i;
	}


	/**
	*           @brief return the title of the reddit post
	*
	*   @return title
	*/
	public String  getTitle() {
		return title;
	}

	public void  setTitle(String titl) {
		title = titl;
	}


	/**
	*           @brief return the author of the reddit post
	*
	*   @return author's username
	*/
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String auth) {
		author = auth;
	}



	/**
	*           @brief return the score (upvotes - downvotes) of the reddit post
	*
	*   @return score (upvotes - downvotes)
	*/
	public int getScore() {
		return score;
	}

	public void setScore(int sc) {
		score = sc;
	}


	/**
	 *   @brief ratio of upvotes to downvotes of the reddit post
	 *
	 *   @return vote ratio
	 */
	public float  getVoteRatio() {
		return vote_ratio;
	}

	public void setVoteRatio(float vr) {
		vote_ratio = vr;
	}

	/**
	 *   @brief number of comments of the reddit post
	 *
	 *   @return number of comments
	 */
	public int  getCommentCount()  {
		return comment_count;
	}

	public void setCommentCount(int cnt) {
		comment_count = cnt;
	}


	/**
	 *   @brief name of the subreddit the post appeared in
	 *
	 *   @return subreddit name
	 */
	public String  getSubreddit() {
		return subreddit;
	}

	public void setSubreddit(String sr) {
		subreddit = sr;
	}

	/**
	 *   @brief time the post was made (UNIX time)
	 *
	 *   @return unix time
	 */
	public int  getPostTime() {
		return post_time;
	}

	public void  setPostTime(int pt) {
		post_time = pt;
	}


	/**
	 *   @brief  URL associated with the post.
	 *
	 *   This could be the url of the reddit post itself or the URL of an associated article/video
	 *
	 *
	 *   @return URL
	 */
	public String  getURL() {
		return url;
	}

	public void  setURL(String u) {
		url = u;
	}


	/**
	 *   @brief   returns the text of the reddit post.
	 *
	 *   The text of the reddit post. Often in markdown format.
	 *
	 *  The text could be empty if the reddit post is just a link to a video or an article
	 *
	 *
	 *   @return full text of the reddit post
	 */
	public String  getText() {
		return text;
	}

	public void setText(String txt) {
		text = txt;
	}
}
