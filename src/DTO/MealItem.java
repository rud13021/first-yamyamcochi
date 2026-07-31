package DTO;

/**
 * 식단 작성 입력(하나만)을 받기 위한 DTO
 * @Food : Food DTO 정보를 받아옴
 * @amountGram : 몇 g이나 먹었는지 입력 받음
 */
public class MealItem {
	Food food;
	double amountGram;
	// 필요시 itemNo 추가.
	
	
	public MealItem() {}
	
	public MealItem(Food food, double amountGram) {
		super();
		this.food = food;
		this.amountGram = amountGram;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public double getAmountGram() {
		return amountGram;
	}

	public void setAmountGram(double amountGram) {
		this.amountGram = amountGram;
	}

	@Override
	public String toString() {
		return "MealItem [food=" + food + ", amountGram=" + amountGram + "]";
	}
	
	
}
