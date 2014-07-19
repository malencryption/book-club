package accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DbConn;

public class UserComment {
	private int commentId;
	private String content;
	private String date;
	private int postId;
	private int accountId;
	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public UserComment(int commentId, String content, String date, int postId,
			int accountId, String firstName, String lastName) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.date = date;
		this.postId = postId;
		this.accountId = accountId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserComment() {

	}

	public static ArrayList<UserComment> getUserCommentsByPostId(int postId) {
		ArrayList<UserComment> commentList = new ArrayList<UserComment>();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();

			String sql = "SELECT u.firstName, u.lastName, c.commentId, c.content, c.date, p.postId, a.accountId FROM comment c INNER JOIN post p ON p.postId = c.postId JOIN account a ON c.accountId = a.accountId JOIN user u ON u.userId = a.userId WHERE p.postId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postId);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int commentId = resultSet.getInt("commentId");
				String content = resultSet.getString("content");
				String date = resultSet.getString("date");
				int accountId = resultSet.getInt("accountId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");

				UserComment newComment = new UserComment();
				newComment.setPostId(postId);
				newComment.setCommentId(commentId);
				newComment.setContent(content);
				newComment.setDate(date);
				newComment.setAccountId(accountId);
				newComment.setFirstName(firstName);
				newComment.setLastName(lastName);
				commentList.add(newComment);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentList;
	}
}
