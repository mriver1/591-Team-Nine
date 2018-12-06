package beans;

import java.util.ArrayList;

public class Assignment {


	private String assignName;
	private Float weightG;
	private Float weightU;
	private Float grade; 
	private int assignID;
	private static String assignCat;
	
	public static String[] categories= {
			"Homework",
			"Midterm",
			"Project",
			"Final",
			"Extra",
			"Attendence",
			"Quiz"
		};


	public Assignment() {
		
	}
	
	public Assignment(String assignName, Float weightG, Float weightU, Float grade, int assignID) {
		super();
		this.assignName = assignName;
		this.weightG = weightG;
		this.weightU = weightU;
		this.grade = grade;
		this.assignID = assignID;
	}

	public String getName() {
		return assignName; 
	}
	
	public String getCategory( ) {
		return assignCat;
	}
	
	public int getID() {
		return assignID;
	}
	
	public Float getWeightGraduate() {
		
		return weightG; 
	
	}
	
	public Float getWeightUndergraduate() {
		
		return weightU;
	}
	public Float getGrade() {
		return grade; 
	}

	public void setName(String assignName) {
		
	}
	
	public void setID(int assignID) {
		
	}
	
	public void setCategory(String assignCat) {
		
	}
	
	public void setWeighG(Float wg) {
		
	}
	
	public void setWeightU(Float wu) {
		
	}
	
	public void setGrade(Float grade) {
		
	}

	public ArrayList<String> printInfo() {
		// TODO Auto-generated method stub
		ArrayList<String> print = new ArrayList<>();
		print.add(getName());
		print.add("" + getWeightGraduate());
		print.add("" + getWeightUndergraduate());
		print.add(getCategory());
		return print;
	}
	
}
