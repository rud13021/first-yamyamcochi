package Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.Food;
import DTO.MealItem;
import DTO.MealRecord;
import Repository.FoodRepository;

public class FoodManager {
	
	Scanner sc = new Scanner(System.in);

	private FoodRepository repo = new FoodRepository();
	
	
	public void loadFoodsFromCsv(String path) {
		repo.loadFoodsFromCsv(path);
	}
	
	public int getMealRecordCount() {
	    return repo.getMealRecordCount();
	}
	//////
	
	/// 식단 작성
	// 구현 순서
	// Food(DTO) 검색 결과 -> 번호 선택 -> MealItem 생성 -> MealRecord에 추가
	
	// 1. 음식명 검색 결과 최대 5개까지 리스트로 만들어서 반환하는 메소드
	// Repository로 이동
	
	// 2. 음식명 검색 결과 리스트 출력 및 번호 선택 메서드
	public Food selectFood(String keyword) {
		List<Food> result = repo.searchFoods(keyword);
		// keyword 검색 결과가 없는 경우
		if(result.isEmpty()) {
			System.out.println("검색 결과가 없습니다.");
			return null;
		}
		// result에서 Food 정보를 받아 보기좋게 출력하기 
		for (int i = 0; i < result.size(); i++) {
		    Food food = result.get(i);
		    System.out.println((i + 1) + ". " 
		        + food.getFoodName() + " / "
		        + food.getServingSize() + " / "
		        + food.getCalorie() + "kcal");
		}
		
		System.out.print("번호 선택 : ");
		int choice = sc.nextInt();
		// 번호 입력 잘 받을 때까지 입력하게끔 해주기.
		while(choice < 1 || choice > result.size()) {
			sc.nextLine(); // 한 줄 넘김 + 버퍼 비우기
			System.out.print("잘못 입력했습니다. 다시 입력해주세요");
			choice = sc.nextInt();
		}
		Food selected = result.get(choice -1);
		return selected;
	}
	
	// 3. 식단 하나 만드는 메소드
	public MealItem createMealItem() {

		System.out.println("음식 검색어 : ");
		String keyword = sc.next();
		
		// selectFood 에서 번호 선택까지 가능
		Food selectedFood = selectFood(keyword);
		if(selectedFood ==  null) return null;
		
		double amountGram;

		while (true) {
		    System.out.print("섭취량(g): ");

		    if (!sc.hasNextDouble()) {
		        System.out.println("숫자로 입력해주세요.");
		        sc.next();
		        continue;
		    }

		    amountGram = sc.nextDouble();

		    if (amountGram <= 0) {
		        System.out.println("0보다 크게 입력해주세요.");
		        continue;
		    } //따로 while문을 쓰면 0미만 입력한 후 문자입력하면 터짐

		    break;
		}
		// 방금 입력받은 값으로 MealItem 객체를 새로 만들어서 돌려준다.
		// MealItem = new MealItem(selectedFood, amountGram);
		// return item; 으로 작성해도 됨.
		return new MealItem(selectedFood, amountGram);
	}
	
	// 4. 식단 기록(MealRecord) 생성 메서드
	public MealRecord createMealRecord(int mealId, int userId, String date, String mealType) {
		List<MealItem> items = new ArrayList<>();
		String yn;
		while(true) {
			MealItem item = createMealItem();
			if(item != null) {
				items.add(item);
			}
			while(true) {
				System.out.println("음식을 더 추가할까요? y/n");
				yn = sc.next();
				// y나 n을 입력하면 while문 하나 빠져나와서
				// y면 다음 items을 add하고 n이면 그만 add하게 구현
				if(yn.equals("y") || yn.equals("n")) {
					break;
				}
				System.out.println("y또는 n만 입력해주세요.");

			}
			
			if(yn.equals("n")) {
				break;
			}
		}

		return new MealRecord(mealId, userId, date, mealType, items);
	}
	
	// 5. 식단 기록 저장 메서드
	public void addMealRecord(MealRecord record) {
	    repo.addMealRecord(record);
	}
	// 실제 저장 로직은 Repository에 있다. 
	
	
	
	///식단 조회
	// 6. 식단 전체 목록 기본 조회(저장된 식단들이 뭐가 있는지 보는 화면)
	public void showAllMealRecords() {
		System.out.println("[전체 식단 목록]");
		
		if(repo.getAllMealRecords().isEmpty()) {
			System.out.println("저장된 식단 기록이 없습니다.");
			return;
		}
		
		for (MealRecord record : repo.getAllMealRecords()) {
			System.out.println(record.getMealId() + "번 | "
							  +record.getDate() + " | " 
							  +record.getMealType() + " | 음식 " 
							  +record.getItems().size()+ "개"
							  );
		}
					
	}
	
	// 7. 식단묶음 찾기 메서드
	// Repository로 이동
	
	// 8. 식단묶음 상세 조회 메서드
	// 없으면 안내 -> 있으면 상세 출력
	public void showMealRecordDetail(int mealId) {
		// records에서 mealId에 해당하는 record 불러옴
		MealRecord record = repo.findMealRecordById(mealId);
		
		if(record == null) {
			System.out.println("해당 식단 기록이 없습니다.");
			return;
		}
		
		System.out.println("[식단 상세]");
		System.out.println("번호: "+ record.getMealId(
				));
		System.out.println("날짜: "+ record.getDate() );
		System.out.println("끼니: "+ record.getMealType()); // 식단 유형
		
		for (MealItem item : record.getItems()) {
			Food food = item.getFood();
			System.out.println("- " + food.getFoodName() + " / " + item.getAmountGram() + "g");
		}
	}
	
	// 9. 식단묶음 수정 메서드
	public void updateMealRecord(int mealId) {
		// 수정을 할 식단묶음 번호를 이용해 갖고와 record에 저장
		MealRecord record = repo.findMealRecordById(mealId);
		// 식단 기록이 없을 때
		if (record == null) {
			System.out.println("해당 식단 기록이 없습니다.");
			return;
		}
		
		// 식단 기록이 있다면 ..?
		// 1. 있으면 새 날짜 입력
		// 2. 새 끼니 입력
		// 3. 기존 record에 date, mealType, items 덮어쓰기
		// 4. 수정 완료 출력
		
		// 1. 새 날짜 입력
		System.out.println("새 날짜 : ");
		String date = sc.next();
		
		// 2. 새 끼니 입력
		System.out.println("새 끼니 : ");
		String mealType = sc.next();
		
		// 3. 기존 record에 덮어쓰기
		// 덮어씌울 newRecord를 만든 후(만들 땐 기존의 데이터로 불러오기)
		MealRecord newRecord = createMealRecord(
				record.getMealId(),
				record.getUserId(),
				date,
				mealType
		);
		
		// 새로 덮어쓰기
		record.setDate(newRecord.getDate());
		record.setMealType(newRecord.getMealType());
		record.setItems(newRecord.getItems());
		// createMealRecord -> items 생성 -> 새 음식 추가 -> new MealRecord로 담아 반환
		
		System.out.println("식단 기록이 수정되었습니다.");
	}
	
	// 10. 식단 삭제 메서드
	public void deleteMealRecord(int mealId) {
		
		if (repo.getAllMealRecords().isEmpty()) {
		    System.out.println("삭제할 식단 기록이 없습니다.");
		    return;
		}
		
		boolean removed = repo.deleteMealRecordById(mealId);
		
		if(removed) {
			System.out.println("식단 기록이 삭제되었습니다.");
		} else {
			System.out.println("해당 식단 기록이 없습니다.");
		}
		
		// 삭제 후 파일 저장은 File I/O 붙일 떄 saveMealRecords 메서드만들면 된다.
	}
	
	// 11. 식단 분석 메서드
	public void analyzeMealRecord(int mealId) {
		MealRecord record = repo.findMealRecordById(mealId);
		// findMealRecordById(mealId); 는 반환이 MealRecord야
		if(record == null) {
			System.out.println("해당 식단 기록이 없습니다.");
			return;
		}
		
		double totalCalorie = 0;
		double totalCarbohydrate = 0;
		double totalProtein = 0;
		double totalFat = 0;
		double totalSugar = 0;
		double totalSodium = 0;
		
		for(MealItem item : record.getItems()) {
			Food food = item.getFood();
			double ratio = item.getAmountGram() / 100.0; 
			//기준에서 얼마나 곱해야하는지, 전부 100g, 100ml라서 일단 100으로 나눔
				
			totalCalorie += food.getCalorie() * ratio;
			totalCarbohydrate += food.getCarbohydrate() * ratio;
			totalProtein += food.getProtein() * ratio;
			totalFat += food.getFat() * ratio;
			totalSugar += food.getSugar() * ratio;
			totalSodium += food.getSodium() * ratio;
		}
		
		System.out.println("[식단 분석]");
		System.out.println("식단 ID: " + record.getMealId());
		System.out.println("날짜: " + record.getDate());
		System.out.println("끼니: " + record.getMealType());

		// 소수점 한자리까지 출력
		System.out.printf("총 칼로리: %.1f kcal%n", totalCalorie);
		System.out.printf("탄수화물: %.1f g%n", totalCarbohydrate);
		System.out.printf("단백질: %.1f g%n", totalProtein);
		System.out.printf("지방: %.1f g%n", totalFat);
		System.out.printf("당류: %.1f g%n", totalSugar);
		System.out.printf("나트륨: %.1f mg%n", totalSodium);
	}
	
	public void saveMealRecordsToJson(String path) {
	    repo.saveMealRecordsToJson(path);
	}

	public void loadMealRecordsFromJson(String path) {
	    repo.loadMealRecordsFromJson(path);
	}
	
}
