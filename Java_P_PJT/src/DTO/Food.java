package DTO;

//기본 음식 데이터 구조(xlsx에서 읽어올 음식 원본 데이터)
/**
 * 기본 음식 데이터 구조(xlsx에서 읽어올 음식 원본 데이터)
 * @foodCode : 식품코드(고유번호)
 * @foodName : 식품 이름
 * @servingSize : 1회 기준량(ex. 100g, 100ml)
 * @calorie : 칼로리(1회 기준량 당)
 * @protein : 단백질(1회 기준량 당)
 * @carbohydrate : 탄수화물(1회 기준량 당)
 * @fat : 지방(1회 기준량 당)
 * @sugar : 당류(1회 기준량 당)
 * @sodium : 나트륨(1회 기준량 당)
 */
public class Food {
	  private String foodCode; // 식품코드 엑셀 인덱스 0
	  private String foodName; // 식품명 엑셀 인덱스 1
	  private String servingSize; // 1회 기준량 예: 100g,100ml 엑셀 인덱스 16
	  private double calorie; // 칼로리 엑셀 인덱스 17
	  private double protein; // 단백질 엑셀 인덱스 19
	  private double carbohydrate; // 탄수화물 엑셀 인덱스 20
	  private double fat; // 지방 엑셀 인덱스 21
	  private double sugar; // 당류 엑셀 인덱스 22
	  private double sodium; // 나트륨 엑셀 인덱스 31
      
	  // 얘는 음식마다 객체가 달라야하니까
	  // 싱글톤 패턴으로 private 하면 안된다.
      public Food() {}

	  public Food(String foodCode, String foodName, String servingSize, double calorie, double protein,
			double carbohydrate, double fat, double sugar, double sodium) {
		super();
		this.foodCode = foodCode;
		this.foodName = foodName;
		this.servingSize = servingSize;
		this.calorie = calorie;
		this.protein = protein;
		this.carbohydrate = carbohydrate;
		this.fat = fat;
		this.sugar = sugar;
		this.sodium = sodium;
	  }

	  public String getFoodCode() {
		  return foodCode;
	  }

	  public void setFoodCode(String foodCode) {
		  this.foodCode = foodCode;
	  }

	  public String getFoodName() {
		  return foodName;
	  }

	  public void setFoodName(String foodName) {
		  this.foodName = foodName;
	  }

	  public String getServingSize() {
		  return servingSize;
	  }

	  public void setServingSize(String servingSize) {
		  this.servingSize = servingSize;
	  }

	  public double getCalorie() {
		  return calorie;
	  }

	  public void setCalorie(double calorie) {
		  this.calorie = calorie;
	  }

	  public double getProtein() {
		  return protein;
	  }

	  public void setProtein(double protein) {
		  this.protein = protein;
	  }

	  public double getCarbohydrate() {
		  return carbohydrate;
	  }

	  public void setCarbohydrate(double carbohydrate) {
		  this.carbohydrate = carbohydrate;
	  }

	  public double getFat() {
		  return fat;
	  }

	  public void setFat(double fat) {
		  this.fat = fat;
	  }

	  public double getSugar() {
		  return sugar;
	  }

	  public void setSugar(double sugar) {
		  this.sugar = sugar;
	  }

	  public double getSodium() {
		  return sodium;
	  }

	  public void setSodium(double sodium) {
		  this.sodium = sodium;
	  }

	  @Override
	  public String toString() {
		return "Food [foodCode=" + foodCode + ", foodName=" + foodName + ", servingSize=" + servingSize + ", calorie="
				+ calorie + ", protein=" + protein + ", carbohydrate=" + carbohydrate + ", fat=" + fat + ", sugar="
				+ sugar + ", sodium=" + sodium + "]";
	  }     
      
      
}
