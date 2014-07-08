package accounts;

import java.util.Date;

public class Comment {
private int commentid;
private String title;
private String content;
private Date date;
private int postId;
private int userId;
public int getCommentid() {
	return commentid;
}
public void setCommentid(int commentid) {
	this.commentid = commentid;
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
public int getPostId() {
	return postId;
}
public void setPostId(int postId) {
	this.postId = postId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public Comment(int commentid, String title, String content, Date date,
		int postId, int userId) {
	super();
	this.commentid = commentid;
	this.title = title;
	this.content = content;
	this.date = date;
	this.postId = postId;
	this.userId = userId;
}

public Comment(){
	
}
}
