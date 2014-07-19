package accounts;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DbConn;

/**
 * Servlet implementation class HomePosts
 */
@WebServlet("/HomePosts")
public class HomePosts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomePosts() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Post> homePostList = getHomePosts();
		int clubId = getHomeClubId();
		
		
		
		request.setAttribute("homePostList", homePostList);
		request.setAttribute("clubId", clubId);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	public static ArrayList<Post> getHomePosts() {
		ArrayList<Post> list = new ArrayList<Post>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM post p INNER JOIN club c ON p.clubId = c.clubId WHERE name='home'";
			ResultSet resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				int postId = resultSet.getInt("postId");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				String date = resultSet.getString("date");
				int accountId = resultSet.getInt("accountId");
				int clubId = resultSet.getInt("clubId");

				Post newPost = new Post();
				newPost.setPostId(postId);
				newPost.setTitle(title);
				newPost.setContent(content);
				newPost.setDate(date);
				newPost.setAccountId(accountId);
				newPost.setClubId(clubId);

				list.add(newPost);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static int getHomeClubId() {
		int clubId = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();
			Statement stmt = conn.createStatement();
			String sql = "SELECT clubId FROM club WHERE name='home'";
			ResultSet resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				clubId = resultSet.getInt("clubId");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clubId;
	}
}
