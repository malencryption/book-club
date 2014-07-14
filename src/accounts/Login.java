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
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DbConn;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");

		HttpSession session = request.getSession(true);
		boolean loginStatus = checkLogin(email, password);
		if (loginStatus) {
			// set session variable
			// session.setAttribute("user", user);
			
			ArrayList<Post> homePostList = HomePosts.getHomePosts();
			UserAccount userAccount = UserAccount.getUserAccountByEmail(email);
			ArrayList<Post> clubPostList = ClubPosts.getClubPostsByAcct(userAccount.getAccountId());
			
			request.setAttribute("clubPostList", clubPostList);
			request.setAttribute("user", userAccount);
			request.setAttribute("homePostList", homePostList);
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			
//			response.sendRedirect("home.jsp");
		} else {
			response.sendRedirect("/login.jsp");
		}
	}

//	private ArrayList<Post> getGroups(int acctId) {
//		ArrayList<Post> list = new ArrayList<Post>();
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//
//			// Properties prop = new Properties();
//			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
//			//
//			// String user = prop.getProperty("dbUser");
//			// String pass = prop.getProperty("dbPassword");
//
//			DbConn dbConn = new DbConn();
//			Connection conn = dbConn.connect();
//			
//			String sql = "SELECT * FROM post p INNER JOIN club c ON p.clubId = c.clubId WHERE accountId = ? AND NOT c.name='home'";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, acctId);
//			ResultSet resultSet = stmt.executeQuery();
//
//			while (resultSet.next()) {
//				int postId = resultSet.getInt("postId");
//				String title = resultSet.getString("title");
//				String content = resultSet.getString("content");
//				Date date = resultSet.getDate("date");
//				int accountId = resultSet.getInt("accountId");
//				int clubId = resultSet.getInt("clubId");
//
//				Post newPost = new Post();
//				newPost.setPostId(postId);
//				newPost.setTitle(title);
//				newPost.setContent(content);
//				newPost.setDate(date);
//				newPost.setAccountId(accountId);
//				newPost.setClubId(clubId);
//
//				list.add(newPost);
//			}
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	private boolean checkLogin(String email, String password) {
		UserAccount userAccount = UserAccount.getUserAccountByEmail(email);
		if (password.equals(userAccount.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

}
