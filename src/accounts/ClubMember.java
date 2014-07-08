package accounts;

public class ClubMember {
	private int memberId;
	private int clubId;
	private int accountId;

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public ClubMember(int memberId, int clubId, int accountId) {
		super();
		this.memberId = memberId;
		this.clubId = clubId;
		this.accountId = accountId;
	}

	public ClubMember(){
		
	}
}
