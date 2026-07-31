package Repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import DTO.Food;
import DTO.MealRecord;
import Util.JsonUtil;

public class FoodRepository {
	// 추후 테스트 데이터 or 엑셀 로딩 필요
	private List<Food> foods = new ArrayList<>();
	// 식단기록을 담아둘 ArrayList 생성
	private List<MealRecord> mealRecords = new ArrayList<>();
	
	public List<MealRecord> getAllMealRecords() {
		return mealRecords;
	}
	
	public boolean deleteMealRecordById(int mealId) {
	  for (int i = 0; i < mealRecords.size(); i++) {
	        MealRecord record = mealRecords.get(i);

	        if (record.getMealId() == mealId) {
	            mealRecords.remove(i);
	            return true;
	        }
	    }
	  	return false;
	}
	
	
	public int getMealRecordCount() {
		return mealRecords.size();
	}
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
	
	// 5. 식단 기록 저장 메서드
	public void addMealRecord(MealRecord record) {
		if(record != null) {
			mealRecords.add(record);
		}
	}
	
	// 7. 식단묶음 찾기 메서드
	public MealRecord findMealRecordById(int mealId) {
		for(MealRecord record : mealRecords) {
			if(record.getMealId() == mealId) {
				return record;
			}
		}
		return null;
	}
	
	
	// csv에서부터 Foods를 불러오는 메서드
	public void loadFoodsFromCsv(String path) {
	    foods.clear();

	    try (BufferedReader br = new BufferedReader(
	            new InputStreamReader(new FileInputStream(path), "UTF-8"))) {

	        String line = br.readLine(); // 첫줄 헤더 스킵

	        while ((line = br.readLine()) != null) {
	            String[] cols = line.split(",", -1);

	            String foodCode = cols[0]; //식품 코드
	            String foodName = cols[1]; // 식품명
	            String servingSize = cols[16]; // 1회 기준량

	            double calorie = parseDouble(cols[17]); // 칼로리
	            double protein = parseDouble(cols[19]); // 단백질
	            double carbohydrate = parseDouble(cols[20]); // 탄수화물
	            double fat = parseDouble(cols[21]); // 지방
	            double sugar = parseDouble(cols[22]); // 당류
	            double sodium = parseDouble(cols[31]); // 나트륨

	            foods.add(new Food(foodCode, foodName, servingSize,
	                    calorie, protein, carbohydrate, fat, sugar, sodium));
	        }

	    } catch (Exception e) {
	        System.out.println("음식 DB 로딩 실패: " + e.getMessage());
	    }
	}
	
	// csv 보조 메서드, csv 값이 비어 있거나 -면 ㅜㅅ자 0으로 바꾸고, 숫자 문자열이면 double로 변환
	private double parseDouble(String value) {
	    try {
	        if (value == null || value.trim().isEmpty() || value.equals("-")) return 0;
	        return Double.parseDouble(value.trim());
	    } catch (NumberFormatException e) {
	        return 0;
	    }
	}
	
	public void saveMealRecordsToJson(String path) {
	    JsonUtil.saveJson(path, mealRecords);
	}

	public void loadMealRecordsFromJson(String path) {
	    Type type = new TypeToken<List<MealRecord>>() {}.getType();
	    List<MealRecord> loaded = JsonUtil.loadJson(path, type);

	    if (loaded != null) {
	        mealRecords = loaded;
	    }
	}
}
