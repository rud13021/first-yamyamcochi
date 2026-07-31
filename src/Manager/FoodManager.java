package Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.Food;
import DTO.MealItem;
import DTO.MealRecord;

public class FoodManager {
	
	Scanner sc = new Scanner(System.in);
	// 추후 테스트 데이터 or 엑셀 로딩 필요
	List<Food> foods = new ArrayList<>();
	
	// 식단기록을 담아둘 ArrayList 생성
	List<MealRecord> mealRecords = new ArrayList<>();
	///////////
	// 임시 더미데이터, 엑셀 연동 후 삭제
	public void loadTestFoods() {
		foods.add(new Food("F001", "국밥_돼지머리", "100g", 137, 6.7, 15.94, 5.16, 0.63, 181));
		foods.add(new Food("F002", "국밥_순대국밥", "100g", 75, 3.17, 10.38, 2.28, 0.48, 126));
		foods.add(new Food("F003", "국밥_콩나물", "100g", 52, 1.45, 10.93, 0.24, 0.56, 172));
		foods.add(new Food("F004", "김치찌개", "100g", 61, 3.2, 4.8, 3.1, 1.2, 420));
		foods.add(new Food("F005", "된장찌개", "100g", 78, 5.1, 6.3, 3.8, 1.0, 390));
		foods.add(new Food("F006", "계란후라이", "100g", 196, 13.5, 1.1, 15.3, 0.4, 152));
	}
	
	public void printFoods() {
		System.out.println(foods);
	}
	
	public int getMealRecordCount() {
		return mealRecords.size();
	}
	//////
	
	/// 식단 작성
	// 구현 순서
	// Food(DTO) 검색 결과 -> 번호 선택 -> MealItem 생성 -> MealRecord에 추가
	
	// 1. 음식명 검색 결과 최대 5개까지 리스트로 만들어서 반환하는 메소드
	public List<Food> searchFoods(String keyword) {
		List<Food> result = new ArrayList<>();
		for(Food food : foods) {
			// foodName은 private으로 접근 불가하기 때문에
			// getFoodName()으로 불러온다.
			if(food.getFoodName().contains(keyword)) {
				// food DTO의 foodName 요소 중 keyword가 포함되어 있다면.
				result.add(food);
			}
			// 음식명 결과가 5개가 되면 그만 보여줘라.
			if(result.size() == 5) break;
		}
		return result;
	}
	
	// 2. 음식명 검색 결과 리스트 출력 및 번호 선택 메서드
	public Food selectFood(String keyword) {
		List<Food> result = searchFoods(keyword);
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
		if(record != null) {
			mealRecords.add(record);
		}
	}
	
	///식단 조회
	// 6. 식단 전체 목록 기본 조회(저장된 식단들이 뭐가 있는지 보는 화면)
	public void showAllMealRecords() {
		System.out.println("[전체 식단 목록]");
		
		if(mealRecords.isEmpty()) {
			System.out.println("저장된 식단 기록이 없습니다.");
			return;
		}
		
		for (MealRecord record : mealRecords) {
			System.out.println(record.getMealId() + "번 | "
							  +record.getDate() + " | " 
							  +record.getMealType() + " | 음식 " 
							  +record.getItems().size()+ "개"
							  );
		}
		
		
		
		
	}
}
