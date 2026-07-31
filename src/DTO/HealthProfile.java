package DTO;

public class HealthProfile {

	private double height; // 키
	private double weight; // 몸무게
	private String goal; // 목표
	
	public HealthProfile() {
		this.height = 0;
		this.weight = 0;
		this.goal = "미설정";
	}
	
	public HealthProfile(double height, double weight, String goal) {
		this.height = height;
		this.weight = weight;
		this.goal = goal;
	}
	
	//----------------------------
	
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	//----------------------------

	@Override
	public String toString() {
		return "HealthProfile [height=" + height + ", weight=" + weight + ", goal=" + goal + "]";
	}
}
