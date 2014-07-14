package accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DbConn;

public class UserAccount {
	private int accountId;
	private String email;
	private String password;
	private int userId;
	private String firstName;
	private String lastName;
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
	
	public UserAccount(){
		
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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newUserAccount;
	}
}
