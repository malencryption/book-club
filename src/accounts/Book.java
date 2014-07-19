package accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DbConn;

public class Book {
	private int bookId;
	private String title;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Book(int bookId, String title, int genreId) {
		super();
		this.bookId = bookId;
		this.title = title;
	}

	public Book() {

	}

	public static ArrayList<Book> getBookList() {
		ArrayList<Book> bookList = new ArrayList<Book>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Properties prop = new Properties();
			// prop.load(getClass().getResourceAsStream("/DbAccess.properties"));
			//
			// String user = prop.getProperty("dbUser");
			// String pass = prop.getProperty("dbPassword");

			DbConn dbConn = new DbConn();
			Connection conn = dbConn.connect();
			
			String sql = "SELECT * FROM book ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String title = resultSet.getString("title");

				Book newBook = new Book();
				newBook.setBookId(bookId);
				newBook.setTitle(title);

				bookList.add(newBook);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}
}
