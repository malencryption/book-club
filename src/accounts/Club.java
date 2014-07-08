package accounts;

import java.util.Date;

public class Club {
	private int clubId;
	private Date dateCreated;
	private String name;
	private int bookId;

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
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

	public Club(int clubId, Date dateCreated, String name, int bookId) {
		super();
		this.clubId = clubId;
		this.dateCreated = dateCreated;
		this.name = name;
		this.bookId = bookId;
	}

	public Club() {

	}
}
