package accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import database.DbConn;

public class Post {
	private int postId;
	private String title;
	private String content;
	private String date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Post(int postId, String title, String content, String date, int accountId) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.date = date;
		this.accountId = accountId;
	}

	public Post() {

	}
	
	public static Post getPostById(int Id){
		Post newPost = new Post();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();
			
			String sql = "SELECT * FROM post p WHERE postId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Id);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int postId = resultSet.getInt("postId");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				String date = resultSet.getString("date");
				int accountId = resultSet.getInt("accountId");
				int clubId = resultSet.getInt("clubId");

				newPost.setPostId(postId);
				newPost.setTitle(title);
				newPost.setContent(content);
				newPost.setDate(date);
				newPost.setAccountId(accountId);
				newPost.setClubId(clubId);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return newPost;
	}
	
	public static void addPost(Post post) {
		Post newPost = post;
//		int postId = newPost.getPostId();
		String title = newPost.getTitle();
		String content = newPost.getContent();
		String date = newPost.getDate();
		int accountId = newPost.getAccountId();
		int clubId = newPost.getClubId();
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

			String sql = "INSERT INTO post ( title, content, date, accountId, clubId) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setString(3, date);
			stmt.setInt(4, accountId);
			stmt.setInt(5, clubId);
			
			stmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
