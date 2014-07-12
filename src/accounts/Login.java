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
			
			ArrayList<Post> homePostList = getHomePosts();
			UserAccount userAccount = getUserAccountByEmail(email);
			ArrayList<Post> groupList = getGroups(userAccount.getAccountId());
			
			request.setAttribute("groupList", groupList);
			request.setAttribute("user", userAccount);
			request.setAttribute("homePostList", homePostList);
			 request.getRequestDispatcher("/home.jsp").forward(request, response);
			
//			response.sendRedirect("home.jsp");
		} else {
			response.sendRedirect("/login.jsp");
		}
	}

	private ArrayList<Post> getGroups(int acctId) {
		ArrayList<Post> list = new ArrayList<Post>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			// Database credentials
			final String USER = "leesaruz_iClient";
			final String PASS = "Jd59wMUo";

			Connection conn = DriverManager.getConnection(DB_CONNECTION, USER, PASS);
			
			String sql = "SELECT * FROM post p INNER JOIN club c ON p.clubId = c.clubId WHERE accountId = ? AND NOT c.name='home'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, acctId);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int postId = resultSet.getInt("postId");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				Date date = resultSet.getDate("date");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private ArrayList<Post> getHomePosts() {
		ArrayList<Post> list = new ArrayList<Post>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			// Database credentials
			final String USER = "leesaruz_iClient";
			final String PASS = "Jd59wMUo";

			Connection conn = DriverManager.getConnection(DB_CONNECTION, USER,
					PASS);
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM post p INNER JOIN club c ON p.clubId = c.clubId WHERE c.name='home'";
			ResultSet resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				int postId = resultSet.getInt("postId");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				Date date = resultSet.getDate("date");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private boolean checkLogin(String email, String password) {
		UserAccount userAccount = getUserAccountByEmail(email);
		if (password.equals(userAccount.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	private final String DB_CONNECTION = "jdbc:mysql://localhost/book_club";

	public UserAccount getUserAccountByEmail(String email) {
		UserAccount newUserAccount = new UserAccount();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			// Database credentials
			final String USER = "leesaruz_iClient";
			final String PASS = "Jd59wMUo";

			Connection conn = DriverManager.getConnection(DB_CONNECTION, USER,
					PASS);
			String sql = "SELECT u.userId, u.firstName, u.lastName, a.accountId, a.email, a.password FROM user u INNER JOIN account a ON u.userId = a.accountId WHERE email=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int userId = resultSet.getInt("userId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				int accountId = resultSet.getInt("accountId");
				String password = resultSet.getString("password");

				newUserAccount.setUserId(userId);
				newUserAccount.setFirstName(firstName);
				newUserAccount.setLastName(lastName);
				newUserAccount.setAccountId(accountId);
				newUserAccount.setEmail(email);
				newUserAccount.setPassword(password);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newUserAccount;
	}
}
