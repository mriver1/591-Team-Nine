package beans;

public class Grade {
	private int ID;
	private int studentID;
	private int classID;
	private int assignID;
	private String comments;
	private Double grade;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public int getAssignID() {
		return assignID;
	}

	public void setAssignID(int assignID) {
		this.assignID = assignID;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Double getScore() {
		return grade;
	}

	public void setScore(Double grade) {
		this.grade = grade;
	}


	public Grade(int ID, int studentID, int classID, int assignID, Double grade) {
		super();
		this.ID = ID;
		this.studentID = studentID;
		this.classID = classID;
		this.assignID = assignID;
		this.grade = grade;
	}

	public Grade() {
		// TODO Auto-generated constructor stub
	}
	
	
}
