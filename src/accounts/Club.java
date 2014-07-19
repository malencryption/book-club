package accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import database.DbConn;

public class Club {
	private int clubId;
	private String dateCreated;
	private String name;
	private int bookId;

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Club(int clubId, String dateCreated, String name, int bookId) {
		super();
		this.clubId = clubId;
		this.dateCreated = dateCreated;
		this.name = name;
		this.bookId = bookId;
	}

	public Club() {

	}
	
	static ArrayList<Club> getClubs(){
		ArrayList<Club> clubList = new ArrayList<Club>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();
			
			String sql = "SELECT * FROM club";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int clubId = resultSet.getInt("clubId");
				String date = resultSet.getString("dateCreated");
				String name = resultSet.getString("name");
				int bookId = resultSet.getInt("bookId");

				Club newClub = new Club();
				newClub.setClubId(clubId);
				newClub.setDateCreated(date);
				newClub.setName(name);
				newClub.setBookId(bookId);

				clubList.add(newClub);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clubList;
		
	}

	public static int addClub(Club club) {
		Club newClub = club;
		String date = newClub.getDateCreated();
		String name = newClub.getName();
		int bookId = newClub.getBookId();
		int lastId = 0;
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

			String sql = "INSERT INTO club ( name, dateCreated, bookId) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, name);
			stmt.setString(2, date);
			stmt.setInt(3, bookId);
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
            if(rs.next())
            {
                lastId = rs.getInt(1);
            }

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastId;
	}
}
