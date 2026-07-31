package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import DTO.HealthProfile;
import DTO.User;
import Manager.UserManager;

public class MainMenu {
	public static void main(String[] args) {

		UserManager userManager = UserManager.getManager();
        Scanner sc = new Scanner(System.in);

        while(true) {
        	
        	User loginUser = userManager.getLoginUser();
        	int menu = 9999;
        	boolean result = false;
        	User resultUser = null;
        	ArrayList<User> arrayResultUser = new ArrayList<>();
        	
        	if (loginUser == null) { // 비로그인
        		System.out.println("╔════════════════════════════════════════════════╗");
        		System.out.println("║                🥗 YAMYAM COACH                 ║");
        		System.out.println("║              건강한 식단 관리 프로그램               ║");
        		System.out.println("╚════════════════════════════════════════════════╝");
        		System.out.println();
        		System.out.println(" [1] 회원가입");
        		System.out.println(" [2] 로그인");
        		System.out.println();
        		System.out.println(" [0] 종료");
        		System.out.println();
        		System.out.println("==================================================");
        		System.out.println("메뉴 선택 ▶ ");
                
                menu = sc.nextInt();
                
                switch(menu) {
                case 1:
                	System.out.print("아이디: ");
                	String userId = sc.next();
                	System.out.print("비밀번호: ");
                	String password = sc.next();
                	System.out.print("이름: ");
                	String name = sc.next();
                	User user = new User(userId, password, name);
                    result = userManager.addUser(user);
                    if (result) {
                    	System.out.println("회원가입 성공!");
                    } else {
                    	System.out.println("회원가입 실패 - 아이디 중복 or 회원 수 초과");
                    }
                    break;
                case 2:
                	System.out.print("아이디: ");
                	String loginId = sc.next();
                	System.out.print("비밀번호: ");
                	String loginPw = sc.next();
                    loginUser = userManager.login(loginId, loginPw);
                    if (loginUser != null) {
                    	System.out.println(loginUser.getName() + "님, 로그인되었습니다.");
                    } else {
                    	System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
                    }
                    break;
                case 0:
        			return;
                }
        	
        	} else if (loginUser.getUserId().equals("admin")) { // 관리자 로그인
        		System.out.println("╔════════════════════════════════════════════════╗");
        		System.out.println("║               🥗 얌얌코치 관리자 메뉴                ║");
        		System.out.println("║             회원 관리 서비스를 선택하세요              ║");
        		System.out.println("╚════════════════════════════════════════════════╝");
        		System.out.println();
        		System.out.println(" [1] 아이디로 회원 조회");
        		System.out.println(" [2] 회원번호로 회원 조회");
        		System.out.println(" [3] 이름으로 회원 조회");
        		System.out.println(" [4] 전체 회원 조회");
        		System.out.println(" [5] 회원 삭제");
        		System.out.println();
        		System.out.println(" [0] 로그아웃 / 종료");
        		System.out.println();
        		System.out.println("==================================================");
        		System.out.print("메뉴 선택 ▶ ");
                
                menu = sc.nextInt();
                
                switch(menu) {
                case 1:
                	// 회원 조회 - userId
                	System.out.print("조회할 회원의 아이디를 입력해 주세요:");
                	resultUser = userManager.searchByUserId(sc.next());
                	if (resultUser != null) {
                		System.out.println(resultUser);
                	} else {
                		System.out.println("회원 조회 실패. 다시 시도해 주세요.");
                	}
                	break;
                case 2:
                	// 회원 조회 - Id
                	System.out.print("조회할 회원의 고유번호를 입력해 주세요:");
                	resultUser = userManager.searchById(sc.nextInt());
                	if (resultUser != null) {
                		System.out.println(resultUser);
                	} else {
                		System.out.println("회원 조회 실패. 다시 시도해 주세요.");
                	}
                	break;
                case 3: 
                	// 회원 조회 - name
                	System.out.print("조회할 회원의 이름을 입력해 주세요:");
                	arrayResultUser = userManager.searchByName(sc.next());
                	if (arrayResultUser.size() != 0) {
                		System.out.println(arrayResultUser);
                	} else {
                		System.out.println("회원 조회 실패. 다시 시도해 주세요.");
                	}
                	break;
                case 4: 
                	// 회원 전체 조회
                	arrayResultUser = userManager.getAllUsers();
                	if (arrayResultUser.size() != 0) {
                		System.out.println(arrayResultUser);
                	} else {
                		System.out.println("회원이 존재하지 않습니다.");
                	}
                	break;
                case 5:
                	// 회원 삭제
                	System.out.print("삭제할 회원의 id를 입력해 주세요: ");
                	result = userManager.removeUser(sc.nextInt());
                	if (result) {
                		System.out.println("회원 삭제 완료!");
                	} else {
                		System.out.println("회원 삭제 실패. 다시 시도해 주세요.");
                	}
                	break;
                case 0:
                	return;
                }
        		
        	} else { // 일반 사용자 로그인
        		System.out.println("╔════════════════════════════════════════════════╗");
        		System.out.println("║                 🥗 얌얌코치 메뉴                   ║");
        		System.out.println("║             건강한 식단 관리를 시작하세요!             ║");
        		System.out.println("╚════════════════════════════════════════════════╝");
        		System.out.println();
        		System.out.println("==========내 정보 관리==========");
        		System.out.println(" [1] 내 정보 조회");
        		System.out.println(" [2] 내 프로필 설정");
        		System.out.println(" [3] 이름 수정");
        		System.out.println(" [4] 비밀번호 수정");
        		System.out.println(" [5] 건강 프로필 수정");
        		System.out.println("==========내 식단 관리==========");
        		System.out.println(" [6] 식단 관리");
        		System.out.println();
        		System.out.println(" [7] 로그아웃");
        		System.out.println(" [0] 종료");
        		System.out.println();
        		System.out.println("==================================================");
        		System.out.print("메뉴 선택 ▶ ");       		
        		
        		menu = sc.nextInt();
        		
        		switch(menu) {
        		case 1: 
        			// 내 정보 조회
               		System.out.println(loginUser);
                	break;
        		case 2:
        			// 내 프로필 설정
               		System.out.print("키: ");
                    double height = sc.nextDouble();
                    System.out.print("몸무게: ");
                    double weight = sc.nextDouble();
                    System.out.print("목표: ");
                    String goal = sc.next();
                    HealthProfile userProfile = new HealthProfile(height, weight, goal);
                    result = userManager.updateProfile(userManager.getLoginUser().getId(), userProfile);
                    if (result) {
                     	System.out.println("프로필 설정이 완료되었습니다.");
                    } else {
                       	System.out.println("프로필 설정을 실패하였습니다. 입력값을 제대로 입력해 주세요.");
                    }
                    break;
        		case 3: 
        			// 이름 수정
        			System.out.print("수정할 이름을 입력해 주세요: ");
        			String updateName = sc.next();
        			result = userManager.updateName(loginUser.getId(), updateName);
        			if (result) {
        				System.out.println(updateName + "으로 이름이 변경되었습니다.");
        			} else {
        				System.out.println("이름 변경에 실패했습니다. 다시 시도해 주세요.");
        			}
        			break;
        		case 4:
        			// 비밀번호 수정
        			System.out.print("수정할 비밀번호를 입력해 주세요: ");
        			String updatePw = sc.next();
        			result = userManager.updatePassword(loginUser.getId(), updatePw);
        			if (result) {
        				System.out.println("비밀번호가 변경되었습니다.");
        			} else {
        				System.out.println("비밀번호 변경에 실패했습니다. 다시 시도해 주세요.");
        			}
        			break;
        		case 5:
        			// 건강 프로필 수정
        			HealthProfile profile = loginUser.getProfile();
        			System.out.println("수정할 프로필을 입력해 주세요.");
        			System.out.print("키: ");
        			profile.setHeight(sc.nextDouble());
        			System.out.print("몸무게: ");
        			profile.setWeight(sc.nextDouble());
        			System.out.print("목표: ");
        			profile.setGoal(sc.next());
        			result = userManager.updateProfile(loginUser.getId(), profile);
        			if (result) {
        				System.out.println("프로필 수정 완료!");
        			} else {
        				System.out.println("프로필 수정 실패. 다시 시도해 주세요.");
        			}
        			break;
        		case 6:
        			// 식단 관리
        			
        			
        			
        			
        			
        			
        			
        		case 7:
        			// 로그아웃
                	result = userManager.logout();
                	if (result) {
                		System.out.println("로그아웃 성공!");
                	} else {
                		System.out.println("로그아웃 실패. 다시 시도해 주세요.");
                	}
        		case 0:
        			return;
                }
        		
        	}
    	
        }
		
	}
}
