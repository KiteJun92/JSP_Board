package user;

public class User {

	private String userId;
	private String userPw;
	private String userName;
	private String userGender;
	private String userEmail;
	
	// 기본 생성자
	public User() {	}

	// getter / setter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	// toString()
	@Override
	public String toString() {
		return "User [userEmail=" + userEmail + ", userGender=" + userGender + ", userId=" + userId + ", userName="
				+ userName + ", userPw=" + userPw + "]";
	}
	
	
}
