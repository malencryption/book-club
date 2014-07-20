package accounts;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javax.servlet.http.HttpSession;

import database.DbConn;

/**
 * Servlet implementation class groupPosts
 */
@WebServlet("/clubPosts")
public class ClubPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClubPosts() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int accountId = (Integer) session.getAttribute("accountId");
		
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		ArrayList<Post> clubPosts = getClubPosts(clubId);
		String clubName = Club.getNameByClubId(clubId);
		boolean clubStatus = ClubMember.checkClubStatus(clubId, accountId);
		request.setAttribute("clubName", clubName);
		request.setAttribute("clubPostList", clubPosts);
		request.setAttribute("clubStatus", clubStatus);
		
		request.getRequestDispatcher("/clubPosts.jsp").forward(request, response);
	}

	private ArrayList<Post> getClubPosts(int clubId) {
		ArrayList<Post> clubPosts = new ArrayList<Post>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();
			
			String sql = "SELECT * FROM post p INNER JOIN club c ON p.clubId = c.clubId WHERE c.clubId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, clubId);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int postId = resultSet.getInt("postId");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				String date = resultSet.getString("date");
				int accountId = resultSet.getInt("accountId");
				int cId = resultSet.getInt("clubId");

				Post newPost = new Post();
				newPost.setPostId(postId);
				newPost.setTitle(title);
				newPost.setContent(content);
				newPost.setDate(date);
				newPost.setAccountId(accountId);
				newPost.setClubId(cId);

				clubPosts.add(newPost);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clubPosts;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
	public static ArrayList<Post> getClubPostsByAcct(int acctId) {
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
			
			String sql = "SELECT * FROM post p INNER JOIN club c ON p.clubId = c.clubId WHERE accountId = ? AND NOT name='home'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, acctId);
			ResultSet resultSet = stmt.executeQuery();

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
}
