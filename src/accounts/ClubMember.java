package accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DbConn;

public class ClubMember {
	private int memberId;
	private int clubId;
	private int accountId;

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public ClubMember(int memberId, int clubId, int accountId) {
		super();
		this.memberId = memberId;
		this.clubId = clubId;
		this.accountId = accountId;
	}

	public ClubMember(){
		
	}
	
	public static ArrayList<User> getClubMembers(int clubId){
		//query to get all clubmembers 
		ArrayList<User> newUserList = null;
		return newUserList;
	}
	
	public static void joinClub(int accountId, int clubId){

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();

			String sql = "INSERT INTO clubMember ( clubId, accountId) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, clubId);
			stmt.setInt(2, accountId);
			
			stmt.executeUpdate();
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
