package database;

public class Post {
	String username;
	String content;
	String date;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Post(String date, String username, String content) {
		this.username = username;
		this.content = content;
		this.date = date;
	}

	public String toString() {
		String s = this.date + "| " + this.username + "| " + this.content + " \n";
		return s;
	}

}
