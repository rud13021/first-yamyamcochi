package Menu;

import DTO.MealRecord;
import Manager.FoodManager;

public class MainMenu {
	public static void main(String[] args) {
		FoodManager foodManager = new FoodManager();
		
		//// 임시 테스트용
		foodManager.loadTestFoods();
		foodManager.printFoods();
		// Food toString() 형태로 출력 작동.
		MealRecord record = foodManager.createMealRecord(1,1,"2025-01-01","아침");
		foodManager.addMealRecord(record);
		
		System.out.println(foodManager.getMealRecordCount()); // 출력 확인완료
		///
	}
}
