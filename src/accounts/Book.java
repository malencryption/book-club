package accounts;

public class Book {
	private int bookId;
	private String title;
	private int genreId;

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

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public Book(int bookId, String title, int genreId) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.genreId = genreId;
	}

	public Book() {

	}
}
