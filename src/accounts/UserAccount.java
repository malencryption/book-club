package accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DbConn;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Reading;
import facebook4j.User;

public class UserAccount {
	private int accountId;
	private String email;
	private String password;
	private int userId;
	private String firstName;
	private String lastName;
	private String fbId;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public UserAccount(int accountId, String email, String password,
			int userId, String firstName, String lastName) {
		this.accountId = accountId;
		this.email = email;
		this.password = password;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserAccount() {

	}

	public static UserAccount getUserAccountByEmail(String email) {
		UserAccount newUserAccount = new UserAccount();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();
			String sql = "SELECT u.userId, u.firstName, u.lastName, a.accountId, a.email, a.password FROM user u INNER JOIN account a ON u.userId = a.userId WHERE email=?";
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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newUserAccount;
	}

	public static UserAccount getUserAccountByFbId(String fbId) {
		UserAccount newUserAccount = new UserAccount();
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			conn = dbConn.connect();
			String sql = "SELECT u.userId, u.firstName, u.lastName, a.accountId, a.email, a.password, a.fbId FROM user u INNER JOIN account a ON u.userId = a.userId WHERE fbId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, fbId);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int userId = resultSet.getInt("userId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				int accountId = resultSet.getInt("accountId");
				String password = resultSet.getString("password");
				String email = resultSet.getString("email");

				newUserAccount.setUserId(userId);
				newUserAccount.setFirstName(firstName);
				newUserAccount.setLastName(lastName);
				newUserAccount.setAccountId(accountId);
				newUserAccount.setEmail(email);
				newUserAccount.setPassword(password);
				newUserAccount.setFbId(fbId);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newUserAccount;
	}

	public String getFbId() {
		return this.fbId;
	}

	public void setFbId(String fbId) {
		this.fbId = fbId;
	}

	public static UserAccount addUserAccountWithFb(Facebook facebook) {
		
		Connection conn = null;
		String fbId = null;
		User user = null;
		String email = null;
		String firstName = null;
		String lastName = null;
		try {
			fbId = facebook.getId();
			user = facebook.getUser(fbId);
			email = user.getEmail();
			firstName = user.getFirstName();
			lastName = user.getLastName();
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (FacebookException e1) {
			e1.printStackTrace();
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			conn = dbConn.connect();
			conn.setAutoCommit(false);

			String userSql = "INSERT INTO user (firstName, lastName) VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			int lastId = 0;
            if(rs.next())
            {
                lastId = rs.getInt(1);
            }

			String sql = "INSERT INTO account (email, userId, fbId) VALUES (?, ?, ?)";
			PreparedStatement acctStmt = conn.prepareStatement(sql);
			acctStmt.setString(1, email);
			acctStmt.setInt(2, lastId);
			acctStmt.setString(3, fbId);
			acctStmt.executeUpdate();

			conn.commit();
			conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		UserAccount newUserAccount = getUserAccountByFbId(fbId);
		return newUserAccount;
	}
}
