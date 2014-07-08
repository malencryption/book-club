package accounts;

public class AuthorBook {
	private int id;
	private int bookId;
	private int authorId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public AuthorBook(int id, int bookId, int authorId) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.authorId = authorId;
	}

	public AuthorBook() {

	}
}
