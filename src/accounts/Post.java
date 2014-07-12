package accounts;

import java.util.Date;

public class Post {
	private int postId;
	private String title;
	private String content;
	private Date date;
	private int accountId;
	private int clubId;
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	

	public Post(int postId, String title, String content, Date date, int accountId) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.date = date;
		this.accountId = accountId;
	}

	public Post() {

	}
}
