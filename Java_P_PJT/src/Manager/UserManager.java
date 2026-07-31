package Manager;

import java.util.ArrayList;

import DTO.HealthProfile;
import DTO.User;

public class UserManager {

	// User들 저장하는 userList
	private ArrayList<User> userList = new ArrayList<>();
	
	// manager 객체 생성 - 싱글톤
	private static UserManager manager = new UserManager();

	private UserManager() {
	}

	public static UserManager getManager() {
		return manager;
	}
	
    // 현재 로그인한 사용자
    private User loginUser;
	
	//------------------------------------------------
	
	// 1-1. searchByUserId : 아이디로 조회
	public User searchByUserId(String userId) {
		for (User user : userList) {
			if (userId.equals(user.getUserId()))
				return user;
		}
		return null;
	}
	
	// 1-2. searchById : 회원번호로 조회
	public User searchById(int id) {
		for (User user : userList) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}
	
	// 1-3. searchByName : 이름으로 조회
	public ArrayList<User> searchByName(String name) {
		ArrayList<User> result = new ArrayList<>();
		for (User user : userList) {
			if (user.getName().contains(name))
				result.add(user);
		}
		return result;
	}
	
	// 1-4. getAllUsers : 회원 전체 조회
	public ArrayList<User> getAllUsers() {
		return new ArrayList<>(userList);
	}
	

	// 2. addUser : 회원 추가
	public boolean addUser(User user) {
		// 아이디 중복 검사
		if (searchByUserId(user.getUserId()) != null)
			return false;
		
		// 최대 100명까지 가능
		if (userList.size() >= 100)
			return false;
		
		// 아이디 중복 X & 100명 이하면 User 추가
		userList.add(user);
		return true;
	}

	
	// 3-1. updateName : 이름 수정
	public boolean updateName(int id, String name) {
		if (!checkLogin(id))
			return false;
		loginUser.setName(name);
		return true;
	}
	
	// 3-2. updatePassword : 비밀번호 수정
	public boolean updatePassword(int id, String password) {
		if (!checkLogin(id))
			return false;
		loginUser.setPassword(password);
		return true;
	}
	
	// 3-3. updateProfile : 건강 프로필 수정
	public boolean updateProfile(int id, HealthProfile profile) {
		if (!checkLogin(id))
			return false;
		loginUser.setProfile(profile);
		return true;
	}
	
	
	// 4. removeUser : 회원 삭제
	public boolean removeUser(int id) {
		if (!checkLogin(id))
			return false;
		userList.remove(loginUser);
		loginUser = null;
		return true;
	}
	
	
	// 5-1. login : 로그인
	public User login(String userId, String password) {
		// 아이디 존재 확인
		User user = searchByUserId(userId);
		
		// 아이디 미존재 하면 로그인 실패
		if (user == null) 
			return null;
		
		// 비밀번호 확인
		if (user.checkPassword(password)) {
			loginUser = user; // 현재 로그인한 사용자에 넣기
			return user;
		}
		
		// 비밀번호 불일치
		return null;
	}
	
	// 5-2. getLoginUser : 현재 로그인한 User 보기
	public User getLoginUser() {
		return loginUser;
	}
	
	// 5-3. checkLogin : 로그인한 본인인지 확인
	public boolean checkLogin(int id) {
		if (loginUser == null || id != loginUser.getId())
			return false;
		return true;
	}
	
	// 5-4. logout : 로그아웃
	public boolean logout() {
		if (loginUser != null) {
			loginUser = null;
			return true;
		}
		return false;
	}
}
