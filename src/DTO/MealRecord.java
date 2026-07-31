package DTO;

import java.util.List;

/**
 * 식단 기록 1개를 저장하고 조회할 때 쓰는 DTO
 * @mealId : 몇번째 식단기록인지
 * @userId : 몇번째 회원인지
 * @date : 식단날짜
 * @mealType : 아침,점심,저녁,간식
 * @items : 식단 구성(ex. 돈까스 100g)
 */
public class MealRecord {
	int mealId;
	int userId;
	String date;
	String mealType;
	List<MealItem> items;
	
	public MealRecord() {}

	public MealRecord(int mealId, int userId, String date, String mealType, List<MealItem> items) {
		super();
		this.mealId = mealId;
		this.userId = userId;
		this.date = date;
		this.mealType = mealType;
		this.items = items;
	}

	public int getMealId() {
		return mealId;
	}

	public void setMealId(int mealId) {
		this.mealId = mealId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public List<MealItem> getItems() {
		return items;
	}

	public void setItems(List<MealItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "MealRecord [mealId=" + mealId + ", userId=" + userId + ", date=" + date + ", mealType=" + mealType
				+ ", items=" + items + "]";
	}
	
}
