package accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import database.DbConn;

public class Comment {
	private int commentId;
	private String content;
	private String date;
	private int postId;
	private int accountId;

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

	public Comment(int commentId, String content, String date, int postId,
			int accountId) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.date = date;
		this.postId = postId;
		this.accountId = accountId;
	}

	public Comment() {

	}

	public static ArrayList<Comment> getCommentsByPostId(int postId) {
		ArrayList<Comment> commentList = new ArrayList<Comment>();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();

			String sql = "SELECT * FROM comment c INNER JOIN post p ON p.postId = c.postId WHERE p.postId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postId);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int commentId = resultSet.getInt("commentId");
				String content = resultSet.getString("content");
				String date = resultSet.getString("date");
				// int accountId = resultSet.getInt("accountId");
				// int clubId = resultSet.getInt("clubId");

				Comment newComment = new Comment();
				newComment.setPostId(postId);
				newComment.setCommentId(commentId);
				newComment.setContent(content);
				newComment.setDate(date);
				// newPost.setAccountId(accountId);
				// newPost.setClubId(clubId);
				commentList.add(newComment);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentList;
	}

	public static void addComment(Comment comment) {
		Comment newComment = comment;

		
		String content = newComment.getContent();
		int postId = newComment.getPostId();
		int accountId = newComment.getAccountId();
		String date = newComment.getDate();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String currTime = sdf.format(date);
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();

			String sql = "INSERT INTO comment ( content, date, accountId, postId) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, content);
			stmt.setString(2, date);
			stmt.setInt(3, accountId);
			stmt.setInt(4, postId);
			
			stmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
