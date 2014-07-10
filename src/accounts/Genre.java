package accounts;

public class Genre {
	private int genreId;
	private String name;

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Genre(int genreId, String name) {
		super();
		this.genreId = genreId;
		this.name = name;
	}

	public Genre() {

	}
}
