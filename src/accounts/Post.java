package accounts;

import java.util.Date;

public class Post {
	private int postId;
	private String title;
	private String content;
	private Date date;
	private int userId;

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Post(int postId, String title, String content, Date date, int userId) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.date = date;
		this.userId = userId;
	}

	public Post() {

	}
}
