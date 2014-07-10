package accounts;

public class Account {
	private int accountId;
	private String email;
	private String password;
	private int userId;

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

	public Account(int accountId, String email, String password, int userId) {
		super();
		this.accountId = accountId;
		this.email = email;
		this.password = password;
		this.userId = userId;
	}

	public Account() {
		
	}
}
