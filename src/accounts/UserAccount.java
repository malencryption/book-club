package accounts;

public class UserAccount {
	private int accountId;
	private String email;
	private String password;
	private int userId;
	private String firstName;
	private String lastName;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public UserAccount(int accountId, String email, String password,
			int userId, String firstName, String lastName) {
		this.accountId = accountId;
		this.email = email;
		this.password = password;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UserAccount(){
		
	}
}
