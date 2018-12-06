package beans;

public class Course {
	private String className;
	private String classID;
	private String classTerm;
	private String classYear;
	private Float classCredit; 
	private int uniqueID;
	private static String[] categories= {
			"Homework",
			"Midterm",
			"Project",
			"Final",
			"Extra",
			"Attendence",
			"Quiz"
		};
	

	public Course() 
	{
		
	}
	public Course(String className, String classID, String classTerm, String classYear, Float classCredit,
			int uniqueID) {
		this.className = className;
		this.classID = classID;
		this.classTerm = classTerm;
		this.classYear = classYear;
		this.classCredit = classCredit;
		this.uniqueID = uniqueID;
	}

	public static String[] getCategories() {
		return categories;
	}
	public static void setCategories(String[] categories) {
		Course.categories = categories;
	}
	
	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public String getClassTerm() {
		return classTerm;
	}
	public void setClassTerm(String classTerm) {
		this.classTerm = classTerm;
	}
	public String getClassYear() {
		return classYear;
	}
	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}
	public Float getClassCredit() {
		return classCredit;
	}
	public void setClassCredit(Float classCredit) {
		this.classCredit = classCredit;
	}
	

}
