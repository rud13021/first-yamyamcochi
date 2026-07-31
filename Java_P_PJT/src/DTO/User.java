package DTO;

public class User {
<<<<<<< HEAD

	private static int cnt = 0; // User 객체 개수 카운터
	private int id; // 고유번호
	private String userId; // 아이디
	private String password; // 비밀번호
	private String name; // 이름
	private HealthProfile profile; // 건강 프로필
=======
	
	public User() {
	}
	
	public User(String userId, String password, String name) {
		this.id= ++cnt;
		this.userId = userId;
		this.password = password;		
		this.name = name;
	    this.profile = new HealthProfile();
	}

	//-------------------------------------------------------------------

	public static void setCnt(int maxId) {
	    cnt = maxId;
	}
	
	//-------------------------------------------------------------------

	public int getId() {
		return id;
	}
	
	public String getUserId() {
		return userId;
	}

	public boolean checkPassword(String inputPassword) {
		return this.password.equals(inputPassword);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HealthProfile getProfile() {
		return profile;
	}

	public void setProfile(HealthProfile profile) {
		this.profile = profile;
	}

	// toString
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", name=" + name + ", profile="
				+ profile + "]";
	}
}
