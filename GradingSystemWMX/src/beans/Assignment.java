package beans;

import java.util.ArrayList;

public class Assignment {


	private String assignName;
	private Double weightG;
	private Double weightU;
	private String category;
	private String comments = null;
	private int assignID;
	private int classID;
	
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
	


	public Assignment(String assignName, Double weightG, Double weightU, String category, String comments, int assignID,
			int classID) {
		super();
		this.assignName = assignName;
		this.weightG = weightG;
		this.weightU = weightU;
		this.category = category;
		this.comments = comments;
		this.assignID = assignID;
		this.classID = classID;
	}


	public String getAssignName() {
		return assignName;
	}



	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}



	public Double getWeightG() {
		return weightG;
	}



	public void setWeightG(Double weightG) {
		this.weightG = weightG;
	}



	public Double getWeightU() {
		return weightU;
	}



	public void setWeightU(Double weightU) {
		this.weightU = weightU;
	}



	public String getAssignCat() {
		return category;
	}



	public void setAssignCat(String category) {
		this.category = category;
	}



	public String getComment() {
		return comments;
	}



	public void setComment(String comments) {
		this.comments = comments;
	}



	public int getAssignID() {
		return assignID;
	}



	public void setAssignID(int assignID) {
		this.assignID = assignID;
	}



	public int getClassID() {
		return classID;
	}



	public void setClassID(int classID) {
		this.classID = classID;
	}



	public ArrayList<String> printInfo() {
		// TODO Auto-generated method stub
		ArrayList<String> printInfo = new ArrayList<>();
		printInfo.add(getAssignName());
		printInfo.add("" + getWeightG());
		printInfo.add("" + getWeightU());
		printInfo.add(getAssignCat());
		return printInfo;
	}
	
}
