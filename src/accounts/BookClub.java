package accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DbConn;

public class BookClub {
	private int clubId;
	private String dateCreated;
	private String name;
	private int bookId;
	private String bookTitle;

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
	public String getBookTitle() {
		return this.bookTitle;
	}

	public void setBookTitle(String title) {
		this.bookTitle = title;
	}
	public static ArrayList<BookClub> getBookClubs(){
		ArrayList<BookClub> bookClubList = new ArrayList<BookClub>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();
			
			String sql = "SELECT c.clubId, c.dateCreated, c.name, c.bookId, b.title FROM club c INNER JOIN book b ON c.bookId = b.bookId";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int clubId = resultSet.getInt("clubId");
				String date = resultSet.getString("dateCreated");
				String name = resultSet.getString("name");
				int bookId = resultSet.getInt("bookId");
				String bookTitle = resultSet.getString("title");

				BookClub newBookClub = new BookClub();
				newBookClub.setClubId(clubId);
				newBookClub.setDateCreated(date);
				newBookClub.setName(name);
				newBookClub.setBookId(bookId);
				newBookClub.setBookTitle(bookTitle);

				bookClubList.add(newBookClub);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookClubList;
		
	}
	
}
